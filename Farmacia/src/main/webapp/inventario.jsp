<jsp:include page="menufarma.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inventario</title>
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
		<div class="container">
			<h3 class="text-center mt-3">Lista de Medicamentos</h3>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalMedicamentos">
			  Nuevo Medicamento
			</button>
			<table id="tableMedicamentos" class="table table-striped" style="width:100%">
		        <thead>
		            <tr>
		                <th>Código</th>
		                <th>Medicamento</th>
		                <th>Fecha ingreso </th>
		                <th>Fecha caducidad</th>
		                <th>Precio venta</th>
		                <th>Receta</th>
		           		<th>Stock</th>
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
			        <h1 class="modal-title fs-5" id="staticBackdropLabel">Medicamentos</h1>
			      </div>
			      <div class="modal-body">
			        <form id="frmEmpleados" method="post" action="ServletInventarioFa?accion=insertar">
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Código</label>
					    <input type="text" class="form-control" name="codigo" value="0" id="id-codigo" readonly>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Medicamento</label>
					    <input type="text" class="form-control" name="medicamento" id="id-medicamento">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Precio</label>
					    <input type="text" class="form-control" name="precio" id="id-precio">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Fecha ingreso</label>
					    <input type="date" class="form-control" name="ingreso" id="id-ingreso">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Fecha caducidad</label>
					    <input type="date" class="form-control" name="caducado" id="id-caducado">
					  </div>
					    <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Receta</label>
					    <select class="form-select" name="receta" id="id-receta">
						  <option value="" selected>[SELECCIONE]</option>
						  <option value="obligatorio">Obligatorio</option>
						  <option value="libre">Libre</option>					
						</select>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">stock</label>
					    <input type="text" class="form-control" name="stock" id="id-stock">
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
			<form id="form-eliminar" method="post" action="ServletInventarioFa?accion=eliminar">
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
          medicamento: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el medicamento'
              }
            }
          },
          precio: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el precio'
              },
              numeric: {
                message: 'El precio debe ser numérico'
              }
            }
          },
          ingreso: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese la fecha de ingreso'
              },
              date: {
                format: 'YYYY-MM-DD',
                message: 'El formato de fecha no es válido'
              }
            }
          },
          caducado: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese la fecha de caducidad'
              },
              date: {
                format: 'YYYY-MM-DD',
                message: 'El formato de fecha no es válido'
              }
            }
          },
          receta: {
            validators: {
              notEmpty: {
                message: 'Por favor, seleccione si el medicamento requiere receta'
              }
            }
          },
          stock: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el stock'
              },
              numeric: {
                message: 'El stock debe ser numérico'
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
			$.get("ServletInventarioFaJSON",function(response){
				$.each(response,function(index,item){
					$("#tableMedicamentos tbody").append(`<tr><td>\${item.codigo}</td>
														   <td>\${item.nom_producto}</td>
														   <td>\${item.fecha_ingreso}</td>
														   <td>\${item.fecha_caducidad}</td>
														   <td>\${item.precio}</td>
														   <td>\${item.receta}</td>
														   <td>\${item.stock}</td>
														   <td>
										                	<button type="button" class="btn btn-success btn-editar" data-bs-toggle="modal" data-bs-target="#modalMedicamentos">Editar</button>
															<button type="button" class="btn btn-danger btn-eliminar">Eliminar</button>
										                	</td>
														   </tr>`);
				})
				new DataTable("#tableMedicamentos");
			})
		}
		
		
		$(document).on("click",".btn-editar",function(){
			let cod;
			cod=$(this).parents("tr").find("td")[0].innerHTML;
			$.get("ServletInventarioFa?accion=buscarPorCodigo&codigo="+cod,function(data){
				$("#id-codigo").val(data.codigo);
				$("#id-medicamento").val(data.nom_producto);
				$("#id-precio").val(data.precio);
				$("#id-ingreso").val(data.fecha_ingreso);
				$("#id-caducado").val(data.fecha_caducidad);
				$("#id-receta").val(data.receta);
				$("#id-stock").val(data.stock);
		
			})
		})
	
		document.addEventListener("DOMContentLoaded", function() {
		    var now = new Date();
		    var offset = now.getTimezoneOffset() * 60000; // Convertir la diferencia de zona horaria a milisegundos
		    var currentDate = new Date(now.getTime() - offset).toISOString().split('T')[0];
		    document.getElementById("id-ingreso").value = currentDate;
		});
		</script>
		 
	</body>
</html>