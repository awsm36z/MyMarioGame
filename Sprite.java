import java.awt.*;
import java.io.File;
import java.awt.image.*;
 public class Sprite {
    int posX;
    int posY;
    String movingAnimLocation;
    String idleAnimLocation;
    boolean orientation; //true is looking right, false is looking left
    File[] movingAnimationArray; //list of moving character animations.
    File[] idleAnimationArray;//list of idle character animations.
}
