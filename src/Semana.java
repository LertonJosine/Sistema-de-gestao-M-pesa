import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Semana implements Serializable{
    
    // atributos

    private Vector dias = new Vector<>();
    private float defice, excedente;
    private String relatorio;

    // construtor

    public Semana(Vector dias){
        this.dias = dias;

    }

    // metodo que calcula o defice

    public void CalculoDefice()throws IOException, ClassNotFoundException{
        Vector dividas = new Vector<>(), entradas = new Vector<>();

        // pimeiro vamos abrir o ficheiro dias a fim de colectar todos os dias da semana
        try{
            float totalRetiradas = 0, totalEntradas = 0;
            FileInputStream fi = new FileInputStream("Ficheiros/Dias.dat");
            ObjectInputStream obi = new ObjectInputStream(fi);

            dias = (Vector) obi.readObject();
            obi.close();

            // percorrer todos os dias afim de fazer a soma de toas as retiradas e entradas
            for (int c = 0; c < dias.size(); c++){
                dividas = (((Dia) dias.elementAt(c)).getRetiradas()); // obtendo as retiradas
                entradas =(((Dia) dias.elementAt(c))).getEntradas();

                for (int d = 0; d < dividas.size(); d++){
                    totalRetiradas += ((Retirada) dividas.elementAt(d)).getValor(); // somatorio das dividas
                }

                for (int d = 0; d < entradas.size(); d++){
                    totalEntradas += ((Entrada)entradas.elementAt(d)).getValor(); //soma de todas as entradas
                }
            }

            float valorEsperado;

            // valor esperado após as retiradas e entradas
            valorEsperado = ((Dia)dias.elementAt(0)).getTotal() - totalRetiradas + totalEntradas;
            
            // comparação do valor esperado com o valor existente

            if( valorEsperado != ((Dia)dias.elementAt(dias.size()-1)).getTotal()){
                System.out.println("Existe defice de "+(valorEsperado - ((Dia)dias.elementAt(dias.size()-1)).getTotal()));
                defice = valorEsperado - ((Dia)dias.elementAt(dias.size())).getTotal();
            }else{
                System.out.println("Não houve defice!");
            }



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
