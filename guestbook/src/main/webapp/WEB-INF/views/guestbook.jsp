<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>NHN Ent. Guestbook</title>
</head>
<body>
	<form name="summitForm" method="post" action="SummitMessage">
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
					<input type="submit" value="저장" />
				</td>
			</tr>
		</table>
	</form>
	<input type="button" value="출력" onClick="location.href='./PrintMessage'"/>
</body>
</html>