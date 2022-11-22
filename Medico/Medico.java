package Medico;

import java.util.ArrayList;
import java.util.Date;

import Usuario.Administrador;

// Formato de Horarios -> DIA-DIA/HORA-HORA (HORA en 24H y DIA en abreviaciones del ingles)

public abstract class Medico {

    private String name;
    private String ID;
    private float pricePHour;
    private ArrayList<Date> dates;
    private String schedule;
    private ArrayList<Especialidad> especialidades;

    public Medico(String name, String ID, float pricePHour, String schedule, ArrayList<Especialidad> especialidades) throws Exception {

        if (name == null) {
            throw new Exception("El nombre del medico no puede ser nulo");
        }
        if (name.length() == 0) {
            throw new Exception("El nombre del medico no puede ser vacio");
        }
        if (ID == null) {
            throw new Exception("El ID del medico no puede ser nulo");
        }
        if (ID.length() == 0) {
            throw new Exception("El ID del medico no puede ser vacio");
        }
        if (pricePHour <= 0) {
            throw new Exception("El precio por hora debe ser mayor a 0");
        }
        if (schedule == null) {
            throw new Exception("El horario del medico no puede ser nulo");
        }
        if (schedule.length() == 0) {
            throw new Exception("El horario del medico no puede ser vacio");
        }

        this.name = name;
        this.ID = ID;
        this.pricePHour = pricePHour;
        this.dates = new ArrayList<>();
        this.schedule = schedule;
        this.especialidades = especialidades;
    }

    public abstract float calcularValorCita(int timeMin);

    public abstract boolean isAvailable(Date date);

    public ArrayList<Especialidad> getEspecialidades() {
        return this.especialidades;
    }

    public void addEspecialidad(Especialidad nueva) throws Exception {
        this.especialidades.add(nueva);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null) {
            throw new Exception("El nombre del medico no puede ser nulo");
        }
        if (name.length() == 0) {
            throw new Exception("El nombre del medico no puede ser vacio");
        }

        this.name = name;
        Administrador.actualizarMedico(this);
    }

    public float getPricePHour() {
        return pricePHour;
    }

    public void setPricePHour(float pricePHour) throws Exception {
        if (pricePHour <= 0) {
            throw new Exception("El precio por hora debe ser mayor a 0");
        }

        this.pricePHour = pricePHour;
        Administrador.actualizarMedico(this);
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) throws Exception {
        if (ID == null) {
            throw new Exception("El ID del medico no puede ser nulo");
        }
        if (ID.length() == 0) {
            throw new Exception("El ID del medico no puede ser vacio");
        }

        this.ID = ID;
        Administrador.actualizarMedico(this);
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) throws Exception {
        if (schedule == null) {
            throw new Exception("El horario del medico no puede ser nulo");
        }
        if (schedule.length() == 0) {
            throw new Exception("El horario del medico no puede ser vacio");
        }

        this.schedule = schedule;
        Administrador.actualizarMedico(this);
    }

    public ArrayList<Date> getDates() {
        return this.dates;
    }

    public void addDate(Date newDate, boolean update) throws Exception {
        this.dates.add(newDate);
        if (update) {
            Administrador.actualizarMedico(this);
        }
    }
}
