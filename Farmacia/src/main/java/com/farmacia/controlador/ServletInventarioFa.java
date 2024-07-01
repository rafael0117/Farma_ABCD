package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.farmacia.clases.Cliente;

import com.farmacia.clases.InventarioFa;
import com.farmacia.clases.Venta;
import com.farmacia.dao.MySqlClienteDAO;
import com.farmacia.dao.MySqlInventarioFaDAO;
import com.farmacia.dao.MySqlVentaDAO;
import com.google.gson.Gson;

public class ServletInventarioFa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletInventarioFa() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("acciones");
		if (tipo.equals("insertar"))
			insertarEmpleado(request, response);
		else if (tipo.equals("eliminar"))
			eliminarEmpleado(request, response);
		else if (tipo.equals("listar"))
			listarEmpleado(request, response);
		else if (tipo.equals("buscarPorCodigo"))
			buscarPorCodigo(request, response);
		else if (tipo.equals("BuscarCliente"))
			buscarClientePorCodigo(request, response);	
	}
	

    MySqlVentaDAO vdao = new MySqlVentaDAO();

	Venta v = new Venta();
	private void buscarClientePorCodigo(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String accion = request.getParameter("accion");
	    MySqlClienteDAO cldao = new MySqlClienteDAO();
	    MySqlInventarioFaDAO invdao = new MySqlInventarioFaDAO();
	    Cliente cli = (Cliente) request.getSession().getAttribute("c"); // Obtener cliente de la sesión
	    InventarioFa in = (InventarioFa) request.getSession().getAttribute("producto"); // Obtener producto de la sesión

	    int item = 0;
	    int cod;
	    String descripcion;
	    double precio;
	    int cant;
	    double subtotal;
	    double totalPagar = 0;

	    @SuppressWarnings("unchecked")
	    List<Venta> lista = (List<Venta>) request.getSession().getAttribute("lista");

	    if (lista == null) {
	        lista = new ArrayList<>();
	    }

	    switch (accion) {
	        case "Buscar Cliente":
	            String dni = request.getParameter("codigocliente");
	            System.out.println("BuscarCliente action triggered. DNI: " + dni);
	            cli = cldao.buscar(dni);
	            if (cli != null) {
	                System.out.println("Cliente encontrado: " + cli.getNombres());
	            } else {
	                System.out.println("Cliente no encontrado.");
	            }
	            break;

	        case "Buscar Producto":
	            int id = Integer.parseInt(request.getParameter("codigoproducto"));
	            in = invdao.findByID(id);
	            break;

	        case "Agregar":
	            totalPagar = 0.0;
	            item = lista.size() + 1; // Incrementar item basado en el tamaño de la lista
	            cod = Integer.parseInt(request.getParameter("codigoproducto"));
	            descripcion = request.getParameter("nomproducto");
	            precio = Double.parseDouble(request.getParameter("precio"));
	            cant = Integer.parseInt(request.getParameter("cant"));
	            subtotal = precio * cant;

	            Venta v = new Venta();
	            v.setItem(item);
	            v.setIdProducto(cod);
	            v.setDescripcion(descripcion);
	            v.setPrecio(precio);
	            v.setCantidad(cant);
	            v.setSubtotal(subtotal);
	            lista.add(v);

	            for (Venta venta : lista) {
	                totalPagar += venta.getSubtotal();
	            }
	            break;

	        case "GenerarVenta":
	        	 for (int i = 0; i < lista.size(); i++) {
	                 int cantidad = lista.get(i).getCantidad();
	                 int idproducto = lista.get(i).getIdProducto();
	                 MySqlInventarioFaDAO aO = new MySqlInventarioFaDAO();
	                 
	                 // Obtener el inventario del producto
	                 InventarioFa inv = aO.findByID(idproducto);
	                 if (inv != null) {
	                     int nuevoStock = inv.getStock() - cantidad;
	                     aO.actualizarStock(idproducto, nuevoStock);
	                 } else {
	                     System.out.println("Producto no encontrado: " + idproducto);
	                 }
	             }
	        	
	            Venta venta = new Venta();
	            venta.setIdCliente(cli.getIdCliente()); // Asume que cli.getIdCliente() devuelve el ID del cliente
	            venta.setIdEmpledo(1001); // Asume que 2 es el ID del empleado correcto
	            venta.setFechaVenta("2019-06-14");
	            venta.setMonto(totalPagar); // Asume que totalPagar es el monto total a registrar
	            venta.setEstado("1");
	            vdao.guardarVenta(venta);
	            // Llamar al método para guardar la venta en la base de datos
	           
	            
	            int idv = Integer.parseInt(vdao.IdVentas());
	            for(int i=0;i<lista.size();i++) {
	            	v=new Venta();
	            	v.setIdVentas(idv);
	            	v.setIdProducto(lista.get(i).getIdProducto());
	            	v.setCantidad(lista.get(i).getCantidad());
	            	v.setPrecio(lista.get(i).getPrecio());
	            	
	            	vdao.guardarDetalleVenta(v);
	            }
	                
	            
	            break;
	    }

	    // Almacenar los atributos en la sesión para mantener el estado entre solicitudes
	    request.getSession().setAttribute("c", cli);
	    request.getSession().setAttribute("producto", in);
	    request.getSession().setAttribute("lista", lista);
	    request.setAttribute("totalpagar", totalPagar);
	    request.setAttribute("c", cli);
	    request.setAttribute("producto", in);
	    request.setAttribute("lista", lista);
	    request.getRequestDispatcher("emision.jsp").forward(request, response);
	}

	private void buscarPorCodigo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod = request.getParameter("codigo");
		InventarioFa emp = new MySqlInventarioFaDAO().findByID(Integer.parseInt(cod));
		Gson gson = new Gson();
		String json = gson.toJson(emp);
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

	private void listarEmpleado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<InventarioFa> data = new MySqlInventarioFaDAO().findAll();
		request.setAttribute("listaEmpleados", data);
		request.getRequestDispatcher("/inventario.jsp").forward(request, response);

	}

	private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado = new MySqlInventarioFaDAO().deleteByCodigo(Integer.parseInt(cod));
		if (estado == 1)
			request.getSession().setAttribute("MENSAJE", "Medicamento eliminado correctamente");
		else
			request.getSession().setAttribute("MENSAJE", "Error al eliminar Medicamento");

		response.sendRedirect("inventario.jsp");

	}

	private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod, medi, pre, fein, feca, re, sto;
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
		if (Integer.parseInt(cod) == 0) {
			int estado = new MySqlInventarioFaDAO().save(em);
			if (estado == 1)
				request.getSession().setAttribute("MENSAJE", "Medicamento registrado correctamente");
			else
				request.getSession().setAttribute("MENSAJE", "Error en el registro");
		} else {
			em.setCodigo(Integer.parseInt(cod));
			int estado = new MySqlInventarioFaDAO().update(em);
			if (estado == 1)
				request.getSession().setAttribute("MENSAJE", "Medicamento actualizado correctamente");
			else
				request.getSession().setAttribute("MENSAJE", "Error en la actualización");
		}
		response.sendRedirect("inventario.jsp");

	}

}
