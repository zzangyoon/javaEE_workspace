<%@ page contentType="text/html; charset=utf-8"%>
<%
	//상세보기 페이지의 히든을 통해 전송된 파라미터 중,
	//team, rank, depth를 다시 현재 페이지의 히든에 보관해두자
	String team = request.getParameter("team");		//내본글 team
	String rank = request.getParameter("rank");		//내본글 rank
	String depth = request.getParameter("depth");	//내본글 depth
	
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #ffdd00;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 크롬에 ck editor > download > quickstart >  official CKEditor CDN  -->
<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<script>
/*
GET	:	Http 프로토콜에서 헤더 정보에 데이터를 실어 나른다... 아무래도 헤더이다 보니, 전송가능한 양이 미미하다
			현실비유) 편지봉투에 데이터 노출하여 보내는 꼴
POST :	Http 프로토콜에서 바디영역에 데이터를 실어 나른다. 몸체이다 보니 전송량에 한계가 없다
			현실비유) 편지지에 데이터를 숨겨 보내는 꼴
*/
$(function(){	//onload
	CKEDITOR.replace("subject");	//textarea에 부여한 id(=subject)를 넣어줘야함
	
	$($("input[type='button']")[0]).click(function(){	//답글 등록
		$("form").attr({
			method: "post",	
			action :	"/qna/reply.jsp"	
		});
		$("form").submit();	//전송행위
	});
	$($("input[type='button']")[1]).click(function(){	//이전으로
		history.back();
	});
	
});
</script>
</head>
<body>

<div class="container">
  <form>
  	<input type="hidden" name="team" value="<%=team %>">
  	<input type="hidden" name="rank" value="<%=rank %>">
  	<input type="hidden" name="depth" value="<%=depth%>">
  
    <label for="fname">Name</label>
    <input type="text" id="fname" name="writer" placeholder="Your name...">

    <label for="lname">Title</label>
    <input type="text" id="lname" name="title" placeholder="Your title...">

    <label for="subject">Content</label>
    <textarea id="subject" name="content" placeholder="Write something.." style="height:200px"></textarea>

    <input type="button" value="답글등록">
    <input type="button" value="이전으로">
  </form>
</div>
<div style="text-align:center">
Copyright All reserved java board
</div>
</body>
</html>