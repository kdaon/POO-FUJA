public class Item  {
    private String nome;
    private String descricao;
    private boolean carregavel;
    private int peso;

    public Item(String nome, String descricao, String caminho, boolean carregavel,int peso) {
    
        this.nome = nome;
        this.descricao = descricao;
        this.carregavel = carregavel;
        this.peso= peso;
        
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

    public int getPeso(){
        return peso;
    }

    // retorna se o objeto é carregavel
    public boolean carregavel() {
        return carregavel;
    }

    // retorna se o objeto é Arma
    public boolean arma() {
        return false;
    }

    // retorna se o objeto é kit medico
    public boolean kitMedico() {
        return false;
    }

    //retorna se é taco
        public boolean taco() {
        return false;
    }
        

}
