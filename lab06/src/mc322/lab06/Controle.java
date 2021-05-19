package mc322.lab06;
import mc322.lab06.componentes.*;

import java.util.Random;
import java.util.Scanner;

public class Controle {
    private Heroi heroi;
    private int score;
    private String nomeJogador;

    Controle(Heroi heroi){
        this.heroi = heroi;
        this.score = 0;
        this.nomeJogador = "";
    }

    /*
    Recebe o caractere do movimento a ser executado pelo herói e retorna
    a diferença entre a componente (i_final - i_inicial), isto é, quanto ele
    deve se mover na direção i.
     */
    public int incrementoI(String command){
        if (command.equals("w"))
            return -1;
        else if (command.equals("s"))
            return 1;
        else
            return 0;
    }

    /*
    Recebe o caractere do movimento a ser executado pelo herói e retorna
    a diferença entre a componente (j_final - j_inicial), isto é, quanto ele
    deve se mover na direção j.
    */
    public int incrementoJ(String command){
        if (command.equals("a"))
            return -1;
        else if (command.equals("d"))
            return 1;
        else
            return 0;
    }

    /*
    Lê o nome do usuário e a ação que ele deseja que o herói execute.
    Enquanto o cliente não venceu, o usuário digita outra ação e é
    mostrada a situação parcial da caverna, bem como seus pontos.
     */
    public void executaJogo() {
        Scanner keyboard = new Scanner(System.in);
        String command;
        boolean jogando = true;
        boolean movimentou = false;
        char caracPrimeiro;
        int iFim, jFim;


        System.out.print("Digite seu nome: ");
        nomeJogador = keyboard.nextLine();
        System.out.println();
        heroi.caverna.imprimeCaverna(nomeJogador, score);

        while (jogando) {
            System.out.println();
            System.out.print("A ação que deseja executar é: ");
            command = keyboard.nextLine();
            System.out.println();

            caracPrimeiro = heroi.charPrimeiroSala();

            // Verifica se o usuário está tentando se mover caso ele esteja numa casa em que deve-se
            // capturar o ouro.
            if ((command.equals("w") || command.equals("s") || command.equals("a") || command.equals("d"))
                    && (caracPrimeiro == 'O')){
                System.out.println("Você deve capturar o ouro antes de se mover!");
                continue;
            }

            else if ((command.equals("w") || command.equals("s") || command.equals("a") || command.equals("d"))
                    && (caracPrimeiro == 'P')){
                iFim = heroi.getI() + incrementoI(command);
                jFim = heroi.getJ() + incrementoJ(command);

                //verificando o movimento especificado pertence ao tabuleiro
                if (iFim >= 0 && iFim <= 3 && jFim >= 0 && jFim <= 3) {
                    heroi.mover(iFim, jFim);
                    movimentou = true;
                    score -= 15;
                }
                else {
                    System.out.println("O local que você deseja mover é inválido");
                    movimentou = false;
                }
            }

            //Herói equipa a flecha
            else if (command.equals("k")) {
                if (heroi.getNumFlechas() > 0){
                    System.out.println("Você equipou sua flecha!");
                    heroi.equipaFlecha();
                    score -= 100;
                }
                else {
                    System.out.println("Não há flechas para equipar!");
                }
            }

            //Herói captura o ouro
            else if (command.equals("c")) {
                if (caracPrimeiro == 'O'){
                    heroi.capturarOuro();
                    System.out.println("Você capturou o ouro que aqui estava escondido!");
                }
                else{
                    System.out.println("Não há um ouro aqui!");
                }
            }

            //O usuário sai do jogo
            else if (command.equals("q")) {
                jogando = false;
                heroi.caverna.imprimeCaverna(nomeJogador, score);
                System.out.println("Volte sempre !");
                break;
            }

            else{
                System.out.println("Esse não é um movimento válido!");
            }

            caracPrimeiro = heroi.charPrimeiroSala();
            //O herói cai no buraco
            if (caracPrimeiro == 'B'){
                jogando = false;
                score -= 1000;
                heroi.caverna.imprimeCaverna(nomeJogador, score);
                System.out.println("Você perdeu =( ...");
                break;
            }

            else if (caracPrimeiro == 'W'){
                if (heroi.isFlechaEquipada()){
                    Random rand = new Random();
                    int matou = rand.nextInt(2);

                    if (matou == 0) {
                        jogando = false;
                        score -= 1000;
                        heroi.caverna.imprimeCaverna(nomeJogador, score);
                        System.out.println("Você perdeu =( ...");
                        break;
                    }
                    else {
                        heroi.caverna.removeComponente(heroi.getI(), heroi.getJ());
                        heroi.desequipaFlecha();
                        System.out.println("Você conseguiu derrotar o Wumpus! Parabéns!!");
                        System.out.println();
                        score += 500;
                    }
                }
                else {
                    jogando = false;
                    score -= 1000;
                    heroi.caverna.imprimeCaverna(nomeJogador, score);
                    System.out.println("Você perdeu =( ...");
                    break;
                }
            }

            if (movimentou && heroi.isFlechaEquipada()){
                heroi.desequipaFlecha();
            }

            if ((heroi.getI() == 0 && heroi.getJ() == 0) && heroi.heroiCapturouOuro()){
                jogando = false;
                score += 1000;
                heroi.caverna.imprimeCaverna(nomeJogador, score);
                System.out.println("Você ganhou =D !!!");
                break;
            }

            if (movimentou){
                heroi.caverna.imprimeCaverna(nomeJogador, score);
            }

            movimentou = false;
        }
    }
}
