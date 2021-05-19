package mc322.lab06;
import mc322.lab06.componentes.*;

public class Caverna {
    private Sala[][] caverna;

    public Caverna() {
        caverna = new Sala[4][4];
        for (int i = 0; i <= 3; i++){
            for (int j = 0; j <= 3; j++){
                caverna[i][j] = new Sala();
            }
        }
    }

    // Recebe um objeto do tipo componente, e repassa para a sala para que ela insira esse componente
    // na última posição. Devolve uma variável booleana de retorno para saber se inseriu corretamente
    // tal componente, ou seja, se ele era válido de acordo com as especificações da caverna.
    public boolean addComponente(Componente componente) {
        boolean inseriuCorretamente;
        boolean retorno;
        Componente compInserir;
        int i = componente.getI();
        int j = componente.getJ();

        char compInserido = componente.getCaractere();

        inseriuCorretamente = caverna[i][j].insereComponente(componente);
        retorno = inseriuCorretamente;

        // Verificará se é um Wumpus ou Buraco para que se insira o Fedor ou a Brisa.
        if (inseriuCorretamente){
            if((compInserido == 'W')){
                if (i + 1 <= 3){
                    compInserir = new Fedor(i + 1, j, 'f');
                    inseriuCorretamente = caverna[i+1][j].insereComponente(compInserir);
                }
                if (i - 1 >= 0){
                    compInserir = new Fedor (i - 1, j, 'f');
                    inseriuCorretamente = caverna[i-1][j].insereComponente(compInserir);
                }
                if (j + 1 <= 3){
                    compInserir = new Fedor (i, j + 1, 'f');
                    inseriuCorretamente = caverna[i][j+1].insereComponente(compInserir);
                }
                if (j - 1 >= 0) {
                    compInserir = new Fedor(i, j - 1, 'f');
                    inseriuCorretamente = caverna[i][j-1].insereComponente(compInserir);
                }
            }
            else if (compInserido == 'B'){
                if (i + 1 <= 3){
                    compInserir = new Brisa(i + 1, j, 'b');
                    inseriuCorretamente = caverna[i+1][j].insereComponente(compInserir);
                }
                if (i - 1 >= 0){
                    compInserir = new Brisa(i - 1, j, 'b');
                    inseriuCorretamente = caverna[i-1][j].insereComponente(compInserir);
                }
                if (j + 1 <= 3){
                    compInserir = new Brisa(i, j + 1, 'b');
                    inseriuCorretamente = caverna[i][j+1].insereComponente(compInserir);
                }
                if (j - 1 >= 0) {
                    compInserir = new Brisa(i, j - 1, 'b');
                    inseriuCorretamente = caverna[i][j-1].insereComponente(compInserir);
                }
            }
        }
        return retorno;
    }

    // Repassa a sala os índices da sala que o componente que deseja-se remover está.
    public void removeComponente(int i, int j) {
        caverna[i][j].removeComponente();
    }

    //verifica se a caverna foi construída de forma válida, de acordo com as especificações.
    public boolean verificaCaverna(){
        int qtdWumpus = 0;
        int qtdOuro = 0;
        int qtdHeroi = 0;
        int qtdBuracos = 0;
        int qtdPrimarios = 0;
        char compAtual;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < caverna[i][j].ultimaPos; k++) {
                    compAtual = caverna[i][j].lista[k].getCaractere();
                    if (compAtual == 'W') {
                        qtdWumpus++;
                        qtdPrimarios++;
                        if (i == 0 && j == 0)
                            return false;
                    }
                    else if (compAtual == 'O') {
                        qtdOuro++;
                        qtdPrimarios++;
                    }
                    else if (compAtual == 'B') {
                        qtdBuracos++;
                        qtdPrimarios++;
                    }
                    else if (compAtual == 'P') {
                        qtdHeroi++;
                        if (i != 0 || j != 0) {
                            return false;
                        }
                    }
                }
                if (qtdPrimarios > 1)
                    return false;
                qtdPrimarios = 0;
            }
        }

        if (qtdWumpus == 1 && qtdHeroi == 1 && (qtdBuracos == 2 || qtdBuracos == 3) && qtdOuro == 1)
            return true;

        return false;
    }

    // Retorna a sala na posição i,j da caverna.
    public Sala getSala(int i, int j){
        return caverna[i][j];
    }

    // Faz a impressão da caverna de acordo com a forma especificada.
    public void imprimeCaverna(String nomeJogador, int score) {
        for (int i = 0; i <= 3; i++) {
            System.out.print(i + 1);
            for (int j = 0; j <= 3; j++) {
                if (caverna[i][j].lista[0] == null && !caverna[i][j].visitado) {
                    System.out.print(" -");
                }
                else if (caverna[i][j].lista[0] == null && caverna[i][j].visitado){
                    System.out.print(" #");
                }
                else if (caverna[i][j].lista[0] != null && !caverna[i][j].visitado){
                    System.out.print(" -");
                }
                else if (caverna[i][j].lista[0] != null && caverna[i][j].visitado){
                    System.out.print(" " + caverna[i][j].lista[0].getCaractere());
                }
            }
            System.out.println();
        }
        System.out.println("  1 2 3 4");
        System.out.println();
        System.out.println("Player: " + nomeJogador);
        System.out.println("Score: " + score);
    }
}
