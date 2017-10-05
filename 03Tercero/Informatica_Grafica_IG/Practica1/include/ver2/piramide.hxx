// Dibujamos la Piramide
_piramide::_piramide(float tam, float al){
	// Reservamos memoria para los 16 vértices de la pirámide
	vertices.resize(16);
	// Construimos la base de la pirámide
	// Cara 0
	/*vertices[0].x = -tam;	vertices[0].y = tam;    vertices[0].z = -tam;
	   vertices[1].x = tam;	vertices[1].y = tam;    vertices[1].z = -tam;
	   vertices[2].x = -tam;	vertices[2].y = tam;    vertices[2].z = tam;

	   vertices[1].x = tam;	vertices[1].y = tam;    vertices[1].z = -tam;
	   vertices[3].x = tam;	vertices[3].y = tam;    vertices[3].z = tam;
	   vertices[2].x = -tam;	vertices[2].y = tam;    vertices[2].z = tam;

	   // Cara 1
	   vertices[2].x = -tam;	vertices[2].y = tam;    vertices[2].z = tam;
	   vertices[3].x = tam;	vertices[3].y = tam;    vertices[3].z = tam;
	   vertices[4].x = al;		vertices[4].y = al;     vertices[4].z = al;

	   // Cara 2
	   vertices[2].x = -tam;	vertices[2].y = tam;    vertices[2].z = tam;
	   vertices[4].x = al;		vertices[4].y = al;     vertices[4].z = al;
	   vertices[0].x = -tam;	vertices[0].y = tam;    vertices[0].z = -tam;

	   // Cara 3
	   vertices[0].x = -tam;	vertices[0].y = tam;    vertices[0].z = -tam;
	   vertices[4].x = al;		vertices[4].y = al;     vertices[4].z = al;
	   vertices[1].x = tam;	vertices[1].y = tam;    vertices[1].z = -tam;

	   // Cara 4
	   vertices[1].x = tam;	vertices[1].y = tam;    vertices[1].z = -tam;
	   vertices[4].x = al;		vertices[4].y = al;     vertices[4].z = al;
	   vertices[3].x = tam;	vertices[3].y = tam;    vertices[3].z = tam;*/

	vertices[0].x = 0;  vertices[0].y = tam;    vertices[0].z = 0;
	vertices[1].x = -tam;   vertices[1].y = -tam;   vertices[1].z = tam;
	vertices[2].x = tam;    vertices[2].y = -tam;   vertices[2].z = tam;
	vertices[3].x = tam;    vertices[3].y = -tam;   vertices[3].z = -tam;
	vertices[4].x = -tam;   vertices[4].y = -tam;   vertices[4].z = -tam;

	// modificamos el tamaño para los colores
	colores.resize(16);
	colores[0].x = (double) rand() / (RAND_MAX);   colores[0].y = (double) rand() / (RAND_MAX);   colores[0].z = (double) rand() / (RAND_MAX);
	colores[1].x = (double) rand() / (RAND_MAX);   colores[1].y = (double) rand() / (RAND_MAX);   colores[1].z = (double) rand() / (RAND_MAX);
	colores[2].x = (double) rand() / (RAND_MAX);   colores[2].y = (double) rand() / (RAND_MAX);   colores[2].z = (double) rand() / (RAND_MAX);
	colores[3].x = (double) rand() / (RAND_MAX);   colores[3].y = (double) rand() / (RAND_MAX);   colores[3].z = (double) rand() / (RAND_MAX);
	colores[4].x = (double) rand() / (RAND_MAX);   colores[4].y = (double) rand() / (RAND_MAX);   colores[4].z = (double) rand() / (RAND_MAX);
	colores[5].x = (double) rand() / (RAND_MAX);   colores[5].y = (double) rand() / (RAND_MAX);   colores[5].z = (double) rand() / (RAND_MAX);
	colores[6].x = (double) rand() / (RAND_MAX);   colores[6].y = (double) rand() / (RAND_MAX);   colores[6].z = (double) rand() / (RAND_MAX);
	colores[7].x = (double) rand() / (RAND_MAX);   colores[7].y = (double) rand() / (RAND_MAX);   colores[7].z = (double) rand() / (RAND_MAX);
	colores[8].x = (double) rand() / (RAND_MAX);   colores[8].y = (double) rand() / (RAND_MAX);   colores[8].z = (double) rand() / (RAND_MAX);
	colores[9].x = (double) rand() / (RAND_MAX);   colores[9].y = (double) rand() / (RAND_MAX);   colores[9].z = (double) rand() / (RAND_MAX);
	colores[10].x = (double) rand() / (RAND_MAX);   colores[10].y = (double) rand() / (RAND_MAX);   colores[10].z = (double) rand() / (RAND_MAX);
	colores[11].x = (double) rand() / (RAND_MAX);   colores[11].y = (double) rand() / (RAND_MAX);   colores[11].z = (double) rand() / (RAND_MAX);
	colores[12].x = (double) rand() / (RAND_MAX);   colores[12].y = (double) rand() / (RAND_MAX);   colores[12].z = (double) rand() / (RAND_MAX);
	colores[13].x = (double) rand() / (RAND_MAX);   colores[13].y = (double) rand() / (RAND_MAX);   colores[13].z = (double) rand() / (RAND_MAX);
	colores[14].x = (double) rand() / (RAND_MAX);   colores[14].y = (double) rand() / (RAND_MAX);   colores[14].z = (double) rand() / (RAND_MAX);
	colores[15].x = (double) rand() / (RAND_MAX);   colores[15].y = (double) rand() / (RAND_MAX);   colores[15].z = (double) rand() / (RAND_MAX);

	// Resevamos memoria para los 6 triángulos de las 5 caras de la pirámide
	caras.resize(6);

	// Adjudico para cada cara los vértices correspondientes de cada triángulo
	// Cara 0
	/*caras[0]._0 = 0;	caras[0]._1 = 1;	caras[0]._2 = 2;
	   caras[1]._0 = 1;	caras[1]._1 = 3;	caras[1]._2 = 2;

	   // Cara 1
	   caras[2]._0 = 3;	caras[2]._1 = 4;	caras[2]._2 = 2;

	   // Cara 2
	   caras[3]._0 = 2;	caras[3]._1 = 4;	caras[3]._2 = 0;

	   // Cara 3
	   caras[4]._0 = 0;	caras[4]._1 = 4;	caras[4]._2 = 1;

	   // Cara 4
	   caras[5]._0 = 1;	caras[5]._1 = 4;	caras[5]._2 = 3;*/


	caras[0]._0 = 2;    caras[0]._1 = 0;    caras[0]._2 = 1;
	caras[1]._0 = 3;    caras[1]._1 = 2;    caras[1]._2 = 1;
	caras[2]._0 = 3;    caras[2]._1 = 1;    caras[2]._2 = 4;
	caras[3]._0 = 3;    caras[3]._1 = 0;    caras[3]._2 = 2;
	caras[4]._0 = 4;    caras[4]._1 = 0;    caras[4]._2 = 3;
	caras[5]._0 = 1;    caras[5]._1 = 0;    caras[5]._2 = 4;
}
