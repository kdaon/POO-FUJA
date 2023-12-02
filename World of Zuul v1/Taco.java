public class Taco extends Item {
    private int dano;

    public Taco(String nome, int dano, String descricao, String caminho, boolean carregavel, int peso) {
        super(nome, descricao, caminho, carregavel, peso);
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
