/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

import DAO.clienteDAO;
import model.Cliente;
/**
 *
 * @author Alberto343
 */
public class clienteController {
      private static clienteController instancia = null;

    public static clienteController getInstance() {
        instancia = new clienteController();
        return instancia;
    }

    public List<Cliente> getAllClients() {
        clienteDAO cDAO = new clienteDAO();
        return cDAO.getAll();
    }

    public Cliente getClientsById(int codigoOperario) {
        clienteDAO cDAO = new clienteDAO();
        return cDAO.getByID(codigoOperario);
    }

    public boolean insertClients(Cliente a) {
        boolean result = false;
        if (a != null) {
            clienteDAO cDAO = new clienteDAO();
            cDAO.insert(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean editClients(Cliente a) {
        boolean result = false;
        if (a != null) {
            clienteDAO cDAO = new clienteDAO();
            cDAO.edit(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean removeClients(Cliente a) {
        boolean result = false;
        if (a != null) {
            clienteDAO cDAO = new clienteDAO();
            cDAO.remove(a);
        } else {
            result = false;
        }
        return result;
    }
     public boolean searchClientsByID(int codigo) {
        clienteDAO cDAO = new clienteDAO();
        return cDAO.searchByID(codigo);
    }
    
}
