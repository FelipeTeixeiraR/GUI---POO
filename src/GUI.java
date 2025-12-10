import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    public static void main(String[] args) {

        Jogar j1 = new Jogar();

        JFrame janela = new JFrame("Jogo");
        janela.setSize(400, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);


        JPanel painel = new JPanel();
        painel.setBackground(new Color(250, 250, 240));
        painel.setBounds(0, 0, 400, 300);


        JLabel msg1 = new JLabel("Quantidade de jogadores:");
        msg1.setFont(new Font("Arial", Font.BOLD, 14));
        msg1.setBounds(70, 20, 300, 25);
        msg1.setForeground(new Color(50, 50, 50));

        JLabel msg2 = new JLabel("Número de rodadas:");
        msg2.setBounds(70, 70, 300, 25);
        msg2.setFont(new Font("Arial", Font.BOLD, 14));
        msg2.setForeground(new Color(50, 50, 50));

        JLabel msg3 = new JLabel("Dificuldade (1-3):");
        msg3.setBounds(70, 120, 300, 25);
        msg3.setFont(new Font("Arial", Font.BOLD, 14));
        msg3.setForeground(new Color(50, 50, 50));


        JTextField entrada1 = new JTextField();
        entrada1.setBounds(70, 40, 200, 25);
        entrada1.setFont(new Font("Arial", Font.PLAIN, 14));
        entrada1.setBackground(new Color(255, 255, 255));
        entrada1.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JTextField entrada2 = new JTextField();
        entrada2.setBounds(70, 90, 200, 25);
        entrada2.setFont(new Font("Arial", Font.PLAIN, 14));
        entrada2.setBackground(new Color(255, 255, 255));
        entrada2.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JTextField entrada3 = new JTextField();
        entrada3.setBounds(70, 140, 200, 25);
        entrada3.setFont(new Font("Arial", Font.PLAIN, 14));
        entrada3.setBackground(new Color(255, 255, 255));
        entrada3.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));


        JButton btn1 = new JButton("Confirmar");
        btn1.setBounds(100, 180, 120, 30);
        btn1.setFont(new Font("Arial", Font.BOLD, 14));
        btn1.setBackground(new Color(97, 219, 44));
        btn1.setForeground(Color.WHITE);
        btn1.setFocusPainted(false);
        btn1.setBorder(BorderFactory.createEmptyBorder());


        JLabel lblConta = new JLabel("");
        lblConta.setBounds(100, 230, 540, 25);
        lblConta.setFont(new Font("Arial", Font.PLAIN, 16));
        lblConta.setForeground(new Color(50, 50, 50));

        JTextField resposta = new JTextField();
        resposta.setBounds(100, 260, 150, 25);
        resposta.setVisible(false);
        resposta.setFont(new Font("Arial", Font.PLAIN, 14));
        resposta.setBackground(new Color(255, 255, 255));
        resposta.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JButton btnResponder = new JButton("Responder");
        btnResponder.setBounds(180, 260, 120, 25);
        btnResponder.setVisible(false);
        btnResponder.setFont(new Font("Arial", Font.BOLD, 14));
        btnResponder.setBackground(new Color(70, 130, 180));
        btnResponder.setForeground(Color.WHITE);
        btnResponder.setFocusPainted(false);
        btnResponder.setBorder(BorderFactory.createEmptyBorder());

        // Placar final
        JLabel resultados = new JLabel("<html></html>");
        JScrollPane scroll = new JScrollPane(resultados);
        scroll.setBounds(20, 300, 540, 150);
        scroll.setVisible(true);

        // Listas de contas e respostas
        List<String> contas = new ArrayList<>();
        List<Integer> respostasJogador = new ArrayList<>();
        final int[] idx = {0};

        btn1.addActionListener(e -> {
            int qtd = Integer.parseInt(entrada1.getText());
            int rod = Integer.parseInt(entrada2.getText());
            int dif = Integer.parseInt(entrada3.getText());

            List<String> nomes = quantNomePopUps(qtd);

            j1.criarJogadoresAutomaticamente(qtd, rod, dif, nomes);


            contas.clear();
            contas.addAll(j1.pegarTextosParaGUI());

            lblConta.setText(contas.get(0));
            lblConta.setBounds(55, 50, 500, 25);
            resposta.setVisible(true);
            resposta.setBounds(85, 90, 200, 25);
            btnResponder.setVisible(true);
            btnResponder.setBounds(120, 130, 120, 25);

            //
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
                ArrayList<String> res = j1.iniciarComRespostas(respostasJogador);

                StringBuilder html = new StringBuilder("<html>");
                for (String s : res) html.append(s).append("<br>");
                html.append("</html>");

                resultados.setText(html.toString());
                scroll.setVisible(true);
                scroll.setBounds(100, 60, 200, 100);

                lblConta.setText("Jogo finalizado!");
                lblConta.setFont(new Font("Arial", Font.BOLD, 30));
                lblConta.setBounds(90, 10, 540, 25);
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


        janela.add(painel);
        janela.setVisible(true);
    }

    public static List<String> quantNomePopUps(int quantJogadores) {
        List<String> nomes = new ArrayList<>();

        for (int i = 0; i < quantJogadores; i++) {
            nomes.add(JOptionPane.showInputDialog("Nome do jogador " + (i + 1) + ":"));
        }
        return nomes;
    }
}
