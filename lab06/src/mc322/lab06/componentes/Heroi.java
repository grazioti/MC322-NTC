package mc322.lab06.componentes;
import mc322.lab06.*;

public class Heroi extends Componente {
    public Caverna caverna;
    private boolean flechaEquipada;
    private int numFlechas;
    private boolean capturouOuro;

    public Heroi(int i, int j, char caractere, Caverna caverna) {
        super(i, j, caractere);
        this.caverna = caverna;
        this.flechaEquipada = false;
        this.numFlechas = 1;
        this.capturouOuro = false;
    }

    //Recebe as coordenadas (i,j) da intenção do movimento do herói.
    public void mover(int i, int j) {
        caverna.removeComponente(this.getI(), this.getJ());
        this.setI(i);
        this.setJ(j);
        caverna.addComponente(this);
    }

    //Retorna o caractere do componente de maior prioridade da sala
    public char charPrimeiroSala() {
        Sala atual;
        atual = caverna.getSala(this.getI(), this.getJ());

        return atual.lista[0].getCaractere();
    }

    // Seta o valor de flecha equipada para true e retira uma flecha da quantidade total de flechas.
    public void equipaFlecha() {
        this.flechaEquipada = true;
        this.numFlechas--;
    }

    // Seta o valor da flecha para false.
    public void desequipaFlecha(){
        this.flechaEquipada = false;
    }

    public int getNumFlechas(){
        return numFlechas;
    }

    // Atribui à variável do heroi que ele está com o ouro e retira o ouro da primeira posição da sala
    // em que ele estava inserido.
    public void capturarOuro(){
        caverna.removeComponente(this.getI(), this.getJ());
        this.capturouOuro = true;
    }

    public boolean isFlechaEquipada(){
        return flechaEquipada;
    }

    // Retorna um valor booleano que diz se o herói está com o ouro ou não.
    public boolean heroiCapturouOuro(){
        return capturouOuro;
    }
}
