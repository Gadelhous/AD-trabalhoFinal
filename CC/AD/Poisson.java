import java.util.Random;

public class Poisson {

    private Random random;

    public Poisson()
    {
        random = new Random();
    }

    /*
        Calcula o tempo do proximo evento (chegada/servico) a partir do momento corrente.
        Utiliza um gerador de numero aleatorio entre 0 e 1 para calcular a variável aleatória.
     */
    public double getInterval(double currentTime, double lambda){
        return currentTime + (- Math.log(random.nextDouble()) / lambda);
    }
}
