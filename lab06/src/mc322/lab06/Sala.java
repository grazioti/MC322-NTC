package mc322.lab06;
import mc322.lab06.componentes.*;
import java.util.Arrays;

public class Sala {
    public Componente[] lista;
    public boolean visitado;
    public int ultimaPos;

    public Sala(){
        lista = new Componente[4];
        visitado = false;
        int ultimaPos = 0;
    }

    // Insere um componente na última posição da lista da sala. Após a inserção, caso os índices da sala
    // sejam válidos em que deseja-se inserir, há a ordenação da lista da sala em relação ao peso de cada
    // componente, em ordem decrescente. Essa ordenação acontece para que quando necessita-se retirar um
    // componente, haja a remoção da posição 0, seja ela o Herói, um Wumpus ou o Ouro.

    public boolean insereComponente(Componente componente){
        boolean retorno = false;
        int contador = 0;

        char compInserir = componente.getCaractere();
        char compAtual;

        //verificando se o índice de inserção é válido
        if (componente.getI() >= 0 && componente.getI() <= 3 &&
                componente.getJ() >= 0 && componente.getJ() <= 3) {
            // Verifica se há um componente primário na sala.
            if (compInserir == 'W' || compInserir == 'O' || compInserir == 'B') {
                for (int i = 0; i < ultimaPos; i++) {
                    compAtual = lista[i].getCaractere();
                    if (compAtual == 'W' || compAtual == 'O' || compAtual == 'B') {
                        retorno = false;
                        return retorno;
                    }
                }
                lista[ultimaPos] = componente;
                ultimaPos++;
                retorno = true;
            }
            // Verifica se já há alguma brisa ou fedor na sala para que não se adicione dois do mesmo elemento.
            else if (compInserir == 'b' || compInserir == 'f') {
                for (int i = 0; i < ultimaPos; i++) {
                    compAtual = lista[i].getCaractere();
                    if (compAtual == compInserir) {
                        contador++;
                    }
                }
                if (contador == 0) {
                    lista[ultimaPos] = componente;
                    ultimaPos++;
                    retorno = true;
                }
            }
            else if (compInserir == 'P') {
                lista[ultimaPos] = componente;
                ultimaPos++;
                retorno = true;
                visitado = true;
            }

            // Método que ordena a lista de componentes em ordem decrescente.
            Arrays.sort(lista, (a, b) -> (a != null && b != null) ?
                    (a.getPeso() > b.getPeso() ? -1 : a.getPeso() == b.getPeso() ? 0 : 1) : 0);
        }

        //índice de inserção não é válido
        else{
            retorno = false;
        }

        return retorno;
    }

    // Remove o componente com maior prioridade na sala, ou seja, o componente que está na posição 0
    // da sala, reordenando o vetor em ordem decrescente após a retirada de tal componente.
    public void removeComponente() {
        lista[0] = lista[ultimaPos - 1];
        lista[ultimaPos - 1] = null;
        ultimaPos--;
        Arrays.sort(lista, (a, b) -> (a != null && b != null) ?
                (a.getPeso() > b.getPeso() ? -1 : a.getPeso() == b.getPeso() ? 0 : 1) : 0);
    }
}
