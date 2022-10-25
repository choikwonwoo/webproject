<%@page import="vo.BorderInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<% 
request.setCharacterEncoding("utf-8");
BorderInfo borderInfo = (BorderInfo)request.getAttribute("borderInfo"); 
int count = 0;
if(!borderInfo.getBs_place3().equals("")){
	count = 3;
}else if(!borderInfo.getBs_place2().equals("")){
	count = 2;
}else if(!borderInfo.getBs_place1().equals("")){
	count = 1;
}
out.println(count);
%>
<!DOCTYPE html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> 
<script>
var lacation = "";
var car
var popupWidth = 1000;
var popupHeight = 700;

var popupX = (window.screen.width / 2) - (popupWidth / 2);
// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

var popupY= (window.screen.height / 2) - (popupHeight / 2);
// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
function showPopup1()
{ 
	window.name = 1;
	openWin = window.open('diary/kakaoMap.jsp', 'childForm', 'status=no, height=700, width=1000, left='+ popupX + ', top='+ popupY);
}

function showPopup2()
{ 
	window.name = 2;
	openWin = window.open('diary/kakaoMap.jsp', 'childForm', 'status=no, height=700, width=1000, left='+ popupX + ', top='+ popupY);
}

function showPopup3()
{ 
	window.name = 3;
	openWin = window.open('diary/kakaoMap.jsp', 'childForm', 'status=no, height=700, width=1000, left='+ popupX + ', top='+ popupY);
}
$(function() {
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['1월','2월','3월','4월','5월','6월',
		'7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월',
		'7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		//buttonImage: "/images/kr/create/btn_calendar.gif",
		buttonImageOnly: true,
		// showOn :"both",
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		duration:200,
		showAnim:'show',
		showMonthAfterYear: false
		// yearSuffix: '년'
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);

	$( "#sdate" ).datepicker({
		//defaultDate: "+1w",
		changeMonth: true,
		//numberOfMonths: 3,
		dateFormat:"yy-mm-dd",
		onClose: function( selectedDate ) {
			//$( "#eday" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$( "#edate" ).datepicker({
		//defaultDate: "+1w",
		changeMonth: true,
		//numberOfMonths: 3,
		dateFormat:"yy-mm-dd",
		onClose: function( selectedDate ) {
			//$( "#sday" ).datepicker( "option", "maxDate", selectedDate );
		}
	});
});
</script>
<script>
var count = <%=count + 1 %>;
function pclick(num){
	if(count < 4) {
		if(count > 1) {
			if(count === 2) {
				$( '#trival' ).before( '<input type="button" class="name' + count + '" name="name' + count + '" value="여행코스' + count + '" id="name' + count + '" onclick="showPopup2();" />' );
				$( '#trival' ).before( '<span class="name' + count + '">&nbsp;&nbsp;&nbsp;</span>' );
				$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_place' + count + '" id="bs_place' + count + '"  />' );
				$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_lat' + count + '"  id="bs_lat' + count + '"  />' );
				$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_lng' + count + '"  id="bs_lng' + count + '"  />' );
				count++;	
			}else if(count === 3){
				$( '#trival' ).before( '<input type="button" class="name' + count + '" name="name' + count + '" value="여행코스' + count + '" id="name' + count + '" onclick="showPopup3();" />' );
				$( '#trival' ).before( '<span class="name' + count + '">&nbsp;&nbsp;&nbsp;</span>' );
				$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_place' + count + '" id="bs_place' + count + '"  />' );
				$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_lat' + count + '"  id="bs_lat' + count + '"  />' );
				$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_lng' + count + '"  id="bs_lng' + count + '"  />' );
				count++;	
			}
		}else{
			$( '#trival' ).before( '<input type="button" class="name' + count + '" name="name' + count + '" value="여행코스' + count + '" id="name' + count + '" onclick="showPopup1();" />' );
			$( '#trival' ).before( '<span class="name' + count + '">&nbsp;&nbsp;&nbsp;</span>' );
			$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_place' + count + '" id="bs_place' + count + '"  />' );
			$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_lat' + count + '"  id="bs_lat' + count + '"  />' );
			$( '#trival' ).append( '<input type="hidden" class="name' + count + '" name="bs_lng' + count + '"  id="bs_lng' + count + '"  />' );
			count++;
		}
	}
}

function mclick(num){
	if(count >= 0){
		if(count == 2){
			$('.name1').remove();
			count--;
		}else if(count == 3){
			$('.name2').remove();
			count--;
		}else if(count == 4){
			$('.name3').remove();
			count--;
		}
	}
}

var title = false;
const calender = /(^\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;

function test(){
	if(count > 1 && $("#bs_place1").val() !== ""){
		if(calender.test($("#sdate").val()) && calender.test($("#edate").val())){
			return true;
		}else{
			alert("여행 기간을 정확히 입력바랍니다.");
			return false;
		}
	}else{
		alert("여행코스는 1개이상 입력하셔야 합니다.");
		return false;
	}
}

</script>
<style>
body{
    background-color: var(--color-black);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
textarea {
	width:700px; height=50px;
}
</style>
</head>
<body>
<form name="frm" action="write_proc_up" method="post" onsubmit="return test()" >
<input type="hidden" name="bs_num" value="<%=borderInfo.getBs_num() %>" />
<table width="700px" border="1">
<tr>
<th width=40px">제목  </th>
<td> <input type="text" minlength="4" name="bs_title" value="<%=borderInfo.getBs_title() %>" placeholder="제목을 입력하세여" style="width:*;"/> </td>
</tr>
<tr>
<th>여행 기간  </th>
<td >
<input type="text" name="bs_start" id="sdate" value="<%=borderInfo.getBs_start() %>" size="10" class="ipt" /> ~
<input type="text" name="bs_end" id="edate" value="<%=borderInfo.getBs_end() %>" size="10" class="ipt" />
</td>
</tr>
<tr>
<th> 여행 코스  </th>
<td> 
<%
if(count == 1){
%>
<input type="button" class="name1" name="name1" value="<%=borderInfo.getBs_place1() %>" id="name1" onclick="showPopup1();" />
<span class="name1">&nbsp;&nbsp;&nbsp;</span>
<input type="hidden" class="name1" name="bs_place1" value="<%=borderInfo.getBs_place1() %>" id="bs_place1"  />
<input type="hidden" class="name1" name="bs_lat1" value="<%=borderInfo.getBs_lat1() %>" id="bs_lat1"  />
<input type="hidden" class="name1" name="bs_lng1" value="<%=borderInfo.getBs_lng1() %>"  id="bs_lng1"  />
<%
}else if(count == 2){
%>
<input type="button" class="name1" name="name1" value="<%=borderInfo.getBs_place1() %>" id="name1" onclick="showPopup1();" />
<span class="name1">&nbsp;&nbsp;&nbsp;</span>
<input type="hidden" class="name1" name="bs_place1" value="<%=borderInfo.getBs_place1() %>" id="bs_place1"  />
<input type="hidden" class="name1" name="bs_lat1" value="<%=borderInfo.getBs_lat1() %>" id="bs_lat1"  />
<input type="hidden" class="name1" name="bs_lng1" value="<%=borderInfo.getBs_lng1() %>"  id="bs_lng1"  />
<input type="button" class="name2" name="name2" value="<%=borderInfo.getBs_place2() %>" id="name2" onclick="showPopup2();" />
<span class="name2">&nbsp;&nbsp;&nbsp;</span>
<input type="hidden" class="name2" name="bs_place2" value="<%=borderInfo.getBs_place2() %>" id="bs_place2"  />
<input type="hidden" class="name2" name="bs_lat2" value="<%=borderInfo.getBs_lat2() %>" id="bs_lat2"  />
<input type="hidden" class="name2" name="bs_lng2" value="<%=borderInfo.getBs_lng2() %>"  id="bs_lng2"  />
<% }else if(count == 3) { %>
<input type="button" class="name1" name="name1" value="<%=borderInfo.getBs_place1() %>" id="name1" onclick="showPopup1();" />
<span class="name1">&nbsp;&nbsp;&nbsp;</span>
<input type="hidden" class="name1" name="bs_place1" value="<%=borderInfo.getBs_place1() %>" id="bs_place1"  />
<input type="hidden" class="name1" name="bs_lat1" value="<%=borderInfo.getBs_lat1() %>" id="bs_lat1"  />
<input type="hidden" class="name1" name="bs_lng1" value="<%=borderInfo.getBs_lng1() %>"  id="bs_lng1"  />
<input type="button" class="name2" name="name2" value="<%=borderInfo.getBs_place2() %>" id="name2" onclick="showPopup2();" />
<span class="name2">&nbsp;&nbsp;&nbsp;</span>
<input type="hidden" class="name2" name="bs_place2" value="<%=borderInfo.getBs_place2() %>" id="bs_place2"  />
<input type="hidden" class="name2" name="bs_lat2" value="<%=borderInfo.getBs_lat2() %>" id="bs_lat2"  />
<input type="hidden" class="name2" name="bs_lng2" value="<%=borderInfo.getBs_lng2() %>"  id="bs_lng2"  />
<input type="button" class="name3" name="name3" value="<%=borderInfo.getBs_place3() %>" id="name3" onclick="showPopup3();" />
<span class="name3">&nbsp;&nbsp;&nbsp;</span>
<input type="hidden" class="name3" name="bs_place3" value="<%=borderInfo.getBs_place3() %>" id="bs_place3"  />
<input type="hidden" class="name3" name="bs_lat3" value="<%=borderInfo.getBs_lat3() %>" id="bs_lat3"  />
<input type="hidden" class="name3" name="bs_lng3" value="<%=borderInfo.getBs_lng3() %>"  id="bs_lng3"  />
<% } %>
<span id="trival"></span>
<input type="button" name="plus_click" id="plusb" onclick="pclick(this);" value="여행지 추가" />
<input type="button" name="minus_click" id="minusb" onclick="mclick(this);" value="여행지 제거" /></td>
</tr>
<tr cellpadding="1px";>
<th>여행 지역 </th>
<td>
<input type="radio" name="bs_area"  value="a" <% if(borderInfo.getBs_area().equals("a")) { %> checked="checked" <% } %> />서울&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="b" <% if(borderInfo.getBs_area().equals("b")) { %> checked="checked" <% } %> />경기&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="c" <% if(borderInfo.getBs_area().equals("c")) { %> checked="checked" <% } %> />인천&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="d" <% if(borderInfo.getBs_area().equals("d")) { %> checked="checked" <% } %> />대구&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="e" <% if(borderInfo.getBs_area().equals("e")) { %> checked="checked" <% } %> />충청북도&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="f" <% if(borderInfo.getBs_area().equals("f")) { %> checked="checked" <% } %> />충청남도&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="g" <% if(borderInfo.getBs_area().equals("g")) { %> checked="checked" <% } %> />전라북도&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="h" <% if(borderInfo.getBs_area().equals("h")) { %> checked="checked" <% } %> />전라남도&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="i" <% if(borderInfo.getBs_area().equals("i")) { %> checked="checked" <% } %> />강원도&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="c" <% if(borderInfo.getBs_area().equals("j")) { %> checked="checked" <% } %> />광주&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="d" <% if(borderInfo.getBs_area().equals("k")) { %> checked="checked" <% } %> />대전&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="e" <% if(borderInfo.getBs_area().equals("l")) { %> checked="checked" <% } %> />울산&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="f" <% if(borderInfo.getBs_area().equals("m")) { %> checked="checked" <% } %> />부산&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="g" <% if(borderInfo.getBs_area().equals("n")) { %> checked="checked" <% } %> />제주도&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="h" <% if(borderInfo.getBs_area().equals("o")) { %> checked="checked" <% } %> />경상북도&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="i" <% if(borderInfo.getBs_area().equals("p")) { %> checked="checked" <% } %> />경상남도&nbsp;&nbsp;&nbsp;
</td>
</tr>
<tr>
<th>성별 </th>
<td> <input type="radio" name="bs_gender" value="m" id="man" <% if(borderInfo.getBs_gender().equals("m")) { %> checked="checked" <% } %> >남자
<input type="radio" name="bs_gender" value="w" id="woman" <% if(borderInfo.getBs_gender().equals("w")) { %> checked="checked" <% } %> >여자</td>
</tr>
<tr>
<th>재방문 의사 </th>
<td> <input type="radio" name="bs_visit" value="y" <% if(borderInfo.getBs_visit().equals("y")) { %> checked="checked" <% } %> >재방문 의사 있음
<input type="radio" name="bs_visit" value="n" <% if(borderInfo.getBs_visit().equals("n")) { %> checked="checked" <% } %> >재방문 의사 없음</td>
</tr>
<tr>
<td colspan="2"/>
<input type="file" name="bs_img1" />
</tr>
<tr >
<td colspan="2">
<textarea minlength="4" rows="35px" name="bs_content" ><%=borderInfo.getBs_content() %></textarea>
</td>
</tr>
</table>
<p align="right">
전체공개&nbsp;&nbsp;
<input type="radio" name="bs_astatus" value="y" <% if(borderInfo.getBs_astatus().equals("y")) { %> checked="checked" <% } %> />공개
<input type="radio" name="bs_astatus" value="n" <% if(borderInfo.getBs_astatus().equals("n")) { %> checked="checked" <% } %> />비공개
</p>
<p align="right">
연인공개&nbsp;&nbsp;
<input type="radio" name="bs_cstatus" value="y" <% if(borderInfo.getBs_cstatus().equals("y")) { %> checked="checked" <% } %> >공개
<input type="radio" name="bs_cstatus" value="n" <% if(borderInfo.getBs_cstatus().equals("n")) { %> checked="checked" <% } %> >비공개
</p>
<p align="center" >
<input type="submit" value="제출" style="width:100px; height:30px;"/>
<input type="reset" value="다시입력" style="width:100px; height:30px;" /> 
</p>
</form>
</body>
</html>