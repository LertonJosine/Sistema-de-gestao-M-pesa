import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class Teste {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Vector dias = new Vector<>();

        try {
            FileInputStream fi = new FileInputStream("Ficheiros/Dias.dat");
            ObjectInputStream obi = new ObjectInputStream(fi);

            dias = (Vector) obi.readObject();
            dias.trimToSize();
            obi.close();

            System.out.println(dias.size());
            Semana sm = new Semana(dias);
            // System.out.println(((Dia)dias.elementAt(0)).getRetiradas());
             sm.CalculoDefice();
             System.out.println(sm.getDefice());


        } catch (FileNotFoundException e) {
            System.out.println("FIcheiro não foi encontrado");

        }

    }
}
