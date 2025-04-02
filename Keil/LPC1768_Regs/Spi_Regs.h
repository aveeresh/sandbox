#ifndef SPI_REGS_H
#define SPI_REGS_H

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 DSS:4;
		uint32 FRF:2;
		uint32 CPOL:1;
		uint32 CPHA:1;
		uint32 SCR:8;
		uint32 :24;
	}B;
}Spi_SSPxCR0_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 LBM:1;
		uint32 SSE:1;
		uint32 MS:1;
		uint32 SOD:1;
		uint32 :28;
	}B;
}Spi_SSPxCR1_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 DATA:8;
		uint32 :24;
	}B;
}Spi_SSPxDR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 TFE:1;
		uint32 TNF:1;
		uint32 RNE:1;
		uint32 RFF:1;
		uint32 BSY:1;
		uint32 :27;
	}B;
}Spi_SSPxSR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 CPSDVSR:8;
		uint32 :24;
	}B;
}Spi_SSPxCPSR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RORIM:1;
		uint32 RTIM:1;
		uint32 RXIM:1;
		uint32 TXIM:1;
		uint32 :28;
	}B;
}Spi_SSPxMSC_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RORRIS:1;
		uint32 RTRIS:1;
		uint32 RXRIS:1;
		uint32 TXRIS:1;
		uint32 :28;
	}B;
}Spi_SSPxRIS_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RORMIS:1;
		uint32 RTMIS:1;
		uint32 RXMIS:1;
		uint32 TXMIS:1;
		uint32 :28;
	}B;
}Spi_SSPxMIS_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RORIC:1;
		uint32 RTIC:1;
		uint32 :30; 
	}B;
}Spi_SSPxICR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 RXDMAE:1;
		uint32 TXDMAE:1;
	}B;
}Spi_SSPxDMACR_t;

#endif
