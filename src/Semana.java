import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Semana implements Serializable {

    // atributos

    private Vector dias = new Vector<>();
    private Vector retiradasTotal = new Vector<>(), entradasTotal = new Vector<>();
    private float defice, excedente, totalRetiradas = 0, totalEntradas = 0;
    private String relatorio;

    // construtor

    public Semana(Vector dias) {
        this.dias = dias;

    }

    // metodo que calcula o defice

    public void CalculoDefice(Vector dias) throws IOException, ClassNotFoundException {
        Vector dividas = new Vector<>(), entradas = new Vector<>();

        // pimeiro vamos abrir o ficheiro dias a fim de colectar todos os dias da semana

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

    }

    // metodo que calcula excedente

    public void CalculoExcedente(Vector dias) {
        float totalEntradas = 0, totalRetiradas = 0, totalEsperado, primeiroTotal, totalActual;
        Vector entradas, retiradas;
        Retirada retirada;
        Entrada entrada;

        // recolher as retiradas de cada dia e estrtair o valor das retiradas e entradas
        for (int c = 0; c < dias.size(); c++) {
            retiradas = ((Dia) dias.elementAt(c)).getRetiradas(); // recebe o vector retiradas
            entradas = ((Dia) dias.elementAt(c)).getEntradas(); // recebe o vector entradas
            // soma de todas as retiradas
            if (retiradas.size() != 0 & retiradas != null) {
                for (int d = 0; d < retiradas.size(); d++) {
                    retirada = ((Retirada) retiradas.elementAt(d));
                    totalRetiradas += retirada.getValor();
                }
            }

            // somatorio de totas as entradas
            if (entradas.size() != 0 & entradas != null) {
                for (int d = 0; d < entradas.size(); d++) {
                    entrada = (Entrada) entradas.elementAt(d);
                    totalEntradas += entrada.getValor();
                }
            }
        }

        primeiroTotal = ((Dia) dias.elementAt(0)).getSaldo(); // recebe o total do valor no inicio da semana
        totalEsperado = (primeiroTotal + totalEntradas) - totalRetiradas; // calculo para estipular após as retiradas e
                                                                          // entradas quando deveria ficar
        totalActual = ((Dia) dias.elementAt(dias.size() - 1)).getTotal(); // recebe o valor total do ultimo dia de
                                                                          // actividade

        // caso o total esperado seja maior em relação ao total efectivo então há
        // dinheiro a mais
        if (totalEsperado > totalActual) {
            excedente = totalEsperado - totalActual;
        }
    }

    // gerador de relatorio

    public void GerarRelatorio() {
        System.out.println(
                "\t\tRetiradas efectuadas\n");

        if (retiradasTotal.size() == 0)
            System.out.println("Não houve retiradas esta semana!");
        // mostrar todas as retiradas efectuadas
        else {
            for (int c = 0; c < retiradasTotal.size(); c++)
                System.out.println(((Retirada) retiradasTotal.elementAt(c)).toString());
        }

        System.out.println("\t\tEntradas efectuadas\n");
        if (entradasTotal.size() == 0)
            System.out.println("Não houve entradas esta semana!");
        else {
            // mostra todas as entradas da semana

            for (int c = 0; c < entradasTotal.size(); c++)
                System.out.println(((Entrada) entradasTotal.elementAt(c)).toString());

        }

        System.out.println("\nTotal em retiradas: " + totalRetiradas+"Mt");
        System.out.println("Total entradas: "+ totalEntradas+"Mt");
        System.out.println("Defice: "+defice+"Mt");
        System.out.println("Excedente: "+excedente+"");
    }

    // getters

    public float getDefice() {
        return defice;
    }

    public float getExcedente() {
        return excedente;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public Vector getDias() {
        return dias;
    }
}
