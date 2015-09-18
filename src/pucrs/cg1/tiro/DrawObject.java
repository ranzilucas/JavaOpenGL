package pucrs.cg1.tiro;

import com.jogamp.opengl.GL2;
import pucrs.cg1.tiro.object.GameObject;
import pucrs.cg1.tiro.object.Gun;
import pucrs.cg1.tiro.object.XY;

import javax.swing.*;
import java.util.List;

import static com.jogamp.opengl.GL.GL_LINE_LOOP;

/**
 * Created by Lucas on 17/09/2015.
 */
class DrawObject {

    public static boolean drawForms(GL2 gl, List<GameObject> gameObjects, Timer timerAddObject, Timer timerMoveObject, Gun gun) {
        boolean move = true;
        for (GameObject f : gameObjects) {
            //detecta colisao
            move = (move) ? (isColisionObject(timerAddObject, timerMoveObject, gun, f)) : false;

            if (!f.isInside()) {
                f.setTx(f.getTXRandom());
                f.setTy(3f);
            }  //gameObjects.remove(f);
                /*score++;
                if ((score % dificulty) == 0 && score != 0) {
                    dificulty += 10;
                    TimerActionAddGameObject(-500);
                }*/
            //} else { //desenha objeto da posicao atual da lista
            draw(gl, f);
            //}
        }
        return move;
    }

    // Detecta colis�o do circulo jogador com objeto
    private static boolean isColisionObject(Timer timerAddObject, Timer timerMoveObject, Gun gun, GameObject f) {
        if (f.getMaxX() < gun.getMinX() && gun.getMaxX() < f.getMinX())
            if (f.getMaxY() > gun.getMinY() && gun.getMaxY() > f.getMinY()) {
                gun.getXy();
                timerMoveObject.stop();
                timerAddObject.stop();
                DrawScenario.drawGameOver();
                return false;
            }
        return true;
    }

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
