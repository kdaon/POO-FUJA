/**
 * A classe Inimigo representa um adversário no jogo.
 * Cada inimigo possui um nome, descrição e um item necessário para derrotá-lo.
 */

public class Inimigo {
    private String nome;
    private String descricao;
    private Item itemDerrotar;


    public Inimigo(String nome, String descricao, Item itemDerrotar) {
        this.nome = nome;
        this.descricao = descricao;
        this.itemDerrotar = itemDerrotar;
    }
    /**
     * Método chamado quando o inimigo é derrotado.
     * Exibe uma mensagem indicando a derrota do inimigo e usa o item necessário para derrotá-lo.
     */
    public void serDerrotado() {
        System.out.println("Você derrotou o " + nome + " usando " + itemDerrotar.getNome() + ".");
        itemDerrotar.usar();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
    /**
     * Obtém o item necessário para derrotar o inimigo.
     *
     * @return O item necessário para derrotar o inimigo.
     */
    public Item getItemDerrotar() {
        return itemDerrotar;
    }

}
