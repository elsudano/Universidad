module NapakalakiGame
  class Card
    def name()
      raise NotImplementedError.new
    end
    
    def basicValue()
      raise NotImplementedError.new
    end

    def specialValue()
      raise NotImplementedError.new
    end
    
    private_class_method :new
  end
end