

public class Retirada {
    // atributos
    private float valor;
    private String justificativa;

    // construtor
    public Retirada(float valor, String justificativa){
        justificativa = "Não definida";
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
}   
