<c:choose>
    <c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/fil'}">
    	<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/stylesheets/filDiscussion.css">
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/connexion'}">
    	<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/stylesheets/connexion.css">
    </c:when>
</c:choose>


