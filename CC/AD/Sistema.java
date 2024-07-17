import java.util.ArrayList;

public class Sistema {

    private final double lambda;
    private final double maxTempoSimulacao;

    private final Poisson poisson;
    private final Servidor servidor;
    private final Fila fila;
    private final ArrayList<Cliente> clientesCompletos;

    public Sistema(TipoServidor tipoServidor, double mu, double lambda, double maxTempoSimulacao){

        this.maxTempoSimulacao = maxTempoSimulacao;
        this.lambda = lambda;

        this.poisson = new Poisson();
        this.servidor = new Servidor(tipoServidor, mu);
        this.fila = new Fila();
        this.clientesCompletos = new ArrayList<>();
    }


    public double calculaMedia(){
        //o .orElseThrow() foi adicionado para não ficar a má pratica de utilizar um Optional sem checar sua existência
        return clientesCompletos.stream().mapToDouble(cliente -> cliente.getTempoSaida() - cliente.getTempoChegada()).average().orElseThrow();
    }

    public void simula()
    {
        double currentTime = 0;
        double tempoTransiente = this.maxTempoSimulacao/10;

        while(currentTime < this.maxTempoSimulacao + tempoTransiente)
        {
            // Calcula proxima chegada,
            double tempoChegadaClientePrecedente = this.fila.getTempoChegadaClientePrecedente();
            if(tempoChegadaClientePrecedente <= currentTime)
            {
                // calcula proxima chegada
                Cliente proxCliente = new Cliente(
                        this.poisson.getInterval(tempoChegadaClientePrecedente, this.lambda)
                );

                this.fila.pushCliente(proxCliente);
            }

            /*
                Proximo evento sera:
                    servir o proximo cliente da fila
                        (tempo maximo entre := tempo de chegada ou no tempo que servidor ficar ocioso)
                    calcula chegada de cliente
                        se servidor ainda ocioso quando ocorreu ultima chegada
             */
            currentTime = Math.min(
                    Math.max(this.servidor.getProxTempoOcioso(), this.fila.getTempoChegadaClienteProximo()),
                    this.fila.getTempoChegadaClientePrecedente()
            );

            // Serve proximo cliente, se servidor ocioso
            if(this.servidor.getProxTempoOcioso() <= currentTime)
            {
                Cliente cliente = this.fila.popCliente();
                cliente = servidor.serveCliente(cliente, currentTime);

                if(currentTime > tempoTransiente){
                    this.clientesCompletos.add(
                            cliente
                    );
                }
            }
        }
    }

}
