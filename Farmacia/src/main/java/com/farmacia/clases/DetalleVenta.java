package com.farmacia.clases;

import lombok.Data;

@Data
public class DetalleVenta {
	private int cod_detalle,cantidad;
	private String nom_emp,nom_prod,fecha_venta;
	private double monto;
}
