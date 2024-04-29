package manager;

import java.util.ArrayList;

public class Entrenadores extends Personas {
    private int torneosGanados;
    private boolean seleccionadorNacional;

    public Entrenadores(String nombre, String apellido, String fechaDeNacimiento, int nivelDeMotivacion, int sueldoAnual, int idEntrenador, int torneosGanados, boolean seleccionadorNacional) {
        super(nombre, apellido, fechaDeNacimiento, nivelDeMotivacion, sueldoAnual, idEntrenador);
        this.torneosGanados = torneosGanados;
        this.seleccionadorNacional = seleccionadorNacional;
    }

    public void incrementarSueldo() {
        this.sueldoAnual = ((sueldoAnual*105)/100);
    }

    public int getTorneosGanados() {
        return torneosGanados;
    }

    public void setTorneosGanados(int torneosGanados) {
        this.torneosGanados = torneosGanados;
    }

    public boolean isSeleccionadorNacional() {
        return seleccionadorNacional;
    }

    public void setSeleccionadorNacional(boolean seleccionadorNacional) {
        this.seleccionadorNacional = seleccionadorNacional;
    }

    @Override
    public void entrenar(ArrayList<Personas> personas) {
        if (!(nivelDeMotivacion >= 10)){
            if (this.seleccionadorNacional) {
                this.nivelDeMotivacion = (float) (this.nivelDeMotivacion + 0.3);
            }else {
                this.nivelDeMotivacion = (float) (this.nivelDeMotivacion + 0.15);
            }
        } else {
            this.nivelDeMotivacion = 10;
        }

        this.incrementarSueldo();

    }

    @Override
    public String toString() {
        return "Entrenadores{" +
                "torneosGanados=" + torneosGanados +
                ", seleccionadorNacional=" + seleccionadorNacional +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", nivelDeMotivacion=" + nivelDeMotivacion +
                ", sueldoAnual=" + sueldoAnual +
                ", id=" + id +
                '}';
    }
}
