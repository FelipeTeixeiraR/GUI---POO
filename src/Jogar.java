import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Jogar {
    ArrayList<Jogador> jogadores = new ArrayList<>();
    private int numeroRodads;
    private int dificuldade;

    public void criarJogadoresAutomaticamente() {
        JFrame janela = new JFrame("Login");

        JPanel meuPainel = new JPanel();
        LayoutManager layout = new BoxLayout(meuPainel, BoxLayout.Y_AXIS);
        meuPainel.setLayout(layout);
        meuPainel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        janela.add(meuPainel);
        janela.setSize(300, 200);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(janela.DISPOSE_ON_CLOSE);

        JLabel quantidadeJogadores = new JLabel("Digite quantos jogadores esta partida tera: ");
        JTextField quantJogStr = new JTextField();
        meuPainel.add(quantidadeJogadores);
        meuPainel.add(quantJogStr);

        JLabel numeroPartidas = new JLabel("Digite quantas rodadas essa partida tera: ");
        JTextField numeroRodadasStr = new JTextField();
        meuPainel.add(numeroPartidas);
        meuPainel.add(numeroRodadasStr);

        JLabel dificuldadePartida = new JLabel("Digite a dificuldade da partida;\n1- Facil\n2- Mediana\n3- Dificil");
        JTextField dificuldadeStr = new JTextField();
        meuPainel.add(dificuldadePartida);
        meuPainel.add(dificuldadeStr);

        // Adicionando o botão "Iniciar Jogo"
        JButton botaoEntrar = new JButton("Iniciar Jogo");
        meuPainel.add(botaoEntrar);

        // ActionListener para o botão
        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantJog = 0;
                int numeroRodadas = 0;
                int dificuldade = 0;

                // Verificar se os campos não estão vazios antes de tentar converter os valores
                if (!quantJogStr.getText().isEmpty()) {
                    quantJog = Integer.parseInt(quantJogStr.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira a quantidade de jogadores!");
                    return; // Encerra o método se o campo estiver vazio
                }

                if (!numeroRodadasStr.getText().isEmpty()) {
                    numeroRodadas = Integer.parseInt(numeroRodadasStr.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o número de rodadas!");
                    return;
                }

                if (!dificuldadeStr.getText().isEmpty()) {
                    dificuldade = Integer.parseInt(dificuldadeStr.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira a dificuldade da partida!");
                    return;
                }

                // Agora, criamos os jogadores
                for (int i = 0; i < quantJog; i++) {
                    String nomeJogador = JOptionPane.showInputDialog("Digite o nome do jogador " + (i + 1) + ": ");
                    Jogador j = new Jogador(i + 1, nomeJogador);
                    cadastrarJogadores(j);  // Chama o método para adicionar jogadores
                }

                // Inicia a partida após a criação dos jogadores e configurações
                iniciar(numeroRodadas, dificuldade);

                // Chama o método para mostrar a pontuação final
                pontuacaoFinal();
            }
        });
    }

    // Método para cadastrar um jogador
    public void cadastrarJogadores(Jogador j) {
        jogadores.add(j);  // Adiciona o jogador na lista de jogadores
    }

    // Método para iniciar o jogo com rodadas e dificuldade
    public void iniciar(int numeroRodadas, int dificuldade) {
        Pergunta p;
        for (int i = 0; i < numeroRodadas; i++) {
            for (int a = 0; a < jogadores.size(); a++) {
                switch (dificuldade) {
                    case 1:
                        p = new PerguntaFacil();
                        break;
                    case 2:
                        p = new PerguntaMediana();
                        break;
                    case 3:
                        p = new PerguntaDificil();
                        break;
                    default:
                        p = new PerguntaFacil();
                        break;
                }

                Jogador j = jogadores.get(a);
                JOptionPane.showMessageDialog(null, "--- Rodada numero " + (i + 1) + " ---\nVez do jogador " + j.getId() + ", " + j.getNome() + ": ");

                p.criarPergunta();
                String resposta = JOptionPane.showInputDialog(p.mostrarPergunta() + " = ");
                int respJogador = Integer.parseInt(resposta);

                if (p.verificarResposta(respJogador)) {
                    j.adicionarPontos(5);
                    JOptionPane.showMessageDialog(null, "Acertou!!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Errou!!!");
                }

                JOptionPane.showMessageDialog(null, "O jogador " + j.getId() + ", " + j.getNome() + " tem " + j.getPontos() + " Pontos!");
            }
        }
    }

    // Método para mostrar a pontuação final de todos os jogadores
    public void pontuacaoFinal() {
        // Ordena os jogadores pela pontuação em ordem decrescente
        Collections.sort(jogadores, new Comparator<Jogador>() {
            @Override
            public int compare(Jogador j1, Jogador j2) {
                return Integer.compare(j2.getPontos(), j1.getPontos());
            }
        });

        // Criar um texto com a tabela de pontuação
        StringBuilder resultado = new StringBuilder("--- Tabela Final de Pontuação ---\n");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            resultado.append((i + 1) + ". Jogador " + j.getId() + ", " + j.getNome() + " : " + j.getPontos() + " pontos\n");
        }

        // Exibe o resultado final em uma caixa de mensagem
        JOptionPane.showMessageDialog(null, resultado.toString(), "Resultado Final", JOptionPane.INFORMATION_MESSAGE);
    }

    public int getNumeroRodads() {
        return numeroRodads;
    }

    public int getDificuldade() {
        return dificuldade;
    }
}
