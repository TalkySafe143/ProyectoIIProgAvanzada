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

import Medico.*;

import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Administrador extends Usuario {

    public Administrador (String email, String name, String ID, String password) throws Exception {
        super(email, name, ID, password, "admin");
    }

    public static void agregarMedico(Medico nuevoMedico) throws Exception {
        FileWriter file = new FileWriter("./src/Usuario/medicos.txt", true);
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
        buffer.write(citas.size());
        buffer.newLine();
        for (Date x: citas) {
            buffer.write(x.toString());
            buffer.newLine();
        }
        buffer.write(nuevoMedico.getSchedule());
        buffer.newLine();
        buffer.write("#");
        buffer.newLine();
        buffer.write(especialidades.size());
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


    public void eliminarMedico(Medico deleted) throws Exception {
        ArrayList<Medico> medicosActuales = Administrador.cargarMedicos();

        if (!medicosActuales.remove(deleted)) {
            throw new Exception("El medico que ha querido eliminar, no existe");
        }

        File oldFile = new File("./src/Usuario/medicos.txt");

        if (!oldFile.delete()) {
            throw new Exception("No se pudo borrar el archivo anterior");
        }

        for (Medico x : medicosActuales) {
            this.agregarMedico(x);
        }
    }

    public static ArrayList<Medico> cargarMedicos() throws Exception {
        ArrayList<Medico> medicos = new ArrayList<>();

        FileReader file = null;
        BufferedReader buffer = null;

        try {
            file = new FileReader("./src/Usuario/medicos.txt");
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
            throw e;
        }

        return medicos;
    }

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
            res = new MedicoPediatra(data.get(0), data.get(1), Float.parseFloat(data.get(2)));
        } else if (line.equals("Medicina Interna")) {
            res = new MedicoInternista(data.get(0), data.get(1), Float.parseFloat(data.get(2)));
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

        for (int i = 2; i <= datesSize+2; i++) {
            res.addDate(DateFormat.getDateInstance().parse(data.get(i)));
        }

        return res;
    }

}
