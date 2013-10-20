
package app.dao;

import app.model.Alquiler;
import app.zelper.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAO extends BaseDAO{

    public List<Alquiler> list() throws DAOExcepcion, SQLException {
       List<Alquiler> lista = new ArrayList<Alquiler>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            String query = "select * from solicitud_alquiler order;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Alquiler item = new Alquiler();
                item.setId(rs.getInt("id"));
                item.setHoraInicio(rs.getString("horaInicio"));
                item.setHoraFin(rs.getString("horaFin"));
                item.setFecha(rs.getDate("fecha"));
                item.setServicios(rs.getString("servicios"));
                item.setEstado(rs.getInt("estado"));

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

    public Alquiler get(Alquiler alquiler) {
            String query = "select * from solicitud_alquiler where id = ?";
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Alquiler item = new Alquiler();
            try {
                con = ConexionDB.obtenerConexion();
                stmt = con.prepareStatement(query);
                stmt.setLong(1, alquiler.getId());

                rs = stmt.executeQuery();

                while (rs.next()) {
                    item.setId(rs.getInt("id"));
                    item.setHoraInicio(rs.getString("horaInicio"));
                    item.setHoraFin(rs.getString("horaFin"));
                    item.setFecha(rs.getDate("fecha"));
                    item.setServicios(rs.getString("servicios"));
                    item.setEstado(rs.getInt("estado"));
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

    public Alquiler save(Alquiler alquiler) throws DAOExcepcion {
        String query = "insert into solicitud_alquiler(hora_inicio,hora_fin,fecha,servicios,estado) values (?,?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);

            stmt.setString(1, alquiler.getHoraInicio());
            stmt.setString(2, alquiler.getHoraFin());
     //       stmt.setDate(3, alquiler.getFecha());
            stmt.setString(4, alquiler.getServicios());
            stmt.setInt(5, alquiler.getEstado());
            
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
            alquiler.setId(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return alquiler;
    }

    public Alquiler update(Alquiler alquiler) throws DAOExcepcion {
        String query = "update solicitud_alquiler hora_inicio=?,hora_fin=?,fecha=?,servicios=?,estado=? where id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, alquiler.getHoraInicio());
            stmt.setString(2, alquiler.getHoraFin());
     //       stmt.setInt(3, alquiler.getFecha());
            stmt.setString(4, alquiler.getServicios());
            stmt.setInt(5, alquiler.getEstado());
            stmt.setLong(6, alquiler.getId());

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
        return alquiler;
    }

    public void delete(Alquiler alquiler) throws DAOExcepcion {
        String query = "delete from solicitud_alquiler WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionDB.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, alquiler.getId());
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
