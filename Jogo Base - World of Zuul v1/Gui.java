/*
 * @author Alexandra Melo
 * 
 * Esta classe implementa a inteface gráfica do usuário
 * 
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    // construtor inicializa todos os atributos da GUI
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
                "/Users/alexandramelo/Documents/interface-grafica-PPOO/interface/src/imagens/quarto.jpeg");
        rotuloImagemMapa = new JLabel(
                new ImageIcon(redimesionaImagem(imagemAmb)),
                SwingConstants.CENTER); // inicializado
        // com a
        // imagem do
        // quarto
        // pois o jogo comeca no quarto
        botaoConfirmar = new JButton("OK");
        botaoConfirmar.addActionListener(this);

        montarJanela();
    }

    // monta a janela do jogo
    public void montarJanela() {
        janela.setSize(800, 700);
        janela.setLayout(new BorderLayout());

        criaPainelAmbiente();
        criaPainelInferior();

        janela.add(painelAmbiente, BorderLayout.CENTER);
        janela.add(painelInferior, BorderLayout.SOUTH);

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janela.pack();
    }

    // cria painel de ambiente
    public void criaPainelAmbiente() {
        painelAmbiente.setPreferredSize(new Dimension(620, 300));
        painelAmbiente.setLayout(new BoxLayout(painelAmbiente, BoxLayout.Y_AXIS));
        painelAmbiente.add(rotuloImagemMapa);
    }

    // cria separadamente o painel de interacao
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

    // cria separadamente o painel de movimentos
    public void criaPainelMovimentos() {
        painelMovimentos.setPreferredSize(new Dimension(100, 400));
        painelMovimentos.setLayout(new BoxLayout(painelMovimentos, BoxLayout.Y_AXIS));
        painelMovimentos.add(areaMovimentos);
    }

    // cria o painel inferior unindo o painel de movimentos e painel de interacao
    public void criaPainelInferior() {
        painelInferior.setPreferredSize(new Dimension(680, 400));
        painelInferior.setLayout(new BoxLayout(painelInferior, BoxLayout.X_AXIS));
        criaPainelInteracao();
        criaPainelMovimentos();
        painelInferior.add(painelInteracao);
        painelInferior.add(painelMovimentos);
    }

    // trata o clique no botao 'OK'
    @Override
    public void actionPerformed(ActionEvent e) {
        String ultimoComando = campoEntrada.getText();
        campoEntrada.setText("");
    }

    // retorna o ultimo comando
    public String getUltimoComando() {
        return ultimoComando;
    }

    // passa uma nova mensagem para ser exibida na tela
    public void setMensagem(String msg) {
        areaMensagens.setText(msg);
        areaMensagens.repaint();
        areaMensagens.setCaretPosition(areaMensagens.getDocument().getLength());
    }

    // muda a imagem do ambiente de acordo com o comando do usuario
    public void ambienteMudou(String amb) {

        ImageIcon img = new ImageIcon(
                "Users/alexandramelo/Documents/interface-grafica-PPOO/interface/src/imagens/banheiro.jpeg");
        rotuloImagemMapa.setIcon(new ImageIcon(redimesionaImagem(img)));

        painelAmbiente.revalidate();
        painelAmbiente.repaint();
    }

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

    public Image redimesionaImagem(ImageIcon ambiente) {
        return ambiente.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
    }

}
