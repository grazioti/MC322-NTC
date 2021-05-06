package mc322.lab06;

public class Peca {
    private char peca;

    public Peca(char caractere){
        peca = caractere;
    }
    /*
    public void setPeca(char caractere){
        this.peca = caractere;
    }
    */
    public char getPeca(){
        return this.peca;
    }

    public int[] verificaMovimento(int iIni, int jIni, int iFim, int jFim, char[] pecasCaminho){
        // O vetor é inicializado com valores aleatórios que não são usados pela lógica do programa (-1).
        int[] movimentos = {-1,-1,-1,-1};

        if (pecasCaminho[0] == '-'){
            movimentos[0] = 0;
        }

        return movimentos;
    }

}
