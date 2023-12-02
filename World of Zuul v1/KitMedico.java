public class KitMedico extends Item  {
    public KitMedico(String nome, String descricao, String caminho, boolean carregavel, int peso) {
        super(nome, descricao, caminho, carregavel,peso);
    }

    @Override
    public boolean kitMedico() {
        return true;
    }
    
}
