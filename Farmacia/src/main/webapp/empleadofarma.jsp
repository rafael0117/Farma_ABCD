<jsp:include page="menufarma.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Empleado Farmacia</title>
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
	.container {	    
		margin-top: 5em;
	}
</style>
</head>
<body>
	<div class="container">
			<h3 class="text-center mt-5">Lista de Empleados</h3>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalEmpleado">
			  Nuevo Empleado
			</button>
			<table id="tableEmpleados" class="table table-striped" style="width:100%">
		        <thead>
		            <tr>
		                <th>Código</th>
		                <th>Nombre</th>
		                <th>Apellido Paterno</th>
		                <th>Apellido Materno</th>
		                <th></th>
		            </tr>
		        </thead>
		        <tbody>     
		        </tbody>
		    </table>
			<div class="modal fade" id="modalEmpleado" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="staticBackdropLabel">Empleado</h1>
			      </div>
			      <div class="modal-body">
			        <form id="frmEmpleados" method="post" action="ServletEmpleadoFa?accion=insertar">
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Código</label>
					    <input type="text" class="form-control" name="codigo" value="0" id="id-codigo" readonly>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Nombre</label>
					    <input type="text" class="form-control" name="nombre" id="id-nombre">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Paterno</label>
					    <input type="text" class="form-control" name="paterno" id="id-paterno">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Materno</label>
					    <input type="text" class="form-control" name="materno" id="id-materno">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Tipo Empleado</label>
					    <select class="form-select" name="tipo" id="id-tipo">
						  <option value="" selected>Seleccione Tipo Empleado</option>
						</select>
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
			<form id="form-eliminar" method="post" action="ServletEmpleadoFa?accion=eliminar">
				<input type="hidden" name="codigo" id="codigoEliminar">
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
			llenarTipo();
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
			
			function leerJSON(){
				$.get("ServletEmpleadoFaJSON",function(response){
					$.each(response,function(index,item){
						$("#tableEmpleados tbody").append(`<tr><td>\${item.codigo}</td>
															   <td>\${item.nombre}</td>
															   <td>\${item.paterno}</td>
															   <td>\${item.materno}</td>
															   <td>
											                	<button type="button" class="btn btn-success btn-editar" data-bs-toggle="modal" data-bs-target="#modalEmpleado">Editar</button>
																<button type="button" class="btn btn-danger btn-eliminar">Eliminar</button>
											                	</td>
															   </tr>`);
					})
					new DataTable("#tableEmpleados");
				})
			}
			$(document).on("click",".btn-editar",function(){
				let cod;
				cod=$(this).parents("tr").find("td")[0].innerHTML;
				$.get("ServletEmpleadoFa?accion=buscarPorCodigo&codigo="+cod,function(data){
					$("#id-codigo").val(data.codigo);
					$("#id-nombre").val(data.nombre);
					$("#id-paterno").val(data.paterno);
					$("#id-materno").val(data.materno);
					$("#id-tipo").val(data.codigoTipo);
				})
			})
			function llenarTipo(){
				$.get("ServletTipoUsuarioFaJSON",function(data){
					$.each(data,function(index,item){
						$("#id-tipo").append(`<option value="\${item.codigoTipo}">\${item.nombreTipo}</option>`)
					})
				})
			}
		</script>
		
		<script type="text/javascript">    
		    $(document).ready(function(){     
		        $('#frmEmpleados').bootstrapValidator({      
		        	 fields:{
		        		nombre:{
			        		validators:{
			        			notEmpty:{
			        				message:'Ingresar nombre'
			        			},
			        			regexp:{
			        				regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\Ó\Ú}]{3,30}$/,
			        				message:'Ingresar solo nombre letras min:3 - max:30'
			        			}
			        		}		 
			        	},
			        	paterno:{
				        	validators:{
				        		notEmpty:{
				        			message:'Ingresar apellido paterno'
				        		},
				        		regexp:{
				        			regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\Ó\Ú}]{2,30}$/,
				        			message:'Ingresar apellido paterno solo letras min:2 - max:30'
				        		}
				        	}		 
				        },
				        materno:{
					        validators:{
					        	notEmpty:{
					        		message:'Ingresar apellido materno'
					        	},
					        	regexp:{
					        		regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\Ó\Ú}]{2,30}$/,
					        		message:'Ingresar apellido materno solo letras min:2 - max:30'
					        	}
					        }		 
					    },
				        tipo:{
			        		validators:{
			        			notEmpty:{
			        				message:'Seleccionar tipo empleado'
			        			}
			        		}		 
			        	}
		        		
		        	 }
		        });   	
		    });    
		</script> 
</body>
</html>