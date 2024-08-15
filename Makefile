BUILD_DIR = ./build
Feng_3000_HOME = $(shell pwd)

default: verilog

verilog:
	mkdir -p $(BUILD_DIR)
	-rm $(BUILD_DIR)/* -r
	mill -i Feng_3000.runMain Feng_3000.Elaborate --target-dir $(BUILD_DIR)
	# mill -i  Feng_3000.Elaborate --target-dir $(BUILD_DIR)


test:
	mill -i __.test

emu: verilog
	cd $(Feng_3000_HOME)/difftest && $(MAKE)  EMU_TRACE=1  emu   
	
init:
	git submodule update --init --recursive


help:
	mill -i Feng_3000.runMain Feng_3000.Elaborate --help
	# mill -i  Feng_3000.Elaborate --help


clean:
	-rm -rf $(BUILD_DIR)

.PHONY: clean init bump bsp idea help verilog emu
