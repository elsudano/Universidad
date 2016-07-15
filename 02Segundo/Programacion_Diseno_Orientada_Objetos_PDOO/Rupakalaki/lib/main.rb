#
# Para poder ejecutar este codigo ruby es necesario tener instalado 
# la gema de Shoes para ruby (la verde va mejor que la purpura) se encarga de
# generar una interfaz Gráfica a partir de un fichero de texto programado en ruby.
# 
# para instalar la gema en ruby: 
#

require 'pry'
require 'green_shoes'
#require 'purple_shoes'
require_relative 'napakalaki/napakalaki'
require_relative 'napakalaki/combat_result'

#
# Método que se encarga de la actualización de la vista del jugador
# pasandole un jugador al método se muestran todas las propiedades de este.
# @param player [Player] Será el jugador que queremos mostrar
# @return [void]
#
def actualiza_jugador(player)
  # Comienza jugador
  stack(width: $ANCHO/4, height: ($ALTO/12)*9, margin: [10,5,0,0]) do 
    caption fg("Jugador", darkred), weight: "bold"
    flow() do # fila del nombre
      para "Nombre: ", width: $ANCHO/10, weight: "normal", size: 11, margin: [5,5,0,0]
      edit_line text: player.getName, state: "readonly", width: $ANCHO/10, height: $ALTO/30
    end
    flow() do # fila del nivel
      para "Nivel: ", width: $ANCHO/10, weight: "normal", size: 11, margin: [5,5,0,0]
      edit_line text: player.level, state: "readonly", width: $ANCHO/10, height: $ALTO/30
    end
    flow() do # fila del enemigo
      para "Enemigo ", width: $ANCHO/10, weight: "normal", size: 11, margin: [5,5,0,0]
      edit_line text: player.enemy.getName, state: "readonly", width: $ANCHO/10, height: $ALTO/30
    end
    caption fg("Tesoros Visibles", darkred), weight: "normal"
    # creamos un array para los nombres de los tipos de los tesoros
    tesorosv = Array.new
    # recorremos los tesoros y los guardamos en el array
    player.getVisibleTreasures.each do |tesoro|
      tesorosv << tesoro.type.to_s
    end
    # ponemos los tesoros en el campo para mostrarlos
    edit_box text: "#{tesorosv}",width: $ANCHO/5, height: $ALTO/20, state: "readonly", margin: 3
    caption fg("Tesoros Ocultos", darkred), weight: "normal"
    tesoroso = Array.new
    player.getHiddenTreasures.each do |tesoro|
      tesoroso << tesoro.type.to_s
    end
    edit_box text: "#{tesoroso}",width: $ANCHO/5, height: $ALTO/20, state: "readonly", margin: 3
    if (player.pendingBadConsequence != nil && !player.pendingBadConsequence.isEmpty)
      caption fg("Mal Rollo Pendiente", darkred), weight: "normal"
      if (player.pendingBadConsequence.is_a?(NapakalakiGame::NumericBadConsequence))
        # niveles que pierdes por culpa del mal rollo
        para "Pierdes #{player.pendingBadConsequence.levels} niveles", width: $ANCHO/5, weight: "normal", size: 11, margin: [5,5,0,0]
        # numero de tesoros visibles que hay que descartarse
        para "Tienes que descartarte de: #{player.pendingBadConsequence.nVisibleTreasures} tesoros visibles", width: $ANCHO/5, weight: "normal", size: 11, margin: [5,5,0,0]
        # numero de tesoros ocultos que hay que descartarse
        para "Tienes que descartarte de: #{player.pendingBadConsequence.nHiddenTreasures} tesoros ocultos", width: $ANCHO/5, weight: "normal", size: 11, margin: [5,5,0,0]
        # comprobamos que este mal rollo produce la muerte
        if (player.pendingBadConsequence.myBadConsequenceIsDeath)
          para "Y Vas a MORIR...", width: $ANCHO/5, weight: "bold", size: 11, margin: [5,5,0,0]
        end
      elsif(player.pendingBadConsequence.is_a?(NapakalakiGame::SpecificBadConsequence))
        # niveles que pierdes por culpa del mal rollo
        para "Pierdes #{player.pendingBadConsequence.levels} niveles", width: $ANCHO/5, weight: "normal", size: 11, margin: [5,5,0,0]
        # Tipos de tesoros visibles que hay que descartarse
        # creamos un array para los nombres de los tipos de los tesoros visibles del mall rollo
        tesorosv = Array.new
        tesorosv.clear
        # recorremos los tesoros y los guardamos en el array
        player.pendingBadConsequence.specificVisibleTreasures.each do |tesoro|
          tesorosv << tesoro.type.to_s
        end
        # ponemos los tesoros en el campo para mostrarlos
        edit_box text: "#{tesorosv}",width: $ANCHO/5, height: $ALTO/20, state: "readonly", margin: 3
        # Tipos de tesoros ocultos que hay que descartarse
        tesoroso = Array.new
        tesoroso.clear
        player.pendingBadConsequence.specificHiddenTreasures.each do |tesoro|
          tesoroso << tesoro.type.to_s
        end
        edit_box text: "#{tesoroso}",width: $ANCHO/5, height: $ALTO/20, state: "readonly", margin: 3
        # comprobamos que este mal rollo produce la muerte
        if (player.pendingBadConsequence.myBadConsequenceIsDeath)
          para "Y Vas a MORIR...", width: $ANCHO/5, weight: "bold", size: 11, margin: [5,5,0,0]
        end
      end
    end
  end
  # termina jugador
end

#
# Método que se encarga de mostrar la carta con las propiedades del tesoro
# que se le pasa por parámetros
# @param treasure [Treasure] Será el tesoro que queremos pintar en la vista
# @return [void]
#
def carta_tesoro(treasure)
  seleccionado = false
  carta = stack(width: 125, height: 215) do
    background white
    if (seleccionado)
      border yellowgreen, strokewidth: 2
    else
      border black, strokewidth: 2
    end
    stack(height: 45, margin: [5,3,0,0]) do
      para treasure.name, weight: "bold"
    end
    tamanyo = imagesize File.join($IMAGENES_TESOROS,"#{treasure.name}.png")
    image File.join($IMAGENES_TESOROS,"#{treasure.name}.png"),width: tamanyo[0]*0.7, height: tamanyo[1]*0.7, margin: [28,2,0,2]
    para "Tipo: #{treasure.type}", width: 120, size: 10, margin: [5,0,0,0]
    para "Bonus: #{treasure.maxBonus}", width: 120, size: 10, margin: [5,0,0,0]
    para "Monedas: #{treasure.goldCoins}", width: 120, size: 10, margin: [5,0,0,0]
  end
  carta.click() do
    seleccionado = !seleccionado
    puts "pulsada la carta: #{treasure.type}"
  end
end

#
# Método que se encarga de mostrar en la vista de tesoros todos los tesoros 
# que tiene el jugador, visibles y ocultos
# @param tesoros_visibles [Array<Treasure>] Serán los tesoros visibles del
# jugador que se mostraran en la vista de tesoros.
# @param tesoros_ocultos [Array<Treasure>] Serán los tesoros ocultos del
# jugador que se mostraran en la vista de tesoros.
# @return [void]
#
def actualiza_tesoros(tesoros_visibles,tesoros_ocultos)
  binding.pry if $DEBUGMODE
  # comienza tesoros
  stack(width: ($ANCHO/4)*2, height: ($ALTO/12)*9) do 
    para fg("Tesoros Equipados: ", darkred), weight: "bold", size: 11
    flow(width: ($ANCHO/4)*2, height: ($ALTO/12)*4, margin: [3,0,3,0]) do
      tesoros_visibles.each do |tesoro|
        carta_tesoro(tesoro)
      end
    end
    para fg("Tesoros en la Mochila: ", darkred), weight: "bold", size: 11, margin: [0,10,0,0]
    flow(width: ($ANCHO/4)*2, height: ($ALTO/12)*4, margin: [3,0,3,0]) do
      tesoros_ocultos.each do |tesoro|
        carta_tesoro(tesoro)
      end
    end
  end
  # termina tesoros
  binding.pry if $DEBUGMODE
end

#
# Método que se encarga de mostrar en la vista del monstruo todas las propiedades
# del monstruo que se pasa por parámetros
# @param monster [Monster] Será el Monstruo que queremos mostrar
# @return [void]
#
def actualiza_monstruo(monster)
  # comienza monstruo
  @monstruo = stack(width: $ANCHO/4, height: ($ALTO/12)*9, margin: [10,5,0,0]) do 
    caption fg("Monstruo", darkred), weight: "bold"
    # imagen del monstruo
    flow() do
      stack(width: $ANCHO/12, height: ($ALTO/12)*2, margin:3) do
        background white
        border black, strokewidth: 2 
        tamanyo = imagesize File.join($IMAGENES_MONSTRUOS,"#{monster.name}.png")
        image File.join($IMAGENES_MONSTRUOS,"#{monster.name}.png"),width: tamanyo[0]*0.65, height: tamanyo[1]*0.65
      end
      stack(width: $ANCHO/12, height: ($ALTO/12)*2, margin: [5,0,0,0]) do
        # para mostrar el nombre del monstruo
        para "Nombre: ", weight: "bold", size: 10, margin: [5,1,0,0]
        edit_line text: monster.name, state: "readonly", width: $ANCHO/9, height: $ALTO/30
        # para mostrar el nivel de combate del monstruo
        para "Nivel de Combate: ", weight: "bold", size: 10, margin: [3,0,0,0]
        edit_line text: monster.combatLevel, state: "readonly", width: $ANCHO/9, height: $ALTO/30
      end
    end
    caption fg("Si le Ganas:", darkred), weight: "bold"
    # numero de tesoros que te dan si le ganas al monstruo
    para "Te llevas: #{monster.getTreasuresGained} tesoro/s"
    # numero de niveles que te dan si le ganas
    para "Sumas: #{monster.getLevelsGained} nivel/es"
    caption fg("Si Pierdes:", darkred), weight: "bold"
    if (monster.badConsequence.is_a?(NapakalakiGame::NumericBadConsequence))
      # el texto del mal rollo
      para "#{monster.badConsequence.text}", width: $ANCHO/5, weight: "bold", size: 11, margin: [5,5,0,0]
      # niveles que pierdes por culpa del mal rollo
      para "Pierdes #{monster.badConsequence.levels} niveles", width: $ANCHO/5, weight: "normal", size: 11, margin: [5,5,0,0]
      # numero de tesoros visibles que hay que descartarse
      para "Tienes que descartarte de: #{monster.badConsequence.nVisibleTreasures} tesoros visibles", width: $ANCHO/5, weight: "normal", size: 11, margin: [5,5,0,0]
      # numero de tesoros ocultos que hay que descartarse
      para "Tienes que descartarte de: #{monster.badConsequence.nHiddenTreasures} tesoros ocultos", width: $ANCHO/5, weight: "normal", size: 11, margin: [5,5,0,0]
      binding.pry if $DEBUGMODE
    elsif(monster.badConsequence.is_a?(NapakalakiGame::SpecificBadConsequence))
      # el texto del mal rollo
      para "#{monster.badConsequence.text}", width: $ANCHO/5, weight: "bold", size: 11, margin: [5,5,0,0]
      # niveles que pierdes por culpa del mal rollo
      para "Pierdes #{monster.badConsequence.levels} niveles", width: $ANCHO/5, weight: "normal", size: 11, margin: [5,5,0,0]
      # Tipos de tesoros visibles que hay que descartarse
      caption fg("Tesoros Ocultos", darkred), weight: "normal"
      # creamos un array para los nombres de los tipos de los tesoros visibles del mall rollo
      tesoros_visibles = Array.new
      # recorremos los tesoros y los guardamos en el array
      # estos tesoros son del Tipo TreasureKind que son diferentes a los del player
      monster.badConsequence.specificVisibleTreasures.each do |tesoro|
        tesoros_visibles << tesoro.to_s
      end
      # ponemos los tesoros en el campo para mostrarlos
      edit_box text: "#{tesoros_visibles}",width: $ANCHO/5, height: $ALTO/20, state: "readonly", margin: 3
      caption fg("Tesoros Ocultos", darkred), weight: "normal"
      # Tipos de tesoros ocultos que hay que descartarse
      tesoros_ocultos = Array.new
      monster.badConsequence.specificHiddenTreasures.each do |tesoro|
        tesoros_ocultos << tesoro.to_s
      end
      edit_box text: "#{tesoros_ocultos}",width: $ANCHO/5, height: $ALTO/20, state: "readonly", margin: 3
      binding.pry if $DEBUGMODE
    end
  end
  # termina monstruo
end

# función que actualiza la vista cada vez que se produce un evento
# se tiene que llamar en cada pulsación de botones
def actualiza(napakalaki)
  #  estado = 0
  # comienza el clear borramos lo que hay en la vista
  clear do
    #
    # este contenedor es para que cuando se maximize la ventana las vistes queden
    # en el centro de la misma.
    # Para eso me hace falta que se modificquen los parámetros de width, height
    # cuando se modifique el tamaño de la ventana
    #
    flow(width: width, height: height, margin: [width/2-$ANCHO/2, height/2-$ALTO/2, 0, 0]) do
      background lightgrey
      stack() do
        #-----------------------------------------------------------------------
        flow(width: $ANCHO, height: $ALTO/12, margin: 10, scroll: false) do # contiene la cabecera
          para fg("Resultado del Combate: ", darkred), width: ($ANCHO/16)*5, weight: "bold", font: "Lacuna Regular", size: 14, margin: [20,7,0,0]
          if (@combat_result != nil)
            case @combat_result.to_s
            when "WINGAME" then
              edit_line text: "Enhorabuena ganaste la partida", state: "readonly", width: ($ANCHO/16)*6, height: $ALTO/30
            when "WIN" then
              edit_line text: "Ganaste este combate... Sigue Así", state: "readonly", width: ($ANCHO/16)*6, height: $ALTO/30
            when "LOSEANDCONVERT"
              then edit_line text: "Has perdido y encima te han convertido...", state: "readonly", width: ($ANCHO/16)*6, height: $ALTO/30
            when "LOSE" then
              edit_line text: "OOOHHHH Has perdido esta lucha...", state: "readonly", width: ($ANCHO/16)*6, height: $ALTO/30
            end
          else
            edit_line text: "", state: "readonly", width: ($ANCHO/16)*6, height: $ALTO/30
          end
        end
        # fin de la cabecera
        # contiene las vistas
        flow(width: $ANCHO, height: ($ALTO/12)*9) do
          # actualizamos la vista del jugador
          actualiza_jugador(napakalaki.getCurrentPlayer)
          # actualizamos la vista de los tesoros
          actualiza_tesoros(napakalaki.getCurrentPlayer.getVisibleTreasures,napakalaki.getCurrentPlayer.getHiddenTreasures)
          # actualizamos la vista del monstruo
          actualiza_monstruo(napakalaki.getCurrentMonster)
        end
        # fin de las vistas
        stack(width: $ANCHO, height: ($ALTO/12)*2, margin: [10,10,0,0]) do # comienzo de los botones
          flow() do # contiene los botones del jugador
            @bequipar_tesoro = button "Equipar tesoro" do
              alert("Equipate con los tesoros seleccionados")
            end
            @bdescartar_tesoro = button "Descartar tesoro" do
              alert("Descartate solo de los tesoros seleccionados")
            end
            @bdescartar_todos = button "Descartar todos" do
              napakalaki.getCurrentPlayer.discardAllTreasures()
              actualiza(napakalaki)
            end
            @broba_tesoro = button "Robar tesoro" do
              binding.pry if $DEBUGMODE
              if (napakalaki.getCurrentPlayer.canISteal) then
                napakalaki.getCurrentPlayer.stealTreasure
              else
                alert("En estos momentos no puedes robar un tesoro")
              end
              binding.pry if $DEBUGMODE
            end
            @bequipar_tesoro.style(width: 150)
            @bdescartar_tesoro.style(width: 150)
            @bdescartar_todos.style(width: 150)
            @broba_tesoro.style(width: 150)
          end # fin de los botones del jugador
          flow() do # contiene los botones de la app
            @bmonstruo = button "Conocer Monstruo" do
              @monstruo.toggle()
              #estado = 1
            end
            @bcombatir = button "Combatir" do
              @combat_result = napakalaki.developCombat
              binding.pry if $DEBUGMODE
              actualiza(napakalaki)
            end
            @bsiguiente = button "Siguiente turno" do
              napakalaki.nextTurn()
              actualiza(napakalaki)
            end
            @bsalir = button "Salir" do
              close
            end
            @bmonstruo.style(width: 150)
            @bcombatir.style(width: 150)
            @bsiguiente.style(width: 150)
            @bsalir.style(width: 150)
          end # fin de los botones de la app
        end # fin de los botones
        # vaciamos las variables que nos sirven para pasar mensajes entre las vistas
        @combat_result = nil
        #-----------------------------------------------------------------------
      end
    end
  end # fin del clear
  
  #  if (estado == 1) then
  #    @bmonstruo.hide()
  #    @bcombatir.show()
  #    @bsiguiente.hide()
  #  elsif (estado == 2) then
  #  
  #  elsif (estado == 3) then
  #    
  #  end
end

def player_names_capture(napakalaki)
  # array para los nombres de los jugadores
  nombres = Array.new
  # estas variables son para poder centrar el cuadro
  # donde se piden los nombres al centro de la ventana
  # serán las que indican el tamaño de los campos
  ancho = $ANCHO/3
  alto = $ALTO/3
  # ponemos un color par saber los limites
  background lightgrey
  # contenedor horizontal que tiene los campos
  flow(width: width, height: height, margin: [width/2-ancho/2, height/2-alto/2, 0, 0]) do
    # contenedor vertical que contiene los campos
    stack(width: ancho, height: alto, margin: 5, scroll: false) do
      border fuchsia
      caption "Nombre Jugador 1"
      jugador1 = edit_line width: ancho*0.9, margin: [5,0,5,0]
      caption "Nombre Jugador 2"
      jugador2 = edit_line width: ancho*0.9, margin: [5,0,5,0]
      stack(height: alto/6) do end
      flow(width: ancho, height: alto/4) do # comienza el cajon
        button "Comenzar" do
          # metemos el valor del campo 1 en el array de nombres
          nombres << jugador1.text()
          # metemos el valor del campo 2 en el array de nombres
          nombres << jugador2.text()
          binding.pry if $DEBUGMODE
          # inicializamos el juego
          napakalaki.initGame(nombres)
          # actualizamos la vista con los datos recibidos
          actualiza(napakalaki)
          #alert("He pulsado el boton y llego a actualiza")
        end
        flow(width: ancho/2) do end
        button "Cancelar" do
          close
          #exit()
          #Kernel.exit
        end
      end # termina el cajon
    end
  end
end

#
# @!atribute [r] DEBUGMODE
#   @return [Boolean] Con esto ponemos en funcionamiento pry para debug.
#   Verdadero si el modo de depuración esta activado falso en caso contrario.
#
$DEBUGMODE = false
#
# @!atribute [r] ANCHO
#   @return [int] Con esto asignamos el tamaño que queremos que tenga la ventana
#   principal del juego
#
$ANCHO = 1100
#
# @!atribute [r] ALTO
#   @return [int] Con esto asignamos el tamaño que queremos que tenga la ventana
#   principal del juego
#
$ALTO = 650
#
# @!atribute [r] DIRSRC
#   @return [String] Contiene la dirección o ruta del fichero ruby que estamos
#   ejecutando en este momento
#
DIRSRC = File.dirname(File.absolute_path(__FILE__))
#
# @!atribute [r] BD_MONSTRUOS
#   @return [String] Contiene la dirección o ruta del fichero con la base de 
#   datos de los parametros de los monstruos
#
$BD_MONSTRUOS   ="#{DIRSRC}/resources/base_datos_monstruos.txt"
#
# @!atribute [r] IMAGES_MONSTRUOS
#   @return [String] Contiene la dirección o ruta de los ficheros de imagenes
#   que se usan para las cartas de los monstruos
#
$IMAGENES_MONSTRUOS   ="#{DIRSRC}/resources/monstruos/"
#
# @!atribute [r] BD_TESOROS
#   @return [String] Contiene la dirección o ruta del fichero con la base de 
#   datos de los parametros de los tesoros
#
$BD_TESOROS   ="#{DIRSRC}/resources/base_datos_tesoros.txt"
#
# @!atribute [r] IMAGES_TESOROS
#   @return [String] Contiene la dirección o ruta de los ficheros de imagenes
#   que se usan para las cartas de los tesoros
#
$IMAGENES_TESOROS   ="#{DIRSRC}/resources/tesoros/"
#
# @!atribute [r] BD_SECTARIOS
#   @return [String] Contiene la dirección o ruta del fichero con la base de 
#   datos de los parametros de las cartas para los jugadores sectarios
#
$BD_SECTARIOS   ="#{DIRSRC}/resources/base_datos_sectarios.txt"
#
# @!atribute [r] ICON
#   @return [String] Contiene la dirección o ruta del fichero de imagen que
#   usamos para el Icono del SystemTray de la aplicación
#
ICON   ="#{DIRSRC}/resources/spyke.png"

Shoes.app(title: "Juego Napakalaki", width: $ANCHO, height: $ALTO, resizable: false, scroll: false) do
  # Hago la extencion del podulo para poder utilizar el modelo
  extend NapakalakiGame
  # lo primero que llamo es a la pantalla de captura de nombres
  player_names_capture(NapakalakiGame::Napakalaki.instance)
  systray do
    syst_icon  ICON
    syst_add_button "Edit configuration"  do |state| alert("Edit configuration checkButon: ") end
    syst_add_sepratator
    syst_add_check "Option"               do |state| alert("Test checkButon: ")  end
    syst_quit_button true
  end
end