import java.io.Serializable;

public class Entrada implements Serializable{
    
    // atributos

    private float valor;
    private String nota;

    // construtor

    public Entrada(float valor, String nota){

        this.nota = nota;
        this.valor = valor;
    }

    // getters

    public float getValor(){
        return valor;
    }

    public String getNota(){
        return nota;
    }

    public String toString(){
        return "Valor: "+valor+"\nJustificacao: "+nota;
    }
}
