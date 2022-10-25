<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="_inc/inc_head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
  font-weight: bold;
  text-align: left;
  border-style: solid;
  border-width: 1px;
  border-color: #666666;
}

.wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top:50px
}
.text {
  width:500px;
  font-size:20px;
}
button{
  height:30px;
}
</style>
<script>
var popupWidth = 1000;
var popupHeight = 700;

var popupX = (window.screen.width / 2) - (popupWidth / 2);
// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

var popupY= (window.screen.height / 2) - (popupHeight / 2);
// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음

function showPopup()
{
	$.ajax({
		type : "POST",
		url : "/woodaProject/present_login_check",
		data : {},
		success : function(chkRs){
			if(chkRs > 0){
				window.name = "requestPrasent";
				openWin = window.open('request_parent.jsp', 'childForm', 'status=no, height=600, width=800, left='+ popupX + ', top='+ popupY);
			}else if(chkRs == 0){
				alert("로그인후 이용하시기 바랍니다.");
				window.location.reload();
			}else{
				alert("댓글 등록 실패!^0-");
				window.location.reload();
			}
		}
	});
	
}
</script>
</head>
<body>
<p class="wrapper">
<input type="text" name="keyword" class="text" value="" />
<button type="submit" value="검색하기">검색하기</button>&nbsp;&nbsp;&nbsp;&nbsp;
<button onclick="showPopup();" >상품등록 요청</button>
</p>
<div class="wrapper">
<table width="800px" class="">
<caption class="cap" border="1px">기념일</caption>
<td>
<div><img width="70px" height="70px" src="/woodaProject/img/a1.jpg" /></div>
<div>브랜드명</div>
<div> 상품명</div>
<br />
<div> 가격 </div>
</td>
<td>
<div><img width="70px" height="70px" src="/woodaProject/img/a1.jpg" /></div>
<div>브랜드명</div>
<div> 상품명</div>
<br />
<div> 가격 </div>
</td>
<td>
<div><img width="70px" height="70px" src="/woodaProject/img/a1.jpg" /></div>
<div>브랜드명</div>
<div> 상품명</div>
<br />
<div> 가격 </div>
</td>
<td>
<div><img width="70px" height="70px" src="/woodaProject/img/a1.jpg" /></div>
<div>브랜드명</div>
<div> 상품명</div>
<br />
<div> 가격 </div>
</td>
</table>
</div>
</body>
</html>