package modelos;

public class Conversor extends Moeda {
    private double valorDesejado;

    public Conversor(Moeda moeda) {
        super(moeda.getNomeMoeda(),moeda.getValorParaConverterMoeda(), moeda.getNomeDaMoedaConvertida());
    }

    public double getValorDesejado() {
        return valorDesejado;
    }

    public void setValorDesejado(double valorDesejado) {
        this.valorDesejado = valorDesejado;
    }

    public double getConverter() {
        double resultado = valorDesejado * getValorParaConverterMoeda();
        return Math.round(resultado * 100.0) / 100.0;
    }
}
