<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>NHN Ent. Guestbook</title>
	<script>
		function textCheck(text) {
			if(text.length==0) {
				alert("본문 내용을 입력하세요.");
				return false;
			}
			else if(text.length>300) {
				alert("300자 이하의 길이로 입력하세요.");
				return false;
			}
			return true;
		}
		
		function passwordCheck(pass) {
			if(pass.length<4) {
				alert("4자 이상의 비밀번호를 입력하세요.");
				document.getElementById('password').value="";
				return false;
			}
			return true;
		}
		
		function emailCheck(email) {
			return true;
		}
	
		function validationCheck() {
			if(textCheck(document.getElementById('text').value)!=true) {
				return false;
			}
			if(passwordCheck(document.getElementById('password').value)!=true) {
				return false;
			}
			if(emailCheck(document.getElementById('email').value)!=true) {
				return false;
			}
			else {
				document.getElementById('submitForm').submit();
				return true;
			}
		}
	</script>
</head>
<body>
	<form id="submitForm" method="post" action="./SubmitMessage">
		<table>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" id="email" value=""/></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" id="password" value=""/></td>
			</tr>
			<tr>
				<td colspan="4">
					본문 <br>
					<textarea name="text" id="text" cols="50" rows="5"></textarea><br>
					<input type="button" value="저장" onclick='validationCheck();' />
				</td>
			</tr>
		</table>
	</form>
	<input type="button" value="출력" onClick="location.href='./PrintMessage'"/>
</body>
</html>