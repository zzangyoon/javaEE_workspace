<%@page import="board.model.News"%>
<%@page import="java.util.List"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	NewsDAO dao = new NewsDAO();
	List<News> list = dao.selectAll();

	int totalRecord = list.size();	//총 레코드 수
	int pageSize = 10;	//페이지당 보여질 레코드수(개발자가 임의로 지정 가능)
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);	//총 페이지수
	int blockSize = 10;	//블럭당 보여질 페이지수(개발자가 임의로 지정 가능)
	int currentPage = 1;	//현재 페이지 (디폴트는 첫 페이지이다! 따라서 1)
	if(request.getParameter("currentPage")!=null){	//파라미터로 페이지가 전달된다면...
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage = currentPage - (currentPage-1)%blockSize;	//가장 근접한 수를 뺴준다(currentPage-1)
	int lastPage = firstPage + (blockSize-1);
	int curPos = (currentPage-1)*pageSize;	//페이지당 List에서의 시작 index (list는 0부터 시작하므로)
	int num = totalRecord - curPos;	//페이지당 시작 번호 (총 레코드수 부터 일정부분씩 빼야한다)
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
body{font-size:11pt;}
table{
	width:75%;
	border:1px solid black;
}
td{
	border:1px solid gray;
}
a{text-decoration:none;}

.pageNum{
	font-size:15pt;
	color : blue;
	font-weight:bold;
}
.inactive{
	color:#cccccc;
}
</style>
<script>
function showColor(obj){
	obj.style.background="dodgerblue";
}
function hideColor(obj){
	obj.style.background="";
}
</script>
</head>
<body>

	<table>
		<tr style="text-align:center">
			<td width="5%">No</td>
			<td width="60%">제목</td>
			<td width="15%">작성자</td>
			<td width="15%">등록일</td>
			<td width="5%">조회수</td>
		</tr>
		<%for(int i=1; i<=pageSize; i++){ %>
		<%if(num<1)break; %>
		<%News news = list.get(curPos++); 	//curPos에 pageSize만큼(10개)가 들어있으므로, 1씩 증가시켜서 꺼낸다%>	
		<tr onMouseOver="showColor(this)" onMouseout="hideColor(this)"><!-- 여기서 this는 tr이다 -->
			<td style="text-align:center"><%=num-- %></td>
			<td>
			<%if(news.getWriter().length()<1){//작성자의 문자열 길이가 0이라면 (삭제된 게시물이라면 글씨만 출력) %>
				<span class="inactive"><%=news.getTitle() %></span>
			<%}else{ %>
				<a href="detail.jsp?news_id=<%=news.getNews_id()%>"><%=news.getTitle() %>
				<%if(news.getCnt()>0){ %>
				[<%=news.getCnt() %>]
				<%} %>
			<%} %>
			</a>
			</td>
			<td style="text-align:center"><%=news.getWriter() %></td>
			<td style="text-align:center"><%=news.getRegdate().substring(0, 10) %></td>
			<td style="text-align:center"><%=news.getHit() %></td>
		</tr>
		<%} %>
		
		<tr>
			<td colspan="5" align="center">
				 <a href="list.jsp?currentPage=<%=firstPage-1%>">◀</a>
				 <%for(int i=firstPage; i<=lastPage; i++){ %>
				 <%if(i>totalPage)break; 	//총 페이지수 넘어서면%>
				 <a href="list.jsp?currentPage=<%=i%>" <%if(currentPage==i){ %>class="pageNum"<%} %>>[<%=i %>]</a>
				 <%} %>
				 <a href="list.jsp?currentPage=<%=lastPage+1 %>">▶</a>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<button onClick="location.href='registForm.jsp';">글쓰기</button>
			</td>
		</tr>
	</table>

</body>
</html>