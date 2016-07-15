module NapakalakiGame
  
  class DeathBadConsequence < NumericBadConsequence
    
    def initialize(text="",death=false)
      super(text,Player.MAX_LEVEL,death,BadConsequence.MAXTREASURES,BadConsequence.MAXTREASURES)
    end
    
    public_class_method :new
  end
end
