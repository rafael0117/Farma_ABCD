package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.farmacia.clases.Venta;
import com.farmacia.interfaces.VentaDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlVentaDAO implements VentaDAO{

	@Override
	public String IdVentas() {
		String idventas="";
		String sql="select max(IdVentas) from ventas";
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexionFa.getConexion();
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				idventas=rs.getString(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return idventas;
	}


	@Override
	public int guardarVenta(Venta ve) {
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    int resultado = 0;

	    String sql = "INSERT INTO ventas (IdCliente, cod_emp, FechaVentas, Monto, Estado) VALUES (?, ?, ?, ?, ?)";

	    try {
	        cn = MySqlConexionFa.getConexion();
	        pstm = cn.prepareStatement(sql);
	        pstm.setInt(1, ve.getIdCliente());
	        pstm.setInt(2, ve.getIdEmpledo()); // Asegúrate que getIdEmpledo() sea el método correcto para obtener el código de empleado
	        pstm.setString(3, ve.getFechaVenta());
	        pstm.setDouble(4, ve.getMonto());
	        pstm.setString(5, ve.getEstado());
	        
	        resultado = pstm.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstm != null) pstm.close();
	            if (cn != null) cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return resultado;
	}

@Override
public int guardarDetalleVenta(Venta venta) {
    Connection cn = null;
    PreparedStatement pstm = null;
    int resultado = 0;

    String sql = "INSERT INTO tb_detalleVentas (IdVentas, cod_prod, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

    try {
        cn = MySqlConexionFa.getConexion();
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1, venta.getIdVentas()); // Asegúrate que getIdVentas() está definido correctamente en la clase Venta
        pstm.setInt(2, venta.getIdProducto());
        pstm.setInt(3, venta.getCantidad());
        pstm.setDouble(4, venta.getPrecio());

        resultado = pstm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (pstm != null) pstm.close();
            if (cn != null) cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return resultado;
}


}
