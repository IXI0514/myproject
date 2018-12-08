<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div>
		<table>
			<tr>
				<td>您的成绩是：</td>
				<td>${pagebean.getScore()}</td>
			</tr>		
			<tr>
				<td>您的最好成绩是：</td>
				<td>${pagebean.getBestscore()}</td>
			</tr>
			<tr>
				<td> <a href="index.jsp">继续</a> </td>
				<td> <a href="../exam/signout">退出</a> </td>
			</tr>
		</table>
		<table  style="border:1px solid block;border-collapse: collapse;">
			<tr>
				<td>题号</td>
				<c:forEach begin="1" end="${pagebean.getItemsum()}" step="1" var="i">
    				<td>${i}</td>
 				</c:forEach>	
			</tr>
		
			<tr>
				<td>正确答案</td>
				<c:forEach items="${pagebean.getList()}" var="qitem">
					<td>${qitem.getAnswer().toUpperCase()}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>您的答案</td>
				<c:forEach begin="0" end="${pagebean.getItemsum()-1}" step="1" var="a">
					<c:set value="${pagebean.getAnswered().get(a)}" var="myanswer"></c:set>
					<td>
						${myanswer}
						<c:if test="${empty myanswer}">
								\
						</c:if>
						
					</td>    				
 				</c:forEach>
			</tr>
		</table>
		
	</div>
</body>
</html>