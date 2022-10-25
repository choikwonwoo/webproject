<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_side.jsp" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<BorderInfo> borderList = (ArrayList<BorderInfo>)request.getAttribute("borderList");
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
%>
<style>
.topbtn { width:1200px; margin-top:50px; margin-bottom:20px; text-align:right;}
.btnReport { width:100px; height:40px; font-size:12px; font-weight:bold; margin-right:10px; }

</style>
<script>
function boardDel(bsnum) {
	if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			type : "POST", 
			url : "/wdAdmin/board_proc_del", 
			data : {"bsnum" : bsnum}, 
			success : function(chkRs) {
				if (chkRs == 0) {
					alert("게시글 삭제에 실패했습니다.\n다시 시도하세요.");
				}
				location.reload();
			}
		});
	}
}
	
function chkDel() {
	var bsnum = getSelectedValues();
	// 선택한값들이 쉼표를 기준으로 '1,2,3,4' 문자열로 저장됨
	if (bsnum == "") {
		alert("삭제할 게시글을 선택하세요.");
	} else {
		boardDel(bsnum);
	}
}

function boardUp(bsnum) {		
	$.ajax({
			type : "POST", 
			url : "/wdAdmin/board_proc_up", 
			data : {"bsnum" : bsnum }, 
			success : function(chkRs) {
				if (chkRs == 0) {
					alert("게시글 상태 변경에 실패했습니다.\n다시 시도하세요.");
				}
				location.reload();
			}
		});
	}
	
function getSelectedValues() {
	// 체크박스들 중 선택된 체크박스들의 값들을 쉼표로 구분하여 문자열로 리턴하는 함수
		var chk = document.frmBoardStatus.chk; 
		var idxs = "";	
		for (var i = 0 ; i < chk.length ; i++) {
			if (chk[i].checked)	idxs += "," + chk[i].value;
		}
	
		return idxs.substring(1);
	}

function chkN(bsnum) {
	var bsnum = getSelectedValues();
	if (bsnum == "") {
		alert("상태 변경할 게시글을 선택하세요.");
	} else {
		boardUp(bsnum);
	}
}

</script>
<div class="content" align="center" >
<form name="frmBoardStatus">
<input type="hidden" name="chk" />
<div class="topbtn">
	<input type="button" class="btnReport" value="게시글 삭제" onclick="chkDel();" />
	<input type="button" class="btnReport" value="게시글 상태변경" onclick="chkN();" />
</div>
<div class="boardList">
<table width="1200" border="0" cellpadding="5" cellspacing="0" id="list">
	<tr>
		<th></th><th>게시글 번호</th><th>게시글 상태</th><th>사진</th><th>제목 + 날짜 + 코스</th>
	</tr>
<%
if (borderList.size() > 0) {	// 게시할 글목록이 있으면
	int num = rcnt - (psize * (cpage - 1));
	for (int i = 0 ; i < borderList.size() ; i++) {
		BorderInfo bl = borderList.get(i);
		String title = bl.getBs_title();
		String img1 = bl.getBs_img1();
		if (title.length() > 30)	title = title.substring(0, 27) + "...";
		title = "<a href='board_view?idx=" + bl.getBs_num() + args + "'>" + title + "</a>";
		img1 = "<a href='board_view?idx=" + bl.getBs_num() + args + "'>" + img1 + "</a>";
%>
<tr align="center" height="30">
	<td rowspan="3"><input type="checkbox" name="chk" value="<%=bl.getBs_num() %>" id="selectboard + <%=i %>" /></td>
	<td width="10%" rowspan="3"><%=bl.getBs_num() %></td>
	<td rowspan="3"><%=bl.getBs_isview() %></td>
<td width="30%" rowspan="3">
<% if(bl.getBs_img1().equals("")) { %>
<img src="img\trival.jpg" />
<% }else{ %>
<img src="img\<%=bl.getBs_img1() %>" />
<% } %>
</td>
<td ><%=title %></td>
</tr>
<tr align="center" >
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
</tr>

<%
		num--;
	}
} else {	// 글목록이 없으면
	out.println("<tr height='50'><td colspan='5' align='center'>");
	out.println("검색결과가 없습니다.</td></tr>");
}
%>
</table>
</div>
</form>
<br />
<table width="1200" cellpadding="5">
<tr align="center">
<td width="600">
<%
if (rcnt > 0) {	// 게시글이 있으면 - 페이징 영역을 보여줌
	String lnk = "index_board?cpage=";
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
</td>
</tr>
<tr align="center">
<td colspan="3">
	<form name="frmSch" method="get">
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
		<input type="text" name="keyword" value="<%=keyword %>" style="width:500px;" />
		<input type="submit" value="검색" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="전체글" onclick="location.href='index_board';" />
	</form>
</td></tr>
</table>
</div>
</body>
</html>