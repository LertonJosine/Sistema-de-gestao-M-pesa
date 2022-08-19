import java.io.IOException;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

public class Teste {
    public static float CalculoDefice(Vector dias) throws IOException, ClassNotFoundException {
        Vector dividas = new Vector<>(), entradas = new Vector<>();

        // pimeiro vamos abrir o ficheiro dias a fim de colectar todos os dias da semana

        float totalRetiradas = 0;
        float totalEntradas = 0, defice = 0;
        Vector entradasTotal = new Vector<>();
        Vector retiradasTotal = new Vector<>();


        // percorrer todos os dias afim de fazer a soma de todas as retiradas e entradas
        for (int c = 0; c < dias.size(); c++) {
            dividas = (((Dia) dias.elementAt(c)).getRetiradas()); // obtendo as retiradas
            entradas = (((Dia) dias.elementAt(c))).getEntradas(); // obtendo as entradas

            for (int d = 0; d < dividas.size(); d++) {
                totalRetiradas += ((Retirada) dividas.elementAt(d)).getValor(); // somatorio das dividas
                retiradasTotal.addElement(dividas.elementAt(d)); // registra cada entrada de cada dia para ilustrar no
                                                                 // relatorio

            }

            for (int d = 0; d < entradas.size(); d++) {
                totalEntradas += ((Entrada) entradas.elementAt(d)).getValor(); // soma de todas as entradas
                entradasTotal.addElement(entradas.elementAt(d)); // registra cada entrada para posteriormente ilustrar
                                                                 // no relatorio
            }
        }

        float valorEsperado;

        // valor esperado após as retiradas e entradas
        valorEsperado = ((Dia) dias.elementAt(0)).getTotal() - totalRetiradas + totalEntradas;

        // comparação do valor esperado com o valor existente

        if (valorEsperado > ((Dia) dias.elementAt(dias.size() - 1)).getTotal()) {
            defice = valorEsperado - ((Dia) dias.elementAt(dias.size() - 1)).getTotal();
        }
        return defice;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Vector vc = new Vector<>(), retiradas = new Vector<>();

        try {
            FileInputStream fi = new FileInputStream("Ficheiros/Dias.dat");
            ObjectInputStream obi = new ObjectInputStream(fi);

            vc = (Vector) obi.readObject();
            obi.close();
            retiradas = (((Dia)vc.elementAt(0)).getRetiradas());
            System.out.println(retiradas.size());
            System.out.println(CalculoDefice(vc));
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }
    }
}
