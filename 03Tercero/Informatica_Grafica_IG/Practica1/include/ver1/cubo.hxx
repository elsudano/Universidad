// Dibujamos el _cubo
_cubo::_cubo(float tam){
	// Reservamos memoria para los 8 vértices del cubo
	vertices.resize(8);
	// Pintamos los vértices de cada cara del triángulo del cubo
	// Cara 0 primer triángulo
	vertices[0].x = -tam;   vertices[0].y = -tam;   vertices[0].z = tam;
	vertices[1].x = tam;    vertices[1].y = -tam;   vertices[1].z = tam;
	vertices[2].x = -tam;   vertices[2].y = tam;    vertices[2].z = tam;
	// Cara 0 segundo triángulo
	vertices[1].x = tam;    vertices[1].y = -tam;   vertices[1].z = tam;
	vertices[3].x = tam;    vertices[3].y = tam;    vertices[3].z = tam;
	vertices[2].x = -tam;   vertices[2].y = tam;    vertices[2].z = tam;
	// Cara 1 primer triángulo
	vertices[1].x = tam;    vertices[1].y = -tam;   vertices[1].z = tam;
	vertices[5].x = tam;    vertices[5].y = -tam;   vertices[5].z = -tam;
	vertices[3].x = tam;    vertices[3].y = tam;    vertices[3].z = tam;
	// Cara 1 segundo triángulo
	vertices[5].x = tam;    vertices[5].y = -tam;   vertices[5].z = -tam;
	vertices[7].x = tam;    vertices[7].y = tam;    vertices[7].z = -tam;
	vertices[3].x = tam;    vertices[3].y = tam;    vertices[3].z = tam;
	// Cara 2 primer triángulo
	vertices[3].x = tam;    vertices[3].y = tam;    vertices[3].z = tam;
	vertices[7].x = tam;    vertices[7].y = tam;    vertices[7].z = -tam;
	vertices[6].x = -tam;   vertices[6].y = tam;    vertices[6].z = -tam;
	// Cara 2 segundo triángulo
	vertices[3].x = tam;    vertices[3].y = tam;    vertices[3].z = tam;
	vertices[6].x = -tam;   vertices[6].y = tam;    vertices[6].z = -tam;
	vertices[2].x = -tam;   vertices[2].y = tam;    vertices[2].z = tam;
	// Cara 3 primer triángulo
	vertices[4].x = -tam;   vertices[4].y = -tam;   vertices[4].z = -tam;
	vertices[6].x = -tam;   vertices[6].y = tam;    vertices[6].z = -tam;
	vertices[7].x = tam;    vertices[7].y = tam;    vertices[7].z = -tam;
	// Cara 3 segundo triángulo
	vertices[4].x = -tam;   vertices[4].y = -tam;   vertices[4].z = -tam;
	vertices[7].x = tam;    vertices[7].y = tam;    vertices[7].z = -tam;
	vertices[5].x = tam;    vertices[5].y = -tam;   vertices[5].z = -tam;
	// Cara 4 primer triángulo
	vertices[4].x = -tam;   vertices[4].y = -tam;   vertices[4].z = -tam;
	vertices[5].x = tam;    vertices[5].y = -tam;   vertices[5].z = -tam;
	vertices[0].x = -tam;   vertices[0].y = -tam;   vertices[0].z = tam;
	// Cara 4 segundo triángulo
	vertices[5].x = tam;    vertices[5].y = -tam;   vertices[5].z = -tam;
	vertices[1].x = tam;    vertices[1].y = -tam;   vertices[1].z = tam;
	vertices[0].x = -tam;   vertices[0].y = -tam;   vertices[0].z = tam;
	// Cara 5 primer triángulo
	vertices[0].x = -tam;   vertices[0].y = -tam;   vertices[0].z = tam;
	vertices[6].x = -tam;   vertices[6].y = tam;    vertices[6].z = -tam;
	vertices[4].x = -tam;   vertices[4].y = -tam;   vertices[4].z = -tam;
	// Cara 5 segundo triángulo
	vertices[0].x = -tam;   vertices[0].y = -tam;   vertices[0].z = tam;
	vertices[2].x = -tam;   vertices[2].y = tam;    vertices[2].z = tam;
	vertices[6].x = -tam;   vertices[6].y = tam;    vertices[6].z = -tam;

	// Resevamos memoria para los 12 triángulos de las 6 caras del cubo
	caras.resize(12);
	// Adjudico para cada cara los vértices correspondientes de cada triángulo
	// Cara 0
	caras[0]._0 = 2;    caras[0]._1 = 0;    caras[0]._2 = 1;
	caras[1]._0 = 1;    caras[1]._1 = 3;    caras[1]._2 = 2;
	// Cara 1
	caras[2]._0 = 1;    caras[2]._1 = 5;    caras[2]._2 = 3;
	caras[3]._0 = 5;    caras[3]._1 = 7;    caras[3]._2 = 3;
	// Cara 2
	caras[4]._0 = 2;    caras[4]._1 = 3;    caras[4]._2 = 6;
	caras[5]._0 = 3;    caras[5]._1 = 7;    caras[5]._2 = 6;
	// Cara 3
	caras[6]._0 = 5;    caras[6]._1 = 4;    caras[6]._2 = 7;
	caras[7]._0 = 4;    caras[7]._1 = 6;    caras[7]._2 = 7;
	// Cara 4
	caras[8]._0 = 5;    caras[8]._1 = 1;    caras[8]._2 = 0;
	caras[9]._0 = 0;    caras[9]._1 = 4;    caras[9]._2 = 5;
	// Cara 5
	caras[10]._0 = 0;   caras[10]._1 = 6;   caras[10]._2 = 4;
	caras[11]._0 = 0;   caras[11]._1 = 2;   caras[11]._2 = 6;
}
