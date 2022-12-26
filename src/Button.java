import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Button extends JButton implements MouseListener, ActionListener {
    private JButton button;
    private Image image;
    private boolean rightPressed;
    private boolean buttonVisible = true;
    private int state;
    final int size = 28;


    public Button(int i, int j) {
        super();
        button = new JButton();
        setStateButton(0);
        button.setPreferredSize(new Dimension(size, size));
        button.setMaximumSize(button.getPreferredSize());
        button.setBounds(3 + j, 3 + i, 28, 28);
        button.addMouseListener(this);
        setImageButton();

    }

    public void setStateButton(int state1) {
        state = state1;
    }
    public void setButtonVisible(boolean visible)
    {
        buttonVisible = visible;
    }
    public boolean getButtonVisible()
    {
        return buttonVisible;
    }
    public JButton getButton() {
        return button;
    }

    public void setImageButton() {
        String name;
        switch (state) {
            case 0:
                name = "10";
                break;
            case 1:
                name = "9";
                break;
            case 2:
                name = "11";
                break;
            default:
                name = "10";
                break;
        }
        try {
            image = ImageIO.read(new File("photos/" + name + ".png"));
            button.setIcon(new ImageIcon(image));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button.getModel().setArmed(false);
        button.getModel().setPressed(false);
        if (e.getButton() == 3) {
            if (rightPressed) {
                rightPressed = false;
                setStateButton(0);
                setImageButton();
            } else {
                rightPressed = true;
                setStateButton(1);
                setImageButton();
            }
        } else if (e.getButton() == 1 && !rightPressed) {
            button.setVisible(false);
            setButtonVisible(false);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
