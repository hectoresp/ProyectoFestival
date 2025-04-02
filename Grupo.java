public class Grupo extends Artista {
    private int numIntegrantes;
    private boolean stand;

    //método constructor
    public Grupo(String nombre, String genero, boolean headliner, int precioEntrada,int duracionConcierto, int aforo, boolean confirmado, int numIntegrantes, boolean stand){
        super(nombre, genero, headliner, precioEntrada, duracionConcierto, aforo, confirmado);
        this.numIntegrantes = numIntegrantes;
        this.stand = stand;
    }

    // GETTERS
    public int getNumIntegrantes(){
        return numIntegrantes;
    }

    //ampliación de la información del grupo usando "@override" para sobreescribir en los métodos de la clase abstracta "Artista".
    @Override
    public boolean necesitaStand(){
        return stand;
    }
    // FIN GETTERS

    @Override
    public String toString(){
        String cadena = super.toString() + "\nNumero de integrantes: " + numIntegrantes + "\n¿Tiene stand?: " + (stand?"Sí":"No");
        return cadena;
    }

    @Override
    public boolean necesitaCamerino() {
        return false;
    }
}
