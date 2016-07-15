module NapakalakiGame
  
  class Treasure

    attr_accessor :name, :goldCoins, :minBonus, :maxBonus, :type

    def initialize(n,g,min,max,t)
      @name=n
      @goldCoins=g
      @minBonus=min
      @maxBonus=max
      @type=t
    end

    def to_s
        "Nombre: #{@name}, Tipo: #{@type}, Monedas: #{@goldCoins}, MaxBonus: #{@maxBonus}, MinBonus: #{@minBonus}"
    end
  end
end
