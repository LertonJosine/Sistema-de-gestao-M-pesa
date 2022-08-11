import java.io.Serializable;

public class Retirada implements Serializable{
    // atributos
    private float valor;
    private String justificativa;

    // construtor
    public Retirada(float valor, String justificativa){
        this.justificativa = justificativa = "Não definida";
        this.valor = valor = 0;
    }

    // getters

    public float getValor(){
        return valor;
    }
    
    public String getJustificativa(){
        return justificativa;
    }
}   
