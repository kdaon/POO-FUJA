/**
 * A classe Inimigo representa um adversário no jogo.
 * Cada inimigo possui um nome, descrição e um item necessário para derrotá-lo.
 */

public class Inimigo extends ObjetoDoJogo {
    private Item itemDerrotar;

    public Inimigo(String nome, String descricao, Item itemDerrotar) {
        super(nome, descricao);
        this.itemDerrotar = itemDerrotar;
    }
    public void serDerrotado() {
        System.out.println("Você derrotou o " + getNome() + " usando " + itemDerrotar.getNome() + ".");
        itemDerrotar.usar();
    }

    public Item getItemDerrotar() {
        return itemDerrotar;
    }

    
}
