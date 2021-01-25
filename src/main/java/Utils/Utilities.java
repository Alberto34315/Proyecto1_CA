/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Scanner;

/**
 *
 * @author Alberto343
 */
public class Utilities {

    private static Scanner keyboard = new Scanner(System.in);

    /**
     * Escribe un texto en consola sin retorno de carro
     *
     * @param f texto a imprimir
     */
    public static void p(String f) {
        System.out.print(f);
    }

    /**
     * Escribe un texto en consola con retorno de carro
     *
     * @param f texto a imprimir
     */
    public static void P(String f) {
        Utilities.p(f + "\n");
    }

    /**
     * Lee un entero de teclado
     *
     * @return devuelve el entero leído
     */
    public static int getInt() {
        Integer result = 0;
        boolean valid = false;
        do {
            try {
                result = Integer.parseInt(keyboard.nextLine());
                valid = true;

            } catch (IllegalStateException ex) {
                keyboard = new Scanner(System.in);
                Utilities.P("Error in keyboard. Please, try it again: ");
            } catch (NumberFormatException ex) {
                Utilities.P("Error reading integer type. Please, try it again: ");
            } catch (Exception ex) {
                ex.printStackTrace();
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }

    /**
     * Lee un entero de teclado
     *
     * @param f Mensaje a imprimir al usuario antes de solicitar el entero
     * @return devuelve el entero leído
     */
    public static int getInt(String f) {
        Utilities.p(f + " : ");
        return Utilities.getInt();
    }

    public static int Menu() {
        int opcion = 0;

        P("-----Menu-----");
        P("1) Acceso de dos operarios a la vez para la inserción de nuevas\n"
                + "cuentas bancarias.");
        P("2) Uso del cajero automático de dos clientes asociados a la\n"
                + "misma cuenta bancaria para ingresar dinero. ");
        P("3) Uso del cajero automático de 5 clientes asociados a la misma\n"
                + "cuenta bancaria para sacar dinero. ");
        P("4) Acceso de 5 clientes a la cuenta clave. 3 de los clientes\n"
                + "quieren sacar 500 euros cada uno. 2 de los clientes quieren\n"
                + "ingresar 200 euros cada uno.");
        P("5) Resumen de todas las cuentas del banco.");
        P("6) Salir");
        P("-------------------------------------");
        p("> ");

        try {
            opcion = keyboard.nextInt();
        } catch (Exception e) {
            System.out.println("Introduzca un número entero");
            keyboard = new Scanner(System.in);
        }

        return opcion;
    }

}
