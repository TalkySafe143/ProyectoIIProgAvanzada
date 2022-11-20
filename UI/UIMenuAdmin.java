package UI;

import Medico.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class UIMenuAdmin {

    public static void showAdminMenu(){
        int response = 0;
        System.out.println("1. Crear medico");
        System.out.println("0. Salir");

        do{
            System.out.println("Seleccione una opcion: ");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch(response){
                case 1:
                    crearMedico();
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
    public static void crearMedico() {
        Scanner sc = new Scanner(System.in);
        String name, ID, universityName, specialtyName;
        float pricePHour;
        Especialidad especialidad;
        int response = 0;
        int responseRepeat = 0;
        LinkedList<Medico> medicos = new LinkedList<>();
        do{
            System.out.println("<<<<<<<< CREAR MEDICO <<<<<<<<");
            System.out.println("Digite el nombre: ");
            name = sc.nextLine();
            System.out.println("Digite el numero de identificacion: ");
            ID = sc.nextLine();
            System.out.println("Digite el valor por HORA de consulta: ");
            pricePHour = sc.nextFloat();
            System.out.println("Es medico (1)pediatra o (2)internista?(Digite un numero): ");
            do{
                response = Integer.valueOf(sc.nextLine());
                switch(response){
                    case 1:
                        System.out.println("Digite el nombre de su especialidad: ");
                        specialtyName = sc.nextLine();
                        System.out.println("Digite la universidad donde se especializo: ");
                        universityName = sc.nextLine();
                        medicos.add(new MedicoPediatra()); //Meter todos los datos de la clase que haga andres
                        break;
                    case 2:
                        System.out.println("Digite el nombre de su especialidad: ");
                        specialtyName = sc.nextLine();
                        System.out.println("Digite la universidad donde se especializo: ");
                        universityName = sc.nextLine();
                        medicos.add(new MedicoInternista()); //Meter todos los datos de la clase que haga andres
                        break;
                    default:
                        System.out.println("Digite un numero valido");
                        break;
                }
            }while(response != 1 || response != 2);

            System.out.println("Desea crear otro medico?(1. Si, 2. No): ");
            
            do{
                responseRepeat = Integer.valueOf(sc.nextLine());
                if(responseRepeat == 1 || responseRepeat == 2){
                    break;
                }

                System.out.println("Digite un valor valido");
            }while(responseRepeat != 1 || responseRepeat != 2);

        }while(responseRepeat == 1);
    
        //Escribirlo en el archivo
        FileWriter fw = null;
        try{
            fw = new FileWriter("Medicos.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            for (Medico medico : medicos) {
                bw.write(medico.getID() + "," + medico.getName() + "," + medico.getPricePHour() + "," + medico.getSchedule() + "," + medico.getSpecialty().getName() + "," + medico.getSpecialty().getUniversity());
                bw.newLine();
            }
            bw.close();
        }catch (Exception e){
            System.out.println("Error al escribir el archivo!");
        }finally{
            try{
                if (fw!=null)
                    fw.close();
            }catch (IOException e){
                System.out.println("Error al cerrar el archivo!");
            }
        }
    }

    public static LinkedList<Medico> cargarMedicos() {
        LinkedList<Medico> medicos = new LinkedList<>();
        FileReader fr = null;

        try{
            fr = new FileReader("Medicos.txt");
            BufferedReader br = new BufferedReader(fr);
            String Linea;
            String []partes;
            
            while ((Linea=br.readLine())!=null){
                partes = Linea.split(",");
                if(partes[3].equals("8-5")){
                    medicos.add(new MedicoPediatra());//Llenar con todos los datos de la clase que haga andres
                }
                if(partes[3].equals("6-8")){
                    medicos.add(new MedicoInternista());//Llenar con todos los datos de la clase que haga andres
                }
            }
            br.close();
        }catch (Exception e){
            System.out.println("Error al leer el archivo!");
        }finally{
            try{
                if (fr!=null)
                    fr.close();
            }catch (IOException e){
                System.out.println("Error al cerrar el archivo!");
            }
        }
        return medicos;
    }
}
