import java.awt.*;
import java.lang.*;
 public class Sprite {
    int posX;
    int posY;
    String movingAnimLocation;
    String idleAnimLocation;
    boolean orientation; //true is looking right, false is looking left
    Image characterAnim; //current character animation
    Image[] movingAnimationArray; //list of moving character animations.
    Image[] idleAnimationArray;//list of idle character animations.
}
