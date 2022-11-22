package Interfaces;

import Medico.*;
import Usuario.Administrador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

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
                System.out.println("Ingrese el nombre de la especialidad " + i+2);
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


    }

    public static void mostrarMedicos() {
        // Llamar a Administrador.cargarMedicos() y mostrarlos en pantalla
        try {
            ArrayList<Medico> medicos = Administrador.cargarMedicos();
            System.out.println("Los medicos existentes son: ");
            for (Medico x: medicos) {
                System.out.println("[Nombre] " + x.getName() + "[ID] " + x.getID() + " [Valor x Hora] " + x.getPricePHour() + " [Horario] " + x.getSchedule() + " [Especializacion] " + x.getEspecialidades().get(0).getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarMedicos() {
        // No es necesario:)
    }
}
