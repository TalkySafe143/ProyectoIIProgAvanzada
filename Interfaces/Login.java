package Interfaces;

import Usuario.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Login {

    private Usuario actualUser;

    public Login(String action) {

        Scanner scanner = new Scanner(System.in);

        if (action.equals("login")) {
            // Proceso de Login

            System.out.println("Por favor, ingrese el correo");
            String email = scanner.nextLine();

            if (!email.contains("@")) {
                email = email.concat("@gmail.com");
                System.out.println("No ha ingresado su correo correctamente, se ha modificado a: " + email);
            }

            System.out.println("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            try {
                this.actualUser = this.login(email, password);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                this.actualUser = null;
            }

        } else {
            // Proceso de registro

            System.out.println("Ingrese su correo: ");
            String email = scanner.nextLine();
            if (!email.contains("@")) {
                email = email.concat("@gmail.com");
                System.out.println("No ha ingresado su correo correctamente, se ha modificado a: " + email);
            }

            System.out.println("Ingrese su nombre: ");
            String name = scanner.nextLine();

            System.out.println("Ingrese su ID: ");
            String ID = scanner.nextLine();

            System.out.println("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            System.out.println("Ingrese el tipo de usuario a registrar\n1 -> admin / 2 -> user :");
            int adminOption = scanner.nextInt();

            if (adminOption > 2 || adminOption < 1) {
                adminOption = 2;
                System.out.println("No ha ingresado un tipo de usuario valido, se le ha asignado (Usuario) por defecto.");
            }

            String admin;

            if (adminOption == 1) {
                admin = "admin";
            } else {
                admin = "user";
            }

            try {
                this.actualUser = this.register(email, name, ID, password, admin);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                this.actualUser = null;
            }
        }
    }

    private Usuario login(String email, String password) throws Exception {
        Usuario logged = null;

        FileReader file = null;
        BufferedReader buffer = null;

        boolean exist = false;

        try {
            file = new FileReader("./src/Usuario/users.txt");
            buffer = new BufferedReader(file);

            String line;

            while ((line = buffer.readLine()) != null) {
                String parts[] = line.split(";");

                if (parts[0].equals(email)) {
                    exist = true;
                    if (parts[3].equals(password)) {
                        file.close();
                        buffer.close();
                        if (parts[4].equals("admin")) {
                            logged = new Administrador(email, parts[1], parts[2], password);
                        } else if (parts[4].equals("user")) {
                            logged = new User(email, parts[1], parts[2], password);
                        } else {
                            throw new Exception("No se encontro el tipo de usuario");
                        }
                        break;
                    } else {
                        throw new Exception("Alguna de su informacion es incorrecta");
                    }
                }
            }

            file.close();
            buffer.close();

            if (!exist) {
                throw new Exception("No se encontró el usuario que se quiere loggear");
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw e;
        }

        return logged;
    }

    private Usuario register(String email, String name, String ID, String password, String admin) throws Exception {
        Usuario nuevo = null;

        if (Usuario.checkIfUserExist(email)) {
            throw new Exception("El usuario que trata de registrar ya existe");
        }

        if (admin.equals("admin")) {
            nuevo = new Administrador(email, name, ID, password);
        } else if (admin.equals("user")) {
            nuevo = new User(email, name, ID, password);
        } else {
            throw new Exception("El tipo de usuario no fue envíado correctamente");
        }

        return nuevo;
    }

    public Usuario getActualUser() {
        return this.actualUser;
    }
}
