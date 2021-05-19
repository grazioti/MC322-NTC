package mc322.lab06;
import mc322.lab06.componentes.*;

public class Montador {

    /*
    Recebe uma String contendo o caminho da pasta contendo o arquivo.csv em que
    cada linha contém os índices da Sala e o componente a ser adicionado. Cria um
    objeto caverna e o herói, adicionando cada componente em sua devida sala, se
    for válido. Retorna o herói se a caverna foi criada corretamente e null se ela
    foi criada incorretamente.
     */
    public Heroi criarCaverna(String caminho) {
        //lendo o csv, ex retorno: [[1:1, P], [1:2, _], ...,[4:4, _]]
        CSVHandling csv = new CSVHandling();
        csv.setDataSource(caminho);
        String[][] commands = csv.requestCommands();

        Caverna caverna = new Caverna();
        Heroi heroi = null;
        Componente componente;
        boolean inseriuCorretamente = false;
        String letra;
        int i = 0;
        int j = 0;

        for (int k = 0; k < commands.length; k++) {
            if (commands[k] == null || commands[k][0].equals(""))
                break;
            i = Integer.parseInt(commands[k][0].substring(0,1)) - 1;
            j = Integer.parseInt(commands[k][0].substring(2)) - 1;

            if (i > 3 || i < 0 || j > 3 || j < 0)
                return null;

            letra = commands[k][1];
            switch (letra) {
                case "P" -> {
                    heroi = new Heroi(i, j, 'P', caverna);
                    inseriuCorretamente = caverna.addComponente(heroi);
                }
                case "B" -> {
                    componente = new Buraco(i, j, 'B');
                    inseriuCorretamente = caverna.addComponente(componente);
                }
                case "O" -> {
                    componente = new Ouro(i, j, 'O');
                    inseriuCorretamente = caverna.addComponente(componente);
                }
                case "W" -> {
                    componente = new Wumpus(i, j, 'W');
                    inseriuCorretamente = caverna.addComponente(componente);
                }
            }
            if (!inseriuCorretamente){
                return null;
            }
        }
        return heroi;
    }
}
