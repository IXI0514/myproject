<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function  indexup() {
        var indexform =document.getElementById("indexform");
        indexform.action="../exam/upload";
        indexform.enctype="multipart/form-data";
        var uploadfile =document.getElementById("uploadfile").value;
        if(uploadfile==null||uploadfile==""){
        	alert("未选择文件！！");
        }else{
        	var conf =confirm("确认上传？"+uploadfile);
			if(conf){
				indexform.submit();        		
			}
        }
    }
    function  indexexam() {
        var indexform =document.getElementById("indexform");
        indexform.action="../exam/goexam";
		indexform.submit();
    }
</script>
</head>
<body>
	<div>
		<form id="indexform" action="" method="post"  >
			<table>
				<tr>
					<td>用户名：${userinfo.getEname()}</td>
					<td>分数：${userinfo.getScore()}</td>
					
				</tr>
				<tr>
					<td>当前题库数量：</td>
					<td>${itemsum}</td>
				</tr>
				<tr>
					<td>
						<input type="file" id="uploadfile" name="uploadfile"/> 
					</td>
					<td>
						<a href="javascript:indexup()">上传文件</a>
					</td>
					
				</tr>
				<tr>
				<td>
						<input  type="hidden" id="requsetsum" value="20" name="requsetsum" /> 
					</td>
					<td>
						<a href="javascript:indexexam()">随机考试</a>
					</td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>