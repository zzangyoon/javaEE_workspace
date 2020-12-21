<%@page import="com.model2.domain.Board"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//정말로 포워딩이 요청을 유지했는지 테스트해보자!
	//out.print("현재 이 jsp페이지가 사용하는 요청 객체는 "+request);	//memo19
	Board board = (Board)request.getAttribute("board");
	//out.print("요청 객체에 담겨진 board는 "+board);	//memo21
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
  background-color: #f2f2f2;
  padding: 20px;
}
.reply-box{
	background:lightblue;
}
.reply-list{
	overflow: hidden;
}
.reply-list p{
	float:left;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<script>
$(function(){
	CKEDITOR.replace("content");	//textarea의 id가 content인 컴포넌트를 편집기 스킨으로 변경
	
	$($("input[type='button']")[0]).click(function(){	//수정버튼
		edit();
	});
	getCommentList();	//댓글 목록 비동기로 가져오기!! onLoad 할때 가져와야함!!! memo39
});

//글수정 요청
function edit(){
	$("form").attr({
		action:"/board/edit.do",
		method:"post"
	});
	$("form").submit();
}

//댓글 목록 가져오기	memo37
function getCommentList(){
	$.ajax({
		url:"/comment/list.do",
		type:"get",
		data:{
			board_id:<%=board.getBoard_id()%>
		},
		success:function(result){
			//서버에서 전송되어온 데이터는 json 객체가 아닌, 문자열일 뿐이다
			//json={name:"ddd"}
			//var json = JSON.parse(result); 
			//파싱하기 귀찮으므로 comment_list.jsp 에서 인코딩 타입을 text/json으로 주자! memo42
			console.log(result.list.length);
			
			//result에는 서버에서 전송한 json 배열이 들어있다.. 
			//이 배열을 이용하여 아래의 컨텐츠를 완성하기!
			//memo41
			$("#list-area").html("");		//초기화 시킨후
			
			var tag="";
			for(var i=0; i<result.list.length; i++){
				var json = result.list[i];
				tag+="<div class=\"reply-list\">";
		  		tag += "<p style=\"width:75%\">"+json.msg+"</p>";
		  		tag += "<p style=\"width:11%\">"+json.author+"</p>";
		  		tag += "<p style=\"width:13%\">"+json.cdate+"</p>";
		  		tag += "</div>";
	  		}
	  		$("#list-area").html(tag);		//innerHTML과 동일
		}
	});
}

/*memo 26
function registComment(){
	$.ajax({
		url:"/test/json.jsp",
		type:"get",
		//피드백은 success로 받는다, 즉 서버에서 에러없이 데이터의 결과값이 전송되면
		//success 우측에 명시된 익명함수가 동작하게 된다
		success:function(result){
			alert("서버로 부터 받은 결과데이터 "+result);
		}
	});	
}
*/

//현재 페이지가 새로고침(reloading)되지 않게, 비동기방식으로 등록요청을 시도하자!
//순수 js의 ajax를 사용하면 처리가 복잡하므로, jquery-ajax로 처리해보자!
function registComment(){
	$.ajax({
		url:"/comment/regist.do",
		type:"post",
		data:{
			msg:$("input[name='msg']").val(),
			author:$("input[name='author']").val(),
			board_id:<%=board.getBoard_id()%>
		},
		//피드백은 success로 받는다, 즉 서버에서 에러없이 데이터의 결과값이 전송되면
		//success 우측에 명시된 익명함수가 동작하게 된다
		success:function(result){
			//alert("서버로 부터 받은 결과데이터 "+result);
			if(result==1){
				getCommentList();
			}else{
				alert("등록실패");
			}
			
		}
	});	
}
</script>
</head>
<body>

<h3>글 작성하기</h3>

<div class="container">
  <form>
  	<input type="hidden" name="board_id" value="<%=board.getBoard_id()%>">
    <input type="text" name="title" value="<%=board.getTitle()%>">
    <input type="text" name="writer" value="<%=board.getWriter()%>">
    <textarea id="content" name="content" style="height:200px"><%=board.getContent() %></textarea>
    
    <input type="button" value="글수정">
    <input type="button" value="글삭제">
    <input type="button" value="목록보기" onClick="location.href='/board/list.do'">
  	<div class="reply-box">
  		<!-- 댓글 폼이 나올 영역 -->
  		<input type="text" name="msg" placeholder="댓글 입력..." style="width:75%">
  		<input type="text" name="author" placeholder="작성자 입력..." style="width:15%">  		
  		<button type="button" onClick="registComment()">댓글 등록</button>
  	</div>
  	<div id="list-area"></div>
  	<!-- memo41
  	  	<div class="reply-list">
  		<p style="width:75%">댓글 내용 샘플...</p>
  		<p style="width:11%">홍길동</p>
  		<p style="width:13%">2020-12-25</p>
  	</div>
  	 -->
  </form>
</div>

</body>
</html>