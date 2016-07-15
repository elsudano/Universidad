require_relative "napakalaki/napakalaki"
require_relative "test/GameTester"

module NapakalakiGame
  class EjemploMain
    #
    # @!atribute [r] DEBUGMODE
    #   @return [Boolean] Con esto ponemos en funcionamiento pry para debug.
    #   Verdadero si el modo de depuración esta activado falso en caso contrario.
    #
    $DEBUGMODE = false
    #
    # @!atribute [r] DIRSRC
    #   @return [String] Contiene la dirección o ruta del fichero ruby que estamos
    #   ejecutando en este momento
    #
    DIRSRC = File.dirname(File.absolute_path(__FILE__))
    #
    # @!atribute [r] BD_MONSTRUOS
    #   @return [String] Contiene la dirección o ruta del fichero con la base de 
    #   datos de los parametros de los monstruos
    #
    $BD_MONSTRUOS   ="#{DIRSRC}/resources/base_datos_monstruos.txt"
    #
    # @!atribute [r] BD_TESOROS
    #   @return [String] Contiene la dirección o ruta del fichero con la base de 
    #   datos de los parametros de los tesoros
    #
    $BD_TESOROS   ="#{DIRSRC}/resources/base_datos_tesoros.txt"
    #
    # @!atribute [r] BD_SECTARIOS
    #   @return [String] Contiene la dirección o ruta del fichero con la base de 
    #   datos de los parametros de las cartas para los jugadores sectarios
    #
    $BD_SECTARIOS   ="#{DIRSRC}/resources/base_datos_sectarios.txt"
    def prueba
      test = Test::GameTester.instance
      game = Napakalaki.instance
      #Se prueba el juego con 2 jugadores
      test.play(game, 2);
    end
  end
  e = EjemploMain.new
  e.prueba()
end
