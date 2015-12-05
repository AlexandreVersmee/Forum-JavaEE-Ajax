<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<body:base_layout>
		<div class="large-12 columns">
			<div class="large-8 large-centered columns">
			<h2 class="text-center orange">Inscription</h2>
			<form:form method="POST" modelAttribute="addMembre" action="/Forum-Bovier-Versmee/inscription/add">		
				<div class="row">
				  <div class="large-4 columns">
				    <label>Name
				      <form:input path="name" type="text" placeholder="nom" tabindex="1"/>
					  <form:errors path="name" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
				<div class="row">
				  <div class="large-4 columns">
				    <label>Pseudo
				      <form:input path="pseudo" type="text" placeholder="pseudo" tabindex="2"/>
					  <form:errors path="pseudo" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
				<div class="row">
				  <div class="large-4 columns">
				    <label>Mail
				      <form:input path="email" type="text" placeholder="mail" tabindex="3"/>
					  <form:errors path="email" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
				<div class="row">
				  <div class="large-4 columns">
				    <label>Password
				      <form:input path="password" type="password" placeholder="password" tabindex="4"/>
					  <form:errors path="password" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
				<div class="row">
				  <div class="large-4 columns">
				    <label>IP
				      <form:input path="ip" type="text" placeholder="adresse ip" tabindex="5"/>
					  <form:errors path="ip" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
					<div class="row">
						<div class="large-5 large-centered columns">
							<button class="white text-center" type="submit">
								<i class="fa fa-sign-in fa-padding"></i> S'inscrire
							</button>
							<br/>								
						</div>
					</div>	
			</form:form>								
			</div>
		</div>
</body:base_layout>