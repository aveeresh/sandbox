#ifndef DAC_REGS_H
#define DAC_REGS_H

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 :6;
		uint32 VALUE:10;
		uint32 BIAS:1;
		uint32 :15;
	}B;
}Dac_DACR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 INT_DMA_REQ:1;
		uint32 DBLBUF_ENA:1;
		uint32 CNT_ENA:1;
		uint32 DMA_ENA:1;
		uint32 :28;
	}B;
}Dac_DACCTRL_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 VALUE:16;
		uint32 :16;
	}B;
}Dac_DACCNTVAL_t;

/* 
 * Define register collections here 
 *
*/
typedef struct
{
	Dac_DACR_t DACR;
	Dac_DACCTRL_t DACCTRL;
	Dac_DACCNTVAL_t DACCNTVAL;
}Dac_Regs_t;	
	
#endif
