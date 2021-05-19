package mc322.lab06.componentes;

public class Componente {
    protected char caractere;
    protected int peso;
    protected int i;
    protected int j;

    public Componente(int i, int j, char caractere){
        this.i = i;
        this.j = j;
        this.caractere = caractere;
        this.peso = extrairPeso(caractere);
    }

    // Cria os pesos para cada tipo de componente, sendo O, B e W peso 4, P peso 3, f peso 2 e b peso 1.
    public int extrairPeso(char caractere) {
        int retorno = 0;
        if (caractere == 'O' || caractere == 'B' || caractere == 'W')
            retorno = 4;
        else if (caractere == 'P')
            retorno = 3;
        else if (caractere == 'f')
            retorno = 2;
        else if (caractere == 'b')
            retorno = 1;
        return retorno;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getPeso() {
        return peso;
    }

    public char getCaractere() {
        return caractere;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }


}
