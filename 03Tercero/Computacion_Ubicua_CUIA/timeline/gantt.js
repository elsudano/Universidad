var w = 1600;
var h = 800;
var gantt_title = "TimeLine Interactivo del desarrolo de la Aplicación"
var data_url = "https://www.sudano.net/cloud/index.php/s/tn4gTK3MUEyRKmv/download"
var color_ini = "#99FF36"
var color_fin = "#9E00B2"
//var color_ini = "#00B9FA"
//var color_fin = "#F95002"

d3.csv(data_url).row(
	function(d) {
		return {
			fase: d.fase,
			tarea: d.tarea,
			inicio: d.inicio,
			fin: d.fin,
			completado: +d.completado,
			detalles: d.detalles
		};
	}
).get(
	function(error, datos) {
		console.log(datos);

		var svg = d3.selectAll(".svg")
		//.selectAll("svg")
		.append("svg")
		.attr("width", w)
		.attr("height", h)
		.attr("class", "svg");

		var dateFormat = d3.time.format("%d/%m/%Y");

		var timeScale = d3.time.scale().domain([
			d3.min(datos, function(d) {return dateFormat.parse(d.inicio);}),
			d3.max(datos, function(d) {return dateFormat.parse(d.fin);})
		]).range([0,w-150]);

		var categorias = new Array();

		for (var i = 0; i < datos.length; i++){
			categorias.push(datos[i].fase);
		}

		var catsUnfiltered = categorias; //for vert labels

		categorias = checkUnique(categorias);


		makeGant(datos, w, h);

		var title = svg.append("text")
		.text(gantt_title)
		.attr("x", w/2)
		.attr("y", 25)
		.attr("text-anchor", "middle")
		.attr("font-size", 18)
		.attr("fill", "#009FFC");

		function makeGant(tareas, pageWidth, pageHeight){

			var barHeight = 20;
			var gap = barHeight + 4;
			var topPadding = 75;
			var sidePadding = 75;

			var colorScale = d3.scale.linear()
			.domain([0, categorias.length])
			.range([color_ini, color_fin])
			.interpolate(d3.interpolateHcl);

			makeGrid(sidePadding, topPadding, pageWidth, pageHeight);
			drawRects(tareas, gap, topPadding, sidePadding, barHeight, colorScale, pageWidth, pageHeight);
			vertLabels(gap, topPadding, sidePadding, barHeight, colorScale);

		}


		function drawRects(theArray, theGap, theTopPad, theSidePad, theBarHeight, theColorScale, w, h){

			var bigRects = svg.append("g")
			.selectAll("rect")
			.data(theArray)
			.enter()
			.append("rect")
			.attr("x", 0)
			.attr("y", function(d, i){
				return i*theGap + theTopPad - 2;
			})
			.attr("width", function(d){
				return w-theSidePad/2;
			})
			.attr("height", theGap)
			.attr("stroke", "none")
			.attr("fill", function(d){
				for (var i = 0; i < categorias.length; i++){
					if (d.fase == categorias[i]){
						return d3.rgb(theColorScale(i));
					}
				}
			})
			.attr("opacity", 0.2);


			var rectangles = svg.append('g')
			.selectAll("rect")
			.data(theArray)
			.enter();

			var shadowRects = rectangles.append("rect")
			.attr("rx", 3)
			.attr("ry", 3)
			.attr("x", function(d){
				return timeScale(dateFormat.parse(d.inicio)) + theSidePad;
			})
			.attr("y", function(d, i){
				return i*theGap + theTopPad;
			})
			.attr("width", function(d){
				return (timeScale(dateFormat.parse(d.fin))-timeScale(dateFormat.parse(d.inicio)));
			})
			.attr("height", theBarHeight)
			.attr("stroke", "none")
			.attr("fill", "#9f9fa3")

			var innerRects = rectangles.append("rect")
			.attr("rx", 3)
			.attr("ry", 3)
			.attr("x", function(d){
				return timeScale(dateFormat.parse(d.inicio)) + theSidePad;
			})
			.attr("y", function(d, i){
				return i*theGap + theTopPad;
			})
			.attr("width", function(d){
				// aqui poner lo del porcentaje para rellenar el color del rectángulo
				return ((timeScale(dateFormat.parse(d.fin))-timeScale(dateFormat.parse(d.inicio)))*d.completado)/100;
			})
			.attr("height", theBarHeight)
			.attr("stroke", "none")
			.attr("fill", function(d){
				for (var i = 0; i < categorias.length; i++){
					if (d.fase == categorias[i]){
						return d3.rgb(theColorScale(i));
					}
				}
			})

			var rectText = rectangles.append("text")
			// .text(function(d){
			// 	return d.tarea;
			// })
			.text(function(d){
				return d.completado + "%";
			})
			.attr("x", function(d){
				return (timeScale(dateFormat.parse(d.fin))-timeScale(dateFormat.parse(d.inicio)))/2 + timeScale(dateFormat.parse(d.inicio)) + theSidePad;
			})
			.attr("y", function(d, i){
				return i*theGap + 14+ theTopPad;
			})
			.attr("font-size", 11)
			.attr("text-anchor", "middle")
			.attr("text-height", theBarHeight)
			.attr("fill", "#fff");

			shadowRects.on('mouseover', function(e) {
				//console.log(this);
				var tag = "";

				if (d3.select(this).data()[0].detalles != undefined){
					tag = "Tarea: " + d3.select(this).data()[0].tarea + "<br/>" +
					"Fase: " + d3.select(this).data()[0].fase + "<br/>" +
					"Inicio: " + d3.select(this).data()[0].inicio + "<br/>" +
					"Fin: " + d3.select(this).data()[0].fin + "<br/>" +
					"Completado: " + d3.select(this).data()[0].completado + "% <br/>" +
					"Detalles: " + d3.select(this).data()[0].detalles;
				} else {
					tag = "Tarea: " + d3.select(this).data()[0].tarea + "<br/>" +
					"Fase: " + d3.select(this).data()[0].fase + "<br/>" +
					"Inicio: " + d3.select(this).data()[0].inicio + "<br/>" +
					"Fin: " + d3.select(this).data()[0].fin + "<br/>" +
					"Completado: " + d3.select(this).data()[0].completado + "%";
				}
				var output = document.getElementById("tag");

				var x = (this.x.animVal.value + this.width.animVal.value/2) + "px";
				var y = this.y.animVal.value + 25 + "px";

				output.innerHTML = tag;
				output.style.top = y;
				output.style.left = x;
				output.style.display = "block";
			}).on('mouseout', function() {
				var output = document.getElementById("tag");
				output.style.display = "none";

			});

			innerRects.on('mouseover', function(e) {
				//console.log(this);
				var tag = "";

				if (d3.select(this).data()[0].detalles != undefined){
					tag = "Tarea: " + d3.select(this).data()[0].tarea + "<br/>" +
					"Fase: " + d3.select(this).data()[0].fase + "<br/>" +
					"Inicio: " + d3.select(this).data()[0].inicio + "<br/>" +
					"Fin: " + d3.select(this).data()[0].fin + "<br/>" +
					"Completado: " + d3.select(this).data()[0].completado + "% <br/>" +
					"Detalles: " + d3.select(this).data()[0].detalles;
				} else {
					tag = "Tarea: " + d3.select(this).data()[0].tarea + "<br/>" +
					"Fase: " + d3.select(this).data()[0].fase + "<br/>" +
					"Inicio: " + d3.select(this).data()[0].inicio + "<br/>" +
					"Fin: " + d3.select(this).data()[0].fin + "<br/>" +
					"Completado: " + d3.select(this).data()[0].completado + "%";
				}
				var output = document.getElementById("tag");

				var x = (this.x.animVal.value + this.width.animVal.value/2) + "px";
				var y = this.y.animVal.value + 25 + "px";

				output.innerHTML = tag;
				output.style.top = y;
				output.style.left = x;
				output.style.display = "block";
			}).on('mouseout', function() {
				var output = document.getElementById("tag");
				output.style.display = "none";

			});

			// rectText.on('mouseover', function(e) {
			// 	// console.log(this.x.animVal.getItem(this));
			// 	var tag = "";
			//
			// 	if (d3.select(this).data()[0].detalles != undefined){
			// 		tag = "Tarea: " + d3.select(this).data()[0].tarea + "<br/>" +
			// 		"Fase: " + d3.select(this).data()[0].fase + "<br/>" +
			// 		"Inicio: " + d3.select(this).data()[0].inicio + "<br/>" +
			// 		"Fin: " + d3.select(this).data()[0].fin + "<br/>" +
			// 		"Completado: " + d3.select(this).data()[0].completado + "% <br/>" +
			// 		"Detalles: " + d3.select(this).data()[0].detalles;
			// 	} else {
			// 		tag = "Tarea: " + d3.select(this).data()[0].tarea + "<br/>" +
			// 		"Fase: " + d3.select(this).data()[0].fase + "<br/>" +
			// 		"Inicio: " + d3.select(this).data()[0].inicio + "<br/>" +
			// 		"Fin: " + d3.select(this).data()[0].fin + "<br/>" +
			// 		"Completado: " + d3.select(this).data()[0].completado + "%";
			// 	}
			// 	var output = document.getElementById("tag");
			//
			// 	var x = this.x.animVal.getItem(this) + "px";
			// 	var y = this.y.animVal.getItem(this) + 25 + "px";
			//
			// 	output.innerHTML = tag;
			// 	output.style.top = y;
			// 	output.style.left = x;
			// 	output.style.display = "block";
			// }).on('mouseout', function() {
			// 	var output = document.getElementById("tag");
			// 	output.style.display = "none";
			// });
		}

		function makeGrid(theSidePad, theTopPad, w, h){

			var xAxis = d3.svg.axis()
			.scale(timeScale)
			.orient('bottom')
			.ticks(d3.time.days, 3)
			.tickSize(-h+theTopPad+20, 0, 0)
			.tickFormat(d3.time.format('%d/%m'));

			var grid = svg.append('g')
			.attr('class', 'grid')
			.attr('transform', 'translate(' +theSidePad + ', ' + (h - 50) + ')')
			.call(xAxis)
			.selectAll("text")
			.style("text-anchor", "middle")
			.attr("fill", "#000")
			.attr("stroke", "none")
			.attr("font-size", 10)
			.attr("dy", "1em");
		}

		function vertLabels(theGap, theTopPad, theSidePad, theBarHeight, theColorScale){
			var numOccurances = new Array();
			var prevGap = 0;

			for (var i = 0; i < categorias.length; i++){
				numOccurances[i] = [categorias[i], getCount(categorias[i], catsUnfiltered)];
			}

			var axisText = svg.append("g") //without doing this, impossible to put grid lines behind text
			.selectAll("text")
			.data(numOccurances)
			.enter()
			.append("text")
			.text(function(d){
				return d[0];
			})
			.attr("x", 10)
			.attr("y", function(d, i){
				if (i > 0){
					for (var j = 0; j < i; j++){
						prevGap += numOccurances[i-1][1];
						// console.log(prevGap);
						return d[1]*theGap/2 + prevGap*theGap + theTopPad;
					}
				} else{
					return d[1]*theGap/2 + theTopPad;
				}
			})
			.attr("font-size", 11)
			.attr("text-anchor", "start")
			.attr("text-height", 14)
			.attr("fill", function(d){
				for (var i = 0; i < categorias.length; i++){
					if (d[0] == categorias[i]){
						//  console.log("true!");
						return d3.rgb(theColorScale(i)).darker();
					}
				}
			});

		}

		//from this stackexchange question: http://stackoverflow.com/questions/1890203/unique-for-arrays-in-javascript
		function checkUnique(arr) {
			var hash = {}, result = [];
			for ( var i = 0, l = arr.length; i < l; ++i ) {
				if ( !hash.hasOwnProperty(arr[i]) ) { //it works with objects! in FF, at least
					hash[ arr[i] ] = true;
					result.push(arr[i]);
				}
			}
			return result;
		}

		//from this stackexchange question: http://stackoverflow.com/questions/14227981/count-how-many-strings-in-an-array-have-duplicates-in-the-same-array
		function getCounts(arr) {
			var i = arr.length, // var to loop over
			obj = {}; // obj to store results
			while (i) obj[arr[--i]] = (obj[arr[i]] || 0) + 1; // count occurrences
			return obj;
		}

		// get specific from everything
		function getCount(word, arr) {
			return getCounts(arr)[word] || 0;
		}
	}
);
