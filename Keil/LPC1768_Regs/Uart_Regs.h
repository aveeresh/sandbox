#ifndef UART_REGS_H
#define UART_REGS_H

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 RBR:8;
		uint32 :24;
	}B;
}Uart_RBR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 THR:8;
		uint32 :24;
	}B;
}Uart_THR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 DLLSB:8;
		uint32 :24;
	}B;
}Uart_DLL_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 DLMSB:8;
		uint32 :24;
	}B;
}Uart_DLM_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RBREn:1;
		uint32 THREEn:1;
		uint32 RXLineStat:1;
		uint32 :5;
		uint32 ABEOIntEn:1;
		uint32 ABTOIntEn:1;
		uint32 :22;
	}B;
}Uart_IER_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 IntStatus:1;
		uint32 IntId:3;
		uint32 :2;
		uint32 FIFOEn:2;
		uint32 ABEOInt:1;
		uint32 ABTOInt:1;
		uint32 :22;
	}B;
}Uart_IIR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 FIFOEn:1;
		uint32 RxFIFORst:1;
		uint32 TxFIFORst:1;
		uint32 DMAModeSelect:1;
		uint32 :2;
		uint32 RxTrigLvl:2;
		uint32 :24;
	}B;
}Uart_FCR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 WordLenSel:2;
		uint32 StpBitSel:1;
		uint32 ParEn:1;
		uint32 ParSel:2;
		uint32 BrkCtrl:1;
		uint32 DLAB:1;
		uint32 :24;
	}B;
}Uart_LCR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RDR:1;
		uint32 OE:1;
		uint32 PE:1;
		uint32 FE:1;
		uint32 BI:1;
		uint32 THRE:1;
		uint32 TEMT:1;
		uint32 RXFE:1;
		uint32 :24;
	}B;
}Uart_LSR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 Pad:8;
		uint32 :24;
	}B;
}Uart_SCR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 Start:1;
		uint32 Mode:1;
		uint32 AutoRestart:1;
		uint32 :5;
		uint32 ABEOIntClr:1;
		uint32 ABTOIntClr:1;
		uint32 :22;
	}B;
}Uart_ACR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 IrDAEn:1;
		uint32 IrDAInv:1;
		uint32 FixPulseEn:1;
		uint32 PulseDiv:3;
		uint32 :26;
	}B;
}Uart_ICR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 DIVADDVAL:4;
		uint32 MULVAL:4;
		uint32 :24;
	}B;
}Uart_FDR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :7;
		uint32 TXEN:1;
	}B;
}Uart_TER_t;

/* 
 * Define register collections here 
 *
*/
typedef struct
{
	union
	{
		Uart_RBR_t RBR;
		Uart_THR_t THR;
		Uart_DLL_t DLL;
	}RBR_THR_DLL;
	union
	{
		Uart_DLM_t DLM;
		Uart_IER_t IER;
	}DLM_IER;
	union
	{
		Uart_IIR_t IIR;
		Uart_FCR_t FCR;
	}IIR_FCR;
	Uart_LCR_t LCR;
	uint32 Rsvd1[1];
	Uart_LSR_t LSR;
	Uart_SCR_t SCR;
	Uart_ACR_t ACR;
	Uart_ICR_t ICR;
	Uart_FDR_t FDR;
	uint32 Rsvd2[1];
	Uart_TER_t TER;
}Uart_Regs_t;
	
#endif
