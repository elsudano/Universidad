module NapakalakiGame
  #  require 'pry'
  require 'singleton'
  require_relative 'treasure_kind'
  require_relative 'treasure'
  require_relative 'bad_consequence'
  require_relative 'numeric_bad_consequence'
  require_relative 'specific_bad_consequence'
  require_relative 'death_bad_consequence'
  require_relative 'cultist'
  require_relative 'prize'
  require_relative 'monster'

  class CardDealer
    include Singleton
    attr_accessor :usedMonsters, :unusedMonsters, :usedTreasures, :unusedTreasures, :usedCultists, :unusedCultists
    # 
    # Esta es una función que sirve para inicializar el mazo de los 
    # Tesoros se está intentando que se importen los tesoros
    # directamente desde un fichero de texto para que no tener que
    # crearlos todos a mano.
    #
    def initTreasureCardDeck()
      binding.pry if $DEBUGMODE
      @unusedTreasures = Array.new
      @usedTreasures = Array.new
      fd = File.open("#{$BD_TESOROS}", "r")
      fd.each_line do |line|
        columnas = line.split(",")
        @unusedTreasures.push(Treasure.new(columnas[0], columnas[4], columnas[2].to_i, columnas[3].to_i, columnas[1].upcase))
      end
      fd.close
      #      @unusedTreasures.each do |t| 
      #        puts "Tesoro: #{t.to_s}"
      #      end
      binding.pry if $DEBUGMODE
    end
    # 
    # Esta es una funcion que sirve para inicializar el mazo de los 
    # monstruos se esta intentando que se importen los monstruos
    # directamente desde un fichero de texto para que no tener que
    # crearlos todos a mano.
    #
    def initMonsterCardDeck()
      binding.pry if $DEBUGMODE
      @unusedMonsters = Array.new
      @usedMonsters = Array.new
      fd = File.open("#{$BD_MONSTRUOS}", "r")
      fd.each_line do |line|
        columnas = line.split(",")
        miPrice = Prize.new(columnas[2], columnas[3])
        if (columnas[10] == "true") then
          miBadConsequence = DeathBadConsequence.new(columnas[4], columnas[10])
        elsif (!columnas[8].empty? || !columnas[9].empty?) then
          #          tesorosVisibles = columnas[8].split("-")
          #          tesorosOcultos = columnas[9].split("-")
          tesorosVisibles = leeTesoros(columnas[8])
          tesorosOcultos = leeTesoros(columnas[9])
          miBadConsequence = SpecificBadConsequence.new(columnas[4], columnas[5].to_i, columnas[10], tesorosVisibles, tesorosOcultos) 
        else
          miBadConsequence = NumericBadConsequence.new(columnas[4], columnas[5].to_i, columnas[10], columnas[6].to_i, columnas[7].to_i)
        end
        @unusedMonsters.push(Monster.new(columnas[0], columnas[1].to_i, miBadConsequence, miPrice))
      end
      fd.close
      #      @unusedMonsters.each do |m| 
      #        puts "Monstruo: #{m.to_s}"
      #      end
      binding.pry if $DEBUGMODE
    end
    # 
    # Esta es una funcion que sirve para inicializar el mazo de los 
    # Jugadores Sectarios se importen directamente desde un fichero
    # de texto para que no tener que crearlos todos a mano.
    #
    def initCultistCardDeck()
      binding.pry if $DEBUGMODE
      @unusedCultists = Array.new
      @usedCultists = Array.new
      fd = File.open("#{$BD_SECTARIOS}", "r")
      fd.each_line do |line|
        columnas = line.split(",")
        @unusedCultists.push(Cultist.new(columnas[0], columnas[1].to_i))
      end
      fd.close
      binding.pry if $DEBUGMODE
    end
    
    def shuffleTreasures()
      @unusedTreasures = @unusedTreasures.shuffle
    end
  
    def shuffleMonsters()
      @unusedMonsters = @unusedMonsters.shuffle
    end
  
    def shuffleCultists()
      @unusedCultists = @unusedCultists.shuffle
    end
    
    def nextTreasure()
      binding.pry if $DEBUGMODE
      if @unusedTreasures.empty?
        @usedTreasures.each do |t| 
          @unusedTreasures<<t
        end
        shuffleTreasures()
        @usedTreasures.clear
      end
      t = @unusedTreasures.at(0)
      @usedTreasures<<t
      @unusedTreasures.delete(t);
      binding.pry if $DEBUGMODE
      return t
    end
  
    def nextMonster()
      binding.pry if $DEBUGMODE
      if @unusedMonsters.empty?
        @usedMonsters.each do |m| 
          @unusedMonsters<<m
        end
        shuffleMonsters()
        @usedMonsters.clear
      end
      m = @unusedMonsters.at(0)
      @usedMonsters<<m
      @unusedMonsters.delete(m);
      binding.pry if $DEBUGMODE
      return m
    end
  
    def nextCultist()
      binding.pry if $DEBUGMODE
      if @unusedCultists.empty?
        @usedCultists.each do |m| 
          @unusedCultists<<m
        end
        shuffleCultists()
        @usedCultists.clear
      end
      c = @unusedCultists.at(0)
      @usedCultists<<c
      @unusedCultists.delete(c);
      binding.pry if $DEBUGMODE
      return c
    end
    
    def giveTreasuresBack(t)
      @usedTreasures<<t
    end
  
    def giveMonstersBack(m)
      @usedMonsters<<m
    end
    
    def giveCardBack(c)
      @usedMonsters<<c
    end
  
    def initCards()
      binding.pry if $DEBUGMODE
      initTreasureCardDeck()
      shuffleTreasures()
      initMonsterCardDeck()
      shuffleMonsters()
      initCultistCardDeck()
      shuffleCultists()
      binding.pry if $DEBUGMODE
    end
  
    def leeTesoros(cadena_tesoros)
      cadena_tesoros = cadena_tesoros.upcase
      cadena_tesoros = cadena_tesoros.split("-")
      binding.pry if $DEBUGMODE
      # estos serán del tipo Treasure Kind
      tesoros = Array.new
      cadena_tesoros.each do |tk|
        case tk
        when "ARMOR" then
          tesoros << TreasureKind::ARMOR
        when "ONEHAND" then
          tesoros << TreasureKind::ONEHAND
        when "BOTHHANDS" then
          tesoros << TreasureKind::BOTHHANDS
        when "HELMET" then
          tesoros << TreasureKind::HELMET
        when "SHOES" then
          tesoros << TreasureKind::SHOES
        end
      end
      binding.pry if $DEBUGMODE
      return tesoros
    end
    
    private :initMonsterCardDeck, :initTreasureCardDeck, :initCultistCardDeck, :shuffleMonsters, :shuffleTreasures, :shuffleCultists, :leeTesoros
  end
end
