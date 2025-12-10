import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TelaSimples {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JFrame janela = new JFrame("Primeira tela");
        janela.setSize(400, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);

        JButton btn = new JButton("Clique aqui");
        btn.setBounds(120,100, 150, 30);

        JLabel mnsg = new JLabel("Hello world!");
        mnsg.setBounds(80, 130, 300, 30);
        mnsg.setVisible(false);
        JTextField campo = new JTextField("Escreva o seu texto:");
        campo.setBounds(120, 80, 200, 20);
        String mmnsg = campo.getText();

        //Parte de logica de botoes:

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (mnsg.isVisible()){
                    mnsg.setVisible(false);
                }else {
                    mnsg.setVisible(true);
                }
            }
        });


        // Parte para fazer as coisas aparecerem na tela:
        janela.add(btn);
        janela.add(mnsg);
        janela.add(campo);
        campo.setVisible(true);
        janela.setVisible(true);
    }
}
