/**
 * A classe Item representa um objeto ou ferramenta que pode ser usado no jogo.
 * Cada item possui um nome e uma descrição.
 * @author Gabriela Memento e Olivia Campos
 */

public class Item extends ObjetoDoJogo {

    public Item(String nome, String descricao) {
        super(nome, descricao);
    }

    /**
     * Método que simula o uso do item, exibindo uma mensagem informando o nome do item usado.
     */
    
    public void usar() {
        System.out.println("Você usou o item: " + getNome());
    }

}

