//
//*************************************************************************
// Constructor por defecto de Triangulos3D
//*************************************************************************
_triangulos3D::_triangulos3D(){

}

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
	const int numero_caras = caras.size();

	glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

	glBegin(GL_TRIANGLES);
	glShadeModel( GL_SMOOTH);
	for (int j = 0; j < numero_caras; j++) {
		glColor3f(colores.at(caras.at(j)._0).x,colores.at(caras.at(j)._0).y,colores.at(caras.at(j)._0).z);
		glVertex3f(vertices.at(caras.at(j)._0).x, vertices.at(caras.at(j)._0).y, vertices.at(caras.at(j)._0).z);
		glColor3f(colores.at(caras.at(j)._1).x,colores.at(caras.at(j)._1).y,colores.at(caras.at(j)._1).z);
		glVertex3f(vertices.at(caras.at(j)._1).x, vertices.at(caras.at(j)._1).y, vertices.at(caras.at(j)._1).z);
		glColor3f(colores.at(caras.at(j)._2).x,colores.at(caras.at(j)._2).y,colores.at(caras.at(j)._2).z);
		glVertex3f(vertices.at(caras.at(j)._2).x, vertices.at(caras.at(j)._2).y, vertices.at(caras.at(j)._2).z);
	}
	glShadeModel( GL_FLAT);
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
	const int numero_caras = caras.size(); // para evitar el warning de comparación

	glClearDepth (1.0);
	glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glEnable(GL_DEPTH_TEST); // Activar el modo EPO

	if (!suavizado && this->normales_caras.size() == 0) {
		glShadeModel(GL_FLAT);
		this->calcular_normales_caras();
		if (DEBUG_MODE)
			printf("%s\n", "Entramos para calcular las normales de caras triangulos3D.hxx->draw_solido_luz->1");
	} else if (suavizado && this->normales_vertices.size() == 0) {
		glShadeModel(GL_SMOOTH);
		this->calcular_normales_vertices();
		if (DEBUG_MODE)
			printf("%s\n", "Entramos para calcular las normales de vertices triangulos3D.hxx->draw_solido_luz->2");
	}
	if (DEBUG_MODE){
		printf("%s %lu\n", "Tamaño vector normales_vertices: triangulos3D.hxx->draw_solido_luz->3", this->normales_vertices.size());
		printf("%s %lu\n", "Tamaño vector normales_caras: triangulos3D.hxx->draw_solido_luz->4", this->normales_caras.size());
		printf("%s %lu\n", "Tamaño vector vertices: triangulos3D.hxx->draw_solido_luz->5", this->vertices.size());
		printf("%s %lu\n", "Tamaño vector caras: triangulos3D.hxx->draw_solido_luz->6", this->caras.size());
	}
	glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT,this->material_ambiente);
	glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE,this->material_difusa);
	glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR,this->material_especular);
	glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS,this->brillo);
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
}

//*************************************************************************
// Función para calcular las direcciones normales de las caras para
// el calculo de brillos
//*************************************************************************
void _triangulos3D::calcular_normales_caras(){
	double modulo;
	_vertex3f normalCara;
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
}
