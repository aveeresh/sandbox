#ifndef CAN_REGS_H
#define CAN_REGS_H

/* 
· Document Title: OBD Stack
· Version: V1.0
· Date: 31/3/25
· Author: Akshay Naik
*/

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 TS1:1;
		uint32 TS2:1;
		uint32 :6;
		uint32 TBS1:1;
		uint32 TBS2:1;
		uint32 :6;
		uint32 TCS1:1;
		uint32 TCS2:1;
		uint32 :14;
	}B;
}Can_TxSR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RS1:1;
		uint32 RS2:1;
		uint32 :6;
		uint32 RB1:1;
		uint32 RB2:1;
		uint32 :6;
		uint32 DOS1:1;
		uint32 DOS2:1;
		uint32 :14;
	}B;
}Can_RxSR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 E1:1;
		uint32 E2:1;
		uint32 :6;
		uint32 BS1:1;
		uint32 BS2:1;
		uint32 :22;
	}B;
}Can_MSR_t;


typedef union
{
	uint32 R;
	struct 
	{
		uint32 RM:1;
		uint32 LOM:1;
		uint32 STM:1;
		uint32 TPM:1;
		uint32 SM:1;
		uint32 RPM:1;
		uint32 :1;
		uint32 TM:1;
		uint32 :24;
	}B;
} Can_MOD_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 TR:1;
		uint32 AT:1;
		uint32 RRB:1;
		uint32 CDO:1;
		uint32 SRR:1;
		uint32 STB1:1;
		uint32 STB2:1;
		uint32 STB3:1;
		uint32 :24;
	}B;
}Can_CMR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RBS:1;
		uint32 DOS:1;
		uint32 TBS:1;
		uint32 TCS:1;
		uint32 RS:1;
		uint32 TS:1;
		uint32 ES:1;
		uint32 BS:1;
		uint32 :8;
		uint32 RXERR:8;
		uint32 TXERR:8;
	}B;
}Can_GSR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RI:1;
		uint32 TI1:1;
		uint32 EI:1;
		uint32 DOI:1;
		uint32 WUI:1;
		uint32 EPI:1;
		uint32 ALI:1;
		uint32 BEI:1;
		uint32 IDI:1;
		uint32 TI2:1;
		uint32 TI3:1;
		uint32 :5;
		uint32 ERRBIT:5;
		uint32 ERRDIR:1;
		uint32 ERRC1:2;
		uint32 ALCBIT:8;
	}B;
}Can_ICR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RIE:1;
		uint32 TIE1:1;
		uint32 EIE:1;
		uint32 DOIE:1;
		uint32 WUIE:1;
		uint32 EPIE:1;
		uint32 ALIE:1;
		uint32 BEIE:1;
		uint32 IDIE:1;
		uint32 TIE2:1;
		uint32 TIE3:1;
		uint32 :21;
	}B;
}Can_IER_t;

typedef union 
{
	uint32 R;
	struct
	{
		uint32 BRP:10;
		uint32 :4;
		uint32 SJW:2;
		uint32 TSEG1:4;
		uint32 TSEG2:3;
		uint32 SAM:1;
		uint32 :8;
	}B;
} Can_BTR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 EWL:8;
		uint32 :24;
	}B;
}Can_EWL_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RBS:1;
		uint32 DOS:1;
		uint32 TBS1:1;
		uint32 TCS1:1;
		uint32 RS:1;
		uint32 TS1:1;
		uint32 ES:1;
		uint32 BS:1;
		uint32 RBS1:1;
		uint32 DOS1:1;
		uint32 TBS2:1;
		uint32 TCS2:1;
		uint32 RS1:1;
		uint32 TS2:1;
		uint32 ES1:1;
		uint32 BS1:1;
		uint32 RBS2:1;
		uint32 DOS2:1;
		uint32 TBS3:1;
		uint32 TCS3:1;
		uint32 RS2:1;
		uint32 TS3:1;
		uint32 ES3:1;
		uint32 BS3:1;
		uint32 :8;
		
	}B;
}Can_SR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 IdIdx:10;
		uint32 BP:1;
		uint32 :5;
		uint32 DLC:4;
		uint32 :10;
		uint32 RTR:1;
		uint32 FF:1;
	}B;
}Can_RFS_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 Id:11;
		uint32 :21;
	}StdId;
	struct
	{
		uint32 Id:29;
		uint32 :3;
	}ExtId;
}Can_RID_t;

typedef union
{
	uint32 R;
	struct
	{
		uint8 Data1:8;
		uint8 Data2:8;
		uint8 Data3:8;
		uint8 Data4:8;
	}B;
}Can_RDA_t;

typedef union
{
	uint32 R;
	struct
	{
		uint8 Data5:8;
		uint8 Data6:8;
		uint8 Data7:8;
		uint8 Data8:8;
	}B;
}Can_RDB_t;

typedef union
{
	uint32 R;
	struct 
	{
		uint32 PRIO:8;
		uint32 :8;
		uint32 DLC:4;
		uint32 :10;
		uint32 RTR:1;
		uint32 FF:1;
	}B;
}Can_TFI_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 Id:11;
		uint32 :21;
	}StdId;
	struct
	{
		uint32 Id:29;
		uint32 :3;
	}ExtId;
}Can_TID_t;

typedef union
{
	uint32 R;
	struct
	{
		uint8 Data1:8;
		uint8 Data2:8;
		uint8 Data3:8;
		uint8 Data4:8;
	}B;
}Can_TDA_t;

typedef union
{
	uint32 R;
	struct
	{
		uint8 Data5:8;
		uint8 Data6:8;
		uint8 Data7:8;
		uint8 Data8:8;
	}B;
}Can_TDB_t;

typedef struct
{
	uint32 R;
	struct
	{
		uint32 :1;
		uint32 CAN1SLEEP:1;
		uint32 CAN2SLEEP:1;
		uint32 :29;
	}B;
}Can_CANSLEEPCLR_t;

typedef struct
{
	uint32 R;
	struct
	{
		uint32 :1;
		uint32 CAN1WAKE:1;
		uint32 CAN2WAKE:1;
		uint32 :29;
	}B;
}Can_CANWAKEFLAGS_t;	

/* 
 * Define register collections here 
 *
*/
typedef struct 
{
	volatile Can_AFMR_t AFMR;
	volatile uint32 SFF_sa;
} Can_AccpFltrRegs_t;

typedef struct 
{
	volatile Can_TxSR_t CANTxSR;
	volatile Can_RxSR_t CANRxSR;
	volatile Can_MSR_t CANMSR;
}Can_CentralRegs_t;

typedef struct 
{
	volatile Can_MOD_t MOD;
	volatile Can_CMR_t CMR;
	volatile Can_GSR_t GSR;
	volatile Can_ICR_t ICR;
	volatile Can_IER_t IER;
	volatile Can_BTR_t BTR;
	volatile Can_EWL_t EWL;
	volatile Can_SR_t SR;
	volatile Can_RFS_t RFS;
	volatile Can_RID_t RID;
	volatile Can_RDA_t RDA;
	volatile Can_RDB_t RDB;
	volatile Can_TFI_t TFI1;
	volatile Can_TID_t TID1;
	volatile Can_TDA_t TDA1;
	volatile Can_TDB_t TDB1;
	volatile Can_TFI_t TFI2;
	volatile Can_TID_t TID2;
	volatile Can_TDA_t TDA2;
	volatile Can_TDB_t TDB2;
	volatile Can_TFI_t TFI3;
	volatile Can_TID_t TID3;
	volatile Can_TDA_t TDA3;
	volatile Can_TDB_t TDB3;
}Can_GeneralRegs_t;

#endif
