<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<h6>Loyalty Contest</h6>
	<h5>Welcome ${customer.email}</h5>

	<c:if test="${daNClaimed}">
		
		<input type="submit" onclick="claimdaily()" id="dailyPoints" value="claim daily points" >
	</c:if>
	<c:if test="${!daNClaimed}">
	 <p>You have already claimed your points for today</p>
	</c:if>
	<c:if test="${waNClaimed}">
		<form action="claimWeekly" method="post"><input type="submit" onclick="claimdaily()" id="dailyPoints" value="claim weakly points" ></form>
	</c:if>
	<c:if test="${!waNClaimed}">
	 <p>You have already claimed your points for today</p>
	</c:if>
	<c:if test="${maNClaimed}">
		<form action="claimMonthly" method="post"><input type="submit" onclick="claimdaily()" id="dailyPoints" value="claim Monthly points" ></form>
	</c:if>
	<c:if test="${!maNClaimed}">
	 <p>You have already claimed your points for today</p>
	</c:if>
	
	<table>
		<thead>
			<tr>
				<td>Date</td>
				<td>Scored points</td>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="score" items="${scoreList}" varStatus="loop">
				<tr>
					<td>${loop.index+1}</td>
					<td>${score.checkinDate}</td>
					<td>${score.points}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

</body>
<script>
	function claimdaily() {
		window.location="${pageContext.request.contextPath}/getPoints";
	}

	function getXMLHttpRequest() {
		var xmlHttpReq = false;
		// to create XMLHttpRequest object in non-Microsoft browsers
		if (window.XMLHttpRequest) {
			xmlHttpReq = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			try {
				// to create XMLHttpRequest object in later versions
				// of Internet Explorer
				xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (exp1) {
				try {
					// to create XMLHttpRequest object in older versions
					// of Internet Explorer
					xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (exp2) {
					xmlHttpReq = false;
				}
			}
		}
		return xmlHttpReq;
	}
	/*
	 * AJAX call starts with this function
	 */
	function makeRequest() {
		var xmlHttpRequest = getXMLHttpRequest();
		xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest);
		xmlHttpRequest.open("POST", "helloWorld.do", true);
		xmlHttpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.send(null);
	}

	/*
	 * Returns a function that waits for the state change in XMLHttpRequest
	 */
	function getReadyStateHandler(xmlHttpRequest) {

		// an anonymous function returned
		// it listens to the XMLHttpRequest instance
		return function() {
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					document.getElementById("hello").innerHTML = xmlHttpRequest.responseText;
				} else {
					alert("HTTP error " + xmlHttpRequest.status + ": "
							+ xmlHttpRequest.statusText);
				}
			}
		};
	}
</script>
</html>