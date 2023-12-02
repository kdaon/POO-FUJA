/* 
 * A classe Personagem representa o jogador do jogo.
 * A classe Personagem tem atributos estáticos que pertencem à classe como um todo.
*/
public class Personagem {
    private static String nome;
    private static int vida;
   
    private static Personagem instanciaUnica;
    private Personagem(String nome, int vida){
        Personagem.nome = nome;
        Personagem.vida = vida;
 
    }

    public static Personagem getInstancia(String nome, int vida, int dano){
        if(instanciaUnica == null){
            instanciaUnica = new Personagem(nome, vida);
        }
        return instanciaUnica;
    }

    public static String getNome(){
        return Personagem.nome;
    }
    

    
}
