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
	    Cliente cli = (Cliente) request.getSession().getAttribute("c");
	    InventarioFa in = (InventarioFa) request.getSession().getAttribute("producto");

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
	            int item = lista.size() + 1;
	            int cod = Integer.parseInt(request.getParameter("codigoproducto"));
	            String descripcion = request.getParameter("nomproducto");
	            double precio = Double.parseDouble(request.getParameter("precio"));
	            int cant = Integer.parseInt(request.getParameter("cant"));
	            double subtotal = precio * cant;

	            Venta v = new Venta();
	            v.setItem(item);
	            v.setIdProducto(cod);
	            v.setDescripcion(descripcion);
	            v.setPrecio(precio);
	            v.setCantidad(cant);
	            v.setSubtotal(subtotal);
	            lista.add(v);

	            request.getSession().setAttribute("lista", lista);
	            break;

	        case "GenerarVenta":
	            // Actualizar el stock en la base de datos
	            for (Venta venta : lista) {
	                int cantidad = venta.getCantidad();
	                int idproducto = venta.getIdProducto();
	                InventarioFa inv = invdao.findByID(idproducto);
	                int nuevoStock = inv.getStock() - cantidad;
	                invdao.actualizarStock(idproducto, nuevoStock);
	            }

	            // Calcular el total a pagar
	            double totalPagar = 0.0;
	            for (Venta venta : lista) {
	                totalPagar += venta.getSubtotal();
	            }

	            // Guardar la venta en la base de datos
	            Venta venta = new Venta();
	            venta.setIdCliente(cli.getIdCliente());
	            venta.setIdEmpledo(1001); // Asumiendo que 1001 es el ID del empleado correcto
	            venta.setFechaVenta("2019-06-14");
	            venta.setMonto(totalPagar);
	            venta.setEstado("1");
	            vdao.guardarVenta(venta);

	            int idVenta = Integer.parseInt(vdao.IdVentas());
	            for (Venta itemVenta : lista) {
	                Venta detalleVenta = new Venta();
	                detalleVenta.setIdVentas(idVenta);
	                detalleVenta.setIdProducto(itemVenta.getIdProducto());
	                detalleVenta.setCantidad(itemVenta.getCantidad());
	                detalleVenta.setPrecio(itemVenta.getPrecio());
	                vdao.guardarDetalleVenta(detalleVenta);
	            }

	            // Limpiar la lista y los atributos de sesión después de generar la venta
	            lista.clear();
	            cli = null;
	            in = null;
	            totalPagar = 0.0;

	            request.getSession().removeAttribute("c");
	            request.getSession().removeAttribute("producto");
	            request.getSession().removeAttribute("lista");

	            try {
	                Thread.sleep(3000); // 4000 milisegundos = 4 segundos
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            // Redireccionar a emision.jsp con el parámetro ventaGenerada=true
	       
	            request.setAttribute("totalpagar", totalPagar); // Asegurar que totalpagar esté limpio
	            request.getRequestDispatcher("emision.jsp").forward(request, response);
	            return;
	    }

	    // Calcular el total a pagar después del switch
	    double totalPagar = 0.0;
	    for (Venta venta : lista) {
	        totalPagar += venta.getSubtotal();
	    }

	    // Establecer los atributos de sesión y de solicitud para la página emision.jsp
	    request.getSession().setAttribute("c", cli);
	    request.getSession().setAttribute("producto", in);
	    request.getSession().setAttribute("lista", lista);
	    request.setAttribute("c", cli);
	    request.setAttribute("producto", in);
	    request.setAttribute("lista", lista);
	    request.setAttribute("totalpagar", totalPagar);
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
