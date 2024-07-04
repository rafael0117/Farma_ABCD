<jsp:include page="menufarma.jsp"/>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<body>

<main>
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
        <div class="row mt-4">
            <c:forEach items="${sessionScope.MENUS}" var="row">
                <div class="col-xl-4 col-md-6">
                    <div class="card rounded-4 bg-success bg-gradient text-white mb-4" style="height: 100px;width:200px">
                        <div class="card-body d-flex align-items-center justify-content-center">
                            <div class="row">
                                <div class="col-sm-10 d-flex align-items-center justify-content-center">
                                    <a class="nav-link text-center" href="${row.ruta}">${row.descripcion}</a>
                                </div>
                                <div class="col-sm-10">
                                 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>