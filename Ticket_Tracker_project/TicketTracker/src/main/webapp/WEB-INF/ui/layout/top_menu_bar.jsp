<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<table style="background-color:olive; ; color:aqua; ; width: 100%">
		<tr>
			<td style="width: 35%;"><h1>${applicationTitle}</h1></td>
			<td style="width: 10%;">
				<h2>
					<a href="display_tickets" style="color:aqua; text-decoration:none;">${searchTicketsLinkCaption}</a>
				</h2>
			</td>
			<td style="width: 55%;">
				<h2>
					<a href="new_ticket" style="color:aqua; text-decoration:none;">${newTicketLinkCaption}</a>
				</h2>
			</td>
		</tr>
	</table>
</body>
</html>