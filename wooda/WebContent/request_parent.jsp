<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.text {
  width:500px;
  height:30px;
  font-size:20px;
}

.wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top:50px
}
</style>
<script>

</script>
</head>
<body>
<form name="frm" action="present_request_proc_in" method="post">
<table  width="500px" style="margin-left: auto; margin-right: auto;">
<tr align="center">
<td>
<div>
<label for="gi_brand">브랜드</label>
<input type="text" class="text" name="gi_brand" value="" id="gi_brand" />
</div>
<br />
</td>
</tr>
<tr align="center">
<td>
<div>
<label for="gi_name">상품명</label>
<input type="text" class="text" name="gi_name" value="" id="gi_name" />
</div>
<br />
</td>
</tr>
<tr align="center">
<td>
<div>
<label for="gi_price">가격</label>
<input type="text" class="text" name="gi_price" value="" id="gi_price" />
</div>
<br />
</td>
</tr>
<tr align="center">
<td>
<div>
<label for="gi_tag">태그</label>
<input type="text" class="text" name="gi_tag" value="" id="gi_tag" />
</div>
<br />
</td>
</tr>
<tr>
<td>
<hr />
<br />
<div>
<textarea rows="7" cols="80" id="gi_content" style="font-size:13px;" name="gi_content"></textarea>
</div>
<br />
<hr />
</td>
</tr>
<br />
</table>
<table width="500px" style="margin-left: auto; margin-right: auto;">
<tr>
<td width="33%">
<input type="file" name="gi_img1" value="" />
</td>
<td width="33%">
<input type="file" name="gi_img2" value="" />
</td>
<td width="33%">
<input type="file" name="gi_img3" value="" />
</td>
</tr>
</table>
<div class="wrapper">
<input type="submit" name="submit" value="등록하기" style="width:100px; height:50px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="exit" value="취소하기" style="width:100px; height:50px" onclick="window.close();" />
</div>
</form>
</body>
</html>