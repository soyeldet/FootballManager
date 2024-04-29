package manager;
import java.util.ArrayList;

public class Equipos{
    private String nombre;
    private int anyoFundacion;
    private String ciudad;
    private String nombreEstadio;
    private String nombrePresidente;
    private Entrenadores entrenador;
    private ArrayList<Jugadores> jugadores;
    private int idEquipo;
    private int goles;
    private int diferenciaDeGoles;
    private int puntos;
    private double puntuacionEquipo;

    private double motivacionEquipo;

    public Equipos(String nombre, int anyoFundacion, String ciudad, String nombreEstadio, String nombrePresidente, Entrenadores entrenador, ArrayList<Jugadores> jugadores, int id_equipo, double puntuacionEquipo, double motivacionEquipo) {
        this.nombre = nombre;
        this.anyoFundacion = anyoFundacion;
        this.ciudad = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.nombrePresidente = nombrePresidente;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
        this.idEquipo = id_equipo;
        this.puntuacionEquipo = puntuacionEquipo;
        this.motivacionEquipo = motivacionEquipo;
    }

    public double getMotivacionEquipo() {
        return motivacionEquipo;
    }

    public void setMotivacionEquipo(double motivacionEquipo) {
        this.motivacionEquipo = motivacionEquipo;
    }

    public double getPuntuacionEquipo() {
        return puntuacionEquipo;
    }

    public void setPuntuacionEquipo(double puntuacionEquipo) {
        this.puntuacionEquipo = puntuacionEquipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getDiferenciaDeGoles() {
        return diferenciaDeGoles;
    }

    public void setDiferenciaDeGoles(int diferenciaDeGoles) {
        this.diferenciaDeGoles = diferenciaDeGoles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnyoFundacion() {
        return anyoFundacion;
    }

    public void setAnyoFundacion(int anyoFundacion) {
        this.anyoFundacion = anyoFundacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }

    public void setNombreEstadio(String nombreEstadio) {
        this.nombreEstadio = nombreEstadio;
    }

    public String getNombrePresidente() {
        return nombrePresidente;
    }

    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }

    public Entrenadores getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenadores entrenador) {
        this.entrenador = entrenador;
    }

    public ArrayList<Jugadores> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void modificarPresidente(String nombre) {
        this.setNombrePresidente(nombre);
    }

    public void destituirEntrenador() {
        this.setEntrenador(null);
    }

    public void ficharJugadorEntrenador(Jugadores jugador, Entrenadores entrenador) {
        int puntuacionJugadores = 0;
        int motivacionEquipo = 0;
        ArrayList<Jugadores> jugadoresEquipo;
        if (entrenador == null){
            jugadoresEquipo = this.getJugadores();
            jugadoresEquipo.add(jugador);
        } else {
            this.setEntrenador(entrenador);
        }
    }

    @Override
    public String toString() {
        return "Equipos{" +
                "nombre='" + nombre + '\'' +
                ", anyoFundacion=" + anyoFundacion +
                ", ciudad='" + ciudad + '\'' +
                ", nombreEstadio='" + nombreEstadio + '\'' +
                ", nombrePresidente='" + nombrePresidente + '\'' +
                ", entrenador=" + entrenador +
                ", jugadores=" + jugadores +
                ", idEquipo=" + idEquipo +
                ", goles=" + goles +
                ", diferenciaDeGoles=" + diferenciaDeGoles +
                ", puntos=" + puntos +
                ", puntuacionEquipo=" + puntuacionEquipo +
                ", motivacionEquipo=" + motivacionEquipo +
                '}';
    }

    public void calcularPuntuacionMedia() {
        double puntuacionJugadores = 0;
        int tieneEntrenador = 1;

        for (int i = 0; i < this.getJugadores().size(); i++) {
            puntuacionJugadores = puntuacionJugadores + this.getJugadores().get(i).getPuntuacion();
            motivacionEquipo = motivacionEquipo + this.getJugadores().get(i).getNivelDeMotivacion();
        }
        motivacionEquipo = motivacionEquipo + this.getEntrenador().getNivelDeMotivacion();

        this.setPuntuacionEquipo((puntuacionJugadores/this.getJugadores().size()));

        if (this.getEntrenador() == null){
            tieneEntrenador = 0;
        }

        this.setMotivacionEquipo((motivacionEquipo/((this.getJugadores().size())+(tieneEntrenador))));

        System.out.println(this);

    }
}