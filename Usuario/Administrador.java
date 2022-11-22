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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
        ArrayList<Medico> medicosActuales = Administrador.cargarMedicos();

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
