#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/freeglut.h>

// Dibujamos los ejes del mundo
void Ejes (int width) {
    glLineWidth(width);
    glBegin(GL_LINES);
       glColor3f(1.0,0.0,0.0);
       glVertex3f(-1.0,0.0,0.0);
       glVertex3f(1.0,0.0,0.0);
       glColor3f(0.0,1.0,0.0);
       glVertex3f(0.0,-1.0,0.0);
       glVertex3f(0.0,1.0,0.0);
    glEnd();
    // se pone otra vez a 1 para que se ponga por defecto
    glLineWidth(1);
}

// Dibujamos la cara del muñeco
void Monigote () {
    // Cuadrado principal
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    glColor3f(1.0,0.8,0.6);
    glBegin(GL_POLYGON);
        glVertex3f(-0.2,0.0,0.0);
        glVertex3f(0.2,0.0,0.0);
        glVertex3f(0.2,0.55,0.0);
        glVertex3f(-0.2,0.55,0.0);
    glEnd();
    // Linea de contorno
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    glColor3f(0.0,0.0,0.0);
    glBegin(GL_POLYGON);
        glVertex3f(-0.2,0.0,0.0);
        glVertex3f(0.2,0.0,0.0);
        glVertex3f(0.2,0.55,0.0);
        glVertex3f(-0.2,0.55,0.0);
    glEnd();
}

// inicializamos los valores de por defecto
static void Init( ) {
   glShadeModel( GL_FLAT);
   // Modo para usar colores con suavisado
   //glShadeModel( GL_SMOOTH);
}

// Funcion para que se reescriba la pantalla
static void Reshape( int width, int height ) {
    glViewport(0, 0, (GLint)width, (GLint)height);
    glOrtho (-1, 1, -1, 1, -10, 10.0);
}

static void Display( ) {
   glClearColor(0.0,0.3,0.5,0.0);
   glClear( GL_COLOR_BUFFER_BIT );

   Ejes(6);
   Monigote();

   glFlush();
}


static void Keyboard(unsigned char key, int x, int y )
{

  if (key==27)
      exit(0);

}


int main( int argc, char **argv )
{
   glutInit(&argc,argv);
   glutInitDisplayMode( GLUT_RGB );

   glutInitWindowPosition( 20, 100 );
   glutInitWindowSize(500, 500 );
   glutCreateWindow("Practica 1 IG");


   Init();

   glutReshapeFunc(Reshape);
   glutDisplayFunc(Display);
   glutKeyboardFunc(Keyboard);

   glutMainLoop( );

   return 0;
}
