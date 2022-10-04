<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
function showPopup()
{ 
	window.name = "parentForm";
	openWin = window.open('kakaoMap.jsp', 'childForm', 'status=no, height=700, width=1000, left='+ popupX + ', top='+ popupY);
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

	$( "#edusdate" ).datepicker({
		//defaultDate: "+1w",
		changeMonth: true,
		//numberOfMonths: 3,
		dateFormat:"yy-mm-dd",
		onClose: function( selectedDate ) {
			//$( "#eday" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$( "#eduedate" ).datepicker({
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
var count = 0;
function pclick(num){
	while(count < 3){
		count ++;
			$( '#trival' ).before( '<input type="button" name="name' + count + '" value="여행코스' + count + '" id="name' + count + '" onclick="showPopup();" />&nbsp;&nbsp;&nbsp;' );
			$( '#trival' ).append( '<input type="hidden" name="bs_place' + count + '" id="bs_place' + count + '"  />' );
			$( '#trival' ).append( '<input type="hidden" name="bs_lat' + count + '"  id="bs_lat' + count + '"  />' );
			$( '#trival' ).append( '<input type="hidden" name="bs_lng' + count + '"  id="bs_lng' + count + '"  />' );
			return;
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
	width:1000px; height=50px;
}
</style>
</head>
<body>
<form name="frm" action="write_proc_in" method="post">
<table width="900px" border="1";>
<tr>
<th width=40px">제목  </th>
<td> <input type="text" name="bs_title" value="" placeholder="제목을 입력하세여" style="width:*;"/> </td>
</tr>
<tr>
<th>여행 기간  </th>
<td >
<input type="text" name="bs_start" id="edusdate" value="" size="10" class="ipt" /> ~
	<input type="text" name="bs_end" id="eduedate" value="" size="10" class="ipt" />
</td>
</tr>
<tr>
<th> 여행 코스  </th>
<td> <span id="trival"></span>
<input type="button" name="plus_click" id="plusb" onclick="pclick(this);" value="여행지 추가" />
<input type="button" name="minus_click" id="minusb" onclick="mclick(this);" value="여행지 제거" /></td>
</tr>
<tr cellpadding="1px";>
<th>여행 지역 </th>
<td>
<input type="radio" name="bs_area"  value="a" checked="checked" />서울&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="b"  >경기&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area"  value="c"  />강원&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area" value="d"  />인천&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area" value="e"  />강릉&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area" value="f"  />대구&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area" value="g"  />울산&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area" value="h"  />부산&nbsp;&nbsp;&nbsp;
<input type="radio" name="bs_area" value="i"  />제주&nbsp;&nbsp;&nbsp;
</td>
</tr>
<tr>
<th>성별 </th>
<td> <input type="radio" name="bs_gender" value="m" id="man" checked="checked" >남자
<input type="radio" name="bs_gender" value="w" id="woman">여자</td>
</tr>
<tr>
<th>재방문 의사 </th>
<td> <input type="radio" name="bs_visit" value="y" checked="checked" >재방문 의사 있음
<input type="radio" name="bs_visit" value="n">재방문 의사 없음</td>
</tr>
<tr>
<td colspan="2"/>
<input type="file" name="bs_img1" />
</tr>
<tr >
<td colspan="2">
<textarea rows="35px" name="bs_content" ></textarea>
</td>
</tr>
</table>
<p align="right">
전체공개&nbsp;&nbsp;
<input type="radio" name="bs_astatus" value="y"  >공개
<input type="radio" name="bs_astatus" value="n" checked="checked" >비공개
</p>
<p align="right">
연인공개&nbsp;&nbsp;
<input type="radio" name="bs_cstatus" value="y"  >공개
<input type="radio" name="bs_cstatus" value="n" checked="checked" >비공개
</p>
<p align="center" >
<input type="submit" value="제출" style="width:100px; height:30px;"/>
<input type="reset" value="다시입력" style="width:100px; height:30px;" /> 
</p>
</form>
</body>
</html>