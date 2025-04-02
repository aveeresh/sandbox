#include<lpc17xx.h>
#include "ADC_driver.h"

volatile uint32 *adcr=(volatile uint32 *)ADCR_BaseAddr;

void ADC_Init(uint8 adcClock_MHz){
    volatile uint32 *powercontrol = (volatile uint32 *)PowerControl_Addr;
    volatile uint32 *peripheralclock = (volatile uint32 *)PeripheralClock_Addr;
    uint32 pclk_adc,clkdiv;
		if(adcClock_MHz>13){
				adcClock_MHz=13;
		}
    *powercontrol |= (1<<12); // A/D converter (ADC) power/clock control bit.
    *peripheralclock &= ~(0x03<<24);
		*peripheralclock |= (0x01<<24);
	  pclk_adc = SystemCoreClock/1;
		clkdiv=(pclk_adc/(adcClock_MHz*1000000))- 1;
    *adcr=(1 << 21)|((clkdiv & 0xFF)<<8);
}


uint16_t ADC_ReadValue(uint8 channel){
		uint32_t adc_temp;	
		volatile uint32 *pinsel0 = (volatile uint32 *)PINSEL0_Addr;
    volatile uint32 *pinsel1 = (volatile uint32 *)PINSEL1_Addr;
    volatile uint32 *pinsel3 = (volatile uint32 *)PINSEL3_Addr;
		volatile uint32 *addr_reg=(volatile uint32*)(ADDR_BaseAddr+(channel*4)); 
    switch(channel){
        case 0:
            *pinsel1 &= ~(0x3<<14); // Clear bits for P0.23 (AD0.0) channel 0
            *pinsel1 |= (0x1<<14);  // Set P0.23 as AD0.0
            break;
        case 1:
            *pinsel1 &= ~(0x3<<16); // Clear bits for P0.24 (AD0.1) channel 1
            *pinsel1 |= (0x1<<16);  // Set P0.24 as AD0.1
            break;
        case 2:
            *pinsel1 &= ~(0x3<<18); // Clear bits for P0.25 (AD0.2) channel 2
            *pinsel1 |= (0x1<<18);  // Set P0.25 as AD0.2
            break;
        case 3:
            *pinsel1 &= ~(0x3<<20); // Clear bits for P0.26 (AD0.3) channel 3
            *pinsel1 |= (0x1<<20);  // Set P0.26 as AD0.3
            break;
        case 4:
            *pinsel3 &= ~(0x3<<28); // Clear bits for P1.30 (AD0.4) channel 4
            *pinsel3 |= (0x3<<28);  // Set P1.30 as AD0.4
            break;
        case 5:
            *pinsel3 &= ~(0x3U<<30); // Clear bits for P1.31 (AD0.5) channel 5
            *pinsel3 |= (0x3U<<30);  // Set P1.31 as AD0.5
            break;
        case 6:
            *pinsel0 &= ~(0x3<<6); // Clear bits for P0.3 (AD0.6) channel 6
            *pinsel0 |= (0x2<<6);  // Set P0.3 as AD0.6
            break;
        case 7:
            *pinsel0 &= ~(0x3<<4); // Clear bits for P0.2 (AD0.7) channel 7
            *pinsel0 |=(0x2<<4);  // Set P0.2 as AD0.7
            break;
        default:
            break;
		}
		*adcr &= ~(0xFF);  // Clear any previously selected channels
    *adcr |= (1<<channel); // Select ADC channel
    *adcr |= (1<<24); // Start conversion now
		if(channel>7){
				adc_temp=0;
		}
    while((*addr_reg&0x80000000)==0); //Wait until the conversion is complete
    adc_temp=*addr_reg;
    adc_temp>>=4;
    adc_temp&=0xFFF;
    return (uint16_t)adc_temp;
}

void ADC_Delay(void){
    unsigned long long i;
    for(i=0;i<6500000;i++);
}
