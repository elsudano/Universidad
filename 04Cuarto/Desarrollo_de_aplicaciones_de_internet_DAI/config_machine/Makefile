###############################################################################
# Variable que guarda los ficheros que hay que compilar
SRC_DIR = src/

###############################################################################
# Extraemos los nombres de los ficheros para los binarios
BIN_DIR = bin/

###############################################################################
# Variables que no cambian para cualquier compilación
# Extención de los ficheros fuente
EXT = py

###############################################################################
# ruta del comando cliente para el despliegue en Zeit.co
NOW_COMMAND = /home/usuario/now-linux
SSH_COMMAND = ssh
SED_COMMAND = sed

###############################################################################
# Cliente web para linea de comandos
WEBCLI = curl

###############################################################################
# Configuración para las configuraciones remotas
WEB_LOCAL_SRV = localhost:# pruebas en local
URL_ZEIT = middlewarenextcloud-ysiglvkfgt.now.sh
WEB_ZEIT_SRV = https://${URL_ZEIT}#Pruebas en remoto
ID_RSA_FILE = ~/.ssh/id_rsa_deploying
KNOWN_FILE = ~/.ssh/known_hosts

###############################################################################
# Nombre del programa Principal
NAME = main

###############################################################################
# Comando para eliminar los ficheros
RM = rm -Rf

###############################################################################
# Indica cual es el compilador para los fuentes
COMPILER = python3.6

.PHONY: help
.SECONDARY:

all: clean help

makedir:
	@mkdir ./$(BIN_DIR)

run:
	$(COMPILER) $(SRC_DIR)$(NAME).$(EXT)

build: clean makedir 
	$(COMPILER) build -o $(BIN_DIR)$(NAME) $(SRC_DIR)*.$(EXT)

deploy: clean
	$(NOW_COMMAND) -e USER_NEXTCLOUD=${USER_NEXTCLOUD} -e PASS_NEXTCLOUD=${PASS_NEXTCLOUD} -e DOMAIN=${DOMAIN} -e URL_BASE=${URL_BASE} -e PORT=${PORT} --public

connect:
	@$(SED_COMMAND) '/$(URL_AZURE)/d' $(KNOWN_FILE) > $(KNOWN_FILE)
	@$(SSH_COMMAND) -i $(ID_RSA_FILE) $(URL_AZURE)


clean_deploy: clean
	$(NOW_COMMAND) rm $(NAME) -y

tests: clean
	$(COMPILER) test $(SRC_DIR)*_test.$(EXT)

test_status: clean
	@echo "LOCAL"
	$(WEBCLI) $(WEB_LOCAL_SRV)${PORT}/status
	@echo "HEROKU"
	$(WEBCLI) $(WEB_HEROKU_SRV)/status
	@echo "ZEIT"
	$(WEBCLI) $(WEB_ZEIT_SRV)/status
	@echo "AZURE"
	$(WEBCLI) $(WEB_AZURE_SRV)/status

test_list: clean
	$(WEBCLI) $(WEB_LOCAL_SRV)${PORT}/list | jq

test_show: clean
	$(WEBCLI) $(WEB_LOCAL_SRV)${PORT}/show/$(ID) | jq

test_new: clean
	$(WEBCLI) $(WEB_LOCAL_SRV)${PORT}/new $(STRING2)

test_update: clean
	$(WEBCLI) $(WEB_LOCAL_SRV)${PORT}/update $(ID)

test_delete: clean
	$(WEBCLI) $(WEB_LOCAL_SRV):${PORT}/delete $(STRING1)

docu:
	@doxygen ./doc/doxys/dox_config

clean:
	$(RM) $(BIN_DIR) doc/html/* doc/latex/*

touch:
	@touch $(SRC_DIR)/*

help:
	@echo "Available targets:"
	@echo "- run          Run Application whitout compile"
	@echo "- build        Generate bin file on directory $(BIN_DIR)"
	@echo "- deploy       Execute now for deploy on Zeit.co"
	@echo "- connect      Connect by ssh to Azure Virtual Machine"
	@echo "- clean_deploy Clean all instances of Zeit.co"
	@echo "- tests        Run tests"
	@echo "- test_status  Run test for status command in all containers"
	@echo "- test_list    Run test for list command of webservice"
	@echo "- test_show    Run test for show command of webservice"
	@echo "- test_new     Run test for new command of webservice"
	@echo "- test_update  Run test for update command of webservice"
	@echo "- test_delete  Run test for delete command of webservice"
	@echo "- docu         Create a documentation of project"
	@echo "- clean        Clean up the source directory $(SRC_DIR) and bin directory $(BIN_DIR)"
	@echo "- help         This info"
	@echo 
