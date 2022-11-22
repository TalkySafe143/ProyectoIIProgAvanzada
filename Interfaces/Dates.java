package Interfaces;

import java.util.Date;
import java.util.Scanner;

import Usuario.User;
import Usuario.Usuario;

/*
 * Andrian ruiz
 * Sebastian galindo
 * Andres Cano
 */


 /*
  * NOMBRE dates
  OBJETIVO interfaz para que el usuario pueda generar una cita con un doctor 
  PARAMETROS Ninguno
  SALIDAS Ninguno
  */

// TODO: Implementar estos metodos
public class Dates {

    public static void agendarCitaFiltros(){
        Scanner scanner = new Scanner(System.in);
        // Pedir los filtros para agendar citas y pasarlos como parametros a User.agendarCita()
        System.out.println("Ingrese el año para su cita");
        int year = scanner.nextInt();
        if (year < 2022) {
            System.out.println("Año invalido, ingrese de nuevo su informacion");
            return;
        }
        System.out.println("Ingrese el mes de su cita (1 a 12)");
        int month = scanner.nextInt();
        if (month < 1 || month > 12) {
            System.out.println("Mes invalido, ingrese de nuevo su informacion");
            return;
        }

        month--;

        System.out.println("Ingrese el dia de su cita (1 a 31)");
        int day = scanner.nextInt();

        if (day < 1 || day > 31) {
            System.out.println("Dia invalido, ingrese de nuevo su informacion");
            return;
        }

        System.out.println("Ingrese la hora de su cita, hora militar (0 a 23)"); //ingresa y  verifica que la hora sea valida
        int hour = scanner.nextInt();

        if (hour < 0 || hour > 23) {
            System.out.println("Hora invalida, ingrese de nuevo su informacion");
            return;
        }

        System.out.println("Ingrese el nombre de la especialidad (Medico Internista --> 1 / Pediatria --> 2)"); // ingresa y verifica la especialidad
        int opt = scanner.nextInt();

        String espec;

        if (opt == 1) {
            espec = "Medicina Interna";
        } else {
            espec = "Pediatria";
        }

        try {
            User.agendarCita(new Date(year-1900, month, day, hour, 0), espec);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
