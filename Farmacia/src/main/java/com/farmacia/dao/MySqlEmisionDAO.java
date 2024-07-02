package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.clases.DetalleVenta;
import com.farmacia.clases.Emision;
import com.farmacia.clases.EmpleadoFa;
import com.farmacia.interfaces.EmisionDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlEmisionDAO implements EmisionDAO{

	@Override
	public int save(Emision bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="insert into tb_cdp values(null,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNom_compra());
			pstm.setString(2, bean.getFecha_emi_cdp());
			pstm.setDouble(3, bean.getValor_pago());
			pstm.setString(4, bean.getCorreo_comprador());
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm!= null) pstm.close();
				if(cn!= null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public List<DetalleVenta> findAll() {
		List<DetalleVenta>  lista= new ArrayList<DetalleVenta>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="SELECT \r\n"
					+ "    d.cod_detalle,\r\n"
					+ "    v.FechaVentas,\r\n"
					+ "    v.Monto,\r\n"
					+ "    e.nom_emp,\r\n"
					+ "    p.nom_prod,\r\n"
					+ "    d.cantidad\r\n"
					+ "   \r\n"
					+ "FROM \r\n"
					+ "    ventas v\r\n"
					+ "JOIN \r\n"
					+ "    tb_detalleVentas d ON v.IdVentas = d.IdVentas\r\n"
					+ "JOIN \r\n"
					+ "    tb_empleado e ON v.cod_emp = e.cod_emp\r\n"
					+ "JOIN\r\n"
					+ "    tb_productos p ON d.cod_prod = p.cod_prod;";
			pstm=cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
			   DetalleVenta dv = new DetalleVenta();
				dv.setCod_detalle(rs.getInt(1));
				dv.setFecha_venta(rs.getString(2));
				dv.setMonto(rs.getDouble(3));
				dv.setNom_emp(rs.getString(4));
				dv.setNom_prod(rs.getString(5));
				dv.setCantidad(rs.getInt(6));
				
				lista.add(dv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;

	}

	
	
}
