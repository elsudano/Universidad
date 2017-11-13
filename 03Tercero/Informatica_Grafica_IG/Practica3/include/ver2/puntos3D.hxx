// Constructor por defecto de Punto3D
_puntos3D::_puntos3D(){}

// Función para pintar un punto en el plano
void _puntos3D::draw_puntos(float r, float g, float b, int grosor){
	glColor3f(r, g, b); // Le asignamos un color a un punto
	glPointSize(grosor);    // Asignación del grosor de punto

	// Dibujo cada punto con sus vértices correspondientes
	glBegin(GL_POINTS);
	const int TAMANIO_PUNTOS = vertices.size();
	for (int i = 0; i < TAMANIO_PUNTOS; i++)
		glVertex3f(vertices[i].x, vertices[i].y, vertices[i].z);
	glEnd();
}
