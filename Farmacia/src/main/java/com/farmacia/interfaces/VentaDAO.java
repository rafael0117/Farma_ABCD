package com.farmacia.interfaces;

import com.farmacia.clases.Venta;

public interface VentaDAO {

	String IdVentas();
	int guardarVenta(Venta ve);

	int guardarDetalleVenta(Venta venta);
}
