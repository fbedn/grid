/**************************************************************************/
/*************************** Fernando B.  Ramos ***************************/
/** Maratona de Programação da Sociedade Brasileira de Computação - 2012 **/
/********************** Problema G - Grid de Largada **********************/
/**************************************************************************/

/*

Caderno de problemas e soluções da Maratona de Programação da SBC - 2012 disponível em:
http://maratona.ime.usp.br/hist/2012/primeira-fase/solucoes.pdf

*/

/*

PROBLEMA:
Na Nlogônia, vai ser realizada a sensacional final mundial da fórmula 17. Os competidores se alinham na largada e disputam a corrida. Você vai ter acesso aos grids de largada e de chegada. A questão é determinar o número mínimo de ultrapassagens que foram efetuadas durante a competição.

ENTRADA:
Cada caso de teste utiliza três linhas. A primeira linha de um caso de teste contém um inteiro N (2 ≤ N ≤ 24) indicando o número de competidores. Cada competidor é identificado com um número de 1 a N. A segunda linha de cada caso tem os N competidores, em ordem do grid de largada. A terceira linha de cada caso tem os mesmos competidores, porém agora na ordem de chegada.

SAÍDA:
Para cada caso de teste imprima uma linha contendo um único número inteiro, que indica o número mínimo de ultrapassagens necessárias para se chegar do grid de largada ao grid de chegada.

EXEMPLO ENTRADA (entrada pelo terminal):
5
1 2 3 4 5
3 1 2 5 4
5
3 1 2 5 4
1 2 3 4 5
5
3 1 2 5 4
5 3 2 1 4

EXEMPLO SAÍDA (saída pelo terminal):
3
3
4

*/

/*OBS: há soluções mais eficientes para o problema*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class grid {

    //transforma uma string em um vetor de inteiros
    static int[] vetorInt(String s) {

		String[] sAux = s.split(" ");
		int[] veInt = new int[sAux.length];

		for(int i = 0; i < sAux.length; i++)
			veInt[i] = Integer.valueOf(sAux[i]);

        return(veInt);
    }

    //a partir do valor de um elemento, encontra o índice dele no vetor
    static int encontraIndice(int val, int[] vetor){
        int i = 0;
        while (i < vetor.length) {
            if (vetor[i] == val) break;
            ++i;
        }
        return(i);
    }

    //encontra o menor número de ultrapassagens, dada a ordem de largada, e a ordem de chegada
    static int menorNumeroUltrapassagens(int n_Participantes, int[] largada, int[] chegada) {

        int ultrapassagens = 0;

        for (int iLarg_A = 0; iLarg_A < n_Participantes; iLarg_A++){
        //PASSA por CADA ELEMENTO (até n_Participantes) na LISTA DE LARGADA. Considere abstratamente que esse elemento atual é "A"

            int vA = largada[iLarg_A]; //pega o valor (vA) do elemento atual
            int iCheg_A = encontraIndice(vA, chegada); //encontra o índice desse elemento na lista de chegada

            for (int iCheg_B = 0; iCheg_B < iCheg_A; iCheg_B++) {
            //na LISTA DE CHEGADA, PASSA por CADA ELEMENTO "À FRENTE" do ELEMENTO ATUAL. Considere abstratamente que esse elemento é "B"

                int vB = chegada[iCheg_B]; //obtém o valor (vB) desse elemento à frente
                int iLarg_B = encontraIndice(vB, largada); //encontra o índice desse elemento na lista de largada
                //lembrando: vB está à frente de vA na lista de chegada
                if (iLarg_B > iLarg_A) { //verifica se vB estava atrás de vA na lista de largada (ou seja, se tinha índice maior)
                    ++ultrapassagens; //se sim, foi necessária (pelo menos) uma ultrapassagem, então somamos 1 à quantidade de ultrapassagens
                }
            }

        }
        return(ultrapassagens);
    }

    /**** MAIN ****/
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        List<Integer> lMenNumUltrap = new ArrayList<Integer>(); //lista guardará o menor número de ultrapassagens para cada corrida

        while (ler.hasNextLine()) { //laço de leitura pelo terminal

            String stringN = ler.nextLine(); //lê o número de participantes
            if (stringN.isEmpty()) { //sai do laço quando a linha lida estiver vazia
                break;
            }
            int n = Integer.valueOf(stringN); //passa para o tipo int o número de participantes

            String l = ler.nextLine(); //lê a lista de largada
            String c = ler.nextLine(); //lê a lista de chegada

            int[] largada = vetorInt(l); //passa a lista de largada para um vetor de int
            int[] chegada = vetorInt(c); //passa a lista de chegada para um vetor de int

            if (n > largada.length) n = largada.length; //Pequena verificação. Se necessário, corrige a variável do número de participantes. Pois o número informado não deveria exceder a quantidade na lista de largada

            int m = menorNumeroUltrapassagens(n, largada, chegada); //encontra o menor número de "ultrapassagens" para a corrida
            lMenNumUltrap.add(m); //adiciona esse valor à lista
        }

        for (Integer m : lMenNumUltrap) { //imprime a quantidade (mínima) de ultrapassagens para cada corrida (cada linha imprimida se refere a uma corrida)
            System.out.println(m);
        }

	}

}
