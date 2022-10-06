<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="_inc/inc_head.jsp"%>
<%@ page import="vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.contents { width:100%; display:flex; }
.contents .main_01 { font-size:20px; width:100%; display:initial; }
.contents .main_01 .main_title { display:flex; width:1200px; margin:0px 13%; }
.contents .main_01 .main_title h1 { width:1200px; margin:15px 0 25px; }
.contents .main_01 .hotplace { display:flex; justify-content:space-around; box-sizing:content-box; padding:0px 160px 50px; text-align:center; }
.contents .hotplace div a img { border-radius:75px; }
.contents .hotplace .text { font-size:15px; }
</style>
</head>
<body>
	<!-- 얘도 나중에 로그인 폼으로 보내야 함 -->
<section class="">
	<div class="contents">
		<div class="main_01">
			<header class="main_title">
				<span>인기 여행지</span>
			</header>

			<div class="hotplace">
				<div>
					<a href="#" class="area" id="jeju">
					<img src="/woodaProject/img/jeju.jpg" width="170" height="170" style="opacity: 0.7;"><div class="text">제주도</div>
					</a>
				</div>

				<div>
					<a href="#" class="area" id="gangwon">
					<img src="/woodaProject/img/gangwon.jpg" width="160" height="160" style="opacity: 0.7;"><div class="text">강원도</div>
					</a>
				</div>

				<div>
					<a href="#" class="area" id="busan">
					<img src="/woodaProject/img/busan.jpg" width="160" height="160" style="opacity: 0.7;"><div class="text">부산</div>
					</a>
				</div>

				<div>
					<a href="#" class="area" id="gyeongbuk">
					<img src="/woodaProject/img/gyeongbuk.jpg" width=160 height="160" style="opacity: 0.7;"><div class="text">경상북도</div>
					</a>
				</div>

				<div>
					<a href="#" class="area" id="gwangju">
					<img src="/woodaProject/img/gwangju.jpg" width="160" height="160" style="opacity: 0.7;"><div class="text">광주</div>
					</a>
				</div>

			</div>
		</div>
	</div>
</section>



<section>
	<div style="height:500px; font-size:20px;">
		<div border="1" align="left" style="height:70px; font-size:20px;">추천 게시물</div>

		<div class="theme" style="font-size: 15px;">
			<button onclick="">테마1</button>
			<button onclick="">테마2</button>
			<button onclick="">테마3</button>
			<button onclick="">테마4</button>
		</div>

		<div class="reboard">
			<a href="/diary_write_view"></a>
			<table>

			</table>

		</div>
	</div>
</section>


<section>
	<div id="gift"></div>
</section>
<section>
	<div id="logo"></div>
</section>
<section>
	<div id="desc">
		<address>
			회사명 : 그린컴퓨터503 | 대표이사 : 조삼삼 | 사업자 등록번호 : 333-13-32133 | 메일 :
			jo333@samjo.com | 주소 : 서울시 강남구 역삼동 815-4 만이빌딩 5층, 10층 | 고객센터 :
			1588-0000 | 운영 : 월 ~ 금, 09:30 ~ 18:20<br />
		</address>
	</div>
</section>
</body>
</html>
