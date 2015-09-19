package pucrs.cg1.tiro.object;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lucas on 17/09/2015.
 */
public class GameObject implements GameObjectInterface {

    private List<XY> xy;

    private float maxX, maxY, minX, minY;

    private float tx = 0.0f;  //transalaco em x do objeto
    private float ty = 0.0f;  //transalaco em y do objeto

    //Cores
    private float red = 0.0f;
    private float green = 0.0f;
    private float blue = 0.0f;

    //conta quantas voltas o objeto deu para aumentar a velocidade
    private int countLoop = 1;

    private GameObjectType form;

    public GameObject(GameObjectType type, float tx, float ty, float red, float green, float blue) {
        this.tx = tx;
        this.ty = ty;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.form = type;
        this.xy = getPositionsXY(type);
        getMaxMin();
    }

    public static List<XY> getPositionsXY(GameObjectType type) {
        String path = null;
        switch (type) {
            case ENEMYA:
                path = "C:/opt/inimigoA.mod";
                break;
            case ENEMYB:
                path = "C:/opt/inimigoA.mod";
                break;
            case ENEMYC:
                path = "C:/opt/inimigoA.mod";
                break;
            case ENEMYD:
                path = "C:/opt/inimigoA.mod";
                break;
            case GUN:
                path = "C:/opt/canhao.mod";
                break;
            case VITAMIN:
                path = "C:/opt/inimigoA.mod";
                break;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            List<String> strings = new ArrayList<>();
            while (reader.ready())
                strings.add(reader.readLine());

            int i = Integer.valueOf(strings.get(1).split(" ")[1]);
            List<XY> xyList = new ArrayList<>();
            for (int j = 2; j < i + 2; j++) {
                String[] str = strings.get(j).split(" ");
                float x = Float.valueOf(str[0]) / 100;
                float y = Float.valueOf(str[1]) / 100;
                xyList.add(new XY(x, y));
            }

            reader.close();

            return xyList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        ty -= 0.002f + (countLoop * 0.01);
    }

    // verifica se objeto esta dentro da janela
    public boolean isInside() {
        if (ty - maxY < -2.5f)
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

    @Override
    public float getGreen() {
        return green;
    }

    @Override
    public float getBlue() {
        return blue;
    }

    @Override
    public GameObjectType getForm() {
        return form;
    }

    public void addCountLoop() {
        this.countLoop++;
    }
}
