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

    public static Vector Abrir(String ficheiro) throws IOException, ClassNotFoundException {
        Vector vc = new Vector<>();
        try {
            FileInputStream fi = new FileInputStream("Ficheiros/"+ficheiro);
            ObjectInputStream obi = new ObjectInputStream(fi);

            vc = (Vector) obi.readObject();
            obi.close();

        } catch (FileNotFoundException e) {
            System.out.println("Criando o ficheiro"+ ficheiro);

        }
        return vc;
    }

    public static void Actualizar(String ficheiro, Object obj) throws IOException, ClassNotFoundException {
        Vector vc = new Vector<>();
        vc = Abrir(ficheiro);
        vc.addElement(obj);
        
        FileOutputStream fo = new FileOutputStream("Ficheiros/"+ficheiro);
        ObjectOutputStream obo = new ObjectOutputStream(fo);

        obo.writeObject(vc);
        obo.close();

        System.out.println("Ficheiro actualizado");

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Instanciacao
        Validacoes vl = new Validacoes();
        Vector vc = new Vector<>(), retiradas = new Vector<>(), entradas = new Vector<>();
        Retirada rt;
        Entrada et;
        Dia dia;
        Semana sm;
        Mes mes;
        // declacao de variaveis

        byte esc, esc2 = 0; // variaveis de escolha de opcoes em menus e submenus
        byte contador = 0; // contadores
        boolean sinal, sinal2 = false; // sinalizadores

        do {
            // ilustracao do menu princiupal
            esc = vl.ValidarByte("\t[1] Registros\n\t[2] Visulizar \n\t[3] Relatorios\n\t[4] Sair", (byte) 1, (byte) 4);

            switch (esc) {
                case 1:
                    float saldo = 0, comissao = 0, valorEspecie = 0;
                    String justificativa = "";

                    for (int c = 0; c < 2; c++) {

                        // colecta de dados de saldo comissao
                        saldo += vl.ValidarFloat("Introduza o saldo do " + (c + 1) + " cartão");
                        comissao += vl.ValidarFloat("Introduza o valor da comissao do " + (c + 1) + "cartão");
                    }
                        valorEspecie = vl.ValidarFloat("Introduza o valor em especie");
                        dia = new Dia(saldo, valorEspecie, comissao);
                        dia.CalculoTotal();
                        // registro de retiradas
                        do {
                            esc2 = vl.ValidarByte("Houve retiradas?\n\t[1] Sim\n\t[2] Não", (byte) 1, (byte) 2);
                            if (esc2 == 1) {
                                float valor;

                                valor = vl.ValidarFloat("Introduza o valor");
                                justificativa = vl.ValidarStr("Para que fim foi retirado o valor?");

                                rt = new Retirada(valor, justificativa);
                                dia.RegistroRetiradas(rt);

                            }

                        } while (esc2 != 2);

                         vc = Abrir("Retiradas.dat"); // abrir o ficheiro conservar as informacoes contidas

                        // adicionar as informacoes recentes

                        for (int c = 0; c < retiradas.size(); c++) {
                            vc.addElement(retiradas.elementAt(c));
                            vc.trimToSize();
                        }

                        Actualizar("Retiradas.dat", vc); // actualizar o ficheiro

                        // registro de entradas
                        do {
                            esc2 = vl.ValidarByte("Houve entradas?\n\t[1] Sim\n\t[2] Não", (byte) 1, (byte) 2);

                            if (esc2 == 1) {
                                float valor = vl.ValidarFloat("Introduza o valor");
                                String nota = vl.ValidarStr("Introduza uma nota");
                                et = new Entrada(valor, nota);
                                dia.RegistroEntrada(et); // registrar a entrada 
                            }
                        } while (esc2 != 2);

                        vc.clear();
                        vc = Abrir("Entradas.dat");

                        for (int c = 0; c < entradas.size(); c++){
                            vc.addElement(entradas.elementAt(c));
                            vc.trimToSize();
                        }

                        Actualizar("Entradas.dat", vc); // actualizacao do ficheiro de entradas
                   

                    // instanciar o dia
                    
                    Actualizar("Dias.dat", dia); // actualizacao

                    System.out.println("Registro concluido");

                    System.out.println("Total do dia: "+dia.getTotal());
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        } while (esc != 4);

    }

}
