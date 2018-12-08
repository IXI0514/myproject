<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam</title>
<script type="text/javascript">
	function setanswerspan(me) {
		document.getElementById("answerspan").textContent=me.value;
		var sum =document.getElementById("answersum").textContent;
		sum=sum+1;
	}
	
	/* function setradio(nodename) {
		var answered =document.getElementById("answerspan").textContent;
		var nodes =document.getElementsByName(nodename);
		for(var i=1;i<nodes.length;i++){
			var node =nodes[i];
			alert(node.value+"=="+answered+nodevalue==answered);
			if(node.value==answered){
				node.checked="checked";
			}
		}
	} */
	function turnback() {
		var answered =document.getElementById("answerspan").textContent;
		location.href="../exam/goexam?turnpage=back&answer="+answered;
	}
	function turngo() {
		var answered =document.getElementById("answerspan").textContent;
		location.href="../exam/goexam?turnpage=go&answer="+answered;	
	}
	function turnsub() {
		
		var answered =document.getElementById("answerspan").textContent;	

		var answersum =document.getElementById("answersum").textContent;
		var examsum =document.getElementById("examsum").textContent;
		var reg =/^[A-Z]$/;
		if(answered!=null&&reg.test(answered)){
			answersum++;
		}
		var conf =confirm("确认提交？");
		if(conf){
			alert(answersum!=examsum);
			if(answersum!=examsum){
				var conf2 =confirm("已答"+answersum+"/"+examsum+"\n是否提交??");
				if(conf2){
					location.href="../exam/goexam?turnpage=submit&answer="+answered;
				}
			}else{
				location.href="../exam/goexam?turnpage=submit&answer="+answered;
				
			}
		}
	
	}
	
</script>
</head>
<body onload="setradio(${ pagequestion.getQid()})">
	<div>
		<table>
			<c:set var="pagenum" value="${pagebean.getCurrentPage()}"></c:set>
			<c:set var="pagequestion" value="${pagebean.getList().get(pagenum)}"></c:set>
			<c:set var="answered" value="${pagebean.getAnswered().get(pagenum)}"></c:set>
			<tr>
				<td>题目:${pagenum+1 }</td>
				<td>${pagequestion.getQuestion()}</td>
			</tr>
			<tr>
				<td> <input type="radio"  name="${pagequestion.getQid()}"  value="A" onclick="setanswerspan(this)"> </td>
				<td >${pagequestion.getOptiona()}</td>	
			</tr>
			<tr>
				<td> <input type="radio" name="${pagequestion.getQid()}"    value="B" onclick="setanswerspan(this)"> </td>
				<td >${pagequestion.getOptionb()}</td>	
			</tr>
	
			<c:if test="${not empty pagequestion.getOptionc()}">
				<tr>
					<td> <input type="radio"  name="${pagequestion.getQid()}"   value="C" onclick="setanswerspan(this)"> </td>
					<td >${pagequestion.getOptionc()}</td>	
				</tr>
				<tr>
					<td> <input type="radio"  name="${pagequestion.getQid()}"    value="D" onclick="setanswerspan(this)"> </td>
					<td >${pagequestion.getOptiond()}</td>	
				</tr>
			</c:if>
			<tr>
				<td colspan="2">答案：
					<span id="answerspan" >
					<c:if test="${not empty answered }">
						${answered}
					</c:if>
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left">
					<span>已答：</span>	
					<span id="answersum">${pagebean.getAnswered().size()}</span>
					/<span id="examsum">${pagebean.getItemsum()}</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${ pagenum!=0}">
						<a href="javascript:turnback()">上一题&nbsp</a>
					</c:if>

					<c:if test="${pagenum!=pagebean.getTotalpage()-1}">
						<a href="javascript:turngo()">下一题&nbsp</a>
					</c:if>
					<a href="javascript:turnsub()">提交&nbsp</a>
					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>