package mc322.lab06;
import mc322.lab06.componentes.*;

public class AppMundoWumpus {
    public static void main(String[] args){
        Montador M = new Montador();
        Heroi heroi = M.criarCaverna(args[0]);

        // Verificará se o herói foi colocado corretamente, ou seja, se não houve alguma falha na inserção
        // de algum componente. Caso tenha ocorrido, a função criarCaverna terá retornado nulo. Verifica-se
        // também se a caverna contém a quantidade de elementos que é válida pela entrada do programa.
        if (heroi == null || !heroi.caverna.verificaCaverna()){
            System.out.println("Sua caverna não corresponde a uma caverna válida!!!");
        }
        else{
            Controle C = new Controle(heroi);
            C.executaJogo();
        }
    }
}
