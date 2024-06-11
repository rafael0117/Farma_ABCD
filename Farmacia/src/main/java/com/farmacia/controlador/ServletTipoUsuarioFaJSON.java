package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.farmacia.clases.EmpleadoFa;
import com.farmacia.clases.TipoUsuarioFa;
import com.farmacia.dao.MySqlEmpleadoFaDAO;
import com.farmacia.dao.MySqlTipoUsuarioFaDAO;
import com.google.gson.Gson;

public class ServletTipoUsuarioFaJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletTipoUsuarioFaJSON() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TipoUsuarioFa> lista=new MySqlTipoUsuarioFaDAO().findAll();
		Gson gson=new Gson();
		String json=gson.toJson(lista);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

}
