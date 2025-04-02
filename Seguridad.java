public class Seguridad implements Interfaz{
    private String nombre;
    private int cobroPorGuarda=PRECIO_GUARD;

    public Seguridad(String nombre){
        this.nombre = nombre;
    }

    // GETTERS
    public String getNombre(){
        return nombre;
    }
    public int getCobro(){
        return cobroPorGuarda;
    }
    // FIN GETTERS
    
}
