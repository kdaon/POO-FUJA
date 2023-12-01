public class Chave extends Item {

    public Chave(String nome, String descricao, String caminho, boolean carregavel) {
        super(nome, descricao, caminho, carregavel);
    }

    @Override
    public boolean chave() {
        return true;
    }

}