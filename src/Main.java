import manager.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Personas> personas = new ArrayList<>();
        ArrayList<Equipos> equipos = new ArrayList<>();

        ArrayList<Integer> contadorJugadores = new ArrayList<>();
        ArrayList<Integer> contadorEntrnadores = new ArrayList<>();

        int opcion;

        darDeAltaJugadoresPredeterminados(personas, contadorJugadores);
        darDeAltaEntrenadoresPredeterminados(personas, contadorEntrnadores);
        darDeAltaEquiposPredeterminados(personas, equipos);
        generarJugadoresYEntrenadoresDisponibles(personas, contadorJugadores, contadorEntrnadores);

        do {
            opcion = menuDelManager();
            switch (opcion) {
                case 1:
                    gestionarEquipo(equipos, personas, contadorJugadores, contadorEntrnadores);
                    break;
                case 2:
                    darDeAltaEquipo(equipos);
                    break;
                case 3:
                    darDeAltaJugadorEntrenador(personas, contadorJugadores, contadorEntrnadores);
                    break;
                case 4:
                    consultarDatosEquipo(equipos);
                    break;
                case 5:
                    consultarDatosJugador(equipos);
                    break;
                case 6:
                    crearLiga(equipos);
                    break;
                case 7:
                    realizarEntrenamiento(personas);
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    private static void realizarEntrenamiento(ArrayList<Personas> personas) {
        for (Personas persona : personas) {
            persona.entrenar(personas);
        }
    }

    private static void crearLiga(ArrayList<Equipos> equipos) {
        String nombre;
        int numeroEquipos;
        ArrayList<Equipos> equiposLiga;

        nombre = nombreLiga();
        numeroEquipos = pedirNumeroEquipos(equipos);
        equiposLiga = pedirEquiposLiga(equipos, numeroEquipos);

        Liga liga = new Liga(nombre, equiposLiga);

        liga.disputarLiga();

    }

    private static ArrayList<Equipos> pedirEquiposLiga(ArrayList<Equipos> equipos, int numeroEquipos) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipos> equiposLiga = new ArrayList<>();
        Equipos equipo = null;
        String nombreEquipo;
        boolean equipoCoincidente = false;

        for (int i = 0; i < numeroEquipos; i++) {
            do {
                System.out.println("Escribe el nombre del " + (i+1) + " del equipo.");
                nombreEquipo = sc.nextLine();

                for (Equipos equipoEncontrado : equipos){
                    if (nombreEquipo.equalsIgnoreCase(equipoEncontrado.getNombre())){
                        equipo = equipoEncontrado;
                        equipoCoincidente = true;
                        for (Equipos equipoRepetido : equiposLiga){
                            if (equipoRepetido.getNombre().equalsIgnoreCase(nombreEquipo)){
                                equipo = null;
                                equipoCoincidente = false;
                            }
                        }
                    }
                }

                if (!equipoCoincidente){
                    System.out.println("No se ha encontrado el equipo o ya se encuentra en la liga");
                } else {
                    equiposLiga.add(equipo);
                }
            }while(!equipoCoincidente);
            equipoCoincidente = false;
        }

        return equiposLiga;

    }

    private static int pedirNumeroEquipos(ArrayList<Equipos> equipos) {
        Scanner sc = new Scanner(System.in);
        String numeroIntroducido;
        int numeroEquipos = 0;
        boolean salir = false;

        System.out.println("Se ha encontrado un total de " + equipos.size() + " equipos.");
        System.out.println("Introduce el numero de equipos que participaran en la liga: (Mínimo 2)");

        do {
            numeroIntroducido = sc.nextLine();
            try {
                numeroEquipos = Integer.parseInt(numeroIntroducido);
                if (numeroEquipos >= 2 && numeroEquipos <= equipos.size()) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un número de equipos valido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número de equipos valido.");
            }
        }while(!salir);

        return numeroEquipos;

    }

    private static String nombreLiga() {
        Scanner sc = new Scanner(System.in);
        String nombre;

        do {
            System.out.println("Escribe el nombre de la liga:");
            nombre = sc.nextLine();
        } while (nombre.isEmpty());
        return nombre;
    }

    private static void consultarDatosJugador(ArrayList<Equipos> equipos) {
        Equipos equipo;
        Jugadores jugador;

        equipo = pedirEquipo(equipos);
        if (equipo != null){
            jugador = (Jugadores) pedirJugador(equipo);
            if (jugador != null){
                System.out.println(jugador);
            } else {
                System.out.println("No se ha encontrado el jugador");
            }
        }
    }

    private static Object pedirJugador(Equipos equipo) {
        Scanner sc = new Scanner(System.in);
        String nombre, dorsalIntroducido;
        boolean salir = false;
        int dorsalJugador = 0;
        ArrayList<Jugadores> jugadoresEquipo;
        Jugadores jugador = null;

        System.out.println("Introduce el nombre del jugador:");
        nombre = sc.nextLine();

        System.out.println("Introduce el dorsal del jugador:");
        do {
            dorsalIntroducido = sc.nextLine();
            try {
                dorsalJugador = Integer.parseInt(dorsalIntroducido);
                if (dorsalJugador >= 0) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un número válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }while(!salir);

        jugadoresEquipo = equipo.getJugadores();

        for (Jugadores jugadorEncontrado : jugadoresEquipo) {
            if (jugadorEncontrado.getNombre().equalsIgnoreCase(nombre) && jugadorEncontrado.getDorsal() == dorsalJugador) {
                jugador = jugadorEncontrado;
            }
        }
        return jugador;
    }

    private static Equipos pedirEquipo(ArrayList<Equipos> equipos) {
        Scanner sc = new Scanner(System.in);
        String nombreEquipo;
        boolean equipoCoincidente = false;
        Equipos equipo = null;

        System.out.println("Escribe el nombre del equipo:");
        nombreEquipo = sc.nextLine();
        for (Equipos equipoEncontrado: equipos){
            if (nombreEquipo.equalsIgnoreCase(equipoEncontrado.getNombre())){
                equipo = equipoEncontrado;
                equipoCoincidente = true;
            }
        }
        if (!equipoCoincidente){
            System.out.println("No se ha encontrado el equipo.");
        }
        return equipo;
    }

    private static void consultarDatosEquipo(ArrayList<Equipos> equipos) {
        Equipos equipo;

        equipo = pedirEquipo(equipos);
        System.out.println(equipo);

    }

    private static void darDeAltaJugadorEntrenador(ArrayList<Personas> personas, ArrayList<Integer> contadorJugadores, ArrayList<Integer> contadorEntrnadores) {
        String nombre, apellido, fechaDeNacimiento;
        int nivelDeMotivacion, tipoDePersona, sueldoAnual;

        tipoDePersona = seleccionDelTipoDePersona();
        nombre = pedirNombrePersona();
        apellido = perdirApellidoPersonas();
        fechaDeNacimiento = pedirFechaNac();
        nivelDeMotivacion = pedirNivelDemOtivacion();
        sueldoAnual = pedirSueldoAnual();

        switch(tipoDePersona) {
            case 1:
                darDeAltaJugador(nombre, apellido, fechaDeNacimiento, nivelDeMotivacion, sueldoAnual, contadorJugadores, personas);
                break;
            case 2:
                darDeAltaEntrenador(nombre, apellido, fechaDeNacimiento, nivelDeMotivacion, sueldoAnual, contadorEntrnadores, personas);
                break;
        }
    }

    private static void darDeAltaEntrenador(String nombre, String apellido, String fechaDeNacimiento, int nivelDeMotivacion, int sueldoAnual, ArrayList<Integer> contadorEntrnadores, ArrayList<Personas> personas) {
        Scanner sc = new Scanner(System.in);
        String torneosIntroducidos, seleccionadorNacionalIntorducido;
        int torneosGanados = 0;
        boolean seleccionadorNacional = false;
        boolean salir = false;

        System.out.println("Escribe el numero de torneos ganados:");
        do {
            torneosIntroducidos = sc.nextLine();
            try {
                torneosGanados = Integer.parseInt(torneosIntroducidos);
                if (torneosGanados >= 0 && !torneosIntroducidos.isEmpty()) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un número válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }while(!salir);

        System.out.println("El entrenador es seleccionador nacional?");
        seleccionadorNacionalIntorducido = sc.nextLine();

        if (seleccionadorNacionalIntorducido.equalsIgnoreCase("yes") || seleccionadorNacionalIntorducido.equalsIgnoreCase("si") || seleccionadorNacionalIntorducido.equalsIgnoreCase("true")){
            seleccionadorNacional = true;
        }

        Entrenadores en = new Entrenadores(nombre, apellido, fechaDeNacimiento, nivelDeMotivacion, sueldoAnual, (contadorEntrnadores.size()+1) ,torneosGanados, seleccionadorNacional);

        personas.add(en);
        contadorEntrnadores.add(1);
    }

    private static void darDeAltaJugador(String nombre, String apellido, String fechaDeNacimiento, int nivelDeMotivacion, int sueldoAnual, ArrayList<Integer> contadorJugadores, ArrayList<Personas> personas) {
        Scanner sc = new Scanner(System.in);
        String dorsalIntroducido, posicion;
        int dorsalJugador = 0;
        int puntuacionJugador;
        boolean salir = false;

        System.out.println("Escribe el dorsal del jugador:");
        do {
            dorsalIntroducido = sc.nextLine();
            try {
                dorsalJugador = Integer.parseInt(dorsalIntroducido);
                if (dorsalJugador >= 0 && !dorsalIntroducido.isEmpty()) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un número válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        } while (!salir);

        salir = false;

        do {
            System.out.println("¿Qué posición juega el jugador? (POR, DEL, MED, DEF)");
            posicion = sc.nextLine();
            if (posicion.equalsIgnoreCase("por")) {
                posicion = "POR";
                salir = true;
            } else if (posicion.equalsIgnoreCase("del")) {
                posicion = "DEL";
                salir = true;
            } else if (posicion.equalsIgnoreCase("med")) {
                posicion = "MED";
                salir = true;
            } else if (posicion.equalsIgnoreCase("def")) {
                posicion = "DEF";
                salir = true;
            } else {
                System.out.println("Error, introduce una posición válida:");
            }
        } while (!salir);

        puntuacionJugador = (int) (Math.random() * 71) + 30;

        Jugadores j = new Jugadores(nombre, apellido, fechaDeNacimiento, nivelDeMotivacion, sueldoAnual, (contadorJugadores.size()+1), dorsalJugador, puntuacionJugador, posicion);

        personas.add(j);
        contadorJugadores.add(1);

        }

    private static int pedirNivelDemOtivacion() {
        Scanner sc = new Scanner(System.in);
        String nivelIntroducido;
        int nivelJugador = 0;
        boolean salir = false;

        System.out.println("Escribe el nivel de motivación del jugador: (5-10)");
        do {
            nivelIntroducido = sc.nextLine();
            try {
                nivelJugador = Integer.parseInt(nivelIntroducido);
                if (nivelJugador >= 5 && nivelJugador <= 10) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un número válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        } while (!salir);

        return nivelJugador;
    }

    private static int pedirSueldoAnual() {
        Scanner sc = new Scanner(System.in);
        String sueldoIntroducido;
        int sueldoSeleccionado = 0;
        boolean salir = false;

        System.out.println("Escribe el sueldo anual (1000-2000)");
        do {
            sueldoIntroducido = sc.nextLine();
            try {
                sueldoSeleccionado = Integer.parseInt(sueldoIntroducido);
                if (sueldoSeleccionado >= 1000 && sueldoSeleccionado <= 2000) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un sueldo valido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un sueldo válido.");
            }
        }while(!salir);
        return sueldoSeleccionado;
    }

    private static int seleccionDelTipoDePersona() {
        Scanner sc = new Scanner(System.in);
        String seleccion;
        int tipoDePersona = 0;
        boolean salir = false;

        do {
            System.out.println("¿Qué quieres dar de alta: Jugador o Entrenador?");
            seleccion = sc.nextLine();
            if (seleccion.equalsIgnoreCase("jugador")) {
                tipoDePersona = 1;
                salir = true;
            } else if (seleccion.equalsIgnoreCase("entrenador")){
                tipoDePersona = 2;
                salir = true;
            }  else {
                System.out.println("Error, introduce Jugador o Entrenador");
            }
        } while (!salir);
        return tipoDePersona;
    }

    private static String pedirFechaNac() {
        Scanner sc = new Scanner(System.in);
        String diaIntroducido, mesIntroducido, anyoIntroducido, fechaNacimiento;
        int diaNacimiento, mesNacimiento, anyoNacimiento;
        boolean salir = false;

        System.out.println("Escribe el dia (1-31)");
        do {
            diaIntroducido = sc.nextLine();
            try {
                diaNacimiento = Integer.parseInt(diaIntroducido);
                if (diaNacimiento >= 1 && diaNacimiento <= 31) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un dia valido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un dia válido.");
            }
        }while(!salir);

        salir = false;

        System.out.println("Escribe el mes (1-12)");
        do {
            mesIntroducido = sc.nextLine();
            try {
                mesNacimiento = Integer.parseInt(mesIntroducido);
                if (mesNacimiento >= 1 && mesNacimiento <= 12) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un mes valido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un mes válido.");
            }
        }while(!salir);

        salir = false;

        System.out.println("Escribe el anyo (1950-2024)");
        do {
            anyoIntroducido = sc.nextLine();
            try {
                anyoNacimiento = Integer.parseInt(anyoIntroducido);
                if (anyoNacimiento >= 1950 && anyoNacimiento <= 2024) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un anyo valido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un anyo válido.");
            }
        }while(!salir);

        fechaNacimiento = (diaIntroducido + "/" + mesIntroducido + "/" + anyoIntroducido);

        return fechaNacimiento;
    }

    private static String perdirApellidoPersonas() {
        Scanner sc = new Scanner(System.in);
        String apellido;

        System.out.println("Escribe el apellido:");
        do {
            apellido = sc.nextLine();
            if (apellido.isEmpty()) {
                System.out.println("Escribe el apellido:");
            }
        }while(apellido.isEmpty());
        return apellido;
    }

    private static String pedirNombrePersona() {
        Scanner sc = new Scanner(System.in);
        String nombre;

        System.out.println("Escribe el nombre:");
        do {
            nombre = sc.nextLine();
            if (nombre.isEmpty()) {
                System.out.println("Escribe el nombre:");
            }
        }while(nombre.isEmpty());
        return nombre;
    }

    private static void darDeAltaEquipo(ArrayList<Equipos> equipos) {
        Scanner sc = new Scanner(System.in);
        String nombre, ciudad, nombreEstadio, nombrePresidente;
        int anyoFundacion;
        ArrayList<Jugadores> jugadores = new ArrayList<>();
        Equipos eq;

        nombre = obtenerNombreEquipo(equipos);
        anyoFundacion = obtenerAnyoFundacionEquipo();
        ciudad = obtenerCiudadEquipo();

        System.out.println("Escribe el nombre del estadio (Opcional):");
        nombreEstadio =sc.nextLine();

        System.out.println("Escribe el nombre del presidente (Opcional):");
        nombrePresidente =sc.nextLine();

        eq = new Equipos(nombre, anyoFundacion, ciudad, nombreEstadio, nombrePresidente, null, jugadores, (equipos.size() + 1), 0, 0);
        equipos.add(eq);

    }

    private static String obtenerCiudadEquipo() {
        Scanner sc = new Scanner(System.in);
        String ciudad;

        System.out.println("Escribe la ciudad del equipo:");
        do {
            ciudad = sc.nextLine();
            if (ciudad.isEmpty()) {
                System.out.println("Escribe una ciudad:");
            }
        }while(ciudad.isEmpty());
        return ciudad;
    }

    private static int obtenerAnyoFundacionEquipo() {
        Scanner sc = new Scanner(System.in);
        String anyoIntroducido;
        int anyoFundacion = 0;
        boolean salir = false;

        System.out.println("Escribe el año de fundacion:");
        do {
            anyoIntroducido = sc.nextLine();
            try {
                anyoFundacion = Integer.parseInt(anyoIntroducido);
                if (anyoFundacion >= 1800 && anyoFundacion <= 2024) {
                    salir = true;
                } else {
                    System.out.println("Error: Ingresa un año válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un año válido.");
            }
        }while(!salir);
        return anyoFundacion;
    }

    private static String obtenerNombreEquipo(ArrayList<Equipos> equipos) {
        Scanner sc = new Scanner(System.in);
        boolean nombreEncontrado = false;
        String nombre;
        boolean salir = false;

        System.out.println("Escribe el nombre del equipo:");
        do {
            nombre = sc.nextLine();
            for (Equipos equipoConElMismoNombre : equipos) {
                if (nombre.equalsIgnoreCase(equipoConElMismoNombre.getNombre())) {
                    nombreEncontrado = true;
                }
            }
            if (nombreEncontrado) {
                System.out.println("El nombre ya existe, por favor introduce otro:");
            } else {
                salir = true;
            }

            if (nombre.isEmpty()) {
                salir = false;
                System.out.println("El nombre esta vacio");
            }
            nombreEncontrado = false;

        } while (!salir);
        return nombre;
    }

    private static void gestionarEquipo(ArrayList<Equipos> equipos, ArrayList<Personas> personas, ArrayList<Integer> contadorJugadores, ArrayList<Integer> contadorEntrnadores) {
        String equipoSeleccionado;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        do {
            System.out.println("Que equipo quieres gestionar?");
            equipoSeleccionado = sc.nextLine();
            equipoSeleccionado = buscarEquipo(equipoSeleccionado, equipos);
            if (equipoSeleccionado == null){
                System.out.println("No se ha encontrado el equipo");
            } else {
                exit = true;
            }
        }while(!exit);

        menuDeGestionDelEquipo(equipos, equipoSeleccionado, personas, contadorJugadores, contadorEntrnadores);
    }

    private static String buscarEquipo(String equipoSeleccionado, ArrayList<Equipos> equipos) {
        for (Equipos equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(equipoSeleccionado)) {
                return equipo.getNombre();
            }
        }
        return null;
    }

    private static void menuDeGestionDelEquipo(ArrayList<Equipos> equipos, String equipoSeleccionado, ArrayList<Personas> personas, ArrayList<Integer> contadorJugadores, ArrayList<Integer> contadorEntrnadores) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String seleccion;
        boolean salir = false;


        do {
            do {
                System.out.println("--Menu de gestion del equipo--");
                System.out.println("\t 1.-Dar de baja equipo");
                System.out.println("\t 2.-Modificar presidente");
                System.out.println("\t 3.-Destituir entrenador");
                System.out.println("\t 4.-Fichar jugador/entrenador");
                System.out.println("\t 5.-Transferir jugador");
                System.out.println("\t 0.-Salir");
                seleccion = sc.nextLine();
                try {
                    opcion = Integer.parseInt(seleccion);
                    if (opcion >= 0 && opcion <= 5) {
                        salir = true;
                    } else {
                        System.out.println("Error: Ingresa un número válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingresa un número válido.");
                }
            } while (!salir);

            sc.reset();

            salir = false;
            opcion = Integer.parseInt(seleccion);

            switch (opcion) {
                case 1:
                    darDeBajaEquipo(equipos, equipoSeleccionado);
                    salir = true;
                    break;
                case 2:
                    modificarPresidente(equipos, equipoSeleccionado);
                    break;
                case 3:
                    destituirEntrenador(equipos, equipoSeleccionado, personas);
                    break;
                case 4:
                    ficharJugadorEntrenador(equipos, equipoSeleccionado, personas, contadorJugadores, contadorEntrnadores);
                    break;
                case 5:
                    transferirJugador(equipos, equipoSeleccionado);
                    break;
                case 0:
                    salir = true;
                    break;
            }
        }while(!salir);
    }

    private static void ficharJugadorEntrenador(ArrayList<Equipos> equipos, String equipoSeleccionado, ArrayList<Personas> personas, ArrayList<Integer> contadorJugadores, ArrayList<Integer> contadorEntrnadores) {Scanner sc = new Scanner(System.in);
        String seleccion;
        int id;
        boolean salir = false;
        boolean equipoConEntrenador = true;
        Jugadores jugadorSeleccionado = null;
        Entrenadores entrenadorSeleccionado = null;
        int puntuacionJugadores = 0;
        float motivacionEquipo = 0;
        float tieneEntrenador = 1;

        do {
            System.out.println("¿Qué quieres fichar: Jugador o Entrenador?");
            seleccion = sc.nextLine();
            if (seleccion.equalsIgnoreCase("jugador") || seleccion.equalsIgnoreCase("entrenador")) {
                salir = true;
            } else {
                System.out.println("Error");
            }
        } while (!salir);

        for (Equipos equipoParaModificar : equipos) {
            if (equipoParaModificar.getNombre().equalsIgnoreCase(equipoSeleccionado)) {
                if (equipoParaModificar.getEntrenador() == null) {
                    equipoConEntrenador = false;
                }
            }
        }

        if (seleccion.equalsIgnoreCase("jugador")) {
            System.out.println("Jugadores:");
            for (Personas persona : personas) {
                if (persona instanceof Jugadores) {
                    System.out.println(persona);
                }
            }

            id = buscarId(seleccion, personas);

            for (Personas persona : personas) {
                if (persona instanceof Jugadores && persona.getId() == id) {
                    jugadorSeleccionado = (Jugadores) persona;
                }
            }

            if (jugadorSeleccionado != null) {
                personas.remove(jugadorSeleccionado);
            }

            for (Equipos equipoParaModificar : equipos) {
                if (equipoParaModificar.getNombre().equalsIgnoreCase(equipoSeleccionado)) {
                    equipoParaModificar.ficharJugadorEntrenador(jugadorSeleccionado, null);
                }
            }



        } else if (seleccion.equalsIgnoreCase("entrenador")) {
            if (!equipoConEntrenador) {
                System.out.println("Entrenadores:");
                for (Personas persona : personas) {
                    if (persona instanceof Entrenadores) {
                        System.out.println(persona);
                    }
                }

                id = buscarId(seleccion, personas);

                for (Personas persona : personas) {
                    if (persona instanceof Entrenadores && persona.getId() == id) {
                        entrenadorSeleccionado = (Entrenadores) persona;
                    }
                }

                if (entrenadorSeleccionado != null) {
                    personas.remove(entrenadorSeleccionado);
                }

                for (Equipos equipoParaModificar : equipos) {
                    if (equipoParaModificar.getNombre().equalsIgnoreCase(equipoSeleccionado)) {
                        equipoParaModificar.ficharJugadorEntrenador(null, entrenadorSeleccionado);
                    }
                }

                for (Equipos equipoParaModificar : equipos) {
                    if (equipoParaModificar.getNombre().equalsIgnoreCase(equipoSeleccionado)) {
                        equipoParaModificar.calcularPuntuacionMedia();
                    }
                }

            } else {
                System.out.println("Este equipo ya tiene entrenador.");
            }
        }
    }

    private static int buscarId(String seleccion, ArrayList<Personas> personas) {
        ArrayList<Jugadores> idJugadores = new ArrayList<>();
        ArrayList<Entrenadores> idEntrenadores = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String seleccionDeId;
        int id = 0;

        if (seleccion.equalsIgnoreCase("jugador")) {
            for (Personas personasDisponibles : personas) {
                if (personasDisponibles instanceof Jugadores) {
                    idJugadores.add((Jugadores) personasDisponibles);
                }
            }
        } else {
            for (Personas personasDisponibles : personas) {
                if (personasDisponibles instanceof Entrenadores) {
                    idEntrenadores.add((Entrenadores) personasDisponibles);
                }
            }
        }

            do {
                System.out.println("Escribe la id de quien quieres fichar");
                seleccionDeId = sc.nextLine();

                try {
                    id = Integer.parseInt(seleccionDeId);

                    if (seleccion.equalsIgnoreCase("jugador")) {
                        for (Jugadores idJugador : idJugadores) {
                            if (id == idJugador.getId()) {
                                return id;
                            }
                        }

                    } else if (seleccion.equalsIgnoreCase("entrenador")) {
                        for (Entrenadores idEntrenador : idEntrenadores) {
                            if (id == idEntrenador.getId()) {
                                return id;
                            }
                        }
                    }
                    id = 0;
                    System.out.println("No se encuentra la id, vuelve a introducirla");
                } catch (NumberFormatException e) {
                    System.out.println("Error");
                }
            } while (id == 0);
        idJugadores.clear();
        idEntrenadores.clear();
        return id;
    }

    private static void destituirEntrenador(ArrayList<Equipos> equipos, String equipoSeleccionado, ArrayList<Personas> personas) {
        Scanner sc = new Scanner(System.in);
        String confirmar;

        System.out.println("Escribe 'CONFIRMAR' para destituir al entrenador");
        confirmar = sc.nextLine();

        if (confirmar.equalsIgnoreCase("confirmar")) {

            for (Equipos equipoParaModificar : equipos) {
                if (equipoParaModificar.getNombre().equalsIgnoreCase(equipoSeleccionado)) {
                    personas.add(equipoParaModificar.getEntrenador());
                    equipoParaModificar.destituirEntrenador();
                    System.out.println("El entrenador ha sido destituido");
                }
            }
        } else {
            System.out.println("El entrenador no ha sido destituido");
        }
    }

    private static void modificarPresidente(ArrayList<Equipos> equipos, String equipoSeleccionado) {
        Scanner sc = new Scanner(System.in);
        String nombreNuevoPresidente;

        System.out.println("Escribe el nombre del nuevo presidente:");
        nombreNuevoPresidente = sc.nextLine();
        for (Equipos equipoParaModificar : equipos) {
            if (equipoParaModificar.getNombre().equalsIgnoreCase(equipoSeleccionado)) {
                if (equipoParaModificar.getNombrePresidente().equals(nombreNuevoPresidente)) {
                    System.out.println("El nombre del presidente es el mismo que habia antes");
                } else if (equipoParaModificar.getNombrePresidente() == null || equipoParaModificar.getNombrePresidente().isEmpty()) {
                    System.out.println("Has asignado el presidente a un equipo que no tenía");
                }
                equipoParaModificar.modificarPresidente(nombreNuevoPresidente);
                System.out.println(equipoParaModificar.getNombrePresidente());
            }
        }
    }

    private static void darDeBajaEquipo(ArrayList<Equipos> equipos, String equipoSeleccionado) {
        Scanner sc = new Scanner(System.in);
        String nombreEquipo;

        System.out.println("Escribe el nombre del equipo para borrarlo:");
        nombreEquipo = sc.nextLine();
        if (nombreEquipo.equals(equipoSeleccionado)) {
            for (Equipos equipoParaBorrar : equipos) {
                if (equipoParaBorrar.getNombre().equalsIgnoreCase(nombreEquipo)) {
                    equipos.remove(equipoParaBorrar);
                }
            }
        } else {
            System.out.println("Introduce el nombre del equipo");
        }
    }

    private static void transferirJugador(ArrayList<Equipos> equipos, String equipoSeleccionado) {
        Scanner sc = new Scanner(System.in);
        String jugador, equipo;
        Jugadores jugadorSeleccionado = null;
        Equipos equipoParaTransferir = null;
        Equipos equipoParaBorrarJugador;
        boolean exit = false;
        ArrayList <Jugadores> jugadoresDelEquipo;

        for (Equipos equipoParaModificar : equipos) {
            if (equipoParaModificar.getNombre().equalsIgnoreCase(equipoSeleccionado)) {
                equipoParaBorrarJugador = equipoParaModificar;
                jugadoresDelEquipo = equipoParaModificar.getJugadores();

                do {
                    System.out.println("Que jugador quieres transferir?");
                    jugador = sc.nextLine();

                    for (int i = 0; i < jugadoresDelEquipo.size(); i++) {
                        Jugadores jugadorEncontrado = jugadoresDelEquipo.get(i);
                        for (int j = 0; j < jugadoresDelEquipo.size(); j++) {
                            if (jugador.equalsIgnoreCase(jugadorEncontrado.getNombre())) {
                                jugadorSeleccionado = jugadorEncontrado;
                                exit = true;
                            }
                        }
                    }
                    if (jugadorSeleccionado == null){
                        System.out.println("No se ha encontrado el Jugador, por favor escribelo de nuevo:");
                    }
                }while(!exit);

                exit = false;

                do {
                    System.out.println("A que equipo lo quieres transferir?");
                    equipo = sc.nextLine();

                    for (Equipos equipoEncontrado : equipos) {
                        if (equipoEncontrado.getNombre().equalsIgnoreCase(equipo)) {
                            equipoParaTransferir = equipoEncontrado;
                            exit = true;
                        }
                    }
                    if (equipoParaTransferir == null){
                        System.out.println("No se ha encontrado el Jugador, por favor escribelo de nuevo:");
                    }
                }while(!exit);

                    jugadoresDelEquipo = equipoParaBorrarJugador.getJugadores();

                for (int i = 0; i < jugadoresDelEquipo.size(); i++) {
                    Jugadores jugadorParaBorrar = jugadoresDelEquipo.get(i);
                    if(jugadorParaBorrar == jugadorSeleccionado){
                        jugadoresDelEquipo.remove(jugadorParaBorrar);
                    }
                }

                System.out.println(jugadoresDelEquipo);

                if (jugadorSeleccionado.esTransferible()){
                    jugadorSeleccionado.transferirAEquipo(equipoParaTransferir, jugadorSeleccionado);
                }
            }
        }
    }

    private static void generarJugadoresYEntrenadoresDisponibles(ArrayList<Personas> personas, ArrayList<Integer> contadorJugadores, ArrayList<Integer> contadorEntrnadores) {
        Jugadores j5 = new Jugadores("Gabriel", "Torres", "20/2/2000", 8, 1300, 5, 53,  80, "DEF");
        Jugadores j6 = new Jugadores("Guillermo", "Rodriguez", "18/4/2002", 9, 1300, 6, 1,  86, "POR");
        Jugadores j7 = new Jugadores("Sebastian", "Fernandez", "29/8/1999", 8, 1500, 7, 11,  90, "MED");
        Jugadores j8 = new Jugadores("Adrian", "Plazas", "1/10/2003", 7, 1400, 8, 26,  85, "DEL");
        personas.add(j5);
        personas.add(j6);
        personas.add(j7);
        personas.add(j8);
        contadorJugadores.add(1);
        contadorJugadores.add(1);
        contadorJugadores.add(1);
        contadorJugadores.add(1);
        Entrenadores en3 = new Entrenadores("Isidoro","Navarro","23/4/1981",8,1900, 3,12,false);
        Entrenadores en4 = new Entrenadores("Jack","Ramirez","1/12/1980",9,1800, 4,23,false);
        personas.add(en3);
        personas.add(en4);
        contadorEntrnadores.add(1);
        contadorEntrnadores.add(1);
    }

    private static void darDeAltaEquiposPredeterminados(ArrayList<Personas> persona, ArrayList<Equipos> equipos) {
        ArrayList<Jugadores> jugadores1 = new ArrayList<>();
        ArrayList<Jugadores> jugadores2 = new ArrayList<>();
        double puntuacionEquipo1, puntuacionEquipo2;
        double motivacionEquipo1, motivacionEquipo2;
        jugadores1.add((Jugadores)persona.get(0));
        jugadores1.add((Jugadores)persona.get(1));
        puntuacionEquipo1 = (((Jugadores) persona.get(0)).getPuntuacion() + ((Jugadores) persona.get(1)).getPuntuacion())/2;
        puntuacionEquipo2 = (((Jugadores) persona.get(2)).getPuntuacion() + ((Jugadores) persona.get(3)).getPuntuacion())/2;
        motivacionEquipo1 = ((persona.get(0)).getNivelDeMotivacion() + (persona.get(1)).getNivelDeMotivacion() + (persona.get(4)).getNivelDeMotivacion())/3;
        motivacionEquipo2 = ((persona.get(2)).getNivelDeMotivacion() + (persona.get(3)).getNivelDeMotivacion() + (persona.get(5)).getNivelDeMotivacion())/3;
        jugadores2.add((Jugadores)persona.get(2));
        jugadores2.add((Jugadores)persona.get(3));
        Equipos eq1 = new Equipos("eq1", 1965, "Valencia", "Mestalla", "Layhoon Chan", (Entrenadores) persona.get(4) ,jugadores1, 1, puntuacionEquipo1, motivacionEquipo1);
        Equipos eq2 = new Equipos("eq2", 1907, "Sevilla", "Benito Villamarín", "Ángel Haro", (Entrenadores) persona.get(5) ,jugadores2, 2, puntuacionEquipo2, motivacionEquipo2);
        equipos.add(eq1);
        equipos.add(eq2);
        persona.removeAll(persona);
    }

    private static void darDeAltaEntrenadoresPredeterminados(ArrayList<Personas> personas, ArrayList<Integer> contadorEntrnadores) {
        Entrenadores en1 = new Entrenadores("Miguel","Antunez","13/8/1985",8,1700, 1,7,false);
        Entrenadores en2 = new Entrenadores("Eduardo","Navarro","21/11/1979",7,1800, 2,32,true);
        personas.add(en1);
        personas.add(en2);
        contadorEntrnadores.add(1);
        contadorEntrnadores.add(1);
    }

    private static void darDeAltaJugadoresPredeterminados(ArrayList<Personas> personas, ArrayList<Integer> contadorJugadores) {
        Jugadores j1 = new Jugadores("Manolo", "Fernandez", "25/2/2002", 7, 1500, 1, 9,  85, "DEL");
        Jugadores j2 = new Jugadores("Antonio", "Garcia", "14/6/2003", 8, 1400, 2, 15,  79, "DEF");
        Jugadores j3 = new Jugadores("Juan", "Perez", "17/7/2002", 6, 1400, 3, 5,  80, "DEF");
        Jugadores j4 = new Jugadores("Gustavo", "Lopez", "2/9/2004", 9, 1300, 4, 26,  90, "POR");
        personas.add(j1);
        personas.add(j2);
        personas.add(j3);
        personas.add(j4);
        contadorJugadores.add(1);
        contadorJugadores.add(1);
        contadorJugadores.add(1);
        contadorJugadores.add(1);
    }

    private static int menuDelManager() {
        Scanner sc = new Scanner(System.in);
        String seleccion;
        int opcion;
        boolean salir = false;
        do {
            System.out.println("--(Bienvenido al futbol manager)--");
            System.out.println("\t1.-Gestionar equipo");
            System.out.println("\t2.-Dar de alta equipo");
            System.out.println("\t3.-Dar de alta jugador/entrenador");
            System.out.println("\t4.-Consultar datos equipo");
            System.out.println("\t5.-Consultar datos jugador");
            System.out.println("\t6.-Disputar nueva liga");
            System.out.println("\t7.-Realizar sesión de entrenamiento");
            System.out.println("\t0.-Salir");
            seleccion = sc.nextLine();
            try {
                opcion = Integer.parseInt(seleccion);
                if (opcion >= 0 && opcion <= 11) {
                    salir = true;
                }else {
                    System.out.println("Error: Ingresa un número válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        } while (!salir);
        opcion = Integer.parseInt(seleccion);
        return opcion;
    }
}