package Medico;

/*
 * Andrian ruiz
 * Sebastian galindo
 * Andres Cano
 */

 /*
  * NOMBRE especialidad 
  OBJETIVO verificar el tipo de especialidad de cada doctor
  PARAMETROS String name, String university
  SALIDAS ninguna
  */

public class Especialidad {

    private String name;
    private String university;

    public Especialidad(String name, String university) throws Exception {

        if (name == null) {
            throw new Exception("El nombre de la especialidad no puede ser nulo");
        }
        if (name.length() == 0) {
            throw new Exception("El nombre de la especialidad no puede ser vacio");
        }
        if (university == null) {
            throw new Exception("El nombre de la universidad no puede ser nulo");
        }
        if (university.length() == 0) {
            throw new Exception("El nombre de la universidad no puede ser vacio");
        }

        this.name = name;
        this.university = university;
    }

    public String getName() {
        return this.name;
    }

    public String getUniversity() {
        return this.university;
    }

    public void setName(String name) throws Exception {

        if (name == null) {
            throw new Exception("El nombre de la especialidad no puede ser nulo");
        }
        if (name.length() == 0) {
            throw new Exception("El nombre de la especialidad no puede ser vacio");
        }

        this.name = name;
    }

    public void setUniversity(String university) throws Exception {

        if (university == null) {
            throw new Exception("El nombre de la universidad no puede ser nulo");
        }
        if (university.length() == 0) {
            throw new Exception("El nombre de la universidad no puede ser vacio");
        }

        this.university = university;
    }
}
