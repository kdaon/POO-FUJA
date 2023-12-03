public class Inimigo {
    private String nome;
    private String descricao;
    private Item itemDerrotar;

    public Inimigo(String nome, String descricao, Item itemDerrotar) {
        this.nome = nome;
        this.descricao = descricao;
        this.itemDerrotar = itemDerrotar;
    }

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

    public Item getItemDerrotar() {
        return itemDerrotar;
    }

}
