<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="control" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<body:base_layout>
<div class="row">
	<div class="large-12 columns">
		<table>
			<thead>
			<tr>
				<th>Nom</th>
				<th>Pseudo</th>
				<th>Adresse email</th>
				<th>Droits</th>
				<control:if test="${membreSession.pouvoir >= 2 }">
				<th class="text-center">Utilisateur</th>
				<th class="text-center">Moderateur</th>
				<th class="text-center">Administrateur</th>		
				</control:if>
			</tr>
			</thead>
            	<tbody>
                	<c:forEach var="item" items="${membreList}" varStatus="status">
                    	<jsp:useBean id="status" type="javax.servlet.jsp.jstl.core.LoopTagStatus"/>
                    	<c:choose>
                    		<c:when test="<%=status.getCount()%2==0%>">
                            	<tr>
                                	<td><c:out value="${item.name}"/></td>
                                	<td><c:out value="${item.pseudo}"/></td>
                                	<td><c:out value="${item.email}"/></td>
                                	<c:choose>
                    					<c:when test="${item.pouvoir == 0}">
                    						<td>Membre</td>
                    					</c:when>
                    					<c:when test="${item.pouvoir == 1}">
                    						<td>Modérateur</td>
                    					</c:when>
                    					<c:when test="${item.pouvoir == 2}">
                    						<td>Administrateur</td>
                    					</c:when>
                   					</c:choose>
                   					<control:if test="${membreSession.pouvoir >= 2 }">
                                	<td class="text-center">
	                                	<form:form method="POST" action="/Forum-Bovier-Versmee/administration-pouvoirs/utilisateur/${item.id}">
    		                            	<button type="submit" value="Submit" class="small">
												<spring:message code="validate" />
		                                	</button>
            	                    	</form:form>
                                	</td>
                                	<td class="text-center">
	                                	<form:form method="POST" action="/Forum-Bovier-Versmee/administration-pouvoirs/moderateur/${item.id}">
    		                            	<button type="submit" value="Submit" class="small">
												<spring:message code="validate" />
		                                	</button>
            	                    	</form:form>
                                	</td>
                                	<td class="text-center">
	                                	<form:form method="POST" action="/Forum-Bovier-Versmee/administration-pouvoirs/administrateur/${item.id}">
    		                            	<button type="submit" value="Submit" class="small">
												<spring:message code="validate" />
		                                	</button>
            	                    	</form:form>
                                	</td>      
                                	</control:if>                       	                                	
                            	</tr>
                        	</c:when>
                        	<c:otherwise>
                            	<tr class="bgOrangeSoft">
                                	<td><c:out value="${item.name}"/></td>
                                	<td><c:out value="${item.pseudo}"/></td>
                                	<td><c:out value="${item.email}"/></td>
                               		<c:choose>
                    					<c:when test="${item.pouvoir == 0}">
                    						<td>Membre</td>
                    					</c:when>
                    					<c:when test="${item.pouvoir == 1}">
                    						<td>Modérateur</td>
                    					</c:when>
                    					<c:when test="${item.pouvoir == 2}">
                    						<td>Administrateur</td>
                    					</c:when>
                   					</c:choose>
                   					<control:if test="${membreSession.pouvoir >= 2 }">
                                	<td class="text-center">
	                                	<form:form method="POST" action="/Forum-Bovier-Versmee/administration-pouvoirs/utilisateur/${item.id}">
    		                            	<button type="submit" value="Submit" class="small">
												<spring:message code="validate" />
		                                	</button>
            	                    	</form:form>
                                	</td>
                                	<td class="text-center">
	                                	<form:form method="POST" action="/Forum-Bovier-Versmee/administration-pouvoirs/moderateur/${item.id}">
    		                            	<button type="submit" value="Submit" class="small">
												<spring:message code="validate" />
		                                	</button>
            	                    	</form:form>
                                	</td>
                                	<td class="text-center">
	                                	<form:form method="POST" action="/Forum-Bovier-Versmee/administration-pouvoirs/administrateur/${item.id}">
    		                            	<button type="submit" value="Submit" class="small">
												<spring:message code="validate" />
		                                	</button>
            	                    	</form:form>
                                	</td> 
                                	</control:if>                               	                                	
                            	</tr>
                        	</c:otherwise>
                    	</c:choose>
                	</c:forEach>
                </tbody>
            </table>
            <c:if test="${empty membreList}">
            <div class="row">
                <div class="large-12 columns text-center grey">
                    <i>Pas d'utilisateurs à valider</i>
                </div>
            </div>
            </c:if>
            	</div>
</div>
</body:base_layout>