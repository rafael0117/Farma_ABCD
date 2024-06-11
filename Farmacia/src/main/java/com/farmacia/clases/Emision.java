package com.farmacia.clases;

import lombok.Data;

public @Data class Emision {
	private int cod_venta;
	private String nom_compra,fecha_emi_cdp,correo_comprador; 
	private double valor_pago;
}
