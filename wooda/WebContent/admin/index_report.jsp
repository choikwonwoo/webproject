<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_side.jsp" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<ReportInfo> reportInfo = (ArrayList<ReportInfo>)request.getAttribute("reportInfo");
ArrayList<BorderInfo> borderInfo = (ArrayList<BorderInfo>)request.getAttribute("borderInfo");
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
<style>
.topbtn { width:1200px; margin-top:50px; margin-bottom:20px; text-align:right;}
.btnReport { width:100px; height:40px; font-size:12px; font-weight:bold; margin-right:10px; }
.reportListTable { border:1px black solid; margin-top:30px;}
</style>

<script>
function reportDel(b1idx) {
	if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			type : "POST", 
			url : "/wdAdmin/report_proc_del", 
			data : {"b1idx" : b1idx}, 
			success : function(chkRs) {
				if (chkRs == 0) {
					alert("신고내역 삭제에 실패했습니다.\n다시 시도하세요.");
				}
				location.reload();
			}
		});
	}
}
	
function chkDel() {
	var b1idx = getSelectedValues();
	// 선택한값들이 쉼표를 기준으로 '1,2,3,4' 문자열로 저장됨
	if (b1idx == "") {
		alert("삭제할 신고내역을 선택하세요.");
	} else {
		reportDel(b1idx);
	}
}

function reportUp(b1idx) {		
	$.ajax({
			type : "POST", 
			url : "/wdAdmin/report_proc_up", 
			data : {"b1idx" : b1idx }, 
			success : function(chkRs) {
				if (chkRs == 0) {
					alert("게시물 상태 변경에 실패했습니다.\n다시 시도하세요.");
				}
				location.reload();
			}
		});
	}
	
function getSelectedValues() {
	// 체크박스들 중 선택된 체크박스들의 값들을 쉼표로 구분하여 문자열로 리턴하는 함수
		var chk = document.frmReportStatus.chk; 
		var idxs = "";	
		for (var i = 0 ; i < chk.length ; i++) {
			if (chk[i].checked)	idxs += "," + chk[i].value;
		}
	
		return idxs.substring(1);
	}

function chkN(b1idx) {
	var b1idx = getSelectedValues();
	if (b1idx == "") {
		alert("비활성화할 신고내역을 선택하세요.");
	} else {
		reportUp(b1idx);
	}
}

</script>

<div class="content" >
<form name="frmReportStatus">
<input type="hidden" name="chk" />
<div class="topbtn">
	<input type="button" class="btnReport" value="신고내역 삭제" onclick="chkDel();" />
	<input type="button" class="btnReport" value="게시글 상태변경" onclick="chkN();" />
</div>

<div class="reportList">
<table width="1200" border="0" cellpadding="5" cellspacing="0" id="list" >
<tr><th></th><th>번호</th><th>제목</th><th>신고내용</th><th>신고자</th><th>신고자 IP</th><th>공개여부</th></tr>
<% if (reportInfo.size() > 0) {	
	int num = rcnt - (psize * (cpage - 1));
	for (int i = 0; i < reportInfo.size(); i++) {
		ReportInfo ri = reportInfo.get(i);
%>
<tr height="30" align="center">
	<td><input type="checkbox" name="chk" value="<%=ri.getB1_idx() %>" id="select112 + <%=i %>" /></td>
	<td><label for="select112 + <%=i %>"><%=ri.getB1_idx() %></label></td>
	<td><a href="board_view?idx= <%=ri.getBs_num() + args %> "><%=ri.getBs_title() %></a></td>
	<td><label for="select112 + <%=i %>"><%=ri.getB1_content() %></label></td>
	<td><label for="select112 + <%=i %>"><%=ri.getMi_mail() %></label></td>
	<td><label for="select112 + <%=i %>"><%=ri.getB1_ip() %></label></td>
	<td><label for="select112 + <%=i %>"><%=ri.getBs_isview() %></label></td>
</tr>
<%
	}
} else {
	out.println("<tr height='50'><td colspan='7' align='center'>");
	out.println("신고 게시물이 없습니다.</td></tr>");
}
%>
</table>
</div>
</form>

<table width="1200" cellpadding="5"  >
<tr align="center">
<td width="600">
<%
if (rcnt > 0) {	// 게시글이 있으면 - 페이징 영역을 보여줌
	String lnk = "index_report?cpage=";
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

<tr align="center"><td colspan="3">
	<form name="frmSch" method="get">
		<select name="schtype">
			<option value="">검색 조건</option>
			<option value="bs_title" 
			<% if (schtype.equals("mail")) { %>selected="selected"<% } %>>글제목</option>
			<option value="b1_content" 
			<% if (schtype.equals("nick")) { %>selected="selected"<% } %>>신고내용</option>
			<option value="tc" 
			<% if (schtype.equals("name")) { %>selected="selected"<% } %>>제목 + 내용</option>
		</select>&nbsp;
		<input type="text" name="keyword"  value="<%=keyword %>" style="width:500px;" />&nbsp;
		<input type="submit" value="검색"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</form>
</td></tr>
</table>
</div>
</body>
</html>