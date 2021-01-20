package controller;

import DAO.cuentaDAO;
import java.sql.Timestamp;
import java.util.List;
import model.Cliente;
import model.Cuenta;

public class cuentaController {
    
    private static cuentaController instancia = null;
    
    public static cuentaController getInstance() {
        instancia = new cuentaController();
        return instancia;
    }

    public List<Cuenta> getAllCuentas() {
        cuentaDAO aDAO = new cuentaDAO();
        return aDAO.getAll();
    }

    public Cuenta getCuentaById(int codigoCuenta) {
        cuentaDAO aDAO = new cuentaDAO();
        return aDAO.getByID(codigoCuenta);
    }

    public boolean insertCuenta(Cuenta a) {
        boolean result = false;
        if (a != null) {
            cuentaDAO aDAO = new cuentaDAO();
            aDAO.insert(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean editCuenta(Cuenta a) {
        boolean result = false;
        if (a != null) {
            cuentaDAO aDAO = new cuentaDAO();
            aDAO.edit(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean removeCuenta(Cuenta a) {
        boolean result = false;
        if (a != null) {
            cuentaDAO aDAO = new cuentaDAO();
            aDAO.remove(a);
        } else {
            result = false;
        }
        return result;
    }
    
    /*
    public List<Disco> getRepertorio(int ID) {
        ArtistaDAO aDAO = new ArtistaDAO();
        return aDAO.getListRepertorio(ID);
    }
    */

    public boolean searchByCuentaID(int codigoCuenta) {
        cuentaDAO aDAO = new cuentaDAO();
        return aDAO.searchByID(codigoCuenta);
    }
    
    public Cuenta getCuentaClientById(int codigoCliente) {
        cuentaDAO aDAO = new cuentaDAO();
        return aDAO.getCountByClient(codigoCliente);
    }
    public List<Cliente> getClientsCount(int ID) {
        cuentaDAO aDAO = new cuentaDAO();
        return aDAO.getListClienteCount(ID);
    }
    
    public boolean insertClientC(int a, int c, Timestamp f) {
        boolean result = false;
        if (a != 0 && c!= 0) {
            cuentaDAO aDAO = new cuentaDAO();
            aDAO.insertClienC(a,c,f);
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}
