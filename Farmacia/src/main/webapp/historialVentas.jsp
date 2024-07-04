<jsp:include page="menufarma.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Historial Venta</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link href="https://cdn.datatables.net/2.0.3/css/dataTables.bootstrap5.css" rel="stylesheet">
	<link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet">
	<style>
		.modal-header{
			color:#fff;
			background: #428bca;
			display: flex;
		  	justify-content: center;
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
		.container{
			margin-top: 5em;
		}
	</style>
</head>
<body>
	<div class="container bg-light rounded border border-success" style="--bs-bg-opacity: .8;margin-top:100px;">
			<h3 class="text-center mt-3">Historial Venta</h3>

			<table id="tableMedicamentos" class="table table-striped" style="width:100%">
		        <thead>
		            <tr>
		                <th>Código</th>
		                <th>Fecha Venta</th>
		                <th>Monto</th>
		                <th>Vendedor</th>
		                <th>Producto</th>
		                <th>Cantidad</th>
		                <th></th>
		            </tr>
		        </thead>
		        <tbody>
	
		        </tbody>
		    </table>
			<div class="modal fade" id="modalMedicamentos" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="staticBackdropLabel">Ventas</h1>
			      </div>
			      <div class="modal-body">
			        <form id="frmEmpleados" method="post" action="">
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Código</label>
					    <input type="text" class="form-control" name="codigo" value="0" id="id-codigo" readonly>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Nombre</label>
					    <input type="text" class="form-control" name="nombre" id="id-nombre">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Fecha Emision</label>
					    <input type="text" class="form-control" name="emision" id="id-emision">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Monto</label>
					    <input type="date" class="form-control" name="monto" id="id-monto">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Correo</label>
					    <input type="date" class="form-control" name="correo" id="id-correo">
					  </div>					  
					 
					  <div class="modal-footer">
				        <button type="submit" class="btn btn-primary">Grabar</button>
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="btn-cerrar">Cerrar</button> 
				      </div>
					</form>
			      </div>
			    </div>

			  </div>
			</div>
			<form id="form-eliminar" method="post" action="ServletHistorialVenta">
				<input type="hidden" name="codigo" id="codigoEliminar">
			</form>
			<form id="form-reporte" action="ServletHistorialVenta?accion=reporte" method="post">
			<input type="hidden" name="reporte" id="codigoReporte"> 
		</form>
		</div>
		<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
		<script src="https://cdn.datatables.net/2.0.3/js/dataTables.js"></script>
		<script src="https://cdn.datatables.net/2.0.3/js/dataTables.bootstrap5.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.0/js/bootstrapValidator.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>		
		<c:if test="${sessionScope.MENSAJE!=null}">
			<script>
				toastr.success("${sessionScope.MENSAJE}", "Mensaje", {timeOut:1000})
			</script>
		</c:if>
		<c:remove var="MENSAJE" scope="session"/>
		
		<script>
		
		leerJSON();
		
		$(document).on("click",".btn-eliminar",function(){
			let cod;
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			$("#codigoEliminar").val(cod);
			
			Swal.fire({
				  title: "¿Seguro de eliminar?",
				  text: "",
				  icon: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#3085d6",
				  cancelButtonColor: "#d33",
				  confirmButtonText: "Si, estoy seguro",
				  cancelButtonText: "No"
				}).then((result) => {
				  if (result.isConfirmed) {
				    $("#form-eliminar").submit();
				  }
				});
		})
		$(document).on("click","#btn-cerrar",function(){
			$("#frmEmpleados").trigger("reset");
			$("#frmEmpleados").bootstrapValidator("resetForm",true);
		})
		
				
	$(document).on("click", ".btn-reporte", function() {
    let cod;
    cod = $(this).parents("tr").find("td")[0].innerHTML;
    $("#codigoReporte").val(cod);

    Swal.fire({
        title: "¿Quieres ver el reporte?",
        text: "",
        icon: "question", // Ícono de pregunta
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: '<i class="fas fa-check-circle"></i> Si, estoy seguro', // Ícono de confirmación
        cancelButtonText: '<i class="fas fa-times-circle"></i> No' // Ícono de cancelación
    }).then((result) => {
        if (result.isConfirmed) {
            $("#form-reporte").submit();
        }
    });
});

		function leerJSON(){
			$.get("ServletEmision",function(response){
				$.each(response,function(index,item){
					$("#tableMedicamentos tbody").append(`<tr><td>\${item.cod_detalle}</td>
														   <td>\${item.fecha_venta}</td>
														   <td>\${item.monto}</td>
														   <td>\${item.nom_emp}</td>
														   <td>\${item.nom_prod}</td>
														   <td>\${item.cantidad}</td>
														   <td>
														   <button type="button" class="btn btn-warning btn-reporte">Reporte</button>
															<button type="button" class="btn btn-danger btn-eliminar">Eliminar</button>
										                	</td>
														   </tr>`);
				})
				new DataTable("#tableMedicamentos");
			})
		}
		
		
	/*$(document).on("click",".btn-editar",function(){
			let cod;
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			$.get("ServletHistorialVenta?accion=buscarPorCodigo&codigo="+cod,function(data){
				$("#id-codigo").val(data.codigo);
				$("#id-medicamento").val(data.fecha_emision);
				$("#id-precio").val(data.monto);
				$("#id-ingreso").val(data.correo);
		
			})
		})*/
	
		/*document.addEventListener("DOMContentLoaded", function() {
		    var now = new Date();
		    var offset = now.getTimezoneOffset() * 60000;
		    var currentDate = new Date(now.getTime() - offset).toISOString().split('T')[0];
		    document.getElementById("id-ingreso").value = currentDate;
		});*/
		</script>

</body>
</html>