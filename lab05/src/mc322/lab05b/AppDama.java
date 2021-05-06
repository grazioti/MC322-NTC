package mc322.lab06;

public class AppDama {

    static String[] executaJogo(String caminhoEntrada, String caminhoSaida) {
        CSVHandling csv = new CSVHandling();
        csv.setDataSource(caminhoEntrada);
        String[] commands = csv.requestCommands();
        boolean ehMovimentoValido = true;

        String[] estados = new String[commands.length + 1];
        Tabuleiro tabuleiro = new Tabuleiro();

        System.out.println("Tabuleiro inicial:");
        tabuleiro.mostrarTabuleiro();
        estados[0] = tabuleiro.tabuleiroParaString();

        for (int i = 1; i <= commands.length; i++){
            System.out.println("Source: " + commands[i-1].substring(0, 2));
            System.out.println("Target: " + commands[i-1].substring(3, 5));
            ehMovimentoValido = tabuleiro.solicitaMovimento(commands[i - 1]);
            if (ehMovimentoValido){
                tabuleiro.mostrarTabuleiro();
            }
            estados[i] = tabuleiro.tabuleiroParaString();
        }
        tabuleiro.exportarArquivo(caminhoSaida, ehMovimentoValido);
        return estados;
    }

    public static void main(String[] args) {
        String[] vetorTabuleiros = executaJogo(args[0], args[1]);
    }
}
