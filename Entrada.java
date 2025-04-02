public class Entrada {
    private int idEntrada;
    private Artista artista;

    //método constructor
    public Entrada(int idEntrada, Artista artista){
        this.idEntrada = idEntrada;
        this.artista = artista;
    }
    // GETTERS
    public int getIdEntrada(){
        return idEntrada;
    }

    public Artista getArtista(){
        return artista;
    }
    // FIN GETTERS
    
    //función override para tomar el "toString" de la clase "Artista"
    @Override
    public String toString(){
        String cadena = "\nID entrada: " + idEntrada + "\nInformacion del artista:\n" + artista.toString();
        return cadena;
    }

}
