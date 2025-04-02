#include<lpc17xx.h>
#include<stdio.h>
#include "ADC_driver.h"
//#include "lcd.h"
#include<stdint.h>
#include <string.h>
#include "can_driver.h"

int main(){
		uint16_t adc_result;
        uint8_t len,i;
        uint8_t CAN_recv[7];
	    uint8_t rx_data[8];
	    uint32_t rx_id;
		char result[6];
		uint16_t integer,decimal;
		SystemInit();
		SystemCoreClockUpdate();
		ADC_Init(); 
		//lcd_init();
                CAN_Init();
                CAN_PinConfig();
                CAN_Set_BRate(500000, 25000000);  // Corrected function call

		ADC_ConfigChannel(0); //Using channel 0
		while(1){
				ADC_StartConversion(0); //Using channel 0
				adc_result=ADC_ReadValue(0); //Using channel 0
				integer=adc_result/10;
				decimal=adc_result%10;
				sprintf(result,"%x.%x",integer,decimal);
				/*
						Write your code here temperature is stored in the result string.
						You can transmit this result from my 1st microcontroller to the 2nd microcontroller using the CAN driver
						and then from 2nd to 3rd using the UART driver then display it on the LCD on the 3rd microcontroller
				*/
                                     CAN_Transmit(0x101, (uint8_t*)result, 6); // Transmit data
									 
                                     len = CAN_Receive(&rx_id, rx_data); //Receive data
                               
                                if (len > 0)
                                {
                                    // printf("Received ID: 0x%X, Data: ", rx_id);
                                     for (i = 0; i < len; i++) 
                                    {
                                      CAN_recv[i] =rx_data[i];
                                    }
                                   CAN_recv[i]='\0';
                                }
                               /*
                                           The received result is stored in "CAN_recv" it can be passes through UART 
                               */    
                          }
   
                                             


		}
