//******************************************************************************
// Constructor por defecto
//******************************************************************************
_objeto3D::_objeto3D(){

}

//******************************************************************************
// Lectura del fichero y carga de los datos en memoria
//******************************************************************************
void _objeto3D::leer_objeto(char *archivo){
	_file_ply mi_ply;
	int size_vertex, size_face;
	vector<float> vertices_aux;
	vector<int> caras_aux;
	_vertex3i cara_aux;
	_vertex3f punto_aux;

	if (mi_ply.open(archivo) == 0)  // Si se ha abierto correctamente
		if (mi_ply.read(vertices_aux, caras_aux) == 0) // Si se ha leído correctamente los vértices y las caras
			mi_ply.close(); // Cerramos el fichero

	if (DEBUG_MODE)
		printf("%s\n", "Se cierra el fichero, objeto3D.hxx->leer_objeto->1");
	// calculamos la cantidad de vertices que tenemos a partir
	// del tamaño del vector que hemos llenado con los datos leidos
	size_vertex = vertices_aux.size() / 3;
	// Incluimos los puntos leidos en la estructura de datos
	for (int i = 0; i < size_vertex; i++) {
		// generamos un punto con el valor correspondiente de las 3 coordenadas
		punto_aux.x = vertices_aux[3*i];
		punto_aux.y = vertices_aux[3*i+1];
		punto_aux.z = vertices_aux[3*i+2];
		// lo añadimos al vector de vertices
		vertices.push_back(punto_aux);
	}
	if (DEBUG_MODE)
		printf("%s\n", "Se carga los datos del fichero en los vertices, objeto3D.hxx->leer_objeto->2");
	// Igual que con los vertices calculamos la cantidad de caras a
	// partir de los datos que hemos leido del fichero
	size_face = caras_aux.size() / 3;
	// Incluimos las caras en el vector
	for (int i = 0; i < size_face; i++) {
		cara_aux.x = caras_aux[3*i];
		cara_aux.y = caras_aux[3*i+1];
		cara_aux.z = caras_aux[3*i+2];
		// lo añadimos al vector de vertices
		caras.push_back(cara_aux);
	}
	if (DEBUG_MODE)
		printf("%s\n", "Se carga los datos del fichero en las caras, objeto3D.hxx->leer_objeto->3");
}

//******************************************************************************
// Volcado de los datos de memoria en un fichero
//******************************************************************************
void _objeto3D::escribir_objeto(char *archivo){

}

//******************************************************************************
// Comprobamos si el objeto tiene puntos asignados a los diferentes vectores
//******************************************************************************
bool _objeto3D::in_use(){
	bool aux = false;
	if (vertices.size() > 0 || caras.size() > 0)
		aux = true;
	return aux;
}

//******************************************************************************
// Limpiamos el objeto para que se pueda volver a usar, y llenar los
// vectores de caras y vertices con datos nuevos
//******************************************************************************
void _objeto3D::clear(){
	vertices.clear();
	caras.clear();
}
