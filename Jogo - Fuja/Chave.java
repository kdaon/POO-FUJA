public class Chave extends Item {

    public Chave(String nome, String descricao, String caminho, boolean carregavel, int peso) {
        super(nome, descricao, caminho, carregavel,peso);
    }

    @Override
    public boolean chave() {
        return true;
    }

}
