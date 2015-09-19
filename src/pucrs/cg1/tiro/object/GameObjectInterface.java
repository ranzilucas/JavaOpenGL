package pucrs.cg1.tiro.object;

import java.util.List;

/**
 * Created by Lucas on 17/09/2015.
 */
public interface GameObjectInterface {

    List<XY> getXy();

    float getTx();

    float getTy();

    float getRed();

    float getGreen();

    float getBlue();

    GameObjectType getForm();

}
