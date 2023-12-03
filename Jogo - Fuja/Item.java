/**
 * Classe Item  representa os objetos contidos no jogo
 * Como arma, taco e kit medico 
 * @author Gabriela
**/

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

    public void usar() {
        System.out.println("Você usou o item: " + nome);
    }

}
