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
	function modify(number) {
		var password = prompt('등록시 입력한 비밀번호를 입력하세요', '');
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", "./write");
		var id = document.createElement("input");
		id.setAttribute("type", "hidden");
		id.setAttribute("name", "id");
		id.setAttribute("value", number);
		form.appendChild(id);
		var hashedPass = document.createElement("input");
		hashedPass.setAttribute("type", "hidden");
		hashedPass.setAttribute("name", "pass");
		hashedPass.setAttribute("value", hash(password));
		form.appendChild(hashedPass);
		document.body.appendChild(form);
		form.submit();
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<input type="button"
			class="btn btn-default" value="글쓰기" onClick="location.href='./write'" />
		${table}
	</div>
</body>
</html>