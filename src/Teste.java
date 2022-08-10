import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
public class Teste {
    public static void main(String[] args)throws IOException, ClassNotFoundException {
        Vector vc = new Vector<>();

        try{
            FileInputStream fi = new FileInputStream("Dias.dat");
            ObjectInputStream obi = new ObjectInputStream(fi);

            vc = (Vector) obi.readObject();
            // vc.trimToSize();
            obi.close();

            System.out.println(vc.size());

        }catch(FileNotFoundException e){
            System.out.println("FIcheiro não foi encontrado");

        }
    }
}
