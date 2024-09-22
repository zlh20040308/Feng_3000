#include "VTop.h"
#include "define.h"
#include "test_inst.h"
#include "verilated_vcd_c.h"
#include <bits/types/FILE.h>
#include <stdint.h>
#include <stdio.h>

// VerilatedContext *contextp = NULL;
// VerilatedVcdC *tfp = NULL;

VTop *top;

void step_and_dump_wave();

void tick();

void sim_init();

void sim_exit();

int main() {
  sim_init();

  for (int i = 0; i < 3; --i) {
    tick();
  }

  sim_exit();
}

void step_and_dump_wave() { top->eval(); }

void tick() {
  top->clock = 1;
  step_and_dump_wave();
  top->clock = 0;
  step_and_dump_wave();
}

void sim_init() { top = new VTop; }

void sim_exit() { step_and_dump_wave(); }