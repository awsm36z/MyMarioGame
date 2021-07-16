import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame game = new JFrame("Yassine's Mario Game!");
        JPanel panel = new JPanel();
        MarioSprite mario =  new MarioSprite(panel);
        game.add(panel);
        game.setVisible(true);

    }
    
}