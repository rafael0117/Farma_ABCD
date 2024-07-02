package com.farmacia.interfaces;

import java.util.List;

import com.farmacia.clases.DetalleVenta;
import com.farmacia.clases.Emision;


public interface EmisionDAO {
	int save(Emision bean);
	List<DetalleVenta> findAll();
}
