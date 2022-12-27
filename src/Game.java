import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;

public class Game extends JFrame {
    private ImageIcon imageIcon;
    public Game() {
        super("Minesweeper");
        imageIcon = new ImageIcon("photos/minesweepericon.png");
        setIconImage(imageIcon.getImage());
        setBackground(new Color(192, 192, 192));
        add(new Grid());
        setVisible(true);
        setResizable(false);
        setSize(288, 310);
        setLocation(816,385);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}