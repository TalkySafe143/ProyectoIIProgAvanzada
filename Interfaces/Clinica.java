package Interfaces;

import Medico.*;
import Usuario.Administrador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

/*
 * Andrian ruiz
 * Sebastian galindo
 * Andres Cano
 */

 /*
  * NOMBRE clinica 
  OBJETIVO crear los medicos
  PARAMETROS ninguno
  SALIDAS ninguna
  */


// TODO: Implementar estos metodos
public class Clinica {

    private LinkedList<Medico> Medicos;

    public static void crearMedico() {
        Scanner scanner = new Scanner(System.in);
        // Pedir los datos del medico, instanciar una clase Medico y llamar a Administrador.agregarMedico(Medico)
        System.out.println("Porfavor, ingrese los datos del medico:");
        System.out.println("Ingrese el nombre: ");
        String name = scanner.nextLine();
        System.out.println("Ingrese el ID: ");
        String ID = scanner.nextLine();
        System.out.println("Ingrese el valor a cobrar por hora: ");
        float getPricePHour = scanner.nextFloat();
        System.out.println("Cuantas especialidades tiene? ");
        int nSpecial = scanner.nextInt();

        int option;
        do {

            System.out.println("Digite 1 -> Pediatria y 2 --> Medicina interna");
            option = scanner.nextInt();

            if (option != 1 && option != 2) {
                System.out.println("Digite una opcion correcta");
            }

        } while (option != 1 && option != 2);

        ArrayList<Especialidad> especialidades= new ArrayList<>();
        Medico nuevoMedico = null;
        System.out.println("En que universidad fue hecha la especializacion: ");
        scanner.nextLine();
        String universidad = scanner.nextLine();

        try {
            if (option == 1) {
                especialidades.add(new Especialidad("Pediatria", universidad));
            } else {
                especialidades.add(new Especialidad("Medicina Interna", universidad));
            }

            for (int i = 0; i < nSpecial-1; i++) {
                System.out.println("Ingrese el nombre de la especialidad " + (i+2));
                String specName = scanner.nextLine();
                System.out.println("Escriba el nombre de la universidad");
                String uniName = scanner.nextLine();

                especialidades.add(new Especialidad(specName, uniName));
            }

            if (option == 1) {
                nuevoMedico = new MedicoPediatra(name, ID, getPricePHour, especialidades);
            } else {
                nuevoMedico = new MedicoInternista(name, ID, getPricePHour, especialidades);
            }

            Administrador.agregarMedico(nuevoMedico);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*
  * NOMBRE mostrar medicos 
  OBJETIVO mostrar los medicos existentes
  PARAMETROS ninguno
  SALIDAS ninguna
  */



    }

    public static void mostrarMedicos() {
        // Llamar a Clinica.cargarMedicos() y mostrarlos en pantalla
        try {
            ArrayList<Medico> medicos = Clinica.cargarMedicos();
            System.out.println("Los medicos existentes son: ");
            for (Medico x: medicos) {
                System.out.println("[Nombre] " + x.getName() + " [ID] " + x.getID() + " [Valor x Hora] " + x.getPricePHour() + " [Horario] " + x.getSchedule() + " [Especializacion] " + x.getEspecialidades().get(0).getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarMedicos() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Medico deleted = null;

        System.out.println("Porfavor, ingrese el ID del medico que quiere eliminar");
        String ID = scanner.nextLine();

        ArrayList<Medico> medicosActuales = Clinica.cargarMedicos();

        for (Medico x: medicosActuales) {
            if (x.getID().equals(ID)) {
                deleted = x;
                break;
            }
        }

        if (deleted == null) {
            throw new Exception("El medico no existe");
        }

        if (!medicosActuales.remove(deleted)) {
            throw new Exception("El medico que ha querido eliminar, no existe");
        }

        File oldFile = new File("./Usuario/medicos.txt");
        if (!oldFile.delete()) {
            throw new Exception("No se pudo borrar el archivo anterior");
        }

        for (Medico x : medicosActuales) {
            Administrador.agregarMedico(x);
        }
    }

    public static ArrayList<Medico> cargarMedicos() throws Exception {
        ArrayList<Medico> medicos = new ArrayList<>();

        FileReader file = null;
        BufferedReader buffer = null;

        try {
            file = new FileReader("./Usuario/medicos.txt");
            buffer = new BufferedReader(file);

            String line;

            while ((line = buffer.readLine()) != null) {
                if (line.equals("-")) {
                    medicos.add(cargarMedico(buffer));
                } else {
                    throw new Exception("Quiza el pointer no esta avanzando");
                }
            }

            buffer.close();
            file.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return medicos;
    }
       /* 
    * NOMBRE cargar medico 
    OBJETIVO  llenar la lista de médicos con la información que está almacenada en el archivo.
    PARAMETROS BufferedReader buffer
    SALIDAS ninguna
    */

    private static Medico cargarMedico(BufferedReader buffer) throws Exception {
        Medico res = null;

        ArrayList<String> data = new ArrayList<>();

        String line;

        data.add(buffer.readLine()); // Nombre
        data.add(buffer.readLine()); // ID
        data.add(buffer.readLine()); // PrecioPorHora

        line = buffer.readLine();

        if (!line.equals("+")) {
            throw new Exception("Ups, quiza vamos mal en las citas");
        }

        int datesSize = Integer.parseInt(buffer.readLine());

        // Leer data desde el index 2 hasta datesSize+2
        for (int i = 0; i < datesSize; i++) {
            data.add(buffer.readLine()); // Citas
        }
        data.add(buffer.readLine()); // Horario del medico

        line = buffer.readLine();

        if (!line.equals("#")) {
            throw new Exception("Ups, quiza vamos mal en las especializaciones");
        }

        int especSize = Integer.parseInt(buffer.readLine())-1; // Menos uno porque vamos a leer el primero manualmente

        Especialidad it = null;

        line = buffer.readLine();

        if (line.equals("Pediatria")) {
            res = new MedicoPediatra(data.get(0), data.get(1), Float.parseFloat(data.get(2)), new ArrayList<Especialidad>());
        } else if (line.equals("Medicina Interna")) {
            res = new MedicoInternista(data.get(0), data.get(1), Float.parseFloat(data.get(2)), new ArrayList<Especialidad>());
        }

        if (res == null) {
            throw new Exception("La primera especialidad no es la principal");
        }

        it = new Especialidad(line, buffer.readLine());

        res.addEspecialidad(it);

        for (int i = 0; i < especSize; i++) {
            line = buffer.readLine();
            it = new Especialidad(line, buffer.readLine());
            res.addEspecialidad(it);
        }

        if (datesSize != 0) {
            for (int i = 3; i < datesSize+3; i++) {
                DateFormat  dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                res.addDate(dateFormat.parse(data.get(i)), false);
            }
        }

        return res;
    }

    
}
