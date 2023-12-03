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
 * /**
 * A classe Ambiente representa uma localização no jogo, contendo informações sobre a descrição do ambiente,
 * suas saídas para outras áreas, a presença de inimigos, itens e se está trancado ou não.
 */
 



 // método ajustarSaidas
import java.util.ArrayList;

public class Ambiente {
    public String descricao;
    public Ambiente saidaNorte;
    public Ambiente saidaSul;
    public Ambiente saidaLeste;
    public Ambiente saidaOeste;
    private boolean ambienteTrancado;
    private String chave;

    private ArrayList<Inimigo> inimigos;
    private Item item;

    /**
     * Cria um novo ambiente com a descrição fornecida.
     *
     * @param descricao A descrição do ambiente.
     */

    public Ambiente(String descricao) {
        this.inimigos = new ArrayList<>();
        this.descricao = descricao;
        this.ambienteTrancado = false;
        this.chave = "";
    }
    /**
     * Define as saídas deste ambiente para outras áreas.
     *
     * @param saidas Ambientes vizinhos para as direções norte, leste, sul e oeste.
     */

    public void ajustarSaidas(Ambiente... saidas) {
        if (saidas.length >= 1) {
            saidaNorte = saidas[0];
        }
        if (saidas.length >= 2) {
            saidaLeste = saidas[1];
        }
        if (saidas.length >= 3) {
            saidaSul = saidas[2];
        }
        if (saidas.length >= 4) {
            saidaOeste = saidas[3];
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public Inimigo getInimigo() {
        if (inimigos.size() == 0) {
            return null;
        }
        return inimigos.get(0);
    }

    public void removerInimigo(Inimigo inimigo) {
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
     // Métodos para manipulação de inimigos, itens e travamento do ambiente...

    /**
     * Verifica se o ambiente está trancado.
     *
     * @return true se o ambiente está trancado, caso contrário, false.
     */

    public boolean estaTrancado() {
        return this.ambienteTrancado;
    }

    /**
     * Obtém a chave usada para trancar o ambiente.
     *
     * @return A chave usada para trancar o ambiente.
     */

    public String getChave() {
        return chave;
    }
}

