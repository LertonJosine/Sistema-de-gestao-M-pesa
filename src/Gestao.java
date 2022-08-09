//importacoes

import java.io.IOException;
import java.util.Vector;

public class Gestao {
    public static void main(String[] args)throws IOException {

        //Instanciacao
        Validacoes vc = new Validacoes();

        //declacao de variaveis

        byte esc, esc2 = 0; //variaveis de escolha de opcoes em menus e submenus
        byte contador = 0; //contadores
        boolean sinal, sinal2 = false; //sinalizadores

        do{
            //ilustracao do menu princiupal
            esc = vc.ValidarByte("\t[1] Registros\n\t[2] Visulizar \n\t[3] Relatorios\n\t[4] Sair", (byte) 1, (byte) 4);

            switch(esc){
                case 1:
                    float saldo = 0, comissao = 0, valorEspecie = 0;
                    String justificativa = "";

                    for(int c = 0; c < 2; c++){

                        // colecta de dados de saldo comissao
                        saldo += vc.ValidarFloat("Introduza o saldo do "+(c+1)+" cartão");
                        comissao += vc.ValidarFloat("Introduza o valor da comissao do "+ (c+1)+ "cartão");
                        valorEspecie = vc.ValidarFloat("Introduza o valor em especie");

                        esc2 = vc.ValidarByte("Houve retiradas?\n\t[1] Sim\n\t[2] Não", (byte) 1, (byte) 2);

                        switch(esc2){
                            case 1:
                                
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
