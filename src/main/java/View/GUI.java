/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.clienteDAO;
import Utils.Utilities;
import controller.appController;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Alberto343
 */
public class GUI {

    private static appController controlador = appController.getInstance();

    public static void principal() {
        int opcion = 0;
        do {
            opcion = Utilities.Menu();
            ControladorPrimerMenu(opcion);
        } while (opcion != 6);

    }

    private static void ControladorPrimerMenu(int op1) {
        
        switch (op1) {
            case 1:
                    
                break;

            case 2:
                
                clienteDAO cliente1 = new clienteDAO();
                clienteDAO cliente2 = new clienteDAO();
                
                List<Cliente> Cl = cliente1.getAll();
                for (Cliente cliente : Cl) {
                    if (cliente.getLogin().equals("Usuario1a") && cliente.getPassword().equals("Usuario1a")){
                        cliente1 = new clienteDAO(cliente);
                        cliente1.start();
                    }
                }
                
                List<Cliente> Cl2 = cliente2.getAll();
                for (Cliente cliente : Cl2) {
                    if (cliente.getLogin().equals("Usuario1b") && cliente.getPassword().equals("Usuario1b")){
                        cliente2 = new clienteDAO(cliente);
                        cliente2.start();
                    }
                }
        
                break;

            case 3:

                break;

            case 4:

                break;

            case 5:

                break;
            case 6:
                Utilities.P("Saliendo de la aplicación.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

}
