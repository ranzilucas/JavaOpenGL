package pucrs.cg1.tiro;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import pucrs.cg1.tiro.object.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class TiroGame extends GLCanvas implements GLEventListener {

    private static final int CANVAS_WIDTH = 800; // largura janela
    private static final int CANVAS_HEIGHT = 600; // altura janela
    private static final int FPS = 60;

    static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    static float gunSpeed = 0.07f;

    static int score = 0;

    static int dificulty = 10;

    static int timeAdd = 500;
    static Timer timerMoveObject;
    static Timer timerAddObject;

    static boolean move = true;

    static ActionListener actionMove = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < gameObjects.size(); i++)
                gameObjects.get(i).move();
        }
    };

    static ActionListener actionAddForm = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (gameObjects.size() < 15)
                gameObjects.add(RandomObject.getObjectRandom());
        }
    };

    static private Gun gun;
    private GLU glu;

    public TiroGame() {
        this.addGLEventListener(this);
        gun = new Gun(GameObjectType.GUN, getPositions(), -1, 0f, -2.0f, 0.0f, 0.0f, 1.0f);// Criar o canhao
        TimerActionMove(17);
        TimerActionAddGameObject(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                // Armazena elementos opengl
                GLCanvas canvas = new TiroGame();
                canvas.setPreferredSize(new Dimension(CANVAS_WIDTH,
                        CANVAS_HEIGHT));

                final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

                final JFrame frame = new JFrame();
                frame.getContentPane().add(canvas);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        new Thread() {
                            @Override
                            public void run() {
                                if (animator.isStarted())
                                    animator.stop();
                                System.exit(0);
                            }
                        }.start();
                    }
                });

                // captura de teclas
                KeyListener keyListener = new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent key) {

                    }

                    @Override
                    public void keyReleased(KeyEvent key) {

                    }

                    @Override
                    public void keyPressed(KeyEvent key) {
                        if (move) {
                            if (key.getKeyCode() == 27)
                                System.exit(0);
                            switch (key.getKeyCode()) {
                                case 37:// Left
                                    if (gun.getTx() > -2.8f)
                                        gun.setTx(gun.getTx() - gunSpeed);
                                    break;
                                case 39:// Right
                                    if (gun.getTx() < 2.8f)
                                        gun.setTx(gun.getTx() + gunSpeed);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                };
                canvas.addKeyListener(keyListener);
                frame.setTitle("T1 CG-I");
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);
                animator.start(); // animation loop
                canvas.requestFocus();
            }
        });
    }

    // movimenta objetos em tempo determinado(segundos)
    public static void TimerActionMove(int sec) {
        timerMoveObject = new Timer(sec, actionMove);
        timerMoveObject.start();
    }

    // gera objetos em intervalo de tempo especificado
    public static void TimerActionAddGameObject(int sec) {
        if (timeAdd - sec > 0)
            timeAdd -= sec;
        timerAddObject = new Timer(timeAdd, actionAddForm);
        timerAddObject.start();
    }

    public List<XY> getPositions() {
        List<XY> xyList = new ArrayList<XY>();

        xyList.add(new XY(0.2f, 0f));
        xyList.add(new XY(0.2f, 0.2f));
        xyList.add(new XY(-0.2f, 0.2f));
        xyList.add(new XY(-0.2f, 0f));
        return xyList;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color

        // Linha XY
        gl.glLoadIdentity();
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        DrawScenario.drawLines(gl);

        // Desenha canhao;
        DrawObject.draw(gl, gun);

        // Desenha objetos obstaculos
        DrawObject.drawForms(gl, gameObjects, timerAddObject, timerMoveObject, gun);

        // Desenha Score
        DrawScenario.drawScore(score);
    }

    // inicia as variaveis opengl e gluOrtho
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // background color
        gl.glMatrixMode(GL_PROJECTION);
        glu.gluOrtho2D(-1f, 1f, -1f, 1f);
        gl.glMatrixMode(GL_MODELVIEW);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {

        GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context

        if (height == 0)
            height = 1; // prevent divide by zero
        float aspect = (float) width / height;

        // Set the view port (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL_PROJECTION); // choose projection matrix
        gl.glLoadIdentity(); // reset projection matrix
        glu.gluPerspective(45.0, aspect, 0.1, 0.0); // fovy, aspect, zNear,
        // zFar

        // Enable the model-view transform
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity(); // reset
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub
    }

}
