package pucrs.cg1.tiro;

import com.jogamp.opengl.GL2;
import pucrs.cg1.tiro.object.GameObject;
import pucrs.cg1.tiro.object.XY;

import javax.swing.*;
import java.util.List;

import static com.jogamp.opengl.GL.GL_LINE_LOOP;

/**
 * Created by Lucas on 17/09/2015.
 */
class DrawObject {

    public static boolean drawForms(GL2 gl, List<GameObject> gameObjects, Timer timerAddObject, Timer timerMoveObject, GameObject gun) {
        boolean move = true;
        for (GameObject f : gameObjects) {
            //detecta colisao
            if (isColisionObject(gun, f)) {
                gun.getXy();
                timerMoveObject.stop();
                timerAddObject.stop();
                DrawScenario.drawGameOver();
                move = false;
            }

            if (!f.isInside()) {
                f.setTx(f.getTXRandom());
                f.setTy(3f);
            }
                /*life++;
                if ((life % dificulty) == 0 && life != 0) {
                    dificulty += 10;
                    TimerActionAddGameObject(-500);
                }*/
            //} else { //desenha objeto da posicao atual da lista
            draw(gl, f);
            //}
        }
        return move;
    }

    // Detecta colisão do circulo jogador com objeto
    private static boolean isColisionObject(GameObject gun, GameObject f) {
        if (f.getTy() < 0)
            if (f.getTx() > 0 && gun.getTx() > 0) {
                if (f.getMaxX() + f.getTx() < gun.getMinX() + gun.getTx() && gun.getMaxX() + gun.getTx() < f.getMinX() + f.getTx())
                    if (-f.getMaxY() + f.getTy() < -gun.getMinY() + gun.getTy() && -gun.getMaxY() + gun.getTy() < -f.getMinY() + f.getTy())
                        return true;

            } else if (f.getTx() < 0 && gun.getTx() < 0) {
                if (-f.getMaxX() + f.getTx() > -gun.getMinX() + gun.getTx() && -gun.getMaxX() + gun.getTx() > -f.getMinX() + f.getTx())
                    if (-f.getMaxY() + f.getTy() < -gun.getMinY() + gun.getTy() && -gun.getMaxY() + gun.getTy() < -f.getMinY() + f.getTy())
                        return true;
            }

        return false;
    }

    /**
     * Desenha o objeto passado
     *
     * @param gl
     * @param gun
     */
    public static void draw(GL2 gl, GameObject gun) {
        gl.glLoadIdentity();
        gl.glColor3f(gun.getRed(), gun.getGreen(), gun.getBlue());
        gl.glTranslatef(gun.getTx(), gun.getTy(), -6.0f);
        gl.glBegin(GL_LINE_LOOP);
        for (XY xy : gun.getXy())
            gl.glVertex2d(xy.getX(), xy.getY());
        gl.glEnd();
    }


}
