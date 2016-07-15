package aplicacionaula;

import java.util.ArrayList;

public class AplicacionAula {

    public static void main(String[] args) {
        Profesor jesus, joaquin;
        Alumno juan, beatriz;
        Persona per;
        ArrayList<Delegado> delegados;

        jesus = new Profesor("Jesus");
        joaquin = new Profesor("Joaquin", "B7");
        juan = new Alumno("Juan");
        beatriz = new Alumno("Beatriz", 4, 5);
        per = new Persona();
        delegados = new ArrayList<Delegado>();

        per.altura = 3.3F;
        System.out.println("\n" + jesus.toString());   // Mostramos a los profesores
        System.out.println(joaquin.toString());
        jesus.setDespacho("C7");                     // Cambiamos el despacho de Jesus
        System.out.println(jesus.toString() + "\n");   // Comprobamos la nueva informacion

        jesus.darClase();                               // Jesus comineza la clase
        jesus.habla();
        juan.pregunta("¿Que es un objeto?");             // El alumno juan pregunta
        jesus.responderPregunta("¿Que es un objeto?");   // Jesus contesta
        beatriz.pregunta("¿Es lo mismo clase y objeto?");
        jesus.responderPregunta("¿Es lo mismo clase y objeto?");
        jesus.pregunta("¿Os habeis enterado?");

        for (int i = 0; i < 10; i++) {
            delegados.add(new Delegado());
        }

        System.out.println(delegados.toString() + "\n");
        double x = -3.7d;
        System.out.println("Valor Absoluto de -3,7 = " + Math.abs(x));
        System.out.println("Valor Absoluto de 3,7 = " + Math.abs(3.7));
        System.out.println("Raiz Cuadrada de 37 = " + Math.sqrt(37));

        char aux = 'B';
        if (Character.isLowerCase(aux)) {
            System.out.println("La letra " + aux + " es minúscula");
        } else {
            System.out.println("La letra " + aux + " es mayúscula");
        }

        int entero = 5;
        double doble = 5.5;
        String letra = "";
        tester myTesterVar = new tester(entero);
        System.out.println(entero);
        System.out.println(letra);
        System.out.println("Tipo de dato del 5 antes de la conversión: " + myTesterVar.getType());
        System.out.println("Es un entero: " + myTesterVar.isInt());
        letra = "" + entero;
        entero = 0;
        myTesterVar.setVarToTest(letra);
        System.out.println(entero);
        System.out.println(letra);
        System.out.println("Tipo de dato del 5 después de la conversión: " + myTesterVar.getType());
        System.out.println("Es un entero: " + myTesterVar.isInt());
        letra += 1;
        entero = Integer.parseInt(letra);
        myTesterVar.setVarToTest(entero);
        System.out.println(entero);
        System.out.println(letra);
        System.out.println("Tipo de dato del 5 después de la conversión: " + myTesterVar.getType());
        System.out.println("Es un entero: " + myTesterVar.isInt());
        myTesterVar.setVarToTest(doble);
        System.out.println(doble);
        System.out.println(letra);
        System.out.println("Tipo de dato del 5.5 antes de la conversión: " + myTesterVar.getType());
        System.out.println("Es un doble: " + myTesterVar.isDouble());
        letra = "" + doble;
        doble = Double.MAX_VALUE;
        myTesterVar.setVarToTest(letra);
        System.out.println(doble);
        System.out.println(letra);
        System.out.println("Tipo de dato del 5.5 antes de la conversión: " + myTesterVar.getType());
        System.out.println("Es un doble: " + myTesterVar.isDouble());
        System.out.println("hola y Hola, Son iguales si no tenemos en cuenta las Mayúsculas " + "hola".compareToIgnoreCase("Hola"));
        System.out.println("hola y Hola, Son diferentes si tenemos en cuenta las Mayúsculas " + "hola".compareTo("Hola"));
        String saludo = "Hola";
        System.out.println(saludo.substring(1, 2));
        System.out.println(saludo);
        saludo += " y adios";
        System.out.println(saludo);
    }

}
