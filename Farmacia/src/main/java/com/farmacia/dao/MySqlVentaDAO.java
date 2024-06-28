package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.farmacia.interfaces.VentaDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlVentaDAO implements VentaDAO{

	@Override
	public String GenerarSerie() {
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		String numeroserie="";
		String sql="select max(NumeroSerie) from ventas";
		try {
			cn=MySqlConexionFa.getConexion();
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while (rs.next()) {
				numeroserie=rs.getString(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return numeroserie;
	}

}
