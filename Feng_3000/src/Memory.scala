package Feng_3000

import chisel3._
import chisel3.util._

import Consts._

class ImemPort extends Bundle {
  val addr = Input(UInt(WORD_LEN.W))
  val inst = Output(UInt(WORD_LEN.W))
}

class DmemPort extends Bundle {
  val addr  = Input(UInt(WORD_LEN.W))
  val rdata = Output(UInt(WORD_LEN.W))
}

class ram_2r1w extends BlackBox with HasBlackResource {
  val io = IO(new Bundle {
    val clk = Input(Clock())

    val imem_addr = Input(UInt(WORD_LEN.W))
    val imem_inst = Output(UInt(WORD_LEN.W))

    val dmem_addr  = Input(UInt(WORD_LEN.W))
    val dmem_rdata = Output(UInt(WORD_LEN.W))
  })

  addResource("/vsrc/ram_2r1w.sv")
}

class Memory extends Module {
  val io = IO(new Bundle {
    val imem = new ImemPort()
    val dmem = new DmemPort()
  })

  val mem = Module(new ram_2r1w())
  mem.io.clk       := clock
  mem.io.imem_addr := io.imem.addr
  io.imem.inst     := mem.io.inst

  mem.io.dmem_addr := io.dmem.addr
  io.dmem.addr     := mem.io.dmem_rdata
}
