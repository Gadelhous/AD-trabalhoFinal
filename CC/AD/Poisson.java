import java.util.Random;


/*
  Classe criada com o intuito de dispor de um metodo que calculasse os intervalos de um evento que segue uma distribuição poisso
  (arrival de um novo evento no servidor e tempo de serviço(quando não é constante))
   */
public class Poisson {
    //A troca de Random para SecureRandom (pseudoaleatório vs aleatório) não alterou os valores  de tempo no sistema, apenas aumentou(e muito) o tempo de execução da simulaçã
    private Random random;

    public Poisson()
    {
        random = new Random();
    }

    /*
        Calcula o tempo do proximo evento (chegada/servico(quando não é constante)) a partir do momento corrente.

        Como na distribuição Poisson, o tempo entre chegadas segue uma distribuição exponencial,
        utilizamos a inversa da CDF para gerar valores para x que satisfazem a distribuição.

        A função CDF da exponencial é F(x;lambda) = 1 - e^(lambda*x).
        Sendo assim, sua inversa será G(u;lambda) = -(1/lambda) * ln(1-u).
        Como geramos u pertence ao intervalo [0,1], aqui geramos uma variável aleatória v=1-u.
        O que também satisfaz o problema.

        Por fim, G(v;lambda) = - ln(v)/lambda.

        Obs:
        - Essa função calcula a próxima chegada de acordo com o tempo corrente, por isso a soma do currentTime.
        - Essa função utiliza um gerador de numero (pseudo)aleatorio entre 0 e 1 para calcular a variável aleatória v.

     */
    public double getInterval(double currentTime, double lambda){
        return currentTime + (- Math.log(random.nextDouble()) / lambda);
    }
}
