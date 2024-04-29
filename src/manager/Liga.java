package manager;

import java.util.ArrayList;

public class Liga {
    private String nombre;
    private ArrayList<Equipos> listaEquipos;
    private int puntuacionDelEquipo;
    private int partidosDisputados;
    private int golesFavor;
    private int golesContra;
    private int diferenciaGoles;

    public String getNombreEquipo() {
        return nombre;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombre = nombreEquipo;
    }

    public ArrayList<Equipos> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ArrayList<Equipos> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public int getPuntuacionDelEquipo() {
        return puntuacionDelEquipo;
    }

    public void setPuntuacionDelEquipo(int puntuacionDelEquipo) {
        this.puntuacionDelEquipo = puntuacionDelEquipo;
    }

    public int getPartidosDisputados() {
        return partidosDisputados;
    }

    public void setPartidosDisputados(int partidosDisputados) {
        this.partidosDisputados = partidosDisputados;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    public void anyadirEquipos(Equipos equipo) {
        this.listaEquipos.add(equipo);
    }

    public void disputarLiga() {
        for (int i = 0; i < (this.listaEquipos.size()); i++) {
            Equipos equipoLocal = this.listaEquipos.get(i);
            for (int j = 0; j < this.listaEquipos.size(); j++) {
                Equipos equipoVisitante = this.listaEquipos.get(j);
                if (!equipoVisitante.getNombre().equalsIgnoreCase(equipoLocal.getNombre())){

                    if (equipoLocal.getMotivacionEquipo() >= 7 && equipoLocal.getPuntuacionEquipo() >= 80){
                        equipoLocal.setGoles((int) (Math.random() * (10 - 5 + 1)) + 5);
                    } else {
                        equipoLocal.setGoles((int) (Math.random() * 8));
                    }

                    if (equipoLocal.getMotivacionEquipo() >= 7 && equipoLocal.getPuntuacionEquipo() >= 80){
                        equipoVisitante.setGoles((int) (Math.random() * (10 - 5 + 1)) + 5);
                    } else {
                        equipoVisitante.setGoles((int) (Math.random() * 8));
                    }

                    if (equipoLocal.getGoles() > equipoVisitante.getGoles()){
                        equipoLocal.setPuntos(equipoLocal.getPuntos()+3);

                    } else if (equipoLocal.getGoles() < equipoVisitante.getGoles()){
                        equipoVisitante.setPuntos(equipoVisitante.getPuntos()+3);

                    } else if (equipoLocal.getGoles() == equipoVisitante.getGoles()){
                        equipoLocal.setPuntos(equipoLocal.getPuntos()+1);
                        equipoVisitante.setPuntos(equipoVisitante.getPuntos()+1);

                    }
                    equipoLocal.setDiferenciaDeGoles(equipoLocal.getDiferenciaDeGoles()+equipoLocal.getGoles());
                    equipoVisitante.setDiferenciaDeGoles(equipoVisitante.getDiferenciaDeGoles()+equipoVisitante.getGoles());
                }
            }
        }
    }

    public void visualizarResultados(){

    }

    public Liga(String nombreEquipo, ArrayList<Equipos> listaEquipos) {
        this.nombre = nombreEquipo;
        this.listaEquipos = listaEquipos;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "nombre='" + nombre + '\'' +
                ", listaEquipos=" + listaEquipos +
                ", puntuacionDelEquipo=" + puntuacionDelEquipo +
                ", partidosDisputados=" + partidosDisputados +
                ", golesFavor=" + golesFavor +
                ", golesContra=" + golesContra +
                ", diferenciaGoles=" + diferenciaGoles +
                '}';
    }


}