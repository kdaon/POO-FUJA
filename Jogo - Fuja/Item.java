/**
 * A classe Item representa um objeto ou ferramenta que pode ser usado no jogo.
 * Cada item possui um nome e uma descrição.
 * @author Gabriela
 */

public class Item {
    private String nome;
    private String descricao;

    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Método que simula o uso do item, exibindo uma mensagem informando o nome do item usado.
     */
    
    public void usar() {
        System.out.println("Você usou o item: " + nome);
    }

}
