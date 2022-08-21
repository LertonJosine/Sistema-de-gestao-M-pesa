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

        System.out.println("Ficheiro " + ficheiro + " actualizado");

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

                    vc = Abrir("Dias.dat"); // abertura do ficheiro dias para comparação

                    if (vc.size() == 6)
                        System.out.println("Não pode adicionar mais dias");
                    else {

                        vc.addElement(dia);
                        System.out.println(vc.elementAt(vc.size() - 1));
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

                    esc2 = vl.ValidarByte("\t[1] Visualizar saldo\n\t[2] Visualizar Valor em Especie"
                            + "\n\t[3] Visualizar comição\n\t[4] Visualizar Total\n\t[5] Visualizar Ultimas Retiradas"
                            + "\n\t[6] Visualizar Ultimas Entradas\n\t[7] Voltar", (byte) 1, (byte) 7);

                    switch (esc2) {
                        case 1: // visualizar o saldo
                            vc.clear();

                            vc = Abrir("Dias.dat");
                            float saldoActual;
                            saldoActual = ((Dia) vc.elementAt(vc.size() - 1)).getSaldo();

                            System.out.println("Saldo actual: " + saldoActual);
                            break;
                        case 2: // visualizar o valor em especie
                            vc.clear();

                            vc = Abrir("Dias.dat");
                            float valorEspecieActual;
                            valorEspecieActual = ((Dia) vc.elementAt(vc.size() - 1)).getValorEspecie();

                            System.out.println("Valor em Especie actual: " + valorEspecieActual);
                            break;
                        case 3: // visualizar a comição actual
                            vc.clear();

                            vc = Abrir("Dias.dat");
                            float comissaoActual;
                            comissaoActual = ((Dia) vc.elementAt(vc.size() - 1)).getComissao();

                            System.out.println("Comissao actual: " + comissaoActual);
                            break;
                        case 4: // visualizar total actual
                            vc.clear();

                            vc = Abrir("Dias.dat");
                            float totalActual;
                            totalActual = ((Dia) vc.elementAt(vc.size() - 1)).getTotal();

                            System.out.println("Total actual: " + totalActual);
                            break;
                        case 5: // visualizar ultimas retiradas
                            vc.clear();
                            vc = Abrir("Dias.dat");
                            vc.trimToSize();
                            String ultimaRetirada; // vatriavel que vai receber as informações da ultima retirada

                            for (int c = vc.size(); c > 0; c--) {
                                System.out.println(c);
                                /*
                                 * Vai percorrer os dias de trás para frente a fim de
                                 * achar a ultima retirada
                                 */
                                retiradas = (((Dia) vc.elementAt(c - 1)).getRetiradas()); // recebe o vector de
                                                                                          // retiradas de
                                                                                          // cada dia
                                System.out.println(retiradas.size());
                                if (retiradas.size() != 0) {
                                    System.out.println("Entrou");
                                    ultimaRetirada = (((Retirada) retiradas.elementAt(retiradas.size() - 1))
                                            .toString());
                                    System.out.println(ultimaRetirada);
                                    break;

                                }
                                if (c == 0) {
                                    System.out.println("Não houve retiradas nesta semana");
                                    break;
                                }
                            }
                            break;
                        case 6: // ultima entrada
                            vc.clear();
                            vc = Abrir("Dias.dat");
                            String ultimaEntrada;
                            System.out.println(vc.size());
                            for (int c = vc.size(); c > 0; c--) {
                                System.out.println(c);
                                entradas = ((Dia) vc.elementAt(c - 1)).getEntradas(); // receber o vector de entradas

                                // percorer o vector de trás para frente afim de achar a ultima entrada
                                if (entradas.size() != 0) {
                                    for (int d = entradas.size(); d >= 0; d--) {
                                        ultimaEntrada = ((Entrada) entradas.elementAt(d - 1)).toString();
                                        System.out.println(ultimaEntrada);
                                        break;
                                    }
                                    break;
                                }
                                if (c == 1)
                                    System.out.println("Não houve entradas esta semana");
                            }
                        case 7: // sair
                            break;
                    }

                    break;

                case 3: // Relatorios

                    // ilustra o submenu
                    esc2 = vl.ValidarByte("\t[1] Relatorio Semanal\n\t[2] Relatorio Mensal\n\t[3]Sair", (byte) 1,
                            (byte) 3);
                    switch (esc2) {
                        case 1: // relatorio da semana

                            vc.clear(); // limpar o vector para receber novos dados

                            vc = Abrir("Dias.dat"); // abrir o ficheiro dias
                            if (vc.size() < 6)
                                System.out.println("Não tem dias suficientes para gerar um relatorio semanal.");
                            else {
                                sm = new Semana(vc); // instanciamos a semana com o parametro dias

                                sm.CalculoDefice(); // procurar defices que possas ter ocorido na semana
                                sm.CalculoExcedente(); // procurar excedentes que possam ter ocorrido durante a semana
                                sm.GerarRelatorio(); // gerar relatorio

                                /*
                                 * Actualiza o ficheiro semanas
                                 * caso nao tenha um ficheiro semana o ficheiro sera criado e a semana sera
                                 * gravada
                                 */
                                vc.clear();
                                vc = Abrir("Semanas.dat");
                                vc.addElement(sm);
                                Actualizar("Semanas.dat", vc);

                                // esperimental
                                // apos a criacao do relatorio vamos limpar os dias no ficheiro dias para então
                                // gravar novos dias para a =-0
                                vc.clear();
                                Actualizar("Dias.dat", vc);
                            }
                            break;
                        case 2: // relatorio mensal
                            System.out.println("Em sesenvolvimento");
                            break;
                    }
                    break;
            }
        } while (esc != 4);

    }

}
