package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.clases.InventarioFa;
import com.farmacia.interfaces.InventarioFaDAO;
import com.farmacia.utils.MySqlConexionFa;

public class MySqlInventarioFaDAO implements InventarioFaDAO{
	int r;
	@Override
	public int save(InventarioFa bean) {
		int salida = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="insert into tb_productos values(null,?,?,?,?,?,?)";
	        pstm = cn.prepareStatement(sql);
	        pstm.setString(1, bean.getNom_producto());
	        pstm.setDouble(2, bean.getPrecio());
	        pstm.setString(3, bean.getFecha_ingreso());
	        pstm.setString(4, bean.getFecha_caducidad());
	        pstm.setString(5, bean.getReceta());
	        pstm.setInt(6, bean.getStock());
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
	public int update(InventarioFa bean) {
		int salida = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="update tb_productos set nom_prod=?,pre_ven=?,fecha_ingreso=?,fecha_caducidad=?,receta=?, stock=? where cod_prod=?";
	        pstm = cn.prepareStatement(sql);
	        pstm.setString(1, bean.getNom_producto());
	        pstm.setDouble(2, bean.getPrecio());
	        pstm.setString(3, bean.getFecha_ingreso());
	        pstm.setString(4, bean.getFecha_caducidad());
	        pstm.setString(5, bean.getReceta());
	        pstm.setInt(6, bean.getStock());
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
	        String sql="delete from tb_productos where cod_prod=?";
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
	public List<InventarioFa> findAll() {
		List<InventarioFa> lista = new ArrayList<InventarioFa>();
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="select * from tb_productos";
	        pstm=cn.prepareStatement(sql);
	        rs = pstm.executeQuery();
	        while(rs.next()) {
	            InventarioFa e = new InventarioFa();
	            e.setCodigo(rs.getInt(1));
	            e.setNom_producto(rs.getString(2));
	            e.setPrecio(rs.getDouble(3));
	            e.setFecha_ingreso(rs.getString(4));
	            e.setFecha_caducidad(rs.getString(5));
	            e.setReceta(rs.getString(6));
	            e.setStock(rs.getInt(7));
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
	public InventarioFa findByID(int cod) {
		InventarioFa obj=null;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn=MySqlConexionFa.getConexion();
	        String sql="select * from tb_productos where cod_prod=?";
	        pstm=cn.prepareStatement(sql);
	        pstm.setInt(1, cod);
	        rs = pstm.executeQuery();
	        if(rs.next()) {
	            obj = new InventarioFa();
	            obj.setCodigo(rs.getInt(1));
	            obj.setNom_producto(rs.getString(2));
	            obj.setPrecio(rs.getDouble(3));
	            obj.setFecha_ingreso(rs.getString(4));
	            obj.setFecha_caducidad(rs.getString(5));
	            obj.setReceta(rs.getString(6));
	            obj.setStock(rs.getInt(7));
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
   
	@Override
	public InventarioFa buscar(int id) {
		InventarioFa in=new InventarioFa();
		String sql="select*from tb_productos where cod_prod"+id;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexionFa.getConexion();
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				  in.setCodigo(rs.getInt(1));
		          in.setNom_producto(rs.getString(2));
		          in.setPrecio(rs.getDouble(3));
		          in.setFecha_ingreso(rs.getString(4));
		          in.setFecha_caducidad(rs.getString(5));
		          in.setReceta(rs.getString(6));
		          in.setStock(rs.getInt(7));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return in;
	}

	@Override
	public int actualizarStock(int id, int stock) {
	    String sql = "UPDATE tb_productos SET stock = ? WHERE cod_prod = ?";
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    int salida=-1;
	    try {
	        cn = MySqlConexionFa.getConexion();
	        pstm = cn.prepareStatement(sql);
	        pstm.setInt(1, stock);
	        pstm.setInt(2, id);
	        salida=pstm.executeUpdate();
	        
	        // Aquí podrías configurar propiedades en el objeto inventario si es necesario
	        
	    }  catch (Exception e) {
	        e.printStackTrace();
	        // Manejo de otras excepciones
	    } finally {
	        try {
	            if (pstm != null) pstm.close();
	            if (cn != null) cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Manejo de la excepción al cerrar la conexión
	        }
	    }
	    
	    return salida;
	}


}
