module NapakalakiGame
  
  class Prize
    attr_reader :treasures, :level

    def initialize(t ,l)
      @treasures=t.to_i
      @level=l.to_i
    end

    def to_s
      "Tesoros ganados:#{@treasures}, Niveles ganados: #{@level}"
    end
  end
end
