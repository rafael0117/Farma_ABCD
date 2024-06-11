package com.farmacia.interfaces;

import java.util.List;

import com.farmacia.clases.Proveedores;

public interface ProveedoresDAO {
	int save(Proveedores bean);
	int update(Proveedores bean);
	int deleteByCodigo(int cod);
	List<Proveedores> findAll();
	Proveedores findByID(int cod);
}
