package com.farmacia.interfaces;

import com.farmacia.clases.Cliente;

public interface ClienteDAO {
 
	Cliente buscar(String dni);
}
