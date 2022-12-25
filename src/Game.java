import javax.swing.*;
import java.awt.*;

public class Frame {
    Grid grid = new Grid();

    public Frame() {
        super("Minesweeper");
        this.getContentPane().setBackground(new Color(192, 192, 192));
        setLayout(new GridLayout(9, 9, 3, 3));
        setSize(288, 361);
        for (int i = 0; i < 270; i ++) {
            {
                ImageIcon img = new ImageIcon("photos/Minesweeper_unopened_square.png");
                JButton resetButton = new JButton(img);
                resetButton.setPreferredSize(new Dimension(30, 30));
                add(resetButton);
//                g2.setColor(new Color(92, 201, 89));
//                g2.fill3DRect(2 + j, y + i, 28, 28, true);
            }
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
