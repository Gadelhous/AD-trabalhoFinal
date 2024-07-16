public class Projeto {

    //private ArrayList<Sistema> sistemas;
    public static void main(String[] args) {

        int numeroSistemas = 100;
        double mu = 1;
        double maxTempoSimulacao = 10000;

        List<Double> lambdas =  Arrays.asList(0.5, 0.8, 0.9, 0.99);
        List<TipoServidor> tipoServidores = Arrays.asList(TipoServidor.Exponencial, TipoServidor.Constante);

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

        System.out.println("A média de tempo esperado no sistema exponencial por cada cliente é de: " +mediaGeralExponencial);
        System.out.println("A média de tempo esperado no sistema estático por cada cliente é de: " +mediaGeralEstatico);


        /*
        double media = 0;
        for (int i = 0; i < 1000000; i++) {
            Poisson poisson = new Poisson();
            media += (poisson.getInterval(0, 1)/1000000);
        }

        System.out.println("Media poisson:" +media);

         */

    }

}
