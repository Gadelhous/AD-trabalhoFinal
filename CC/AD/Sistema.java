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
                Próximo evento será (ou exclusivo):
                    - Servir o próximo cliente da fila
                        Tempo máximo entre (tempo de chegada do próximo cliente, tempo que o servidor ficará ocioso).
                    - Chegada de cliente
                        Se o servidor ainda estiver ocupado quando ocorreu última chegada, calculará a próxima.

               proxTempoOcioso > tempoChegadaClienteProximo ? (proxTempoOcioso < tempoChegadaCLientePrecedente ? proxTempoOcioso : tempoChegadaClientePrecedente)
                            : (tempoChegadaCLienteProximo < tempoChegadaClientePrecedente ? tempoChegadaClienteProximo : tempoChegadaClientePrecedente)

             */
            currentTime = Math.min(
                    Math.max(this.servidor.getProxTempoOcioso(), this.fila.getTempoChegadaProximoClienteDaFila()), // Serve próximo cliente
                    this.fila.getTempoChegadaClientePrecedente() // Calcula próxima chegada de cliente ao sistema
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
