import java.util.ArrayList;

public class Fila {

    private final ArrayList<Cliente> clientes;
    private double tempoChegadaClientePrecedente;

    public Fila() {
        this.clientes = new ArrayList<>();
        this.tempoChegadaClientePrecedente = 0;
    }

    public double getTempoChegadaClientePrecedente() {
        return tempoChegadaClientePrecedente;
    }

    public double getTempoChegadaProximoClienteDaFila() { return clientes.get(0).getTempoChegada(); }

    public void pushCliente(Cliente cliente){
        tempoChegadaClientePrecedente = cliente.getTempoChegada();
        clientes.add(cliente);
    }

    public Cliente popCliente(){
        Cliente proximoCLiente = clientes.get(0);
        clientes.remove(proximoCLiente);
        return proximoCLiente;
    }

}
