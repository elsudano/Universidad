module NapakalakiGame
  #  require 'pry'
  require_relative 'dice'
  
  class Player
    attr_reader :dead, :name, :visibleTreasures, :hiddenTreasures, :canISteal
    attr_accessor :enemy ,:level, :pendingBadConsequence
    @@MIN_LEVEL=1
    @@MAX_LEVEL=10
    @@MAX_HIDDEN_TREASURES=4

    def initialize(name)
      @dead = true
      @name = name
      @level = @@MIN_LEVEL
      @pendingBadConsequence
      @visibleTreasures = Array.new()
      @hiddenTreasures = Array.new()
      @enemy
      @canISteal=true
    end
    
    def self.MIN_LEVEL
      return @@MIN_LEVEL
    end
    
    def self.MAX_LEVEL
      return @@MAX_LEVEL
    end
    
    def self.MAX_HIDDEN_TREASURES
      return @@MAX_HIDDEN_TREASURES
    end
    
    def Player.newPlayer(p)
      return new(p.dead,p.name,p.level,p.pendingBadConsequence,p.visibleTreasures,p.hiddenTreasures)
    end
    
    def getName()
      return @name
    end
    
    def getVisibleTreasures()
      return @visibleTreasures
    end
    
    def getHiddenTreasures()
      return @hiddenTreasures
    end
    
    def getCombatLevel() 
      lvl = @level
      @visibleTreasures.each do |t| 
        lvl+=t.minBonus+t.maxBonus
      end 
      return lvl
    end 

    def isDead()
      return @dead == true
    end

    def bringToLife()
      @dead = false
    end

    def incrementLevels(i)
      @level += i
      if(@level > @@MAX_LEVEL)
        @level = @@MAX_LEVEL
      end
    end

    def decrementLevels(i)
      @level -= i
      if (@level < @@MIN_LEVEL)
        @level = @@MIN_LEVEL
      end
    end

    def die()
      @level = 1 
      dealer = CardDealer.instance 
      @visibleTreasures.each do |t|
        dealer.giveTreasuresBack(t) 
      end
      @visibleTreasures.clear 
      @hiddenTreasures.each do |t|
        dealer.giveTreasuresBack(t) 
      end
      @hiddenTreasures.clear 
      dieIfNoTreasures() 
    end

    def discardNecklaceIfVisible()
      dealer = CardDealer.instance
      for t in @visibleTreasures
        if (t.type == TreasureKind::NECKLACE)
          dealer.giveTreasureBack(t)
          @visibleTreasures.pop(t)            
        end
      end
    end

    def dieIfNoTreasures()
      @dead = @visibleTreasures.empty? && @hiddenTreasures.empty?
    end

    def computeGoldCoinsValue(t=Array.new)
      goldCoins = 0
      for tre in t
        goldCoins += tre.goldCoins
      end
      return goldCoins/1000
    end

    def canIBuyLevels(l)
      return @level + l < @@MAX_LEVEL
    end

    def applyPrize(m)
      binding.pry if $DEBUGMODE
      nLevels = m.getLevelsGained()
      incrementLevels(nLevels)
      nTreasures = m.getTreasuresGained()
      
      if (nTreasures>0)
        dealer = CardDealer.instance
        for i in 1..nTreasures
          treasure = dealer.nextTreasure()
          @hiddenTreasures.push(treasure)
        end
      end
      binding.pry if $DEBUGMODE
    end

    def combat (m)
      binding.pry if $DEBUGMODE
      myLevel = getCombatLevel()
      monsterLevel = m.combatLevel
      if myLevel>monsterLevel
        applyPrize(m)
        if (@level >= @@MAX_LEVEL) #@TODO Verificar que el getCombatLevel esta bien implementado
          combatResult = CombatResult::WINGAME
        else 
          combatResult = CombatResult::WIN
        end
      else
        applyBadConsequence(m)
        if (shouldConvert) 
          combatResult = CombatResult::LOSEANDCONVERT
        else 
          combatResult = CombatResult::LOSE
        end
      end
      binding.pry if $DEBUGMODE
      return combatResult   
    end

    def applyBadConsequence(m)
      binding.pry if $DEBUGMODE
      badConsequence = m.badConsequence
      nLevels = badConsequence.levels
      decrementLevels(nLevels)
      #puts badConsequence.to_s
      pendingBad = m.badConsequence.adjustToFitTreasureList(@visibleTreasures, @hiddenTreasures)
      setPendingBadConsequence(pendingBad)
    end
    
    def setPendingBadConsequence(b)
      @pendingBadConsequence=b;
    end

    def makeTreasureVisible(t)
      canI = canMakeTreasureVisible(t)
      if canI
        @visibleTreasures.push(t)
        @hiddenTreasures.delete(t)
      end
    end

    def canMakeTreasureVisible(t)
      binding.pry if $DEBUGMODE
      result = true
      cont = 0
      case t.type.to_s
      when "ONEHAND"
        @visibleTreasures.each do |tesoro|
          if (tesoro.type.eql?("ONEHAND"))
            cont +=1
          end
          if (tesoro.type.eql?("BOTHHANDS"))
            result = false
          end
        end
        if (cont >= 2)
          result = false
        end
      when "BOTHHANDS"
        @visibleTreasures.each do |tesoro|
          if (tesoro.type.eql?("ONEHAND"))
            cont +=1
          end
          if (tesoro.type.eql?(TreasureKind::BOTHHANDS))
            result = false
          end
        end
        if (cont > 0)
          result = false
        end
      else
        @visibleTreasures.each do |tesoro|
          if (tesoro.type.eql?(t.type))
            result = false
          end
        end
      end
      binding.pry if $DEBUGMODE
      return result
    end

    def howManyTreasureVisible(tKind)
      for t in @visibleTreasures
        if(t.type==tKind)
          i+=1 
        end
      end
      return i
    end
    
    def discardVisibleTreasure(t)
      binding.pry if $DEBUGMODE
      @visibleTreasures.delete(t)
      if(pendingBadConsequence!=nil && !pendingBadConsequence.isEmpty())
        pendingBadConsequence.substractVisibleTreasure(t)
      end
      dieIfNoTreasures()
      binding.pry if $DEBUGMODE
    end

    def discardHiddenTreasure(t)
      binding.pry if $DEBUGMODE
      @hiddenTreasures.delete(t)
      if(pendingBadConsequence!=nil && !pendingBadConsequence.isEmpty())
        pendingBadConsequence.substractHiddenTreasure(t)
      end    
      dieIfNoTreasures()
      binding.pry if $DEBUGMODE
    end

    def buyLevels(visible = Array.new, hidden = Array.new)
      canI = buyLevels(visible, hidden)
      levelsMayBought = computeGoldCoinsValue(visible)
      levelsMayBought += computeGoldCoinsValue(hidden)
      canI = canIBuyLevels(levelsMayBought)
      if canI
        incrementLevels(levelsMayBought)
      end
      visible.removeAll(visible)
      hidden.removeAll(hidden)
      dealer = CardDealer.instance
      @specificVisibleTreasures.each do |trk|

      end
      visible.each do |treasure|
        dealer.giveTreasureBack(treasure)
      end
      hidden.each do |treasure|
        dealer.giveTreasureBack(treasure)
      end
      return canI    
    end

    def validState()
      binding.pry if $DEBUGMODE
      bcaux = true
      # esto lo pongo asi por que ruby no es igual que java y comprueba todas
      # las opciones de un if, y cuando no hay un pendingBC falla
      if (@pendingBadConsequence != nil) then
        bcaux = @pendingBadConsequence.isEmpty()
      end
      return @hiddenTreasures.size() <= @@MAX_HIDDEN_TREASURES && bcaux
      binding.pry if $DEBUGMODE
    end

    def hasVisibleTreasures()
      return @visibleTreasures.isEmpty() == false    
    end

    def initTreasures()
      dealer = CardDealer.instance
      dice = Dice.instance
      bringToLife()
      treasure = dealer.nextTreasure()
      @hiddenTreasures.push(treasure)
      number = dice.nextNumber()
      if (number > 1)
        treasure = dealer.nextTreasure()
        @hiddenTreasures.push(treasure)
      end
      if (number == 6)
        treasure = dealer.nextTreasure()
        @hiddenTreasures.push(treasure)
      end
    end
    
    def stealTreasure()
      canI = canISteal()
      if (canI)
        canYou = @enemy.canYouGiveMeATreasure()
      end
      if (canYou)
        treasure = @enemy.giveMeATreasure()
        @hiddenTreasures.add(treasure)
        haveStolen()
      end
      return treasure
    end
    
    def setEnemy(enemy)
      @enemy=enemy
    end
    
    def giveMeATreasure()
      return @hiddenTreasures[ 1 + rand(@hiddenTreasures.size-1)]
    end
    
    def canYouGiveMeATreasure()
      return (!@visibleTreasures.empty? ||  !@hiddenTreasures.empty? )
    end
    
    def haveStolen()
      @canISteal=false
    end
    
    def discardAllTreasures()
      binding.pry if $DEBUGMODE
      auxv = Array.new(@visibleTreasures);
      auxh = Array.new(@hiddenTreasures);
      auxv.each do |t|
        discardVisibleTreasure(t);
      end
      auxh.each do |t|
        discardHiddenTreasure(t);
      end
      binding.pry if $DEBUGMODE
    end
    
    def to_s
      ret = "Name: #{@name}\nLevel: #{@level}\nEnemy: #{@enemy.name}\nVisible Treasures:\n\t{"
      @visibleTreasures.each do |t|
        ret += t.type + " "
      end    
      ret += "}\nHidden Treasures:\n\t{"
      @hiddenTreasures.each do |t|
        ret += t.type + " "
      end
      ret += "}"
      if (@pendingBadConsequence != nil) then
        ret += "\nPending BadStuff: #{@pendingBadConsequence.to_s}\nCombat Level: #{@combatLevel}\nDeath: #{@dead}"
      end
      return ret
    end

    def shouldConvert
      dice = Dice.instance
      return dice.nextNumber() == 1;
    end
    
    private :bringToLife, :getCombatLevel, :incrementLevels, :decrementLevels, :setPendingBadConsequence, :applyPrize, :applyBadConsequence, :canMakeTreasureVisible, :howManyTreasureVisible, :dieIfNoTreasures, :haveStolen, :die, :discardNecklaceIfVisible, :computeGoldCoinsValue, :canIBuyLevels, :shouldConvert 
    
  end
end
