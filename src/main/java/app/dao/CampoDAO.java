package app.dao;

import app.model.Campo;
import app.zelper.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CampoDAO extends BaseDAO{

    public List<Campo> list() throws DAOExcepcion {
        List<Campo> lista = new ArrayList<Campo>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select * from Campo order by descripcion;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Campo item = new Campo();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setEstado(rs.getInt("estado"));
                item.setTipo(rs.getInt("tipo"));
                item.setCostoHora(rs.getDouble("costoHora"));
  //              item.setLocal(rs.getLocal("Local"));
                lista.add(item);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return lista;
    }

    public Campo get(Campo campo)
        throws DAOExcepcion {
            String query = "select * from campo where id = ?";
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Campo item = new Campo();
            try {
                con = ConexionDB.obtenerConexion();
                stmt = con.prepareStatement(query);
                stmt.setLong(1, campo.getId());

                rs = stmt.executeQuery();

                while (rs.next()) {
                    item.setId(rs.getInt("id"));
                    item.setDescripcion(rs.getString("descripcion"));
                    item.setEstado(rs.getInt("estado"));
                    item.setTipo(rs.getInt("tipo"));
                    item.setCostoHora(rs.getDouble("costoHora"));

                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                this.cerrarResultSet(rs);
                this.cerrarStatement(stmt);
                this.cerrarConexion(con);
            }
            return item;
        }


    public Campo save(Campo campo) throws DAOExcepcion {
        String query = "insert into Campo(descripcion,estado,tipo,costo_hora) values (?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);

            stmt.setString(1, campo.getDescripcion());
            stmt.setInt(1, campo.getEstado());
            stmt.setInt(1, campo.getTipo());
            stmt.setDouble(1, campo.getCostoHora());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo insertar");
            }
            int id = 0;
            query = "select last_insert_id()";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            campo.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return campo;
    }
    
    
    
    

    public Campo update(Campo campo) throws DAOExcepcion {
        String query = "update campo descripcion=?,estado=?,tipo=?,costo_hora=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            
            stmt.setString(1, campo.getDescripcion());
            stmt.setInt(2, campo.getEstado());
            stmt.setInt(3, campo.getTipo());
            stmt.setDouble(4, campo.getCostoHora());
            stmt.setLong(5, campo.getId());
            
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return campo;
    }

    public void delete(Campo campo) throws DAOExcepcion {
        String query = "delete from campo WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, campo.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }


    
}