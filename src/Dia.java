import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;
public class Dia implements Serializable{
    
    // atributos

    // private Calendar data;
    private float saldo, valorEspecie, comissao, total;
    private Vector retiradas, entradas;

    // construtor

    public Dia(float saldo, float valorEspecie, float comissao, Vector rt, Vector et){

        this.comissao = comissao;
        this.valorEspecie = valorEspecie;
        this.saldo = saldo;
        this.retiradas = retiradas;
        this.entradas = entradas;
        
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
