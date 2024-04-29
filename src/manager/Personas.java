package manager;

import java.util.ArrayList;

public abstract class Personas {
    protected String nombre;
    protected String apellido;
    protected String fechaDeNacimiento;
    protected double nivelDeMotivacion;
    protected int sueldoAnual;
    protected int id;

    public Personas(String nombre, String apellido, String fechaDeNacimiento, double nivelDeMotivacion, int sueldoAnual, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nivelDeMotivacion = nivelDeMotivacion;
        this.sueldoAnual = sueldoAnual;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public double getNivelDeMotivacion() {
        return nivelDeMotivacion;
    }

    public void setNivelDeMotivacion(int nivelDeMotivacion) {
        this.nivelDeMotivacion = nivelDeMotivacion;
    }

    public int getSueldoAnual() {
        return sueldoAnual;
    }

    public void setSueldoAnual(int sueldoAnual) {
        this.sueldoAnual = sueldoAnual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void entrenar(ArrayList<Personas> personas) {
        if (!(nivelDeMotivacion >= 10)){
            this.nivelDeMotivacion = (float) (this.nivelDeMotivacion + 0.2);
        } else {
            this.nivelDeMotivacion = 10;
        }
    }
}
