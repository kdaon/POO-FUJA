public class Item  {
    private String nome;
    private String descricao;
    private boolean carregavel;
    //String caminho;

    public Item(String nome, String descricao, String caminho, boolean carregavel) {
       //super(caminho);
        this.nome = nome;
        this.descricao = descricao;
        this.carregavel = carregavel;
        //this.caminho= caminho;
    }

    public boolean chave() {
        return false;
    }

    
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    // retorna se o objeto é carregavel
    public boolean carregavel() {
        return carregavel;
    }

    // retorna se o objeto é Arma
    public boolean arma() {
        return false;
    }



}
