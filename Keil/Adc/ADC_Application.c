#include<lpc17xx.h>
#include "ADC_driver.h"

#define PCLK_ADC0 25
#define ADCCLK    5

int main()
{
		uint16 adc_result;
	  int i;

		SystemInit();
		SystemCoreClockUpdate();
	
		/* Enable power to the ADC module */
		LPC_SC->PCONP = (0x01<<12);
	
		/* Select pin mode for ADC0.0 pin */
		//LPC_PINCON->PINSEL1 |= (0x01<<14);
		/* Select pin mode for ADC0.0 pin */
		LPC_PINCON->PINSEL1 |= (0x01<<20);
	
		ADC_Init(PCLK_ADC0, ADCCLK); 

		while(1)
		{
			adc_result = ADC_ReadValue(ADC0_AIN0);
			for(i=0 ; i<4000000 ; i++);
		}
}	
