package pucrs.cg1.tiro;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

import static com.jogamp.opengl.GL.GL_LINE_LOOP;

/**
 * Created by Lucas on 17/09/2015.
 */
class DrawScenario {
    static TextRenderer renderer;

    // desenha eixos X e Y
    public static void drawLines(GL2 gl) {
        gl.glBegin(GL_LINE_LOOP);
        gl.glVertex2d(-3, 2.0f);
        gl.glVertex2d(-3f, -2.0f);
        gl.glVertex2d(3f, -2.0f);
        gl.glVertex2d(3f, 2.0f);
        gl.glEnd();
    }

    public static void drawScore(int score) {
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 25));
        renderer.beginRendering(850, 850);
        renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        renderer.draw("Score: " + score, 700, 800);
        renderer.endRendering();
    }

    public static void drawGameOver() {
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 40));
        renderer.beginRendering(850, 850);
        renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        renderer.draw("Game Over", 320, 425);
        renderer.endRendering();
    }

}
