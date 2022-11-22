import Interfaces.Clinica;
import Interfaces.Dates;
import Interfaces.Login;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

/*
 * Andrian ruiz
 * Sebastian galindo
 * Andres Cano
 */

 /*
  * NOMBRE main 
  OBJETIVO interfaz principal del programa
  PARAMETROS String[] args
  SALIDAS ninguna
  */


public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido, haga el proceso de autenticacion antes de entrar al sistema: ");

        int option = -1;

        while (option == -1) {

            System.out.println("1. Login\n2. Registrarse\n3. Salir\nPor favor, eliga una opcion: ");

            option = scanner.nextInt();

            if (option > 3 || option < 1) {
                System.out.println("Porfavor, ingrese una opcion valida");
                new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
                //new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();// Limpiar la consola
                option = -1;
                CLS();
            }

            CLS();

            Login UI = null;

            if (option == 1) {
                UI = new Login("login");
                if (UI.getActualUser() == null) {
                    option = -1;
                    continue;
                }

                if (UI.getActualUser().getUserType().equals("admin")) {
                    System.out.println("Bienvenido administrador " + UI.getActualUser().getName() + "\n");
                    int op = -1;

                    while (op == -1) {
                        System.out.println("1. Agregar un medico\n2. Mostrar todos los medicos\n3. Eliminar un medico \n4.Cerrar sesion");
                        op = scanner.nextInt();

                        if (op > 4 || op < 1) {
                            System.out.println("Porfavor, ingrese una opcion valida");
                            op = -1;
                            continue;
                        }

                        if (op == 1) {
                            Clinica.crearMedico();
                            op = -1;
                        } else if (op == 2) {
                            Clinica.mostrarMedicos();
                            op = -1;
                        }else if (op == 3) {
                            Clinica.eliminarMedicos();
                            op = -1;
                        } else {
                            option = -1;
                        }
                    }
                } else if (UI.getActualUser().getUserType().equals("user")) {
                    System.out.println("Bienvenido usuario: " + UI.getActualUser().getName() + "\n");

                    int op = -1;

                    while (op == -1) {
                        System.out.println("1. Agendar una cita\n2. Cerrar sesion");
                        op = scanner.nextInt();

                        if (op > 2 || op < 1) {
                            System.out.println("Porfavor, ingrese una opcion valida");
                            op = -1;
                            continue;
                        }

                        if (op == 1) {
                            Dates.agendarCitaFiltros();
                            op = -1;
                        } else {
                            option = -1;
                        }
                    }
                }
            } else if (option == 2) {
                UI = new Login("register");

                System.out.println("Se ha creado su usuario correctamente");

                option = -1;
            }
        }

    }

    public static void CLS() {
        for(int i = 0; i < 1000; i++)
        {
            System.out.println("\b");
        }
    }
}