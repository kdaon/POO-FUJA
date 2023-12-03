/* 
 * A classe Personagem representa o jogador do jogo.
 * A classe Personagem tem atributos estáticos que pertencem à classe como um todo.
 * A classe Personagem representa um personagem do jogo que possui um inventário de itens.
*/


import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Personagem {
    private List<Item> inventario;


    public Personagem() {
        this.inventario = new ArrayList<>();  // Inicializa o inventário
    }

    /**
     * Adiciona um item ao inventário do personagem.
     *
     * @param item O item a ser adicionado ao inventário.
     */

    public void adicionarItem(Item item) {
        inventario.add(item);
    }

     /**
     * Remove um item do inventário do personagem.
     *
     * @param item O item a ser removido do inventário.
     */

    public void removerItem(Item item) {
        inventario.remove(item);
    }

    /**
     * Obtém uma lista imutável (não modificável) contendo todos os itens no inventário do personagem.
     *
     * @return A lista imutável de itens no inventário do personagem.
     */

    public List<Item> getInventario() {
        return Collections.unmodifiableList(inventario);
    }
    
    /**
     * Verifica se o personagem possui um determinado item no inventário.
     *
     * @param item O item a ser verificado se está presente no inventário.
     * @return true se o personagem possuir o item, false caso contrário.
     */

    public boolean possuiItem(Item item) {
        // Implemente a lógica para verificar se o personagem possui o item
        // Retorna true se o personagem possuir o item, false caso contrário
        return inventario.contains(item);
    }
    
}