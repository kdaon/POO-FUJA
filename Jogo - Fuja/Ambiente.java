/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
import java.util.ArrayList;
import java.util.HashMap;

 public class Ambiente {

    private HashMap<String, Ambiente> saidas;
    public String descricao;
    
    private boolean ambienteTrancado;
    private String chave;
    private ArrayList<Inimigo> inimigos;
    private Item item;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) {
        
        this.inimigos = new ArrayList<>();
        this.descricao = descricao;
        this.ambienteTrancado = false;
        this.chave = "";
        saidas = new HashMap<String, Ambiente>();

    }

    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaidas(String direcao, Ambiente ambiente) {
        saidas.put(direcao, ambiente);
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }

    public Inimigo getInimigo() {
        if (inimigos.size() == 0) {
            return null;
        }
        return inimigos.get(0);  // Retorna o primeiro inimigo da lista (assumindo apenas um inimigo por ambiente)
    }
    
    public void removerInimigo(Inimigo inimigo) {
        // Lógica para remover o inimigo do ambiente
        this.inimigos = new ArrayList<>();
    }

    public void adicionarInimigo(Inimigo inimigo) {
        inimigos.add(inimigo);
    }

    public void adicionarItem(Item item) {
        this.item = item;
    }

    public void removerItem() {
        this.item = null;
    }

    public Item getItem() {
        return item;
    }

    public void trancarAmbiente(String chave) {
        this.ambienteTrancado = true;
        this.chave = chave;
    }

    public void destrancarAmbiente(String chave) {
        if (this.chave.equals(chave)) {
            this.ambienteTrancado = false;
            System.out.println("O ambiente foi destrancado.");
        }
    }

    public boolean estaTrancado() {
        return this.ambienteTrancado;
    }

    public String getChave() {
        return chave;
    }

}
