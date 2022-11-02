package Usuario;

public class Usuario {

    private String email;
    private String name;
    private String ID;

    private String password;

    public Usuario(String email, String name, String ID, String password) {
        // Tiene que escribir el usuario en un archivo texto
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

    public void setEmail(String email) throws Exception {

        if (email == null) {
            throw new Exception("El email no puede ser nulo");
        }
        if (email.length() == 0) {
            throw new Exception("El email no puede ser vacio");
        }

        this.email = email;
    }

    public void setID(String ID) throws Exception {

        if (ID == null) {
            throw new Exception("El ID no puede ser nulo");
        }
        if (ID.length() == 0) {
            throw new Exception("El ID no puede ser vacio");
        }

        this.ID = ID;
    }

    public void setName(String name) throws Exception {

        if (name == null) {
            throw new Exception("El nombre no puede ser nulo");
        }

        if (name.length() == 0) {
            throw new Exception("El nombre no puede ser vacio");
        }

        this.name = name;
    }

    public void setPassword(String password) throws Exception {

        if (password == null) {
            throw new Exception("El password no puede ser nulo");
        }

        if (password.length() == 0) {
            throw new Exception("El password no puede ser vacio");
        }

        this.password = password;
    }
}
