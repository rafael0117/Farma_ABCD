package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.farmacia.clases.Cliente;
import com.farmacia.interfaces.ClienteDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlClienteDAO implements ClienteDAO{

	@Override
	public Cliente buscar(String dni) {
		Cliente c=new Cliente();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql="SELECT * FROM cliente WHERE Dni="+dni;
		try {
			cn=MySqlConexionFa.getConexion();
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				c.setIdCliente(rs.getInt(1));
				c.setDni(rs.getString(2));
				c.setNombres(rs.getString(3));
				c.setDireccion(rs.getString(4));
				c.setEstado(rs.getString(5));
				
			}
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
		return c;
	}

}
