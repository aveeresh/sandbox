#ifndef I2C_REGS_H
#define I2C_REGS_H

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 AA:1;
		uint32 SI:1;
		uint32 STO:1;
		uint32 STA:1;
		uint32 I2EN:1;
		uint32 :27;
	}B;
}I2C_I2CONSET_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :2;
		uint32 AAC:1;
		uint32 SIC:1;
		uint32 :1;
		uint32 STAC:1;
		uint32 I2ENC:1;
		uint32 :25;
	}B;
}I2C_I2CONCLR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :2;
		uint32 STATUS:5;
		uint32 :24;
	}B;
}I2C_I2STAT_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 DATA:8;
		uint32 :24;
	}B;
}I2C_I2DAT_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 MM_ENA:1;
		uint32 ENA_SCL:1;
		uint32 MATCH_ALL:1;
		uint32 :29;
	}B;
}I2C_I2MMCTRL_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 DATA:8;
		uint32 :24;
	}B;
}I2C_I2DATA_BUFFER_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 GC:1;
		uint32 ADDR:7;
		uint32 :24;
	}B;
}I2C_I2ADRx_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :1;
		uint32 MASK:7;
		uint32 :24;
	}B;
}I2C_I2MASKx_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 SCLH:16;
		uint32 :16;
	}B;
}I2C_I2SCLH_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 SCLL:16;
		uint32 :16;
	}B;
}I2C_I2SCLL_t;

#endif
