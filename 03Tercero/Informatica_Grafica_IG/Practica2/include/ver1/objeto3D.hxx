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
	_vertex3f aux1;

	if (mi_ply.open(archivo) == 0)  // Si se ha abierto correctamente
		if (mi_ply.read(vertices_aux, caras_aux) == 0) // Si se ha leído correctamente los vértices y las caras
			mi_ply.close(); // Cerramos el fichero

	size_vertex = vertices_aux.size() / 3;
	for (int i = 0; i < size_vertex; i++) {
		aux1.x = vertices_aux[3*i];
		aux1.y = vertices_aux[3*i+1];
		aux1.z = vertices_aux[3*i+2];
		vertices.push_back(aux1);
	}

	size_face = caras_aux.size() / 3;
	caras.resize(size_face);
	for (int i = 0; i < size_face; i++) {
		caras[i].x = caras_aux[3*i];
		caras[i].y = caras_aux[3*i+1];
		caras[i].z = caras_aux[3*i+2];
	}
}

//******************************************************************************
// Volcado de los datos de memoria en un fichero
//******************************************************************************

void _objeto3D::escribir_objeto(char *archivo){

}

//******************************************************************************
// Creación de un objeto por el metodo de revolución
//******************************************************************************

void _objeto3D::revolucion(){

}

//******************************************************************************
// Creación del objeto por el método de extrución
//******************************************************************************

void _objeto3D::extrucion(){

}
