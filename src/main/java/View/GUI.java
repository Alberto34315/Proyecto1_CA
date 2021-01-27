/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.OperarioDAO;
import DAO.clienteDAO;
import DAO.cuentaDAO;
import Utils.Utilities;
import controller.appController;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Cuenta;
import model.Operario;

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
                    OperarioDAO operario1=new OperarioDAO();
                    OperarioDAO operario2=new OperarioDAO();
                    operario1.setPriority(5);
                    operario2.setPriority(10);
                    List<Operario> ol = operario1.getAll();
                for (Operario op : ol) {
                    if (op.getLogin().equals("Operario1") && op.getPassword().equals("Operario1")){
                        operario1 = new OperarioDAO(op);
                        operario1.run();
                    }
                }
                
                
                for (Operario op : ol) {
                    if (op.getLogin().equals("Operario2") && op.getPassword().equals("Operario2Operario2")){
                        operario2 = new OperarioDAO(op);
                        operario2.run();
                    }
                }
             cuentaDAO cDAO=new cuentaDAO();
             cDAO.remove(cDAO.getCountByClient(21));
             cDAO.remove(cDAO.getCountByClient(22));
                break;

            case 2:
                
                clienteDAO cliente1 = new clienteDAO();
                clienteDAO cliente2 = new clienteDAO();
                cliente1.setPriority(5);
                cliente2.setPriority(10);
                List<Cliente> Cl = cliente1.getAll();
                for (Cliente cliente : Cl) {
                    if (cliente.getLogin().equals("Usuario1a") && cliente.getPassword().equals("Usuario1a")){
                        cliente1 = new clienteDAO(cliente,2);
                        cliente1.run();
                    }
                }
                
                
                for (Cliente cliente : Cl) {
                    if (cliente.getLogin().equals("Usuario1b") && cliente.getPassword().equals("Usuario1b")){
                        cliente2 = new clienteDAO(cliente,2);
                        cliente2.run();
                    }
                }
        
                break;

            case 3:
               
                clienteDAO cliente1a = new clienteDAO();
                clienteDAO cliente1b = new clienteDAO();
                clienteDAO cliente1c = new clienteDAO();
                clienteDAO cliente1d = new clienteDAO();
                clienteDAO cliente1e = new clienteDAO();
                cliente1a.setPriority(5);
                cliente1b.setPriority(6);
                cliente1c.setPriority(7);
                cliente1d.setPriority(8);
                cliente1e.setPriority(10);
                
                List<Cliente> Cl1a = cliente1a.getAll();
                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1a") && cliente.getPassword().equals("Usuario1a")){
                        cliente1a = new clienteDAO(cliente,3);
                        System.out.println(cliente1a);
                        cliente1a.run();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }
                
                
                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1b") && cliente.getPassword().equals("Usuario1b")){
                        cliente1b = new clienteDAO(cliente,3);
                        System.out.println(cliente1b);
                        cliente1b.run();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }
                
                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1c") && cliente.getPassword().equals("Usuario1c")){
                        cliente1c = new clienteDAO(cliente,3);
                        System.out.println(cliente1c);
                        cliente1c.run();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }
                
                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1d") && cliente.getPassword().equals("Usuario1d")){
                        cliente1d = new clienteDAO(cliente,3);
                        System.out.println(cliente1d);
                        cliente1d.run();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }
                
                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1e") && cliente.getPassword().equals("Usuario1e")){
                        cliente1e = new clienteDAO(cliente,3);
                        System.out.println(cliente1e);
                        cliente1e.run();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }
                cuentaDAO c=new cuentaDAO();
                System.out.println("Saldo Actual: "+c.getByID(11).getSaldo());
                break;

            case 4:
                 
                break;

            case 5:
                    cuentaDAO cuentas=new cuentaDAO();
                    for (Cuenta cuenta : cuentas.getAll()) {
                        System.out.println(cuenta);
                        for (Cliente cliente : cuentas.getListClienteCount(cuenta.getCodigoCuenta())) {
                            cliente.setCuenta(cuenta);
                            System.out.println("--"+cliente);
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
                }
                break;
            case 6:
                Utilities.P("Saliendo de la aplicación.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

}
