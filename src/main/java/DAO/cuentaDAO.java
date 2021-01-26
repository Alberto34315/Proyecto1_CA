package DAO;

import Utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Cuenta;


public class cuentaDAO extends Cuenta {
    
    enum queries {
        INSERT("INSERT INTO Cuenta (codigoCuenta, saldo, fechahoracreacion, fechahoraultimamodificacion) VALUES (NULL,?,?,?)"),
        UPDATE("UPDATE Cuenta SET saldo=?,fechahoracreacion=?,fechahoraultimamodificacion=? WHERE codigoCuenta=?"),
        DELETE("DELETE FROM Cuenta WHERE codigoCuenta=?"),
        GETBYID("SELECT * FROM Cuenta WHERE codigoCuenta=?"),
        GETALL("SELECT * FROM Cuenta"),
        INSERTCLIENTC("INSERT INTO cliente_cuenta (codigoCliente,codigoCuenta,fechahoraultimoacceso) VALUES(?,?,?)"),
        GETCLIENTBYIDC("SELECT c.* FROM cliente as c INNER JOIN cliente_cuenta as CC on CC.codigoCliente=c.codigoCliente WHERE CC.codigoCuenta=?"),
        GETCOUNTBYIDCLIENT("SELECT c.* FROM cuenta as c INNER JOIN cliente_cuenta as CC on CC.codigoCuenta=c.codigoCuenta WHERE CC.codigoCliente=?");
        
        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    
    Connection conn;
    
    public cuentaDAO(int codigoCuenta, float saldo, Timestamp fechaHoraC, Timestamp fechaHoraUM) {
        super(codigoCuenta, saldo, fechaHoraC, fechaHoraUM);
    }

    public cuentaDAO() {
        super();
    }

    public cuentaDAO(Cuenta d) {
        super(d.getCodigoCuenta(), d.getSaldo(), d.getFechaHoraC(), d.getFechaHoraUM());
    }
    
    //__________________________________________________________________________CRUD
    
    public void insertClient(Cliente c){
        this.ListClient=new ArrayList<>();
        this.ListClient.add(c);
    }
    public void insert(Cuenta a) {
        int result = -1;
        try {
            conn = ConnectionUtils.getConnection();
            if (this.codigoCuenta > 0) {
                edit(a);
            } else {
                PreparedStatement stat = conn.prepareStatement(queries.INSERT.getQ(), Statement.RETURN_GENERATED_KEYS);
                stat.setFloat(1, a.getSaldo());
                stat.setTimestamp(2, a.getFechaHoraC());
                stat.setTimestamp(3,  a.getFechaHoraUM());

                stat.executeUpdate();
                try ( ResultSet generatedKeys = stat.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);
                    }
                }
                this.codigoCuenta = result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void edit(Cuenta a) {
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement stat = conn.prepareStatement(queries.UPDATE.getQ());
            stat.setFloat(1, a.getSaldo());
            stat.setTimestamp(2, (java.sql.Timestamp) a.getFechaHoraC());
            stat.setTimestamp(3, (java.sql.Timestamp) a.getFechaHoraUM());
             stat.setInt(4, a.getCodigoCuenta());
            stat.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(Cuenta a) {
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(queries.DELETE.getQ());
            ps.setInt(1, a.getCodigoCuenta());

            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se ha borrado correctametne");
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    protected Cuenta convert(ResultSet rs) throws SQLException {
        cuentaDAO ADAO = new cuentaDAO();
        int codigoCuenta = rs.getInt("codigoCuenta");
        Float saldo = rs.getFloat("saldo");
        Timestamp fechaHoraC = rs.getTimestamp("fechahoracreacion");
        Timestamp fechaHoraUM = rs.getTimestamp("fechahoraultimamodificacion");
        Cuenta a = new Cuenta(codigoCuenta, saldo, fechaHoraC, fechaHoraUM);
        return a;
    }
    
    public List<Cuenta> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cuenta> listA = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETALL.getQ());
            rs = stat.executeQuery();
            while (rs.next()) {
                listA.add(convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listA;
    }

    public Cuenta getByID(int codigoCuenta) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cuenta a = new Cuenta();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, codigoCuenta);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convert(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return a;
    }
    
    public boolean searchByID(int codigoCuenta) {
        boolean result = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETBYID.getQ());
            stat.setInt(1, codigoCuenta);
            rs = stat.executeQuery();
            if (rs.next()) {
                Cuenta c = convert(rs);
                if (c.getCodigoCuenta() != -1) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
    
    public boolean insertClienC(int a, int c, Timestamp f) {
        boolean result = false;
        clienteDAO cDAO = new clienteDAO();
        try {
            PreparedStatement stat = null;
            conn = ConnectionUtils.getConnection();
           stat = conn.prepareStatement(queries.INSERTCLIENTC.getQ());
            if (this.getByID(a) != null && cDAO.getByID(c) != null) {
                stat.setInt(1, a);
                stat.setInt(2, c);
                stat.setTimestamp(3, f);
                stat.executeUpdate();
                result=true;
            } else {
                result=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
     public List<Cliente> getListClienteCount(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        clienteDAO cDAO = new clienteDAO();
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETCLIENTBYIDC.getQ());
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                clientes.add(cDAO.convert(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return clientes;
    }
     
     public Cuenta getCountByClient(int codigoCliente) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cuenta a = new Cuenta();
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETCOUNTBYIDCLIENT.getQ());
            stat.setInt(1, codigoCliente);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convert(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return a;
    } 
      
     public boolean searchCountByClient(int codigoCliente) {
        boolean result = false;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            stat = conn.prepareStatement(queries.GETCOUNTBYIDCLIENT.getQ());
            stat.setInt(1, codigoCliente);
            rs = stat.executeQuery();
            if (rs.next()) {
                Cuenta c = convert(rs);
                if (c.getCodigoCuenta() != -1) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
    
    
}
