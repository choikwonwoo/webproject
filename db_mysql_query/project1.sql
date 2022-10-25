use wooda;

drop table t_gift_info;
drop table t_member_info;


create table t_member_info (
	mi_mail varchar(50)	primary key, 			-- 이메일
	mi_nick varchar(20)	unique, 				-- 닉네임
	mi_pw	varchar(20)	not null, 				-- 비밀번호
	mi_name varchar(20) not null, 				--  이름
	mi_birth char(10) not null, 					-- 생년원일
	mi_gender char(1) default 'F', 			-- 성별
    mi_pimg varchar(50) default '', -- 프로필 이미지 / 기본이미지 주소
	mi_iscouple char(1) default	'n',			-- 연인 유무
	mi_status char(1) default 'Y', 			-- 상태
	mi_join	datetime default now(), 			-- 가입일
	mi_last	datetime,							-- 최종 로그인일자
	ci_idx varchar(10),	 				-- 커플일련번호
    ci_nick varchar(20)	,			-- 커플 닉네임
    mi_dding varchar(1)	default	'n'				-- 인증요청 관련 여부 확인 보냄 a, 받음 b, 초기 c, n(안보냄, 안받음)
    
);

-- 메일주고받기 테이블
create table t_couple_mailing ( 			
	cm_mail_s varchar(50) primary key,	-- 발신자 메일
    ci_idx varchar(10),					--  커플 일련 번호
    mi_name_s varchar(20) not null,
	cm_mail_r varchar(50),					-- 수신자 메일
    mi_name_r varchar(20) not null,
	cm_content varchar(100) not null,			-- 짧은 메시지
	cm_jdate date null, 					-- 사귄날짜
	cm_sdate datetime default now(),			-- 보낸날짜
	cm_isok	char(1) default	'n',				-- 수락여부 
	cm_ydate datetime null				-- 수락한 날짜
);

select * from  t_board_story;
-- 연인 정보 테이블	
create table t_couple_info (  		
	ci_idx  varchar(20) primary key,	-- 커플 일련번호
	ci_nick varchar(20)	,			-- 커플 닉네임
	ci_mail_r varchar(50),				-- 수신자
	ci_mail_s varchar(50),				-- 송신자
	ci_break char(1) default 'n',		-- 결별여부
    cm_jdate date not null			-- 사귄 날짜
);

-- 여행후기 테이블
create table t_board_story (                     
   bs_num int primary key,                -- 글번호
   ci_idx varchar(20),						-- 커플 정보
    mi_mail varchar(20) not null,            -- 이메일
   mi_nick varchar(20)   not null,            -- 닉네임
    bs_area char(1) default 'a',            -- 여행지역
   bs_title   varchar(50)   not null,         -- 제목
   bs_start char(10)   not null,            -- 여행 시작일
   bs_end   char(10)   not null,            -- 여행 종료일
   bs_content text   not null,               -- 내용
    bs_visit char(1) default 'n',      -- 재방문 여부
    bs_gender char(1) default 'm',      -- 성별
    bs_lat1 double default 0,          -- 위도 시작
   bs_lng1 double default 0,          -- 경도 시작
   bs_place1 varchar(20),            -- 장소 이름 시작
   bs_lat2 double default 0,          -- 위도 종료
   bs_lng2 double default 0,          -- 경도 종료
   bs_place2 varchar(20),            -- 장소 이름 종료
   bs_lat3 double default 0,          -- 위도 경유
   bs_lng3 double default 0,          -- 경도 경유
   bs_place3 varchar(20),            -- 장소 이름 경유
   bs_img1 varchar(50)   not null,            -- 이미지 1
   bs_img2 varchar(50) ,                  -- 이미지 2
   bs_img3 varchar(50),                   -- 이미지 3
   bs_img4 varchar(50),                    -- 이미지 4
   bs_img5 varchar(50),                   -- 이미지 5
   bs_astatus char(1) default   'n',          -- 전체공개 여부
   bs_cstatus char(1) default   'n',          -- 연인공개 여부
   bs_mstatus char(1) default   'y',          -- 나만보기 여부
   bs_like   int default 0,                   -- 좋아요(숫자)
   bs_read int default 0,                   -- 조회수
   bs_ip varchar(15) not null,               -- IP주소
   bs_date datetime default now(),          -- 작성일
   bs_isview char(1) default'y',
   constraint foreign key (mi_mail) references t_member_info(mi_mail)
);

-- 조아요 테이블
create table t_board_like (
bl_bum int auto_increment primary key,
bs_num int not null,
mi_mail varchar(20) not null,
bl_like int default 0,
constraint foreign key (bs_num) references t_board_story(bs_num),
constraint foreign key (mi_mail) references t_member_info(mi_mail)
);


insert into t_board_story(bs_num, mi_mail, mi_nick, bs_area, bs_title, bs_start, bs_end, bs_content, bs_visit, bs_gender, bs_lat1, bs_lng1, bs_place1, bs_lat2, bs_lng2, bs_place2, bs_lat3, bs_lng3,
bs_place3, bs_img1, bs_img2, bs_img3, bs_astatus, bs_cstatus, bs_ip) 
values (1, 'wooda@naver.com', '우다', 'a', '여행 다녀왔어요.', '2022-09-01', '2022-09-01', '너무 재밌었어요. 히히히히', 'y', 'w',  58.0, 127.0, '제주도', 37.1, 150.8, '부산', 132.5, 37.9,
'대전', '../a.jpg', '', '', 'y', 'y', '123.123.123.123');

-- 게시글 댓글 테이블
create table t_board_reply  (                        
br_num int auto_increment primary key,                     -- 댓글번호
bs_num int not null,                           -- 글번호
mi_nick varchar(20)   not null,            -- 닉네임
br_content   varchar(100)   not null,      -- 내용
br_ip   varchar(15)   not null,               -- IP주소
br_date   datetime default   now(),          -- 작성일
br_isview   char(1) default   'y',                -- 게시여부
constraint foreign key (bs_num) references t_board_story(bs_num),
constraint foreign key (mi_nick) references t_member_info(mi_nick)
);


--  게시글 신고 테이블
create table t_board_112 (                      
b1_idx   int unique,         -- 일련번호
bs_num   int   not null,               -- 글번호
mi_mail   varchar(50)   not null,       -- 이메일
b1_content   varchar(100) not null,   -- 신고사유
b1_ip   varchar(15)   not null,         -- IP주소
b1_date   datetime default   now(),   -- 신고일
constraint t_board_112_pk primary key (bs_num,mi_mail),
constraint foreign key (bs_num) references t_board_story(bs_num),
constraint foreign key (mi_mail) references t_member_info(mi_mail)
);


-- 선물 등록 정보 테이블
create table t_gift_info (                     
gi_num   int auto_increment primary key,               -- 상품번호
mi_mail varchar(50) not null,         -- 이메일
gi_brand  varchar(50)   not null,                  -- 브랜드
gi_name  varchar(50)   not null,                  -- 상품명
gi_price int default 0,                  -- 가격 
gi_tag1 varchar(50)   not null,         -- 태그1 main
gi_tag2 varchar(50)   ,                  -- 태그2
gi_tag3 varchar(50)   ,                  -- 태그3
gi_tag4 varchar(50)   ,                  -- 태그4
gi_tag5 varchar(50)   ,                  -- 태그5
gi_content varchar(100)   not null,   -- 후기 내용
gi_img1 varchar(50)   not null,         -- 상품이미지1
gi_img2 varchar(50)   ,                  -- 상품이미지2
gi_img3 varchar(50)   ,                  -- 상품이미지3
gi_like int default 0   ,                   -- 좋아요
gi_isview char(1) default   'n',         -- 게시여부
gi_date   datetime default   now(),      -- 등록일
constraint foreign key (mi_mail) references t_member_info(mi_mail)
);

create table  t_admin_gift (
ag_num int auto_increment primary key,   -- 일련번호
ag_name varchar(50) not null,   -- caption 이름
ag_num1 int not null,        -- 첫번째 등록 상품
gi_img1 varchar(50)   not null,         -- 상품이미지1
gi_brand1  varchar(50)   not null,                   -- 브랜드
gi_name1  varchar(50)   not null,                     -- 상품명
gi_price1 int default 0,                                 -- 가격 
ag_num2 int,                  -- 두번째 등록 상vna
gi_img2 varchar(50) ,          -- 상품이미지1
gi_brand2  varchar(50),                    -- 브랜드
gi_name2  varchar(50),                        -- 상품명
gi_price2 int default 0,                                 -- 가격 
ag_num3 int,                 -- 세번째 등록 상품
gi_img3 varchar(50),            -- 상품이미지1
gi_brand3  varchar(50),                    -- 브랜드
gi_name3  varchar(50),                       -- 상품명
gi_price3 int default 0,                                 -- 가격 
ag_num4 int,                 -- 네번째 등록 상품  
gi_img4 varchar(50),            -- 상품이미지1
gi_brand4  varchar(50),                      -- 브랜드
gi_name4  varchar(50),                        -- 상품명
gi_price4 int default 0,                                 -- 가격 
ag_date datetime default now(),  -- 등록일
constraint foreign key (ag_num1) references t_gift_info(gi_num)
);

select bs_num, bs_img1, bs_title, bs_start, bs_end, bs_place1, bs_place2, bs_place3, bs_isview from t_board_story  order by bs_isview desc, bs_date desc  limit 0, 5;
-- 관리자 테이블
create table t_admin_info (          
ai_id   varchar(20)   primary key,         -- 아이디
ai_pw varchar(20)   not null,         -- 비밀번호
ai_name varchar(20)   not null,         -- 이름
ai_date   datetime default   now()      -- 등록일
);
insert into t_admin_info (ai_id,ai_pw,ai_name)  values('admin1', '1234', '홍');
select * from t_admin_info;
-- 메인 페이지 추첱 게시글 바꾸기
create table t_admin_main (
am_code char(1) primary key, -- 지역 알파벳 pk
am_name char(10) not null, -- 지역명
am_img varchar(50) not null, -- 지역 이미지
am_num	char(1)	-- 보여질 부분의 번호
);
select * from t_admin_main;
insert into t_admin_main values('a','서울','basic.jpg','4');
insert into t_admin_main values('b','경기도','basic.jpg','');
insert into t_admin_main values('c','인천','basic.jpg','');
insert into t_admin_main values('d','대구','basic.jpg','');
insert into t_admin_main values('e','충북','basic.jpg','0');
insert into t_admin_main values('f','충남','basic.jpg','');
insert into t_admin_main values('g','전북','basic.jpg','');
insert into t_admin_main values('h','전남','basic.jpg','1');
insert into t_admin_main values('i','강원도','basic.jpg','');
insert into t_admin_main values('j','광주','basic.jpg','');
insert into t_admin_main values('k','대전','basic.jpg','2');
insert into t_admin_main values('l','울산','basic.jpg','');
insert into t_admin_main values('m','부산','basic.jpg','');
insert into t_admin_main values('n','제주도','basic.jpg','3');
insert into t_admin_main values('o','경북','basic.jpg','');
insert into t_admin_main values('p','경남','basic.jpg','');


update t_admin_main set am_num = '0' where am_code = 'b';
-- 메인 시작시 관리자가 설정된 값을 불러오는 쿼리
select * from t_admin_main where am_num != '' order by am_num;


-- -----------------------------------------------------------------------

-- 회원가입 프로시저
drop procedure if exists sp_member_join;
 delimiter $$    
create procedure sp_member_join(
mimail varchar(50), minick varchar(20), mipw varchar(20), miname varchar(20), 
mibirth char(8), migender char(1))
begin    
   insert into t_member_info (mi_mail, mi_nick, mi_pw, mi_name, mi_birth, mi_gender)
   values(mimail, minick, mipw, miname, mibirth, migender);
end $$
delimiter ;

call sp_member_join ('wooda@naver.com', '우다', '1234', '우다다', '19970825', 'm');



-- 선물 등록 프로시저 - sp_gift_stock_insert
drop procedure if exists sp_gift_stock_insert;
 delimiter $$
create procedure sp_gift_stock_insert(giid char(7), mimail varchar(50), gibr char(2), giprice int, gitag1 varchar(50), gitag2 varchar(50), 
gitag3 varchar(50), gitag4 varchar(50), gitag5 varchar(50), gicontent varchar(100), giimg1 varchar(50), giimg2 varchar(50), giimg3 varchar(50), 
gilike int, giisview char(1), gidate datetime)
begin
   insert into t_gift_info (gi_id, mi_mail, gi_br, gi_price, gi_tag1, gi_tag2, gi_tag3, gi_tag4, gi_tag5, gi_content, gi_img1, 
                     gi_img2, gi_img3 )
   values(giid, mimail, giid, giprice, gitag1, gitag2, gitag3, gitag4, gitag5, gicontent, giimg1, giimg2, giimg3);
end $$
delimiter ;
call sp_gift_stock_insert ('', '', '', '', '', '', '', '', '', '', '', '', '');



-- -----------------------------------------------------------

-- 이메일 주고받기 수락 여부 쿼리 
select count(*) from t_couple_mailing 
where (mi_mail_s = 'test@test.com' and cs_isok <> 'n') 
or (mi_mail_r = 'test@test.com' and cs_isok <> 'n');


-- -----------------------------------------------------
-- 마이페이지 뷰() v_couple_stock_list
create view v_mypage as 
   select a.mi_mail, a.mi_nick, a.mi_pimg, b.bs_img1, b.bs_title, b.bs_content, b.bs_like, b.bs_read  
    from t_member_info a, t_board_story b 
    where a.mi_mail = b.mi_mail 
    group by a.mi_mail, a.mi_nick;
-- sum이나 count는 집계함수로 group by로 묶어줘야 함
select * from v_mypage;
-- t_member_info(닉네임, 이메일, 프로필사진) t_board_story (사진, 제목, 내용, 좋아요, 조회수)

drop trigger if exists tr_price;
delimiter $$
create trigger tr_change_nick after update on t_member_info for each row
begin
   declare old_nick, new_nick varchar(20);
   declare tpid char(6);
    set old_nick = old.mi_nick;   -- update 전 상품가격
    set new_nick = new.mi_nick;   -- update 후 상품가격

   if old_nick <> new_nick then   -- update로 가격을 변경할 경우
      update t_board_story set mi_nick = new_nick where mi_nick = old_nick;
   end if;
end $$
delimiter ;

drop trigger if exists tr_update_ciidx;
delimiter $$
create trigger tr_update_ciidx after insert on t_couple_info for each row
begin
   declare new_ciidx, new_mail_r,new_mail_s  varchar(20);
    set new_mail_r = new.ci_mail_r;
	set new_mail_s = new.ci_mail_s;
    set new_ciidx = new.ci_idx;   

	update t_board_story set ci_idx = new_ciidx where mi_mail = new_mail_r;
	update t_board_story set ci_idx = new_ciidx where mi_mail = new_mail_s;
end $$
delimiter ;
select sum(cnt) cnt from (select count(*) cnt from t_board_story where mi_mail = 'test1' union select count(*) cnt from t_board_story where bs_cstatus = 'y' and ci_idx = '') cnt;
select sum(cnt) from (select count(*) cnt from t_board_story where mi_mail = 'test1' union select count(*) cnt from t_board_story where bs_cstatus = 'y' and ci_idx = '') a;

update t_admin_main set am_num = ''; -- 하기전에 실행
update t_admin_main set an_num = case -- 하기
when am_code = '' then '0' 
when am_code = '' then '1' 
when am_code = '' then '2' 
when am_code = '' then '3' 
when am_code = '' then '4' end; 

-- 이미지관리자 프로시저
drop procedure if exists sp_update_img;
delimiter $$
create procedure sp_update_img(code0 char(1), code1 char(1), code2 char(1), code3 char(1), code4 char(1), img0 varchar(20), img1 varchar(20), img2 varchar(20), img3 varchar(20), img4 varchar(20))
begin
	update t_admin_main set am_num = '';
     
    update t_admin_main set am_num = '0' where am_code = code0;
    update t_admin_main set am_num = '1' where am_code = code1;
    update t_admin_main set am_num = '2' where am_code = code2;
    update t_admin_main set am_num = '3' where am_code = code3;
    update t_admin_main set am_num = '4' where am_code = code4;
    
    update t_admin_main set am_img = img0 where am_code = code0;
    update t_admin_main set am_img = img1 where am_code = code1;
    update t_admin_main set am_img = img2 where am_code = code2;
    update t_admin_main set am_img = img3 where am_code = code3;
    update t_admin_main set am_img = img4 where am_code = code4;
    
    
end$$ 
delimiter ;
call sp_update_img('f','b','c','d','e','f','b','c','d','e');


