/* 
 * A classe Zumbi representa o mostro do jogo o qual pode ser atacado pelo personagem do jogo ou
 * o Zumbi pode atacar o personagem
*/

public class Zumbi {
    private String nome;
    private int forca;
    private int vida;
    private int danoSofrido;
    
    public Zumbi(String nome, int forca, int vida){
        this.nome = nome;
        this.forca = forca;
        this.vida = vida;
    }

    public String getDescricaoMonstro(){
        String descricaoZumbi = "O zumbi se chama  "+nome+" tem forca "+forca+" e total de vida e "+vida;
        return descricaoZumbi;
    }

    public int getForca(){
        return this.forca;
    }

    public String getNome(){
        return this.nome;
    }

    public int getVida(){
        return this.vida;
    }

    public void sofreuAtaquePersonagem(int dano){//Diminui a vida do monstro conforme o dano do personagem
        danoSofrido = dano;
        this.vida -= danoSofrido;
    }

    public void atacouPersonagem(){//Ataca o personagem
        Personagem.sofreuAtaqueZumbi(forca);
    }

}
