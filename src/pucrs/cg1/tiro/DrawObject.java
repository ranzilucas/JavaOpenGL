package pucrs.cg1.tiro;

import com.jogamp.opengl.GL2;
import pucrs.cg1.tiro.object.GameObject;
import pucrs.cg1.tiro.object.XY;

import java.util.List;

import static com.jogamp.opengl.GL.GL_LINE_LOOP;

/**
 * Created by Lucas on 17/09/2015.
 */
class DrawObject {

    public static void drawForms(GL2 gl, List<GameObject> gameObjects) {
        for (GameObject f : gameObjects) {
            //detecta colisao
            /*if (colisionDetect(f.getTx() + f.getX(), f.getTy() + f.getY(),
                    f.getRay())) {
                timerMoveObject.stop();
                timerAddObject.stop();
                renderer = new TextRenderer(
                        new Font("SansSerif", Font.BOLD, 40));
                renderer.beginRendering(850, 850);
                renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
                renderer.draw("Game Over", 320, 425);
                renderer.endRendering();
                move = false;
            }*/
            //if (!f.isInside()) {
            //    gameObjects.remove(f);
            //score++;
            //if ((score % dificulty) == 0 && score != 0) {
            //    dificulty += 10;
            //    TimerActionAddGameObject(-500);
            //}
            //} else { //desenha objeto da posicao atual da lista
            draw(gl, f);
        }
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

    // Detecta colisão do circulo jogador com objeto
    public boolean colisionDetect(float px, float py, float r) {
        //	float x = (float) Math.pow(gun.getTx() - px, 2);
        //	float y = (float) Math.pow(gun.getTy() - py, 2);
        //	float d = (float) Math.sqrt(x + y);
        //	if ((gun.getRay() + r) >= d)
        //		return true;
        return false;
    }
}
