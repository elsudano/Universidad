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
	this->encendida_apagada = false;
}


_luz::_luz(int num_luz, GLfloat *datos, string modo){

}

//*************************************************************************
// Metodo para poner la posicion en los ejes X, Y, Z de donde estará la luz
// @param modo de tipo string nos da las opciones de que sea: ambiental o foco
//*************************************************************************
void _luz::set_posicion(_vertex3f posicion){
	this->posicion[0] = posicion.x;
	this->posicion[1] = posicion.y;
	this->posicion[2] = posicion.z;
}

//*************************************************************************
// Metodo que nos permite poner el tipo de luz que queremos poner
// @param modo de tipo string nos da las opciones de que sea: ambiental o foco
// @note hay que tener en cuenta que si elegimos el ambiental no sirve
// para nada la componente specular, y si montamos foco la componente
// especular es imprecindible
//*************************************************************************
void _luz::set_modo(string modo){
	if (modo == "ambiental") {
		this->posicion[3] = 1.0;
	}else if (modo == "foco") {
		this->posicion[3] = 0.0;
	}
}

//*************************************************************************
// Para encender y apagar la luz y poder setear todos los parametros
// necesarios para OpenGL
// @param bool on_off indica si queremos encenderla o apagarla
//*************************************************************************
void _luz::interruptor(){
    glDisable (GL_LIGHT0);
	if (!this->encendida_apagada) {
		glEnable (GL_LIGHTING);
        glEnable (this->num_luz);
		glLightfv (this->num_luz, GL_AMBIENT, this->ambiental);
		glLightfv (this->num_luz, GL_DIFFUSE, this->difusa);
        glLightfv (this->num_luz, GL_SPECULAR, this->especular);
        glLightfv (this->num_luz, GL_POSITION, this->posicion);
        glLightf(this->num_luz, GL_SPOT_CUTOFF, 10.0);
        glLightf(this->num_luz, GL_SPOT_EXPONENT, 20.0);
        //glLightfv(this->num_luz,GL_SPOT_DIRECTION,_spotlight_direction);
	}else{
		glDisable (GL_LIGHTING);
	}
	this->encendida_apagada = !this->encendida_apagada;
}
