//*************************************************************************
// Constructor por defecto de la clase Textura
//*************************************************************************
_textura::_textura(){}

// Función que se encarga de leer cualquier imagen y cargarla en un vector
// de datos yque se devuelve.
std::vector<unsigned char> _textura::cargar_imagen_a_memoria(const char* fichero, int &ancho, int &alto){
	//hacemos la lectura del fichero y la cargamos en memoria
	textura.load(fichero);
	// empaquetamos bien los datos
	for (long y = 0; y < textura.height(); y++)
		for (long x = 0; x < textura.width(); x++) {
			unsigned char *r = textura.data(x, y, 0, 0);
			unsigned char *g = textura.data(x, y, 0, 1);
			unsigned char *b = textura.data(x, y, 0, 2);
			this->data.push_back(*r);
			this->data.push_back(*g);
			this->data.push_back(*b);
		}
	ancho = textura.width();
	alto = textura.height();
	return this->data;
}

// Función que se encarga de preparar la textura para que OpenGl pueda
// usarla, le pone una ID, pasa los datos de la imagen a la textura
// prepara los parametros de OpenGL para su uso.
GLuint _textura::prepara_textura(int id, int &ancho, int &alto, std::vector<unsigned char> datos){
	glGenTextures(id, &this->textura_id);
	glBindTexture(GL_TEXTURE_2D, this->textura_id);
	glActiveTexture(GL_TEXTURE0);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	// TRASFIERE LOS DATOS A GPU
	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, ancho, alto, 0, GL_RGB, GL_UNSIGNED_BYTE, &datos[0]);
	return this->textura_id;
}
