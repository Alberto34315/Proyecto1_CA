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
import model.Cuenta;


public class cuentaDAO extends Cuenta {
    
    enum queries {
        INSERT("INSERT INTO Cuenta (codigoCuenta, saldo, fechaHoraC, fechaHoraUM) VALUES (NULL,?,?,?)"),
        UPDATE("UPDATE Cuenta SET saldo=?,fechaHoraC=?,fechaHoraUM=? WHERE codigoCuenta=?"),
        DELETE("DELETE FROM Cuenta WHERE codigoCuenta=?"),
        GETBYID("SELECT * FROM Cuenta WHERE codigoCuenta=?"),
        GETALL("SELECT * FROM Cuenta");

        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
    
    Connection conn;
    
    public cuentaDAO(int codigoCuenta, float saldo, Date fechaHoraC, Date fechaHoraUM) {
        super(codigoCuenta, saldo, fechaHoraC, fechaHoraUM);
    }

    public cuentaDAO() {
        super();
    }

    public cuentaDAO(Cuenta d) {
        super(d.getCodigoCuenta(), d.getSaldo(), d.getFechaHoraC(), d.getFechaHoraUM());
    }
    
    //__________________________________________________________________________CRUD
    public void insert(Cuenta a) {
        int result = -1;
        try {
            conn = ConnectionUtils.getConnection();
            if (this.codigoCuenta > 0) {
                edit(a);
            } else {
                PreparedStatement stat = conn.prepareStatement(queries.INSERT.getQ(), Statement.RETURN_GENERATED_KEYS);
                stat.setFloat(1, a.getSaldo());
                stat.setDate(2, a.getFechaHoraC());
                stat.setDate(3,  a.getFechaHoraUM());

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
            stat.setDate(2, (java.sql.Date) a.getFechaHoraC());
            stat.setDate(3, (java.sql.Date) a.getFechaHoraUM());
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
        Date fechaHoraC = rs.getDate("fechaHoraC");
        Date fechaHoraUM = rs.getDate("fechaHoraUM");
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
    
}
