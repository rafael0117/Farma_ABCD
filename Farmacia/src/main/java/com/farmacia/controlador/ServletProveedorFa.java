package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.farmacia.clases.Proveedores;
import com.farmacia.dao.MySqlProveedoresDAO;
import com.google.gson.Gson;


public class ServletProveedorFa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletProveedorFa() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("insertar"))
			insertarEmpleado(request,response);
		else if(tipo.equals("eliminar"))
			eliminarEmpleado(request,response);
		else if(tipo.equals("listar"))
			listarEmpleado(request,response);
		else if(tipo.equals("buscarPorCodigo"))
			buscarPorCodigo(request,response);
	}

	private void buscarPorCodigo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod = request.getParameter("codigo");
		Proveedores pro = new MySqlProveedoresDAO().findByID(Integer.parseInt(cod));
		Gson gson = new Gson();
		String json = gson.toJson(pro);
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

	private void listarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Proveedores> data = new MySqlProveedoresDAO().findAll();
		request.setAttribute("listaempleados", data);
		request.getRequestDispatcher("/inventario.jsp").forward(request, response);
	}

	private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado = new MySqlProveedoresDAO().deleteByCodigo(Integer.parseInt(cod));
		if(estado==1) 			
			request.getSession().setAttribute("MENSAJE","Proveedor eliminado correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar Proveedor");
		
		
		response.sendRedirect("proveedor.jsp");
	}

	private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod, ruc, cor, nom, fech, direcc, tlf;
		cod = request.getParameter("codigo");
		ruc = request.getParameter("ruc");
		cor = request.getParameter("correo");
		nom = request.getParameter("nombre");
		fech = request.getParameter("fecha");
		direcc = request.getParameter("direccion");
		tlf = request.getParameter("telefono");
		Proveedores pr = new Proveedores();
		pr.setRuc(ruc);
		pr.setCorreo(cor);
		pr.setNombre_empresa(nom);
		pr.setFecha_registro(fech);
		pr.setDireccion(direcc);
		pr.setTelefono(tlf);
		if(Integer.parseInt(cod)==0) {
			int estado=new MySqlProveedoresDAO().save(pr);
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","Proveedor registrado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en el registro");
		}
		else {		
			pr.setCodigo(Integer.parseInt(cod));
			int estado=new MySqlProveedoresDAO().update(pr);	
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","Proveedor actualizado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en la actualización");
		}
		response.sendRedirect("proveedor.jsp");
	}

}
