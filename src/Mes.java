import java.io.Serializable;
import java.util.Vector;

public class Mes implements Serializable{
    
    // atributos

    private Vector semanas, dividas, entradas;
    private float deficeTotal, dividaTotal, excedente;
    private String relatorio; 

    // construtor

    public Mes(Vector semanas){
        this.semanas = semanas;
    }


    // metodo que calcula o total do defice

    private void CalcularDefice(){

    }

    // metodo que calcula os excedentes

    private void CalcularExcedentes(){

    }

    // metodo que gera relatorio

    private void GerarRelatorio(){

    }

    public float getDeficeTotal(){return deficeTotal;}
    
    public float getDividaTotal(){return dividaTotal;}

    public float getExcedentes(){return excedente;}

    public String getRelatorio(){return relatorio;}
}
