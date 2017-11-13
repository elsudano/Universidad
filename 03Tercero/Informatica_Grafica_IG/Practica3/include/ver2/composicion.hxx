//******************************************************************************
// Creación de un objeto combinado
//******************************************************************************
_composicion::_composicion(){
    this->size_of_dot = 3;
    this->size_of_line = 1;
}

//******************************************************************************
// Con este metodo se genera la cabeza del muñeco
//******************************************************************************
void _composicion::cabeza(){
    char fichero[40];
    strcpy(fichero,"datos/robot/cabeza.ply");
    glPushMatrix();
    // glScalef(0.2,0.1,0.2);
    // glTranslatef(5.0, 5.0, 0.0);
    this->leer_objeto(fichero);
    glPopMatrix();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->cabeza->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->cabeza->2", this->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->cabeza->3", this->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el cuerpo del muñeco
//******************************************************************************
void _composicion::cuerpo(){
    char fichero[40];
    strcpy(fichero,"datos/robot/tronco-SUP.ply");
    glPushMatrix();
    this->leer_objeto(fichero);
    glPopMatrix();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->cuerpo->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->cuerpo->2", this->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->cuerpo->3", this->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el antebrazo derecho del muñeco
//******************************************************************************
void _composicion::antebrazoderecho(){
    char fichero[40];
    strcpy(fichero,"datos/robot/antebrazo-DR.ply");
    glPushMatrix();
    this->leer_objeto(fichero);
    glPopMatrix();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->antebrazoderecho->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->antebrazoderecho->2", this->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->antebrazoderecho->3", this->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el brazo derecho del muñeco
//******************************************************************************
void _composicion::brazoderecho(){
    char fichero[40];
    strcpy(fichero,"datos/robot/brazo-DR.ply");
    glPushMatrix();
    this->leer_objeto(fichero);
    glPopMatrix();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->brazoderecho->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->brazoderecho->2", this->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->brazoderecho->3", this->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el antebrazo izquierdo del muñeco
//******************************************************************************
void _composicion::antebrazoizquierdo(){
    char fichero[40];
    strcpy(fichero,"datos/robot/antebrazo-IZ.ply");
    glPushMatrix();
    this->leer_objeto(fichero);
    glPopMatrix();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->antebrazoizquierdo->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->antebrazoizquierdo->2", this->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->antebrazoizquierdo->3", this->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el brazo izquierdo del muñeco
//******************************************************************************
void _composicion::brazoizquierdo(){
    char fichero[40];
    strcpy(fichero,"datos/robot/brazo-IZ.ply");
    glPushMatrix();
    this->leer_objeto(fichero);
    glPopMatrix();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->brazoizquierdo->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->brazoizquierdo->2", this->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->brazoizquierdo->3", this->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera las piernas y los pies del muñeco
//******************************************************************************
void _composicion::troncoinferior(){
    char fichero[40];
    strcpy(fichero,"datos/robot/tronco-INF.ply");
    glPushMatrix();
    this->leer_objeto(fichero);
    glPopMatrix();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->troncoinferior->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->troncoinferior->2", this->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->troncoinferior->3", this->caras.size());
    }
}

//******************************************************************************
// Metodo que sirve para simplificar el redibujado del modelo
//******************************************************************************
// void _composicion::dibuja(float r, float g,float b){
//     if (this->mode_of_paint == 1)
//         this->draw_puntos(r, g, b, this->size_of_dot);
//     else if (this->mode_of_paint == 2)
//         this->draw_aristas(r, g, b, this->size_of_line);
//     else if (this->mode_of_paint == 3)
//         this->draw_solido(r, g, b);
//     else if (this->mode_of_paint == 4)
//         this->draw_solido_ajedrez(r, g, b, 1.0-r, 1.0-g, 1.0-b);
//     else if (this->mode_of_paint == 5)
//         this->draw_solido_colores();
// }

//******************************************************************************
// Con este metodo se dibuja el modelo completo del muñeco, osea que se juntan todas
// las piezas que se han generado antes para que se vea un modelo completo
//******************************************************************************
void _composicion::componer(){
    glDepthFunc(GL_LEQUAL);
    // Activamos el el Z-Buffer
    glEnable(GL_DEPTH_TEST);
    // Borramos el buffer de color y el Z-Buffer
    //glClearColor(0.0,0.0,0.0,0.0);
    //glClearDepth(1.0);
    //glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    // Proyección perspectiva. El ángulo de visualización es de 60
    // grados, la razón ancho/alto es 1 (son inguales), la distancia
    // mínima es z=1.0, y la distancia máxima es z=100.0
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(25.0,1.0,1.0,100.0);
    glMatrixMode(GL_MODELVIEW);
    glTranslatef(0.0,0.0,0.0);
    // Comenzamos con el dibujado del modelo
    // Dibujamos la cabeza
    //this->cabeza();
    // Dibujamos el cuerpo
	this->cuerpo();
    // Dibujamos el antebrazo derecho
    this->antebrazoderecho();
    // Dibujamos el brazo derecho
	this->brazoderecho();
    // Dibujamos el antebrazo izquierdo
	this->antebrazoizquierdo();
	// Dibujamos el brazo izquierdo
	this->brazoizquierdo();
    // Dibujamos el resto del cuerpo
    this->troncoinferior();
}
