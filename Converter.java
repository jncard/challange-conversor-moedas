class Converter {
    public double converterMoeda(coin origem, String codigoDestino) {
        double valorOrigem = origem.getValor();
        double cotacaoDestino = over.getCotacao(codigoDestino);

        return Math.round(valorOrigem * cotacaoDestino * 100.0) / 100.0;
    }

    public double converterMedida(double origem, double metrica) {
        return Math.round(origem * metrica * 100.0) / 100.0;
    }
}
