//
//*************************************************************************
// Constructor por defecto de luz
//*************************************************************************
_luz::_luz(int num_luz){
	switch(num_luz) {
		case 1:
            this->num_luz = GL_LIGHT1;
            break;
        case 2:
            this->num_luz = GL_LIGHT2;
            break;
        case 3:
            this->num_luz = GL_LIGHT3;
            break;
        case 4:
            this->num_luz = GL_LIGHT4;
            break;
        case 5:
            this->num_luz = GL_LIGHT5;
            break;
        case 6:
            this->num_luz = GL_LIGHT6;
            break;
        case 7:
            this->num_luz = GL_LIGHT7;
            break;
		default:
            this->num_luz = GL_LIGHT0;
	}
	this->tipo_luz = 2;
	this->componente_ambiental[0] = 0.3;
	this->componente_ambiental[1] = 0.3;
	this->componente_ambiental[2] = 0.3;
	this->componente_ambiental[3] = 1.0;
	this->componente_difusa[0] = 0.3;
	this->componente_difusa[1] = 0.3;
	this->componente_difusa[2] = 0.3;
	this->componente_difusa[3] = 1.0;
	this->componente_especular[0] = 0.3;
	this->componente_especular[1] = 0.3;
	this->componente_especular[2] = 0.3;
	this->componente_especular[3] = 0.3;
	this->posicion[0] = 1.0;
	this->posicion[1] = 5.0;
	this->posicion[2] = 10.0;
	this->posicion[3] = 1.0;
	this->direccion[0] = 0.0;
	this->direccion[1] = 0.0;
	this->direccion[2] = -1.0;
	this->exponente = 0.0;
	this->obturador_luz = 50.0;
}

//*************************************************************************
// Metodo para poner la posicion en los ejes X, Y, Z de donde estará la luz
// @param modo de tipo string nos da las opciones de que sea: ambiental o foco
//*************************************************************************
void _luz::set_posicion(_vertex4f posicion){
	this->posicion[0] = posicion._0;
	this->posicion[1] = posicion._1;
	this->posicion[2] = posicion._2;
	this->angulo_giro = posicion._3;
}

//*************************************************************************
// Metodo que nos indica en donde se encuentra la luz en el eje de coordenadas
//*************************************************************************
_vertex4f _luz::get_posicion(){
	_vertex4f aux;
	aux._0 = this->posicion[0];
	aux._1 = this->posicion[1];
	aux._2 = this->posicion[2];
	aux._3 = this->angulo_giro;
	return aux;
}

//*************************************************************************
// Metodo que nos permite poner el tipo de luz que queremos poner
// @param modo de tipo string nos da las opciones de que sea: ambiental o foco
// @note hay que tener en cuenta que si elegimos el ambiental no sirve
// para nada la componente specular, y si montamos foco la componente
// especular es imprecindible
//*************************************************************************
void _luz::set_modo(int modo){
	switch (modo) {
		case LUZ_FOCO: // esta es la posición para especificar el foco
			this->posicion[3] = 1.0;
			break;
		case LUZ_DIFUSA: // esta es la posición para especificar luz difusa
			this->posicion[3] = 0.0;
			break;
	}
}

//*************************************************************************
// Metodo que establece cual es el angulo de apertura del foco
// @param valor del angulo comprendido entre 0 y 180
//*************************************************************************
void _luz::set_obturador_foco(float foco){
	this->obturador_luz = foco;
}

//*************************************************************************
// Metodo que nos dice cual es el angulo actual del obturador del foco
//*************************************************************************
float _luz::get_obturador_foco(){
	return this->obturador_luz;
}

//*************************************************************************
// Para encender y apagar la luz y poder setear todos los parametros
// necesarios para OpenGL, la primera llamada enciende las luces
// la segunda llamada apaga las luces.
//*************************************************************************
void _luz::interruptor(){
    glDisable (GL_LIGHT0);
	glEnable (GL_LIGHTING);
	if (this->encendida_apagada) {
        glEnable (this->num_luz);
		glPushMatrix();
		glLightfv (this->num_luz, GL_AMBIENT, this->componente_ambiental);
		glLightfv (this->num_luz, GL_DIFFUSE, this->componente_difusa);
        glLightfv (this->num_luz, GL_SPECULAR, this->componente_especular);
		glRotatef(this->angulo_giro, 0, 1, 0);
        glLightfv (this->num_luz, GL_POSITION, this->posicion);
        glLightf(this->num_luz, GL_SPOT_CUTOFF, this->obturador_luz);
        glLightf(this->num_luz, GL_SPOT_EXPONENT, this->exponente);
        glLightfv(this->num_luz,GL_SPOT_DIRECTION,this->direccion);
		glPopMatrix();
	}else{
		glEnable (GL_LIGHT0);
		glDisable (GL_LIGHTING);
		glDisable (this->num_luz);
	}
	this->encendida_apagada = !this->encendida_apagada;
}
