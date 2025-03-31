public class Solista extends Artista {
    private boolean camerino;
    private int tfnoManager;
    
    public Solista(String nombre, String genero, boolean headliner, int precioEntrada,int duracionConcierto, int aforo, boolean confirmado, boolean camerino, int tfnoManager){
        super(nombre, genero, headliner, precioEntrada, duracionConcierto, aforo, confirmado);
        this.camerino = camerino;
        this.tfnoManager = tfnoManager;
    }
    // GETTERS
    @Override
    public boolean necesitaCamerino(){
        return camerino;
    }

    public int getTfnoManager(){
        return tfnoManager;
    }
    // FIN GETTERS

    @Override
    public String toString(){
        String cadena = super.toString() + "\n¿Tiene camerino?: " + (camerino?"Sí":"No") + "\nTelefono del manager: " + tfnoManager;
        return cadena;
    }
    @Override
    public boolean necesitaStand() {
        return false;
    }
}
