package pucrs.cg1.tiro.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lucas on 17/09/2015.
 */
public class RandomObject {

    private static List<XY> position;

    public static GameObject getObjectRandom() {

        Random random = new Random();
        float red = random.nextFloat();
        float green = random.nextFloat();
        float blue = random.nextFloat();

        int source = random.nextInt(20); //Origem do objeto(0~19)
        float pos = (float) (random.nextDouble() * 5 - 2.5); // -2.5~2.5
        float x = 0;

        if (0 <= source && source <= 4) {
            x = -2.8f * 1.58f;
        } else if (5 <= source && source <= 9) {
            x = pos * 0.14f;
        } else if (10 <= source && source <= 14) {
            x = 2.8f * 0.76f;
        } else if (15 <= source && source <= 19) {
            x = pos;
        }
        return new EnemyA(GameObjectType.ENEMYA, getPosition(), source, x, 3f, red, green, blue);
    }

    public static List<XY> getPosition() {
        List<XY> xyList = new ArrayList<XY>();

        xyList.add(new XY(0.2f, 0f));
        xyList.add(new XY(0.2f, 0.2f));
        xyList.add(new XY(-0.2f, 0.2f));
        xyList.add(new XY(-0.2f, 0f));
        return xyList;
    }


}
