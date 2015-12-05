<%@ taglib prefix="body" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="control" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="row">
  <div class="large-12 columns">
    <div class="nav-bar right">
     <ul class="button-group">
		<li><a href="/Forum-Bovier-Versmee/" class="button" data-tooltip aria-haspopup="true" title="Retour vers l'accueil"><i class="fa fa-home"></i> Accueil</a></li>
		<li><a href="/Forum-Bovier-Versmee/sujets" class="button" data-tooltip aria-haspopup="true" title="Acceder aux sujets"><i class="fa fa-list"></i> Listes des sujets</a></li>
		<control:if test="${membreSession != NULL }">
			<li><a href="/Forum-Bovier-Versmee/profil" class="button" data-tooltip aria-haspopup="true" title="Acceder a votre profil"><i class="fa fa-user"></i> Profil</a></li>
		</control:if>
		<control:if test="${membreSession.pouvoir >= 1 }">
				<li><a href="/Forum-Bovier-Versmee/administration" class="button" data-tooltip aria-haspopup="true" title="Acceder e l'administration"><i class="fa fa-tachometer"></i> Administration</a></li>
		</control:if>
		<control:if test="${membreSession != NULL }">
			<li>
				<form:form method="POST" action="/Forum-Bovier-Versmee/deconnexion">
					<button type="submit" id="deconnexion-btn" data-tooltip aria-haspopup="true" title="Se deconnecter">
						<i class="fa fa-sign-out"></i> Deconnexion
					</button>
				</form:form>
			</li>
		</control:if>
		<control:if test="${membreSession == NULL }">
				<li><a href="/Forum-Bovier-Versmee/connexion" class="button" data-tooltip aria-haspopup="true" title="Se connecter"><i class="fa fa-sign-in"></i> Connexion</a></li>
		</control:if>
      </ul>
    </div>
    <h1>HalloweenBlog</h1>
    <hr/>
  </div>
</div>
