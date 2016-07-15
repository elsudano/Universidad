module NapakalakiGame
#  require 'pry'
  require_relative 'treasure_kind'
  require_relative 'treasure'

  #
  # @abstract Esta clase es la encargada del mal rollo que tienen los monstruos
  # pero tambien se encarga de pasar el mal rollo pendiente al jugador cuando
  # pierde un combate po lo tanto 
  #
  class BadConsequence

    @@MAXTREASURES=10
    
    attr_accessor :text, :levels, :death
    
    def initialize(text="", levels=0, death=false)
      @text=text
      @levels=levels
      @death=death
    end
    
    def self.MAXTREASURES
      return @@MAXTREASURES
    end
    
    def isEmpty()
      raise NotImplementedError.new
    end
    
    def substractVisibleTreasure(treasure)
      raise NotImplementedError.new
    end
    
    def substractHiddenTreasure(treasure)
      raise NotImplementedError.new
    end
    
    def adjustToFitTreasureList(tVisible,tHidden)
      raise NotImplementedError.new
    end
    
    def myBadConsequenceIsDeath()
      return @death
    end

    def to_s
      return "\n\t\tTexto: #{@text}
      \t\tNiveles: #{@levels}
      \t\tMuerte: #{@death}\n"
    end
    
    private_class_method :new
  end
end