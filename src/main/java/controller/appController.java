/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Cliente;
import model.Connection;
import model.Cuenta;
import model.Operario;



/**
 *
 * @author Alberto343
 */
public class appController {
    public static Connection currentConnection;
    private static appController instancia = null;
    private OperarioController operarioControl = null;
    private clienteController clientControl = null;
    private cuentaController cuentaControl = null;

    public appController() {
        operarioControl=OperarioController.getInstance();
        clientControl=clienteController.getInstance();
        cuentaControl=cuentaController.getInstance();
    }
    
    public appController getInstance(){
        instancia=new appController();
        return instancia;
    }
    
    //FUNCIONES OPERARIO
    
    public List<Operario> getAllOperarios() {
        return operarioControl.getAllOperario();
    }

    public Operario getOperarioById(int id) {
        return operarioControl.getOperarioById(id);
    }

    public boolean insertOperario(Operario a) {
        return operarioControl.insertOpers(a);
    }

    public boolean editOperario(Operario a) {
        return operarioControl.editOpers(a);
    }

    public boolean removeOperario(Operario a) {
        return operarioControl.removeOpers(a);
    }

    public boolean searchOperarioByID(int id){
     return operarioControl.searchOperByID(id);
     }
    
    //FUNCIONES CLIENTE
    
    public List<Cliente> getAllClient() {
        return clientControl.getAllClients();
    }

    public Cliente getClientsById(int id) {
        return clientControl.getClientsById(id);
    }

    public boolean insertClient(Cliente a) {
        return clientControl.insertClients(a);
    }

    public boolean editClient(Cliente a) {
        return clientControl.editClients(a);
    }

    public boolean removeClient(Cliente a) {
        return clientControl.removeClients(a);
    }

    
    public boolean searchClientsByID(int id){
     return clientControl.searchClientsByID(id);
     }
    
    //FUNCIONES CUENTA
    
    public List<Cuenta> getAllCuentas() {
        return cuentaControl.getAllCuentas();
    }

    public Cuenta getCuentasById(int id) {
        return cuentaControl.getCuentaById(id);
    }

    public boolean insertCuenta(Cuenta a) {
        return cuentaControl.insertCuenta(a);
    }

    public boolean editCuenta(Cuenta a) {
        return cuentaControl.editCuenta(a);
    }

    public boolean removeCuenta(Cuenta a) {
        return cuentaControl.removeCuenta(a);
    }

    public boolean searchCuentaByID(int id){
     return cuentaControl.searchByCuentaID(id);
     }
    
}

