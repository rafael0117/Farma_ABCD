package com.farmacia.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import com.farmacia.clases.DetalleVenta;
import com.farmacia.dao.MySqlEmisionDAO;
import com.farmacia.dao.MySqlHistorialVentaDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;


public class ServletHistorialVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletHistorialVenta() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("reporte"))
			reporte(request,response);
		else if(tipo.equals("eliminar"))
			eliminarVenta(request,response);
	
	}
	private void reporte(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    response.setContentType("application/pdf");
	    OutputStream out = response.getOutputStream();
	    
	    try {
	        String id = request.getParameter("reporte");
	        
	        if (id != null && !id.isEmpty()) {
	            DetalleVenta r = new MySqlEmisionDAO().reporte(Integer.parseInt(id));
	            
	            if (r != null) {
	                Document document = new Document(PageSize.A4.rotate());
	                PdfWriter writer = PdfWriter.getInstance(document, out);
	                
	                // Listener para la adición de la imagen en el evento "onEndPage"
	                writer.setPageEvent(new PdfPageEventHelper() {
	                    public void onEndPage(PdfWriter writer, Document document) {
	                        try {
	                            // Cargar la imagen desde recursos (ajusta la ruta según tu proyecto)
	                            String imagePath = "file:///C:/Users/USER%20GW/git/Farma_ABCD/Farmacia/src/main/webapp/img/image.png";
	                            Image img = Image.getInstance(imagePath);
	                            
	                            // Escalar la imagen para que sea más grande
	                            img.scaleToFit(105, 105); // Ajusta los valores según tu necesidad
	                            
	                            // Posicionar la imagen en la esquina superior derecha
	                            img.setAbsolutePosition(document.getPageSize().getWidth() - 150, document.getPageSize().getHeight() - 100);
	                            
	                            // Agregar la imagen al documento
	                            document.add(img);
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                        }
	                    }
	                });
	                
	                document.open();

	                // Título centrado
	                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD);
	                Paragraph title = new Paragraph("FARMA ABCD", titleFont);
	                title.setAlignment(Element.ALIGN_CENTER);
	                document.add(title);

	                // Línea horizontal
	                LineSeparator line = new LineSeparator();
	                document.add(new Chunk(line));

	                // Título de la boleta
	                Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
	                Paragraph subtitle = new Paragraph("BOLETA DE VENTA", subtitleFont);
	                subtitle.setAlignment(Element.ALIGN_CENTER);
	                document.add(subtitle);

	                document.add(Chunk.NEWLINE);

	                // Información del cliente
	                Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
	                Paragraph clientInfo = new Paragraph();
	                clientInfo.add(new Phrase("Cliente: " + r.getNom_cli() + "\n", infoFont));
	                clientInfo.add(new Phrase("Dirección: " + r.getDireccion() + "\n", infoFont));
	                clientInfo.add(new Phrase("DNI: " + r.getDni_cli() + "\n", infoFont));
	                document.add(clientInfo);

	                document.add(Chunk.NEWLINE);

	                // Tabla con detalles de la venta
	                PdfPTable table = new PdfPTable(6);
	                table.setWidthPercentage(100);

	                // Encabezados de la tabla
	                String[] headers = {"Nro Venta", "Fecha Venta", "Monto", "Vendedor", "Producto", "Cantidad"};
	                for (String header : headers) {
	                    PdfPCell cell = new PdfPCell(new Phrase(header, infoFont));
	                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	                    table.addCell(cell);
	                }

	                // Datos de la venta
	                table.addCell(String.valueOf(r.getCod_detalle()));
	                table.addCell(r.getFecha_venta());
	                table.addCell(String.valueOf(r.getMonto()));
	                table.addCell(r.getNom_emp());
	                table.addCell(r.getNom_prod());
	                table.addCell(String.valueOf(r.getCantidad()));

	                document.add(table);

	                document.add(Chunk.NEWLINE);

	                // Mensaje de agradecimiento
	                Paragraph thankYou = new Paragraph("¡Gracias por su compra! Esperamos que vuelva pronto.", infoFont);
	                thankYou.setAlignment(Element.ALIGN_CENTER);
	                document.add(thankYou);

	                document.close();
	            } else {
	                System.out.println("No se encontró la venta con el ID especificado.");
	            }
	        } else {
	            System.out.println("El parámetro ID es nulo o está vacío.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void eliminarVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
