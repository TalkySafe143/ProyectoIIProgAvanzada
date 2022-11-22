package Medico;

import java.util.ArrayList;
import java.util.Date;


/*
 * Andrian ruiz
 * Sebastian galindo
 * Andres Cano
 */

 /*
  * NOMBRE medico internista
  OBJETIVO agendar una cita con el valor y horario disponible para medicos internistas
  PARAMETROS String name, String ID, float pricePHour, ArrayList<Especialidad> especialidades
  SALIDAS true o false dependiendo la disponibilidad del doctor
  */

public class MedicoInternista extends Medico {

    public MedicoInternista(String name, String ID, float pricePHour, ArrayList<Especialidad> especialidades) throws Exception {

        super(name, ID, pricePHour, "Mon-Fri/6-20", especialidades);
    }

    @Override
    public float calcularValorCita(int timeMin) {
        return this.getPricePHour() * ((float)timeMin/60);
    }

    @Override
    public boolean VerificarDisponibilidad(Date date) {

        if (date.getDay() > 5 || date.getDay() < 1) {
            return false;
        }
        if (date.getHours() < 6 || date.getHours() > 20) {
            return false;
        }

        for (Date x: this.getDates()) {
            if (x.compareTo(date) == 0) {
                return false;
            }
        }

        return true;
    }
}
