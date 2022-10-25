<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_side.jsp" %>
<%
ArrayList<AdminImg> adminImg = (ArrayList<AdminImg>) request.getAttribute("adminImg");
%>
<style>
.menu { width:1200px; height:height:1000px;  font-weight:bold; background:#fff;}
#menu2 { display:none; }

.btn { width:200px; height:50px; background:#eee; color:black; font-weight:bold; border:1px black solid; }
.btn:hover { background: #333; color: #fff; cursor:pointer;  }

table, th, td { border:1px solid black; padding:5px; font-size:12px; }
td { padding-left:20px; }
.pdt { width:1200px; border-collapse:collapse; }
.mainimg { width:320px; height:40px; font-size:15px;  }
.cata { width:156px; }

.subbtn { margin-top:300px; text-align:center; }
.mainsubbtn { width:100px; height:40px; font-size:12px; font-weight:bold; }
.tapcho { margin-bottom:10px; }
.text {font-size: 25px;}
/* #aa1 {position: relative; display: inline; width: 300px;} */
#a1 {position: absolute; top: 80px; left: 62px;}
/* #aa2 {position: relative; display: inline; width: 300px;} */
#a2 {position: absolute; top: 80px; left: 60px;}
/* #aa3 {position: relative; display: inline; width: 300px;} */
#a3 {position: absolute; top: 80px; left: 73px;}
/* #aa4 {position: relative; display: inline; width: 300px;} */
#a4 {position: absolute; top: 80px; left: 50px;}
/* #aa5 {position: relative; display: inline; width: 300px;} */
#a5 {position: absolute; top: 80px; left: 73px;}
.area {position: relative; height: 200px; width: 200px; border-radius: 50%; margin: 0 auto; padding: 0; cursor: pointer; object-fit: cover;}

</style>
<script src="/woodaProject/js/jquery-3.6.1.js"></script>
<script>
var cmenu = 1;
function showMenu(num) {
	var obj1 = document.getElementById("menu" + cmenu);
	obj1.style.display = "none";	
	var menu = document.getElementById("menu" + num);	
	menu.style.display = "block";	
	cmenu = num;
}
$(function() {
    $("#myFile0").on('change', function(){
    readURL(this, "0");
    });
});
$(function() {
    $("#myFile1").on('change', function(){
    readURL(this, "1");
    });
});
$(function() {
    $("#myFile2").on('change', function(){
    readURL(this, "2");
    });
});
$(function() {
    $("#myFile3").on('change', function(){
    readURL(this, "3");
    });
});
$(function() {
    $("#myFile4").on('change', function(){
    readURL(this, "4");
    });
});
function readURL(input, str) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
        	if (str == "0"){
        		$('#View0').attr('src', e.target.result);
        	}else if(str == "1"){
        		$('#View1').attr('src', e.target.result);
        	}else if(str == "2"){
        		$('#View2').attr('src', e.target.result);
        	}else if(str == "3"){
        		$('#View3').attr('src', e.target.result);
        	}else if(str == "4"){
        		$('#View4').attr('src', e.target.result);
        	}
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
function localChange(sel){
	var text = sel.options[sel.selectedIndex].text
	var val = sel.options[sel.selectedIndex].value
	var id = sel.id
	if (id == "selector0"){
		$('#a1').html(text);
		$('#local_name0').val(val);
	}else if(id == "selector1"){
		$('#a2').html(text);
		$('#local_name1').val(val);
	}else if(id == "selector2"){
		$('#a3').html(text);
		$('#local_name2').val(val);
	}else if(id == "selector3"){
		$('#a4').html(text);
		$('#local_name3').val(val);
	}else if(id == "selector4"){
		$('#a5').html(text);
		$('#local_name4').val(val);
	}
}

</script>
<body>
<div class="content">
<h3>메인 페이지 관리</h3>
<div class="tapcho">
<input type="button" class="btn" value="인기여행지" onclick="showMenu(1);" />
<input type="button" class="btn" value="하단정보" onclick="showMenu(2);" />
</div>
<form action="uploadPartProc2" method="post" enctype="multipart/form-data" style="width: 100%;">

<div id="imgwrap"  style="margin: 0 auto; display: flex; align-items: center; vertical-align: middle; justify-content: space-between;">
<% for (int i = 0; i<adminImg.size(); i++) {
	AdminImg ai = adminImg.get(i);
%>
	<div id="aa<%=i+1%>">
		<div class="area" >
			<img src="/woodaProject/img/<%=ai.getAm_img() %>" id="View<%=i %>" class="area" width="170" height="170" style="opacity: 0.7;">
			<input type="file" id="myFile<%=i %>" name="img_file" accept="image/jpg, image/jpeg, image/png, image/png" onchange="checkSize(this);" value="<%=ai.getAm_img() %>"/>
			<select id="selector<%=i%>" onchange="localChange(this);">
				<option selected="selected">지역을 선택 하세요</option>
				<option value="a">서울</option>
				<option value="b">경기도</option>
				<option value="c">인천</option>
				<option value="d">대구</option>
				<option value="e">충정북도</option>
				<option value="f">충청남도</option>
				<option value="g">전라북도</option>
				<option value="h">전라남도</option>
				<option value="i">강원도</option>
				<option value="j">광주</option>
				<option value="k">대전</option>
				<option value="l">울산</option>
	 			<option value="m">부산</option>
				<option value="n">제주도</option>
				<option value="o">경상북도</option>
				<option value="p">경상남도</option>
			</select>
			<span id="a<%=i+1 %>" class="text" ><%=ai.getAm_name() %></span>	
			<input type="hidden" name="img_name<%=i %>" value="<%=ai.getAm_img() %>" /> <!--  img 값 -->
			<input type="hidden" id="local_name<%=i %>" name="local_name<%=i %>" /> <!--  코드 값 -->
			
		</div>
	</div>
<% } %>
</div>
<div class="subbtn" width="1200">
	<input class="mainsubbtn" type="submit" value="수정" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input class="mainsubbtn" type="reset" value="취소" />
</div>
</form>
</div>

<div class="menu" id="menu2">
<table align="left" class="pdt">
<tr><th>하단 정보 이미지</th><td><input type="file" name="desc" class="mainimg" /></td></tr>
</table>	
</div>

</div>	
</body>
</html>



