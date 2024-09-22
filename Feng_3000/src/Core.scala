package Feng_3000

import chisel3._
import chisel3.util._
import Consts._

object Instructions {
  val LW = BitPat("b?????????????????010?????0000011")
}

import Instructions._

class Core extends Module {
  val io = IO(new Bundle {
    val imem = Flipped(new ImemPort())
    val dmem = Flipped(new DmemPort())
    val exit = Ouput(Bool())
    val gp   = Output(UInt(WORD_LEN.W))

  })

  val regfile = Mem(32, UInt(WORD_LEN.W))

  val pc_reg = RegInit(START_ADDR)
  pc_reg := pc_reg + 4.U(WORD_LEN.W)

  io.imem.addr := pc_reg
  val inst = io.imem.inst

  val imm_i      = inst(31, 20)
  val imm_i_sext = Cat(Fill(20, imm_i(11), imm_i))

  val rs1_addr = inst(19, 15)
  val rs2_addr = inst(24, 20)
  val wb_addr  = inst(11, 7)

  // EX

  val alu_out = MuxCase(
    0.U(WORD_LEN.W),
    Seq(
      (inst === LW) -> (rs1_data + imm_i_sext)
    )
  )

  // MEM
  io.dmem.addr := alu_out

  when(inst === LW) {
    io.dmem.addr := alu_out
  }

  // WB
  val wb_data = io.data.rdata
  when(inst === LW) {
    regfile(wb_addr) := wb_data
  }

  // 当 inst 为 "34333231" (读取程序最后一行) 时，设定 exit 信号为 true.B
  io.exit := (inst === 0x34333231.U(WORD_LEN.W))

}
