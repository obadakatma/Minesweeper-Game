import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Grid extends JPanel {
    Random random = new Random();
    Image image;
    private final int[][] gridArray = new int[9][9];
    private static final ArrayList<Button> buttons = new ArrayList<Button>();
    private final ArrayList<Boolean> bomb = new ArrayList<Boolean>(81);
    private final ArrayList<Integer> randomBomb = new ArrayList<Integer>(10);
    private final ArrayList<Boolean> buttonPressed = new ArrayList<Boolean>(81);
    final private int x = 0;
    final private int y = 0;
    private int state;


    public Grid() {
        for (int i = 0; i < 81; i++) {
            bomb.add(false);
            buttonPressed.add(true);
        }
        while (randomBomb.size() < 10) {
            int randomNum = random.nextInt(81);
            if (!randomBomb.contains(randomNum)) {
                randomBomb.add(randomNum);
            }
            bomb.set(randomNum, true);
        }
        for (int i = 0; i < 81; i++) {
            if (bomb.get(i)) {
                System.out.println(i + 1);
            }
        }
    }

    public void setState(int state) {
        this.state = state;
    }

    public void bombPlace() {
        int f = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (bomb.get(f))
                    gridArray[i][j] = 10;
                f++;
            }
        }
    }

    public void setValues() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int ct = 0;
                //left
                if (i > 0 && gridArray[i - 1][j] == 10)
                    ct++;
                //right
                if (i < 9 - 1 && gridArray[i + 1][j] == 10)
                    ct++;
                //up
                if (j > 0 && gridArray[i][j - 1] == 10)
                    ct++;
                //down
                if (j < 9 - 1 && gridArray[i][j + 1] == 10)
                    ct++;
                //top left
                if (i > 0 && j > 0 && gridArray[i - 1][j - 1] == 10)
                    ct++;
                //top right
                if (i < 9 - 1 && j > 0 && gridArray[i + 1][j - 1] == 10)
                    ct++;
                //bottom left
                if (i > 0 && j < 9 - 1 && gridArray[i - 1][j + 1] == 10)
                    ct++;
                //bottom right
                if (i < 9 - 1 && j < 9 - 1 && gridArray[i + 1][j + 1] == 10)
                    ct++;
                gridArray[i][j] = ct;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(192, 192, 192));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.GRAY);
        g2.drawRect(x, y, 270, 270);
        for (int i = 30; i < 270; i += 30) {
            g2.drawLine(i, y, i, 270);
            g2.drawLine(x, y + i, 270, y + i);
        }
        int k = 0;
        setLayout(new GridLayout(9, 9, 2, 2));
        setValues();
        bombPlace();
        for (int i = 0, o = 0; i < 270 && o < 9; i += 30, o++) {
            for (int j = 0, u = 0; j < 270 && u < 9; j += 30, u++) {
                setState(gridArray[o][u]);
                g2.drawImage(setImage(), 3 + j, 3 + i, 28, 28, null);
                buttons.add(new Button(i, j));
//                add(buttons.get(k).getButton());
                buttonPressed.set(k, buttons.get(k).getButtonVisible());
                if (!buttonPressed.get(k)) {
                    if (gridArray[o][u] == 0) {
                        if (bomb.get(k - 2))
                            buttons.get(k - 1).setButtonVisible(false);
                    }

                }
                k++;
            }
        }
        if (checkLose()) {
            JOptionPane.showMessageDialog(null, "YOU LOSE\nGAME OVER", "Minesweeper", JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println(buttonPressed);
        System.out.println(bomb);
        System.out.println(checkLose());
    }

    public Image setImage() {
        String name;
        switch (state) {
            case 0:
                name = "0";
                break;
            case 1:
                name = "1";
                break;
            case 2:
                name = "2";
                break;
            case 3:
                name = "3";
                break;
            case 4:
                name = "4";
                break;
            case 5:
                name = "5";
                break;
            case 6:
                name = "6";
                break;
            case 7:
                name = "7";
                break;
            case 8:
                name = "8";
                break;
            case 9:
                name = "11";
                break;
            case 10:
                name = "12";
                break;
            default:
                name = "0";
                break;
        }
        try {
            image = ImageIO.read(new File("photos/" + name + ".png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
        return image;
    }
    public boolean checkLose() {
        return buttonPressed.indexOf(false) == bomb.indexOf(true);
    }
}
