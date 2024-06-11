package com.farmacia.interfaces;

import java.util.List;

import com.farmacia.clases.EmpleadoFa;
import com.farmacia.clases.EnlaceFa;

public interface UsuarioFaDAO {
	EmpleadoFa iniciarSesion(String login, String clave);
	List <EnlaceFa> taerMenusDelUsuario(int codRol);
}
