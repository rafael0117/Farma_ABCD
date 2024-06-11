package com.farmacia.clases;

import lombok.Data;

public @Data class PedidoFa {
	private int cod_ped, can_ped;
	private String fecha_emision_ped,descripcion, id_empresa,correo_empresa;
}
