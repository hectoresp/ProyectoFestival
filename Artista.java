public abstract class Artista {
    
    protected String nombre;
    protected String genero;
    protected boolean headliner;
    protected int precioEntrada;
    protected int duracionConcierto;
    protected int aforo;
    protected boolean confirmado;

    //método constructor (al ser una clase abstracta, realmente tendremos grupos y solistas, no tendremos un objeto "Artista" como tal)
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


    //función @override para poder acceder y añadir elementos a este método desde otras clases. Para ello se debe poner en los otros métodos
    //el mismo nombre acompañado de la función "super.toString".
    @Override
    public String toString(){
        String cadena = "Nombre: " + nombre + "\nGenero: " + genero + "\n¿Es headliner?: " + (headliner?"Sí":"No") + "\nPrecio de entrada: " + precioEntrada + "\nDuracion del concierto: " + duracionConcierto + "\nAforo: " + aforo + "\nConfirmado: " + (confirmado?"Sí":"No");
        return cadena;
    }
}