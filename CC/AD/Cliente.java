public class Cliente {

    private double tempoChegada;
    private double tempoSaida;

    public double getTempoChegada() {
        return tempoChegada;
    }

    public double getTempoSaida() {
        return tempoSaida;
    }

    public void setTempoSaida(double tempoSaida) {
        this.tempoSaida = tempoSaida;
    }

    public Cliente(double tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

}
