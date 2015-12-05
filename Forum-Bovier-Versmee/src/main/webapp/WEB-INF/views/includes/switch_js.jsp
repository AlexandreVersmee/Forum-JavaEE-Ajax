
<%-- <c:out value="${pageContext.request.requestURL}"/><br/>
<c:out value="${requestScope['javax.servlet.forward.servlet_path']}"/><br/> 
<c:out value="${pageContext.request.servletPath}"/><br/>
<c:out value="${pageContext.request.contextPath}"/><br/>
<c:out value="${pageContext.request.requestURI}"/>
 --%>
<c:choose>
    <c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/'}">
        <script src="resources/javascripts/filDiscussion.js" type="text/javascript"></script>
    </c:when>
</c:choose>
