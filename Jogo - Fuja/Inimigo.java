/**
 * A classe Inimigo representa um adversário no jogo.
 * Cada inimigo possui um nome, descrição e um item necessário para derrotá-lo.
 * @author Gabriela Memento e Olivia Campos
 */

public class Inimigo extends ObjetoDoJogo {
    private Item itemDerrotar;

    public Inimigo(String nome, String descricao, Item itemDerrotar) {
        super(nome, descricao);
        this.itemDerrotar = itemDerrotar;
    }

    // Método que simula a derrota do inimigo, exibindo uma mensagem informando o nome do inimigo derrotado e o nome do item usado para derrotá-lo
    public void serDerrotado() {
        System.out.println("Você derrotou o " + getNome() + " usando " + itemDerrotar.getNome() + ".");
        itemDerrotar.usar();
    }

    // Retorna o item necessário para derrotar o inimigo
    public Item getItemDerrotar() {
        return itemDerrotar;
    }

    
}
