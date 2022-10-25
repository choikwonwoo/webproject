<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="vo.*"%>
<%
	request.setCharacterEncoding("utf-8");

	ArrayList<BorderInfo> mainList = (ArrayList<BorderInfo>) request.getAttribute("mainList");
	ArrayList<AdminImg> adminImg = (ArrayList<AdminImg>) request.getAttribute("adminImg");

	String alpha = (String) request.getAttribute("alpha");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/woodaProject/js/jquery-3.6.1.js"></script>
<style>
.contents {width: 100%; display: flex; margin: 30px 0;}
.contents .main_01 {margin: 0 auto; font-size: 20px; width: 1150px;	display: initial;}
.contents .main_01 .main_title {display: flex; width: 1200px; margin: 0px 50px;}
.contents .main_01 .main_title span {width: 1200px; margin: 15px 0 25px;}
.wrapper {display: flex; justify-content: center; align-items: center; margin: 50px 0 20px 0;}
.contents .hotplace div a img {border-radius: 75px;}
.contents .hotplace {margin: 20 auto; display: flex; width: 1150px; justify-content: space-around;}

.text {font-size: 25px;}
#aa1 {position: relative; display: inline;}
#a1 {position: absolute; top: 80px; left: 62px;}
#aa2 {position: relative; display: inline;}
#a2 {position: absolute; top: 80px; left: 60px;}
#aa3 {position: relative; display: inline;}
#a3 {position: absolute; top: 80px; left: 73px;}
#aa4 {position: relative; display: inline;}
#a4 {position: absolute; top: 80px; left: 50px;}
#aa5 {position: relative; display: inline;}
#a5 {position: absolute; top: 80px; left: 73px;}

.area {position: relative; height: 200px; width: 200px; border-radius: 50%; margin: 0 auto; padding: 0; object-fit: cover;}
#img1 {width: 100px; height: 80px}
.wrapper .con_test_01 {margin: 0 auto; width: 1150px;}
.wrapper .con_test_01 .frm_test1 {display: flex; align-items: center; justify-content: flex-start; margin-top: 50px;}
.wrapper .con_test_01 .btn_test1 {background: white; border: 1px solid black; vertical-align: middle; padding: 10px; cursor: pointer; width: 140px; font-size: 16px; border-radius: 30px; font-weight: 600; margin: 0px 15px 0 0;}
.wrapper .con_test_01 .btn_test1:hover {background: #60c1ee; color: white; border: 1px solid #60c1ee;}
.sel_con_main {width: 100%; margin-bottom: 50px;} 
.sel_con_sub {width: 1150px; margin: 0 auto;}

.main_footer {background: lightgray; height: 100px;text-align: center; display: flex; align-items: center; justify-content: center; position: inherit; width: 100%; bottom: 0;}
</style>

</head>

<body>	
<%@ include file="_inc/inc_head.jsp"%>

<div class="div_main_con">
	<!-- 얘도 나중에 로그인 폼으로 보내야 함 -->
	<section class="">
		<div class="contents">
			<div class="main_01">
				<span>인기 여행지
					<form name="frm" action="diary_area_list" method="get">
						<div class="hotplace">
<% for (int i = 0; i<adminImg.size(); i++) {
	AdminImg ai = adminImg.get(i);
%>
							<div id="aa<%=i+1%>">
								<button type="submit" name="bs_area" class="area" value="<%=ai.getAm_code() %>" />
								<img src="/woodaProject/img/<%=ai.getAm_img() %>" class="area" width="170" height="170" style="opacity: 0.7;">
								<p id="a<%=i+1 %>" class="text"><%=ai.getAm_name() %></p>
								</button>
							</div>
						
<% } %>
				</span>
				</div>
			</div>
			</form>
		</div>
	</section>



	<div class="wrapper">
		<div class="con_test_01">
			<span style="font-size: 25px;">추천 게시물</span>
			<form name="frm1" action="diary_main_list" method="get"
				class="frm_test1">
				<button type="submit" name="alpha" value="a" onclick=""
					class="btn_test1">이달의 추천</button>
				<button type="submit" name="alpha" value="b" onclick=""
					class="btn_test1">가장 최근 글</button>
				<button typr="submit" name="alpha" value="c" onclick=""
					class="btn_test1">조회수 높은 글</button>
				<button type="submit" name="alpha" value="d" onclick=""
					class="btn_test1">좋아요 많은 글</button>
				<!-- ajax사용할 예정 -->
			</form>
		</div>
	</div>

	<div class="sel_con_main">
		<div class="sel_con_sub">
			<table width="800px" id="lo">

				<%
				if (mainList != null && mainList.size() != 0) {
					for (int i = 0; i < mainList.size(); i++) {
						BorderInfo bl = mainList.get(i);
				%>
				<th></th>
				<th></th>
				<th></th>
				<tr align="center">
					<td width="90px" rowspan="2">
						<%
							if (bl.getBs_img1().equals("")) {
						%> <img id="img1" src="img\trival.jpg" /> <%
					 	} else {
					 %> <img id="img1" src="img\<%=bl.getBs_img1()%>" /> <%
					 	}
					 %>
										</td>
										<td><%=bl.getBs_title()%></td>
										<td><%=bl.getBs_place1()%> <%
					 	if (bl.getBs_place2() != null && !bl.getBs_place2().equals("")) {
					 %> -> <%=bl.getBs_place2()%> <%
					 	}
					 %> <%
					 	if (bl.getBs_place3() != null && !bl.getBs_place3().equals("")) {
					 %> -> <%=bl.getBs_place3()%> <%
					 	}
					 %>
					 </td>
				</tr>

				<%
					}
					}
				%>

			</table>
		</div>
	</div>


	<section>
		<div class="gift"></div>
	</section>
	<section>
		<div class="logo"></div>
	</section>
	<section>
		<div class="main_footer">
			<p>
				회사명 : 그린컴퓨터503 | 대표이사 : 조삼삼 | 사업자번호 : 333-13-32133 | 메일 : jo333@samjo.com | 주소 : 서울시 강남구 역삼동 815-4 만이빌딩 5층, 10층 |<br>
				고객센터 : 금세인	[seins2doyou@gmail.com] 김성길 [gggills4235@gmail.com]<br>
				박세영[seyoung054@gmail.com] 박준혁 [junni802@naver.com] 최권우[mkp9928@gmail.com]
			</p>
		</div>
	</section>
</div>
</body>
</html>
