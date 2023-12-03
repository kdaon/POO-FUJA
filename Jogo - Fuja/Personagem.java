/* 
 * A classe Personagem representa o jogador do jogo.
 * A classe Personagem tem atributos estáticos que pertencem à classe como um todo.
*/
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Personagem {
    private List<Item> inventario;

    public Personagem() {
        this.inventario = new ArrayList<>();  // Inicializa o inventário
    }

    public void adicionarItem(Item item) {
        inventario.add(item);
    }

    public void removerItem(Item item) {
        inventario.remove(item);
    }

    public List<Item> getInventario() {
        return Collections.unmodifiableList(inventario);
    }

    public boolean possuiItem(Item item) {
        // Implemente a lógica para verificar se o personagem possui o item
        // Retorna true se o personagem possuir o item, false caso contrário
        return inventario.contains(item);
    }
    
}