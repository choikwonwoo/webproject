use wooda;

delete from t_member_info;
 -- 회원 정보 테이블			
create table t_member_info (
	mi_mail varchar(50)	primary key, 			-- 이메일
	mi_nick varchar(20)	unique, 				-- 닉네임
	mi_pw	varchar(20)	not null, 				-- 비밀번호
	mi_name varchar(20) not null, 				--  이름
	mi_birth char(8) not null, 					-- 생년원일
	mi_gender char(1) default 'F', 			-- 성별
    mi_pimg varchar(50) default '../img/p.jpg', -- 프로필 이미지 / 기본이미지 주소
	mi_iscouple char(1) default	's',			-- 연인 유무
	mi_status char(1) default 'a', 			-- 상태
	mi_join	datetime default now(), 			-- 가입일
	mi_last	datetime,							-- 최종 로그인일자
	ci_idx varchar(10),	 				-- 커플일련번호
    mi_dding varchar(1)	default	'n'				-- 인증요청 관련 여부 확인 보냄 a, 받음 b, n(안보냄, 안받음)
);
update t_member_info set mi_dding = 'a' where mi_mail =  '" + cm.getCm_mail_s()"';
update t_member_info set mi_nick = '우다다닥' where mi_mail = 'wooda@naver.com';
select * from t_member_info;
select * from t_couple_mailing;t_order_info
insert into t_couple_mailing (cm_mail_s, cm_mail_r, cm_content, cm_jdate) values('wooda@naver.com', 'wooda', '123', '1997-08-25');
delete from t_couple_mailing where cm_mail_s = 'wooda@naver.com';
-- 메일주고받기 테이블
create table t_couple_mailing ( 			
	cm_mail_s varchar(50) primary key,	-- 발신자 메일
	cm_mail_r varchar(50),					-- 수신자 메일 
	cm_content varchar(100) not null,			-- 짧은 메시지
	cm_jdate datetime null, 					-- 사귄날짜
	cm_sdate datetime default now(),			-- 보낸날짜
	cm_isok	char(1) default	'b',				-- 수락여부 
	cm_ydate datetime null					-- 수락한 날짜
);


-- 연인 정보 테이블	
create table t_couple_info (  		
	ci_idx varchar(10) primary key,	-- 커플 일련번호
	ci_nick varchar(20)	unique,			-- 커플 닉네임
	ci_mail_m varchar(50),				-- 남자
	ci_mail_f varchar(50),				-- 여자
	ci_break char(1) default 'n',		-- 결별여부
	constraint foreign key (ci_mail_m) references t_member_info(mi_mail),
	constraint foreign key (ci_mail_f) references t_member_info(mi_mail)
);




-- 여행후기 테이블
-- 여행후기 테이블
select * from t_board_story where bs_area = 'a';
select count(*) cnt from t_board_story where bs_astatus = 'y';
-- 여행후기 테이블
create table t_board_story (                     
   bs_num int primary key,                -- 글번호
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


insert into t_board_story(bs_num, mi_mail, mi_nick, bs_area, bs_title, bs_start, bs_end, bs_content, bs_visit, bs_gender, bs_lat1, bs_lng1, bs_place1, bs_lat2, bs_lng2, bs_place2, bs_lat3, bs_lng3,
bs_place3, bs_img1, bs_img2, bs_img3, bs_astatus, bs_cstatus, bs_ip) 
values (1, 'wooda@naver.com', '우다', 'a', '여행 다녀왔어요.', '2022-09-01', '2022-09-01', '너무 재밌었어요. 히히히히', 'y', 'w',  58.0, 127.0, '제주도', 37.1, 150.8, '부산', 132.5, 37.9,
'대전', '../a.jpg', '', '', 'y', 'y', '123.123.123.123');

select * from t_couple_mailing;

-- 조아요 테이블
create table t_board_like (
bl_bum int auto_increment primary key,
bs_num int not null,
mi_mail varchar(20) not null,
bl_like int default 0,
constraint foreign key (bs_num) references t_board_story(bs_num),
constraint foreign key (mi_mail) references t_member_info(mi_mail)
);

-- 게시글 댓글 테이블
create table t_board_reply  (								
br_num int primary key,							-- 댓글번호
bs_num int not null,									-- 글번호
mi_nick varchar(20)	not null,				-- 닉네임
br_content	varchar(100)	not null,		-- 내용
br_ip	varchar(15)	not null,					-- IP주소
br_date	datetime default	now(), 			-- 작성일t_gift_info
br_isview	char(1) default	'y', 					-- 게시여부
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


-- 여행코스 테이블
create table t_research_area (		
ra_idx	int  primary key,			-- 일련번호
bs_num int	not null,				-- 글번호
ra_long FLOAT default 0, 			-- 위도
ra_lati FLOAT default 0, 			-- 경도
ra_place varchar(20),				-- 장소 이름
constraint foreign key (bs_num) references t_board_story(bs_num)
);




-- 선물 등록 정보 테이블
create table t_gift_info (							
gi_id	char(7) primary key,					-- 상품ID
mi_mail varchar(50) not null,			-- 이메일
gi_brand	char(2)	not null,						-- 브랜드
gi_price int default 0,						-- 가격 
gi_tag1 varchar(50)	not null,			-- 태그1 main
gi_tag2 varchar(50)	,						-- 태그2
gi_tag3 varchar(50)	,						-- 태그3
gi_tag4 varchar(50)	,						-- 태그4
gi_tag5 varchar(50)	,						-- 태그5
gi_content varchar(100)	not null,	-- 후기 내용
gi_img1 varchar(50)	not null,			-- 상품이미지1
gi_img2 varchar(50)	,						-- 상품이미지2
gi_img3 varchar(50)	,						-- 상품이미지3
gi_like int default 0	, 						-- 좋아요
gi_isview char(1) default	'n',			-- 게시여부
gi_date	datetime default	now(),		-- 등록일
constraint foreign key (mi_mail) references t_member_info(mi_mail)
);


-- 관리자 테이블
create table t_admin_info ( 			
ai_id	varchar(20)	primary key,			-- 아이디
ai_pw varchar(20)	not null,			-- 비밀번호
ai_name varchar(20)	not null,			-- 이름
ai_date	datetime default	now()		-- 등록일
);


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
call sp_member_join ('wooda', '우우', '1234', '우다닥', '19950825', 'f');
 select * from t_member_info;
 select * from t_mailing;
 
-- 게시물등록 프로시저 - sp_board_stock_insert
drop procedure if exists sp_board_stock_insert;
 delimiter $$
create procedure sp_board_stock_insert(bsnum int, mimail varchar(20), minick varchar(20), bsarea char(1), bstitle varchar(50), bsstart char(10), 
bsend char(10), bsgoal char(1), bsage char(1), bscontent text, 
bsstartlat FLOAT, bsstartlng FLOAT, bsstartplace varchar(20), 
bsendlat FLOAT, bsendlng FLOAT, bsendplace varchar(20),
bsvialat FLOAT, bsvialng FLOAT, bsviaplace varchar(20),
bsimg1 varchar(50), bsimg2 varchar(50), bsimg3 varchar(50), 
bsimg4 varchar(50), bsimg5 varchar(50), bsip varchar(15))
begin
	insert into t_board_story (bs_num, mi_mail, mi_nick, bs_area, bs_title, bs_start, bs_end, bs_goal, bs_age, bs_content, bs_start_lat, bs_start_lng, bs_start_place, bs_end_lat, bs_end_lng, bs_end_place, bs_via_lat, bs_via_lng, bs_via_place, bs_img1, bs_img2, bs_img3, bs_img4, bs_img5, bs_ip)
	values(bsnum, mimail, minick, bsarea, bstitle, bsstart, bsend, bsgoal, bsage, bscontent, bsstartlat, bsstartlng, bsstartplace, bsendlat, bsendlng, bsendplace, bsvialat, bsvialng, bsviaplace, bsimg1, bsimg2, bsimg3, bsimg4, bsimg5, bsip);
end $$
delimiter ;
call sp_board_stock_insert (1, 'wooda@naver.com', '우다', 'a', '여행 다녀왔어요.', '2022-09-01', '2022-09-01', 'a', 'a', '너무 재밌었어요. 히히히히', 58.0, 127.0, '제주도', 37.1, 150.8, '부산', 132.5, 37.9, '대전', '../a.jpg', '', '', '', '', '123.123.123.123');





	
	bs_like	int default 0, 						-- 좋아요(숫자)
	bs_read int default 0, 						-- 조회수
	bs_ip varchar(15) not null,					-- IP주소
	bs_date datetime default	now(), 			-- 작성일
	bs_isview	char(1) default	'y', 			-- 게시여부
	constraint foreign key (mi_mail) references t_member_info(mi_mail)


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
    set old_nick = old.mi_nick;	-- update 전 상품가격
    set new_nick = new.mi_nick;	-- update 후 상품가격

	if old_nick <> new_nick then	-- update로 가격을 변경할 경우
		update t_board_story set mi_nick = new_nick where mi_nick = old_nick;
	end if;
end $$
delimiter ;





















