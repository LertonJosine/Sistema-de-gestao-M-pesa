import java.io.Serializable;
import java.util.Vector;

public class Mes implements Serializable {

    // atributos

    private Vector semanas, dividas, entradas;
    private float deficeTotal = 0, dividaTotal = 0, entradasTotal = 0, excedente = 0;
    private String relatorio;

    // construtor

    public Mes(Vector semanas) {
        this.semanas = semanas;
    }

    // metodo que calcula o total do defice

    private void CalcularDefice(Vector semana) {

        if (semana.size() < 4)
            System.out.println("Não tem semanas suficientes para gerar um relatorio mensal");
        else {
            for(int c = 0; c<semana.size(); c++){
                deficeTotal += ((Semana)semana.elementAt(c)).getDefice();
                
            }
        }

    }

    // metodo que calcula os excedentes

    private void CalcularExcedentes() {

    }

    // metodo que gera relatorio

    private void GerarRelatorio() {

    }

    public float getDeficeTotal() {
        return deficeTotal;
    }

    public float getDividaTotal() {
        return dividaTotal;
    }

    public float getExcedentes() {
        return excedente;
    }

    public String getRelatorio() {
        return relatorio;
    }
}
