package UI;

import java.util.Scanner;

public class UIMenu {

    public static void showMenu(){
        int response = 0;
        System.out.println("Bienvenido Usuario!");

        do{
            System.out.println("1. Adminitrador");
            System.out.println("2. Paciente");
            System.out.println("0. Salir");
            System.out.println("Seleccione una opcion: ");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch(response){
                case 1: 
                    response = 0;
                    UIMenuAdmin.showAdminMenu();   
                    break;
                case 2:
                    response = 0;
                    UIMenuPatient.showPatientMenu();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default: 
                    System.out.println("Digite una opcion valida");
            }
        }while(response != 0);

    }
    
}
