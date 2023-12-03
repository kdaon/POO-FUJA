public class Inimigo extends Personagem{
    private int dano;

    public Inimigo(String nome, String descricao, int vida, int dano) {
        super(nome, descricao, vida);
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }
  
}
