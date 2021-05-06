package mc322.lab06;

public class Peao extends Peca{

    public Peao(char caractere) {
        super(caractere);
    }

    public int[] verificaMovimento(int iIni, int jIni, int iFim, int jFim, char[] pecasCaminho){
        int[] movimentos = super.verificaMovimento(iIni, jIni, iFim, jFim, pecasCaminho);

        int deltaI = iFim - iIni;
        int deltaJ = jFim - jIni;

        //caso 1: peça a ser movimentada é nula
        if (movimentos[0] == 0) {
            return movimentos;
        }

        //caso 2: a peça continua no mesmo lugar ou anda mais casas do que deveria
        if (pecasCaminho.length > 3 || pecasCaminho.length == 1){
            movimentos[0] = 0;
            return movimentos;
        }
        else {
            //caso 3: tentativa de movimento sem captura de peça
            if (pecasCaminho.length == 2){
                //caso3.1: o destino não é vazio.
                if (pecasCaminho[1] != '-'){
                    movimentos[0] = 0;
                    return movimentos;
                }
                //caso 3.2: a peça branca tenta se mover para trás
                if (pecasCaminho[0] == 'b' && deltaI < 0 ) {
                    movimentos[0] = 0;
                    return movimentos;
                }
                //caso 3.3: a peça preta tenta se mover para trás
                else if (pecasCaminho[0] == 'p' && deltaI > 0 ) {
                    movimentos[0] = 0;
                    return movimentos;
                }
                //caso 3.4: o movimento é válido
                else {
                    movimentos[0] = 1;
                    movimentos[1] = 0;
                    return movimentos;
                }
            }
            //caso 4: tentativa de movimento com captura de peça da cor oposta
            else if (pecasCaminho.length == 3){
                // caso 4.1: o destino não é vazio
                if (pecasCaminho[2] != '-'){
                    movimentos[0] = 0;
                    return movimentos;
                }
                // caso 4.2: a captura é válida para uma peça branca capturar uma peça preta
                if (pecasCaminho[0] == 'b' && (pecasCaminho[1] == 'p' || pecasCaminho[1] == 'P')){
                    movimentos[0] = 1;
                    movimentos[1] = 1;
                    movimentos[2] = iIni + (deltaI / 2);
                    movimentos[3] = jIni + (deltaJ / 2);
                    return movimentos;
                }
                // caso 4.3: a captura é válida para uma peça preta capturar uma peça branca
                else if (pecasCaminho[0] == 'p' && (pecasCaminho[1] == 'b' || pecasCaminho[1] == 'B')){
                    movimentos[0] = 1;
                    movimentos[1] = 1;
                    movimentos[2] = iIni + (deltaI / 2);
                    movimentos[3] = jIni + (deltaJ / 2);
                    return movimentos;
                }
                // caso 4.4: a peça intermediária não é válida para a captura, seja ela '-' ou do mesmo tipo
                // que a peça inicial.
                else {
                    movimentos[0] = 0;
                    return movimentos;
                }
            }
        }
        return movimentos;
    }
}
