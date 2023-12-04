
/**
 * A classe Gui é responsável por criar e controlar a interface gráfica do jogo.
 * Ela utiliza componentes Swing para criar uma janela com áreas de texto, input de comandos,
 * botão de confirmação, entre outros elementos, para interagir com o jogador durante o jogo.
 * Implementa a interface ActionListener para lidar com eventos de ação, como clicar no botão "OK".
 * @author Alexandra Melo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Gui implements ActionListener {
    private JFrame janela; // frame para criar a janela do jogo
    private JTextArea areaMensagens; // area para mostrar as mensagens do jogo
    private JTextArea areaMovimentos; // area para mostrar os movimentos ainda disponiveis para o jogador
    private JTextField campoEntrada; // input de comandos
    private JPanel painelAmbiente; // painel para mostra os ambientes do jogo e sinaliza em qual deles o jogador
                                   // esta
    private JPanel painelInteracao; // painel para mostrar as mensagens do jogo e o input de texto
    private JPanel painelMovimentos; // painel para mostrar os movimentos restantes para o jogador
    private JPanel painelInferior; // painel que contem o painel interacao e movimentos para ambos ficarem na
                                   // direcao sul
    private JLabel rotuloImagemMapa; // imagem do mapa
    private JButton botaoConfirmar; // botao para confirmar o input do jogador
    private String ultimoComando = "";
    // private int movimentos;

    /*
     * 
     * O Construtor da classe Gui inicializa os atributos e define suas propriedades
     * iniciais
     * 
     */
    public Gui() {
        janela = new JFrame("Jogo - Fuja!");
        areaMensagens = new JTextArea();
        areaMensagens.setEditable(false);

        campoEntrada = new JTextField();

        areaMovimentos = new JTextArea();
        areaMovimentos.setEditable(false);

        painelAmbiente = new JPanel();
        painelInteracao = new JPanel();
        painelMovimentos = new JPanel();
        painelInferior = new JPanel();

        ImageIcon imagemAmb = new ImageIcon(
                "Jogo - Fuja/mapa-jogo.png");
        rotuloImagemMapa = new JLabel(
                new ImageIcon(redimesionaImagem(imagemAmb)),
                SwingConstants.CENTER);

        botaoConfirmar = new JButton("OK");
        botaoConfirmar.addActionListener(this);

        montarJanela();
    }

    /*
     * 
     * Esse metodo define o tamanho da janela do jogo e adiciona seus componentes a
     * tela
     * 
     */
    public void montarJanela() {
        janela.setSize(1200, 700);
        janela.setLayout(new BorderLayout());

        criaPainelAmbiente();
        criaPainelInferior();

        janela.add(painelAmbiente, BorderLayout.CENTER);
        janela.add(painelInferior, BorderLayout.SOUTH);

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janela.pack();
    }

    /*
     * 
     * Esse metodo cria o painel de AMBIENTE, responsavel por exibir a imagem do
     * ambiente atualizada com a marcacao de onde o personagem está
     * 
     */
    public void criaPainelAmbiente() {
        painelAmbiente.setPreferredSize(new Dimension(620, 300));
        painelAmbiente.setLayout(new BoxLayout(painelAmbiente, BoxLayout.Y_AXIS));
        painelAmbiente.add(rotuloImagemMapa);
    }

    /*
     * Esse metodo cria o painel de INTERACAO DO USUARIO, que é responsavel por
     * exibir as mensagens do jogo e pegar os dados de comando digitados pelo
     * usuario
     * 
     */
    public void criaPainelInteracao() {
        painelInteracao.setPreferredSize(new Dimension(550, 400));
        painelInteracao.setLayout(new BoxLayout(painelInteracao, BoxLayout.Y_AXIS));
        areaMensagens.setPreferredSize(new Dimension(500, 300));
        campoEntrada.setPreferredSize(new Dimension(500, 50));
        botaoConfirmar.setPreferredSize(new Dimension(50, 30));
        painelInteracao.add(areaMensagens);
        painelInteracao.add(campoEntrada);
        painelInteracao.add(botaoConfirmar);
    }

    /*
     * 
     * Esse metodo cria o painel de MOVIMENTOS RESTANTES, responsavel por mostrar ao
     * usuario a quantidade de movimentos restantes que ele possui
     * 
     */
    public void criaPainelMovimentos() {
        painelMovimentos.setPreferredSize(new Dimension(150, 400));
        painelMovimentos.setLayout(new BoxLayout(painelMovimentos, BoxLayout.Y_AXIS));
        painelMovimentos.add(areaMovimentos);
    }

    /*
     * 
     * Esse metodo cria o painel INFERIOR, que contem os paineis de INTERACAO e
     * MOVIMENTOS, para que ambos fiquem juntos na parte sul da janela
     * 
     */
    public void criaPainelInferior() {
        painelInferior.setPreferredSize(new Dimension(680, 400));
        painelInferior.setLayout(new BoxLayout(painelInferior, BoxLayout.X_AXIS));
        criaPainelInteracao();
        criaPainelMovimentos();
        painelInferior.add(painelInteracao);
        painelInferior.add(painelMovimentos);
    }

    /*
     * 
     * Esse metodo sobrescreve o metodo actionPerformed responsavel por tratar o
     * clique feito em um botao. Nesse caso, ao clicar no botao, a funcao realizar
     * um set no valor do atributo ultimo comando com o novo comando que foi
     * digitado pelo usuario
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String ultimoComando = campoEntrada.getText();
        campoEntrada.setText("");
    }

    /*
     * 
     * Esse metodo retorna o valor do atributo ultimo comando, sendo o valor dele
     * igual ao ultimo comando enviado pelo usuario
     * 
     */
    public String getUltimoComando() {
        return ultimoComando;
    }

    /*
     * 
     * Esse metodo altera a mensagem do jogo que é exibida pelo usuario
     * 
     */
    public void setMensagem(String msg) {
        areaMensagens.setText("teste :" + msg);
        areaMensagens.repaint();
        areaMensagens.setCaretPosition(areaMensagens.getDocument().getLength());
    }

    /*
     * 
     * Esse metodo realiza a leitura do arquivo texto que guarda a quantidade de
     * movimentos restantes que o usuario possui
     * 
     * @param Embora o metodo nao possua parametro, é preciso passar a raiz do
     * caminho em que se encontra o arquivo movimentos.txt na variavel caminho
     * 
     */
    public String leArquivoMovimentos() {
        BufferedReader arq = null;
        String conteudo = "";
        String caminho = "movimentos.txt";

        try {
            arq = new BufferedReader(new FileReader(caminho));
            conteudo = arq.readLine();

            if (conteudo.equals("")) {
                conteudo = "";
            }

        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo");
        } finally {
            if (arq != null) {
                try {
                    arq.close();

                } catch (IOException e) {
                    System.err.println(e.getMessage());

                }
            }
        }

        return conteudo;

    }

    /*
     * 
     * Esse metodo modifica o valor da quantidade de movimentos restantes do jogador
     * que é exibida na tela
     * 
     */
    public void setQtdMovimentos() {
        areaMovimentos.setText("Movimentos restantes: \n" + leArquivoMovimentos());
    }

    /*
     * 
     * Esse metodo exibe a janela do jogo e a força a continuar aberta para que o
     * jogo nao se encerre inesperadamente
     * 
     */
    public void exibir() {
        // força a janela a ficar aberta
        SwingUtilities.invokeLater(() -> {
            janela.setVisible(true);
        });

        // Aguarda até que a janela seja fechada pelo usuário
        while (janela.isVisible()) {
            try {
                Thread.sleep(100); // Pausa para evitar alto uso da CPU
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 
     * Esse metodo redimensiona a imagem do ambiente do jogo
     * 
     */
    public Image redimesionaImagem(ImageIcon ambiente) {
        return ambiente.getImage().getScaledInstance(700, 300, Image.SCALE_SMOOTH);
    }

}