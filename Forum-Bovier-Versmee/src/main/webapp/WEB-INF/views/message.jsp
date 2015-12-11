<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="control" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<body:base_layout>
<div class="row">
	<div class="large-12 columns">
		<h3 class="orange"><c:out value="${discussion.title}"/></h3>
		<br/>
		
		<!-- DISPLAY LIST MESSAGE  -->
		<div class="row">
			<div id="messageList" class="large-12 columns">
			</div>
		</div>
	</div>
</div>
<br/>

<!-- Line between messageList and btn to add message -->
<div class="row"><div class="large-12 columns"><hr></div></div> 

<!-- BTN TO DISPLAY POPUP  -->
<div class="row">
	<div class="large-12 columns">
		<button id="newMessage"><i class="fa fa-comments-o"></i>  Ajouter une message</button>
	</div>
</div>

<!-- POPUP TO ADD MESSAGE -->
<div id="dialog" title="Ajouter un message" class="hide">
	<form:form method="POST" modelAttribute="addMessage" action="/Forum-Bovier-Versmee/discussion/add/${discussion.id}">
		<div class="row">
			<div class="large-12 columns">
				<div class="row">
					<div class="large-12 columns">
						<spring:message code="enter.message.ph" var="messagePh" />
						<label><spring:message code="enter.message.lbl" /> <form:input
								path="texte" type="text" placeholder="${messagePh}" /> <form:errors
								path="texte" cssClass="error" /> </label>
					</div>
				</div>
				<div class="row">
					<div class="large-12 columns">
						<button id="btnSubmit" class="right small" type="submit">Valider</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
<div id="dialogEdit" title="Editer un message" class="hide">
	<form:form method="POST" modelAttribute="editMessage" action="/Forum-Bovier-Versmee/discussion/${discussion.id}/edit/">
		<div class="row">
			<div class="large-12 columns">
				<div class="row">
				<input id="editId" />
					<div class="large-12 columns">
						<spring:message code="enter.message.ph" var="messagePh" />
						<label>Editez votre message<form:input path="texte"
								type="text" class="editField" placeholder="${messagePh}" /> <form:errors
								path="texte" cssClass="error" />
						</label>
					</div>
				</div>
				<div class="row">
					<div class="large-12 columns">
						<button id="btnEditSubmit" class="right small" type="submit">Valider</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
</body:base_layout>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/javascripts/messageAjax.js" type="text/javascript"></script>
