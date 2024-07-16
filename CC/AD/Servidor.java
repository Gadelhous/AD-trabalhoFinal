public class Servidor {

    private final Poisson poisson;

    private final TipoServidor tipoServidor;
    private final double mu;
    private double proxTempoOcioso;

    public Servidor(TipoServidor tipoServidor, double mu){
        poisson = new Poisson();

        this.tipoServidor = tipoServidor;
        this.mu = mu;
        this.proxTempoOcioso = 0;
    }

    public double getProxTempoOcioso() {
        return proxTempoOcioso;
    }

    /*
        Serve o cliente, calculando o tempo de saida dele do servidor (consequentemente do sistema).
        O proxTempoOcioso sera a saida desse cliente servido, quando o servidor podera atender
        o proximo cliente.
     */
    public Cliente serveCliente(Cliente cliente, double currentTime){

        double tempoSaida = calculaTempoSaida(currentTime);

        cliente.setTempoSaida(tempoSaida);

        this.proxTempoOcioso = tempoSaida;

        return cliente;
    }

    /*
        Calcula o tempo de saida (currentTime + tempo de servico) exponencial com media 1.
        Como media da exponencial: E[X] = 1/taxa, taxa = 1.
        Lambda = 1.
     */
    private double calculaTempoSaida(double currentTime){
        if(tipoServidor.equals(TipoServidor.Constante))
        {
            return currentTime + mu;
        }

        return poisson.getInterval(currentTime, mu);
    }
}
