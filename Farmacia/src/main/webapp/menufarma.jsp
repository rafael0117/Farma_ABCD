<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu Farmacia</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
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
        cursor:pointer;
        background-color:#dee2e6;
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
<body>
	
	<nav class="navbar navbar-dark bg-dark fixed-top">
	  <div class="container-fluid"> 
	    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar">
	      <span class="navbar-toggler-icon"></span>
	    </button>	
	    <form class="d-flex" action="ServletUsuarioFa?accion=CERRARSESION" method="post">
	        <button class="btn btn-outline-danger" type="submit">Cerrar Sesión</button>
	    </form>
	    <div class="offcanvas offcanvas-start text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
	      <div class="offcanvas-header">
	      	<img alt="ABCD farma" src="./img/image.png" width="100px" height="100px">	     
	        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	      </div>
	      <div class="offcanvas-body">
	      	<h3>Bienvenid@, ${sessionScope.DATOS}</h3>
	        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">	          
	          <c:forEach items="${sessionScope.MENUS}" var="row">
		          <li class="nav-item">
		            <a class="nav-link text-white mouse" href="${row.ruta}">${row.descripcion}</a>
		          </li>
			  </c:forEach>			         
		      <li class="nav-item dropdown">
		            <ul class="dropdown-menu dropdown-menu-dark">
				          	<li><a class='dropdown-item' href=''></a></li>	
				          	<li><hr class="dropdown-divider"></li>	
		            </ul>
		      </li>    
	        </ul>	        
	      </div>
	    </div>
	  </div>
	</nav>
</body>
</html>