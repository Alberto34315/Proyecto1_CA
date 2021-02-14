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
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    public static void principal() {
        int opcion = 0;
        mostrarResumen();
        do {
            opcion = Utilities.Menu();
            ControladorPrimerMenu(opcion);
        } while (opcion != 6);

    }

    private static void ControladorPrimerMenu(int op1) {
        cuentaDAO Cuenta = new cuentaDAO();
        Cuenta cue = Cuenta.getByID(11);
        
        switch (op1) {
            case 1:
                OperarioDAO operario1 = new OperarioDAO();
                OperarioDAO operario2 = new OperarioDAO();
                cuentaDAO cDAO = new cuentaDAO();

                clienteDAO clDAO = new clienteDAO();
                List<Operario> ol = operario1.getAll();
                for (Operario op : ol) {
                    if (op.getLogin().equals("Operario1") && op.getPassword().equals("Operario1")) {
                        operario1 = new OperarioDAO(op, new Cuenta(1250, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())), clDAO.getByID(21));
                        operario1.start();
                    }
                }

                for (Operario op : ol) {
                    if (op.getLogin().equals("Operario2") && op.getPassword().equals("Operario2Operario2")) {
                        operario2 = new OperarioDAO(op, new Cuenta(1200, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())), clDAO.getByID(22));
                        operario2.start();
                    }
                }

                break;

            case 2:
                clienteDAO cliente1 = new clienteDAO();
                clienteDAO cliente2 = new clienteDAO();
                
              /*  cliente1.setPriority(7);
                cliente2.setPriority(10);*/
                List<Cliente> Cl = cliente1.getAll();
                for (Cliente cliente : Cl) {
                    if (cliente.getLogin().equals("Usuario1a") && cliente.getPassword().equals("Usuario1a")) {
                        cliente1 = new clienteDAO(cliente, cue, 2);

                        cliente1.start();
                    }
                }

                for (Cliente cliente : Cl) {
                    if (cliente.getLogin().equals("Usuario1b") && cliente.getPassword().equals("Usuario1b")) {
                        cliente2 = new clienteDAO(cliente, cue, 2);
                        cliente2.start();
                    }
                }

                break;

            case 3:
                
                clienteDAO cliente1a = new clienteDAO();
                clienteDAO cliente1b = new clienteDAO();
                clienteDAO cliente1c = new clienteDAO();
                clienteDAO cliente1d = new clienteDAO();
                clienteDAO cliente1e = new clienteDAO();
                

                List<Cliente> Cl1a = cliente1a.getAll();
                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1a") && cliente.getPassword().equals("Usuario1a")) {
                        cliente1a = new clienteDAO(cliente, cue, 3);
                        //System.out.println(cliente1a);
                        cliente1a.start();
                     //   System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1b") && cliente.getPassword().equals("Usuario1b")) {
                        cliente1b = new clienteDAO(cliente, cue, 3);
                     //   System.out.println(cliente1b);
                        cliente1b.start();
                     //   System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1c") && cliente.getPassword().equals("Usuario1c")) {
                        cliente1c = new clienteDAO(cliente, cue, 3);
                      //  System.out.println(cliente1c);
                        cliente1c.start();
                     //   System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1d") && cliente.getPassword().equals("Usuario1d")) {
                        cliente1d = new clienteDAO(cliente, cue, 3);
                     //   System.out.println(cliente1d);
                        cliente1d.start();
                     //   System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                for (Cliente cliente : Cl1a) {
                    if (cliente.getLogin().equals("Usuario1e") && cliente.getPassword().equals("Usuario1e")) {
                        cliente1e = new clienteDAO(cliente, cue, 3);
                    //    System.out.println(cliente1e);
                        cliente1e.start();
                     //   System.out.println("------------------------------------------------------------------------------------------");
                    }
                }
                cuentaDAO c = new cuentaDAO();
                System.out.println("Saldo Actual: " + c.getByID(11).getSaldo());
                break;

            case 4:
                clienteDAO cliente1aW = new clienteDAO();
                clienteDAO cliente1bW = new clienteDAO();
                clienteDAO cliente1cW = new clienteDAO();
                clienteDAO cliente1dW = new clienteDAO();
                clienteDAO cliente1eW = new clienteDAO();                

                cuentaDAO cW = new cuentaDAO();

                List<Cliente> Cl1aW = cliente1aW.getAll();
                for (Cliente cliente : Cl1aW) {
                    if (cliente.getLogin().equals("Usuario1a") && cliente.getPassword().equals("Usuario1a")) {
                        cliente1a = new clienteDAO(cliente, 4, 1);
                        System.out.println(cliente1a);
                        cliente1a.start();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                for (Cliente cliente : Cl1aW) {
                    if (cliente.getLogin().equals("Usuario1b") && cliente.getPassword().equals("Usuario1b")) {
                        cliente1b = new clienteDAO(cliente, 4, 1);
                        System.out.println(cliente1b);
                        cliente1b.start();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                for (Cliente cliente : Cl1aW) {
                    if (cliente.getLogin().equals("Usuario1c") && cliente.getPassword().equals("Usuario1c")) {
                        cliente1c = new clienteDAO(cliente, 4, 1);
                        System.out.println(cliente1c);
                        cliente1c.start();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                for (Cliente cliente : Cl1aW) {
                    if (cliente.getLogin().equals("Usuario1d") && cliente.getPassword().equals("Usuario1d")) {
                        cliente1d = new clienteDAO(cliente, 4, 2);
                        System.out.println(cliente1d);
                        cliente1d.start();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                for (Cliente cliente : Cl1aW) {
                    if (cliente.getLogin().equals("Usuario1e") && cliente.getPassword().equals("Usuario1e")) {
                        cliente1e = new clienteDAO(cliente, 4, 2);
                        System.out.println(cliente1e);
                        cliente1e.start();
                        System.out.println("------------------------------------------------------------------------------------------");
                    }
                }

                System.out.println("Saldo Actual: " + cW.getByID(11).getSaldo());
                break;

            case 5:
                mostrarResumen();
                break;
            case 6:
                Utilities.P("Saliendo de la aplicación.");
                break;

            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    public static void mostrarResumen() {
        cuentaDAO cuentas = new cuentaDAO();
        for (Cuenta cuenta : cuentas.getAll()) {
            System.out.println(cuenta);
            for (Cliente cliente : cuentas.getListClienteCount(cuenta.getCodigoCuenta())) {
                cliente.setCuenta(cuenta);
                System.out.println("--" + cliente);
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        }
    }

}
