import java.io.*;
import java.util.*;

public class Principal implements Interfaz {

    final static Scanner TECLADO = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Artista[] artistas;
        try {
            artistas = leerArtistas("Artistas.txt");
        } catch (Exception e) {
            artistas = null;
            System.out.println("Error leyendo el archivo 'Artistas.txt'.\n");
        }
        Asistente[] asistentes;
        try {
            asistentes = leerAsistentes("Asistentes.txt");
        } catch (Exception e) {
            asistentes = null;
            System.out.println("Error leyendo el archivo 'Asistentes.txt'.\n");
        }

        Festival f = new Festival("JAVASTIC-FEST", "Ciudad Real", artistas, asistentes);
        Seguridad s = new Seguridad("GSyA SL");

        ejecutarMenu(f, s);

        System.out.println("Gracias por su confianza.");

    }

    // Menú principal
    public static void ejecutarMenu(Festival f, Seguridad s) {
        boolean continuarPrograma = true;
        boolean usuarioRegistrado = false;
        Asistente a = null;

        while (continuarPrograma) {
            if (a != null) {
                usuarioRegistrado = true;
            } else {
                usuarioRegistrado = false;
            }

            if (usuarioRegistrado) {
                System.out.println(f.saludo(a));
            }
            System.out.println("\nElija una opcion:\n1. Mostrar artistas programados.\n2. Calcular precio de la seguridad.\n3. Consultar precio de una entrada.\n4. Simular compra.\n5. Comprar entrada.\n6. Mostrar entradas compradas.\n7. Mostrar artistas con stand de merch (solo para asistentes VIP)\n8. Iniciar sesion.\n9. Registrarse\n10. Cerrar sesión\n11. Salir.");
            System.out.print("Opción: ");
            int opcion = numIntCorrecto();

            switch (opcion) {
                case 1: // Listar artistas
                    System.out.println("A continuacion listamos todos los artistas.");
                    System.out.println(f.listarArtistas());
                    break;
                case 2: // Calcular precio de seguridad
                    int numGuardas = f.calcularNumGuardas(f.calcularTotalAsistentes(), f.getTotalStands(),
                            f.getTotalCamerinos());
                    System.out.printf("El coste por la seguridad con %d guardas sera: %d$\n", numGuardas,
                            f.precioSeguridad(s));
                    break;
                case 3: // Consulta el precio de la entrada de un artista
                    if (!usuarioRegistrado) {
                        a = pedirRegistro(f);
                        usuarioRegistrado = true;
                    } else {
                        System.out.print("Introduzca el nombre exacto del artista a consultar: ");
                        String nombre = TECLADO.next();
                        System.out.println(f.cadenaPrecioEntrada(nombre, a));
                    }
                    break;
                case 4: // Simula la compra para todos los headliners + camisetas
                    if (!usuarioRegistrado) {
                        a = pedirRegistro(f);
                    } else {
                        simularCompra(f, a);
                    }
                    break;
                case 5: // Comprar entrada
                    if (!usuarioRegistrado) {
                        a = pedirRegistro(f);
                    } else {
                        if (!f.puedeComprarEntrada(a)) {
                            System.out.println("No puede comprar mas de 7 entradas.");
                        } else {
                            comprarEntrada(f, a);
                        }
                    }
                    break;
                case 6: // Listar entradas compradas
                    if (!usuarioRegistrado) {
                        a = pedirRegistro(f);
                    } else {
                        System.out.println(a.listarEntradas());
                    }
                    break;
                case 7: // primero vemos si el usuario está registrado y luego si es VIP, mostrando 2
                        // mensajes diferentes para cada opción
                    if (!usuarioRegistrado) {
                        a = pedirRegistro(f);
                    } else if (!a.esVIP()) {
                        System.out.println("Debe ser asistente VIP para acceder a esta opción. Se le devolverá al menú principal.");
                    } else
                        System.out.println(f.leerArtistasConStand());
                    break;
                case 8: // Iniciar sesión
                    a = iniciarSesion(f);
                    break;
                case 9: // Registro
                    a = registrarse();
                    f.addAsistente(a);
                    break;
                case 10: // Cerrar sesión
                    System.out.println("¡¡Adiós, " + a.getNombre() + "!!\nCerrando sesión...");
                    a = null;
                    break;
                case 11: // Terminar programa
                    continuarPrograma = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public static int numIntCorrecto() {
        int numMenu = 0;
        boolean numCorrecto = false;
        while (!numCorrecto) {
            try {
                numMenu = TECLADO.nextInt();
                numCorrecto = true;
            } catch (Exception e) {
                System.out.println("Los caracteres introducidos no son válidos, por favor intoduzca un número dentro de las opciones del menú:");
                TECLADO.nextLine();

            }
        }
        return numMenu;
    }

    // Pide al usuario todos los datos del registro
    public static Asistente registrarse() {
        System.out.print("Introduzca su DNI: ");
        String DNI = TECLADO.next();

        System.out.print("Introduzca su nombre: ");
        String nombre = TECLADO.next();

        System.out.print("Introduzca su numero de tarjeta de credito: ");
        String tarjeta = TECLADO.next();

        System.out.print("¿Ha asistido antes a nuestro festival? (si/no)");
        String haAsistidoAntesCadena = TECLADO.next();

        while (!haAsistidoAntesCadena.equalsIgnoreCase("si") && !haAsistidoAntesCadena.equalsIgnoreCase("no")) {
            System.out.println("Por favor, introduzca un valor válido (si/no)");
            haAsistidoAntesCadena = TECLADO.next();
        }

        boolean haAsistidoAntes = false;
        if (haAsistidoAntesCadena.equalsIgnoreCase("si")) {
            haAsistidoAntes = true;
        }

        Asistente a = new Asistente(nombre, DNI, tarjeta, haAsistidoAntes);
        return a;
    }

    // Pide al usuario el DNI y comprueba que exista dicho DNI
    public static Asistente iniciarSesion(Festival f) {
        Asistente a = null;

        System.out.print("Introduzca su DNI: ");
        String DNI = TECLADO.next();

        int idUsuario = f.encontrarIdUsuario(DNI);
        if (idUsuario == -1) {
            System.out.println("No se ha encontrado al usuario. Por favor, registrese.");
        } else {
            a = f.getAsistente(idUsuario);
            System.out.println("Inicio de sesion correcto.");
        }
        return a;
    }

    // Pide al usuario registrarse o iniciar sesión
    public static Asistente pedirRegistro(Festival f) {
        Asistente a = null;
        String cadena = "No se ha encontrado al usuario. Por favor, registrese o inicie sesión a continuación.\n1. Registrarse\n2. Iniciar sesión\n3. Volver al menú principal\nOpción: ";
        System.out.println(cadena);

        int opcion = numIntCorrecto();
        switch (opcion) {
            case 1:
                a = registrarse();
                break;
            case 2:
                a = iniciarSesion(f);
                break;

            default:
                System.out.println("Volviendo al menú principal.");
                break;
        }
        return a;
    }

    // Pide al usuario un artista para comprar la entrada y realiza la compra
    public static void comprarEntrada(Festival f, Asistente a) {
        System.out.println("Introduzca a continuación para qué artistas quieres comprar una entrada.");
        System.out.println("(Elija el número de artista que quieras comprar una entrada)");
        System.out.println(f.listarNombresArtistas());
        System.out.print("Artista: ");
        int idArtista = numIntCorrecto();

        while (idArtista < 0 || idArtista >= f.getNArtistas()) {
            System.out.println("El número de artista introducido no es válido. Introduzca un número válido.");
            idArtista = numIntCorrecto();
        }

        f.comprarEntrada(idArtista, a);

        String nombreArtista = f.encontrarNombreArtista(idArtista);
        int precioEntrada = f.getPrecioEntrada(nombreArtista);
        double precioFinal = f.calcularPrecioFinal(precioEntrada, a);
        System.out.println("El precio por la entrada sera: " + precioFinal + "$.");
    }

    // Pide al usuario datos para calcular el numero total de guardas
    // Simula la compra de todas las entradas para los headliners, comprando 1
    // camiseta por stand disponible
    public static void simularCompra(Festival f, Asistente a) {
        int precioEntradas = 0;
        int precioCamisetas = 0;

        System.out.println("Simulacion de compra para todos los headliners.");
        Artista[] artistas = f.getArtistas();
        for (int i = 0; i < f.getNArtistas(); i++) {
            if (artistas[i].esHeadliner()) {
                precioEntradas += artistas[i].getPrecio();
            }

            if (artistas[i].necesitaStand()) {
                precioCamisetas += PRECIO_CAMI;
            }
        }
        double descuentoEntradas = a.calcularDescuento();
        double descuentoCamisetas = 1;

        // calculamos el precio total de la simulación de compra aplicando todos los
        // descuentos si los hay
        if (a.haAsistidoAntes()) {
            descuentoCamisetas -= DESC_CAMI_RECURRENTE;
        }
        double precioTotal = precioEntradas * descuentoEntradas + precioCamisetas * descuentoCamisetas;
        System.out.println("El precio por comprar las entradas es: " + precioEntradas * descuentoEntradas);
        System.out.println("El precio por comprar las camisetas es: " + precioCamisetas * descuentoCamisetas);
        System.out.println("TOTAL: " + precioTotal);
    }

    // Lectura asistentes
    public static Asistente[] leerAsistentes(String cadena) throws IOException {
        Asistente[] asistentes = new Asistente[50];
        File f = new File(cadena);
        Scanner nombre_f = new Scanner(f);

        String nombre, dni, tarjeta;
        boolean frecuente, vip;
        int tarjetaVIP;

        // utilizamos este bucle para mostrar en pantalla todos los asistentes con sus
        // datos correspondientes
        int i = 0;
        while (nombre_f.hasNext()) {
            nombre = nombre_f.next();
            dni = nombre_f.next();
            tarjeta = nombre_f.next();
            frecuente = nombre_f.nextBoolean();
            vip = nombre_f.nextBoolean();
            if (vip) {
                tarjetaVIP = nombre_f.nextInt();
                asistentes[i] = new AsistenteVIP(nombre, dni, tarjeta, frecuente, tarjetaVIP);
            } else {
                asistentes[i] = new Asistente(nombre, dni, tarjeta, frecuente);
            }
            i++;
        }
        return asistentes;
    }

    // Lectura artistas
    public static Artista[] leerArtistas(String ruta) throws IOException {
        Artista[] artistas = new Artista[20];

        File f = new File(ruta);
        Scanner leerFichero = new Scanner(f);
        char tipoArtista;
        String nombre, genero;
        boolean headliner, camerinoRequerido, stand, confirmado;
        int precioEntrada, duracionActuacion, aforo, tfnoManager, cantidadIntegrantes;

        // recorremos el fichero y vamos guardando los datos de los artistas
        int i = 0;
        while (leerFichero.hasNext()) {
            tipoArtista = leerFichero.next().charAt(0);
            nombre = leerFichero.next();
            genero = leerFichero.next();
            headliner = leerFichero.nextBoolean();
            precioEntrada = leerFichero.nextInt();
            duracionActuacion = leerFichero.nextInt();
            aforo = leerFichero.nextInt();
            confirmado = leerFichero.nextBoolean();

            // diferenciamos entre grupo y solista y guardamos los datos diferenciales
            if (tipoArtista == 'g') {
                cantidadIntegrantes = leerFichero.nextInt();
                stand = leerFichero.nextBoolean();
                artistas[i] = new Grupo(nombre, genero, headliner, precioEntrada, duracionActuacion, aforo, confirmado,
                        cantidadIntegrantes, stand);

            } else {
                camerinoRequerido = leerFichero.nextBoolean();
                tfnoManager = leerFichero.nextInt();
                artistas[i] = new Solista(nombre, genero, headliner, precioEntrada, duracionActuacion, aforo,
                        confirmado, camerinoRequerido, tfnoManager);
            }
            i++;
        }
        return artistas;
    }

}