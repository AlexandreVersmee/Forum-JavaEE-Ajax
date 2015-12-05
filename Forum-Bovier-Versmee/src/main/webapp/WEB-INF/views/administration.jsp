<%@taglib prefix="body" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="control" %>


<body:base_layout>
<div class="row">
	<div class="large-12 text-center columns">
		<div class="large-3 columns">
			<a href="/Forum-Bovier-Versmee/administration-inscriptions">
				<span class="fa-stack fa-lg fa-4x orange ">
				  <i class="fa fa-square-o fa-stack-2x"></i>
				  <i class="fa fa-user-plus fa-stack-1x"></i>
				</span>
				<br/>Activation des profils
			</a>
		</div>
		<control:if test="${membreSession.pouvoir >= 2 }">
		<div class="large-3 columns">
			<a href="/Forum-Bovier-Versmee/administration-pouvoirs">
				<span class="fa-stack fa-lg fa-4x orange ">
				  <i class="fa fa-square-o fa-stack-2x"></i>
				  <i class="fa fa-users fa-stack-1x"></i>
				</span><br/>
				Gerer les profils
			</a>
		</div>
		</control:if>
		<div class="large-3 columns">
			<a href="/Forum-Bovier-Versmee/administration-messages">
				<span class="fa-stack fa-lg fa-4x orange ">
				  <i class="fa fa-square-o fa-stack-2x"></i>
				  <i class="fa fa-wechat fa-stack-1x"></i>
				</span><br/>
				Gerer les messages
			</a>
		</div>
		<div class="large-3 columns end">
			<a href="/Forum-Bovier-Versmee/administration-sujets">
				<span class="fa-stack fa-lg fa-4x orange ">
				  <i class="fa fa-square-o fa-stack-2x"></i>
				  <i class="fa fa-list fa-stack-1x"></i>
				</span><br/>
				Gerer les sujets
			</a>
		</div>
	</div>
</div>
</body:base_layout>