package com.farmacia.clases;

import lombok.Data;

public @Data class InventarioFa {
	private int codigo, stock;
	private String fecha_ingreso,fecha_caducidad,nom_producto,receta;
	private double precio;
}
