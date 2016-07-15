package aplicacionaula;

/**
 * @description Esta clase esta pensada para poder comprobar de que tipo son
 * las variables de nuestra aplicación, pero utilizando los tipos fundamentales
 * osea esto funcionara con:
 * 
 * byte, short, int, long, float, double, boolean, char
 * 
 * Aunque se puede hacer el código con un simple switch se realiza con la 
 * sobrecarga de los metódos por se mas eficiente ya que la cantidad de código
 * no es excesiva y sin embargo el tiempo de respuesta es mas rápido.
 * 
 * @author Carlos de al Torre
 */
public class tester {
    
    /**
     * Las variables que vienen a continuación serán las encargadas de almacenar
     * el dato que se pase al constructor cuando creamos el objeto tester.
     */
    private byte aux_byte;
    private short aux_short;
    private int aux_int;
    private long aux_long;
    private float aux_float;
    private double aux_double;
    private boolean aux_boolean;
    private char aux_char;
    private Object aux_others;
    
    /**
     * Esta es la variable que usamos para devolver el resultado.
     */
    private String type = "no type primitive";
    
    /**
     * Con esta variable mostraremos el mensaje de si una variable 
     * es del tipo correcto o no.
     */
    private boolean correct = false;

    /********************Constructores**********************/
    tester(byte x){
        this.aux_byte = x;
        this.type = "byte";
    }
    tester(short x){
        this.aux_short = x;
        this.type = "short";
    }
    tester(int x){
        this.aux_int = x;
        this.type = "int";
    }
    tester(long x){
        this.aux_long = x;
        this.type = "long";
    }
    tester(float x){
        this.aux_float = x;
        this.type = "float";
    }
    tester(double x){
        this.aux_double = x;
        this.type = "double";
    }
    tester(boolean x){
        this.aux_boolean = x;
        this.type = "boolean";
    }
    tester(char x){
        this.aux_char = x;
        this.type = "char";
    }
    tester(Object x){
        this.aux_others = x;
        this.type = x.getClass().getSimpleName();
    }
    /********************setType()**************************/
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(byte x){
        this.aux_byte = x;
        this.setInternalType("byte");
    }
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(short x){
        this.aux_short = x;
        this.setInternalType("short");
    }
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(int x){
        this.aux_int = x;
        this.setInternalType("int");
    }
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(long x){
        this.aux_long = x;
        this.setInternalType("long");
    }
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(float x){
        this.aux_float = x;
        this.setInternalType("float");
    }
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(double x){
        this.aux_double = x;
        this.setInternalType("double");
    }
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(boolean x){
        this.aux_boolean = x;
        this.setInternalType("boolean");
    }
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(char x){
        this.aux_char = x;
        this.setInternalType("char");
    }
    /**
     * Asigna el valor de x a la variable de clase que corresponda.
     * @param x el valor que queremos probar
     */
    public void setVarToTest(Object x){
        this.aux_others = x;
        this.setInternalType(x.getClass().getSimpleName());
    }
    /********************isType()**************************/
    /**
     * Devuelve true si es un byte y false en caso de que no lo sea.
     * @return devuelve true en caso de que sea byte y false en caso contrario.
     */
    public boolean isByte(){
        boolean aux = false;
        if (this.type.equals("byte"))
            aux = true;
        return aux;
    }
    /**
     * Devuelve true si es un short y false en caso de que no lo sea.
     * @return devuelve true en caso de que sea shot y false en caso contrario.
     */
    public boolean isShort(){
        boolean aux = false;
        if (this.type.equals("short"))
            aux = true;
        return aux;
    }
    /**
     * Devuelve true si es un entero y false en caso de que no lo sea.
     * @return devuelve true en caso de que sea int y false en caso contrario.
     */
    public boolean isInt(){
        boolean aux = false;
        if (this.type.equals("int"))
            aux = true;
        return aux;
    }
    /**
     * Devuelve true si es un long y false en caso de que no lo sea.
     * @return devuelve true en caso de que sea long y false en caso contrario.
     */
    public boolean isLong(){
        boolean aux = false;
        if (this.type.equals("long"))
            aux = true;
        return aux;
    }
    /**
     * Devuelve true si es un float y false en caso de que no lo sea.
     * @return devuelve true en caso de que sea float y false en caso contrario.
     */
    public boolean isFloat(){
        boolean aux = false;
        if (this.type.equals("float"))
            aux = true;
        return aux;
    }
    /**
     * Devuelve true si es un double y false en caso de que no lo sea.
     * @return devuelve true en caso de que sea double y false en caso contrario.
     */
    public boolean isDouble(){
        boolean aux = false;
        if (this.type.equals("double"))
            aux = true;
        return aux;
    }
    /**
     * Devuelve true si es un boolean y false en caso de que no lo sea.
     * @return devuelve true en caso de que sea boolean y false en caso contrario.
     */
    public boolean isBoolean(){
        boolean aux = false;
        if (this.type.equals("boolean"))
            aux = true;
        return aux;
    }
    /**
     * Devuelve true si es un char y false en caso de que no lo sea.
     * @return devuelve true en caso de que sea char y false en caso contrario.
     */
    public boolean isChar(){
        boolean aux = false;
        if (this.type.equals("char"))
            aux = true;
        return aux;
    }
    /********************getType()**************************/
    /**
     * Devuelve el tipo de la variable que estamos analizando.
     * @return devuelve la cadena que corresponde al tipo
     */
    public String getType(){
        return type;
    }

    /********************setInternalType()**************************/
    /**
     * Guardamos que tipo es el que estamos probando actualmente; 
     * Esta función la pongo para posibles ampliaciones de la clase.
     * @param type especifica la cadena del tipo que corresponda
     */
    private void setInternalType(String type) {
        this.type = type;
    }
    
}
