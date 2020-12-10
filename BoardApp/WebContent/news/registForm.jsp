<%@ page contentType="text/html; charset=utf-8"%>
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$($("button")[0]).click(function(){	//목록
		location.href="list.jsp";
	});
	$($("button")[1]).click(function(){	//등록
		regist();
	});
});

//폼양식을 서버에 전송하자!
function regist(){
	$("form").attr({
		method:"post",
		action:"regist.jsp"
	});
	$("form").submit();
}

</script>
</head>
<body>
	<form>
		<table align="center">
			<tr>
				<td><input type="text" placeholder="작성자 입력" name="writer"></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="제목 입력" name="title"></td>
			</tr>
			<tr>
				<td><textarea name="content"></textarea></td>
			</tr>
			<tr>
				<td align="center">
					<button type="button">목록</button>	<!-- 원래는 type= submit(전송)이 디폴트로 되어있음 -->
					<button type="button">등록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>