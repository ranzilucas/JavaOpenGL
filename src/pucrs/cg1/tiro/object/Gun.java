package pucrs.cg1.tiro.object;

import java.util.List;

/**
 * Created by Lucas on 17/09/2015.
 */
public class Gun extends GameObject {


    public Gun(GameObjectType form, List<XY> xy, int destiny, float tx, float ty, float red, float green, float blue) {
        super(form, xy, destiny, tx, ty, red, green, blue);
    }

    public Gun(GameObjectType form, List<XY> xy, int destiny) {
        super(form, xy, destiny);
    }
}