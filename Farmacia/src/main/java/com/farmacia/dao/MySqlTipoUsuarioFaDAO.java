package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.clases.TipoUsuarioFa;
import com.farmacia.interfaces.TipoUsuarioFaDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlTipoUsuarioFaDAO implements TipoUsuarioFaDAO{

	@Override
	public List<TipoUsuarioFa> findAll() {
		List<TipoUsuarioFa> lista = new ArrayList<TipoUsuarioFa>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexionFa.getConexion();
			String sql="select *from tb_tipo_usuario";
			pstm=cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				TipoUsuarioFa e = new TipoUsuarioFa();
				e.setCodigoTipo(rs.getInt(1));
				e.setNombreTipo(rs.getString(2));
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
	
}
