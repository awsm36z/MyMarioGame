import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class MarioSprite extends Sprite {
    int animationFrame = 0;
    Thread animationThread;
    ImageIcon character;
    JLabel imageLabel;
    File[] idleAnimationArray;
    boolean forward;
    File[] movingAnimationArray; // list of moving character animations.

    public MarioSprite(JPanel panel) {
        posX = 0;
        posY = 0;
        forward = true;
        idleAnimLocation = "/Users/yassine/Documents/GitHub/MarioGame/images/Idle/";
        File f = new File(idleAnimLocation);
        idleAnimationArray = f.listFiles();
        idleAnimationArray = sortFileArray(idleAnimationArray);
        BufferedImage image = Character(idleAnimationArray);
        character = new ImageIcon(image);
        imageLabel = new JLabel(character);
        panel.add(imageLabel);

        // new Thread (new Runnable(){
        // public void run(){
        // while (true){
        // if (animationFrame < 28){
        // animationFrame++;
        // }
        // else{animationFrame = 0;}
        // character.setImage(Character(temp));
        // }}

        // }).start();
    }

    private void progressAnimationFrame() {
        if (forward) {
            if (animationFrame < 27) {
                animationFrame++;
            } else {
                animationFrame--;
                this.forward = false;
            }
        } else {
            if (animationFrame > 0) {
                animationFrame--;
            } else {
                animationFrame++;
                forward = true;
            }
        }
    }

    public void animateCharacter(JPanel panel) {
        progressAnimationFrame();
        panel.remove(imageLabel);
        BufferedImage image = Character(idleAnimationArray);
        character.setImage(image);
        imageLabel.setIcon(character);
        panel.add(imageLabel);
    }

    public BufferedImage Character(File[] temp) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(temp[animationFrame]);
        } catch (IOException e) {
        }
        return image;
    }

    // sort order of animation image arrays
    private File[] sortFileArray(File[] file) {
        File[] buffer = new File[file.length];
        File smallestFile;
        String small;
        String check;
        int smallIndex = 0;
        int bufferIndex = 0;
        for (int i = 0; i < 4; i++) {
            smallestFile = new File("C:/");
            for (int x = 0; x < file.length; x++) {
                smallestFile = file[x];
                if (file[x] == null) {
                    continue;
                }
                check = file[x].getAbsolutePath();

                if (check.contains(".DS")) {
                    continue;
                }

                for (int z = 0; z < file.length; z++) {
                    if (file[z] == null) {
                        continue;
                    }
                    small = file[z].getAbsolutePath();

                    if (small.contains(".DS")) {
                        continue;
                    }

                    if (check.compareTo(small) > 0) {
                        smallestFile = file[z];
                        check = small;
                        smallIndex = z;
                    }

                }
                buffer[bufferIndex] = smallestFile;
                file[smallIndex] = null;
                bufferIndex++;
            }
        }
        return buffer;
    }

}