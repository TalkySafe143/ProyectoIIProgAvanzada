package Medico;

import java.util.ArrayList;
import java.util.Date;

public class MedicoInternista extends Medico {

    public MedicoInternista(String name, String ID, float pricePHour, ArrayList<Especialidad> especialidades) throws Exception {

        super(name, ID, pricePHour, "Mon-Fri/6-20", especialidades);
    }

    @Override
    public float calcularValorCita(int timeMin) {
        return this.getPricePHour() * ((float)timeMin/60);
    }

    @Override
    public boolean isAvailable(Date date) {

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
