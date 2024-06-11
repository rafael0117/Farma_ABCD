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
import com.farmacia.clases.PedidoFa;
import com.farmacia.dao.MySqlEmpleadoFaDAO;
import com.farmacia.dao.MySqlPedidoFaDAO;
import com.google.gson.Gson;


public class ServletPedidoFaJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ServletPedidoFaJSON() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PedidoFa> lista=new MySqlPedidoFaDAO().findAll();
		Gson gson=new Gson();
		String json=gson.toJson(lista);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

}
