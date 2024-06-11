package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.clases.PedidoFa;
import com.farmacia.interfaces.PedidoFaDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlPedidoFaDAO implements PedidoFaDAO{

	@Override
	public int save(PedidoFa bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="insert into tb_pedidos values(null,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getFecha_emision_ped());
			pstm.setString(2, bean.getId_empresa());
			pstm.setInt(3, bean.getCan_ped());
			pstm.setString(4,bean.getDescripcion());
			pstm.setString(5, bean.getCorreo_empresa());
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
	public int update(PedidoFa bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="update tb_pedidos set fecha_emision_ped=?, id_empresa=?, can_ped=?, descripcion=?, correo_empresa=? where cod_ped=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getFecha_emision_ped());		
			pstm.setString(2, bean.getId_empresa());
			pstm.setInt(3, bean.getCan_ped());
			pstm.setString(4, bean.getDescripcion());
			pstm.setString(5, bean.getCorreo_empresa());
			pstm.setInt(6, bean.getCod_ped());
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
			String sql="delete from tb_pedidos where cod_ped=?";
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
	public List<PedidoFa> findAll() {
	    List<PedidoFa> lista = new ArrayList<PedidoFa>();
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn = MySqlConexionFa.getConexion();
	        String sql = "SELECT p.cod_ped, p.fecha_emision_ped, e.nom_empresa, e.correo_empresa, p.can_ped,p.descripcion " +
	                     "FROM tb_pedidos p " +
	                     "JOIN tb_empresa e ON p.id_empresa = e.id_empresa";
	        pstm = cn.prepareStatement(sql);
	        rs = pstm.executeQuery();
	        while (rs.next()) {
	            PedidoFa pf = new PedidoFa();
	            pf.setCod_ped(rs.getInt(1));
	            pf.setFecha_emision_ped(rs.getString(2));
	            pf.setId_empresa(rs.getString(3));
	            pf.setCorreo_empresa(rs.getString(4));
	            pf.setCan_ped(rs.getInt(5));
	            pf.setDescripcion(rs.getString(6));
	            lista.add(pf);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstm != null) pstm.close();
	            if (cn != null) cn.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    return lista;
	}


	@Override
	public PedidoFa findById(int cod) {
		PedidoFa obj=null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="select * from tb_pedidos where cod_ped=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				obj = new PedidoFa();
				obj.setCod_ped(rs.getInt(1));
				obj.setFecha_emision_ped(rs.getString(2));
				obj.setId_empresa(rs.getString(3));								
				obj.setCan_ped(rs.getInt(4));
				obj.setDescripcion(rs.getString(5));
				obj.setCorreo_empresa(rs.getString(6));
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
