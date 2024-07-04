<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Menu Farmacia</title>

<link rel="icon" href="images/fevicon.png" type="image/png" />
<!-- bootstrap css -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- site css -->
<link rel="stylesheet" href="css/style.css" />
<!-- responsive css -->
<link rel="stylesheet" href="css/responsive.css" />
<!-- color css -->
<link rel="stylesheet" href="css/colors.css" />
<!-- select bootstrap -->
<link rel="stylesheet" href="css/bootstrap-select.css" />
<!-- scrollbar css -->
<link rel="stylesheet" href="css/perfect-scrollbar.css" />
<!-- custom css -->
<link rel="stylesheet" href="css/custom.css" />
<!-- calendar file css -->
<link rel="stylesheet" href="js/semantic.min.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
body {
	background-image: url('img/Tecnico.jpg');
	background-position: center top;
	background-repeat: no-repeat;
	background-size: cover;
	background-color: rgba(0, 0, 0, 0.5);
}

.navbar {
	background-image: url('img/Tecnico-nav-m.jpg');
	background-repeat: no-repeat;
	background-position: center;
	background-size: 100%;
}

.mouse:hover {
	cursor: pointer;
	background-color: #dee2e6;
}

body::after {
	content: '';
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	backdrop-filter: blur(14px);
	z-index: -1;
}

.offcanvas-header .btn-close {
	color: #00414b;
}

.offcanvas-body {
	background-color: #00414b;
}

.offcanvas-body h3 {
	color: #FFFFF;
}

.offcanvas-body .nav-link {
	color: #00414b;
}

.offcanvas-body .dropdown-item {
	color: #00414b;
}
</style>
</head>
<body class="inner_page map">
	<div class="full_container">
		<div class="inner_container">
			<!-- Sidebar  -->
			<nav id="sidebar" class="ps active ps--active-y desaparecer">
				<div class="sidebar_blog_1 desaparecer">
					<div class="sidebar-header">
						<div class="logo_section">
							<a href="./farmacia.jsp"><img class="logo_icon img-responsive"
								src="img/image.png" alt="#" /></a>
						</div>
					</div>
					<div class="sidebar_user_info">
						<div class="icon_setting"></div>
						<div class="user_profle_side">
							<div class="user_img">
								<img class="img-responsive" src="${sessionScope.IMAGEN}" alt="#" />
							</div>
							<div class="user_info">
								<h6>${sessionScope.DATOS}</h6>
								<p>
									<span class="online_animation"></span> Online
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="sidebar_blog_2 desaparecer">
					<h4>General</h4>
					<ul class="list-unstyled components">
						<li class="active"><a href="./farmacia.jsp"><i
								class="fa fa-dashboard yellow_color"></i> <span>Inicio</span></a></li>



						
						<c:forEach items="${sessionScope.MENUS}" var="row">
							<li><a href="${row.ruta}"><i
									class="fa fa-diamond purple_color"></i> <span>${row.descripcion}</span></a></li>

						</c:forEach>

		
				
					</ul>
				</div>
			</nav>
			<!-- end sidebar -->
			<!-- right content -->
			<div id="content">
				<!-- topbar -->
				<div class="topbar desaparecer">
					<nav class="navbar navbar-expand-lg navbar-light">
						<div class="full">
							<button type="button" id="sidebarCollapse" class="sidebar_toggle">
								<i class="fa fa-bars"></i>
							</button>
							<div class="right_topbar">
								<div class="icon_info">
									 <ul class="user_profile_dd">
										<li><a class="dropdown-toggle" data-toggle="dropdown"><img
												class="img-responsive rounded-circle"
												src="${sessionScope.IMAGEN}" alt="#" /><span
												class="name_user">${sessionScope.DATOS}</span></a>
											<div class="dropdown-menu">
												<form class="d-flex"
													action="ServletUsuarioFa?accion=CERRARSESION" method="post">
													<button class="btn btn-danger desaparecer" type="submit">Cerrar
														Sesión</button>
												</form>
											</div></li>
									</ul>
								</div>
							</div>
						</div>
					</nav>
				</div>
				<!-- end topbar -->
				<!-- dashboard inner -->








			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- chart js -->
	<script src="js/Chart.min.js"></script>
	<script src="js/Chart.bundle.min.js"></script>
	<script src="js/utils.js"></script>
	<script src="js/analyser.js"></script>
	<!-- wow animation -->
	<script src="js/animate.js"></script>
	<!-- select country -->
	<script src="js/bootstrap-select.js"></script>
	<!-- owl carousel -->
	<script src="js/owl.carousel.js"></script>
	<!-- nice scrollbar -->
	<script src="js/perfect-scrollbar.min.js"></script>
	<!-- sidebar -->
	<script>
		var ps = new PerfectScrollbar('#sidebar');
	</script>
	<!-- custom js -->
	<script src="js/custom.js"></script>
</body>
</html>