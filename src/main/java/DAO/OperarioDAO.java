/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Operario;

/**
 *
 * @author espin
 */
public class OperarioDAO extends Operario {
    enum queries {
        INSERT("INSERT INTO operarios (ID, Nombre, Apellidos, Login, Password) VALUES (NULL,?,?,?,?)"),
        UPDATE("UPDATE operario SET Nombre=?,Apellidos=?,Login=?,Password=? WHERE codigoOperario=?"),
        DELETE("DELETE FROM operario WHERE codigoOperario=?"),
        DELETEALL("DELETE FROM operario INNER JOIN WHERE codigoOperario=?"),
        GETBYID("SELECT codigoOperario,Nombre,Apellidos,Login,Password FROM operario Where codigoOperario=?"),
        GETALL("SELECT  codigoOperario,Nombre,Apellidos,Login,Password FROM operario");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    Connection conn;

    public OperarioDAO(int codigoOperario, String nombre, String apellidos, String login, String password) {
        super(codigoOperario, nombre, apellidos, login, password);        
    }

    public OperarioDAO() {
        super();
        
    }

    public OperarioDAO(Operario c) {
        super(c.getCodigoOperario(), c.getNombre(), c.getApellidos(), c.getLogin(), c.getPassword());
        
    }

    
    public void insert(Operario a) {
        int result = -1;
        try {
            conn = ConnectionUtils.getConnection();
            if (this.codigoOperario > 0) {
                edit(a);
            } else {
                PreparedStatement stat = conn.prepareStatement(queries.INSERT.getQ(), Statement.RETURN_GENERATED_KEYS);
                stat.setString(1, a.getNombre());
                stat.setString(1, a.getApellidos());
                stat.setString(1, a.getLogin());
                stat.setString(1, a.getPassword());
                
              
                
                stat.executeUpdate();
                try ( ResultSet generatedKeys = stat.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);
                    }
                }
                this.codigoOperario = result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public void edit(Operario a) {
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement stat = conn.prepareStatement(queries.UPDATE.getQ());
            stat.setString(1, a.getNombre());
            stat.setString(1, a.getApellidos());
            stat.setString(1, a.getLogin());
            stat.setString(1, a.getPassword());
            stat.setInt(4, a.getCodigoOperario());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public void remove(Operario a) {
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(queries.DELETE.getQ());
            ps.setInt(1, a.getCodigoOperario());

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se ha borrado correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Metodo que convierte un ResultSet en Cancion
     *
     * @param rs Recibe un ResultSet
     * @return Devuelve una Subscripcion
     * @throws SQLException lanza una SQLException
     */
    protected Operario convert(ResultSet rs) throws SQLException {
        
        int id = rs.getInt("codigoOperario");
        String nombre = rs.getString("Nombre");
        String apellidos = rs.getString("Apellidos");
        String login = rs.getString("Login");
        String password = rs.getString("Password");
        
        
        Operario c = new Operario(id, nombre, apellidos, login, password);
        return c;
    }

    
   
    public List<Operario> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Operario> listS = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETALL.getQ());
            rs = stat.executeQuery();
            while (rs.next()) {
                listS.add(convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listS;
    }

    /**
     * Metodo que devuelve una Cancion por id pasado
     *
     * @param id identificador de cada Cancion
     * @return Devuelve una Cancion
     */
    public Operario getByID(int codigoOperario) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Operario c = new Operario();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, codigoOperario);
            rs = stat.executeQuery();
            if (rs.next()) {
                c = convert(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return c;
    }
    /**
     * Metodo que comprueba si existe el ID en la tabla
     * @param id recibe un entero
     * @return devuelve un boolean, si existe devuelve true y false si no
     */
    public boolean searchByID(int codigoOperario) {
        boolean result = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, codigoOperario);
            rs = stat.executeQuery();
            if (rs.next()) {
                Operario c = convert(rs);
                if (c.getCodigoOperario()!= -1) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
    
    
}
