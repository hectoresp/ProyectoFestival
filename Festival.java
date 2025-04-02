public class Festival implements Interfaz{

    private String nombre;
    private String ciudad;
    private Artista[] artistas;
    private int nArtistas;
    private Asistente[] asistentes;
    private int nAsistentes;
    private int totalStands, totalCamerinos;

    //método constructor del festival
    public Festival(String nombre, String ciudad, Artista[] artistas, Asistente[] asistentes) {
        this.nombre = nombre;
        this.ciudad = ciudad;

        //creamos una matriz con el número máximo de artistas (en este caso 20)
        this.artistas = new Artista[20];
        boolean hayArtistas = true;
        nArtistas = 0;

        totalStands = 0;
        totalCamerinos = 0;

        //con este bucle observamos cuántos stands y camerinos son necesarios en total
        while (hayArtistas) {
            if (artistas[nArtistas] != null) {
                this.artistas[nArtistas] = artistas[nArtistas];
                if (artistas[nArtistas] instanceof Grupo && ((Grupo) artistas[nArtistas]).necesitaStand()) {
                    totalStands++;
                } else if (artistas[nArtistas] instanceof Solista && ((Solista) artistas[nArtistas]).necesitaCamerino()) {
                    totalCamerinos++;
                }
                nArtistas++;
            } else {
                hayArtistas = false;
            }
        }

        // for(int i = 0; i < artistas.length && hayArtistas; i++){
        //     if(artistas[i] != null){
        //         this.artistas[i] = artistas[i];
        //         nArtistas++;
        //     } else {
        //         hayArtistas = false;
        //     }
        // }
        this.asistentes = new Asistente[50];
        boolean hayAsistentes = true;
        nAsistentes = 0;

        while (hayAsistentes) {
            if (asistentes[nAsistentes] != null) {
                this.asistentes[nAsistentes] = asistentes[nAsistentes];
                nAsistentes++;
            } else {
                hayAsistentes = false;
            }
        }

        // for(int i = 0; i < asistentes.length && hayAsistentes; i++){
        //     if(asistentes[i] != null){
        //         this.asistentes[i] = asistentes[i];
        //         nAsistentes++;
        //     } else {
        //         hayAsistentes = false;
        //     }
        // }
    }

    // GETTERS
    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Artista[] getArtistas() {
        return artistas;
    }

    public void addArtista(Artista artista) {
        artistas[nArtistas] = artista;
    }

    public Asistente[] getAsistentes() {
        return asistentes;
    }

    public void addAsistente(Asistente asistente) {
        asistentes[nAsistentes] = asistente;
    }

    public int getNArtistas() {
        return nArtistas;
    }

    public int getNAsistentes() {
        return nAsistentes;
    }

    public int getTotalStands() {
        return totalStands;
    }

    public int getTotalCamerinos() {
        return totalCamerinos;
    }
    // FIN GETTERS

    // Calcula el precio de la seguridad
    public int precioSeguridad(Seguridad empresaSeguridad, int numGuardas) {
        int cobroPorGuarda = empresaSeguridad.getCobro();
        return cobroPorGuarda * numGuardas;
    }

    // Calcula el total de asistentes si se llenasen los aforos
    public int calcularTotalAsistentes() {
        int totalAsistentes = 0;
        for (int i = 0; i < nArtistas; i++) {
            totalAsistentes += artistas[i].getAforo();
        }
        return totalAsistentes * 1000;
    }

    // Crea una cadena de texto con todos los artistas y su información
    public String listarArtistas() {
        String cadena = "";

        for (int i = 0; i < nArtistas; i++) {
            cadena += artistas[i].toString() + "\n-------------\n";
        }

        return cadena;
    }

    // Crea una cadena de texto con los nombres de los artistas
    public String listarNombresArtistas(){
        String cadena = "";

        for(int i = 0; i < nArtistas; i++){
            cadena += i + ". " + artistas[i].getNombre() + "\n";
        }
        return cadena;
    }
    
    // Dado el nombre de un artista, obtiene el precio de su entrada
    public int getPrecioEntrada(String nombre) {
        int precio;

        int idArtista = encontrarIdArtista(nombre);

        if (idArtista == -1) {
            precio = -1;
        } else {
            precio = artistas[idArtista].getPrecio();
        }
        return precio;
    }

    // Dado un precio base, resta el descuento
    public double calcularPrecioFinal(int precio, Asistente a) {
        double precioFinal = precio;

        double descuento = a.calcularDescuento();

        precioFinal *= descuento;
        return precioFinal;
    }

    // Crea una cadena de texto con la información del precio de una entrada
    public String cadenaPrecioEntrada(String nombre, Asistente a) {
        String cadena = "";

        int precio = getPrecioEntrada(nombre);

        if (precio == -1) {
            cadena = "No se ha encontrado al artista " + nombre;
        } else {
            double descuento = a.calcularDescuento();
            double precioFinal = calcularPrecioFinal(precio, a);
            cadena = "El precio para la entrada del concierto de " + nombre + " SIN DESCUENTO es: " + precio + "$\nEl precio final con un descuento del " + (1-descuento)*100 + "% es: " + precioFinal + "$.";
        }
        return cadena;
    }

    // Dado el nombre de un artista, encuentra su índice para el vector de artistas
    public int encontrarIdArtista(String nombre) {
        boolean artistaEncontrado = false;
        int idArtista = -1;

        for (int i = 0; i < nArtistas && !artistaEncontrado; i++) {
            if (artistas[i].getNombre().equalsIgnoreCase(nombre)) {
                idArtista = i;
                artistaEncontrado = true;
            }
        }
        return idArtista;
    }

    // Dado el id de un artista, encuentra su nombre
    public String encontrarNombreArtista(int idArtista){
        String nombre = artistas[idArtista].getNombre();
        return nombre;
    }

    // Dado el DNI de un asistente/usuario, encuentra su índice para el vector de asistentes
    public int encontrarIdUsuario(String DNI) {
        int idUsuario = -1;
        boolean usuarioEncontrado = false;

        for (int i = 0; i < nAsistentes && !usuarioEncontrado; i++) {
            if (asistentes[i].getDNI().equals(DNI)) {
                idUsuario = i;
                usuarioEncontrado = true;
            }
        }
        return idUsuario;
    }

    // Realiza la compra de una entrada para un artista de un asistente
    public void comprarEntrada(int idArtista, Asistente a){
        Entrada e = new Entrada(a.getNEntradas(), artistas[idArtista]);
        a.addEntrada(e);
    }

    // Comprueba si el asistente ha comprado ya 7 entradas
    public boolean puedeComprarEntrada(Asistente a){
        boolean puedeComprar = false;
        if(a.getNEntradas() != MAX_NUM_ENTRADAS){
            puedeComprar = true;
        }
        return puedeComprar;
    }

    // Saluda al usuario registrado
    public String saludo(Asistente a){
        return "Hola, " + a.getNombre();
    }
}
