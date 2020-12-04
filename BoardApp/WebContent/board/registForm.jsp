<%@ page contentType="text/html; charset=utf-8"%>
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
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
/*
GET	:	Http 프로토콜에서 헤더 정보에 데이터를 실어 나른다... 아무래도 헤더이다 보니, 전송가능한 양이 미미하다
			현실비유) 편지봉투에 데이터 노출하여 보내는 꼴
POST :	Http 프로토콜에서 바디영역에 데이터를 실어 나른다. 몸체이다 보니 전송량에 한계가 없다
			현실비유) 편지지에 데이터를 숨겨 보내는 꼴
*/
$(function(){
	$("input[type='button']").click(function(){
		//입력양식을 서버에 전송!
		$("form").attr({
			method: "post"	,	//전송할 양이 많으므로
			action :	"/board/regist.jsp"	//"서버측의 전송받을 jsp"
		});
		$("form").submit();		//전송행위!
	});
});
</script>
</head>
<body>

<div class="container">
  <form>
    <label for="fname">Name</label>
    <input type="text" id="fname" name="author" placeholder="Your name...">

    <label for="lname">Title</label>
    <input type="text" id="lname" name="title" placeholder="Your title...">

    <label for="subject">Content</label>
    <textarea id="subject" name="content" placeholder="Write something.." style="height:200px"></textarea>

    <input type="button" value="send">
  </form>
</div>
<div style="text-align:center">
Copyright All reserved java board
</div>
</body>
</html>