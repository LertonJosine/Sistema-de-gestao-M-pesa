

public class Entrada {
    
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
}
