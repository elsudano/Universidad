module NapakalakiGame
#  require 'pry'

  class NumericBadConsequence < BadConsequence
    
    attr_accessor :nVisibleTreasures, :nHiddenTreasures
    
    def initialize(text="", levels=0, death=false, nVisibleTreasures=0, nHiddenTreasures=0)
      super(text,levels,death)
      @nVisibleTreasures = nVisibleTreasures
      @nHiddenTreasures = nHiddenTreasures
    end
    
    def adjustToFitTreasureList(tVisible,tHidden)
      binding.pry if $DEBUGMODE
      bs = NumericBadConsequence.new
      numvisibles = numocultos = 0
      # compruebo el tamaño de los array para ver si puedo usarlos
      if(@nVisibleTreasures > tVisible.size() || @nHiddenTreasures > tHidden.size())
        # actualizo los valores de los tesoros visibles
        if (@nVisibleTreasures > tVisible.size())
          numvisibles = tVisible.size()
        else
          numvisibles = @nVisibleTreasures
        end
        # actualizo los valores de los tesoros ocultos
        if (@nHiddenTreasures > tHidden.size())
          numocultos = tHidden.size()
        else
          numocultos = @nHiddenTreasures
        end
        # genero un malrollo con los valores correctos
        bs = NumericBadConsequence.new(@text, @levels, @death, numvisibles, numocultos)
        binding.pry if $DEBUGMODE
      end
      # devuelvo el malrollo que corresponda
      binding.pry if $DEBUGMODE
      return bs
    end
    
    def substractVisibleTreasure(treasure)
      if @nVisibleTreasures > 0 
        @nVisibleTreasures -= 1
      end
    end
    
    def substractHiddenTreasure(treasure)
      if @nHiddenTreasures > 0 
        @nHiddenTreasures -= 1
      end
    end

    def isEmpty()
      empty = false
      if (@nVisibleTreasures == 0 && @nHiddenTreasures == 0)
        empty = true
      end
      return empty
    end
    
    def to_s
      super()+"\t\tNumero de tesoros Visibles: #{@nVisibleTreasures}\n\t\tNumero de tesoros Ocultos: #{@nHiddenTreasures}"
    end
    
    public_class_method :new
  end
end
