import java.io.*;

import javax.swing.*;
import javax.imageio.*;

import java.awt.Image;
import java.awt.image.*;

public class MarioSprite extends Sprite {
    int animationFrame = 0;
    Thread animationThread;
    ImageIcon character;
    JLabel imageLabel;
    File[] idleAnimationArray;
    boolean forward;
    int scale = 1;
    File[] movingAnimationArray; // list of moving character animations.

    public MarioSprite(JPanel panel) {
        posX = 100;
        posY = 100;
        forward = true;
        idleAnimLocation = "/Users/yassine/Documents/GitHub/MarioGame/images/Idle/";
        File f = new File(idleAnimLocation);
        idleAnimationArray = f.listFiles();
        idleAnimationArray = sortFileArray(idleAnimationArray);
        BufferedImage image = Character(idleAnimationArray);
        character = new ImageIcon(image);
        imageLabel = new JLabel(character);
        panel.add(imageLabel);

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
        
        Image scaledImage = image.getScaledInstance(100 * scale, -1, Image.SCALE_AREA_AVERAGING);
        image.createGraphics().create().drawImage(scaledImage, posX, posY, null);
        return image;

    }

    // sort order of animation image arrays
    private File[] sortFileArray(File[] file) {
        File[] sorted = file;
        int bubble = 1;
        for (int i = bubble; i < sorted.length; i = bubble) {
            boolean small = false;
            while (!small) {
                if (isBubbleLessThanSize(sorted, bubble) && isBiggerThanNext(sorted, bubble)) {
                    File temp = sorted[bubble];
                    sorted[bubble] = sorted[bubble + 1];
                    sorted[bubble + 1] = temp;
                }
                if (isSmallerThanBefore(sorted, bubble)) {
                    File temp = sorted[bubble];
                    sorted[bubble] = sorted[bubble - 1];
                    sorted[bubble - 1] = temp;
                    if (bubble > 1) {
                        bubble--;
                    }
                } else {
                    small = true;
                }
            }
            bubble = i + 1;
        }
        sorted = getRidOfFirstElement(sorted);
        return sorted;

    }

    //check to see if element is bigger than the next element
    private boolean isBiggerThanNext(File[] sorted, int bubble) {
        return sorted[bubble].getAbsolutePath().compareTo(sorted[bubble + 1].getAbsolutePath()) > 0;
    }

    //checks to make sure bubble value is less than the size of the 
    //array to avoid indexOutOfBounds Exception
    private boolean isBubbleLessThanSize(File[] sorted, int bubble) {
        return bubble + 1 < sorted.length;
    }

    //checks to see if the element is smaller than the previous element.
    private boolean isSmallerThanBefore(File[] sorted, int bubble) {
        return sorted[bubble].getAbsolutePath().compareTo(sorted[bubble - 1].getAbsolutePath()) < 0;
    }
    
    //gets rid of .DS_Store file
    private File[] getRidOfFirstElement(File[] f) {
        File[] augmented = new File[f.length - 1];
        for (int i = 1; i < f.length; i++) {
            augmented[i - 1] = f[i];
        }
        return augmented;
    }
}