<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp"%>
<%
	// 회원 전용 페이지 로그인 확인
	if (!isLogin) {
		out.println("<script> alert('로그인 전용 페이지 입니다.'); location.href='login_form.jsp'; </script>");
		out.close();
	}
%>
<style>
hr {
	width: 1150px;
	color: #ccc;
}

#list th {
	border-bottom: double black 3px;
}

#profileImg {
	width: 150px;
	height: 150px;
}

#profileDiv {
	width: 150px;
	height: 150px;
	border-radius: 70%;
	overflow: hidden;
}

#profileZone {
	height: 350px;
}

fieldset {
	border: 0;
}

table {
	width: 1150px;
	height: 330px;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
	box-sizing: border-box;
	border: 0;
}

table th, table td {
	border: 1px solid #ccc;
	padding: 10px;
}

#profileTable3 {
	height: 100px;
}

.profileTable {
	padding: 0 0px;
	box-sizing: border-box;
}

.profileTable th, .profileTable td {
	border: 1px solid #ccc;
	padding: 10px;
}

.display_none {
	display: none;
}

* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
}

.wrap {
	padding: 10px;
}

#btn_open {
	font-weight: bold;
	margin: 5px;
	padding: 4px 6px;
	background: #000;
	color: #fff;
}

#profile_img {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: rgba(0, 0, 0, .5);
	font-size: 0;
	text-align: center;
}

#profile_img:after {
	display: inline-block;
	height: 100%;
	vertical-align: middle;
	content: '';
}

#profile_img .pop_inner {
	display: inline-block;
	padding: 20px 30px;
	background: #fff;
	width: 200px;
	vertical-align: middle;
	font-size: 15px;
}
</style>


<Script>
	var openWin;
	function openSendCard() {
		var _width = '650';
		var _height = '380';
		// window.name = "부모창 이름";  
		window.name = "myPage";
		// 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
		var _left = Math.ceil((window.screen.width - _width) / 2);
		var _top = Math.ceil((window.screen.height - _height) / 2);
		// window.open("open할 window", "자식창 이름", "팝업창 옵션");
		openWin = window.open("mypage/send_card.jsp", "sendCard", "top=" + _top
				+ ",left=" + _left + ",width=400,height=400");
	}
	function frmCom(kind) {
		var frms = document.forms;
		if (kind == "a" || kind == "i") {
			alert("1");
			frms[0].submit();
		} else if (kind == "b") {
			alert("2");
			frms[1].submit();
		} else if (kind == "c") {
			alert("3");
			frms[2].submit();
		}
	}
	window.onload = function() {
		const target = document.querySelector('#profile_img');
		const btn_open = document.querySelector('#btn_open');
		const btn_close = document.querySelector('#btn_close');
		btn_open.addEventListener('click', function() {
			target.classList.remove('display_none');
		});
		btn_close.addEventListener('click', function() {
			target.classList.add('display_none');
		});
	}
	$(function() {
	    $("#myFile").on('change', function(){
	    readURL(this);
	    });
	});
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	        $('#View').attr('src', e.target.result);
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	function checkSize(input) {
	    if (input.files && input.files[0].size > (512 * 1024)) {
	        alert("파일 사이즈가 512kb 를 넘습니다.");
	        input.value = null;
	    }
	}
</Script>
</head>
<body>
	<h2>프로필 수정</h2>
	<div class="wrap">
		<div id="profile_img" class="display_none">
			<div class="pop_inner">
				<form id="imgForm" name="imgForm" action="../uploadPartProc" method="post" enctype="multipart/form-data">
					<input type="hidden" name="mi_mail" value="<%=loginInfo.getMi_mail() %>"/>
					<input type="file" id="myFile" name="img_file" accept="image/jpg, image/jpeg, image/png, image/png" onchange="checkSize(this);"/> <div id="profileDiv" class="profileImg"><img id="View" name="View" src="#" alt=" " style="width:100%; height:100%;"/></div>
					<input type="submit" value="설정"/>
					<button type="button" id="btn_close">닫기</button>
				</form>
			</div>
		</div>
		<div id="profileZone">
			<form name="forms" action="../my_page_up" method="post">
				<input type="hidden" name="mi_mail"
					value="<%=loginInfo.getMi_mail()%>" /> <input type="hidden"
					name="kind" value="a" />
				<table id="profileTable1" border="1" class="profileTable">
					<tr>
						<td rowspan="3" width="30%"><div id="profileDiv">
								<img id="profileImg" name="img" src="../img/<%=loginInfo.getMi_pimg()%>" />
							</div> <br /> <a href="#pop_info_1" id="btn_open">프로필 사진 변경</a></td>
						<td align="left">닉네임 : <input type="text" name="nick"
							value="<%=loginInfo.getMi_nick()%>" /></td>

					</tr>
					<tr>
						<%
							if (loginInfo.getMi_iscouple().equals("y")) {
						%>
						<td align="left">커플 닉네임 : <input type="text" name="cnick"
							value="<%=coupleInfo.getCi_nick()%>" /></td>
						<%
							} else {
						%>
						<td align="left">커플 닉네임 : 연인을 등록해 주세요</td>
						<%
							}
						%>
					</tr>
					<tr>
						<%
							if (loginInfo.getMi_iscouple().equals("y")) {
						%>
						<td align="left">D + day : <input type="date" name="jdate"
							value="<%=coupleInfo.getCm_jdate().substring(0, 10)%>" /></td>
						<%
							} else {
						%>
						<td align="left">D + day : 연인을 등록해 주세요</td>
						<%
							}
						%>
					</tr>
				</table>
			</form>
			<input type=button name=profileModify value="수정 완료"
				onclick="frmCom('a');" />
		</div>
		<hr />
		<div id="profileZone">
			<form name="forms" action="../my_page_up" method="post">
				<input type="hidden" name="kind" value="b" /> <input type="hidden"
					name="mi_mail" value="<%=loginInfo.getMi_mail()%>" />
				<table id="profileTable2" border="1">
					<tr>
						<td rowspan="3" width="30%"><span>개인정보 수정</span></td>
						<td align="left">이름 : <input type="text" name="name"
							value="<%=loginInfo.getMi_name()%>" /></td>
					</tr>
					<tr>
						<td align="left">비밀번호 : <input type="text" name="pwd" /></td>
					</tr>
					<tr>
						<td align="left">비밀번호 확인 : <input type="text" name="pwd" /></td>
					</tr>
				</table>
			</form>
			<input type=button name=profileModify value="수정 완료"
				onclick="frmCom('b');" />
		</div>
		<hr />
		<div id="profileZone">
			<form name="forms" action="../my_page_up" method="post">
				<input type="hidden" name="mi_mail"
					value="<%=loginInfo.getMi_mail()%>" /> <input type="hidden"
					name="ci_idx" value="<%=loginInfo.getCi_idx()%>" /> <input
					type="hidden" name="kind" value="c" />
				<table id="profileTable3" border="1">
					<tr>
						<td rowspan="3" width="30%"><span>연인정보 수정</span></td>
						<%
							if (loginInfo.getMi_iscouple().equals("y")) {
						%>
						<td align="center"><input type="button" value="연인 등록 해제"
							onclick="frmCom('c');" /></td>
						<%
							} else {
						%>
						<td align="center"><input type="button" value="연인 등록 해제"
							disabled="disabled" /></td>
						<%
							}
						%>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<br />
</body>
</html>