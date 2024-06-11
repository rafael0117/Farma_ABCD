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
import com.farmacia.dao.MySqlEmpleadoFaDAO;
import com.google.gson.Gson;

public class ServletEmpleadoFa extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletEmpleadoFa() {
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
			buscarEmpleadoPorCodigo(request,response);
		}

	private void buscarEmpleadoPorCodigo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod=request.getParameter("codigo");
		EmpleadoFa emp=new MySqlEmpleadoFaDAO().findByID(Integer.parseInt(cod));
		Gson gson=new Gson();
		String json=gson.toJson(emp);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);
		
	}

	private void listarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<EmpleadoFa> data=new MySqlEmpleadoFaDAO().findAll();
		request.setAttribute("listaEmpleados", data);
		request.getRequestDispatcher("/empleadofarma.jsp").forward(request, response);
	}

	private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado=new MySqlEmpleadoFaDAO().deleteByID(Integer.parseInt(cod));
		if(estado==1) 
			request.getSession().setAttribute("MENSAJE","Empleado eliminado correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar empleado");
		response.sendRedirect("empleadofarma.jsp");
		
	}

	private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod, nom, pat, mat, codTipo;
		cod = request.getParameter("codigo");
		nom = request.getParameter("nombre");
		pat = request.getParameter("paterno");
		mat = request.getParameter("materno");
		codTipo = request.getParameter("tipo");
		EmpleadoFa emp = new EmpleadoFa();
		emp.setNombre(nom);
		emp.setPaterno(pat);
		emp.setMaterno(mat);
		emp.setCodigoTipo(Integer.parseInt(codTipo));
		if(Integer.parseInt(cod)==0) {
			int estado=new MySqlEmpleadoFaDAO().save(emp);
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","Empleado registrado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en el registro");
		}
		else {
			emp.setCodigo(Integer.parseInt(cod));
			int estado=new MySqlEmpleadoFaDAO().update(emp);
			if(estado==1)
				request.getSession().setAttribute("MENSAJE","Empleado actualizado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en la actualización");
		}
		response.sendRedirect("empleadofarma.jsp");
		
	}
}
