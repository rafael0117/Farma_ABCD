package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.farmacia.dao.MySqlHistorialVentaDAO;


public class ServletHistorialVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletHistorialVenta() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado=new MySqlHistorialVentaDAO().deleteById(Integer.parseInt(cod));
		if(estado==1) 			
			request.getSession().setAttribute("MENSAJE","Venta eliminado correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar Venta");
		
		
		response.sendRedirect("historialVentas.jsp");
	}

}
