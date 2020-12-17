<%@page import="blood.model.BloodAdvisor"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	/*
	String blood = "";	//초기값 부여 (아니면 null이 되서 오류가 난다)
	
	if(request.getParameter("blood")!=null){	//넘어온 파라미터가 있을때는 그 파라미터로 대체
		blood=request.getParameter("blood");
	}
	out.print("전송된 혈액형은 "+blood);
	out.print("<p>");
	----------------------------------------------------------------------------------------------
	String msg=null;
	if(blood.equals("A")){
		msg="꼼꼼하고 세심하다 착하고 솔직하다, 그러나 때론 소심하다";
	}else if(blood.equals("B")){
		msg="여자는 엉뚱매력 쿨하고 활발하다, 그러나 남자는 고집이 쎄다";
	}else if(blood.equals("O")){
		msg="사교성 있고, 둥글둥글하다, 그러나 쓸데없이 오지랖이 넓다";
	}else if(blood.equals("AB")){
		msg="결정이 자꾸 바뀐다, 뒤집는다, 4차원적이다";	>BloodAdvisor.java로 따로 뺴기!
	}
	------------------------------------------------------------------------------------------
	//혈액형에 대한 판단 메시지 작성 및 출력
	BloodAdvisor advisor = new BloodAdvisor();
	String msg = advisor.getAdvice(blood);
	out.print(msg);
	out.print("<p>");
	*/
	

%>
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
	form.action = "/blood.do";
	form.method="get";
	form.submit();	//전송
}
</script>
</head>
<body>
	<form>
		<select name="blood">
			<option>혈액형을 선택하세요</option>
			<option value="A">A형</option>
			<option value="B">B형</option>
			<option value="O">O형</option>
			<option value="AB">AB형</option>
		</select>
		<button type="button" onClick="send()">분석보기</button>
	</form>
</body>
</html>