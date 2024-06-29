package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.clases.EmpleadoFa;
import com.farmacia.clases.EnlaceFa;
import com.farmacia.interfaces.UsuarioFaDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlUsuarioFaDAO implements UsuarioFaDAO{

	@Override
	public EmpleadoFa iniciarSesion(String login, String clave) {
		EmpleadoFa emp = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="select e.cod_emp,e.nom_emp,e.pat_emp,e.mat_emp,u.idrol,e.imagen\r\n"
					+ "from tb_usuario u join tb_empleado e on e.cod_emp=u.cod_emp\r\n"
					+ "where u.cod_usu=? and u.password=?;";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, clave);
			rs = pstm.executeQuery();
			if(rs.next()) {
				emp = new EmpleadoFa();
				emp.setCodigo(rs.getInt(1));
				emp.setNombre(rs.getString(2));
				emp.setPaterno(rs.getString(3));
				emp.setMaterno(rs.getString(4));
				emp.setCodigoTipo(rs.getInt(5));
				emp.setImagen(rs.getString(6));
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
		return emp;
	}

	@Override
	public List<EnlaceFa> taerMenusDelUsuario(int codRol) {
		List<EnlaceFa> lista = new ArrayList<EnlaceFa>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="select e.idenlace,e.descripcion,e.ruta\r\n"
					+ "from tb_rol_enlace re join tb_enlace e\r\n"
					+ "on re.idenlace=e.idenlace where re.idrol=?;";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codRol);
			rs = pstm.executeQuery();
			while(rs.next()) {
				EnlaceFa en = new EnlaceFa();
				en.setCodigo(rs.getInt(1));
				en.setDescripcion(rs.getString(2));
				en.setRuta(rs.getString(3));
				lista.add(en);
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
