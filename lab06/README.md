# Lab 06

## Instruções sobre como acionar o jogo
Para acionar o jogo, deve-se colocar-se na pasta data um arquivo do tipo .csv contendo uma entrada da forma:
```
1:1,P
1:3,W
2:1,B
...
```
Com os índices da sala em que deseja-se inserir o componente. Será verificado se as posições são válidas e se não há componentes primários já nessa sala, assim como se o herói está sendo inserido na posição correta (1,1). A ordem das inserções não necessitam de ser em ordem, podendo ser:
```
1:3,W
2:1,B
1:1,P
...
```
Em um próximo momento, deverá-se digitar qual o nome do jogador e após isso o jogador deverá jogar o jogo com as teclas w,a,s,d,k,c,q, sendo cada função das teclas as seguintes:
```
w - Move-se para cima, caso o movimento seja válido;

a - Move-se para a esquerda, caso o movimento seja válido;

s - Move-se para a baixo, caso o movimento seja válido;

d - Move-se para a direita, caso o movimento seja válido;

c - Captura o ouro caso esteja na posição válida em que o ouro está inserido.

k - Equipa-se a flecha no herói caso o herói contenha alguma flecha ainda. Esta flecha serve para enfrentar o Wumpus.

q - Tecla para sair imediatamente do jogo.
```
O jogo será finalizado, além do modo apertando a letra "q", caso o herói chegue na posição (1,1) com o ouro capturado, ou caso o herói morra, seja caindo em um buraco ou enfrentando um Wumpus e não conseguindo matá-lo.

Especificidades: A flecha, se equipada, permanecerá na mão do herói caso o mesmo continue na mesma casa. Caso esse se mova, se for o local em que o Wumpus está, o herói terá 50% de chance de matar o monstro. Caso não haja um Wumpus na casa em que o herói se moveu, a flecha será perdida.

## Arquivos Java do Jogo O Mundo de Wumpus
[Arquivos](src/mc322/lab06)

## Detalhes de Arquitetura
Considerando os critérios de qualidades que serão avaliados:
```
1. A consistência do restante da solução com a arquitetura inicial proposta: evitar arranjos que desviam, 
subvertem ou simplificam o desenho da arquitetura inicial.

2. A distribuição da lógica do jogo pelas várias classes: tentem distribuir ao máximo as tarefas entre as 
classes e evitem centralização de papéis. Por exemplo, a classe Caverna deveria se preocupar em apenas coordenar
os demais componentes e deixar para eles as tarefas especializadas.

3. Delegue o máximo possível a cada objeto coisas que lhe dizem respeito, por exemplo, cabe ao objeto Herói saber
quantas flechas ele tem e se há flechas disponíveis para disparar.

4. Explore ao máximo o encapsulamento e evite tarefas que o subvertem.

5. Explore ao máximo o polimorfismo.

6. Evolua a arquitetura inicial de tal modo que ela seja fácil de expandir, por exemplo, se eu decidisse acrescentar
um novo tipo de classe Componente (digamos uma classe Teletransporte) o ideal seria que eu não precisasse modificar
ou modificasse o mínimo as demais classes, principalmente a classe Sala e Caverna. Isso funciona se se for deslocado
o máximo das especificidades para cada uma das subclasses de Componente e a Sala se comunicasse com eles sem nem saber
a que subclasse ela se reporta, explorando o polimorfismo.
```
a dupla NTC selecionou um trecho destaque do código do jogo "O Mundo de Wumpus".

### `Explorando o Encapsulamento nos métodos da classe Heroi`

~~~java
public class Heroi extends Componente {
    ...
    private boolean flechaEquipada;
    private int numFlechas;
    private boolean capturouOuro;
    ...
    public int getNumFlechas() { return numFlechas; }
    ...
    public void capturarOuro(){
        caverna.removeComponente(this.getI(), this.getJ());
        this.capturouOuro = true;
    }
    ...
    public boolean isFlechaEquipada() { return flechaEquipada; }
    public boolean heroiCapturouOuro() { return capturouOuro; }
}
~~~

Analisando os métodos da classe Heroi, percebemos que é posto em prática as propriedades do encapsulamento, principalmente, o fato de que
os atributos são expostos para classes/objetos externos através de métodos "getNomeDoMetodo" ou equivalentes. Isso permite que o objeto Heroi
mantenha sua consistência, mesmo após as mudanças de seus atributos - o que gera o solucionamento de um problema frequente de "danificação do
componente". Ademais, é nítida a distribuição de papéis e mantimento de uma lógica coerente, uma vez que o herói, e só o herói, tem acesso direto
número de flechas que lhe estão disponíveis, ou também se há flechas equipadas e até mesmo se foi capturado ou não o ouro.



