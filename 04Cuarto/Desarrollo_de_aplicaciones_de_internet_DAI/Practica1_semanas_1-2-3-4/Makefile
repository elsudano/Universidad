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
VAGRANT_COMMAND = vagrant
FABRIC_COMMAND = fab
ANSIBLE_COMMAND = ansible
SSH_COMMAND = ssh
SED_COMMAND = sed

###############################################################################
# Cliente web para linea de comandos
WEBCLI = curl

###############################################################################
# Configuración para las configuraciones remotas
WEB_LOCAL_SRV = localhost # pruebas en local
URL_ZEIT = middlewarenextcloud-ysiglvkfgt.now.sh
WEB_ZEIT_SRV = https://${URL_ZEIT}#Pruebas en remoto
ID_RSA_FILE = ~/.ssh/id_rsa_deploying
KNOWN_FILE = ~/.ssh/known_hosts

###############################################################################
# Nombre del programa Principal
NAME = main
USER = vagrant

###############################################################################
# Comando para eliminar los ficheros
RM = rm -Rf

###############################################################################
# Indica cual es el compilador para los fuentes
COMPILER = python3.6

.PHONY: help
.SECONDARY:

all: clean help

run: provision_local

makedir:
	@mkdir ./$(BIN_DIR)

connect:
	@$(SED_COMMAND) '/$(WEB_LOCAL_SRV)/d' $(KNOWN_FILE) > $(KNOWN_FILE)
	@$(SSH_COMMAND) -i $(ID_RSA_FILE) $(USER)@$(WEB_LOCAL_SRV) -p 2222
	
stop:
	$(VAGRANT_COMMAND) halt
	
deploy_local: clean
	$(VAGRANT_COMMAND) up --no-provision

provision_local: clean deploy_local
	$(VAGRANT_COMMAND) provision

clean_local_deploy: clean
	$(VAGRANT_COMMAND) destroy --force

deploy_ext: clean
	$(NOW_COMMAND) -e USER_NEXTCLOUD=${USER_NEXTCLOUD} -e PASS_NEXTCLOUD=${PASS_NEXTCLOUD} -e DOMAIN=${DOMAIN} -e URL_BASE=${URL_BASE} -e PORT=${PORT} --public

clean_ext_deploy: clean
	$(NOW_COMMAND) rm $(NAME) -y


docu:
	@doxygen ./doc/doxys/dox_config

clean:
	$(RM) $(BIN_DIR) doc/html/* doc/latex/*

touch:
	@touch $(SRC_DIR)/*

help:
	@echo "Available targets:"
	@echo "- run		    Run Application whitout compile"
	@echo "- stop		    Stop machine and application"
	@echo "- connect	    Connect by ssh to Azure Virtual Machine"
	@echo "- deploy_local       Deploy machine and execute program in local instance"
	@echo "- provision_local    Provisioning software with ansible"
	@echo "- clean_local_deploy Clean all objects, machine and software to the local instance"
	@echo "- clean              Clean up bin directory $(BIN_DIR)"
	@echo "- deploy_ext         Execute now for deploy on Zeit.co"
	@echo "- clean_ext_deploy   Clean all instances of Zeit.co"
	@echo "- docu		    Create a documentation of project"
	@echo "- help               This info"
	@echo 
