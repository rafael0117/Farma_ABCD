package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.clases.Proveedores;
import com.farmacia.interfaces.ProveedoresDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlProveedoresDAO implements ProveedoresDAO{

	@Override
	public int save(Proveedores bean) {
		int salida = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn =  MySqlConexionFa.getConexion();
	        String sql = "insert into tb_empresa values\r\n"
	        		+ "(null, ?, ?, ?, ?, ?, ?)";
	        pstm = cn.prepareStatement(sql);
	        pstm.setString(1, bean.getRuc());
	        pstm.setString(2, bean.getCorreo());
	        pstm.setString(3, bean.getNombre_empresa());
	        pstm.setString(4, bean.getFecha_registro());
	        pstm.setString(5, bean.getDireccion());
	        pstm.setString(6, bean.getTelefono());
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
	public int update(Proveedores bean) {
		int salida = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="update tb_empresa set ruc_empresa=?,correo_empresa=?,nom_empresa=?,fecha_registro=?,direcc_empresa=?,tlf_empresa=? where id_empresa=?";
	        pstm = cn.prepareStatement(sql);
	        pstm.setString(1, bean.getRuc());
	        pstm.setString(2, bean.getCorreo());
	        pstm.setString(3, bean.getNombre_empresa());
	        pstm.setString(4, bean.getFecha_registro());
	        pstm.setString(5, bean.getDireccion());
	        pstm.setString(6, bean.getTelefono());
	        pstm.setInt(7, bean.getCodigo());
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
	public int deleteByCodigo(int cod) {
		int salida = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="delete from tb_empresa where id_empresa=?";
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
	public List<Proveedores> findAll() {
		List<Proveedores> lista = new ArrayList<Proveedores>();
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="select * from tb_empresa";
	        pstm=cn.prepareStatement(sql);
	        rs = pstm.executeQuery();
	        while(rs.next()) {
	            Proveedores p = new Proveedores();
	            p.setCodigo(rs.getInt(1));
	            p.setRuc(rs.getString(2));
	            p.setCorreo(rs.getString(3));
	            p.setNombre_empresa(rs.getString(4));
	            p.setFecha_registro(rs.getString(5));
	            p.setDireccion(rs.getString(6));
	            p.setTelefono(rs.getString(7));
	            lista.add(p);
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
	public Proveedores findByID(int cod) {
		Proveedores obj=null;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="select * from tb_empresa where id_empresa=?";
	        pstm=cn.prepareStatement(sql);
	        pstm.setInt(1, cod);
	        rs = pstm.executeQuery();
	        if(rs.next()) {
	            obj = new Proveedores();
	            obj.setCodigo(rs.getInt(1));
	            obj.setRuc(rs.getString(2));
	            obj.setCorreo(rs.getString(3));
	            obj.setNombre_empresa(rs.getString(4));
	            obj.setFecha_registro(rs.getString(5));
	            obj.setDireccion(rs.getString(6));
	            obj.setTelefono(rs.getString(7));;
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
