/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.OperarioDAO;
import java.util.List;
import model.Operario;

/**
 *
 * @author espin
 */
public class OperarioController {
    private static OperarioController instancia = null;

    public static OperarioController getInstance() {
        instancia = new OperarioController();
        return instancia;
    }

    public List<Operario> getAllSongs() {
        OperarioDAO cDAO = new OperarioDAO();
        return cDAO.getAll();
    }

    public Operario getSongsById(int codigoOperario) {
        OperarioDAO cDAO = new OperarioDAO();
        return cDAO.getByID(codigoOperario);
    }

    public boolean insertOpers(Operario a) {
        boolean result = false;
        if (a != null) {
            OperarioDAO cDAO = new OperarioDAO();
            cDAO.insert(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean editOpers(Operario a) {
        boolean result = false;
        if (a != null) {
            OperarioDAO cDAO = new OperarioDAO();
            cDAO.edit(a);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean removeOpers(Operario a) {
        boolean result = false;
        if (a != null) {
            OperarioDAO cDAO = new OperarioDAO();
            cDAO.remove(a);
        } else {
            result = false;
        }
        return result;
    }
     public boolean searchOperByID(int codigoOperario) {
        OperarioDAO cDAO = new OperarioDAO();
        return cDAO.searchByID(codigoOperario);
    }
    
}
