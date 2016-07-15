module NapakalakiGame
  require 'singleton'
  
  class Dice
    include Singleton
    
    def nextNumber()
      return 1 + rand(6)
    end
  
  end
end