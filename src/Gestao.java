//importacoes

import java.io.IOException;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class Gestao {

    // criacao de rotinas

    public static Vector Abrir(String ficheiro)throws IOException, ClassNotFoundException{
        Vector vc = new Vector<>();
        try{
            FileInputStream fi = new FileInputStream(ficheiro);
            ObjectInputStream obi = new ObjectInputStream(fi);

            vc = (Vector) obi.readObject();
            obi.close();

        }catch(FileNotFoundException e){
            System.out.println("Ficheiro não foi encontrado");

        }
        return vc;
    }
    public static void Actualizar(String ficheiro, Vector vc)throws IOException, ClassNotFoundException{

        FileOutputStream fo = new FileOutputStream(ficheiro);
        ObjectOutputStream obo = new ObjectOutputStream(fo);

        obo.writeObject(vc);
        obo.close();

        System.out.println("Ficheiro actualizado");

    }
    public static void Gravar(String ficheiro, Vector vc){



    }

    public static void main(String[] args)throws IOException {

        //Instanciacao
        Validacoes vl = new Validacoes();

        //declacao de variaveis

        byte esc, esc2 = 0; //variaveis de escolha de opcoes em menus e submenus
        byte contador = 0; //contadores
        boolean sinal, sinal2 = false; //sinalizadores

        do{
            //ilustracao do menu princiupal
            esc = vl.ValidarByte("\t[1] Registros\n\t[2] Visulizar \n\t[3] Relatorios\n\t[4] Sair", (byte) 1, (byte) 4);

            switch(esc){
                case 1:
                    float saldo = 0, comissao = 0, valorEspecie = 0;
                    String justificativa = "";

                    for(int c = 0; c < 2; c++){

                        // colecta de dados de saldo comissao
                        saldo += vl.ValidarFloat("Introduza o saldo do "+(c+1)+" cartão");
                        comissao += vl.ValidarFloat("Introduza o valor da comissao do "+ (c+1)+ "cartão");
                        valorEspecie = vl.ValidarFloat("Introduza o valor em especie");

                        esc2 = vl.ValidarByte("Houve retiradas?\n\t[1] Sim\n\t[2] Não", (byte) 1, (byte) 2);

                        switch(esc2){
                            case 1:
                                float valor;

                                valor = vl.ValidarFloat("Introduza o valor");
                                justificativa = vl.ValidarStr("Para que fim foi retirado o valor?");
                                
                                Retirada rt = new Retirada(valor, justificativa);
                                break;
                            case 2:
                                break;
                        }
                    }
                    


                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }while(esc != 4);
        


    }
    
}
