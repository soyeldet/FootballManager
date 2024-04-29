package manager;

import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;

public class Jugadores extends Personas implements Transferible {
    private int dorsal;
    private double puntuacion;
    private String posicion;

    public Jugadores(String nombre, String apellido, String fechaDeNacimiento, int nivelDeMotivacion, int sueldoAnual, int id_jugador, int dorsal, double puntuacion, String posicion) {
        super(nombre, apellido, fechaDeNacimiento, nivelDeMotivacion, sueldoAnual, id_jugador);
        this.dorsal = dorsal;
        this.puntuacion = puntuacion;
        this.posicion = posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void cambiarPosicion(){
        int random = (int) (Math.random() * (100 + 1));
        String posicion = null;
        if (random >= 1 && random <= 5){
            do {
                int numPosicion = (int) (Math.random() * (4 - 1 + 1)) + 1;
              switch (numPosicion){
                  case 1:
                      posicion = "POR";
                      break;

                  case 2:
                      posicion = "DEF";
                      break;

                  case 3:
                      posicion = "MED";
                      break;

                  case 4:
                      posicion = "DEL";
                      break;
              }

                if (!(this.puntuacion >= 100)) {
                    this.puntuacion += 1;
                }else{
                    this.puntuacion = 100;
                }

            } while (this.posicion.equalsIgnoreCase(posicion));
            this.posicion = posicion;
            System.out.println("La posicion de " + this.nombre + " ha cambiado a " + posicion);
        }
    }

    @Override
    public void entrenar (ArrayList<Personas> personas){
        super.entrenar(personas);
        double random = Math.random();

        BigDecimal incremento = BigDecimal.ZERO;

        if (this.puntuacion < 100) {
            if (random < 0.7) {
                incremento = new BigDecimal("0.10");
                System.out.println("Incremento de calidad en 0.1 puntos para " + this.nombre);
            } else if (random < 0.9) {
                incremento = new BigDecimal("0.20");
                System.out.println("Incremento de calidad en 0.2 puntos para " + this.nombre);
            } else {
                incremento = new BigDecimal("0.30");
                System.out.println("Incremento de calidad en 0.3 puntos para " + this.nombre);
            }
        }

        BigDecimal nuevaPuntuacion = new BigDecimal(Double.toString(this.puntuacion)).add(incremento);
        this.puntuacion = nuevaPuntuacion.doubleValue();

        if (this.puntuacion >= 100) {
            this.puntuacion = 100;
        }
        this.cambiarPosicion();
    }

    @Override
    public void transferirAEquipo(Equipos equipoSeleccionado, Jugadores jugadorSeleccionado) {
        ArrayList<Jugadores> jugadoresDelEquipoSeleccionado = null;
        jugadoresDelEquipoSeleccionado = equipoSeleccionado.getJugadores();
        jugadoresDelEquipoSeleccionado.add(jugadorSeleccionado);
        System.out.println(jugadoresDelEquipoSeleccionado);
    }

    @Override
    public boolean esTransferible() {
        return true;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", dorsal=" + dorsal +
                ", puntuacion=" + puntuacion +
                ", posicion='" + posicion + '\'' +
                ", nivelDeMotivacion=" + nivelDeMotivacion +
                ", sueldoAnual=" + sueldoAnual +
                '}';
    }
}
