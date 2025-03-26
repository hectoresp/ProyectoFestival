public class Grupo extends Artista {
    private int numIntegrantes;
    private boolean stand;

    public Grupo(String nombre, String genero, boolean headliner, int precioEntrada,int duracionConcierto, int aforo, boolean confirmado, int numIntegrantes, boolean stand){
        super(nombre, genero, headliner, precioEntrada, duracionConcierto, aforo, confirmado);
        this.numIntegrantes = numIntegrantes;
        this.stand = stand;
    }

    // GETTERS
    public int getNumIntegrantes(){
        return numIntegrantes;
    }
    public boolean necesitaStand(){
        return stand;
    }
    // FIN GETTERS

    @Override
    public String toString(){
        String cadena = super.toString() + "\nNumero de integrantes: " + numIntegrantes + "\nTiene stand: " + stand;
        return cadena;
    }
}
