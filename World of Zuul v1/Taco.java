public class Taco extends Item  {
    public Taco(String nome, String descricao, String caminho, boolean carregavel, int peso) {
        super(nome, descricao, caminho, carregavel,peso);
    }

    @Override
    public boolean taco() {
        return true;
    }
    
}
