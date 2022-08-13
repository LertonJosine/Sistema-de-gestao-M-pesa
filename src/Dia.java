import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;
public class Dia implements Serializable{
    
    // atributos

    // private Calendar data;
    private float saldo, valorEspecie, comissao, total;
    private Vector retiradas = new Vector<>(), entradas = new Vector<>();

    // construtor

    public Dia(float saldo, float valorEspecie, float comissao){

        this.comissao = comissao;
        this.valorEspecie = valorEspecie;
        this.saldo = saldo;
        this.retiradas = retiradas;
        this.entradas = entradas;
        
    }

    // calculo do total

    public void CalculoTotal(){
        total = saldo + valorEspecie;
    }
    // metodo que registra retiradas

    public void RegistroRetiradas(Retirada retirada){
        retiradas.addElement(retirada);
    }

    // metodo que registra entradas

    public void RegistroEntrada(Entrada entrada){
        entradas.addElement(entrada);
    }

    // getters

    public float getSaldo(){return saldo;}
    public Vector getRetiradas(){return retiradas;}
    public Vector getEntradas(){return entradas;}
    public float getValorEspecie(){return valorEspecie;}
    public float getTotal(){return total;}
    public float getComissao(){return comissao;}
    // public Calendar getData(){return data;}
    public String toString(){
        return "";
    }

}
