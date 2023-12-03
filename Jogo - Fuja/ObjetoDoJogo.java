/**
 * Classe que representa um objeto do jogo.
 * @author Gabriela Memento
 */
public class ObjetoDoJogo {
    private String nome;
    private String descricao;

    public ObjetoDoJogo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
}
