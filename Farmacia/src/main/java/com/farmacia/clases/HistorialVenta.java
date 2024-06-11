package com.farmacia.clases;

import lombok.Data;

public @Data class HistorialVenta {
	private int codigo;
	private String nombre, fecha_emision, correo;
	private double monto;
}
