package Usuario;

/*
* Formato del archivo de Medicos:
*
* -  --> Empieza un medico
* NOMBRE
* ID
* PRECIOPORHORA
* + --> Empieza la lista de citas
* NUMERO DE CITAS
* CITAS[] -> Objeto Date
* HORARIO DEL MEDICO
* # --> Empieza la lista de especialidades
* NUMERO DE ESPECIALIDADES
* NOMBRE ESPECIALIDAD
* UNIVERSIDAD
* ... Se repite los dos ultimos segun las especialidades
*
* */

/*
 * Andrian ruiz
 * Sebastian galindo
 * Andres Cano
 */

 /*
  * NOMBRE administrador 
  OBJETIVO  interfaz con las funciones del administrador 
  PARAMETROS String email, String name, String ID, String password)
  SALIDAS ninguna
  */


import Medico.*;

import java.io.*;

import java.util.ArrayList;
import java.util.Date;
import Interfaces.*;

public class Administrador extends Usuario {

    public Administrador (String email, String name, String ID, String password) throws Exception {
        super(email, name, ID, password, "admin");
    }

    public static void agregarMedico(Medico nuevoMedico) throws Exception {
        FileWriter file = new FileWriter("./Usuario/medicos.txt", true);
        BufferedWriter buffer = new BufferedWriter(file);
        ArrayList<Date> citas = nuevoMedico.getDates();
        ArrayList<Especialidad> especialidades = nuevoMedico.getEspecialidades();

        buffer.write("-");
        buffer.newLine();
        buffer.write(nuevoMedico.getName());
        buffer.newLine();
        buffer.write(nuevoMedico.getID());
        buffer.newLine();
        buffer.write(String.valueOf(nuevoMedico.getPricePHour()));
        buffer.newLine();
        buffer.write("+");
        buffer.newLine();
        buffer.write(String.valueOf(citas.size()));
        buffer.newLine();
        for (Date x: citas) {
            buffer.write(x.toString());
            buffer.newLine();
        }
        buffer.write(nuevoMedico.getSchedule());
        buffer.newLine();
        buffer.write("#");
        buffer.newLine();
        buffer.write(String.valueOf(especialidades.size()));
        buffer.newLine();
        for (Especialidad x: especialidades) {
            buffer.write(x.getName());
            buffer.newLine();
            buffer.write(x.getUniversity());
            buffer.newLine();
        }

        buffer.close();
        file.close();
    }


    public static void actualizarMedico(Medico deleted) throws Exception {
        ArrayList<Medico> medicosActuales = Clinica.cargarMedicos();

        for (Medico x: medicosActuales) {
            if (x.getID().equals(deleted.getID())) {
                medicosActuales.remove(x);
                break;
            }
        }

        File oldFile = new File("./Usuario/medicos.txt");

        if (!oldFile.delete()) {
            throw new Exception("No se pudo borrar el archivo anterior");
        }

        for (Medico x : medicosActuales) {
            Administrador.agregarMedico(x);
        }
        Administrador.agregarMedico(deleted);
    }
}
