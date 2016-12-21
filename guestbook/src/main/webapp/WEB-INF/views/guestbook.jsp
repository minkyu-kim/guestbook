<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>NHN Ent. Guestbook</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css?ver=2"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/passwordHashing.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		var selectId;
		function modify() {
			var password = $("#pass").val();
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", "./write");
			var id = document.createElement("input");
			id.setAttribute("type", "hidden");
			id.setAttribute("name", "id");
			id.setAttribute("value", selectId);
			form.appendChild(id);
			var hashedPass = document.createElement("input");
			hashedPass.setAttribute("type", "hidden");
			hashedPass.setAttribute("name", "pass");
			hashedPass.setAttribute("value", passHash(password));
			form.appendChild(hashedPass);
			document.body.appendChild(form);
			form.submit();
		}

		var dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 210,
			width : 300,
			modal : true,
			buttons : {
				"확인" : modify,
				"취소" : function() {
					dialog.dialog("close");
				}
			}
		});
		$("[id^='modify']").button().on("click", function() {
			dialog.dialog("open");
			selectId=$(this).attr('id').substring(6);
		});
	});
</script>
</head>
<body>
	<div id="dialog-form" title="등록시 입력한 비밀번호를 입력하세요">
		<form>
			<fieldset>
				<div class="form-group">
					<label for="pass"> 비밀번호: </label> <input type="password"
						class="form-control" id="pass">
				</div>
			</fieldset>
		</form>
	</div>

	<div class="container-fluid">
		<input type="button" class="btn btn-default" value="글쓰기"
			onClick="location.href='./write'" />
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th width="70">글번호</th>
							<th width="180">이메일</th>
							<th width="400">본문</th>
							<th width="200">등록/수정 시각</th>
							<th width="50"></th>
						</tr>
					</thead>
					<tbody>${table}</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>