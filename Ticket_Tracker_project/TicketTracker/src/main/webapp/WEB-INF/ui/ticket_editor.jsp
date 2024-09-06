<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${applicationTitle}</title>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<table style="width: 100%">
		<tr>
			<td><%@include file="layout/top_menu_bar.jsp"%>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td style="padding-left: 90px"></td>
						<td>
							<table style="border: 2px solid red;">
								<tr>
									<td rowspan="1"
										style="width: 1150px; background-color: orange; border: 1px solid red; text-align: center;">
										<c:choose>
											<c:when test="${ticketEditorCaption eq 'newTicketPageTitle'}">
												<h1>${newTicketPageTitle}</h1>
											</c:when>
											<c:when
												test="${ticketEditorCaption eq 'editTicketPageTitle'}">
												<h1>${editTicketPageTitle}</h1>
											</c:when>
											<c:otherwise>
												<h1>${viewTicketPageTitle}</h1>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td>
										<table>
											<tr>
												<td><form:form action="save_ticket"
														modelAttribute="ticketModel" method="post">

														<input type="hidden" value="${ticketModel.id}" name="id" />

														<form:errors path="*" cssClass="errorblock" element="div" />

														<table style="width: 900px;">
															<tr>
																<td
																	style="background-color: white; padding-left: 170px;"></td>
																<td
																	style="width: 1000px; background-color: white; font-weight: bolder;">
																	<label for="title">${ticketTitle}</label>
																</td>
															</tr>
															<tr>
																<td
																	style="background-color: white; padding-left: 170px;"></td>
																<td
																	style="width: 1000px; background-color: white; font-weight: bolder;">
																	<c:choose>
																		<c:when test="${newTicket}">
																			<form:input path="ticketTitle" readonly="false"
																				maxlength="50"
																				style="width:800px;background-color:rgb(245, 152, 66);font-weight:bolder;" />
																			<form:errors path="ticketTitle" cssClass="error" />
																		</c:when>
																		<c:otherwise>
																			<form:input path="ticketTitle" readonly="true"
																				maxlength="50"
																				style="width:800px;background-color:rgb(245, 152, 66);font-weight:bolder;" />
																		</c:otherwise>
																	</c:choose>
																</td>
															</tr>
															<tr>
																<td
																	style="background-color: white; padding-left: 170px;"></td>
																<td
																	style="width: 1000px; background-color: white; font-weight: bolder;">
																	<label for="short_description">${ticketShortDescription}</label>
																</td>
															</tr>
															<tr>
																<td
																	style="background-color: white; padding-left: 170px;"></td>
																<td
																	style="width: 1000px; background-color: white; font-weight: bolder;">
																	<c:choose>
																		<c:when
																			test="${ticketEditorCaption eq 'viewTicketPageTitle'}">
																			<form:input path="ticketShortDescription"
																				readonly="true" maxlength="100"
																				style="width:800px;background-color:rgb(245, 152, 66);font-weight:bolder;" />
																		</c:when>
																		<c:otherwise>
																			<form:input path="ticketShortDescription"
																				readonly="false" maxlength="100"
																				style="width:800px;background-color:rgb(245, 152, 66);font-weight:bolder;" />
																			<form:errors path="ticketShortDescription"
																				cssClass="error" />
																		</c:otherwise>
																	</c:choose>

																</td>
															</tr>
															<tr>
																<td
																	style="background-color: white; padding-left: 170px;"></td>
																<td
																	style="width: 1000px; background-color: white; font-weight: bolder;">

																</td>
															</tr>
														</table>
														<br>
														<table style="width: 900px;">
															<tr>
																<td
																	style="background-color: white; padding-left: 525px;"></td>
																<td
																	style="width: 1000px; background-color: white; font-weight: bolder;">
																	<c:choose>
																		<c:when
																			test="${ticketEditorCaption eq 'newTicketPageTitle'}">
																			<input type="submit"
																				value="${newTicketSubmitButtonCaption}"
																				style="color: white; text-decoration: none; border: 2px solid lime; background-color: teal; padding: 5px 5px 5px 5px" />
																		</c:when>
																		<c:when
																			test="${ticketEditorCaption eq 'editTicketPageTitle'}">
																			<input type="submit"
																				value="${editTicketSubmitButtonCaption}"
																				style="color: white; text-decoration: none; border: 2px solid lime; background-color: teal; padding: 5px 5px 5px 5px" />
																		</c:when>
																	</c:choose>
																</td>
															</tr>
														</table>
													</form:form></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>