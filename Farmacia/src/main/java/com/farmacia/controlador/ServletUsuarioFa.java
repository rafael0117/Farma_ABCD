package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.farmacia.clases.EmpleadoFa;
import com.farmacia.clases.EnlaceFa;
import com.farmacia.dao.MySqlUsuarioFaDAO;

public class ServletUsuarioFa extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletUsuarioFa() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("INICIARSESION"))
			loginDelUsuario(request,response);
		else if(tipo.equals("CERRARSESION"))
			cerrarSesionDelUsuario(request,response);
	}

	private void cerrarSesionDelUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		session.invalidate();
		request.getSession().setAttribute("TERMINADA", "Sesión terminada");
		response.sendRedirect("loginfarma.jsp");
	}

	private void loginDelUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String vLogin,vClave;
		vLogin=request.getParameter("username");
		vClave=request.getParameter("password");
		EmpleadoFa bean=new MySqlUsuarioFaDAO().iniciarSesion(vLogin, vClave);
		if(bean==null) { 
			request.getSession().setAttribute("CERRAR", "Credenciales inválidas");
			response.sendRedirect("loginfarma.jsp");
		}
		else {
			request.getSession().setAttribute("DATOS",  bean.getPaterno()+" "+
														bean.getMaterno()+" "+
														bean.getNombre());
			List<EnlaceFa> lista=new MySqlUsuarioFaDAO().taerMenusDelUsuario(bean.getCodigoTipo());
			request.getSession().setAttribute("MENUS", lista);
			response.sendRedirect("farmacia.jsp");
		}
    }
}
