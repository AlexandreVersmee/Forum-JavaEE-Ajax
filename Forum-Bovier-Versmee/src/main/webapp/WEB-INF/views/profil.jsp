<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<body:base_layout>
		<div class="large-12 columns">
			<div class="large-6 large-centered columns">
			<h2 class="text-center orange">Profil</h2>
			<form:form method="POST" modelAttribute="modifMembre" action="/Forum-Bovier-Versmee/profil/add">		
				<div class="row">
				  <div class="large-12 columns">
				    <label>Name
				      <form:input path="name" type="text" placeholder="nom"/>
					  <form:errors path="name" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
				<div class="row">
				  <div class="large-12 columns">
				    <label>Mail
				      <form:input path="email" type="text" placeholder="mail"/>
					  <form:errors path="email" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
				<div class="row">
				  <div class="large-12 columns">
				    <label>Password
				      <form:input path="password" type="password" placeholder="password"/>
					  <form:errors path="password" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
				<div class="row">
				  <div class="large-12 columns">
				    <label>IP
				      <form:input path="ip" type="text" placeholder="adresse ip"/>
					  <form:errors path="ip" cssClass="error"></form:errors>
				    </label>
				  </div>
				</div>
				<div class="row">
					<div class="large-6 large-centered columns">
						<button class="white text-center" type="submit">
							<i class="fa fa-sign-in fa-padding"></i> Modifier
						</button>
						<br/>
														
					</div>
				</div>
				<form:hidden path="pseudo"/>
			</form:form>
			</div>
			<a href="#" class="button right" data-reveal-id="myModal"> <i class="fa fa-sign-in fa-padding"></i>  Supprimer mon compte</a>

		</div>
		 <!-- Modal validation suppression  -->
		<div id="myModal" class="reveal-modal" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
			<h2 class="text-center">Suppresion de votre compte</h2><br/>
			<p class="lead text-center">La suppresion de votre compte sera definitive et aucun retour en arrière sera possible.</p>
			<p class="lead text-center">Etes vous sur de vouloir supprimer votre compte ?</p>
			<div class="row">
				<div class="large-4 large-centered columns">
					<div class="large-3 columns">
						<form:form method="POST" modelAttribute="supprMembre" action="/Forum-Bovier-Versmee/profil/suppr">
							<button class="white" type="submit">Oui</button>
						</form:form>
					</div>
					<div class="large-3 columns">
						<a class="close-reveal-modal button white reset-btn-modal" aria-label="Close">Non</a>
					</div>
				</div>
			</div>			
			 <a class="close-reveal-modal" aria-label="Close">&#215;</a>
		</div>
</body:base_layout>