package pucrs.cg1.tiro.object;

import java.util.List;

/**
 * Created by Lucas on 17/09/2015.
 */
public class Enemy extends GameObject {
    public Enemy(GameObjectType form, int destiny, float tx, float ty, float red, float green, float blue) {
        super(form, destiny, tx, ty, red, green, blue);
    }

    public Enemy(GameObjectType form, List<XY> xy, int destiny) {
        super(form, xy, destiny);
    }

}
