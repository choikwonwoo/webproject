<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%

// 회원 전용 페이지 로그인 확인
if (!isLogin){
	out.println("<script> alert('로그인 전용 페이지 입니다.'); location.href='login_form.jsp'; </script>");
	out.close();
}
%>
<%
request.setCharacterEncoding("utf-8");

ArrayList<BorderInfo> borderList = (ArrayList<BorderInfo>)request.getAttribute("borderList");
// 자유게시판 글목록이 들어있는 ArrayList<FreeList>를 형변환하여 받아옴
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");

int cpage = pageInfo.getCpage(),psize = pageInfo.getPsize(),rcnt = pageInfo.getRcnt();
int spage = pageInfo.getSpage(),bsize = pageInfo.getBsize(),pcnt = pageInfo.getPcnt();

String schtype = pageInfo.getSchtype(), keyword = pageInfo.getKeyword();
String schargs = "", args = "";
if (schtype != null && !schtype.equals("") && keyword != null && !keyword.equals("")) {
	schargs = "&schtype=" + schtype + "&keyword=" + keyword;
	// 검색조건과 검색어가 있으면 검색관련 데이터들을 쿼리스트링으로 지정
}
args = "&cpage=" + cpage + schargs;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
hr {width:1150px;}
#list th { border-bottom:double black 3px; }
#profileImg {width: 150px; height: 150px; }
#profileDiv {width: 150px; height: 150px;  border-radius: 70%; overflow: hidden;}
#profileZone { height:350px; background-color:lightgreen;}
#couplePostZone { height:350px; background-color:lightgreen;}
fieldset {border:0;}
table {border:solid 1px black; width:1150px; height:330px; margin-left:auto; margin-right:auto; text-align:center;}
</style>
<Script>
          
function openSendCard() {
	var openWin;  
    var _width = '650';
    var _height = '380';
	// window.name = "부모창 이름";  
 	window.name = "myPage";  
    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    var _left = Math.ceil(( window.screen.width - _width )/2);
    var _top = Math.ceil(( window.screen.height - _height )/2); 
 	// window.open("open할 window", "자식창 이름", "팝업창 옵션");
    openWin = window.open("mypage/send_card.jsp","sendCard","top=" + _top + ",left=" + _left + ",width=400,height=400");
 
}
function date(){
	var str = document.getElementById("day").value.substring(0,10);
	if (str != ""){
		var today = new Date();
		var year = today.getFullYear(); // 년도
		var month = today.getMonth() + 1;  // 월
		var date = today.getDate();  // 날짜
		var arr1 = str.split('-');
		var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
		var dat2 = new Date(year, month, date);
		document.getElementById("date").innerHTML = (dat2-dat1)/(1000*60*24*60) + "일째 열애중";
	}
	

}
</Script>
</head>
<body onload="date();">
<h2>마이 페이지</h2>
<div id="profileZone">
<table id="profileTable" border="1">

<tr>

<td rowspan="3" width="40%"><div id="profileDiv"><img src="img/<%=loginInfo.getMi_pimg()%>" id="profileImg"/></div></td>
<td align="left"><%=loginInfo.getMi_name() %></td>
<td rowspan="3" width="15%">나의일기<br/>
<div class="page"><%=pageInfo.getSoloPage() %> 장</div>
</td>

<td rowspan="3" width="15%">너의일기<br/>
<%if (loginInfo.getMi_iscouple().equals("y")){%>
<div class="page"><%=pageInfo.getCouple_page() %> 장</div>
<%}else{%>
<div class="page">0 장</div>
<%}%>
</td>
</tr>
<tr>
<td align="left"><%=loginInfo.getMi_mail() %></td>
</tr>
<tr>
<% if (loginInfo.getMi_iscouple().equals("n")){ %>
	<% if (loginInfo.getMi_dding().equals("n")){ %>
	<td align="left"><input type="button" value="연인을 등록하세요" onclick="openSendCard()"/></td>
	<% } else { %>
	<td align="left"><input type="button" value="연인을 등록하세요" disabled="disabled"/></td>
	<% } %>
<% } else { %>
<td align="left"><span id="date"></span></td>
<% } %>
</tr>
</table>
<input type=button name=profileModify value="프로필 수정" onclick="location.href='mypage/my_page_up.jsp'"/>
</div>
<hr/>
<div id="couplePostZone">
<div  style="margin:auto;text-align:center;">
<form name="frmSch" method="get" style="width:1150px; display:inline-block;">
<% if (loginInfo.getMi_iscouple().equals("n")){ %>
<input type="hidden" id="day" value=""/>
<% } else { %>
<input type="hidden" id="day" value="<%=coupleInfo.getCm_jdate()%>"/>
<% } %>
<fieldset>
	<legend>게시판 검색</legend>
	<select name="schtype">
		<option value="">검색 조건</option>
		<option value="title" 
		<% if (schtype.equals("title")) { %>selected="selected"<% } %>>제목</option>
		<option value="content" 
		<% if (schtype.equals("content")) { %>selected="selected"<% } %>>내용</option>
		<option value="nick" 
		<% if (schtype.equals("nick")) { %>selected="selected"<% } %>>작성자</option>
		<option value="tc" 
		<% if (schtype.equals("tc")) { %>selected="selected"<% } %>>제목+내용</option>
	</select>
	<input type="text" name="keyword" value="<%=keyword %>" />
	<input type="submit" value="검색" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="전체글" onclick="location.href='diary_write';" />
</fieldset>
</form>
</div>
<table height="30" border="0" cellpadding="0" cellspacing="0" id="list">
<tr height="12">
<th width="10%">글 번호</th><th width="30%">사진</th><th width="*">제목 + 날짜 + 코스</th>



<%
if (borderList.size() > 0) {	// 게시할 글목록이 있으면
	int num = rcnt - (psize * (cpage - 1));
	for (int i = 0 ; i < borderList.size() ; i++) {
		BorderInfo bl = borderList.get(i);
		String title = bl.getBs_title();
		String img1 = bl.getBs_img1();
		if (title.length() > 30)	title = title.substring(0, 27) + "...";
		title = "<a href='diary_write_view?idx=" + bl.getBs_num() + args + "'>" + title + "</a>";
		img1 = "<a href='diary_write_view?idx=" + bl.getBs_num() + args + "'>" + "<img src=img" + "/" + bl.getBs_img1() +">" + "</a>";
%>
<tr align="center">
<a href="free_view">
<td width="10%" rowspan="3"><%=num %></td>
<td width="30%" rowspan="3">
<% if(bl.getBs_img1().equals("")) { %>
<img src="img\trival.jpg" />
<% }else{ %>
<img src="img\<%=bl.getBs_img1() %>" />
<% } %>
</td>
<td ><%=title %></td>
</tr>
<tr align="center" width="*">
<td>여행 기간 : <%=bl.getBs_start() %>~<%=bl.getBs_end() %></td>
</tr>
<tr align="center">
<td><%=bl.getBs_place1() %> 
<% if(bl.getBs_place2() != null && !bl.getBs_place2().equals("")) {%>
 -> <%=bl.getBs_place2() %> 
<% } %>
 <% if(bl.getBs_place3() != null && !bl.getBs_place3().equals("")){ %>
 ->	<%=bl.getBs_place3() %>
<% } %>
</td>
</tr>
</a>
<%
		num--;
	}
} else {	// 글목록이 없으면
	out.println("<tbody><tr height='300'><td colspan='5' align='center'>검색결과가 없습니다.</td></tr></tbody>");
}
%>
</table>
<br />
<%
if (rcnt > 0) {	// 게시글이 있으면 - 페이징 영역을 보여줌
	String lnk = "diary_write_list?cpage=";
	pcnt = rcnt / psize;
	if (rcnt % psize > 0)	pcnt++;	// 전체 페이지 수

	if (cpage == 1) {
		out.println("[처음]&nbsp;&nbsp;&nbsp;[이전]&nbsp;&nbsp;");
	} else {
		out.println("<a href='" + lnk + "1" + schargs + "'>[처음]</a>&nbsp;&nbsp;&nbsp;");
		out.println("<a href='" + lnk + (cpage - 1) + schargs + "'>[이전]</a>&nbsp;&nbsp;");
	}

	spage = (cpage - 1) / bsize * bsize + 1;	// 현재 블록에서의 시작 페이지 번호
	for (int i = 1, j = spage ; i <= bsize && j <= pcnt ; i++, j++) {
	// i : 블록에서 보여줄 페이지의 개수만큼 루프를 돌릴 조건으로 사용되는 변수
	// j : 실제 출력한 페이지 번호로 전체 페이지 개수(마지막 페이지 번호)를 넘지 않게 사용해야 함
		if (cpage == j) {
			out.println("&nbsp;<strong>" + j + "</strong>&nbsp;");
		} else {
			out.println("&nbsp;<a href='" + lnk + j + schargs + "'>" + j + "</a>&nbsp;");
		}
	}

	if (cpage == pcnt) {
		out.println("&nbsp;&nbsp;[다음]&nbsp;&nbsp;&nbsp;[마지막]");
	} else {
		out.println("&nbsp;&nbsp;<a href='" + lnk + (cpage + 1) + schargs + "'>[다음]</a>");
		out.println("&nbsp;&nbsp;&nbsp;<a href='" + lnk + pcnt + schargs + "'>[마지막]</a>");
	}
}
%>
</div>
</body>
</html>