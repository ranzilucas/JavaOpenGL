package pucrs.cg1.tiro;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

//import pucrs.cg1.dodge.GameObject;
//import pucrs.cg1.dodge.GameObjectType;

public class TiroGame extends GLCanvas implements GLEventListener {

    private static final int CANVAS_WIDTH = 800; // largura janela
    private static final int CANVAS_HEIGHT = 600; // altura janela
    private static final int FPS = 60;

    private GLU glu;

    //static float ballSpeed = 0.07f;
    //static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    static private GameObject ballGame;

    static int score = 0;

    static int dificulty = 10;

    static int timeAdd = 500;

    static Timer timerMoveObject;
    static Timer timerAddObject;
    static boolean move = true;


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
                                    //if (ballGame.getTx() - ballGame.getRay() > -2.4f)
                                    //		ballGame.addTx(-ballSpeed);
                                    break;
                                case 38:// Up
                                    //	if (ballGame.getTy() + ballGame.getRay() < 2.4f)
                                    //		ballGame.addTy(ballSpeed);
                                    break;
                                case 39:// Right
                                    //	if (ballGame.getTx() + ballGame.getRay() < 2.4f)
                                    //		ballGame.addTx(ballSpeed);
                                    break;
                                case 40:// Down

                                    //	if (ballGame.getTy() - ballGame.getRay() > -2.4f)
                                    //		ballGame.addTy(-ballSpeed);
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

    static ActionListener actionMove = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //for (int i = 0; i < gameObjects.size(); i++)
            //	gameObjects.get(i).move(ballGame.getTx() + ballGame.getX(),
            //			ballGame.getTy() + ballGame.getY());
        }
    };

    // gera objetos em intervalo de tempo especificado
    public static void TimerActionAddGameObject(int sec) {
        if (timeAdd - sec > 0)
            timeAdd -= sec;
        timerAddObject = new Timer(timeAdd, actionAddForm);
        timerAddObject.start();
    }

    static ActionListener actionAddForm = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //if (gameObjects.size() < 25) {
            //	GameObject f = new GameObject();
            //		gameObjects.add(f);
            //		}
        }
    };

    public TiroGame() {
        this.addGLEventListener(this);
        // Cria instancia bola do jogador
        //	ballGame = new GameObject(GameObjectType.CIRCLE, 0.0f, 0.0f, 0.15f, -1);
        TimerActionMove(17);
        TimerActionAddGameObject(0);
        //	gameObjects.add(new GameObject());
    }

    // metodo responsavel por desenhar circulos
    private void drawBall(float cx, float cy, float r, GL2 gl) {
        gl.glBegin(GL_LINE_LOOP);
        for (int i = 0; i < 360; i++) {
            float theta = 2.0f * 3.1415926f * i / 360;
            float x = (float) (r * Math.cos(theta));
            float y = (float) (r * Math.sin(theta));
            gl.glVertex3f(x + cx, y + cy, 0);
        }
        gl.glEnd();
    }


    // desenha todas as formas da lista de objetos
    private void drawForms(GL2 gl) {
        /*for (int i = 0; i < gameObjects.size(); i++) {
            GameObject f = gameObjects.get(i);

			//detecta colisao
			if (colisionDetect(f.getTx() + f.getX(), f.getTy() + f.getY(),
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
			}
			
			//verifica se objeto esta dentro da janela
			if (!f.isInside()) {
				gameObjects.remove(i);
				score++;
				if ((score % dificulty) == 0 && score != 0) {
					dificulty += 10;
					TimerActionAddGameObject(-500);
				}
			} else { //desenha objeto da posicao atual da lista
				gl.glLoadIdentity();
				gl.glColor3f(f.getRed(), f.getGreen(), f.getBlue());
				gl.glTranslatef(f.getTx(), f.getTy(), -6.0f);
				switch (f.getForm()) {
				case SQUARE:
					gl.glBegin(GL_QUADS);
					gl.glVertex3f(f.getX() - f.getRay(), f.getY() + f.getRay(),
							0.0f);
					gl.glVertex3f(f.getX() + f.getRay(), f.getY() + f.getRay(),
							0.0f);
					gl.glVertex3f(f.getX() + f.getRay(), f.getY() - f.getRay(),
							0.0f);
					gl.glVertex3f(f.getX() - f.getRay(), f.getY() - f.getRay(),
							0.0f);
					gl.glEnd();
					break;
				case CIRCLE:
					drawBall(f.getX(), f.getY(), f.getRay(), gl);
					break;
				case TRIANGLE:
					gl.glBegin(GL_TRIANGLES);
					gl.glVertex3f(f.getX(), f.getY() + f.getRay(), 0.0f);
					gl.glVertex3f(f.getX() - f.getRay(), f.getY() - f.getRay(),
							0.0f);
					gl.glVertex3f(f.getX() + f.getRay(), f.getY() - f.getRay(),
							0.0f);
					gl.glEnd();
					break;
				case LINE:
					gl.glBegin(GL_LINES);
					gl.glVertex2d(f.getX() - f.getRay(), f.getY());
					gl.glVertex2d(f.getX() + f.getRay(), f.getY());
					gl.glVertex2d(f.getX(), f.getY() + f.getRay());
					gl.glVertex2d(f.getX(), f.getY() - f.getRay());
					gl.glVertex2d(f.getX() - f.getRay()+0.1f, f.getY() + f.getRay()-0.1f);
					gl.glVertex2d(f.getX() + f.getRay()-0.1f, f.getY() - f.getRay()+0.1f);					
					gl.glVertex2d(f.getX() - f.getRay()+0.1f, f.getY() - f.getRay()+0.1f);
					gl.glVertex2d(f.getX() + f.getRay()-0.1f, f.getY() + f.getRay()-0.1f);
					gl.glEnd();
					break;
				default:
					break;
				}
			}
		}*/
    }

    // Detecta colisão do circulo jogador com objeto
    public boolean colisionDetect(float px, float py, float r) {
        //	float x = (float) Math.pow(ballGame.getTx() - px, 2);
        //	float y = (float) Math.pow(ballGame.getTy() - py, 2);
        //	float d = (float) Math.sqrt(x + y);
        //	if ((ballGame.getRay() + r) >= d)
        //		return true;
        return false;
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

        // BALL GAME
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glLineWidth(5.0f);
        //	gl.glTranslatef(ballGame.getTx(), ballGame.getTy(), 0.0f);
        //	drawBall(ballGame.getX(), ballGame.getY(), ballGame.getRay(), gl);
        gl.glLineWidth(1.0f);
        gl.glColor3f(1.0f, 1.0f, 1.0f);

        // Desenha objetos obstaculos
        drawForms(gl);

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
