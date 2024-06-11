package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.farmacia.clases.Emision;
import com.farmacia.dao.MySqlEmisionDAO;


public class ServletEmision extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletEmision() {
        super();
            }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod,fecEmi, nom, tot,corre;
		cod = request.getParameter("codigo");
		fecEmi = request.getParameter("fechaemi");
		nom = request.getParameter("nombre");
		tot = request.getParameter("total");
		corre = request.getParameter("correo");
		Emision emp = new Emision();
		emp.setFecha_emi_cdp(fecEmi);
		emp.setNom_compra(nom);
		emp.setValor_pago(Double.parseDouble(tot));
		emp.setCorreo_comprador(corre);
		if(Integer.parseInt(cod)==0) {
			int estado=new MySqlEmisionDAO().save(emp);
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","CDP registrado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en el registro");
		}
		response.sendRedirect("emision.jsp");	}

}
