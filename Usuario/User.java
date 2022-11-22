package Usuario;

import Medico.*;

import java.util.ArrayList;
import java.util.Scanner;

public class User extends Usuario {

    private Scanner input;

    public User (String email, String name, String ID, String password) throws Exception {
        super(email, name, ID, password, "user");
        input = new Scanner(System.in);
    }

    public void agendarCita(String hora, String dia, String especialidad) throws Exception {
        System.out.println("Hola estos son los medicos que estan disponibles: ");

        ArrayList<Medico> medicos = new ArrayList<>();


    }

}
