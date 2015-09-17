package pucrs.cg1.tiro.object;

import java.util.List;

/**
 * Created by Lucas on 17/09/2015.
 */
public class EnemyA extends GameObject {
    public EnemyA(GameObjectType form, List<XY> xy, int destiny, float tx, float ty, float red, float green, float blue) {
        super(form, xy, destiny, tx, ty, red, green, blue);
    }

    public EnemyA(GameObjectType form, List<XY> xy, int destiny) {
        super(form, xy, destiny);
    }


}
