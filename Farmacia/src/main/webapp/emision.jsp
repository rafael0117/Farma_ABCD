
<%
HttpSession sesion = request.getSession(false);
if (session == null || session.getAttribute("DATOS") == null) {
	response.sendRedirect("loginfarma.jsp");
}
%>
<jsp:include page="menufarma.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Emision de CDP</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<link
	href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-bulma/bulma.css"
	rel="stylesheet">
<style>
.contenedor {
	display: flex;
	justify-content: space-between;
	margin-top: 100px;
}

.contenedor>div {
	flex: 0 0 48%;
}

.help-block {
	color: red;
}

.form-group.has-error .form-control-label {
	color: red;
}

.form-group.has-error .form-control {
	border: 1px solid red;
	box-shadow: 0 0 0 0.2rem rgba(250, 16, 0, 0.18);
}

.form-group.has-error .form-select {
	border: 1px solid red;
	box-shadow: 0 0 0 0.2rem rgba(250, 16, 0, 0.18);
}

.container {
	margin-top: -150px;
}

.codigos {
	display: flex;
	justify-content: space-between;
}
</style>
<script src="https://kit.fontawesome.com/1da5200486.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.12.1/css/all.css"
	crossorigin="anonymous">
</head>
<body>

	<div class="container-fluid">
		<h2 class="text-center mt-5">Emisión de CDP</h2>
		<div class="contenedor d-flex">
			<div class="col-lg-5">
				<form action="ServletInventarioFa?acciones=BuscarCliente"
					method="POST">
					<div class="card">
						<div class="card-body">
							<div class="form-group">
								<label for="codigo">Datos del Cliente</label>
								<div class="row">
									<div class="col-sm-3">
										<input type="text" id="codigocliente" name="codigocliente"
											value="${c.getDni()}" class="form-control"
											placeholder="Código">
									</div>
									<div class="col-sm-3">
										<input type="submit" name="accion" value="Buscar Cliente"
											class="btn btn-outline-info w-100">
									</div>
									<div class="col-sm-6">
										<input type="text" class="form-control"
											value="${c.getNombres()}" placeholder="Nombre de Cliente">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="codigoProducto">Datos del Producto</label>
								<div class="row">
									<div class="col-sm-3">
										<input type="text" id="codigoproducto" name="codigoproducto"
											value="${producto.getCodigo()}" class="form-control"
											placeholder="Código">
									</div>
									<div class="col-sm-3">
										<input type="submit" name="accion" value="Buscar Producto"
											class="btn btn-outline-info w-100">
									</div>
									<div class="col-sm-6">
										<input type="text" value="${producto.getNom_producto()}"
											name="nomproducto" class="form-control"
											placeholder="Nombre de Producto">
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-4">
										<input type="text" name="precio" class="form-control"
											value="${producto.getPrecio()}" placeholder="Precio">
									</div>
									<div class="col-sm-3">
										<input type="number" name="cant" class="form-control"
											value="1" placeholder="Cantidad">
									</div>
									<div class="col-sm-3">
										<input type="text" name="stock" class="form-control"
											value="${producto.getStock()}" placeholder="Stock">
									</div>
									<div class="col-sm-2">
										<input type="submit" name="accion" value="Agregar"
											class="btn btn-outline-info w-100">
									</div>
								</div>
							</div>
						</div>
					</div>
					<button type="button" class="btn btn-success btn-editar mt-3"
						data-bs-toggle="modal" data-bs-target="#modalEmpleado">Pagar</button>
				</form>
			</div>

			<div class="col-lg-7">
				<div class="card">
					<div class="card-body">
						<div class="d-flex col-sm-5 ml-auto">
							<label class="mr-2">Nro.Serie:</label> <input type="text"
								value="${serie}" name="NroSerie" class="form-control">
						</div>
						<table class="table table-hover mt-3">
							<thead>
								<tr>
									<th>Nro</th>
									<th>Codigo</th>
									<th>Descripcion</th>
									<th>Precio</th>
									<th>Cantidad</th>
									<th>SubTotal</th>
									<th>Acciones</th>
								</tr>
							</thead>
							<!-- Aquí va el cuerpo de la tabla -->
							<tbody>
								<!-- Ejemplo de fila -->
								<c:forEach var="list" items="${lista}">
									<tr>
										<td>${list.getItem()}</td>
										<td>${list.getIdVentas()}</td>
										<td>${list.getDescripcion()}</td>
										<td>${list.getPrecio()}</td>
										<td>${list.getCantidad()}</td>
										<td>${list.getSubtotal()}</td>
										<td class="d-flex"><a href="" class="btn btn-warning">Editar</a>
											<a href="" class="btn btn-danger" style="margin-left: 10px">Eliminar</a>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
					<div class="card-footer d-flex">
						<div class="col-sm-6">
							<input type="submit" name="accion" value="Generar Venta"
								class="btn btn-success"> <input type="submit"
								name="accion" value="Cancelar" class="btn btn-danger">
						</div>
						<div class="col-sm-4 ml-auto">
							<input type="text" name="txtTotal" value="S/. ${totalpagar}"
								class="form-control">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.0/js/bootstrapValidator.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>

	<c:if test="${sessionScope.MENSAJE!=null}">
		<script>
			toastr.success("${sessionScope.MENSAJE}", "Mensaje", {
				timeOut : 1000
			})
		</script>
	</c:if>
	<c:remove var="MENSAJE" scope="session" />

	<script>
		
	</script>
</body>
</html>

