package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.clases.HistorialVenta;
import com.farmacia.interfaces.HistorialVentaDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlHistorialVentaDAO implements HistorialVentaDAO{

	@Override
	public int save(HistorialVenta bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="insert into tb_cdp values(null,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNombre());
			pstm.setString(2, bean.getFecha_emision());
			pstm.setDouble(3, bean.getMonto());
			pstm.setString(4, bean.getCorreo());
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
	public int update(HistorialVenta bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="update tb_cdp set nom_cdp=?, fecha_emi_cdp=?, valor_pago=?, correo_comprador=? where cod_venta=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNombre());
			pstm.setString(2, bean.getFecha_emision());
			pstm.setDouble(3, bean.getMonto());
			pstm.setString(4, bean.getCorreo());
			pstm.setInt(5, bean.getCodigo());
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
	public int deleteById(int cod) {
		int salida = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="delete from tb_detalleVentas where cod_detalle=?";
	        pstm = cn.prepareStatement(sql);
	        pstm.setInt(1, cod);
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
	public List<HistorialVenta> findAll() {
		List<HistorialVenta> lista = new ArrayList<HistorialVenta>();
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="select * from tb_cdp";
	        pstm=cn.prepareStatement(sql);
	        rs = pstm.executeQuery();
	        while(rs.next()) {
	            HistorialVenta e = new HistorialVenta();
	            e.setCodigo(rs.getInt(1));
	            e.setNombre(rs.getString(2));
	            e.setFecha_emision(rs.getString(3));
	            e.setMonto(rs.getDouble(4));
	            e.setCorreo(rs.getString(5));
	            lista.add(e);
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

	@Override
	public HistorialVenta findById(int cod) {
		HistorialVenta obj=null;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="select * from tb_cdp where cod_venta=?";
	        pstm=cn.prepareStatement(sql);
	        pstm.setInt(1, cod);
	        rs = pstm.executeQuery();
	        if(rs.next()) {
	            obj = new HistorialVenta();
	            obj.setCodigo(rs.getInt(1));
	            obj.setNombre(rs.getString(2));
	            obj.setFecha_emision(rs.getString(3));
	            obj.setMonto(rs.getDouble(4));
	            obj.setCorreo(rs.getString(5));
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
	    return obj;
	}
	
}
