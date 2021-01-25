/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Cuenta;

/**
 *
 * @author Alberto343
 */
public class clienteDAO extends Cliente {

    enum queries {
        INSERT("INSERT INTO cliente (codigoCliente, dni, login, nombre,apellidos,password,fecha_nac,telefono,email) VALUES (NULL,?,?,?,?,?,?,?,?)"),
        UPDATE("UPDATE cliente SET dni=?,login=?,nombre=?,apellidos=?,password=?,fecha_nac=?,telefono=?,email=? WHERE codigoCliente=?"),
        DELETE("DELETE FROM cliente WHERE codigoCliente=?"),
        GETBYID("SELECT codigoCliente, dni, login, nombre,apellidos,password,fecha_nac,telefono,email FROM cliente Where codigoCliente=?"),
        GETALL("SELECT codigoCliente, dni, login, nombre,apellidos,password,fecha_nac,telefono,email FROM cliente");
   //   GETDISCOLISTBYID("SELECT d.ID, d.Nombre, d.Foto, d.fechap, d.IDArtista FROM disco as d INNER JOIN artista as art on art.ID=d.IDArtista WHERE art.ID=?");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }

    Connection conn;

    public clienteDAO() {
    }

    public clienteDAO(String nombre, String apellidos, String dni, String login, String password, Date fecha_nac, String telefono, String email) {
        super(nombre, apellidos, dni, login, password, fecha_nac, telefono, email);
    }

    public clienteDAO(int codigoCliente, String nombre, String apellidos, String dni, String login, String password, Date fecha_nac, String telefono, String email) {
        super(codigoCliente, nombre, apellidos, dni, login, password, fecha_nac, telefono, email);
    }

    public clienteDAO(Cliente c) {
        super(c.getCodigoCliente(), c.getNombre(), c.getApellidos(), c.getDni(), c.getLogin(), c.getPassword(), c.getFecha_nac(), c.getTelefono(), c.getEmail());
    }

    public clienteDAO(Cliente c, Cuenta cuenta) {
        super(c.getCodigoCliente(), c.getNombre(), c.getApellidos(), c.getDni(), c.getLogin(), c.getPassword(), c.getFecha_nac(), c.getTelefono(), c.getEmail(), cuenta);
    }

    public void insert(Cliente a) {
        int result = -1;
        try {
            conn = ConnectionUtils.getConnection();
            if (this.codigoCliente > 0) {
                edit(a);
            } else {
                PreparedStatement stat = conn.prepareStatement(queries.INSERT.getQ(), Statement.RETURN_GENERATED_KEYS);
                stat.setString(1, a.getDni());
                stat.setString(2, a.getLogin());
                stat.setString(3, a.getNombre());
                stat.setString(4, a.getApellidos());
                stat.setString(5, a.getPassword());
                stat.setDate(6, a.getFecha_nac());
                stat.setString(7, a.getTelefono());
                stat.setString(8, a.getEmail());

                stat.executeUpdate();
                try ( ResultSet generatedKeys = stat.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);
                    }
                }
                this.codigoCliente = result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit(Cliente a) {
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement stat = conn.prepareStatement(queries.UPDATE.getQ());
            stat.setString(1, a.getDni());
            stat.setString(2, a.getLogin());
            stat.setString(3, a.getNombre());
            stat.setString(4, a.getApellidos());
            stat.setString(5, a.getPassword());
            stat.setDate(6, a.getFecha_nac());
            stat.setString(7, a.getTelefono());
            stat.setString(8, a.getEmail());
            stat.setInt(9, a.getCodigoCliente());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void remove(Cliente a) {
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(queries.DELETE.getQ());
            ps.setInt(1, a.getCodigoCliente());

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se ha borrado correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
     
      public Cliente convert(ResultSet rs) throws SQLException {
        int codigoCliente = rs.getInt("codigoCliente");
        String dni = rs.getString("dni");
        String login = rs.getString("login");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String password = rs.getString("password");
        Date fecha_nac = rs.getDate("fecha_nac");
        String telefono = rs.getString("telefono");
         String email = rs.getString("email");
        Cliente a = new Cliente(codigoCliente, dni, login, nombre,apellidos,password,fecha_nac,telefono,email);
        return a;
    }

    public List<Cliente> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cliente> listA = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETALL.getQ());
            rs = stat.executeQuery();
            while (rs.next()) {
                listA.add(convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listA;
    }

    public Cliente getByID(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cliente a = new Cliente();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convert(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return a;
    }
    public boolean searchByID(int id) {
        boolean result = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                Cliente c = convert(rs);
                if (c.getCodigoCliente()!= -1) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
    
    //_______________________________________________________________________________HILO
    public void run(){
        cuentaDAO Cuenta = new cuentaDAO();
        Cuenta cu = Cuenta.getCountByClient(this.codigoCliente);
        System.out.println("Saldo de la cuenta: " + cu.getSaldo());
        cu.setSaldo(cu.getSaldo()+200);
        Cuenta.edit(cu);
        System.out.println("Saldo actualizado: " + cu.getSaldo());
    }
}
