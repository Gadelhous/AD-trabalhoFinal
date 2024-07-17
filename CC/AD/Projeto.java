import java.util.Arrays;
import java.util.List;

public class Projeto {

    public static void main(String[] args) {

        int numeroSistemas = 100;
        double mu = 1;
        double maxTempoSimulacao = 10000;

        List<Double> lambdas =  Arrays.asList(0.5, 0.8, 0.9, 0.99);
        List<TipoServidor> tipoServidores = Arrays.asList(TipoServidor.Exponencial, TipoServidor.Deterministico);

        for (double lambda: lambdas) {
            for(TipoServidor tipoServidor: tipoServidores) {

                double mediaTempoNoSistema = 0;

                for (int i = 0; i < numeroSistemas; i++) {
                    Sistema sistemaExponencial = new Sistema(tipoServidor, mu, lambda, maxTempoSimulacao);
                    sistemaExponencial.simula();
                    mediaTempoNoSistema += (sistemaExponencial.calculaMedia().getAsDouble() / numeroSistemas);
                }

                System.out.println("A média de tempo no sistema " +tipoServidor.toString()+ " com lambda igual a " +lambda+  " por cada cliente é de: " +mediaTempoNoSistema);
            }
        }
    }
}
