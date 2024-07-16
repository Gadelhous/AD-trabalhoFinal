import java.util.ArrayList;
import java.util.OptionalDouble;

public class Sistema {

    private double lambda;
    private double maxTempoSimulacao;

    private Poisson poisson;
    private Servidor servidor;
    private Fila fila;
    private ArrayList<Cliente> clientesCompletos;

    public Sistema(TipoServidor tipoServidor, double mu, double lambda, double maxTempoSimulacao){

        this.maxTempoSimulacao = maxTempoSimulacao;
        this.lambda = lambda;

        this.poisson = new Poisson();
        this.servidor = new Servidor(tipoServidor, mu);
        this.fila = new Fila();
        this.clientesCompletos = new ArrayList<>();
    }

    public OptionalDouble calculaMedia(){
       return clientesCompletos.stream().mapToDouble(cliente -> cliente.getTempoSaida() - cliente.getTempoChegada()).average();
    }

    public void simula()
    {
        double currentTime = 0;

        while(currentTime < this.maxTempoSimulacao)
        {
            double tempoChegadaClientePrecedente = this.fila.getTempoChegadaClientePrecedente();
            if(tempoChegadaClientePrecedente <= currentTime)
            {
                // calcula proxima chegada
                Cliente proxCliente = new Cliente(
                        this.poisson.getInterval(tempoChegadaClientePrecedente, this.lambda)
                );

                this.fila.pushCliente(proxCliente);
            }

            // proxTempoOcioso ou currentTime
            if(this.servidor.getProxTempoOcioso() <= currentTime)
            {
                Cliente cliente = this.fila.popCliente();

                currentTime = Math.max(cliente.getTempoChegada(), currentTime);

                this.clientesCompletos.add(
                        servidor.serveCliente(cliente, currentTime)
                );
            }

            currentTime = Math.min(this.servidor.getProxTempoOcioso(), this.fila.getTempoChegadaClientePrecedente());
        }
    }

}
