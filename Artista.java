public abstract class Artista {
    
    protected String nombre;
    protected String genero;
    protected boolean headliner;
    protected int precioEntrada;
    protected int duracionConcierto;
    protected int aforo;
    protected boolean confirmado;

    public Artista(String nombre, String genero, boolean headliner, int precioEntrada,int duracionConcierto, int aforo, boolean confirmado){
        this.nombre = nombre;
        this.genero = genero;
        this.headliner = headliner;
        this.precioEntrada = precioEntrada;
        this.duracionConcierto = duracionConcierto;
        this.aforo = aforo;
        this.confirmado = confirmado;
    }

    // GETTERS
    public String getNombre(){
        return nombre;
    }
    public String getGenero(){
        return genero;
    }
    public boolean esHeadliner(){
        return headliner;
    }
    public int getPrecio(){
        return precioEntrada;
    }
    public int getDuracionConcierto(){
        return duracionConcierto;
    }
    public int getAforo(){
        return aforo;
    }
    public boolean estaConfirmado(){
        return confirmado;
    }
    // FIN GETTERS
    
    // Métodos abstractos (polimorfismo)
    public abstract boolean necesitaCamerino();
    public abstract boolean necesitaStand();

    @Override
    public String toString(){
        String cadena = "Nombre: " + nombre + "\nGenero: " + genero + "\nEs headliner: " + headliner + "\nPrecio de entrada: " + precioEntrada + "\nDuracion del concierto: " + duracionConcierto + "\nAforo: " + aforo + "\nConfirmado: " + confirmado;
        return cadena;
    }
}