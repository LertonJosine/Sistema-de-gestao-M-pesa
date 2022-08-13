import java.io.Serializable;

public class Retirada implements Serializable{
    // atributos
    private float valor;
    private String justificativa;

    // construtor
    public Retirada(float valor, String justificativa){
        this.justificativa = justificativa;
        this.valor = valor;
    }

    // getters

    public float getValor(){
        return valor;
    }
    
    public String getJustificativa(){
        return justificativa;
    }
    public String toString(){
        return "Valor: "+valor+"\nJustificacao: "+justificativa;
    }
}
