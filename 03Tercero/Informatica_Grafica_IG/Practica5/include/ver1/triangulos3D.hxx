//
//*************************************************************************
// Constructor por defecto de Triangulos3D
//*************************************************************************
_triangulos3D::_triangulos3D(){}

//*************************************************************************
// Función para dibujar aristas
// @param r valor float que indica el valor de la componente roja del color
// @param g valor float que indica el valor de la componente verde del color
// @param b valor float que indica el valor de la componente azul del color
// @param grosor valor int que indica el grosor de la linea
//*************************************************************************
void _triangulos3D::draw_aristas(float r, float g, float b, int grosor){
	glColor3f(r, g, b);     // Le asignamos un color a la línea
	glLineWidth(grosor);    // Asignación del grosor de la línea

	const int numero_caras = caras.size();

	glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

	glBegin(GL_TRIANGLES);
	for (int j = 0; j < numero_caras; j++) {
		glVertex3f(vertices.at(caras.at(j)._0).x, vertices.at(caras.at(j)._0).y, vertices.at(caras.at(j)._0).z);
		glVertex3f(vertices.at(caras.at(j)._1).x, vertices.at(caras.at(j)._1).y, vertices.at(caras.at(j)._1).z);
		glVertex3f(vertices.at(caras.at(j)._2).x, vertices.at(caras.at(j)._2).y, vertices.at(caras.at(j)._2).z);
	}
	glEnd();
}

//*************************************************************************
// Función para colorear el sólido
// @param r valor float que indica el valor de la componente roja del color
// @param g valor float que indica el valor de la componente verde del color
// @param b valor float que indica el valor de la componente azul del color
//*************************************************************************
void _triangulos3D::draw_solido(float r, float g, float b){
	glColor3f(r, g, b);     // Le asignamos un color al sólido

	const int numero_caras = caras.size();

	glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

	glBegin(GL_TRIANGLES);
	for (int j = 0; j < numero_caras; j++) {
		glVertex3f(vertices.at(caras.at(j)._0).x, vertices.at(caras.at(j)._0).y, vertices.at(caras.at(j)._0).z);
		glVertex3f(vertices.at(caras.at(j)._1).x, vertices.at(caras.at(j)._1).y, vertices.at(caras.at(j)._1).z);
		glVertex3f(vertices.at(caras.at(j)._2).x, vertices.at(caras.at(j)._2).y, vertices.at(caras.at(j)._2).z);
	}
	glEnd();
}


//*************************************************************************
// Función para colorear el sólido segun los colores de los vertices
// esta sin funcionar en esta practica por ahora
//*************************************************************************
void _triangulos3D::draw_solido_colores(){
	const int numero_vertices = this->vertices.size();
	const int numero_caras = this->caras.size();
	_vertex3f color_aux;
	for (int i = 0; i < numero_vertices; i++) {
		// creamos un color por cada punto
		color_aux.x = (double) rand() / (RAND_MAX);
		color_aux.y = (double) rand() / (RAND_MAX);
		color_aux.z = (double) rand() / (RAND_MAX);
		// añadimos el color a el vector
		colores.push_back(color_aux);
	}
	if (DEBUG_MODE) {
		printf("%s %d\n", "numero de caras: triangulos3D.hxx->draw_solido_colores->1", numero_caras);
		printf("%s %lu\n", "numero de colores: triangulos3D.hxx->draw_solido_colores->2", this->colores.size());
	}
	glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

	glBegin(GL_TRIANGLES);
	glShadeModel(GL_SMOOTH);
	for (int j = 0; j < numero_caras; j++) {
		glColor3f(colores.at(caras.at(j)._0).x,colores.at(caras.at(j)._0).y,colores.at(caras.at(j)._0).z);
		glVertex3f(vertices.at(caras.at(j)._0).x, vertices.at(caras.at(j)._0).y, vertices.at(caras.at(j)._0).z);
		glColor3f(colores.at(caras.at(j)._1).x,colores.at(caras.at(j)._1).y,colores.at(caras.at(j)._1).z);
		glVertex3f(vertices.at(caras.at(j)._1).x, vertices.at(caras.at(j)._1).y, vertices.at(caras.at(j)._1).z);
		glColor3f(colores.at(caras.at(j)._2).x,colores.at(caras.at(j)._2).y,colores.at(caras.at(j)._2).z);
		glVertex3f(vertices.at(caras.at(j)._2).x, vertices.at(caras.at(j)._2).y, vertices.at(caras.at(j)._2).z);
	}
	glShadeModel(GL_FLAT);
	glEnd();
}

//*************************************************************************
// Función para colorear las caras pares de un color y las impares de otro
// @param r1 valor float que indica el valor de la componente roja del color del primer color
// @param g1 valor float que indica el valor de la componente verde del color del primer color
// @param b1 valor float que indica el valor de la componente azul del color del primer color
// @param r2 valor float que indica el valor de la componente roja del color del segundo color
// @param g2 valor float que indica el valor de la componente verde del color del segundo color
// @param b2 valor float que indica el valor de la componente azul del color del segundo color
//*************************************************************************
void _triangulos3D::draw_solido_ajedrez(float r1, float g1, float b1, float r2, float g2, float b2){
	const int numero_caras = caras.size(); // para evitar el warning de comparación

	glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	glBegin(GL_TRIANGLES);
	for (int j = 0; j < numero_caras; ++j) {
		if (j % 2 == 0)
			glColor3f(r1, g1, b1);          // Le asignamos un color a una cara par
		else
			glColor3f(r2, g2, b2);          // Le asignamos otro color a la cara impar

		glVertex3f(vertices.at(caras.at(j)._0).x, vertices.at(caras.at(j)._0).y, vertices.at(caras.at(j)._0).z);
		glVertex3f(vertices.at(caras.at(j)._1).x, vertices.at(caras.at(j)._1).y, vertices.at(caras.at(j)._1).z);
		glVertex3f(vertices.at(caras.at(j)._2).x, vertices.at(caras.at(j)._2).y, vertices.at(caras.at(j)._2).z);
	}
	glEnd();
}

//*************************************************************************
// Función para colorear las caras pares de un color y las impares de otro
// @param suavizado boleano que indica si lo queremos suavizado o no
//*************************************************************************
void _triangulos3D::draw_solido_luz(bool suavizado){
	if (this->normales_caras.size() == 0) {
		this->calcular_normales_caras();
		this->calcular_normales_vertices();
	}
	const int numero_caras = caras.size(); // para evitar el warning de comparación

	glClearDepth (1.0);
	glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glEnable(GL_DEPTH_TEST); // Activar el modo EPO
	if (suavizado) {
		glShadeModel(GL_SMOOTH);
	}else{
		glShadeModel(GL_FLAT);
	}
	if (DEBUG_MODE) {
		printf("%s %lu\n", "Tamaño vector normales_vertices: triangulos3D.hxx->draw_solido_luz->3", this->normales_vertices.size());
		printf("%s %lu\n", "Tamaño vector normales_caras: triangulos3D.hxx->draw_solido_luz->4", this->normales_caras.size());
		printf("%s %lu\n", "Tamaño vector vertices: triangulos3D.hxx->draw_solido_luz->5", this->vertices.size());
		printf("%s %lu\n", "Tamaño vector caras: triangulos3D.hxx->draw_solido_luz->6", this->caras.size());
	}
	glMaterialfv(GL_FRONT, GL_AMBIENT,this->el_material.get_ambiental()); //GL_FRONT_AND_BACK
	glMaterialfv(GL_FRONT, GL_DIFFUSE,this->el_material.get_difusa());
	glMaterialfv(GL_FRONT, GL_SPECULAR,this->el_material.get_especular());
	glMaterialf(GL_FRONT, GL_SHININESS,this->el_material.get_brillo());
	glEnable(GL_NORMALIZE);
	glBegin(GL_TRIANGLES);
	for (int j = 0; j < numero_caras; j++) {
		if (!suavizado)
			glNormal3f(normales_caras.at(caras.at(j)._0).x, normales_caras.at(caras.at(j)._0).y, normales_caras.at(caras.at(j)._0).z);
		else
			glNormal3f(normales_vertices.at(caras.at(j)._0).x, normales_vertices.at(caras.at(j)._0).y, normales_vertices.at(caras.at(j)._0).z);
		glVertex3f(vertices.at(caras.at(j)._0).x, vertices.at(caras.at(j)._0).y, vertices.at(caras.at(j)._0).z);
		if (suavizado)
			glNormal3f(normales_vertices.at(caras.at(j)._1).x, normales_vertices.at(caras.at(j)._1).y, normales_vertices.at(caras.at(j)._1).z);
		glVertex3f(vertices.at(caras.at(j)._1).x, vertices.at(caras.at(j)._1).y, vertices.at(caras.at(j)._1).z);
		if (suavizado)
			glNormal3f(normales_vertices.at(caras.at(j)._2).x, normales_vertices.at(caras.at(j)._2).y, normales_vertices.at(caras.at(j)._2).z);
		glVertex3f(vertices.at(caras.at(j)._2).x, vertices.at(caras.at(j)._2).y, vertices.at(caras.at(j)._2).z);
	}
	glEnd();
}

//*************************************************************************
// Función que nos permite asignar la imagen que queremos en nuestro objeto
// y una vez cargada la imagen se dibuja el objeto aplicando dicha imagen
// como una textura al mismo.
// @param imagen cadena de texto que indica la ruta de la imagen que servirá
// como textura en el objeto.
//*************************************************************************
void _triangulos3D::draw_solido_tex(const char* imagen){
	// almacenamos los valores de alto y ancho de la imagen
	int ancho, alto;
	int cantidad = vertices.size();
	_vertex3f punto_aux;
	int desde = cantidad/4;
	int hasta = ((cantidad/4)*2)+1;
	GLfloat vertices_aux[cantidad*2];
	GLfloat vertices_tex[cantidad*2] = {0.0};
	if (DEBUG_MODE) {
		printf("%s %d\n", "Cantidad de elementos: triangulos3D.hxx->draw_solido_tex->1", cantidad);
		printf("%s %d\n", "Desde: triangulos3D.hxx->draw_solido_tex->2", desde);
		printf("%s %d\n", "Hasta: triangulos3D.hxx->draw_solido_tex->3", hasta);
		printf("%s %d,%d\n", "Tamaño de la imagen antes de leer, ancho y alto: triangulos3D.hxx->draw_solido_tex->4", ancho, alto);
	}
	// cargamos la imagen en memoria
	std::vector<unsigned char> imagen_en_memoria = this->la_textura.cargar_imagen_a_memoria(imagen, ancho, alto);
	if (DEBUG_MODE) {
		printf("%s %d\n", "Tamaño calculado del vector de imagen en memoria: triangulos3D.hxx->draw_solido_tex->5",ancho*alto*3);
		printf("%s %lu\n", "Tamaño real del vector de imagen en memoria: triangulos3D.hxx->draw_solido_tex->6",imagen_en_memoria.size());
	}
	// preparamos la textura para que la use OpenGL
	GLuint textura_id = this->la_textura.prepara_textura(1,ancho,alto,imagen_en_memoria);
	if (DEBUG_MODE)
		printf("%s %d,%d\n", "Tamaño de la imagen despues de leer, ancho y alto: triangulos3D.hxx->draw_solido_tex->8", ancho, alto);
	// limpiamos OpenGL y la pantalla
	glClearDepth (1.0);
	glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	// Posición del observador -> (5.0, 10, 20)
	// Destino de la vista -> (0,0,0)
	// Definimos el eje Y en positivo  -> (0.0, 1.0, 0.0)
	gluLookAt(5.0, 10, 20, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	for (int i = 0; i < cantidad; i++) {
		if (DEBUG_MODE)
			printf("%s %d\n", "Valor de i: triangulos3D.hxx->draw_solido_tex->9", i);
		punto_aux = this->vertices.at(i);
		vertices_aux[2*i] = punto_aux._0;
		vertices_aux[2*i+1] = punto_aux._1;
		if (i >= desde && i <= hasta) {
			vertices_tex[2*i] = 1.0;
			vertices_tex[2*i+1] = 1.0;
			if (DEBUG_MODE) {
				printf("%s %d\n", "Valor de desde: triangulos3D.hxx->draw_solido_tex->10", desde);
				printf("%s %d\n", "Valor de hasta: triangulos3D.hxx->draw_solido_tex->11", hasta);
			}
		}
	}
	if (DEBUG_MODE) {
		printf("%s\n%s", "Contenido de vector_aux: triangulos3D.hxx->draw_solido_tex->12","[");
		for (int i = 0; i < cantidad; i++) {
			printf(" %f %f ",vertices_aux[2*i],vertices_aux[2*i+1]);
		}
		printf("%s\n%s\n%s", "]", "Contenido de vector_tex: triangulos3D.hxx->draw_solido_tex->13","[");
		for (int i = 0; i < cantidad; i++) {
			printf(" %f %f ",vertices_tex[2*i],vertices_tex[2*i+1]);
		}
		printf("%s\n","]");
	}
	glDisable(GL_COLOR_MATERIAL);
	glEnable(GL_TEXTURE_2D);
	glEnableClientState(GL_VERTEX_ARRAY);
	glEnableClientState (GL_TEXTURE_COORD_ARRAY_EXT);
	glActiveTexture(GL_TEXTURE0);
	glBindTexture(GL_TEXTURE_2D, textura_id);
	glVertexPointer(2, GL_FLOAT, 0, vertices_aux);
	glTexCoordPointer(2, GL_FLOAT, 0, vertices_tex);

	glDrawArrays(GL_TRIANGLES, 0, this->caras.size());

	this->la_textura.limpia_textura();
	glDisableClientState(GL_VERTEX_ARRAY);
	glBindTexture(GL_TEXTURE_2D, 0);
	glDisable(GL_TEXTURE_2D);
	glDisable(GL_COLOR_MATERIAL);
	//no delete this line
	glutSwapBuffers();
}

//*************************************************************************
// Función para calcular las direcciones normales de los vertices para
// el calculo de brillos
//*************************************************************************
void _triangulos3D::calcular_normales_vertices(){
	const int NUMERO_VERTICES = vertices.size();
	const int NUMERO_CARAS = caras.size();

	_vertex3f aux;
	_vertex3f normalVertice;
	int numeroCarasContenidas=0;
	aux.x=0.0;
	aux.y=0.0;
	aux.z=0.0;
	if (DEBUG_MODE)
		printf("%s\n", "Entramos para calcular las normales de vertices triangulos3D.hxx->calcular_normales_vertices->1");
	for (int i=0; i<NUMERO_VERTICES; i++) {
		for (int j=0; j<NUMERO_CARAS; j++) {
			if ((i==caras[j]._0) || (i==caras[j]._1) || (i==caras[j]._2)) {
				numeroCarasContenidas++;
				aux.x = aux.x + normales_caras[j].x;
				aux.y = aux.y + normales_caras[j].y;
				aux.z = aux.z + normales_caras[j].z;
			}
		}
		aux.x=aux.x/numeroCarasContenidas;
		aux.y=aux.y/numeroCarasContenidas;
		aux.z=aux.z/numeroCarasContenidas;

		normalVertice.x = aux.x;
		normalVertice.y = aux.y;
		normalVertice.z = aux.z;

		normales_vertices.push_back(normalVertice);

		numeroCarasContenidas = 0.0;
		aux.x=0.0;
		aux.y=0.0;
		aux.z=0.0;
	}
	if (DEBUG_MODE) {
		printf("%s %lu\n", "Tamaño vector normales_vertices: triangulos3D.hxx->calcular_normales_vertices->2", this->normales_vertices.size());
		printf("%s %lu\n", "Tamaño vector normales_caras: triangulos3D.hxx->calcular_normales_vertices->3", this->normales_caras.size());
		printf("%s %lu\n", "Tamaño vector vertices: triangulos3D.hxx->calcular_normales_vertices->4", this->vertices.size());
		printf("%s %lu\n", "Tamaño vector caras: triangulos3D.hxx->calcular_normales_vertices->5", this->caras.size());
		printf("%s\n", "Final de la función de calculo triangulos3D.hxx->calcular_normales_vertices->6");
	}
}

//*************************************************************************
// Función para calcular las direcciones normales de las caras para
// el calculo de brillos
//*************************************************************************
void _triangulos3D::calcular_normales_caras(){
	double modulo;
	_vertex3f normalCara;
	if (DEBUG_MODE)
		printf("%s\n", "Entramos para calcular las normales de caras triangulos3D.hxx->calcular_normales_caras->1");
	for (unsigned int i=0; i<caras.size(); i++) {
		//Calculamos los vectores de cada cara, Cara 1 = ABC-->vAB = B-A, vBC = C-B
		//A(x,y,z) - B(x,y,z)
		float vectorAB[1][3]={(vertices[caras[i]._1].x)-(vertices[caras[i]._0].x),(vertices[caras[i]._1].y)-(vertices[caras[i]._0].y),(vertices[caras[i]._1].z)-(vertices[caras[i]._0].z)};
		float vectorBC[1][3]={(vertices[caras[i]._2].x)-(vertices[caras[i]._1].x),(vertices[caras[i]._2].y)-(vertices[caras[i]._1].y),(vertices[caras[i]._2].z)-(vertices[caras[i]._1].z)};
		//Calculamos el producto vectorial de los dos vectores obtenidos vectorAB x vectorBC
		float normalC[1][3]={
			{(vectorAB[0][1]*vectorBC[0][2])-(vectorAB[0][2]*vectorBC[0][1]),(vectorAB[0][2]*vectorBC[0][0])-(vectorAB[0][0]*vectorBC[0][2]),(vectorAB[0][0]*vectorBC[0][1])-(vectorAB[0][1]*vectorBC[0][0])}
		};

		modulo = sqrt (((normalC[0][0])*(normalC[0][0]))+((normalC[0][1])*(normalC[0][1]))+((normalC[0][2])*(normalC[0][2])));
		normalCara.x = (normalC[0][0]/modulo);
		normalCara.y = (normalC[0][1]/modulo);
		normalCara.z = (normalC[0][2]/modulo);
		normales_caras.push_back(normalCara);
	}
	if (DEBUG_MODE) {
		printf("%s %lu\n", "Tamaño vector normales_vertices: triangulos3D.hxx->calcular_normales_caras->2", this->normales_vertices.size());
		printf("%s %lu\n", "Tamaño vector normales_caras: triangulos3D.hxx->calcular_normales_caras->3", this->normales_caras.size());
		printf("%s %lu\n", "Tamaño vector vertices: triangulos3D.hxx->calcular_normales_caras->4", this->vertices.size());
		printf("%s %lu\n", "Tamaño vector caras: triangulos3D.hxx->calcular_normales_caras->5", this->caras.size());
		printf("%s\n", "Final de la función de calculo triangulos3D.hxx->calcular_normales_caras->6");
	}
}

//*************************************************************************
// Función que nos permite elegir que tipo de material queremos en nuestro objeto
// @param m tipo _material podemos pasarle el tipo de material que queremos usar
//*************************************************************************
void _triangulos3D::set_material(_material m){
	this->el_material = m;
}

//*************************************************************************
// Función que nos permite elegir que tipo de material queremos en nuestro objeto
// @param m tipo int poder elegir uno de los materiales ya predefinidos:
//    PLATA, ORO, ALUMINIO.
//*************************************************************************
void _triangulos3D::set_material(int m){
	this->el_material.selecionar_material(m);
}
