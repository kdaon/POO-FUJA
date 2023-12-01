public class Arma extends Item {
    private int dano;

    public Arma(String nome, int dano, String descricao, String caminho, boolean carregavel) {
        super(nome, descricao, caminho, carregavel);
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    @Override
    public boolean arma() {
        return true;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + "  dano " + getDano();
    }
}
