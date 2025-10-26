MVN = mvn
MAIN_CLASS = com.dip.parkinglotsimulator.Main
ARGS =

.PHONY: all clean compile package rebuild run test help

all: compile ## Compile the project by default

clean: ## Clean the project
	$(MVN) clean

compile: ## Compile the project without packaging
	$(MVN) compile

recompile: ## Clean and recompile
	$(MVN) clean compile

package: ## Build the JAR
	$(MVN) package

rebuild: ## Clean and recompile everything
	$(MVN) clean package

run: ## Run the application (MAIN_CLASS must be set)
	$(MVN) exec:java -Dexec.mainClass=$(MAIN_CLASS) -Dexec.args="$(ARGS)"

help: ## Show this help
	@echo "Usage: make [target]"
	@echo ""
	@echo "Available targets:"
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-12s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)
