import javax.swing.*;
import java.awt.*;

public class teste {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minha Janela");
        frame.setSize(400, 300); // Tamanho
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar o programa ao fechar a janela
        frame.setVisible(true); // Exibir a janela

        JPanel panel = new JPanel();
        frame.add(panel);

        JDialog dialog = new JDialog(frame, "Meu Pop-up", true);
        dialog.setSize(200, 150);
        dialog.setVisible(true);

        JButton button = new JButton("Clique aqui");
        JLabel label = new JLabel("Texto aqui");
        JTextField textField = new JTextField(20); // 20 é o tamanho do campo


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem newItem = new JMenuItem("Novo");
        fileMenu.add(newItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);








    }
}
