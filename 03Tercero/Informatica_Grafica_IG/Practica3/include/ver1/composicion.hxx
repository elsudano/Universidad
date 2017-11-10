//******************************************************************************
// Creación de un objeto combinado
//******************************************************************************
_doraemon::_doraemon(){
    this->mode_of_paint = 1;
    this->size_of_dot = 3;
    this->size_of_line = 1;
}

//******************************************************************************
// Con este metodo se genera la cabeza del muñeco
//******************************************************************************
void _doraemon::cabeza(){
    char fichero[40];
    strcpy(fichero,"datos/robot/cabeza.ply");
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: doraemon.hxx->cabeza->1",fichero);
    }
    // glScalef(0.2,0.1,0.2);
    // glTranslatef(5.0, 5.0, 0.0);
    this->dibuja(0.5, 0.3, 0.0, fichero);
}

//******************************************************************************
// Con este metodo se genera el cuerpo del muñeco
//******************************************************************************
void _doraemon::cuerpo(){
    char fichero[40];
    strcpy(fichero,"datos/robot/tronco-SUP.ply");
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: doraemon.hxx->cuerpo->1",fichero);
    }
    this->dibuja(0.5, 0.3, 0.0, fichero);
}

//******************************************************************************
// Con este metodo se genera el antebrazo derecho del muñeco
//******************************************************************************
void _doraemon::antebrazoderecho(){
    char fichero[40];
    strcpy(fichero,"datos/robot/antebrazo-DR.ply");
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: doraemon.hxx->antebrazoderecho->1",fichero);
    }
    this->dibuja(0.5, 0.3, 0.0, fichero);
}

//******************************************************************************
// Con este metodo se genera el brazo derecho del muñeco
//******************************************************************************
void _doraemon::brazoderecho(){
    char fichero[40];
    strcpy(fichero,"datos/robot/brazo-DR.ply");
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: doraemon.hxx->brazoderecho->1",fichero);
    }
    this->dibuja(0.5, 0.3, 0.0, fichero);
}

//******************************************************************************
// Con este metodo se genera el antebrazo izquierdo del muñeco
//******************************************************************************
void _doraemon::antebrazoizquierdo(){
    char fichero[40];
    strcpy(fichero,"datos/robot/antebrazo-IZ.ply");
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: doraemon.hxx->antebrazoizquierdo->1",fichero);
    }
    this->dibuja(0.5, 0.3, 0.0, fichero);
}

//******************************************************************************
// Con este metodo se genera el brazo izquierdo del muñeco
//******************************************************************************
void _doraemon::brazoizquierdo(){
    char fichero[40];
    strcpy(fichero,"datos/robot/brazo-IZ.ply");
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: doraemon.hxx->brazoizquierdo->1",fichero);
    }
    this->dibuja(0.5, 0.3, 0.0, fichero);
}

//******************************************************************************
// Con este metodo se genera las piernas y los pies del muñeco
//******************************************************************************
void _doraemon::troncoinferior(){
    char fichero[40];
    strcpy(fichero,"datos/robot/tronco-INF.ply");
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: doraemon.hxx->troncoinferior->1",fichero);
    }
    this->dibuja(0.5, 0.3, 0.0, fichero);
}

//******************************************************************************
// Con este metodo se el modelo completo del muñeco, osea que se juntan todas
// las piezas que se han generado antes para que se vea un modelo completo
//******************************************************************************
void _doraemon::dibuja(float r, float g,float b, char fichero[]){
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: doraemon.hxx->dibuja->1",fichero);
    }
    this->leer_objeto(fichero);
    if (this->mode_of_paint == 1)
        this->draw_puntos(r, g, b, this->size_of_dot);
    else if (this->mode_of_paint == 2)
        this->draw_aristas(r, g, b, this->size_of_line);
    else if (this->mode_of_paint == 3)
        this->draw_solido(r, g, b);
    else if (this->mode_of_paint == 4)
        this->draw_solido_ajedrez(r, g, b, 1.0-r, 1.0-g, 1.0-b);
    else if (this->mode_of_paint == 5)
        this->draw_solido_colores();
}
//******************************************************************************
// Con este metodo se el modelo completo del muñeco, osea que se juntan todas
// las piezas que se han generado antes para que se vea un modelo completo
//******************************************************************************
void _doraemon::componer(int modo){
    this->mode_of_paint = modo;
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
    gluPerspective(60.0,1.0,1.0,100.0);
    glMatrixMode(GL_MODELVIEW);
    glTranslatef(0.0,0.0,0.0);
    // Comenzamos con el dibujado del modelo
    // Dibujamos la cabeza
    this->cabeza();
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
    //this->troncoinferior();
}
