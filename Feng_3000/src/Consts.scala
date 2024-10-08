package Feng_3000

import chisel3._
import chisel3.util._

object Consts {
  val WORD_LEN    = 32
  val START_ADDR  = 0.U(WORD_LEN.W)
  val EXE_FUN_LEN = 5
  val ALU_X       = 0.U(EXE_FUN_LEN.W)
  val ALU_ADD     = 1.U
  val ALU_SUB     = 2.U
  val ALU_AND     = 3.U
  val ALU_OR      = 4.U
  val ALU_XOR     = 5.U
  val ALU_SLL     = 6.U
  val ALU_SRL     = 7.U
  val ALU_SRA     = 8.U
  val ALU_SLT     = 9.U
  val ALU_SLTU    = 10.U
  val BR_BEQ      = 11.U
  val BR_BNE      = 12.U
  val BR_BLT      = 13.U
  val BR_BGE      = 14.U
  val BR_BLTU     = 15.U
  val BR_BGEU     = 16.U
  val ALU_JALR    = 17.U
  val ALU_COPY1   = 18.U
  val ALU_VADDVV  = 19.U
  val VSET        = 20.U
  val ALU_PCNT    = 21.U

  val OP1_LEN = 2
  val OP1_RS1 = 0.U(OP1_LEN.W)
  val OP1_PC  = 1.U(OP1_LEN.W)
  val OP1_X   = 2.U(OP1_LEN.W)
  val OP1_IMZ = 3.U(OP1_LEN.W)

  val OP2_LEN = 3
  val OP2_X   = 0.U(OP2_LEN.W)
  val OP2_RS2 = 1.U(OP2_LEN.W)
  val OP2_IMI = 2.U(OP2_LEN.W)
  val OP2_IMS = 3.U(OP2_LEN.W)
  val OP2_IMJ = 4.U(OP2_LEN.W)
  val OP2_IMU = 5.U(OP2_LEN.W)

  val MEM_LEN = 2
  val MEM_X   = 0.U(MEM_LEN.W)
  val MEM_S   = 1.U(MEM_LEN.W)
  val MEM_V   = 2.U(MEM_LEN.W)

  val REN_LEN = 2
  val REN_X   = 0.U(REN_LEN.W)
  val REN_S   = 1.U(REN_LEN.W)
  val REN_V   = 2.U(REN_LEN.W)

  val WB_SEL_LEN = 3
  val WB_X       = 0.U(WB_SEL_LEN.W)
  val WB_ALU     = 0.U(WB_SEL_LEN.W)
  val WB_MEM     = 1.U(WB_SEL_LEN.W)
  val WB_PC      = 2.U(WB_SEL_LEN.W)
  val WB_CSR     = 3.U(WB_SEL_LEN.W)
  val WB_MEM_V   = 4.U(WB_SEL_LEN.W)
  val WB_ALU_V   = 5.U(WB_SEL_LEN.W)
  val WB_VL      = 6.U(WB_SEL_LEN.W)

}
