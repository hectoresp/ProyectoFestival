public class Solista extends Artista {
    private boolean camerino;
    private int tfnoManager;
    
    public Solista(String nombre, String genero, boolean headliner, int precioEntrada,int duracionConcierto, int aforo, boolean confirmado, boolean camerino, int tfnoManager){
        super(nombre, genero, headliner, precioEntrada, duracionConcierto, aforo, confirmado);
        this.camerino = camerino;
        this.tfnoManager = tfnoManager;
    }
    // GETTERS
    public boolean necesitaCamerino(){
        return camerino;
    }

    public int getTfnoManager(){
        return tfnoManager;
    }
    // FIN GETTERS

    @Override
    public String toString(){
        String cadena = super.toString() + "\nTiene camerino: " + camerino + "\nTelefono del manager: " + tfnoManager;
        return cadena;
    }
}
