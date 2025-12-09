import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    public static void main(String[] args) {

        Jogar j1 = new Jogar();

        JFrame janela = new JFrame("Jogo");
        janela.setSize(600, 500);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);

        JLabel msg1 = new JLabel("Quantidade de jogadores:");
        msg1.setBounds(20, 20, 300, 25);

        JLabel msg2 = new JLabel("Número de rodadas:");
        msg2.setBounds(20, 70, 300, 25);

        JLabel msg3 = new JLabel("Dificuldade (1-3):");
        msg3.setBounds(20, 120, 300, 25);

        JTextField entrada1 = new JTextField();
        entrada1.setBounds(20, 40, 150, 25);

        JTextField entrada2 = new JTextField();
        entrada2.setBounds(20, 90, 150, 25);

        JTextField entrada3 = new JTextField();
        entrada3.setBounds(20, 140, 150, 25);

        JButton btn1 = new JButton("Confirmar");
        btn1.setBounds(20, 180, 120, 30);

        JLabel lblConta = new JLabel("");
        lblConta.setBounds(20, 230, 540, 25);

        JTextField resposta = new JTextField();
        resposta.setBounds(20, 260, 150, 25);
        resposta.setVisible(false);

        JButton btnResponder = new JButton("Responder");
        btnResponder.setBounds(180, 260, 120, 25);
        btnResponder.setVisible(false);

        // Placar final com ScrollPane (flexível)
        JLabel resultados = new JLabel("<html></html>");
        JScrollPane scroll = new JScrollPane(resultados);
        scroll.setBounds(20, 300, 540, 150);
        scroll.setVisible(false);

        List<String> contas = new ArrayList<>();
        List<Integer> respostasJogador = new ArrayList<>();
        final int[] idx = {0};

        btn1.addActionListener(e -> {
            int qtd = Integer.parseInt(entrada1.getText());
            int rod = Integer.parseInt(entrada2.getText());
            int dif = Integer.parseInt(entrada3.getText());

            List<String> nomes = quantNomePopUps(qtd);

            j1.criarJogadoresAutomaticamente(qtd, rod, dif, nomes);

            // AO GERAR AS CONTAS, Jogar.pegarTextosParaGUI() também preenche perguntasGeradas
            contas.clear();
            contas.addAll(j1.pegarTextosParaGUI());


            lblConta.setText(contas.get(0));
            resposta.setVisible(true);
            btnResponder.setVisible(true);

            msg1.setVisible(false); msg2.setVisible(false); msg3.setVisible(false);
            entrada1.setVisible(false); entrada2.setVisible(false); entrada3.setVisible(false);
            btn1.setVisible(false);
        });

        btnResponder.addActionListener(e -> {
            String texto = resposta.getText().trim();

            try {
                int val = Integer.parseInt(texto);
                respostasJogador.add(val);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Resposta inválida. Digite apenas números.");
                return;
            }

            resposta.setText("");
            idx[0]++;

            if (idx[0] >= contas.size()) {
                // Usa a lista de perguntas geradas internamente para verificar corretamente
                ArrayList<String> res = j1.iniciarComRespostas(respostasJogador);

                StringBuilder html = new StringBuilder("<html>");
                for (String s : res) html.append(s).append("<br>");
                html.append("</html>");

                resultados.setText(html.toString());
                scroll.setVisible(true);

                lblConta.setText("Jogo finalizado!");
                resposta.setVisible(false);
                btnResponder.setVisible(false);

            } else {
                lblConta.setText(contas.get(idx[0]));
            }
        });

        janela.add(msg1); janela.add(msg2); janela.add(msg3);
        janela.add(entrada1); janela.add(entrada2); janela.add(entrada3);
        janela.add(btn1);
        janela.add(lblConta);
        janela.add(resposta);
        janela.add(btnResponder);
        janela.add(scroll);
        janela.setVisible(true);
    }

    public static List<String> quantNomePopUps(int quantJogadores){
        List<String> nomes = new ArrayList<>();

        for (int i = 0; i < quantJogadores; i++) {
            nomes.add(JOptionPane.showInputDialog("Nome do jogador " + (i + 1) + ":"));
        }
        return nomes;
    }
}
