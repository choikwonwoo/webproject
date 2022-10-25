<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_side.jsp" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<MemberList> memberList = (ArrayList<MemberList>)request.getAttribute("memberList");
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

<script>
function memberDel(mimail) {
	if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			type : "POST", 
			url : "/wdAdmin/member_proc_del", 
			data : {"mimail" : mimail}, 
			success : function(chkRs) {
				if (chkRs == 0) {
					alert("회원 삭제에 실패했습니다.\n다시 시도하세요.");
				}
				location.reload();
			}
		});
	}
}
	
function chkDel() {
	var mimail = getSelectedValues();
	// 선택한값들이 쉼표를 기준으로 '1,2,3,4' 문자열로 저장됨
	if (mimail == "") {
		alert("삭제할 회원을 선택하세요.");
	} else {
		memberDel(mimail);
	}
}

function memberUp(mimail) {		
	$.ajax({
			type : "POST", 
			url : "/wdAdmin/status_proc_up", 
			data : {"mimail" : mimail }, 
			success : function(chkRs) {
				if (chkRs == 0) {
					alert("회원 상태 변경에 실패했습니다.\n다시 시도하세요.");
				}
				location.reload();
			}
		});
	}
	
function getSelectedValues() {
	// 체크박스들 중 선택된 체크박스들의 값들을 쉼표로 구분하여 문자열로 리턴하는 함수
		var chk = document.frmStatus.chk; 
		var idxs = "";	
		for (var i = 0 ; i < chk.length ; i++) {
			if (chk[i].checked)	idxs += "," + chk[i].value;
		}
	
		return idxs.substring(1);
	}

function chkYN(mimail) {
	var mimail = getSelectedValues();
	if (mimail == "") {
		alert("변경할 회원을 선택하세요.");
	} else {
		memberUp(mimail);
	}
}
</script>

<div class="content" >
	<form name="frmStatus" action="index_mem" method="post">
		<div class="btnmemtop">
			<input type="hidden" name="chk" />
			<input type="button" class="btnYN"  value="선택 회원 삭제" onclick="chkDel();" />
			<input type="button" class="btnYN"  value="상태 변경" onclick="chkYN();"/>
		</div>
			
		<table width="1200" border="0" cellpadding="0" cellspacing="0" id="list">
			<tr><th></th><th>아이디</th><th>닉네임</th><th>암호</th><th>이름</th><th>가입일</th><th>사용여부</th></tr>
			<% if (memberList.size() > 0) {	//  출력할 회원 정보가 있을 경우 
				int num = rcnt - (psize * (cpage - 1));
				for (int i = 0; i < memberList.size(); i++) {
					MemberList mt = memberList.get(i);
					String status = mt.getMi_status();
					
			%>
			<tr height="30" align="center">
				<td><input type="checkbox" name="chk" value="<%=mt.getMi_mail() %>" id="selectMem + <%=i %>" /></td>
				<td><label for="selectMem + <%=i %>"><%=mt.getMi_mail() %></label></td>
				<td><label for="selectMem + <%=i %>"><%=mt.getMi_nick() %></label></td>
				<td><label for="selectMem + <%=i %>"> <%=mt.getMi_pw() %></label></td>
				<td><label for="selectMem + <%=i %>"><%=mt.getMi_name() %></label></td>
				<td><label for="selectMem + <%=i %>"><%=mt.getMi_join() %></label></td>
				<td><label for="selectMem + <%=i %>"><%=mt.getMi_status() %></label></td>
			</tr>
			<%
				}
			} else {	// 출력할 회원 정보가 없을 경우 
				out.println("<tr height='50'><td colspan='7' align='center'>");
				out.println("회원정보가 없습니다.</td></tr>");
			}
			%>
		</table>
		</form>
<br />
<table width="1000" cellpadding="5"  >
<tr align="center">
<td width="600">
<%
if (rcnt > 0) {	// 게시글이 있으면 - 페이징 영역을 보여줌
	String lnk = "index_mem?cpage=";
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
			<option value="mail" 
			<% if (schtype.equals("mail")) { %>selected="selected"<% } %>>아이디</option>
			<option value="nick" 
			<% if (schtype.equals("nick")) { %>selected="selected"<% } %>>닉네임</option>
			<option value="name" 
			<% if (schtype.equals("name")) { %>selected="selected"<% } %>>이름</option>
			<option value="YN" 
			<% if (schtype.equals("YN")) { %>selected="selected"<% } %>>사용여부</option>
		</select>&nbsp;
		<input type="text" name="keyword"  value="<%=keyword %>" style="width:500px;" />&nbsp;
		<input type="submit" value="검색"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</form>
</td></tr>
</table>
</div>
</body>
</html>