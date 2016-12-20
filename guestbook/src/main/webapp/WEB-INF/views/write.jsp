<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>NHN Ent. Guestbook</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/hash.js"></script>
<script>
	function textCheck(text) {
		if (text.length == 0) {
			alert("본문 내용을 입력하세요.");
			return false;
		} else if (text.length > 300) {
			alert("300자 이하의 길이로 입력하세요.");
			return false;
		}
		return true;
	}

	function passwordCheck(pass) {
		if (pass.length < 4) {
			alert("4자 이상의 비밀번호를 입력하세요.");
			document.getElementById('password').value = "";
			return false;
		}
		document.getElementById('hashedPass').value = hash(pass);
		return true;
	}

	function emailCheck(email) {
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var result = email.match(regExp);
		if (result == null) {
			alert("메일 주소가 올바르지 않습니다.");
			document.getElementById('email').value = "";
			return false;
		}
		return true;
	}

	function validationCheck() {
		if (emailCheck(document.getElementById('email').value) != true) {
			return false;
		}
		if (passwordCheck(document.getElementById('password').value) != true) {
			return false;
		}
		if (textCheck(document.getElementById('text').value) != true) {
			return false;
		} else {
			document.getElementById('submitForm').submit();
			return true;
		}
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<form id="submitForm" class="form-inline" method="post"
					action="./submit">
					<div class="form-group">
						<input type="hidden" id="id" name="id" value="${id}"> <label
							for="email"> 이메일 </label> <input type="email"
							class="form-control" id="email" name="email" value="${email}">
					</div>
					<div class="form-group">

						<label for="password"> 비밀번호 </label> <input type="password"
							class="form-control" id="password" name="password">
					</div>
					<br>
					<div class="form-group">

						<label for="text"> 본문 </label>
						<textarea class="form-control" style="width: 200%; height: 100"
							id="text" name="text">${text}</textarea>
					</div>
					<br>
					<button type="button" class="btn btn-default"
						onclick='validationCheck();'>저장</button>
					<input type="hidden" id="hashedPass" name="hashedPass" value="" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>