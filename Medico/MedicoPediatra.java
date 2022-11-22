package Medico;

import java.util.ArrayList;
import java.util.Date;


/*
 * Andrian ruiz
 * Sebastian galindo
 * Andres Cano
 */

 /*
  * NOMBRE medico pediatra extendido a medico 
  OBJETIVO agendar una cita con el valor y horario disponible para medicos pediatras
  PARAMETROS String name, String ID, float pricePHour, ArrayList<Especialidad> especialidades
  SALIDAS true o false dependiendo la disponibilidad del doctor
  */


public class MedicoPediatra extends Medico {
    public MedicoPediatra(String name, String ID, float pricePHour, ArrayList<Especialidad> especialidades) throws Exception {

        super(name, ID, pricePHour, "Mon-Fri/8-15", especialidades);
    }

    @Override
    public float calcularValorCita(int timeMin) {
        return this.getPricePHour() * ((float)timeMin/60); // calcula el valor de la cita con el tiempo de ella
    }

    @Override
    public boolean isAvailable(Date date) {

        if (date.getDay() > 5 || date.getDay() < 1) {
            return false;
        }                                       
        if (date.getHours() < 8 || date.getHours() > 15) {  // verifica que el horario de la cita este disponible
            return false;
        }


        for (Date x: this.getDates()) { //recorre las citas 
            if (x.compareTo(date) == 0) {
                return false;
            }
        }

        return true;
    }
}
