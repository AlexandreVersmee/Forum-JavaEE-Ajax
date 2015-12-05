 <!DOCTYPE html>

<!--[if IE 9]>
<html class="lt-ie10" lang="en"> <![endif]-->
<html class="no-js" lang="en" data-useragent="Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)">
<head>
    <!-- Meta -->
    <meta equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Forum Bovier Versmee</title>
    
     <!-- Favicon -->
    <link rel="apple-touch-icon" sizes="57x57" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192" href="${pageContext.request.contextPath}/resources/images/favicon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/resources/images/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/resources/images/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/images/favicon/favicon-16x16.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/resources/images/favicon/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">

    <!-- Foundation CSS -->
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/stylesheets/foundation/foundation.min.css">
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/stylesheets/foundation/normalize.css">

    <!-- Font Awesome icons -->
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/stylesheets/font-awesome/font-awesome.min.css">

    <!-- Overload of Foundation & Base of layout  -->
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/stylesheets/base.css">

    <!--
    ###############################
    #                             #
    #   Disptacher Stylesheets    #
    #                             #
    ###############################
    -->
    <%@include file="../views/includes/switch_stylesheet.jsp" %>
</head>

<body>
<!--
###############################
#                             #
# Navigation bar (top of App) #
#                             #
###############################
-->
<%@include file="../views/includes/nav_bar.jsp" %>
<!--
###############################
#                             #
#      Flash Message          #
#                             #
###############################
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cc" %>
<div class="row">
	<div class="large-12 columns">
		<cc:choose>
			<cc:when test="${SUCCESS_MESSAGE != null}">
				<div id="flash-message" class="alert-box success text-center text-bold" data-alert>
					${SUCCESS_MESSAGE} <a href="#" class="close">&times;</a>
				</div>
			</cc:when>
			<cc:when test="${ERROR_MESSAGE != null}">
				<div id="flash-message" class="alert-box warning text-center text-bold" data-alert>
					${ERROR_MESSAGE} <a href="#" class="close">&times;</a>
				</div>
			</cc:when>
			<cc:when test="${INFORMATION_MESSAGE != null}">
				<div id="flash-message" class="alert-box info text-center text-bold" data-alert>
					${INFORMATION_MESSAGE} <a href="#" class="close">&times;</a>
				</div>
			</cc:when>
		</cc:choose>
	</div>
</div>
<!--
###############################
#                             #
#     Body (Dynamic part)     #
#                             #
###############################
-->
<%@tag description="Body" pageEncoding="UTF-8" %>
<div class="row">
    <div class="large-12 columns main-content">
        <jsp:doBody/>
    </div>
</div>
<!--
###############################
#                             #
#    Footer (Bottom of App)   #
#                             #
###############################
-->
<%@include file="../views/includes/footer.jsp" %>
<!--
###############################
#                             #
#        JavaScript           #
#                             #
###############################
-->
<!-- Foundation JS       -->
<script src="${pageContext.request.contextPath}/resources/javascripts/vendor/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/javascripts/vendor/modernizr.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/javascripts/foundation.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/javascripts/filDiscussion.js" type="text/javascript"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $(document).foundation();
        var doc = document.documentElement;
        doc.setAttribute('data-useragent', navigator.userAgent);
        $(document).foundation();
        $('.alert-box').delay(3000).fadeOut(4000);
    });
</script>
</body>
</html>
