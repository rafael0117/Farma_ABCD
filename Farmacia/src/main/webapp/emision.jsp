<% HttpSession sesion = request.getSession(false);
if (session == null || session.getAttribute("DATOS")==null){response.sendRedirect("loginfarma.jsp");}%>
<jsp:include page="menufarma.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<link href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-bulma/bulma.css" rel="stylesheet">
<style>
	.contenedor {
	    display: flex;
	    justify-content: space-between; 
	    margin-top:100px;
	    }
	
	.contenedor > div {
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
	.codigos{
		display:flex;
		justify-content: space-between; 
	}
</style>
	<script src="https://kit.fontawesome.com/1da5200486.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css" crossorigin="anonymous">
</head>
<body>
	<div class="container-fluid">
		<h2 class="text-center mt-5">Emision de CDP</h2>
		<div class="contenedor" method="post" action="">			
				<div class="col-lg-3">
					<fieldset class="reset">
						<legend class="reset fw-bold">Medicamentos</legend>
							<table id="tableMedicamentos" class="table table-striped" style="width:100%">
						        <thead>
						            <tr>
						                <th>Código</th>
						                <th>Medicamento</th>
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
					</fieldset>								
				</div>
			
			<div class="col-lg-7">
							<h5>Nueva Venta</h5>
						<form id="formBoleta" method="post" action="procesar_boleta.php">
								    <table id="tableOrientacion" class="table table-striped" style="width: 100%">
								        <thead>
								            <tr>
								                <th>Codigo</th>
								                <th>Medicamento</th>
								                <th>Cantidad</th>
								                <th>Precio</th>
								            </tr>
								        </thead>
								        <tbody>
								            
								        </tbody>
								    </table>
								    <div class="form-group">
							    <label for="exampleInputEmail1" class="form-label">Total a pagar</label>
							    <input type="text" class="form-control total" name="total"  id="id-total" readonly>
							  </div>
							  
							<button type="button" class="btn btn-success btn-editar" data-bs-toggle="modal" data-bs-target="#modalEmpleado">Pagar</button>
		                </form>
						
				<!-- Modal -->
				<div class="modal fade" id="modalEmpleado" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h1 class="modal-title fs-5" id="staticBackdropLabel">Boleta</h1>
				      </div>
				      <div class="modal-body">
				        <form id="frmEmision" method="post" action="ServletEmision">
				        <div class="codigos">
				        
						  <div class="form-group">
						    <label for="exampleInputEmail1" class="form-label">Código Venta</label>
						    <input type="text" class="form-control" name="codigo" value="0" id="id-codigo" readonly>
						  </div>
						  <div class="form-group">
						    <label for="exampleInputEmail1" class="form-label">Nombre</label>
						    <input type="text" class="form-control" name="nombre" id="id-nombre">
						  </div>
				        </div>
				        
				       <div class="form-group">
						    <label for="exampleInputEmail1" class="form-label">Fecha de emision</label>
						    <input type="date" class="form-control" name="fechaemi" id="id-fechaemi">
						  </div>
				        	
						<div class="codigos">
						  
				          <div class="form-group">
						    <label for="exampleInputEmail1" class="form-label">Total a Pagar</label>
						    <input type="text" class="form-control total" name="total" id="id-total">
						  </div>
						  <div class="form-group">
						    <label for="exampleInputEmail1" class="form-label">Monto</label>
						    <input type="text" class="form-control" name="monto" id="id-monto">
						  </div>
						</div>
						 
						  <div class="form-group">
						    <label for="exampleInputEmail1" class="form-label">Vuelto</label>
						    <input type="text" class="form-control" name="vuelto" id="id-vuelto" readonly>
						  </div>
						    <div class="form-group">
						    <label for="exampleInputEmail1" class="form-label">Correo</label>
						    <input type="text" class="form-control" name="correo" id="id-correo" >
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
			</div>
		</div>


	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.0/js/bootstrapValidator.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
		
	<c:if test="${sessionScope.MENSAJE!=null}">
			<script>
				toastr.success("${sessionScope.MENSAJE}", "Mensaje", {timeOut:1000})
			</script>
		</c:if>
	<c:remove var="MENSAJE" scope="session"/>

		
<script>
    $(document).ready(function() {
      $('#frmEmision').bootstrapValidator({
        fields: {
          nombre: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el nombre'
              }
            }
          },
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
          total: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el total a pagar'
              },
              numeric: {
                message: 'El total a pagar debe ser numérico'
              }
            }
          },
          monto: {
            validators: {
              notEmpty: {
                message: 'Por favor, ingrese el monto'
              },
              numeric: {
                message: 'El monto debe ser numérico'
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
    </script>
<script>
				cargarDetalle();
				leerJSON();
			
				function leerJSON(){
					$.get("ServletInventarioFaJSON",function(response){
						$.each(response,function(index,item){
							$("#tableMedicamentos tbody").append(`<tr><td>\${item.codigo}</td>
																   <td>\${item.nom_producto}</td>
																   <td>\${item.fecha_caducidad}</td>
																   <td>\${item.precio}</td>
																   <td>\${item.receta}</td>
																   <td data-stock="\${item.stock}">\${item.stock}</td>
																   <td>
												
																   <button type="button" class="btn btn-success">
																   <i class="fa-solid fa-cart-plus"></i>
																</button>
																
												                </td>
																   </tr>`);
						})
						new DataTable("#tableMedicamentos");
					})
				}

				function cargarDetalle(){
					$.get("ServletAlumnoJSON",function(response){
							$.each(response,function(index,item){
								$("#tableAlumnos tbody").append(`<tr><td>\${item.codigo}</td>
												<td>\${item.paterno} \${item.materno} \${item.nombres}</td>
											    <td>
												    <button type="button"
														class="btn btn-warning btn-datos-alumno">
														<i class="fas fa-user-plus"></i>
													</button>
		  				                	    </td>
										   </tr>`);
							})
							new DataTable("#tableAlumnos");
						})
				
						
				} 
		document.addEventListener("DOMContentLoaded", function() {
		    var now = new Date();
		    var offset = now.getTimezoneOffset() * 60000;
		    var currentDate = new Date(now.getTime() - offset).toISOString().split('T')[0];
		    document.getElementById("id-fechaemi").value = currentDate;
		});
		
		$(document).ready(function() {
		    $("#tableMedicamentos").on("click", "button.btn-success", function() {
		        var $row = $(this).closest("tr");
		        var codigo = $row.find("td:eq(0)").text();
		        var medicamento = $row.find("td:eq(1)").text();
		        var precio = $row.find("td:eq(3)").text();
		        $("#tableOrientacion tbody").append(`<tr>
		                                              <td>\${codigo}</td>
		                                              <td>\${medicamento}</td>
		                                              <td><input type="number" class="form-control" value="1"></td>
		                                              <td>\${precio}</td>
		                                              
		                                              <td><button type="button" class="btn btn-danger btn-eliminar-alumno">
		                          		            <i class="fas fa-trash-alt"></i>
		                        		            </button></td>
		                                              
		                                          </tr>`);
		        calcularTotal();
		    });
		    
		    $("#tableOrientacion").on("click", "button.btn-danger", function() {
		        $(this).closest("tr").remove();
		        calcularTotal();
		    });
		    
		    function calcularTotal() {
		        var total = 0;
		        $("#tableOrientacion tbody tr").each(function() {
		            var precio = parseFloat($(this).find("td:eq(3)").text());
		            total += precio;
		        });
		        $(".total").val(total);
		    }
		});
		
		
		document.addEventListener("DOMContentLoaded", function() {
		    var totalInput = document.getElementById("id-total");
		    var montoInput = document.getElementById("id-monto");
		    var vueltoInput = document.getElementById("id-vuelto");
		
		    montoInput.addEventListener("input", function() {
		        var total = parseFloat(totalInput.value);
		        var monto = parseFloat(montoInput.value);
		        
		        if (!isNaN(monto)) {
		            var vuelto = monto - total;
		            vueltoInput.value = vuelto.toFixed(2);
		        } else {
		            vueltoInput.value = "0.00";
		        }
		    });
		});
		
	 </script>
</body>
</html>

