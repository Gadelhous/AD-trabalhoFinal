import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Projeto {

    /*
        TRABALHO FINAL DE AD

        Alunos:
            Nome: José Roberto Espíndola Corrêa. DRE: 113050633
            Nome: João Victor de Miranda Gadelha. DRE: 117223157

        Resultados e conclusões:

            Exponencial:
                Cálculo teórico:
                    Cálculo da média de tempo do cliente no sistema: E[T] = 1/(mu-lambda).
                    Logo, nosso cálculo para comparações, com mu = 1 fica: E[T] = 1/(1-lambda).
                Resultados:
                Para lambda igual a 0.5: O sistema chegou perto da média teórica de 2.
                Para lambda igual a 0.8: O sistema chegou perto da média teórica de 5.
                Para lambda igual a 0.9: O sistema chegou perto da média teórica de 10.
                Para lambda igual a 0.99: O sistema ficou com média entre 50 e 60, onde a média teórica é 100.

                Conclusão:
                    Ao rodar a simulação, vimos que o tempo total do cliente (ou job) no sistema é menor para lambdas menores.
                    Isso se deve a taxa de serviço mu permanecer a mesma enquanto há mais demanda no sistema quando crescemos
                    o lambda, assim crescendo a utilização do sistema (lambda/mu tendendo a 1). Também notamos que é mais difícil
                    para o sistema convergir para um estado de equilíbrio quando se está perto da utilização 1 do sistema. Ou seja,
                    é necessário mais tempo de simulação para que possamos atingir a média teórica. Testando tempos maiores
                    conseguimos chegar a média teórica E[T] = 100 para lambda = 0.99.

            Determinístico:
                Cálculo teórico:
                    Cálculo da média de tempo do cliente no sistema: E[T] = E[Tq] + E[Ts] .:. Como E[Ts] é 1, e E[Tq] = (rho/1-rho)/2mi.
                    Logo, nosso cálculo para comparações, com mu = 1, e rho = lambda/mu, fica: E[T] = 1 + lambda/2(1-lambda).

                Resultados:
                Para lambda igual a 0.5: O sistema chegou perto da média teórica de 1,5.
                Para lambda igual a 0.8: O sistema chegou perto da média teórica de 3.
                Para lambda igual a 0.9: O sistema chegou perto da média teórica de 5,5.
                Para lambda igual a 0.99: O sistema ficou com média perto de 35, onde a média teórica é 50,5.

                Conclusão:
                    A melhora de performance (dimuição do tempo médio no sistema) no caso determinístico em comparação ao exponencial já era esperada, pois é .
                    Essa discrepância fica maior com a incrementação do lambda, dado que E[Tq] com servidor de atendimento exponencial é 2 vezes maior
                    que no determinístico, e que teremos maior variância na exponencial, enquanto na constante mantemos (chocantemente) constantes.
                    Também notamos que, assim como no serviço exponencial, o sistema leva mais tempo para convergir ao equilíbrio para lambdas maiores,
                    pois estamos perto da saturação do sistema.

     */
    public static void main(String[] args) throws NoSuchElementException {

        int numeroSistemas = 100;
        double mu = 1;
        double maxTempoSimulacao = 10000;

        List<Double> lambdas =  Arrays.asList(0.5,0.8,0.9,0.99);
        List<TipoServidor> tipoServidores = Arrays.asList(TipoServidor.Exponencial, TipoServidor.Deterministico);//TipoServidor.Exponencial, TipoServidor.Deterministico);

        for (double lambda: lambdas) {
            for(TipoServidor tipoServidor: tipoServidores) {

                double mediaTempoNoSistema = 0;

                for (int i = 0; i < numeroSistemas; i++) {
                    Sistema sistemaExponencial = new Sistema(tipoServidor, mu, lambda, maxTempoSimulacao);
                    sistemaExponencial.simula();
                    mediaTempoNoSistema += (sistemaExponencial.calculaMedia()/ numeroSistemas); //
                }

                System.out.println("A média de tempo no sistema " +tipoServidor.toString()+ " com lambda igual a " +lambda+  " por cada cliente é de: " +mediaTempoNoSistema);
            }
        }
    }
}
