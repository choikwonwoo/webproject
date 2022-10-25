<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*" %>
<%
request.setCharacterEncoding("utf-8");
BorderInfo borderInfo = (BorderInfo)request.getAttribute("borderInfo");
int likeCheck = (Integer)request.getAttribute("likeTotal");
int commentTotal = (Integer)request.getAttribute("commentTotal");
ArrayList<BorderComment> CommentList = (ArrayList<BorderComment>)request.getAttribute("CommentList");

int idx = Integer.parseInt(request.getParameter("idx"));
// 페이지 번호를 얻기 위하여 생성
int cpage = Integer.parseInt(request.getParameter("cpage"));
// 현재 페이지 번호를 받기위하여 cpage번호 생성

int likeTotal = borderInfo.getBs_like();

String args = "?cpage=" + cpage;
// 위에서 받은 cpage변수를 사용하여 url에 사용할 쿼리스트링을 저장

String schtype = request.getParameter("schtype");	// 검색조건
String keyword = request.getParameter("keyword");	// 검색어
if (schtype != null && keyword != null && !schtype.equals("") && !keyword.equals("")) {
	// 검색조건(schtype)과 검색어(keyword)가 null이 아니고, 빈 문자열도 아니면
	keyword = keyword.trim().replace("'", "''");
	args += "&schtype=" + schtype + "&keyword=" + keyword;
	// 다른 링크들 에서도 검색 관련 값들을 쿼리스트링으로 연결해줌
}

String upLink = "", delLink = "",	reportLink = "";	// 수정과 삭제용 링크를 저장할 변수
upLink = "diary_form_up" + args + "&idx=" + idx;
delLink = "diary_proc_del?idx=" + idx;
reportLink = "diary/report.jsp?idx=" + idx;
%>
<%
String bs_area = "";
switch(borderInfo.getBs_area()){
case "a" : bs_area = "서울";
	break;
case "b" : bs_area = "경기";
break;
case "c" : bs_area = "강원";
break;
case "d" : bs_area = "인천";
break;
case "e" : bs_area = "강릉";
break;
case "f" : bs_area = "대구";
break;
case "g" : bs_area = "울산";
break;
case "h" : bs_area = "부산";
break;
case "i" : bs_area = "제주";
break;
default:
	bs_area = "기타지역";
}

String bs_gender = "";
switch(borderInfo.getBs_gender()){
case "m" : bs_gender = "남자";
break;
case "w" : bs_gender = "여자";
break;
}

String bs_visit = "";
switch(borderInfo.getBs_visit()){
case "y" : bs_visit = "재방문 의사 있음";
break;
case "n" : bs_visit = "재방문 의사 없음";
break;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.likeimg{
width:30px; height:30px
}
td{ width:*; text-align:center }
body{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
textarea {
	width:1000px; height=50px;
}
</style>
<script>
var popupWidth = 500;
var popupHeight = 300;

var popupX = (window.screen.width / 2) - (popupWidth / 2);
// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

var popupY= (window.screen.height / 2) - (popupHeight / 2);
var idx = 0;
var bs_num = <%=idx %>;
function showPopup()
{ 
	window.name = "parentForm";
	openWin = window.open('<%=reportLink %>' , 'childForm', 'status=no, height=300, width=500, left='+ popupX + ', top='+ popupY);
}
	

</script>
</head>
<body>
<div style="float:left;" class="title1">작성자 : <%=borderInfo.getMi_nick() %> | <%=borderInfo.getBs_date() %></div> 
<div style="float:right;" class="title2"> 조회 <%=borderInfo.getBs_read() %> <input type="button" name="police" value="신고하기" onclick="showPopup();" /></div>
<div>
<input type="button" value="글수정" onclick="location.href='<%=upLink %>';" />
<input type="button" value="글삭제" onclick="location.href='<%=delLink %>';" />
</div>

<table width="1150px" border="1">
<tr>
<th width=100px">제목  </th>
<td><%=borderInfo.getBs_title() %></td>
</tr>
<tr>
<th>여행 기간  </th>
<td ><%=borderInfo.getBs_start() %> - <%=borderInfo.getBs_end() %></td>
</tr>
<tr>
<th> 여행 코스  </th>
<td>
<% if(borderInfo.getBs_place1() != null && !borderInfo.getBs_place1().equals("")) {%>
A. <%=borderInfo.getBs_place1() %> 
<% } %>
<% if(borderInfo.getBs_place2() != null && !borderInfo.getBs_place2().equals("")) {%>
 -> B. <%=borderInfo.getBs_place2() %> 
<% } %>
 <% if(borderInfo.getBs_place3() != null && !borderInfo.getBs_place3().equals("")){ %>
 ->	C. <%=borderInfo.getBs_place3() %>
<% } %></td>
</tr>
<tr cellpadding="1px">
<th>여행 지역 </th>
<td>
<%=bs_area %>
</td>
</tr>
<tr>
<th>성별 </th>
<td> 
<%=bs_gender %>
</td>
</tr>
<tr>
<th>재방문 의사 </th>
<td> 
<%=bs_visit %>
</td>
</tr>
<!-- 이미지 추가해야 함 -->
<tr >
<td colspan="2">
<%=borderInfo.getBs_content() %>
</td>
</tr>
</table>
<div id="map" style="width:1000px;height:350px;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	1f10d748e55146b3f270d9ee2f0639ef"></script>
<script>
var MARKER_WIDTH = 33, // 기본, 클릭 마커의 너비
    MARKER_HEIGHT = 36, // 기본, 클릭 마커의 높이
    OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
    OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
    OVER_MARKER_WIDTH = 40, // 오버 마커의 너비
    OVER_MARKER_HEIGHT = 42, // 오버 마커의 높이
    OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
    OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
    SPRITE_MARKER_URL = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites2.png', // 스프라이트 마커 이미지 URL
    SPRITE_WIDTH = 126, // 스프라이트 이미지 너비
    SPRITE_HEIGHT = 146, // 스프라이트 이미지 높이
    SPRITE_GAP = 10; // 스프라이트 이미지에서 마커간 간격

var markerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT), // 기본, 클릭 마커의 크기
    markerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y), // 기본, 클릭 마커의 기준좌표
    overMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH, OVER_MARKER_HEIGHT), // 오버 마커의 크기
    overMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X, OVER_OFFSET_Y), // 오버 마커의 기준 좌표
    spriteImageSize = new kakao.maps.Size(SPRITE_WIDTH, SPRITE_HEIGHT); // 스프라이트 이미지의 크기


    
var positions = [  // 마커의 위치
        new kakao.maps.LatLng(<%=borderInfo.getBs_lat1() %>, <%=borderInfo.getBs_lng1() %>),
        new kakao.maps.LatLng(<%=borderInfo.getBs_lat2() %>, <%=borderInfo.getBs_lng2() %>),
        new kakao.maps.LatLng(<%=borderInfo.getBs_lat3() %>, <%=borderInfo.getBs_lng3() %>)
    ],
    selectedMarker = null; // 클릭한 마커를 담을 변수

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = { 
        center: new kakao.maps.LatLng(<%=borderInfo.getBs_lat1() %>, <%=borderInfo.getBs_lng1() %>), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도 위에 마커를 표시합니다
for (var i = 0, len = positions.length; i < len; i++) {
    var gapX = (MARKER_WIDTH + SPRITE_GAP), // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
        originY = (MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
        overOriginY = (OVER_MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
        normalOrigin = new kakao.maps.Point(0, originY), // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
        clickOrigin = new kakao.maps.Point(gapX, originY), // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
        overOrigin = new kakao.maps.Point(gapX * 2, overOriginY); // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표
        
    // 마커를 생성하고 지도위에 표시합니다
    addMarker(positions[i], normalOrigin, overOrigin, clickOrigin);
}

// 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
function addMarker(position, normalOrigin, overOrigin, clickOrigin) {

    // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
    var normalImage = createMarkerImage(markerSize, markerOffset, normalOrigin),
        overImage = createMarkerImage(overMarkerSize, overMarkerOffset, overOrigin),
        clickImage = createMarkerImage(markerSize, markerOffset, clickOrigin);
    
    // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: position,
        image: normalImage
    });

    // 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
    marker.normalImage = normalImage;

    // 마커에 mouseover 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseover', function() {

        // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
        // 마커의 이미지를 오버 이미지로 변경합니다
        if (!selectedMarker || selectedMarker !== marker) {
            marker.setImage(overImage);
        }
    });

    // 마커에 mouseout 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseout', function() {

        // 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
        // 마커의 이미지를 기본 이미지로 변경합니다
        if (!selectedMarker || selectedMarker !== marker) {
            marker.setImage(normalImage);
        }
    });

    // 마커에 click 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {

        // 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
        // 마커의 이미지를 클릭 이미지로 변경합니다
        if (!selectedMarker || selectedMarker !== marker) {

            // 클릭된 마커 객체가 null이 아니면
            // 클릭된 마커의 이미지를 기본 이미지로 변경하고
            !!selectedMarker && selectedMarker.setImage(selectedMarker.normalImage);

            // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
            marker.setImage(clickImage);
        }

        // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
        selectedMarker = marker;
    });
}

// MakrerImage 객체를 생성하여 반환하는 함수입니다
function createMarkerImage(markerSize, offset, spriteOrigin) {
    var markerImage = new kakao.maps.MarkerImage(
        SPRITE_MARKER_URL, // 스프라이트 마커 이미지 URL
        markerSize, // 마커의 크기
        {
            offset: offset, // 마커 이미지에서의 기준 좌표
            spriteOrigin: spriteOrigin, // 스트라이프 이미지 중 사용할 영역의 좌상단 좌표
            spriteSize: spriteImageSize // 스프라이트 이미지의 크기
        }
    );
    
    return markerImage;
}
</script>
<%
String like = "좋아요";
%>
<script>
function likeInsert(idx, likeTotal){
	$.ajax({
		type : "POST",
		url : "/woodaProject/love_proc_in",
		data : {"idx": idx, "likeTotal" : likeTotal},
		success : function(chkRs){
			if(chkRs > 0){
			}else if(chkRs == -1){
				alert("로그인후 이용가능한 서버스 입니다.");
			}else{
				alert("좋아요 등록 실패!^0-");
			}
			location.reload();
			},
			 error : function (jqXHR, textStatus, errorThrown){
             	console.log(jqXHR);  
             	console.log(textStatus); 
             	console.log(errorThrown);
             }
		
	});
}

function likeDelete(idx, likeTotal){
	$.ajax({
		type : "POST",
		url : "/woodaProject/love_proc_del",
		data : {"idx": idx, "likeTotal" : likeTotal},
		success : function(chkRs){
			if(chkRs > 0){
				window.location.reload();
			}else if(chkRs == -1){
				alert("로그인후 이용하시기 바랍니다.");
				window.location.reload();
			}else{
				alert("댓글 등록 실패!^0-");
				window.location.reload();
			}
		}
	});
}

$(document).ready(function () {
	$('#submit').click(function(event){
		if($("#br_content").val().length >= 5){
		}else{
			alert("최소 5글자 이상입력 바랍니다.");
			return false;
		}
		$.ajax({
			type : "POST", 
			url : "/woodaProject/comment_proc_in",
			data: $("#frm1").serialize(),
			success : function(chkRs){
				if(chkRs > 0){
					window.location.reload();
				}else if(chkRs == -1){
					alert("로그인후 이용하시기 바랍니다.");
					window.location.reload();
				}else{
					alert("댓글 등록 실패!^0-");
					window.location.reload();
				}
				
			}
		});
	});
});
</script>
<br />
<input type="button" value="목록" onclick="location.href='diary_write_list';"	/>
좋아요 총 개수 : <%=borderInfo.getBs_like() %>
<% if(likeCheck == 0) { %>
<input type="button" id="like" value="좋아요"  onclick="likeInsert(<%=idx %>, <%=likeTotal %>);" />
<% }else { %>
<input type="button" id="like" value="좋아요 취소"  onclick="likeDelete(<%=idx %>, <%=likeTotal %>);" />
<% } %>

총 댓글 개수 : <%=commentTotal %>
<table width="700px">
<% for(int i=0; i<CommentList.size(); i++){ 
	BorderComment bc = CommentList.get(i);
%>
<tr>
<td width="50px"> <%=bc.getMi_nick() %></td>
<td width="500px"> <%=bc.getBr_content() %> </td>
<td width="150px"> <%=bc.getBr_date() %></td>
<tr>
<% } %>
</table>
<form name="frm1" method="post" id="frm1">
<input type="hidden" name="bs_num" value="<%=idx %>" />
<table width="700px">
<tr>
<td width="500px"><textarea cols="40px" name="br_content" id="br_content" ></textarea></td>
<td width="*">
<input type="submit" value="등록" id="submit" />
</td>
</table>
</form>
</body>
</html>