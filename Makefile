TOPNAME = Decode
INC_PATH ?=

VERILATOR = verilator
VERILATOR_CFLAGS += -MMD --build --trace -cc  \
				-O3 --x-assign fast --x-initial fast --noassert

BUILD_DIR = ./build
OBJ_DIR = $(BUILD_DIR)/obj_dir
BIN = $(BUILD_DIR)/$(TOPNAME)
SIMBIN = $(BIN)_sim
VSRCS_DIR = $(BUILD_DIR)/vsrc
Feng_3000_HOME = $(shell pwd)


# project source
VSRCS = $(shell find $(abspath ./$(BUILD_DIR)) -name "*.sv")
CSRCS = $(shell find $(abspath ./csrc) -name "*.c" -or -name "*.cc" -or -name "*.cpp")
INC_PATH = CSRCS

SIMCSRCS = $(CSRCS)

$(shell mkdir -p $(BUILD_DIR))


# rules for verilator
INCFLAGS = $(addprefix -I, $(INC_PATH))
CXXFLAGS += $(INCFLAGS) -DTOP_NAME="\"V$(TOPNAME)\""


default: verilog

verilog:
	mkdir -p $(BUILD_DIR)
	-rm $(BUILD_DIR)/* -r
	mill -i Feng_3000.runMain Feng_3000.Elaborate --target-dir $(BUILD_DIR)
	# mill -i  Feng_3000.Elaborate --target-dir $(BUILD_DIR)
	
init:
	git submodule update --init --recursive


help:
	mill -i Feng_3000.runMain Feng_3000.Elaborate --help
	# mill -i  Feng_3000.Elaborate --help


clean:
	-rm -rf $(BUILD_DIR)

$(SIMBIN): $(VSRCS) $(SIMCSRCS)
	rm -rf $(OBJ_DIR)
	rm -rf ./*.vcd
	$(VERILATOR) $(VERILATOR_CFLAGS) \
		-I$(abspath ./csrc)/ --top-module $(TOPNAME) $^ \
		$(addprefix -CFLAGS , $(CXXFLAGS)) $(addprefix -LDFLAGS , $(LDFLAGS)) \
		--Mdir $(OBJ_DIR) --exe -o $(abspath $(BIN))


all: default

sim: $(SIMBIN)
	@$(BIN)
	# @gtkwave waveform.vcd


.PHONY: default all sim $(BIN) run clean init help verilog
