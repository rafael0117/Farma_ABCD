package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.farmacia.clases.InventarioFa;
import com.farmacia.dao.MySqlInventarioFaDAO;
import com.google.gson.Gson;

public class ServletInventarioFa extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletInventarioFa() {
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
		String cod=request.getParameter("codigo");
		InventarioFa emp=new MySqlInventarioFaDAO().findByID(Integer.parseInt(cod));
		Gson gson=new Gson();
		String json=gson.toJson(emp);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);		
	}

	private void listarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<InventarioFa> data=new MySqlInventarioFaDAO().findAll();
		request.setAttribute("listaEmpleados", data);
		request.getRequestDispatcher("/inventario.jsp").forward(request, response);
		
	}

	private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado=new MySqlInventarioFaDAO().deleteByCodigo(Integer.parseInt(cod));
		if(estado==1) 			
			request.getSession().setAttribute("MENSAJE","Medicamento eliminado correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar Medicamento");
		
		
		response.sendRedirect("inventario.jsp");
		
	}

	private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod,medi,pre,fein,feca,re,sto;
		cod = request.getParameter("codigo");
		medi = request.getParameter("medicamento");
		pre = request.getParameter("precio");
		fein = request.getParameter("ingreso");
		feca = request.getParameter("caducado");
		re = request.getParameter("receta");
		sto = request.getParameter("stock");
		InventarioFa em = new InventarioFa();
		em.setNom_producto(medi);
		em.setPrecio(Double.parseDouble(pre));
		em.setFecha_ingreso(fein);
		em.setFecha_caducidad(feca);
		em.setReceta(re);
		em.setStock(Integer.parseInt(sto));
		if(Integer.parseInt(cod)==0) {
			int estado=new MySqlInventarioFaDAO().save(em);
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","Medicamento registrado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en el registro");
		}
		else {		
			em.setCodigo(Integer.parseInt(cod));
			int estado=new MySqlInventarioFaDAO().update(em);	
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","Medicamento actualizado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en la actualización");
		}
		response.sendRedirect("inventario.jsp");
		
	}

}
