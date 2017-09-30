#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>


int ancho,alto;
unsigned char tecla='1'; /* objeto visualizado por defecto */



void Ejes (int width)
{   
    glLineWidth(width);
    glBegin(GL_LINES);
       glColor3f(1.0,0.0,0.0);
       glVertex3f(0.0,0.0,0.0);
       glVertex3f(100.0,0.0,0.0);
       
       glColor3f(0.0,1.0,0.0);
       glVertex3f(0.0,0.0,0.0);
       glVertex3f(0.0,100.0,0.0);
       
       glColor3f(0.0,0.0,1.0);
       glVertex3f(0.0,0.0,0.0);
       glVertex3f(0.0,0.0,100.0);
    glEnd();
    glLineWidth(1);
}

void Piramide (float size, float r, float g, float b)
{
   glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
   glColor3f(r,g,b);
  //lados     
   glBegin(GL_TRIANGLES);
     glVertex3f(size,-size,size);
     glVertex3f(size,-size,-size);
     glVertex3f(0.0,size,0.0);
     
     glVertex3f(size,-size,-size);
     glVertex3f(-size,-size,-size);
     glVertex3f(0.0,size,0.0);
     
     glVertex3f(-size,-size,-size);
     glVertex3f(-size,-size,size);
     glVertex3f(0.0,size,0.0);
   glEnd();

  // base 
  glBegin(GL_POLYGON);
     glVertex3f(-size,-size,-size);
     glVertex3f(size,-size,-size);
     glVertex3f(size,-size,size);
     glVertex3f(-size,-size,size);  
  glEnd();
}


void Esfera (float size, int la, int lo, float r, float g, float b)
{
   glColor3f(r,g,b);
   glutWireSphere(size,la,lo);
}


void Cilindro (float size, float height, int la, float r, float g, float b)
{
   
   GLUquadricObj *qobj; 
   qobj = gluNewQuadric();
   glColor3f(r,g,b);
   gluQuadricDrawStyle(qobj,GLU_LINE);
   gluCylinder(qobj,size,size,height,la,1); 
}

static void Init( )
{
   glShadeModel( GL_FLAT );
}


static void Reshape( int width, int height )
{
    ancho=width;
    alto=height;
    glViewport(0, 0, (GLint)width, (GLint)height);

 /* Inicia la transformacion de proyeccion */
   glMatrixMode(GL_PROJECTION);
   glLoadIdentity();

   glFrustum (-0.5, 0.5, -0.5, 0.5,0.5, 100.0);
   gluLookAt ( 1.0,0.5,2.5,0.0,0.0,0.0, 0.0,1.0,0.0);

}

static void Display( )
{

   glClearColor(0.0,0.3,0.5,0.0);
   glClear( GL_COLOR_BUFFER_BIT );
   
   
  
   glMatrixMode (GL_MODELVIEW);
   glLoadIdentity();
   
   Ejes(4);
   glColor3f(1.0, 1.0, 1.0);
   switch (tecla)
   {
     case '1':
       Piramide (1.0, 0.0, 0.5, 1.0);
       break;
     case '2':
       Esfera(1.0, 20, 20, 1.0, 1.0, 1.0);
       break;
     case '3':
       Cilindro(1, 1, 18, 1.0, 1.0, 1.0); 
       break;
   }
    
   glFlush();
}


static void Keyboard(unsigned char key, int x, int y )
{
 
  if (key==27)
      exit(0);
  if (key=='1' || key=='2' || key=='3')
  {
   tecla=key;
   Display();
  }
}


int main( int argc, char **argv )
{
   glutInit(&argc,argv);
   glutInitDisplayMode( GLUT_RGB );

   glutInitWindowPosition( 0, 0 );
   glutInitWindowSize(400, 400 );
   glutCreateWindow("Practica 0 3d");


   Init();

   glutReshapeFunc(Reshape);
   glutDisplayFunc(Display);
   glutKeyboardFunc(Keyboard);
  
   glutMainLoop( );

   return 0;
}

