module NapakalakiGame
  require_relative 'bad_consequence'
  require_relative 'prize'

  class Monster
    attr_accessor :name, :combatLevel, :prize, :badConsequence

    def initialize(n, l, b, p)
      @name=n
      @combatLevel=l.to_i
      @badConsequence=b
      @prize=p
    end
    
    def getLevelsGained()
      @prize.level
    end

    def getTreasuresGained()
      return @prize.treasures
    end

    def kills()
      @badConsequence.myBadConsequenceIsDeath()
    end

    def to_s
      return "Nombre: #{@name}\n\tNivel de Combate: #{@combatLevel}\n\tBuen Rollo:\n\t\t[#{@prize}]\n\tMal Rollo:\n\t\t[#{@badConsequence}]"
    end

  end
end
