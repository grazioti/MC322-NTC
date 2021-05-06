package mc322.lab06;

public class Tabuleiro {
    Peca[][] tabuleiro;

    Tabuleiro() {
        tabuleiro = new Peca[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = null;

                if (i <= 2) {
                    if ((i == 0 || i == 2) && (j % 2 == 0)) {
                        tabuleiro[i][j] = new Peao('b');
                    } else if ((i == 1) && (j % 2 == 1)) {
                        tabuleiro[i][j] = new Peao('b');
                    }
                } else if (i >= 5) {
                    if ((i == 5 || i == 7) && (j % 2 == 1)) {
                        tabuleiro[i][j] = new Peao('p');
                    } else if ((i == 6) && (j % 2 == 0)) {
                        tabuleiro[i][j] = new Peao('p');
                    }
                }
            }
        }
    }

    public char[] obterCaminho(int iIni, int jIni, int iFim, int jFim){
        int deltaI = iFim - iIni;
        int deltaJ = jFim - jIni;

        char[] pecasCaminho = new char[Math.abs(deltaI) + 1];

        //1 se o movimento é para cima, -1 se é para baixo.
        int incrementoI = deltaI/Math.abs(deltaI);

        //1 se o movimento é para direita, -1 se é para esquerda.
        int incrementoJ = deltaJ/Math.abs(deltaJ);

        int i = iIni, j = jIni;
        int cont = 0;
        while(i != (iFim + incrementoI)){
            if (tabuleiro[i][j] != null){
                pecasCaminho[cont] = tabuleiro[i][j].getPeca();
            }
            else{
                pecasCaminho[cont] = '-';
            }
            i += incrementoI;
            j += incrementoJ;
            cont++;
        }


        return pecasCaminho;
    }

    public boolean solicitaMovimento(String comando) {
        int iIni = comando.charAt(1) - '1';
        int jIni = comando.charAt(0) - 'a';
        int iFim = comando.charAt(4) - '1';
        int jFim = comando.charAt(3) - 'a';
        char[] pecasCaminho;
        boolean retorno = false;

        /* movimentos é um vetor de 4 posições. A primeira posição será 0 ou 1, indicando se o movimento
        é válido ou não. A segunda posição será 0 ou 1, indicando se o movimento executado é de captura ou
        não. As terceira e quarta posições serão, respectivamente, os índices i e j para retirar a peça
        capturada, caso haja a captura de uma peça. Caso não o movimento não seja de captura, as posições
        3 e 4 terão índices inválidos.
         */
        int[] movimentos;

        //verifica se os índices iniciais e finais estão fora do tabuleiro.
        if (iIni < 0 || iIni >= 8 || jIni < 0 || jIni >= 8 || iFim < 0 || iFim >= 8 || jFim < 0 || jFim >= 8) {
            System.out.println("Movimento inválido!\n");
            retorno = false;
        }
        //verifica se o movimento é diagonal.
        else if ( Math.abs(iIni - iFim) != Math.abs(jIni - jFim) ) {
            System.out.println("Movimento inválido!\n");
            retorno = false;
        }
        //verifica se a posição inicial é válida
        else if (tabuleiro[iIni][jIni] == null){
            System.out.println("Movimento inválido!\n");
            retorno = false;
        }
        else {
            pecasCaminho = obterCaminho(iIni, jIni, iFim, jFim);
            movimentos = tabuleiro[iIni][jIni].verificaMovimento(iIni, jIni, iFim, jFim, pecasCaminho);

            // caso falso, retorna mensagem de erro e falso.
            if (movimentos[0] == 0){
                System.out.println("Movimento inválido!\n");
                retorno = false;
            }
            else if (movimentos[0] == 1) {
                // movimento acontece, mas não é de captura
                if (movimentos[1] == 0) {
                    tabuleiro[iFim][jFim] = tabuleiro[iIni][jIni];
                    tabuleiro[iIni][jIni] = null;
                }
                // movimento acontece e é de captura
                else if (movimentos[1] == 1){
                    tabuleiro[iFim][jFim] = tabuleiro[iIni][jIni];
                    tabuleiro[iIni][jIni] = null;
                    tabuleiro[movimentos[2]][movimentos[3]] = null;
                }
                retorno = true;

                // peça comum atinge o lado oposto do tabuleiro e vira uma dama
                if (tabuleiro[iFim][jFim].getPeca() == 'b' && iFim == 7){
                    tabuleiro[iFim][jFim] = new Dama('B');
                }
                else if (tabuleiro[iFim][jFim].getPeca() == 'p' && iFim == 0){
                    tabuleiro[iFim][jFim] = new Dama('P');
                }
            }
        }

        return retorno;
    }

    public void mostrarTabuleiro() {
        for (int i = 7; i >= 0; i--) {
            System.out.print(i+1);
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] != null)
                    System.out.print(" " + tabuleiro[i][j].getPeca() + " ");
                else
                    System.out.print(" - ");
            }
            System.out.println();
        }
        System.out.println("  a  b  c  d  e  f  g  h");
        System.out.println();
    }

    public void exportarArquivo(String caminhoSaida, boolean ehMovimentoValido) {
        String[] inserirArquivo;
        String aux = "";
        CSVHandling csv = new CSVHandling();

        int contador = 0;

        if (!ehMovimentoValido){
            inserirArquivo = new String[1];
            aux += "erro";
            inserirArquivo[contador] = aux;
        }
        else {
            inserirArquivo = new String[64];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (tabuleiro[j][i] != null) {
                        aux += Character.toString('a' + i) + (j + 1);
                        aux += Character.toString((tabuleiro[j][i].getPeca()));
                    } else {
                        aux += Character.toString('a' + i) + (j + 1) + "_";
                    }
                    inserirArquivo[contador] = aux;
                    contador++;
                    aux = "";
                }
            }
        }
        csv.setDataExport(caminhoSaida);
        csv.exportState(inserirArquivo);
    }

    String tabuleiroParaString() {
        String retorno = "";

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                if (tabuleiro[i][j] != null){
                    retorno += tabuleiro[i][j].getPeca();
                }
                else{
                    retorno += '-';
                }
            }
            retorno += '\n';
        }
        return retorno;
    }
}
