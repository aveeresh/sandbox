#ifndef ADC_REGS_H
#define ADC_REGS_H

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 SEL:8;
		uint32 CLKDIV:8;
		uint32 BURST:1;
		uint32 :4;
		uint32 PDN:1;
		uint32 :2;
		uint32 START:3;
		uint32 EDGE:1;
		uint32 :4;
	}B;
}Adc_ADCR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :4;
		uint32 RESULT:12;
		uint32 :8;
		uint32 CHN:3;
		uint32 :3;
		uint32 OVERRUN:1;
		uint32 DONE:1;
	}B;
}Adc_ADGDR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 ADINTEN0:1;
		uint32 ADINTEN1:1;
		uint32 ADINTEN2:1;
		uint32 ADINTEN3:1;
		uint32 ADINTEN4:1;
		uint32 ADINTEN5:1;
		uint32 ADINTEN6:1;
		uint32 ADINTEN7:1;
		uint32 ADGINTEN:1;
		uint32 :23;
	}B;
}Adc_ADINTEN_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :4;
		uint32 RESULTx:12;
		uint32 :14;
		uint32 OVERRUNx:1;
		uint32 DONEx:1;
	}B;
}Adc_ADDRx_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 DONE0:1;
		uint32 DONE1:1;
		uint32 DONE2:1;
		uint32 DONE3:1;
		uint32 DONE4:1;
		uint32 DONE5:1;
		uint32 DONE6:1;
		uint32 DONE7:1;
		uint32 OVERRUN0:1;
		uint32 OVERRUN1:1;
		uint32 OVERRUN2:1;
		uint32 OVERRUN3:1;
		uint32 OVERRUN4:1;
		uint32 OVERRUN5:1;
		uint32 OVERRUN6:1;
		uint32 OVERRUN7:1;
		uint32 ADINT:1; 
		uint32 :15;
	}B;
}Adc_ADSTAT_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :4;
		uint32 ADCOFFS:4;
		uint32 TRIM:4;
		uint32 :20;
	}B;
}Adc_ADTRM_t;

/* 
 * Define register collections here 
 *
*/
typedef struct
{
	Adc_ADCR_t ADCR;
	Adc_ADGDR_t ADGDR;
	uint32 Reserved;
	Adc_ADINTEN_t ADINTEN;
	Adc_ADDRx_t ADDRx[8];
	Adc_ADSTAT_t ADSTAT;
	Adc_ADTRM_t ADTRM;
}Adc_Regs_t;

#endif
