public class Projeto {

    //private ArrayList<Sistema> sistemas;
    public static void main(String[] args) {
        double mediaGeralExponencial = 0;
        double mediaGeralEstatico = 0;
        int numeroSistemas = 100;

        for (int i = 0; i < numeroSistemas; i++) {
            Sistema sistemaExponencial = new Sistema(TipoServidor.Exponencial,1,0.99,1000);
            sistemaExponencial.simula();
            mediaGeralExponencial += (sistemaExponencial.calculaMedia().getAsDouble() / numeroSistemas);




            Sistema sistemaEstatico = new Sistema(TipoServidor.Constante,1,0.99,1000);
            sistemaEstatico.simula();
            mediaGeralEstatico += (sistemaEstatico.calculaMedia().getAsDouble() / numeroSistemas);
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
