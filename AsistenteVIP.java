public class AsistenteVIP extends Asistente{
    private int numTarjetaVIP;

    public AsistenteVIP(String nombre, String DNI, String tarjetaCredito, boolean haAsistidoAntes, int numTarjetaVIP){
        super(nombre, DNI, tarjetaCredito, haAsistidoAntes);
        this.numTarjetaVIP = numTarjetaVIP;
    }

    // GETTER
    public int getNumTarjetaVIP(){
        return numTarjetaVIP;
    }

    @Override
    public double calcularDescuento(){
        double descuento = super.calcularDescuento() - 0.15;
        return descuento;
    }
}
