import java.io.*;
import java.util.*;

public class Principal {

    final static Scanner TECLADO = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Artista[] artistas;
        artistas = leerArtistas("Artistas.txt");
        Asistente[] asistentes;
        asistentes = leerAsistentes("Asistentes.txt");

        Festival f = new Festival("JAVASTIC-FEST", "Ciudad Real", artistas, asistentes);
        Seguridad s = new Seguridad("GSyA SL", 250);

        ejecutarMenu(f, s);

    }

    public static void ejecutarMenu(Festival f, Seguridad s) {
        boolean continuarPrograma = true;
        boolean usuarioRegistrado = false;
        Asistente a = null;

        while (continuarPrograma) {
            if (usuarioRegistrado) {
                System.out.println(f.saludo(a));
                
            }
            System.out.println(
                    "\nElija una opcion:\n1. Mostrar artistas programados.\n2. Calcular precio de la seguridad.\n3. Consultar precio de una entrada.\n4. Simular compra.\n5. Comprar entrada.\n6. Mostrar entradas compradas.\n7. Iniciar sesion.\n8. Registrarse\n9. Salir.");
            System.out.print("Opción: ");
            int opcion = TECLADO.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("A continuacion listamos todos los artistas.");
                    System.out.println(f.listarArtistas());
                    break;
                case 2:
                    int numGuardas = calcularNumGuardas(f.calcularTotalAsistentes(), f.getTotalStands(),
                            f.getTotalCamerinos());
                    System.out.printf("El coste por la seguridad con %d guardas sera: %d$\n", numGuardas,
                            f.precioSeguridad(s, numGuardas));
                    break;
                case 3:
                    if (!usuarioRegistrado) {
                        a=pedirRegristro(f);
                        usuarioRegistrado = true;
                    } else {
                        System.out.print("Introduzca el nombre exacto del artista a consultar: ");
                        String nombre = TECLADO.next();
                        System.out.println(f.cadenaPrecioEntrada(nombre, a));
                    }
                    break;
                case 4:
                    if (!usuarioRegistrado) {
                        a=pedirRegristro(f);
                        usuarioRegistrado = true;
                    } else {
                        simularCompra(f, a);
                    }
                    break;
                case 5:
                    if (!usuarioRegistrado) {
                        a=pedirRegristro(f);
                        usuarioRegistrado = true;
                    } else {
                        if(!f.puedeComprarEntrada(a)){
                            System.out.println("No puede comprar mas de 7 entradas.");
                        } else {
                            comprarEntrada(f, a);
                        }
                    }
                    break;
                case 6:
                    if (!usuarioRegistrado) {
                        a=pedirRegristro(f);
                        usuarioRegistrado = true;
                    } else {
                        System.out.println(a.listarEntradas());
                    }
                    break;
                case 7:
                    a = iniciarSesion(f);
                    usuarioRegistrado = true;
                    break;
                case 8:
                    a = registrarse();
                    f.addAsistente(a);
                    usuarioRegistrado = true;
                    break;
                case 9:
                    continuarPrograma = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    // Pide al usuario todos los datos del registro
    public static Asistente registrarse() {
        System.out.println("Introduzca su DNI: ");
        String DNI = TECLADO.next();

        System.out.println("Introduzca su nombre: ");
        String nombre = TECLADO.next();

        System.out.println("Introduzca su numero de tarjeta de credito: ");
        String tarjeta = TECLADO.next();

        System.out.println("¿Ha asistido antes a nuestro festival? (true/false)");
        boolean haAsistidoAntes = TECLADO.nextBoolean();

        while (haAsistidoAntes != true && haAsistidoAntes != false) {
            System.out.println("Por favor, introduzca un valor válido (true/false)");
            haAsistidoAntes = TECLADO.nextBoolean();
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
            a = f.getAsistentes()[idUsuario];
            System.out.println("Inicio de sesion correcto.");
        }
        return a;
    }

    public static Asistente pedirRegristro(Festival f){
        Asistente a = null;
        System.out.println("No se ha encontrado al usuario. Por favor, registrese o inicie sesión a continuación.");
        System.out.println("1. Registrarse\n2. Iniciar sesión");
        System.out.print("Opción: ");
        int opcion = TECLADO.nextInt();
        switch (opcion) {
            case 1:
                a = registrarse();
                break;
            case 2:
                a = iniciarSesion(f);
                break;
        
            default:
            System.out.println("Opción no válida. Se procede a volver al menú principal.");
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
        int idArtista = TECLADO.nextInt();

        while (idArtista < 0 || idArtista >= f.getNArtistas()) {
            System.out.println("El número de artista introducido no es válido. Introduzca un número válido.");
            idArtista = TECLADO.nextInt();
        }

        f.comprarEntrada(idArtista, a);

        String nombreArtista = f.encontrarNombreArtista(idArtista);
        int precioEntrada = f.getPrecioEntrada(nombreArtista);
        double precioFinal = f.calcularPrecioFinal(precioEntrada, a);
        System.out.println("El precio por la entrada sera: " + precioFinal + "$.");
    }

    // Pide al usuario datos para calcular el numero total de guardas
    public static int calcularNumGuardas(int totalAsistentes, int totalStands, int totalCamerinos) {
        System.out.println("El número total de asistentes (aforos completos) es: " + totalAsistentes);

        System.out.print("Introduzca cada cuantos asistentes quiere un guarda: ");
        int asistentesPorGuarda = TECLADO.nextInt();

        System.out.print("Introduzca cuantos guardas quiere por stand de merchandising: ");
        int guardasPorStand = TECLADO.nextInt();

        System.out.print("Introduzca cuantos guardas quiere por camerino: ");
        int guardasPorCamerino = TECLADO.nextInt();

        int numGuardas = totalAsistentes / asistentesPorGuarda + guardasPorStand * totalStands
                + guardasPorCamerino * totalCamerinos;
        return numGuardas;
    }

    // Simula la compra de todas las entradas para los headliners, comprando 1 camiesta por stand 
    public static void simularCompra(Festival f, Asistente a) {
        int precioEntradas = 0;
        int precioCamisetas = 0;

        System.out.println("Simulacion de compra para todos los headliners.");
        Artista[] artistas = f.getArtistas();
        for (int i = 0; i < f.getNArtistas(); i++) {
            if (artistas[i].esHeadliner()) {
                precioEntradas += artistas[i].getPrecio();
            }

            if (artistas[i] instanceof Grupo && ((Grupo) artistas[i]).necesitaStand()) {
                precioCamisetas += 25;
            }
        }
        double descuentoEntradas = a.calcularDescuento();
        double descuentoCamisetas = 1;

        if (a.haAsistidoAntes()) {
            descuentoCamisetas -= 0.15;
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
