package com.farmacia.interfaces;

import java.util.List;

import com.farmacia.clases.HistorialVenta;


public interface HistorialVentaDAO {
	int save(HistorialVenta bean);
	int update(HistorialVenta bean);
	int deleteById(int cod);
	List<HistorialVenta> findAll();
	HistorialVenta findById(int cod);
}
