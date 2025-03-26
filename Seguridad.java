public class Seguridad {
    private String nombre;
    private int cobroPorGuarda;

    public Seguridad(String nombre, int cobroPorGuarda){
        this.nombre = nombre;
        this.cobroPorGuarda = cobroPorGuarda;
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
