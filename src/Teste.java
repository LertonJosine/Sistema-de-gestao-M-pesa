import java.io.IOException;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

public class Teste {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Vector vc = new Vector<>(), retiradas = new Vector<>();

        try {
            FileInputStream fi = new FileInputStream("Ficheiros/Dias.dat");
            ObjectInputStream obi = new ObjectInputStream(fi);

            vc = (Vector) obi.readObject();
            obi.close();
            retiradas = (((Dia)vc.elementAt(0)).getRetiradas());
            System.out.println(retiradas.size());
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }
    }
}
