package com.farmacia.clases;

import lombok.Data;

public @Data class Proveedores {
	private int codigo;
	private String nombre_empresa, correo,fecha_registro,direccion,telefono,ruc;
}
