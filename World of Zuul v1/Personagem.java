/* 
 * A classe Personagem representa o jogador do jogo.
 * A classe Personagem tem atributos estáticos que pertencem à classe como um todo.
*/
public class Personagem {
    private static String nome;
    private static int vida;
    private static int pesoInventario;
    private static int pesoMaximo = 100;
    private static int dano;
    private static Personagem instanciaUnica;
    private Personagem(String nome, int vida, int dano){
        Personagem.nome = nome;
        Personagem.vida = vida;
        Personagem.dano = dano;
    }

    public static Personagem getInstancia(String nome, int vida, int dano){
        if(instanciaUnica == null){
            instanciaUnica = new Personagem(nome, vida, dano);
        }
        return instanciaUnica;
    }

    public static void setVida(){
        vida = 4000;
    }


    public static String getNome(){
        return Personagem.nome;
    }
    
    public static boolean adicionarItem(Item item){//Adiciona item, usa o peso como limite, 
        if(pesoInventario + item.getPeso() <= pesoMaximo){
            pesoInventario = pesoInventario + item.getPeso();
            return true;
        }else
        {
            return false;
        }
    }
    

    public static void sofreuAtaqueZumbi(int dano){//Diminui a vida do personagem conforme o dano do monstro
        Personagem.vida -= dano;
        
    }

    public static int getVida(){
        return Personagem.vida;
    }

    public static void atacarMonstro(Zumbi zumbi){//Ataca zumbo
        zumbi.sofreuAtaquePersonagem(dano);
    }
    public static void setDano(int dano){
        Personagem.dano = dano;
    }
    
}
