/* 
 * A classe Personagem representa o jogador do jogo.
 * A classe Personagem tem atributos estáticos que pertencem à classe como um todo.
*/
public class Personagem {
    private String nome;
    private String descricao;
    private int vida;

    public Personagem(String nome, String descricao, int vida) {
        this.nome = nome;
        this.descricao = descricao;
        this.vida = vida;
    }

    public void restaurarVida() {
        vida = 100;
    }

    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean eleFala() {
        return false;
    }

    public boolean estaVivo() {
        if (vida > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean foiAtacado(int dano) {
        vida -= dano;
        return true;
    }

    
}
