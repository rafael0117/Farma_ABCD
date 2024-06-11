package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.farmacia.clases.Emision;
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

}
