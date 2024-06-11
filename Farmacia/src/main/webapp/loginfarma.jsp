<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Farmacia</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<style type="text/css">
	:root{
	  --main-bg:#0dcaf0;
	}
	.main-bg {
	  background: var(--main-bg) !important;
	}	
	input:focus, button:focus {
	  border: 1px solid var(--main-bg) !important;
	  box-shadow: none !important;
	}	
	.form-check-input:checked {
	  background-color: var(--main-bg) !important;
	  border-color: var(--main-bg) !important;
	}	
	.card, .btn, input{
	  border-radius:0 !important;
	}
	.container{
	  margin-top: -150px;
	}
	.card, .btn, input{
	  border-radius:50px !important;
	}
</style>
</head>
<body>
	<div class="container">
    <div class="row justify-content-center" style="margin-top: 300px;!important">
      <div class="col-lg-4 col-md-6 col-sm-6">
      		
      		<c:if test="${sessionScope.TERMINADA!=null}">
		      <div class="alert alert-success" role="alert">
				 ${sessionScope.TERMINADA}
			  </div>
			</c:if>
			<c:if test="${sessionScope.CERRAR!=null}">
		      <div class="alert alert-danger" role="alert">
				 ${sessionScope.CERRAR}
			  </div>
			 </c:if>
		  	
		  	<c:remove var="TERMINADA" scope="session"/>
		  	<c:remove var="CERRAR" scope="session"/>
		  	
        <div class="card shadow">
          <div class="card-title text-center border-bottom">
            <img alt="ABCD logo" src="./img/image.png">
          </div>
          <div class="card-body">
            <form method="post" action="ServletUsuarioFa?accion=INICIARSESION">
              <div class="mb-4">
                <label for="username" class="form-label font-weight-bold">Código</label>
                <input type="text" class="form-control" name="username" />
              </div>
              <div class="mb-4">
                <label for="password" class="form-label font-weight-bold">Contraseña</label>
                <input type="password" class="form-control" name="password" />
              </div>
              <div class="d-grid">
                <button type="submit" class="btn text-light main-bg">Iniciar Sesión</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>