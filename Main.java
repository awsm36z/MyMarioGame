import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame game = new JFrame("Yassine's Mario Game!");
        game.setSize(500, 500);
        JPanel panel = new JPanel();
        MarioSprite mario = new MarioSprite(panel);
        game.add(panel);
        game.setVisible(true);
        while (true) {
            mario.animateCharacter(panel);
            game.repaint();
            waitXSeconds(50);

        }
    }

    private static void waitXSeconds(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}