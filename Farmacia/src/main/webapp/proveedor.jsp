<jsp:include page="menufarma.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Proveedores</title>
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
			<h3 class="text-center mt-3">Lista de Proveedores</h3>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalMedicamentos">
			  Nuevo Proveedor
			</button>
			<table id="tableMedicamentos" class="table table-striped" style="width:100%">
		        <thead>
		            <tr>
		                <th>Id</th>
		                <th>Ruc</th>
		                <th>Correo</th>
		                <th>Nombre</th>
		                <th>Fecha</th>
		                <th>Direccion</th>
		           		<th>Telefono</th>
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
			        <h1 class="modal-title fs-5" id="staticBackdropLabel">Proveedores</h1>
			      </div>
			      <div class="modal-body">
			        <form id="frmEmpleados" method="post" action="ServletProveedorFa?accion=insertar">
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Código</label>
					    <input type="text" class="form-control" name="codigo" value="0" id="id-codigo" readonly>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Ruc</label>
					    <input type="text" class="form-control" name="ruc" id="id-ruc">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Correo</label>
					    <input type="text" class="form-control" name="correo" id="id-correo">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Nombre</label>
					    <input type="text" class="form-control" name="nombre" id="id-nombre">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Fecha registro</label>
					    <input type="date" class="form-control" name="fecha" id="id-fecha">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Dirección</label>
					    <input type="text" class="form-control" name="direccion" id="id-direcc">
					  </div>					    
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Telefono</label>
					    <input type="text" class="form-control" name="telefono" id="id-telef">
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
			<form id="form-eliminar" method="post" action="ServletProveedorFa?accion=eliminar">
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
          ruc: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el RUC'
              },
              regexp: {
                regexp: /^[0-9]{11}$/,
                message: 'El RUC debe contener 11 dígitos numéricos'
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
          },
          nombre: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el nombre'
              }
            }
          },
          fecha: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese la fecha de registro'
              },
              date: {
                format: 'YYYY-MM-DD',
                message: 'El formato de fecha no es válido'
              }
            }
          },
          direccion: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese la dirección'
              }
            }
          },
          telefono: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el teléfono'
              },
              regexp: {
                regexp: /^[0-9]{9}$/,
                message: 'El teléfono debe contener 9 dígitos numéricos'
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
			$.get("ServletProveedoresJSON",function(response){
				$.each(response,function(index,item){
					$("#tableMedicamentos tbody").append(`<tr><td>\${item.codigo}</td>
														   <td>\${item.ruc}</td>
														   <td>\${item.correo}</td>
														   <td>\${item.nombre_empresa}</td>
														   <td>\${item.fecha_registro}</td>
														   <td>\${item.direccion}</td>
														   <td>\${item.telefono}</td>
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
			$.get("ServletProveedorFa?accion=buscarPorCodigo&codigo="+cod,function(data){
				$("#id-codigo").val(data.codigo);
				$("#id-ruc").val(data.ruc);
				$("#id-correo").val(data.correo);
				$("#id-nombre").val(data.nombre_empresa);
				$("#id-fecha").val(data.fecha_registro);
				$("#id-direcc").val(data.direccion);
				$("#id-telef").val(data.telefono);
		
			})
		})
	
		document.addEventListener("DOMContentLoaded", function() {
		    var now = new Date();
		    var offset = now.getTimezoneOffset() * 60000; // Convertir la diferencia de zona horaria a milisegundos
		    var currentDate = new Date(now.getTime() - offset).toISOString().split('T')[0];
		    document.getElementById("id-fecha").value = currentDate;
		});
		</script>
		 
	</body>
</html>