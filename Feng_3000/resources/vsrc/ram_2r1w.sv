import "DPI-C" function void ram_write_helper
(
  input  int    wIdx,
  input  int    wdata,
  input  int    wmask,
  input  bit        wen
);

import "DPI-C" function int ram_read_helper
(
  input  bit        en,
  input  int    rIdx
);

module ram_2r1w (
    input         clk,

    input  [31:0] imem_addr,
    output [31:0] imem_inst,

    input  [31:0] dmem_addr,
    output [31:0] dmem_rdata,
  );

  wire [31:0] imem_inst_0 = ram_read_helper(imem_en, {3'b000, (imem_addr - 64'h0000_0000_8000_0000) >> 3});

  assign imem_inst = {32'b0000_0000_0000_0000, (imem_addr[2] ? imem_inst_0[31:32] : imem_inst_0[31:0])};

  assign dmem_rdata = ram_read_helper(dmem_en, {3'b000, (dmem_addr-64'h0000_0000_8000_0000) >> 3});

  always @(posedge clk)
  begin
    ram_write_helper((dmem_addr - 64'h0000_0000_8000_0000) >> 3, dmem_wdata, dmem_wmask, dmem_en & dmem_wen);
  end

endmodule
