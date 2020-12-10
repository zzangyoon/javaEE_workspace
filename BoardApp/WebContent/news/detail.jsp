<%@page import="board.model.Comments"%>
<%@page import="java.util.List"%>
<%@page import="board.model.CommentsDAO"%>
<%@page import="board.model.News"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	String news_id = request.getParameter("news_id");	//파라미터 받기
	
	NewsDAO dao = new NewsDAO();
	News news = dao.select(Integer.parseInt(news_id));	//데이터 1건 가져오기
	
	//이 게시물에 딸려있는 모든 코멘트 게시물 가져오기!
	CommentsDAO cdao = new CommentsDAO();
	List<Comments> list = cdao.selectAll(Integer.parseInt(news_id));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
table{
	width:70%;
}
td{
	border:2px solid gray;
}
td input, textarea{
	width:99%;
	font-size:1.2em;	/*비율을 말하는 것! em*/
}
textarea{
	height:150px;
}
input[name='msg']{
	width:70%;
}
input[name='author']{
	width:20%;
}

p{
	display:inline-block;
	background:palegoldenrod;
}
.msg{
	width:70%;
}
.author{
	width:11%;
}
.cdate{
	width:17%;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$($("button")[0]).click(function(){	//글 수정
		if(confirm("수정하시겠어요?")){
			update();		
		}
	});
	$($("button")[1]).click(function(){	//삭제
		if(confirm("삭제하시겠습니까?")){
			del();
		}
	});
	$($("button")[2]).click(function(){	//목록
		location.href="list.jsp";
	});
	$($("button")[3]).click(function(){	//댓글등록
		reply();
	});
});

function del(){
	<%if(list.size()>0){%>
	//자식 코멘트가 존재한다면, 업데이트!
	$("form").attr({
		method:"post",
		action:"replace.jsp"
	});
	<%}else{%>
	//자식 코멘트가 없다면 그냥 삭제
	$("form").attr({
		method:"post",
		action:"delete.jsp"
	});
	<%}%>
	$("form").submit();
}
//폼양식을 서버에 전송하자!
function regist(){
	$("form").attr({
		method:"post",
		action:"regist.jsp"
	});
	$("form").submit();
}

//서버에 댓글 등록 요청하기!
function reply(){
	$("form").attr({
		method:"post",
		action:"reply.jsp"
	});
	$("form").submit();
}

//수정
function update(){
	$("form").attr({
		method:"post",
		action:"edit.jsp"
	});
	$("form").submit();
}
</script>
</head>
<body>
	<form>
	<input type="hidden" name="news_id" value="<%=news.getNews_id()%>"><!-- hidden은 꼭 페이지소스로 확인해보기! -->
		<table align="center">
			<tr>
				<td><input type="text" name="writer" value="<%=news.getWriter()%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="title" value="<%=news.getTitle()%>"></td>
			</tr>
			<tr>
				<td><textarea name="content"><%=news.getContent() %></textarea></td>
			</tr>
			<tr>
				<td align="center">
					<button type="button">수정</button>
					<button type="button">삭제</button>
					<button type="button">목록</button>
				</td>
			</tr>
			<tr>
				<td align="center">
					<div>
						<input type="text" placeholder="댓글" name="msg">
						<input type="text" placeholder="작성자" name="author">
						<button type="button">등록</button>
					</div>
				</td>
			</tr>

			<!-- 댓글 리스트 영역 -->
			<tr>
				<td>
					<%for(Comments comments : list){%>
					<div>
						<p class="msg"><%=comments.getMsg() %></p>
						<p class="author"><%=comments.getAuthor() %></p>
						<p class="cdate"><%=comments.getCdate().substring(0,10) %></p>
					</div>
					<%} %>
				</td>			
			</tr>
		</table>
	</form>
</body>
</html>