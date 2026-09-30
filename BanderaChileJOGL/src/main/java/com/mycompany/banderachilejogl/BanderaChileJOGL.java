/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.banderachilejogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BanderaChileJOGL extends JFrame implements GLEventListener {

    public BanderaChileJOGL() {
        setTitle("Bandera de Chile - JOGL OpenGL");
        setSize(800, 533);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLJPanel panel = new GLJPanel(capabilities);
        panel.addGLEventListener(this);
        add(panel);

        FPSAnimator animator = new FPSAnimator(panel, 60);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // --- 1. FRANJA INFERIOR ROJA (50% Inferior completo) ---
        gl.glColor3f(0.83f, 0.08f, 0.18f); // Rojo oficial
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-1.0f,  0.0f);
            gl.glVertex2f( 1.0f,  0.0f);
            gl.glVertex2f( 1.0f, -1.0f);
            gl.glVertex2f(-1.0f, -1.0f);
        gl.glEnd();

        // --- 2. FRANJA SUPERIOR BLANCA (2/3 Derechos superiores) ---
        gl.glColor3f(1.0f, 1.0f, 1.0f); // Blanco
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-0.333f, 1.0f);
            gl.glVertex2f( 1.0f,   1.0f);
            gl.glVertex2f( 1.0f,   0.0f);
            gl.glVertex2f(-0.333f, 0.0f);
        gl.glEnd();

        // --- 3. CUADRO AZUL / CANTÓN (1/3 Izquierdo superior) ---
        gl.glColor3f(0.0f, 0.22f, 0.65f); // Azul oficial
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-1.0f,   1.0f);
            gl.glVertex2f(-0.333f, 1.0f);
            gl.glVertex2f(-0.333f, 0.0f);
            gl.glVertex2f(-1.0f,   0.0f);
        gl.glEnd();

        // --- 4. ESTRELLA SOLITARIA BLANCA ---
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        
        // Centro del cuadro azul en coordenadas OpenGL
        float centroX = -0.666f;
        float centroY = 0.5f;
        
        // Radios de las puntas exteriores e interiores
        float rExterior = 0.18f;
        float rInterior = 0.07f;

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
            // Punto central de la estrella
            gl.glVertex2f(centroX, centroY);
            
            // Generación de los 10 vértices de la estrella (5 puntas)
            for (int i = 0; i <= 10; i++) {
                double angulo = Math.PI / 2 + i * (Math.PI / 5);
                float r = (i % 2 == 0) ? rExterior : rInterior;
                float x = centroX + (float) (r * Math.cos(angulo));
                float y = centroY + (float) (r * Math.sin(angulo));
                gl.glVertex2f(x, y);
            }
        gl.glEnd();

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BanderaChileJOGL().setVisible(true);
        });
    }
}

