package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.clases.EmpleadoFa;
import com.farmacia.interfaces.EmpleadoFaDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlEmpleadoFaDAO implements EmpleadoFaDAO{

	@Override
	public int save(EmpleadoFa bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="insert into tb_empleado values(null,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNombre());
			pstm.setString(2, bean.getPaterno());
			pstm.setString(3, bean.getMaterno());
			pstm.setString(4, bean.getImagen());
			pstm.setInt(5, bean.getCodigoTipo());
		
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
	public int update(EmpleadoFa bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="update tb_empleado set nom_emp=?,pat_emp=?,mat_emp=?,cod_tip_emp=? where cod_emp=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNombre());
			pstm.setString(2, bean.getPaterno());
			pstm.setString(3, bean.getMaterno());
			pstm.setInt(4, bean.getCodigoTipo());
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
	public int deleteByID(int cod) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="delete from tb_empleado where cod_emp=?";
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
	public List<EmpleadoFa> findAll() {
		List<EmpleadoFa> lista = new ArrayList<EmpleadoFa>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="select * from tb_empleado";
			pstm=cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				EmpleadoFa ef = new EmpleadoFa();
				ef.setCodigo(rs.getInt(1));
				ef.setNombre(rs.getString(2));
				ef.setPaterno(rs.getString(3));
				ef.setMaterno(rs.getString(4));
				ef.setImagen(rs.getString(5));
				ef.setCodigoTipo(rs.getInt(6));
				
				lista.add(ef);
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
	public List<EmpleadoFa> findAllByTipo(int codTipo) {
		List<EmpleadoFa> lista = new ArrayList<EmpleadoFa>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="select * from tb_empleado where cod_tip_emp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codTipo);
			rs = pstm.executeQuery();
			while(rs.next()) {
				EmpleadoFa ef = new EmpleadoFa();
				ef.setCodigo(rs.getInt(1));
				ef.setNombre(rs.getString(2));
				ef.setPaterno(rs.getString(3));
				ef.setMaterno(rs.getString(4));
				ef.setCodigoTipo(rs.getInt(5));
				lista.add(ef);
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
	public EmpleadoFa findByID(int cod) {
		EmpleadoFa obj=null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="select * from tb_empleado where cod_emp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				obj = new EmpleadoFa();
				obj.setCodigo(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setPaterno(rs.getString(3));
				obj.setMaterno(rs.getString(4));
				obj.setCodigoTipo(rs.getInt(5));
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
