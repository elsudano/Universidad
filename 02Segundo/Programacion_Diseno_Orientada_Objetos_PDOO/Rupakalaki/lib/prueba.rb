require_relative "napakalaki/napakalaki"

module NapakalakiGame
  #
  # @!atribute [r] miControlador
  #   @return [Napakalaki] Crea un objeto de de la clase napakalaki que será el
  #   modelo que tenga la funcionalidad del juego
  #
  miControlador = Napakalaki.instance
  #
  # @!atribute [r] miResultado
  #   @return [CombatResult] Ponemos el resultado a un valor valido para poder
  #   compararlo en el loop principal
  #
  miResultado = CombatResult::LOSE
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

  jugadores = Array.new(2)
  jugadores << "Carlos"
  jugadores << "Sara"
  miControlador.initGame(jugadores)
  
  begin
    puts miControlador.getCurrentPlayer
    puts miControlador.getCurrentMonster
    misTesoros = Array.new(miControlador.getCurrentPlayer.getHiddenTreasures)    
    miControlador.discardHiddenTreasures(misTesoros)
    miResultado=miControlador.developCombat()
    miControlador.nextTurn()
  end while (miResultado != CombatResult::WIN)
end
