<%@page import="board.model.QnA"%>
<%@page import="java.util.List"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	//DB연동
	QnADAO dao = new QnADAO();
	List<QnA> list = dao.selectAll();

	int totalRecord=list.size();	//총 레코드 수, 실제 DB에 있는 데이터 수를 대입하면 된다
	int pageSize=10;	//한 페이지당 보여줄 레코드 수
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize);	//총 페이지수
	int blockSize=10;	//한 블럭당 보여질 페이지 수
	int currentPage=1;	//현재 페이지 (게시판은 언제나 디폴트가 1페이지)
	
	//아래의 코드는 아무때나 하는 것이 아니다! 어느때만? 누군가가 파라미터를 넘겼을때만... 
	//즉 페이지 넘버를 클릭한 경우를 전제로 숫자화 시키는 코드이다!
	if(request.getParameter("currentPage")!=null){	//즉 페이지를 넘겼다면.. 즉 넘버를 클릭했다면
		currentPage = Integer.parseInt(request.getParameter("currentPage"));		
	}
	int firstPage = currentPage - (currentPage-1)%blockSize;	//반복문의 시작 값 (힌트 : 위에 있는 변수들을 조합한 산술연산)
	int lastPage = firstPage + (blockSize-1);	//반복문의 끝 값
	int num=totalRecord-((currentPage-1)*pageSize);	//페이지당 시작번호(힌트 : 위에있는 모든 변수를 조합하면 금방 나온다)
	int curPos = (currentPage-1)*pageSize;	//페이지당 List에서의 시작 index
	
%>
<%= "totalRecord "+totalRecord+"<br>" %>
<%= "pageSize "+pageSize+"<br>" %>
<%= "totalPage "+totalPage+"<br>" %>
<%= "blockSize "+blockSize+"<br>" %>
<%= "currentPage "+currentPage+"<br>" %>
<%= "firstPage "+firstPage+"<br>" %>
<%= "lastPage "+lastPage+"<br>" %>
<%= "num "+num+"<br>" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 페이징처리</title>
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
img{
	box-sizing:border-box;
}
a{
	text-decoration:none;
}
.pageNum{
	font-size:20pt;
	color:red;
	font-weight:bold;
}
</style>
<script>
</script>
</head>
<body>
<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
	<th>등록일</th>
	<th>조회수</th>
  </tr>
  <%for(int i=1; i<=pageSize; i++){ %>
  <%if(num<1)break; %>
  <%
  	//break문을 만나지 않았다는 것은 레코드가 있다는 것이므로, break문 아래에서 데이터를 추출하자!
  	QnA qna = list.get(curPos++);	//1페이지 : 0~9, 2페이지 : 10~19...
  %>
  <tr>
    <td><%=num-- %></td>
    <td>
    	<%if(qna.getDepth()>0){	//답변인 것만 %>
    	<img src="/images/reply.png" style="margin-left:<%=10*qna.getDepth()%>">
    	<%} %>
    	<%=qna.getTitle() %>
    </td>
    <td><%=qna.getWriter() %></td>
	<td><%=qna.getRegdate() %></td>	
	<td><%=qna.getHit() %></td>
  </tr>
  <%} %>
  <tr>
  	<td colspan="5" style="text-align:center">
  		<%if((firstPage-1)>0){	//페이지가 있다면%>  	
  			<a href="/qna/list2.jsp?currentPage=<%=firstPage-1%>">◀</a>
  		<%}else{ %>
  			<a href="javascript:alert('첫 페이지입니다');">◀</a>
  		<%} %>
		<%for(int i=firstPage; i<=lastPage; i++){ %>
		<%if(i>totalPage) break; //페이지를 출력하는 i가 총페이지수를 넘어서면 반복문 빠져나와라! %>
		<a href="/qna/list2.jsp?currentPage=<%=i %>" <%if(currentPage==i){ %>class="pageNum"<%} %>>[<%=i %>]</a>
		<!-- 내가 보고 있는 페이지만 style 넣기! class="pageNum"으로! -->
		<%} %>
		
		<%if((lastPage+1)<totalPage){ %>
			<a href="/qna/list2.jsp?currentPage=<%=lastPage+1 %>">▶</a>
		<%} else{ %>
			<a href="javascript:alert('마지막 페이지입니다');">▶</a>
		<%} %>
	</td>
  </tr> 
  <tr>
  	<td colspan="5">
		<button>글등록</button>
	</td>
  </tr>
    <tr>
  	<td colspan="5" style="text-align:center">
		<%@ include file="/inc/footer.jsp"%>
	</td>
  </tr>
</table>
</body>
</html>