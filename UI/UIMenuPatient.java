package UI;
import java.util.Scanner;

public class UIMenuPatient{

    public static void showPatientMenu(){
        int response = 0;
        System.out.println("1. Agendar cita");
        System.out.println("0. Salir");

        do{
            System.out.println("Seleccione una opcion: ");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch(response){
                case 1:
                    agendarCita();
                    break;
                case 0:
                    System.out.println("Sesion cerrada");
                    UIMenu.showMenu();
                    break;
                default:
                    System.out.println("Digite una opcion valida");
            }
        }while(response != 0);
    }

    public static void agendarCita(){
        System.out.println("<<<<<<<< AGENDAR CITA <<<<<<<<");
        System.out.println("Necesita un medico (1)internista o (2)pediatra? (Digite un numero): ");
        System.out.println("Digite la fecha para la cita: ");
        System.out.println("Digite la hora: ");
    }
    
}
