// Constructor por defecto de Triangulos3D
_triangulos3D::_triangulos3D(){

}

// Función para dibujar aristas
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

// Función para colorear el sólido
void _triangulos3D::draw_solido(float r, float g, float b){
	glColor3f(r, g, b);     // Le asignamos un color al sólido

	const int numero_caras = caras.size();
	//int numero_vertices;

	glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	//glPolygonMode(GL_FRONT_AND_BACK, GL_LINES);

	glBegin(GL_TRIANGLES);
	for (int j = 0; j < numero_caras; j++) {
		glVertex3f(vertices.at(caras.at(j)._0).x, vertices.at(caras.at(j)._0).y, vertices.at(caras.at(j)._0).z);
		glVertex3f(vertices.at(caras.at(j)._1).x, vertices.at(caras.at(j)._1).y, vertices.at(caras.at(j)._1).z);
		glVertex3f(vertices.at(caras.at(j)._2).x, vertices.at(caras.at(j)._2).y, vertices.at(caras.at(j)._2).z);
	}
	glEnd();
}

// Función para colorear las caras pares de un color y las impares de otro
void _triangulos3D::draw_solido_ajedrez(float r1, float g1, float b1, float r2, float g2, float b2){
	const int numero_caras = caras.size();
	//int numero_vertices;

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
