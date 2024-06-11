package com.farmacia.interfaces;

import java.util.List;

import com.farmacia.clases.PedidoFa;

public interface PedidoFaDAO {
	int save(PedidoFa bean);
	int update(PedidoFa bean);
	int deleteById(int cod);
	List<PedidoFa> findAll();
	PedidoFa findById(int cod);
}
