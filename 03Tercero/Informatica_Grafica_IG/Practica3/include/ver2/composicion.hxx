//******************************************************************************
// Creación de un objeto combinado
//******************************************************************************
_composicion::_composicion(){
    this->parte_cuerpo = new _objeto3D();
    this->giro_cintura = 0.0;
    this->eleva_hombro = 0.0;
    this->eleva_brazo = 0.0;
    this->size_of_dot = 3;
    this->size_of_line = 1;
    this->r = 0.3176;
    this->g = 0.4039;
    this->b = 0.3098;
}

//******************************************************************************
// Con este metodo se genera la cabeza del muñeco
//******************************************************************************
void _composicion::cabeza(int mode){
    strcpy(this->fichero,"datos/robot/cabeza.ply");
    this->parte_cuerpo->leer_objeto(fichero);
	glPushMatrix();
	//glScalef(1.5,1.5,1.5);
    glTranslatef(0.0,0.0,-0.025);
	this->dibuja(mode);
	glPopMatrix();
    this->parte_cuerpo->clear();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->cabeza->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->cabeza->2", this->parte_cuerpo->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->cabeza->3", this->parte_cuerpo->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el cuerpo del muñeco
//******************************************************************************
void _composicion::cuerpo(int mode){
    strcpy(this->fichero,"datos/robot/tronco-SUP.ply");
    this->parte_cuerpo->leer_objeto(fichero);
    glPushMatrix();
    this->dibuja(mode);
    glPopMatrix();
    this->parte_cuerpo->clear();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->cuerpo->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->cuerpo->2", this->parte_cuerpo->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->cuerpo->3", this->parte_cuerpo->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el antebrazo derecho del muñeco
//******************************************************************************
void _composicion::antebrazoderecho(int mode){
    strcpy(this->fichero,"datos/robot/antebrazo-DR.ply");
    this->parte_cuerpo->leer_objeto(fichero);
    glPushMatrix();
    this->dibuja(mode);
    glPopMatrix();
    this->parte_cuerpo->clear();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->antebrazoderecho->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->antebrazoderecho->2", this->parte_cuerpo->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->antebrazoderecho->3", this->parte_cuerpo->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el brazo derecho del muñeco
//******************************************************************************
void _composicion::brazoderecho(int mode){
    strcpy(this->fichero,"datos/robot/brazo-DR.ply");
    this->parte_cuerpo->leer_objeto(fichero);
    glPushMatrix();
    this->dibuja(mode);
    glPopMatrix();
    this->parte_cuerpo->clear();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->brazoderecho->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->brazoderecho->2", this->parte_cuerpo->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->brazoderecho->3", this->parte_cuerpo->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el antebrazo izquierdo del muñeco
//******************************************************************************
void _composicion::antebrazoizquierdo(int mode){
    strcpy(this->fichero,"datos/robot/antebrazo-IZ.ply");
    this->parte_cuerpo->leer_objeto(fichero);
    glPushMatrix();
    this->dibuja(mode);
    glPopMatrix();
    this->parte_cuerpo->clear();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->antebrazoizquierdo->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->antebrazoizquierdo->2", this->parte_cuerpo->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->antebrazoizquierdo->3", this->parte_cuerpo->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera el brazo izquierdo del muñeco
//******************************************************************************
void _composicion::brazoizquierdo(int mode){
    strcpy(this->fichero,"datos/robot/brazo-IZ.ply");
    this->parte_cuerpo->leer_objeto(fichero);
    glPushMatrix();
    this->dibuja(mode);
    glPopMatrix();
    this->parte_cuerpo->clear();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->brazoizquierdo->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->brazoizquierdo->2", this->parte_cuerpo->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->brazoizquierdo->3", this->parte_cuerpo->caras.size());
    }
}

//******************************************************************************
// Con este metodo se genera las piernas y los pies del muñeco
//******************************************************************************
void _composicion::troncoinferior(int mode){
    strcpy(this->fichero,"datos/robot/tronco-INF.ply");
    this->parte_cuerpo->leer_objeto(fichero);
    glPushMatrix();
    this->dibuja(mode);
    glPopMatrix();
    this->parte_cuerpo->clear();
    if (DEBUG_MODE) {
        printf("%s %s\n", "La ruta del fichero es: composicion.hxx->troncoinferior->1",fichero);
        printf("%s %lu\n", "Tamaño del Vector Vertices: composicion.hxx->troncoinferior->2", this->parte_cuerpo->vertices.size());
        printf("%s %lu\n", "Tamaño del Vector Caras: composicion.hxx->troncoinferior->3", this->parte_cuerpo->caras.size());
    }
}

//******************************************************************************
// Metodo que sirve para simplificar el redibujado del modelo
// el color del objeto se define directamente en el Constructor
//******************************************************************************
void _composicion::dibuja(int mode){
    if (mode == 1){
    	this->parte_cuerpo->draw_puntos(this->r, this->g, this->b, this->size_of_dot);
    }else if (mode == 2){
        this->parte_cuerpo->draw_aristas(this->r, this->g, this->b, this->size_of_line);
    }else if (mode == 3){
        this->parte_cuerpo->draw_solido(this->r, this->g, this->b);
    }else if (mode == 4){
        this->parte_cuerpo->draw_solido_ajedrez(this->r, this->g, this->b, 1.0-this->r, 1.0-this->g, 1.0-this->b);
    }else if (mode == 5){
        this->parte_cuerpo->draw_solido_colores();
    }
}

//******************************************************************************
// Con este metodo se dibuja el modelo completo del muñeco, osea que se juntan todas
// las piezas que se han generado antes para que se vea un modelo completo
//******************************************************************************
void _composicion::componer(int mode){
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
    // Comenzamos con el dibujado del modelo
    // Dibujamos la cabeza
    glPushMatrix(); // para que gire la cintura
        glRotatef(this->giro_cintura, 0, 1, 0);
        this->cabeza(mode);
        // Dibujamos el cuerpo
        this->cuerpo(mode);
        glPushMatrix(); // para que eleve los antebrazos y brazos
            glRotatef(this->eleva_hombro, 1, 0, 0);
            // Dibujamos el antebrazo derecho
            this->antebrazoderecho(mode);
            // Dibujamos el antebrazo izquierdo
            this->antebrazoizquierdo(mode);
            glPushMatrix(); // para que eleve los brazos
                glRotatef(this->eleva_brazo, 1, 0, 0);
                // Dibujamos el brazo derecho
                this->brazoderecho(mode);
            	// Dibujamos el brazo izquierdo
                this->brazoizquierdo(mode);
            glPopMatrix();
        glPopMatrix();
    glPopMatrix();
    // Dibujamos el resto del cuerpo
    this->troncoinferior(mode);
    glFlush();
}

//******************************************************************************
// Con esto asignamos la cantidad de grados que queremos girar la cintura
// si queremos que vaya a la derecha del robot serán valores negativos
// a la izquierda valores positivos.
// @param grados: es un valor float expresado en grados con un maximo de +/-45º
//******************************************************************************
void _composicion::set_giro_cintura(float grados){
    if (DEBUG_MODE) {
      printf("%s %f\n", "Los grados son: composicion.hxx->set_giro_cintura->1",grados);
    }
    this->giro_cintura = grados;
}

//******************************************************************************
// Con esto solicitamos el tamaño de giro de cintura se devuelve en grados
//******************************************************************************
float _composicion::get_giro_cintura(){
    if (DEBUG_MODE) {
      printf("%s %f\n", "Los grados son: composicion.hxx->set_giro_cintura->1",this->giro_cintura);
    }
    return this->giro_cintura;
}

//******************************************************************************
// Con esto asignamos la cantidad de cm que queremos elevar el hombro
// si queremos que vaya hacia arriba son valores positivos y si queremos
// que baje valores negativos.
// @param cm: es un valor float expresado en cm con un maximo de +20/-5
//******************************************************************************
void _composicion::set_eleva_hombro(float cm){
    if (DEBUG_MODE) {
      printf("%s %f\n", "Los cm son: composicion.hxx->set_eleva_hombro->1",cm);
    }
    this->eleva_hombro = cm;
}

//******************************************************************************
// Con esto solicitamos el grado de elevación que tiene el hombro
//******************************************************************************
float _composicion::get_eleva_hombro(){
    if (DEBUG_MODE) {
      printf("%s %f\n", "Los grados son: composicion.hxx->get_eleva_hombro->1",this->eleva_hombro);
    }
    return this->eleva_hombro;
}

//******************************************************************************
// Con esto asignamos la cantidad de cm que queremos elevar el brazo
// si queremos que vaya hacia arriba son valores positivos y si queremos
// que baje valores negativos.
// @param cm: es un valor float expresado en cm con un maximo de +20/-5
//******************************************************************************
void _composicion::set_eleva_brazo(float cm){
    if (DEBUG_MODE) {
      printf("%s %f\n", "Los cm son: composicion.hxx->set_eleva_brazo->1",cm);
    }
    this->eleva_brazo = cm;
}

//******************************************************************************
// Con esto solicitamos el grado de elevación que tiene el brazo
//******************************************************************************
float _composicion::get_eleva_brazo(){
    if (DEBUG_MODE) {
      printf("%s %f\n", "Los cm son: composicion.hxx->get_eleva_brazo->1",this->eleva_brazo);
    }
    return this->eleva_brazo;
}
