package pucrs.cg1.tiro.object;

import java.util.List;

/**
 * Created by Lucas on 17/09/2015.
 */
public abstract class GameObject implements GameObjectInterface {

    private List<XY> xy;

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
    }

    public GameObject(GameObjectType form, List<XY> xy, int destiny) {
        setForm(form);
        this.xy = xy;
        this.destiny = destiny;
    }


    public void move() {
        ty -= (0 <= destiny && destiny <= 10) ? 0.02f : 0.04f;
    }

    //TODO REVER verifica se objeto esta dentro da janela
    /*public boolean isInside() {
        if ((x + tx > 2.7f) && (10 <= destiny && destiny <= 14)) {
            return false;
        } else if (x + tx < -2.7f && (0 <= destiny && destiny <= 4)) {
            return false;
        } else if ((y + ty > 2.7f) && (15 <= destiny && destiny <= 19)) {
            return false;
        } else if ((y + ty < -2.7f) && (5 <= destiny && destiny <= 9)) {
            return false;
        } else
            return true;
    }*/

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
