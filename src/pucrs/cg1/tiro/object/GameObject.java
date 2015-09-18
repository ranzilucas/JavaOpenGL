package pucrs.cg1.tiro.object;

import java.util.List;
import java.util.Random;

/**
 * Created by Lucas on 17/09/2015.
 */
public abstract class GameObject implements GameObjectInterface {

    private List<XY> xy;

    private float maxX, maxY, minX, minY;

    private float tx = 0.0f;  //transalaco em x do objeto
    private float ty = 0.0f;  //transalaco em y do objeto

    private float red = 0.0f;  //cores
    private float green = 0.0f;
    private float blue = 0.0f;

    private int destiny;       //destino(0-4= -x, 5-9= -y, 10-14=+x, 15-19=+y)

    private GameObjectType form;

    public GameObject(GameObjectType form, List<XY> xy, int destiny, float tx, float ty, float red, float green, float blue) {
        this.xy = xy;
        this.tx = tx;
        this.ty = ty;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.destiny = destiny;
        this.form = form;
        getMaxMin();
    }

    public GameObject(GameObjectType form, List<XY> xy, int destiny) {
        setForm(form);
        this.xy = xy;
        this.destiny = destiny;
        getMaxMin();
    }

    private void getMaxMin() {
        maxY = 0f;
        maxX = 0f;
        minY = 0f;
        minX = 0f;
        for (XY v : getXy()) {
            if (v.getX() < maxX)
                maxX = v.getX();
            if (v.getY() > maxY)
                maxY = v.getY();
            if (v.getX() > minX)
                minX = v.getX();
            if (v.getY() < minY)
                minY = v.getY();
        }
    }

    public float getTXRandom() {
        Random random = new Random();
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
        return x;
    }

    public void move() {
        ty -= (0 <= destiny && destiny <= 10) ? 0.02f : 0.04f;
    }

    // verifica se objeto esta dentro da janela
    public boolean isInside() {
        if (ty - maxY < -3.0f)
            return false;
        return true;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public float getMinX() {
        return minX;
    }

    public float getMinY() {
        return minY;
    }

    public List<XY> getXy() {
        return xy;
    }

    public void setXy(List<XY> xy) {
        this.xy = xy;
    }

    @Override
    public float getTx() {
        return tx;
    }

    public void setTx(float tx) {
        this.tx = tx;
    }

    @Override
    public float getTy() {
        return ty;
    }

    public void setTy(float ty) {
        this.ty = ty;
    }

    @Override
    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    @Override
    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    @Override
    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    @Override
    public int getDestiny() {
        return destiny;
    }

    public void setDestiny(int destiny) {
        this.destiny = destiny;
    }

    @Override
    public GameObjectType getForm() {
        return form;
    }

    public void setForm(GameObjectType form) {
        this.form = form;
    }
}
