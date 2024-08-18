package Feng_3000

import chisel3._
import chisel3.util._

object Config {
  object AluEnum extends ChiselEnum {
    val ALU_XXX, ALU_ADD, ALU_SUB, ALU_COPY_B, ALU_SLL, ALU_SLT, ALU_SLTU, ALU_XOR, ALU_SRL, ALU_SRA, ALU_OR, ALU_AND =
      Value
    override def getWidth: Int = ALU_XXX.asUInt.getWidth
  }

  object Control {

    object PCSelEnum extends ChiselEnum {
      val PC_4, PC_ALU, PC_0 = Value
      override def getWidth: Int = PC_4.asUInt.getWidth

    }

    object ASelEnum extends ChiselEnum {
      val A_XXX, A_PC, A_RS1 = Value
      override def getWidth: Int = A_XXX.asUInt.getWidth

    }

    object BSelEnum extends ChiselEnum {
      val B_XXX, B_IMM, B_RS2 = Value
      override def getWidth: Int = B_XXX.asUInt.getWidth

    }

    object ImmSelEnum extends ChiselEnum {
      val IMM_X, IMM_I, IMM_S, IMM_U, IMM_J, IMM_B = Value
      override def getWidth: Int = IMM_X.asUInt.getWidth

    }

    object BrTypeEnum extends ChiselEnum {
      val BR_XXX, BR_LTU, BR_LT, BR_EQ, BR_GEU, BR_GE, BR_NE = Value
      override def getWidth: Int = BR_XXX.asUInt.getWidth
    }

    object StTypeEnum extends ChiselEnum {
      val ST_XXX, ST_SW, ST_SH, ST_SB = Value
      override def getWidth: Int = ST_XXX.asUInt.getWidth
    }

    object LdTypeEnum extends ChiselEnum {
      val LD_XXX, LD_LW, LD_LH, LD_LB, LD_LHU, LD_LBU = Value
      override def getWidth: Int = LD_XXX.asUInt.getWidth
    }

    object WbSelEnum extends ChiselEnum {
      val WB_ALU, WB_MEM, WB_PC4, WB_CSR = Value
      override def getWidth: Int = WB_ALU.asUInt.getWidth
    }
  }

}
