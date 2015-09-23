package pucrs.cg1.tiro;

import com.jogamp.opengl.GL2;
import pucrs.cg1.tiro.object.Cordenada;
import pucrs.cg1.tiro.object.GameObject;

import java.util.ArrayList;
import java.util.List;

import static com.jogamp.opengl.GL.GL_LINES;
import static com.jogamp.opengl.GL.GL_LINE_LOOP;

/**
 * Created by Lucas on 17/09/2015.
 */
class DrawObject {

    GL2 gl;

    public DrawObject(GL2 gl) {
        this.gl = gl;
    }

    // Detecta colisão do circulo jogador com objeto
    public boolean isColisionObject(GameObject gun, GameObject f) {
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
     * @param object
     */
    public void draw(GameObject object) {
        gl.glLoadIdentity();
        gl.glColor3f(object.getRed(), object.getGreen(), object.getBlue());
        gl.glTranslatef(object.getTx(), object.getTy(), -6.0f);
        gl.glBegin(GL_LINE_LOOP);
        for (Cordenada c : object.getCordenada())
            gl.glVertex2d(c.getX(), c.getY());
        gl.glEnd();
    }

    /**
     * Desenha os tiros
     * @param bullet
     */
    private void drawBullet(GameObject bullet){
        gl.glLoadIdentity();
        gl.glColor3f(bullet.getRed(), bullet.getGreen(), bullet.getBlue());
        gl.glTranslatef(bullet.getTx(), bullet.getTy(), -6.0f);
        gl.glBegin(GL_LINES);
        for (Cordenada c : bullet.getCordenada())
            gl.glVertex2d(c.getX(), c.getY());
        gl.glEnd();
    }

    /**
     * Calcula se pode ou nao desenhar e desenha os tiros
     * @param bullets
     * @return
     */
    public ArrayList<GameObject> drawBullets(ArrayList<GameObject> bullets) {
        List<GameObject> list = new ArrayList<>();
        for(GameObject object : bullets){
            if(object.isInside()){
                drawBullet(object);
                list.add(object);
            }
        }
        return (ArrayList<GameObject>) list;
    }
}
