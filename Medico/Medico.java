package Medico;

import java.util.LinkedList;

public abstract class Medico {

    private String name;
    private String ID;
    private float pricePHour;
    private LinkedList<String> dates;
    private String schedule;
    private Especialidad specialty;

    public Medico(String name, String ID, float pricePHour, String schedule, Especialidad specialty) throws Exception {

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
        if (specialty == null) {
            throw new Exception("La especialidad del medico no puede ser nula");
        }

        this.name = name;
        this.ID = ID;
        this.pricePHour = pricePHour;
        this.dates = new LinkedList<>();
        this.schedule = schedule;
        this.specialty = specialty;
    }

    public abstract int calcularValorCita(int time);

    public abstract boolean isAvailable(String date);

}
