
//******************************************************************************
// Creación de un objeto por el método de revolución
//******************************************************************************

_revolucion::_revolucion(){

}

//******************************************************************************
//Lectura de un fichero para generar un objeto por revolucion
//@param El nombre del fichero que tenemos que dibujar
//******************************************************************************
void _revolucion::leer_objeto(char *archivo){
	_file_ply mi_ply;
	int size_vertex;
	vector<float> vertices_aux;
	vector<int> caras_aux;
	_vertex3f punto_aux;

	if (mi_ply.open(archivo) == 0) // Si se ha abierto correctamente
		if (mi_ply.read(vertices_aux, caras_aux) == 0) // Si se ha leído correctamente los vértices y las caras
			mi_ply.close(); // Cerramos el fichero

	if (DEBUG_MODE)
		printf("%s\n", "Se cierra el fichero, revolucion.hxx->leer_objeto->1");

	size_vertex = vertices_aux.size() / 3;
	for (int i = 0; i < size_vertex; i++) {
		punto_aux.x = vertices_aux[3 * i];
		punto_aux.y = vertices_aux[3 * i + 1];
		punto_aux.z = vertices_aux[3 * i + 2];
		puntos_perfil.push_back(punto_aux);
	}
	if (DEBUG_MODE)
		printf("%s\n", "Se carga los datos del fichero en los vertices, revolucion.hxx->leer_objeto->2");
}

//******************************************************************************
// Comprobamos si el objeto tiene puntos asignados a los diferentes vectores
// @note: sobrescribimos el metodo de objeto3D por que en la revolucion no
// tenemos el vector de caras
//******************************************************************************
bool _revolucion::in_use(){
	bool aux = false;
	if (vertices.size() > 0 || puntos_perfil.size() > 0)
		aux = true;
	return aux;
}

//******************************************************************************
// Creación de un objeto por revolución con los parámetros minimos
// Prámetros de entrada:
// @param cv: son la cantidad de veces que queremos revolucionar el perfil
// @param po: son los puntos de origen que componen el perfil por el cual se
// contruira el objeto
// @note: Las caras se forman con 3 vertices de esta forma se ponen las
// caras para la revolución del objeto
//******************************************************************************
void _revolucion::caras_objeto(int cv, int po){
	const int cv_aux = cv - 1;
	_vertex3i punto_aux;
	if (DEBUG_MODE)
		printf("%s\n", "generamos las variables, revolucion.hxx->caras_objeto->1");
	for (int i = 0; i < cv_aux; ++i) {
		for (int j = 0; j < po - 1; ++j) {
			// generamos el primer punto del triangulo (cara)
			punto_aux.x = i * po + j;
			punto_aux.y = (i + 1) * po + j;
			punto_aux.z = i * po + (j + 1);
			caras.push_back( punto_aux);

			// generamos el segundo punto del triangulo (cara)
			punto_aux.x = i * po + (j + 1);
			punto_aux.y = (i +1) * po + j;
			punto_aux.z = (i + 1) * po + (j + 1);
			caras.push_back( punto_aux );
		}
	}
	if (DEBUG_MODE)
		printf("%s\n", "Generamos los puntos de los triangulos que haran de caras, revolucion.hxx->caras_objeto->2");
	//Union del ultimo punto del perfil con el primero
	for (int k = 0; k < po - 1; ++k) {
		// generamos el primer punto del triangulo (de la ultima cara)
		punto_aux.x = (cv_aux) *po + k;
		punto_aux.y = k;
		punto_aux.z = (cv_aux) *po + (k + 1);
		caras.push_back( punto_aux );

		// generamos el segundo punto del triangulo (de la ultima cara)
		punto_aux.x = (cv_aux) *po + (k + 1);
		punto_aux.y = k;
		punto_aux.z = k + 1;
		caras.push_back( punto_aux );
	}
	if (DEBUG_MODE)
		printf("%s\n", "Unimos el ultimo punto con el primero, revolucion.hxx->caras_objeto->3");
}


//******************************************************************************
// Generación de las tapas superior he inferior del objeto
// Prámetros de entrada:
// @param cv: son la cantidad de veces que queremos revolucionar el perfil
// @param po: son los puntos de origen que componen el perfil por el cual se
// contruira el objeto
// @note: Las caras se forman con 3 vertices de esta forma se ponen las
// caras para la revolución del objeto, por lo tanto para las tapas se pone un
// punto central y todos los triangulos tienen en comun ese punto central
//******************************************************************************
void _revolucion::tapas_objeto(int cv, int po){
	_vertex3f punto_3D;
	_vertex3i punto_revolucion;
	int punto_central_tapa;
	int punto_x;
	int punto_y;
	if (DEBUG_MODE)
		printf("%s\n", "Generamos las variables, revolucion.hxx->tapas_objeto->1");
	// Punto de abajo
	punto_3D.x = 0.0;
	punto_3D.y = vertices.at( po - 1).y;
	punto_3D.z = 0.0;
	vertices.push_back( punto_3D );
	if (DEBUG_MODE)
		printf("%s\n", "Generamos el punto de abajo, revolucion.hxx->tapas_objeto->2");
	// Punto de arriba
	punto_3D.x = 0.0;
	punto_3D.y = vertices.at(0).y;
	punto_3D.z = 0.0;
	vertices.push_back( punto_3D );
	if (DEBUG_MODE)
		printf("%s\n", "Generamos el punto de arriba, revolucion.hxx->tapas_objeto->3");
	punto_central_tapa = vertices.size() - 2;
	punto_x = cv - 1;
	punto_y = punto_x + cv;
	if (DEBUG_MODE)
		printf("%s\n", "Ponemos los primeros puntos para las caras de la tapa, revolucion.hxx->tapas_objeto->4");
	for(int i = 0; i < cv - 1; ++i) {
		punto_revolucion.x = punto_central_tapa;
		punto_revolucion.y = punto_x;
		punto_revolucion.z = punto_y;
		caras.push_back( punto_revolucion );
		punto_x  = punto_y;
		punto_y += cv;
	}
	if (DEBUG_MODE)
		printf("%s\n", "Ponemos los primeros puntos para las caras de la tapa, revolucion.hxx->tapas_objeto->5");
	punto_revolucion.x = punto_central_tapa;
	punto_revolucion.y = punto_x;
	punto_revolucion.z = cv - 1;
	caras.push_back( punto_revolucion );

	punto_central_tapa++;
	punto_x = cv;
	punto_y = punto_x - cv;

	for(int i = 0; i < cv - 1; ++i) {
		punto_revolucion.x = punto_central_tapa;
		punto_revolucion.y = punto_x;
		punto_revolucion.z = punto_y;
		caras.push_back( punto_revolucion );

		punto_y += cv;
		punto_x += cv;
	}
	if (DEBUG_MODE)
		printf("%s\n", "Ponemos los ultimos puntos para las caras de la tapa, revolucion.hxx->tapas_objeto->6");
	punto_revolucion.x = punto_central_tapa;
	punto_revolucion.y = 0;
	punto_revolucion.z = punto_x - cv;
	caras.push_back( punto_revolucion );
}

//******************************************************************************
// Generación de un objeto por revolución
// Prámetros de entrada:
// @param repe: son la cantidad de caras que queremos representar en el objeto
// @param con_tapas: ponemos el objeto con tapas o sin ellas
//******************************************************************************
void _revolucion::revolucion(int repe, bool con_tapas) {
	float punto_x = 0.0;
	float punto_y = 0.0;
	float punto_z = 0.0;
	float angulo_eje_x = 0.0;
	float angulo_eje_z = 0.0;

	_vertex3f punto_3D;
	float incremento_angulo_rotacion = 0.0;
	float angulo_rotacion = (2.0 * M_PI) / (repe*1.0);

	if (DEBUG_MODE)
		printf("%s\n", "Se generan las variables necesarias, revolucion.hxx->revolucion->1");

	for (int i = 0; i < repe; ++i) {
		// con este for recorremos todas la repeticiones que queremos que tenga nuestro objeto
		for (unsigned int j = 0; j < puntos_perfil.size(); ++j) {
			// con este for recorremos todos los puntos que hemos cargado desde el fichero
			punto_x = puntos_perfil.at(j).x;
			punto_y = puntos_perfil.at(j).y;
			punto_z = puntos_perfil.at(j).z;

			// Calculamos las nuevas posiciones de x y z;
			angulo_eje_x = ( punto_x * cos(incremento_angulo_rotacion) ) + ( punto_z * sin(incremento_angulo_rotacion) );
			angulo_eje_z = ( -punto_x * sin(incremento_angulo_rotacion) ) + ( punto_z * cos(incremento_angulo_rotacion) );

			// Añadimos al vector de coordenadas el punto
			punto_3D.x = angulo_eje_x;
			punto_3D.y = punto_y;
			punto_3D.z = angulo_eje_z;
			vertices.push_back(punto_3D);
		}
		incremento_angulo_rotacion += angulo_rotacion;
	}
	if (DEBUG_MODE)
		printf("%s\n", "Despues de realizar el recorrido con los for, revolucion.hxx->revolucion->2");

	if (con_tapas) {
		tapas_objeto(repe, puntos_perfil.size());
		if (DEBUG_MODE)
			printf("%s\n", "Despues de generar las tapas, revolucion.hxx->revolucion->3");
	}

	caras_objeto(repe, puntos_perfil.size());
	if (DEBUG_MODE)
		printf("%s\n", "Despues de generar las caras, revolucion.hxx->revolucion->4");
}

void _revolucion::print_puntos_perfil(){
	printf("%s %lu\n", "Cantidad de puntos del perfil:", puntos_perfil.size());
}
