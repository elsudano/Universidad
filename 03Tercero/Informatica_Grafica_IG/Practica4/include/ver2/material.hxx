//*************************************************************************
// Constructor por defecto de la clase Material
//*************************************************************************
_material::_material(){

}

void _material::set_ambiental(float r, float g, float b, float a){
	this->ambiental[0] = r;
	this->ambiental[1] = g;
	this->ambiental[2] = b;
	this->ambiental[3] = a;
}

void _material::set_difusa(float r, float g, float b, float a){
	this->difusa[0] = r;
	this->difusa[1] = g;
	this->difusa[2] = b;
	this->difusa[3] = a;
}

void _material::set_especular(float r, float g, float b, float a){
	this->especular[0] = r;
	this->especular[1] = g;
	this->especular[2] = b;
	this->especular[3] = a;
}

void _material::set_brillo(float b){
	this->brillo = b;
}

GLfloat* _material::get_ambiental(){
	return this->ambiental;
}

GLfloat* _material::get_difusa(){
	return this->difusa;
}

GLfloat* _material::get_especular(){
	return this->especular;
}

GLfloat _material::get_brillo(){
	return this->brillo;
}

//*************************************************************************
// Función que se encarga de poner los valores de las tres componente:
// Ambiental, Difusa y Especular a los valores correspondientes para
// simular en el tipo de material.
// @param material tipo int podemos elegir el tipo de material que queremos
// para el objeto
//*************************************************************************
void _material::selecionar_material(int material){
	if (DEBUG_MODE) {
		printf("%s %d %s\n", "El Material vale:", material, "material.hxx->selecionar_material->1");
	}
	switch (material) {
		case 1:
			this->ambiental[0] = 0.19225;
			this->ambiental[1] = 0.19225;
			this->ambiental[2] = 0.19225;
			this->ambiental[3] = 1.0;
			this->difusa[0] = 0.50754;
			this->difusa[1] = 0.50754;
			this->difusa[2] = 0.50754;
			this->difusa[3] = 1.0;
			this->especular[0] = 0.508273;
			this->especular[1] = 0.508273;
			this->especular[2] = 0.508273;
			this->especular[3] = 1.0;
			this->brillo = 1.0;
			break;
		case 2:
			this->ambiental[0] = 0.1745;
			this->ambiental[1] = 0.01175;
			this->ambiental[2] = 0.01175;
			this->ambiental[3] = 0.55;
			this->difusa[0] = 0.61424;
			this->difusa[1] = 0.04136;
			this->difusa[2] = 0.04136;
			this->difusa[3] = 0.55;
			this->especular[0] = 0.727811;
			this->especular[1] = 0.626959;
			this->especular[2] = 0.626952;
			this->especular[3] = 0.55;
			this->brillo = 76.8;
			break;
		case 3:
			this->ambiental[0] = 0.3;
			this->ambiental[1] = 0.3;
			this->ambiental[2] = 0.3;
			this->ambiental[3] = 1.0;
			this->difusa[0] = 0.4;
			this->difusa[1] = 0.4;
			this->difusa[2] = 0.4;
			this->difusa[3] = 1.0;
			this->especular[0] = 0.2;
			this->especular[1] = 0.2;
			this->especular[2] = 0.2;
			this->especular[3] = 1.0;
			this->brillo = 1.0;
			break;
	}
}
