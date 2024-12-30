package modelos;

public class Moeda {
    private String nomeMoeda;
    private double valorParaConverterMoeda;
    private String nomeDaMoedaConvertida;

    public Moeda(String nomeMoeda,double valorParaConverterMoeda, String nomeDaMoedaConvertida) {
        this.nomeMoeda = nomeMoeda;
        this.valorParaConverterMoeda = valorParaConverterMoeda;
        this.nomeDaMoedaConvertida = nomeDaMoedaConvertida;

    }

    public Moeda(MoedaApi moedaApi) {
        this.nomeMoeda = moedaApi.base_code();
        this.valorParaConverterMoeda = moedaApi.conversion_rate();
        this.nomeDaMoedaConvertida = moedaApi.target_code();
    }

    public String getNomeMoeda() {
        return nomeMoeda;
    }

    public double getValorParaConverterMoeda() {
        return valorParaConverterMoeda;
    }

    public String getNomeDaMoedaConvertida() {
        return nomeDaMoedaConvertida;
    }

    @Override
    public String toString() {
        return "Nome da Moeda: " + nomeMoeda +
                " Valor para converter: " + valorParaConverterMoeda;
    }

}
