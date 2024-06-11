package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.farmacia.clases.PedidoFa;
import com.farmacia.dao.MySqlPedidoFaDAO;
import com.google.gson.Gson;

public class ServletPedidoFa extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletPedidoFa() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("insertar"))
			insertarPedido(request,response);
		else if(tipo.equals("eliminar"))
			eliminarPedido(request,response);
		else if(tipo.equals("listar"))
			listarPedido(request,response);
		else if(tipo.equals("buscarPorCodigo"))
			buscarPedidoPorCodigo(request,response);
	}

	private void buscarPedidoPorCodigo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod=request.getParameter("codigo");
		PedidoFa emp=new MySqlPedidoFaDAO().findById(Integer.parseInt(cod));
		Gson gson=new Gson();
		String json=gson.toJson(emp);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

	private void listarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PedidoFa> data=new MySqlPedidoFaDAO().findAll();
		request.setAttribute("listaEmpleados", data);
		request.getRequestDispatcher("/pedidosfarma.jsp").forward(request, response);	
	}

	private void eliminarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado=new MySqlPedidoFaDAO().deleteById(Integer.parseInt(cod));
		if(estado==1) 
			request.getSession().setAttribute("MENSAJE","Pedido eliminado correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar pedido");
		response.sendRedirect("pedidosfarma.jsp");		
	}

	private void insertarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod_ped, fecha_emision_ped, id_empresa,correo, can_ped,descripcion;
		cod_ped = request.getParameter("codigo");
		fecha_emision_ped = request.getParameter("fechaemi");
		correo=request.getParameter("correo");
		id_empresa = request.getParameter("proveedor");
		can_ped = request.getParameter("canped");
		descripcion=request.getParameter("descripcion");
		PedidoFa pe = new PedidoFa();
		pe.setFecha_emision_ped(fecha_emision_ped);
		pe.setId_empresa(id_empresa);
		pe.setCan_ped(Integer.parseInt(can_ped));
		pe.setCorreo_empresa(correo);
		pe.setDescripcion(descripcion);
		if(Integer.parseInt(cod_ped)==0) {
			int estado=new MySqlPedidoFaDAO().save(pe);
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","Pedido registrado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en el registro");
		}
		else {
			pe.setCod_ped(Integer.parseInt(cod_ped));
			int estado=new MySqlPedidoFaDAO().update(pe);
			if(estado==1)
				request.getSession().setAttribute("MENSAJE","Empleado actualizado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en la actualización");
		}
		response.sendRedirect("pedidosfarma.jsp");
		
	}

}
