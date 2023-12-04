/* 
 * A classe Personagem representa o jogador do jogo.
 * A classe Personagem tem atributos estáticos que pertencem à classe como um todo.
 * A classe Personagem representa um personagem do jogo que possui um inventário de itens.
 * 
 * @author Olivia Campos
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Personagem {
    private List<Item> inventario;


    public Personagem() {
        this.inventario = new ArrayList<>();  // Inicializa o inventário
    }

    // Adiciona um item ao inventário
    public void adicionarItem(Item item) {
        inventario.add(item);
    }

    // Remove um item do inventário
    public void removerItem(Item item) {
        inventario.remove(item);
    }

    // Retorna o inventário
    public List<Item> getInventario() {
        return Collections.unmodifiableList(inventario);
    }

    // Verifica se o personagem possui um item
    public boolean possuiItem(Item item) {
        return inventario.contains(item);
    }
    
}