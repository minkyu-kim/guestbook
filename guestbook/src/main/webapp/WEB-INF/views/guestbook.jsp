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
		
		function hash(password) {
			var hashedValue=1;
			for(var character in password) {
				hashedValue*=101;
				hashedValue+=character;
				hashedValue%=1000000007;
			}
			return hashedValue;
		}
		
		function passwordCheck(pass) {
			if(pass.length<4) {
				alert("4자 이상의 비밀번호를 입력하세요.");
				document.getElementById('password').value="";
				return false;
			}
			document.getElementById('hashedPass').value=hash(pass);
			return true;
		}
		
		function emailCheck(email) {
			var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			var result=email.match(regExp);
			if(result==null) {
				alert("메일 주소가 올바르지 않습니다.");
				document.getElementById('email').value="";
				return false;
			}
			return true;
		}
	
		function validationCheck() {
			if(emailCheck(document.getElementById('email').value)!=true) {
				return false;
			}
			if(passwordCheck(document.getElementById('password').value)!=true) {
				return false;
			}
			if(textCheck(document.getElementById('text').value)!=true) {
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
				<td><input type="text" id="email" name="email" value=""/></td>
				<td>비밀번호</td>
				<td><input type="password" id="password" name="password" value=""/></td>
			</tr>
			<tr>
				<td colspan="4">
					본문 <br>
					<textarea id="text" name="text" cols="50" rows="5"></textarea><br>
					<input type="button" value="저장" onclick='validationCheck();' />
				</td>
			</tr>
		</table>
		<input type="hidden" id="hashedPass" name="hashedPass" value="" />
	</form>
	<input type="button" value="출력" onClick="location.href='./PrintMessage'"/>
</body>
</html>