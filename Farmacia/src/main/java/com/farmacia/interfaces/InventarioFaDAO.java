package com.farmacia.interfaces;

import java.util.List;

import com.farmacia.clases.InventarioFa;

public interface InventarioFaDAO {
	int save(InventarioFa bean);
	int update(InventarioFa bean);
	int deleteByCodigo(int cod);
	List<InventarioFa> findAll();
	InventarioFa findByID(int cod);
	InventarioFa buscar(int id);
	int actualizarStock(int id,int stock);
}
