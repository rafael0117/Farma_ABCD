package com.farmacia.interfaces;

import java.util.List;

import com.farmacia.clases.EmpleadoFa;


public interface EmpleadoFaDAO {
	int save(EmpleadoFa bean);
	int update(EmpleadoFa bean);
	int deleteByID(int cod);
	List<EmpleadoFa> findAll();
	List<EmpleadoFa> findAllByTipo(int codTipo);
	EmpleadoFa findByID(int cod);
}
