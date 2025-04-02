#ifndef ADC_DRIVER_H
#define ADC_DRIVER_H

#include "Types.h"

typedef enum 
{
	ADC0_AIN0, 
	ADC0_AIN1, 
	ADC0_AIN2, 
	ADC0_AIN3, 
	ADC0_AIN4, 
	ADC0_AIN5, 
	ADC0_AIN6, 
	ADC0_AIN7, 
}Adc_Chnl_t;

void ADC_Init(uint8, uint8);
uint16 ADC_ReadValue(Adc_Chnl_t); 
void ADC_Delay(void);

#endif
