#
# Esté será el nombre del modulo principal del juego, osea que todos los ficheros
# que se tengan que incluir en el juego se tienen que incluir dentro de este
# modulo para que asi de esa manera puedan llamarse a las diferentes funciones.
# 
# @author Carlos de la Torre
#
module NapakalakiGame
#  require 'pry'
  require 'singleton'
  require_relative 'card_dealer'
  require_relative 'player'
  require_relative 'combat_result'
  
  #  
  # Esta es la clase principal del juego digamos que será el contenedor del resto de
  # clases, que serán las que en realidad tengan toda la funcionalidad que se 
  # espera de la aplicación
  #  
  class Napakalaki
    include Singleton
    
    # @!group Estos son todos los atributos de la instancia de Napakalaki
    
    # @!attribute [rw] currentPlayer
    #   @return [Player] Será el jugador actual de la partida.
    #
    # @!attribute [rw] currentMonster
    #   @return [Monster] Será el monstruo actual de la partida.
    #
    # @!attribute [rw] dealer
    #   @return [CardDealer] Será el encargado de repartir las cartas a los jugadores.
    #
    # @!attribute [rw] dice
    #   @return [Dice] Será el unico dado que habra en la partida.
    #
    # @!attribute [rw] players
    #   @return [Array] Es una colección de todos los jugadores del juego.
    #
    attr_accessor :currentPlayer, :currentMonster, :dealer, :dice, :players
    
    # @!endgroup

    # Este es el constructor de +Napakalaki+ se encarga de guardar en una 
    # variable la instancia del repartidor de cartas (CardDealer) y del
    # dado (Dice) aparte de reservar la memoria oportuna para guardar los
    # jugadores en un Array.
    # @note Tanto el jugador actual como el monstruo actual se quedán 
    # vacios cuidado con esto.
    # @!visibility private
    #
    def initialize()
      @currentPlayer = nil
      @currentMonster = nil
      @players = Array.new
      @dealer = CardDealer.instance
      @dice = Dice.instance
      binding.pry if $DEBUGMODE
    end 
    
    #
    # Método que devuelve el jugador áctual de la partida.
    # @return [Player] Jugador áctual
    # @note se utiliza este metodo en concreto por que se llama así desde otros objetos
    #
    def getCurrentPlayer()
      @currentPlayer
    end
    
    #
    # Método que devuelve el monstruo áctual de la partida.
    # @return [Monster] Monstruo áctual
    # @note se utiliza este metodo en concreto por que se llama así desde otros objetos
    #
    def getCurrentMonster()
      @currentMonster
    end
    
    #
    # Método que se encarga de poner nombre a los jugadores y crearlos en el sistema.
    # @param names [Array<String>] Contiene los nombres de los Jugadores
    # @return [void]
    #
    def initPlayers(names)
      @players = Array.new
      binding.pry if $DEBUGMODE
      names.each do |s|
        @players << Player.new(s)
      end
      binding.pry if $DEBUGMODE
    end
  
    #
    # Este metodo se encarga devolver el siguiente jugador de forma aleatoria
    # la primera vez que se empieza el juego, y luego mantiene el orden de turno
    # de los jugadores de la partida.
    # @return [Player] Este será el siguiente jugador valido.
    #
    def nextPlayer()
      totalPlayers = @players.length 
      if (@currentPlayer == nil) then
        nextIndex = rand(totalPlayers)
      else
        currentPlayerIndex = @players.index(@currentPlayer)
        if currentPlayerIndex == totalPlayers-1 then
          nextIndex = 0
        else
          nextIndex = currentPlayerIndex + 1
        end
      end
      return @players.at(nextIndex)
      binding.pry if $DEBUGMODE
    end
  
    #
    # Este metodo se encarga de decidir si el jugador actual tiene permitido 
    # pasar al siguiente turno.
    # @return [Boolean] Verdadero si se puede pasar de turno, falso en caso contrario.
    #
    def nextTurnAllowed()
      binding.pry if $DEBUGMODE
      if (@currentPlayer == nil)
        allowed = true 
      else
        allowed = @currentPlayer.validState()
      end
      return allowed
      binding.pry if $DEBUGMODE
    end
  
    #
    # Este metodo se encarga de gestionar el combate entre el monstruo y un
    # jugador
    # @return [CombatResult] Este será el resultado que devuelva el combate
    #
    def developCombat()
      binding.pry if $DEBUGMODE
      result = @currentPlayer.combat(@currentMonster)
      @dealer.giveMonstersBack(@currentMonster)
      return result
      binding.pry if $DEBUGMODE
    end
  
    #
    # Método que se encarga de descartarse de todos los tesoros visibles 
    # que se le indican en el parametro de entrada.
    # @param treasures [Array<Treasure>] Contiene los tesoros que queremos
    # descartar
    # @return [void]
    #
    def discardVisibleTreasures(treasures)
      binding.pry if $DEBUGMODE
      treasures.each do |t|
        @currentPlayer.discardVisibleTreasure(t)
        @dealer.giveTreasuresBack(t)
      end
      binding.pry if $DEBUGMODE
    end
  
    #
    # Método que se encarga de descartarse de todos los tesoros ocultos 
    # que se le indican en el parametro de entrada.
    # @param treasures [Array<Treasure>] Contiene los tesoros que queremos
    # descartar
    # @return [void]
    #
    def discardHiddenTreasures(treasures)
      binding.pry if $DEBUGMODE
      treasures.each do |t|
        @currentPlayer.discardHiddenTreasure(t) 
        @dealer.giveTreasuresBack(t) 
      end
      binding.pry if $DEBUGMODE
    end
  
    #
    # Método que se encarga pasar un tesoro que esta oculto a los tesoros
    # que estan visibles, es como si equipase el tesoro al jugador.
    # @param treasure [Treasure] Contiene los tesoros que queremos
    # descartar
    # @return [void]
    #
    def makeTreasuresVisible(treasure)
      binding.pry if $DEBUGMODE
      canI = canMakeTreasureVisible(treasure) 
      if canI then
        @visibleTreasures << treasure 
        @hiddenTreasures.delete(treasure) 
      end
      binding.pry if $DEBUGMODE
    end
  
    #
    # Método que se encarga de iniciar el juego, establece todo lo necesario
    # para comenzar una partida nueva.
    # @param players [Array<Player>] Contiene a los objetos jugadores
    # @return [void]
    #
    def initGame(players)
      binding.pry if $DEBUGMODE
      initPlayers(players)
      setEnemies()
      @dealer.initCards()
      nextTurn()
      binding.pry if $DEBUGMODE
    end
  
    #
    # Este metodo se encarga de comprobar si podemos pasar al siguiente turno.
    # @return [Boolean] Verdadero si se puede pasar al siguiente turno, falso
    # en caso contrario.
    #
    def nextTurn()
      stateOK = nextTurnAllowed()
      binding.pry if $DEBUGMODE
      if (stateOK)
        @currentMonster = @dealer.nextMonster()
        @currentPlayer = nextPlayer()
        dead = @currentPlayer.isDead() 
        if (dead)
          @currentPlayer.initTreasures() 
        end
      end
      return stateOK
      binding.pry if $DEBUGMODE
    end
  
    #
    # Este metodo se encarga de comprobar si hemos llegado al final de la partida
    # @param result [CombatResult] Resultado de la ultima pelea.
    # @return [Boolean] Verdadero si el jugador ha ganado el juego,
    # falso en caso contrario
    #
    def endOfGame(result)
      return result == CombatResult::WINGAME
    end
    
    #
    # Este metodo se encarga de asignar los enemigos a cada uno de los jugadores
    # se asegura tambien de que el jugador no es su propio enemigo.
    # @return [void]
    #
    def setEnemies()
      binding.pry if $DEBUGMODE
      miEnemy=nextPlayer()
      for p in @players
        while (p == miEnemy)
          miEnemy=nextPlayer()
        end
        p.setEnemy(miEnemy)
      end
      binding.pry if $DEBUGMODE
    end
  
    private  :initPlayers, :nextPlayer, :nextTurnAllowed, :setEnemies
  
  end
end