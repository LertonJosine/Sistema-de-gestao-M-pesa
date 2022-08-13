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
            FileInputStream fi = new FileInputStream("Ficheiros/" + ficheiro);
            ObjectInputStream obi = new ObjectInputStream(fi);

            vc = (Vector) obi.readObject();
            obi.close();

        } catch (FileNotFoundException e) {
            System.out.println("Criando o ficheiro" + ficheiro);

        }
        return vc;
    }

    public static void Actualizar(String ficheiro, Vector vc) throws IOException, ClassNotFoundException {
        

        FileOutputStream fo = new FileOutputStream("Ficheiros/" + ficheiro);
        ObjectOutputStream obo = new ObjectOutputStream(fo);

        obo.writeObject(vc);
        obo.close();

        System.out.println("Ficheiro "+ficheiro+ " actualizado");

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
                    vc = Abrir("Dias.dat"); // abertura do ficheiro dias para comparação

                    if (vc.size() == 6)
                        System.out.println("Não pode adicionar mais dias");
                    else {

                        vc.addElement(dia);
                        Actualizar("Dias.dat", vc); // actualizacao
                        
                        vc.clear(); // limpar o vectror para poder receber novas informações
                        vc = Abrir("Retiradas.dat"); // abrir o ficheiro conservar as informacoes contidas
                        // adicionar as retiradas recentes no ficheiro

                        for (int c = 0; c < retiradas.size(); c++) {
                            vc.addElement(retiradas.elementAt(c));
                            vc.trimToSize();
                        }
                        Actualizar("Retiradas.dat", vc); // actualizar o ficheiro de retiradas
                        vc.clear(); // limpar o vector para receber novas informações

                        
                        vc = Abrir("Entradas.dat");

                        for (int c = 0; c < entradas.size(); c++) {
                            vc.addElement(entradas.elementAt(c));
                            vc.trimToSize();
                        }

                        Actualizar("Entradas.dat", vc); // actualizacao do ficheiro de entradas

                        System.out.println("Registro concluido"); // reporte de sucesso
                    }
                    break;
                case 2:

                    // ilusatração do menu de opções

                    esc2 = vl.ValidarByte("\t[1] Visualizar saldo\n\t[2]Visualizar Valor em Especie"
                    +"\n\t[3] Visualizar comição\n\t[4] Visualizar Total\n\t[5] Visualizar Ultimas Retiradas"
                    +"\n\t[6] Visualizar Ultimas Entradas\n\t[7] Voltar", (byte)1, (byte)7);

                    	switch(esc2){
                            case 1:
                                
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7: break;
                        }
                    break;
                case 3:
                    break;
            }
        } while (esc != 4);

    }

}
