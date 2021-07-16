import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class MarioSprite extends Sprite {
    int animationFrame = 0;
    public MarioSprite(JPanel panel){
        posX = 0;
        posY = 0;
        idleAnimLocation = "/Users/yassine/Documents/GitHub/MarioGame/images/Idle/";
        File f = new File(idleAnimLocation);
        File[] temp  = f.listFiles();
        // idleAnimationArray = temp;
        BufferedImage image = Character(temp);
        ImageIcon character = new ImageIcon(image);
        JLabel imageLabel = new JLabel(character);
        panel.add(imageLabel);
        
    // new Thread (new Runnable(){
    //     public void run(){
    //         while (true){
    //         if (animationFrame < 28){
    //             animationFrame++;
    //         }
    //         else{animationFrame = 0;}
    //             character.setImage(Character(temp));
    //     }}

    // }).start();
}
    public BufferedImage Character(File[] temp){
        BufferedImage image = null;
        try {
            image = ImageIO.read(temp[animationFrame]);
        } catch (IOException e) {
        }
        return image;
    }

}
