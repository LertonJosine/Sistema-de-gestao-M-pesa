import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Semana implements Serializable{
    
    // atributos

    private Vector dias;
    private float defice, excedente;
    private String relatorio;

    // construtor

    public Semana(Vector dias){
        this.dias = dias;

    }

    // metodo que calcula o defice

    private void CalculoDefice(){
        // pimeiro vamos abrir o ficheiro dias a fim de colectar todos os dias da semana
        try{
            FileInputStream fi = new FileInputStream("Dias.dat")
            ObjectInputStream obi = new ObjectInputStream(fi);

            

        }catch(FileNotFoundException e){
            System.out.println("Ficheiro não foi encontrado");
        }

    }

    // metodo que calcula excedente

    private void CalculoExcedente(){
        
    }

    // gerador de relatorio

    private void GerarRelatorio(){

    }

    // getters

    public float getDefice(){return defice;}

    public float getExcedente(){return excedente;}
    
    public String getRelatorio(){return relatorio;}

    public Vector getDias(){return dias;}
}
