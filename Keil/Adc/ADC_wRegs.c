#include <lpc17xx.h>

#include "ADC_driver.h"
#include "Adc_Regs.h"

static Adc_Regs_t *AdcRegs = (Adc_Regs_t*)0x40034000;

/* 
 *  ADC_Init - Initializes the ADC HW
 *	Params   - 
 */
void ADC_Init(uint8 PCLK_MHz, uint8 AdcClk_MHz)
{
	uint8 ClkDiv;
	
	/* Maximum ADC clock is 13MHz */
	if( AdcClk_MHz>13)
	{
		AdcClk_MHz = 13;
	}
		
	ClkDiv = (PCLK_MHz/(AdcClk_MHz))- 1;
	AdcRegs->ADCR.B.CLKDIV = ClkDiv;
	
	/* Enable power to the ADC */
	AdcRegs->ADCR.B.PDN = 1;
}


uint16_t ADC_ReadValue(Adc_Chnl_t ChnlIdx)
{
	uint16 AdcResult;
	
	/* Select channel for conversion */
	AdcRegs->ADCR.B.SEL = (0x01<<ChnlIdx);
	
	/* Start conversion now */
	AdcRegs->ADCR.B.START = 0x01;
	
	/* Wait until conversion of channel is over */
	while( !AdcRegs->ADGDR.B.DONE );
	
	/* Read out result from the corresponding register */
	AdcResult = AdcRegs->ADDRx[ChnlIdx].B.RESULTx;
	
	return(AdcResult);
}
