import java.util.ArrayList;

public class Fila {

    private ArrayList<Cliente> processos;

    private double tempoChegadaClientePrecedente;

    public Fila() {
        this.processos = new ArrayList<>();
        this.tempoChegadaClientePrecedente = 0;
    }

    public void pushCliente(Cliente cliente){
        tempoChegadaClientePrecedente = cliente.getTempoChegada();
        processos.add(cliente);
    }

    public double getTempoChegadaClientePrecedente() {
        return tempoChegadaClientePrecedente;
    }

    public void setTempoChegadaClientePrecedente(double tempoChegadaClientePrecedente) {
        this.tempoChegadaClientePrecedente = tempoChegadaClientePrecedente;
    }

    public Cliente popCliente(){
        Cliente proximoCLiente = processos.get(0);
        processos.remove(proximoCLiente);
        return proximoCLiente;
        //
    }

    public ArrayList<Cliente> getProcessos() {
        return processos;
    }
}
