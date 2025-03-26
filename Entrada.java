public class Entrada {
    private int idEntrada;
    private Artista artista;

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
    
    public String toString(){
        String cadena = "\nID entrada: " + idEntrada + "\nInformacion del artista:\n" + artista.toString();
        return cadena;
    }

}
