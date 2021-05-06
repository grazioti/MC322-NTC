package mc322.lab06;

public class Dama extends Peca{

    public Dama(char caractere){
        super(caractere);
    }

    public int[] verificaMovimento(int iIni, int jIni, int iFim, int jFim, char[] pecasCaminho){
        int[] movimentos = super.verificaMovimento( iIni,  jIni,  iFim,  jFim, pecasCaminho);
        int deltaI = iFim - iIni;
        int deltaJ = jFim - jIni;

        int contador = 0;
        int posCaptura = 0;

        //1 se o movimento é para cima, -1 se é para baixo.
        int incrementoI = deltaI/Math.abs(deltaI);

        //1 se o movimento é para direita, -1 se é para esquerda.
        int incrementoJ = deltaJ/Math.abs(deltaJ);

        // caso 1: peça a ser movimentada é nula
        if (movimentos[0] == 0) {
            return movimentos;
        }

        for (int i = 1; i < pecasCaminho.length; i++){
            if (pecasCaminho[i] != '-'){
                contador += 1;
                posCaptura = i;        //guardamos o índice do vetor da peça a ser capturada
            }
        }

        // caso 2: não há peças a serem capturadas, i.e, movimento simples.
        if (contador == 0) {
            movimentos[0] = 1;
            movimentos[1] = 0;
            return movimentos;
        }
        // caso 3: há uma peça no caminho somente
        else if (contador == 1){
            // caso 3.1: o destino não é vazio
            if (pecasCaminho[pecasCaminho.length - 1] != '-'){
                movimentos[0] = 0;
                return movimentos;
            }
            // caso 3.2: o destino é vazio e a peça a ser capturada é de cor oposta à inicial
            if (pecasCaminho[0] == 'P' && (pecasCaminho[posCaptura] == 'b' || pecasCaminho[posCaptura] == 'B')){
                movimentos[0] = 1;
                movimentos[1] = 1;
                movimentos[2] = (posCaptura * incrementoI) + iIni;
                movimentos[3] = (posCaptura * incrementoJ) + jIni;
                return movimentos;
            }
            // caso 3.3: o destino é vazio e a peça a ser capturada é de cor oposta à inicial
            else if (pecasCaminho[0] == 'B' && (pecasCaminho[posCaptura] == 'p' || pecasCaminho[posCaptura] == 'P')){
                movimentos[0] = 1;
                movimentos[1] = 1;
                movimentos[2] = (posCaptura * incrementoI) + iIni;
                movimentos[3] = (posCaptura * incrementoJ) + jIni;
                return movimentos;
            }
        }
        //caso 4: há mais de uma peça no caminho do movimento
        else {
            movimentos[0] = 0;
            return movimentos;
        }

        movimentos[0] = 0;
        return movimentos;
    }
}
