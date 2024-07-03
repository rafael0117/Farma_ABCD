<jsp:include page="menufarma.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedidos de la Farmacia</title>
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
	<div class="container bg-light rounded border border-success" style="--bs-bg-opacity: .8;">
			<h3 class="text-center mt-3">Lista de Pedidos</h3>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalEmpleado">
			  Nuevo Pedido
			</button>
			<table id="tableEmpleados" class="table table-striped" style="width:100%">
		        <thead>
		            <tr>
		                <th>Codigo</th>
		                <th>Fecha de emision</th>
		                <th>Nombre de empresa</th>
		                <th>Correo de empresa</th>
		                <th>Cantidad</th>
		                <th>Descripción</th>
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
			        <h1 class="modal-title fs-5" id="staticBackdropLabel">Pedido</h1>
			      </div>
			      <div class="modal-body">
			        <form id="frmEmpleados" method="post" action="ServletPedidoFa?accion=insertar">
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Codigo</label>
					    <input type="text" class="form-control" name="codigo" value="0" id="id-codigo" readonly>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Fecha de emision</label>
					    <input type="date" class="form-control" name="fechaemi" id="id-fechaemi">
					  </div>
					  
					    <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Proveedor</label>
					    <select class="form-select" name="proveedor" id="id-distrito">
						  <option value="" selected>[SELECCIONE PROVEEDOR]</option>
						</select>
					  </div>
					  
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Cantidad</label>
					    <input type="number" class="form-control" name="canped" id="id-canped">
					  </div>
					  
					  <div class="form-group">
							<label for="inputPassword6" class="form-label fw-bold">Descripcion Pedido</label>
							  <textarea class="form-control descripcion" name="descripcion" id="id-descripcion" rows="3" style="height: 60px!important"></textarea>

						</div>
					  
					  
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Correo</label>
					    <input type="text" class="form-control" name="correo" id="id-correo">
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
			<form id="form-eliminar" method="post" action="ServletPedidoFa?accion=eliminar">
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
		<script>
    $(document).ready(function() {
      $('#frmEmpleados').bootstrapValidator({
        fields: {
          fechaemi: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese la fecha de emisión'
              },
              date: {
                format: 'YYYY-MM-DD',
                message: 'El formato de fecha no es válido'
              }
            }
          },
          proveedor: {
            validators: {
              notEmpty: {
                message: 'Por favor, seleccione el proveedor'
              }
            }
          },
          canped: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese la cantidad'
              },
              numeric: {
                message: 'La cantidad debe ser numérica'
              }
            }
          },
          descripcion: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese la descripción del pedido'
              }
            }
          },
          correo: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el correo electrónico'
              },
              emailAddress: {
                message: 'El correo electrónico no es válido'
              }
            }
          }
        }
      });
    });
  </script>
		<c:if test="${sessionScope.MENSAJE!=null}">
			<script>
				toastr.success("${sessionScope.MENSAJE}", "Mensaje", {timeOut:1000})
			</script>
		</c:if>
		<c:remove var="MENSAJE" scope="session"/>
		
		<script>
			leerJSON();
	
			obtenerCorreoProveedor();
			
			function llenarCombo(){
			    $.get("ServletProveedoresJSON", function(data) {
			        $.each(data, function(index, item) {
			            $("#id-distrito").append(`<option value="\${item.codigo}" data-correo="\${item.correo}">\${item.nombre_empresa}</option>`);
			        });
			    });
			}
			function obtenerCorreoProveedor(selectedProvider) {
			    var correoProveedor = $("#id-distrito option:selected").data("correo");
			    $("#id-correo").val(correoProveedor);
			}

			
			$(document).ready(function() {
			    llenarCombo();
			    
			    $("#id-distrito").change(function() {
			        obtenerCorreoProveedor();
			    });
			});

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
				$.get("ServletPedidoFaJSON",function(response){
					$.each(response,function(index,item){
						$("#tableEmpleados tbody").append(`<tr><td>\${item.cod_ped}</td>
															   <td>\${item.fecha_emision_ped}</td>
															   <td>\${item.id_empresa}</td>
															   <td>\${item.correo_empresa}</td>
															   <td>\${item.can_ped}</td>
															   <td>\${item.descripcion}</td>
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
				$.get("ServletPedidoFa?accion=buscarPorCodigo&codigo="+cod,function(data){
					$("#id-codigo").val(data.cod_ped);
					$("#id-fechaemi").val(data.fecha_emision_ped);
					$("#id-distrito").val(data.id_empresa);
					$("#id-canped").val(data.can_ped);
					$("#id-descripcion").val(data.descripcion);
					$("#id-correo").val(data.correo_empresa);
					
				})
			})			
								
		document.addEventListener("DOMContentLoaded", function() {
		    var now = new Date();
		    var offset = now.getTimezoneOffset() * 60000; // Convertir la diferencia de zona horaria a milisegundos
		    var currentDate = new Date(now.getTime() - offset).toISOString().split('T')[0];
		    document.getElementById("id-fechaemi").value = currentDate;
		});

			
		</script>		
</body>
</html>