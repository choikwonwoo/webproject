<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*" %>
<%
	request.setCharacterEncoding("utf-8");
ArrayList<BorderInfo> AreaList = (ArrayList<BorderInfo>)request.getAttribute("AreaList");

String bs_area = (String)request.getAttribute("bs_area");

// 자유게시판 글목록이 들어있는 ArrayList<FreeList>를 형변환하여 받아옴
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");

String o = pageInfo.getO();

int cpage = pageInfo.getCpage(),psize = pageInfo.getPsize(),rcnt = pageInfo.getRcnt();
int spage = pageInfo.getSpage(),bsize = pageInfo.getBsize(),pcnt = pageInfo.getPcnt();

String schtype = pageInfo.getSchtype(), keyword = pageInfo.getKeyword();
String schargs = "", args = "";
if (schtype != null && !schtype.equals("") && keyword != null && !keyword.equals("")) {
	schargs = "&schtype=" + schtype + "&keyword=" + keyword;
	// 검색조건과 검색어가 있으면 검색관련 데이터들을 쿼리스트링으로 지정
}
args = "&cpage=" + cpage + schargs;

String list = "";
switch(bs_area){
case "a" : list = "서울";
break;
case "b" : list = "경기";
break;
case "c" : list = "인천";
break;
case "d" : list = "대구";
break;
case "e" : list = "충청북도";
break;
case "f" : list = "충청남도";
break;
case "g" : list = "전라북도";
break;
case "h" : list = "전라남도";
break;
case "i" : list = "강원도";
break;
case "j" : list = "광주";
break;
case "k" : list = "대전";
break;
case "l" : list = "울산";
break;
case "m" : list = "부산";
break;
case "n" : list = "제주도";
break;
case "o" : list = "경상북도";
break;
case "p" : list = "경상남도";
break;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img {
width:100px; height:80px
}
body{
    background-color: var(--color-black);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
#list th, #list td { padding:8px 3px; }
#list th { border-bottom:double black 3px; }
#list td { border-bottom:dotted black 1px; }
option{
  width: 200px;
  height : 50px;
  padding: .8em .5em;
  font-family: inherit;
}
select {
  width: 200px; 
  height : 50px;
  padding: .8em .5em; 
  font-family: inherit;
  font-size:auto;
}

</style>
</head>
<body>
<h2><%=list %></h2>
<table width="700px" cellpadding="5" >
	<tr><td align="left">
		<select name="o" onchange="location.href='diary_AreaList?cpage=<%=cpage %>&o=' + this.value;" >
			<option value="a" <% if (o.equals("a")) { %>selected="selected"<%} %>>최근 게시글</option>
			<option value="b" <% if (o.equals("b")) { %>selected="selected"<%} %>>오래된 게시글</option>
			<option value="c" <% if (o.equals("c")) { %>selected="selected"<%} %>>인기 게시글</option>
		</select> &nbsp;&nbsp;&nbsp;
	</td></tr>
	</table>
<table width="700" border="0" cellpadding="0" cellspacing="0" id="list">
<tr height="30">
<th width="10%">글 번호</th><th width="30%">사진</th><th width="*">제목 + 날짜 + 코스</th>
</tr>
<%
	if (AreaList.size() > 0) {	// 게시할 글목록이 있으면
	int num = rcnt - (psize * (cpage - 1));
	for (int i = 0 ; i < AreaList.size() ; i++) {
		BorderInfo bl = AreaList.get(i);
		String title = bl.getBs_title();
		String img1 = bl.getBs_img1();
		if (title.length() > 30)	title = title.substring(0, 27) + "...";
		title = "<a href='diary_write_view?idx=" + bl.getBs_num() + args + "'>" + title + "</a>";
		img1 = "<a href='diary_write_view?idx=" + bl.getBs_num() + args + "'>" + img1 + "</a>";
%>
<tr align="center">
<a href="free_view" >
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
	out.println("<tr height='50'><td colspan='5' align='center'>");
	out.println("검색결과가 없습니다.</td></tr>");
}
%>
</table>
<br />
<table width="700" cellpadding="5">
<tr>
<td width="600">
<%
if (rcnt > 0) {	// 게시글이 있으면 - 페이징 영역을 보여줌
	String lnk = "diary_area_list?cpage=";
	String lnk1 = "&bs_area=";
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
			out.println("&nbsp;<a href='" + lnk + j + lnk1 + bs_area + schargs + "'>" + j + "</a>&nbsp;");
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
</td>
<td width="*" align="right">
	<input type="button" value="글 등록" onclick="location.href='diary/diary_write_in.jsp';" />
</td>
</tr>
<tr><td colspan="2">
	<form name="frmSch" method="get">
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
		<input type="button" value="전체글" onclick="location.href='diary_write_list';" />
	</fieldset>
	</form>
</td></tr>
</table>
</body>
</html>
