module NapakalakiGame

  class Cultist
    attr_accessor :name, :gainedLevels
    
    def initialize(name,gainedLevels)
      @name = name
      @gainedLevels = gainedLevels
    end

    def basicValue()
        return @gainedLevels;
    end

    def specialValue()
      return basicValue() * CultistPlayer.totalCultistPlayers();
    end

    def to_s
      return " Nombre: #{@name} Tipo: #{@gainedLevels}"
    end

    public_class_method :new
  end
  
end
