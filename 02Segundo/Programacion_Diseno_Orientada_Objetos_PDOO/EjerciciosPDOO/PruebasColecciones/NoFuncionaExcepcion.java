/*
 * Del libro "Java 6. Los fundamentos del lenguaje Java". Ed. Eni
 * ¡Lo tenéis en la biblioteca!
 * 
 * Excepciones
 */

package PruebasColecciones;

public class NoFuncionaExcepcion extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public NoFuncionaExcepcion()
        {
            super();
        }
        public NoFuncionaExcepcion(String mensaje)
        {
            super(mensaje);
        }
        public NoFuncionaExcepcion(String mensaje, Throwable causa)
        {
            super(mensaje,causa);
        }
        public NoFuncionaExcepcion(Throwable causa){
            super(causa);
        }
}
