package pucrs.cg1.tiro;

import java.util.Random;

public class GameObject {

    float x = 0.0f;   //posicao x do objeto
    float y = 0.0f;   //posicao y do objeto
    float tx = 0.0f;  //transalaco em x do objeto
    float ty = 0.0f;  //transalaco em y do objeto
    float ray = 0.0f; //raio do obetodo(usado para colisao e tamanho do objeto)
    float red = 0.0f;  //cores
    float green = 0.0f;
    float blue = 0.0f;
    int destiny;       //destino(0-4= -x, 5-9= -y, 10-14=+x, 15-19=+y)
    private GameObjectType form;

    public GameObject(GameObjectType form, float x, float y, float ray, int destiny) {
        this.form = form;
        this.x = x;
        this.y = y;
        this.destiny = destiny;
        this.ray = ray;
    }


    public GameObject() {
        this.ray = 0.3f;

        Random random = new Random();

        //gera cores aleatorias
        red = random.nextFloat();
        green = random.nextFloat();
        blue = random.nextFloat();

        int f = random.nextInt(4); // 4 tipos de inimigos
        if (f == 0)
            form = GameObjectType.ENEMYA;
        else if (f == 1)
            form = GameObjectType.ENEMYB;
        else if (f == 2)
            form = GameObjectType.ENEMYC;
        else
            form = GameObjectType.ENEMYD;

        int source = random.nextInt(20); //Origem do objeto(0~19)

        int dest = random.nextInt(5);

        float pos = (float) (random.nextDouble() * 5 - 2.5); // -2.5~2.5

        if (0 <= source && source <= 4) {
            y = pos;
            x = -2.8f;
            destiny = dest + 10;
        } else if (5 <= source && source <= 9) {
            x = pos;
            y = -2.8f;
            destiny = dest + 15;
        } else if (10 <= source && source <= 14) {
            y = pos;
            x = 2.8f;
            destiny = 4 - dest;
        } else if (15 <= source && source <= 19) {
            x = pos;
            y = 2.8f;
            destiny = 9 - dest;
        }
    }


    public void move(float targetX, float targetY) {
        if (0 <= destiny && destiny <= 4)
            tx -= 0.02f;
        else if (5 <= destiny && destiny <= 9)
            ty -= 0.02f;
        else if (10 <= destiny && destiny <= 14)
            tx += 0.02f;
        else
            ty += 0.02f;
    }

    //verifica se objeto esta dentro da janela
    public boolean isInside() {
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
    }

    public float getRay() {
        return ray;
    }

    public float getTx() {
        return tx;
    }

    public void addTx(float tx) {
        this.tx += tx;
    }

    public float getTy() {
        return ty;
    }

    public void addTy(float ty) {
        this.ty += ty;
    }

    public GameObjectType getForm() {
        return form;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

}