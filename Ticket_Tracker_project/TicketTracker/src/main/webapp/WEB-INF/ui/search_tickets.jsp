<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${applicationTitle}</title>
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
						<td style="padding-left: 150px"></td>
						<td style="width: 1100px">
							<h1>${searchPageTitle}</h1> <c:choose>
								<c:when test="${ticketsExist}">
									<table style="width:60%;">
										<tr>
											<td><form:form action="search_tickets" method="post">
													<input type=text name="searchKeyword"
														style="padding: 5px 5px 5px 0px" />
													<input value="${searchButtonCaption}" type="submit"
														style="color: white; text-decoration: none; border: 2px solid lime; background-color: teal; padding: 5px 5px 5px 5px" />
													<input type="hidden" value="${searchKeyword}"
														name="searchKeyword" />
												</form:form>
												<h4 style="color:green"><i>${searchCriteriaDescription}</i></h4></td>
										</tr>
									</table>
									<br>
									<table>
										<tr
											style="width: 1200px; background-color: orange; border: 1px solid red;">
											<th
												style="width: 5%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticketIdHeaderCaption}</th>
											<th
												style="width: 15%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticketTitleHeaderCaption}</th>
											<th
												style="width: 40%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticketDescriptionHeaderCaption}</th>
											<th
												style="width: 15%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticketDateHeaderCaption}</th>
											<th
												style="width: 25%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticketActionsHeaderCaption}</th>
										</tr>

										<c:forEach var="ticket" items="${tickets}">
											<tr>
												<td id="id"
													style="width: 5%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticket.id}</td>
												<td id="title"
													style="width: 15%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticket.ticketTitle}</td>
												<td id="description"
													style="width: 40%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticket.ticketShortDescription}</td>
												<td id="created_on"
													style="width: 15%; border: 1px solid red; padding: 10px 10px 10px 10px">${ticket.createdOn}</td>
												<td
													style="width: 25%; border: 1px solid red; padding: 10px 10px 10px 10px; text-align: center">
													<table>
														<tr>
															<td style="padding-left: 40px;"></td>
															<td><form:form action="edit_ticket" method="post">
																	<input type="submit" value="${editTicketLinkCaption}"
																		style="color: white; text-decoration: none; border: 2px solid lime; background-color: teal; padding: 5px 5px 5px 5px" />
																	<input type="hidden" name="id" value="${ticket.id}" />
																</form:form></td>
															<td><form:form action="delete_ticket" method="post">
																	<input type="submit" value="${deleteTicketLinkCaption}"
																		style="color: white; text-decoration: none; border: 2px solid lime; background-color: teal; padding: 5px 5px 5px 5px" />
																	<input type="hidden" name="id" value="${ticket.id}" />
																</form:form></td>
															<td><form:form action="view_ticket"
																	modelAttribute="${ticket.id}" method="post">
																	<input type="submit" value="${viewTicketLinkCaption}"
																		style="color: white; text-decoration: none; border: 2px solid lime; background-color: teal; padding: 5px 5px 5px 5px" />
																	<input type="hidden" name="id" value="${ticket.id}" />
																</form:form></td>
														</tr>
													</table>
												</td>
											</tr>
										</c:forEach>
									</table>
								</c:when>
								<c:otherwise>
									<table>
										<tr>
											<td style="width: 100%; border: 1px solid red;">No
												tickets found in the database.</td>
										</tr>
									</table>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>