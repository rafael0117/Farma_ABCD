package com.farmacia.clases;

import lombok.Data;

@Data
public class Venta {
	private int IdVentas,IdCliente,IdProducto,IdEmpledo,item,cantidad;
	private String descripcion,fechaVenta,estado;
	private double monto,subtotal,precio;
	

	
	

	
	

}
