public class AsistenteVIP extends Asistente{
    private int numTarjetaVIP;

    //método constructor
    public AsistenteVIP(String nombre, String DNI, String tarjetaCredito, boolean haAsistidoAntes, int numTarjetaVIP){
        super(nombre, DNI, tarjetaCredito, haAsistidoAntes);
        this.numTarjetaVIP = numTarjetaVIP;
    }

    // GETTER
    public int getNumTarjetaVIP(){
        return numTarjetaVIP;
    }

    // Devuelve el valor del descuento para asistentes VIP
    //con "@override" tomaremos el método de la clase padre "Asistente" y en el caso de que sea VIP, el método "calcularDescuento" 
    //se ampliará teniendo también en cuenta el descuento por ser VIP
    @Override
    public double calcularDescuento(){
        double descuento = super.calcularDescuento() - DESC_VIP;
        return descuento;
    }
    @Override
    public boolean esVIP(){
        return true;
    }
}
