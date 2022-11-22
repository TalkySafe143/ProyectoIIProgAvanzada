package Usuario;

import Medico.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User extends Usuario {

    private Scanner input;

    public User (String email, String name, String ID, String password) throws Exception {
        super(email, name, ID, password, "user");
        input = new Scanner(System.in);
    }

    public static void agendarCita(Date cita, String especialidad) throws Exception {
        Scanner input = new Scanner(System.in);
        int op = -1;
        while (op == -1) {
            System.out.println("Hola estos son los medicos que estan disponibles: ");

            ArrayList<Medico> medicos = Administrador.cargarMedicos();
            ArrayList<Medico> medicsToRemove = new ArrayList<>();

            for (Medico x: medicos) {
                boolean valid = false;
                ArrayList<Especialidad> especialidades = x.getEspecialidades();
                for (Especialidad y: especialidades) {
                    if (y.getName().equals(especialidad)) {
                        valid = x.isAvailable(cita);
                    }
                }

                if (!valid) {
                    medicsToRemove.add(x);
                } else {
                    System.out.print("[" + x.getID() + "] " + x.getName() + "\n");
                }
            }

            medicos.removeAll(medicsToRemove);

            if (medicos.size() == 0) {
                System.out.println("Lo sentimos, no hay medicos disponibles");
                op = 1;
                continue;
            }

            System.out.println("Ingrese el ID del medico que deseea: ['salir' para salir]");
            String medicID = input.nextLine();

            if (!medicID.equals("salir")) {
                boolean exist = false;
                for (Medico x: medicos) {

                    if (x.getID().equals(medicID)) {
                        System.out.println("Nombre del medico: " + x.getName());
                        System.out.println("ID del medico: " + x.getID());
                        System.out.println("Precio por hora del medico: " + x.getPricePHour());
                        System.out.println("Especialidades: ");
                        for (Especialidad y: x.getEspecialidades()) {
                            System.out.println("Nombre de la especialidad: " + y.getName());
                            System.out.println("Universidad donde fue hecha: " + y.getUniversity());
                        }
                        exist = true;
                        break;
                    }
                }

                if (!exist) {
                    System.out.println("Ingrese un ID valido");
                    op = -1;
                } else {
                    int option = -1;
                    while (option == -1) {
                        System.out.println("Desea agendar la cita con este medico? [Y --> Si / N --> No]");
                        String res = input.nextLine();

                        if (res.equals("Y")) {
                            for (Medico x: medicos) {

                                if (x.getID().equals(medicID)) {
                                    x.addDate(cita, true);
                                    break;
                                }
                            }
                            option = 1;
                        } else if (res.equals("N")) {
                            option = -1;
                        } else {
                            System.out.println("Porfavor, ingrese una opcion valida (Y / N)");
                            option = -1;
                        }
                    }
                }
                op = 1;
            } else {
                op = -1;
            }
        }
    }

}
