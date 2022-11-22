package Usuario;

import java.io.*;

/*
*
* FORMATO PARA EL ARCHIVO DE USUARIOS
*
* email;name;ID;password;admin
*
* admin -> ("admin o "user")
* */
public class Usuario {

    private String email;
    private String name;
    private String ID;

    private String password;

    private final String admin;

    public Usuario(String email, String name, String ID, String password, String admin) throws Exception {
        if (email == null) {
            throw new Exception("El email no puede ser nulo");
        }
        if (email.length() == 0) {
            throw new Exception("El email no puede ser vacio");
        }
        if (name == null) {
            throw new Exception("El nombre no puede ser nulo");
        }
        if (name.length() == 0) {
            throw new Exception("El nombre no puede ser vacio");
        }
        if (ID == null) {
            throw new Exception("El ID no puede ser nulo");
        }
        if (ID.length() == 0) {
            throw new Exception("El ID no puede ser vacio");
        }
        if (password == null) {
            throw new Exception("La contraseña no puede ser nula");
        }
        if (password.length() == 0) {
            throw new Exception("La contraseña no puede ser vacia");
        }

        this.email = email;
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.admin = admin;

        boolean exist = Usuario.checkIfUserExist(email);

        if (!exist) {
            FileWriter file = null;
            BufferedWriter buffer = null;

            try {
                file = new FileWriter("./src/Usuario/users.txt", true);
                buffer = new BufferedWriter(file);

                buffer.write(this.email + ";" + this.name + ";" + this.ID + ";" + this.password + ";" + this.admin);
                buffer.newLine();

                buffer.close();
                file.close();
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                throw e;
            }
        }

    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.ID;
    }

    public String getEmail() {
        return this.email;
    }

    private String getPassword() {
        return this.password;
    }

    public String getUserType() {
        return this.admin;
    }

    public void setEmail(String email) throws Exception {

        if (email == null) {
            throw new Exception("El email no puede ser nulo");
        }
        if (email.length() == 0) {
            throw new Exception("El email no puede ser vacio");
        }

        this.email = email;

        this.updateProfile();
    }

    public void setID(String ID) throws Exception {

        if (ID == null) {
            throw new Exception("El ID no puede ser nulo");
        }
        if (ID.length() == 0) {
            throw new Exception("El ID no puede ser vacio");
        }

        this.ID = ID;

        this.updateProfile();
    }

    public void setName(String name) throws Exception {

        if (name == null) {
            throw new Exception("El nombre no puede ser nulo");
        }

        if (name.length() == 0) {
            throw new Exception("El nombre no puede ser vacio");
        }

        this.name = name;

        this.updateProfile();
    }

    public void setPassword(String password) throws Exception {

        if (password == null) {
            throw new Exception("El password no puede ser nulo");
        }

        if (password.length() == 0) {
            throw new Exception("El password no puede ser vacio");
        }

        this.password = password;

        this.updateProfile();
    }

    private void updateProfile() throws Exception {
        File oldFile = null;
        File newFile = null;
        BufferedReader bufferOld = null;
        BufferedWriter bufferNew= null;

        try {
            oldFile = new File("./src/Usuario/users.txt");
            newFile = new File("./src/Usuario/tempUsers.txt");
            bufferNew = new BufferedWriter(new FileWriter(newFile, true));
            bufferOld = new BufferedReader(new FileReader(oldFile));

            String line;

            while ((line = bufferOld.readLine()) != null) {
                if (line.contains(this.ID) || line.contains(this.email)) {
                    bufferNew.write(this.email + ";" + this.name + ";" + this.ID + ";" + this.password + ";" + this.admin);
                    bufferNew.newLine();
                } else {
                    bufferNew.write(line);
                    bufferNew.newLine();
                }
            }

            bufferOld.close();
            bufferNew.close();

            if (!oldFile.delete()) {
                throw new Exception("No se eliminar el archivo antiguo");
            }

            if(!newFile.renameTo(new File("./src/Usuario/users.txt"))) {
                throw new Exception("No se pudo renombrar el archivo nuevo");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static boolean checkIfUserExist(String email) throws Exception {

        FileReader readFile = new FileReader("./src/Usuario/users.txt");
        BufferedReader readBuffer = new BufferedReader(readFile);

        String line;


        while ((line = readBuffer.readLine()) != null) {
            String parts[] = line.split(";");

            if (parts[0].equals(email)) {
                readBuffer.close();
                readFile.close();
                return true;
            }
        }

        return false;
    }
}
