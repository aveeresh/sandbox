#ifndef ETH_REGS_H
#define ETH_REGS_H

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 RX_ENBL:1;
		uint32 PASS_ALL_FRAMES:1;
		uint32 RX_FLOW_CTRL:1;
		uint32 TX_FLOW_CTRL:1;
		uint32 LOOPBACK:1;
		uint32 :3;
		uint32 RESET_TX:1;
		uint32 RESET_MCS_TX:1;
		uint32 RESET_RX:1;
		uint32 RESET_MCS_RX:1;
		uint32 :2;
		uint32 SIMULATION_RESET:1;
		uint32 SOFT_RESET:1;
		uint32 :16;
	}B;
}Eth_MAC1_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 FULL_DUPLEX:1;
		uint32 FRM_LEN_CHECK:1;
		uint32 HUGE_FRM_EN:1;
		uint32 DELAYED_CRC:1;
		uint32 CRC_EN:1;
		uint32 PAD_CRC_EN:1;
		uint32 VLAN_PAD_EN:1;
		uint32 AUTO_DETECT_PAD_EN:1;
		uint32 PURE_PREAMBLE_ENFRCMNT:1;
		uint32 LONG_PREAMBLE_ENFRCMNT:1;
		uint32 :2;
		uint32 NO_BACKOFF:1;
		uint32 BACK_PRESSURE_NO_BACKOFF:1;
		uint32 EXCESS_DEFER:1;
		uint32 :16;
	}B;
}Eth_MAC2_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 BACK_TO_BACK_INTER_PACKET_GAP:7;
		uint32 :24;
	}
}Eth_IPGT_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 NON_BACK_TO_BACK_INTER_PACKET_GAP_PART2:7;
		uint32 :1;
		uint32 NON_BACK_TO_BACK_INTER_PACKET_GAP_PART1:7;
		uint32 :24;
	}B;
}Eth_IPGR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RETRANSMISSION_MAX:4;
		uint32 :4;
		uint32 COL_WINDOW:6;
		uint32 :18;
	}B;
}Eth_CLRT_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 MAX_FRM_LEN:16;
		uint32 :24;
	}B;
}Eth_MAXF_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :8;
		uint32 SPEED:1;
		uint32 :23;
	}B;
}Eth_SUPP_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 SHORTCUT_PAUSE_QUANTA:1;
		uint32 TEST_PAUSE:1;
		uint32 TEST_BACKPRESSURE:1;
	}B;
}Eth_TEST_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 SCAN_INCREMENT:1;
		uint32 SUPPRESS_PREAMBLE:1;
		uint32 :9;
		uint32 RESET_MII_MGMT:1;
		uint32 :24;
	}B;
}Eth_MCFG_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 READ:1;
		uint32 SCAN:1;
	}B;
}Eth_MCMD_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 REGISTER_ADDR:5;
		uint32 :3;
		uint32 PHY_ADDR:5;
		uint32 :19;
	}B;
}Eth_MADR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 WRITE_DATA:16;
		uint32 :24;
	}B;
}Eth_MWTD_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 READ_DATA:16;
		uint32 :24;
	}B;
}Eth_MRDD_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 BUSY:1;
		uint32 SCANNING:1;
		uint32 NOT_VALID:1;
		uint32 MII_LNK_FAIL:1;
		uint32 :28;
	}B;
}Eth_MIND_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 STATION_ADDR_OCTET2:8;
		uint32 STATION_ADDR_OCTET1:8;
		uint32 :16;
	}B;
}Eth_SA0_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 STATION_ADDR_OCTET4:8;
		uint32 STATION_ADDR_OCTET3:8;
		uint32 :16;
	}B;
}Eth_SA1_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 STATION_ADDR_OCTET6:8;
		uint32 STATION_ADDR_OCTET5:8;
		uint32 :16;
	}B;
}Eth_SA2_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RxEn:1;
		uint32 TxEn:1;
		uint32 :1;
		uint32 RegReset:1;
		uint32 TxReset:1;
		uint32 RxReset:1;
		uint32 PassRuntFrame:1;
		uint32 PassRxFltr:1;
		uint32 TxFlowCtrl:1;
		uint32 RMII:1;
		uint32 FullDuplex:1;
		uint32 :21;
	}B;
}Eth_CMD_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RxStatus:1;
		uint32 TxStatus:1;
		uint32 :30;
	}B;
}Eth_STAT_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :2;
		uint32 RxDesc:30;
	}B;
}Eth_RxDesc_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :3;
		uint32 RxStatus:29;
	}B;
}Eth_RxStatus_t;	
	
typedef union
{
	uint32 R;
	struct
	{
		uint32 RxDescNum:16;
		uint32 :16;
	}B;
}Eth_RxDescNum_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RxProduceIdx:16;
		uint32 :16;
	}B;
}Eth_RxProduceIdx_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RxConsumeIdx:16;
		uint32 :16;
	}B;
}Eth_RxConsumeIdx_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :2;
		uint32 TxDesc:30;
	}B;
}Eth_TxDesc_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :2;
		uint32 TxStatus:30;
	}B;
}Eth_TxStatus_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 TxDescNum:16;
		uint32 :16;
	}B;
}Eth_TxDescNum_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 TxProduceIdx:16;
		uint32 :16;
	}B;
}Eth_TxProduceIdx_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 TxConsumeIdx:16;
		uint32 :16;
	}B;
}Eth_TxConsumeIdx_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 CRCErr:1;
		uint32 LenCheckErr:1;
		uint32 LenOutOfRange:1;
		uint32 Done:1;
		uint32 Multicast:1;
		uint32 Broadcast:1;
		uint32 PacketDefer:1;
		uint32 ExcessiveDefer:1;
		uint32 ExcessiveCollision:1;
		uint32 LateCollision:1;
		uint32 Giant:1;
		uint32 Underrun:1;
		uint32 TotalBytes:16;
		uint32 ControlFrame:1;
		uint32 Pause:1;
		uint32 Backpressure:1;
		uint32 VLAN:1;
	}B;
}Eth_TSV0_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 TxByteCount:16;
		uint32 TxCollisionCount:4;
		uint32 :12;
	}B;
}Eth_TSV1_t;
#endif