import java.util.Calendar;
import java.util.Vector;
public class Dia {
    
    // atributos

    // private Calendar data;
    private float saldo, valorEspecie, comissao, total;
    private Vector retiradas, entradas;

    // construtor

    public Dia(float saldo, float valorEspecie, float comissao){

        this.comissao = comissao;
        this.valorEspecie = valorEspecie;
        this.saldo = saldo;
        
    }

    // metodo que registra retiradas

    public void RegistroRetiradas(Retirada retirada){
        

    }

    // metodo que registra entradas

    public void RegistroEntrada(Entrada entrada){

    }

    // getters

    public float getSaldo(){return saldo;}

    public Vector getRetiradas(){return retiradas;}

    public Vector getEntradas(){return entradas;}

    // public Calendar getData(){return data;}

}
