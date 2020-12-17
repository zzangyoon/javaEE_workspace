<%@page import="blood.model.BloodAdvisor"%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
function send() {
	var form = document.querySelector("form");
	form.action = "/movie.do";	//매핑 파일에서 .do로 설정해놓음
	form.method="post";	//한글이 껴있으므로 post로 보내는 것이 낫겠다!
	form.submit();	//전송
}
</script>
</head>
<body>
	<form>
		<select name="movie">
			<option>영화를 선택하세요</option>
			<option value="미션임파서블5">미션임파서블5</option>
			<option value="스타워즈">스타워즈</option>
			<option value="존윅3">존윅3</option>
			<option value="분노의질주">분노의질주</option>
		</select>
		<button type="button" onClick="send()">분석보기</button>
	</form>
</body>
</html>