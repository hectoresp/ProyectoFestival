public class Asistente implements Interfaz{

    protected String nombre;
    protected String DNI;
    protected String tarjetaCredito;
    protected boolean haAsistidoAntes;
    protected Entrada[] entradas;
    protected int nEntradas;

    public Asistente(String nombre, String DNI, String tarjetaCredito, boolean haAsistidoAntes){
        this.nombre = nombre;
        this.DNI = DNI;
        this.tarjetaCredito = tarjetaCredito;
        this.haAsistidoAntes = haAsistidoAntes;
        entradas = new Entrada[7];
        nEntradas = 0;
    }
    // GETTERS
    public String getNombre(){
        return nombre;
    }
    public String getDNI(){
        return DNI;
    }
    public String getTarjetaCredito(){
        return tarjetaCredito;
    }
    public boolean haAsistidoAntes(){
        return haAsistidoAntes;
    }
    public Entrada[] getEntradas(){
        return entradas;
    }
    public int getNEntradas(){
        return nEntradas;
    }
    // FIN GETTERS

    // Añade una entrada a la lista de entradas del asistente
    public void addEntrada(Entrada e){
        entradas[nEntradas] = e;
        nEntradas++;
    }

    // Devuelve una cadena con la informacion de las entradas compradas
    public String listarEntradas(){
        String cadena = "";

        for(int i = 0; i < nEntradas; i++){
            cadena += entradas[i].toString() + "\n-------------\n";
        }
        return cadena;
    }

    // Calcula el porcentaje de descuento aplicable
    public double calcularDescuento(){
        double descuento = 1;
        if(haAsistidoAntes){
            descuento -= DESC_RECURRENTE;
        }
        return descuento;
    }

}