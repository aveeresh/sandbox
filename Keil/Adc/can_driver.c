//#include "lpc1768_can_macros.h"
//#include "lpc1768_can_reg_axs.h"
#include<stdio.h>
#include<string.h>
#include "can_driver.h"
#include <lpc17xx.h>
	
	uint8_t i;
uint8_t len;
//static volatile unsigned char *base_addr = ((volatile unsigned char *) 0xE000C000);

/**
* @brief the Initialization of CAN
* @param : none
* return none 
**/
void CAN_Init(void)
{
	LPC_SC->PCONP |=(1<<13); //enable power to CAN1
}
/*
void CAN_Init(LPC_CAN_TypeDef *CANx)
{
	if(CANx==LPC_CAN1)
	{
		LPC_SC->PCONP |=(1<<13); //enable power to CAN1
	}
	else if(CANx==LPC_CAN2)
	{
		LPC_SC->PCONP |=(1<<14); //enable power to CAN2
	}		
}
*/

/**
* @brief the pin configuration of CAN
* @param : none
* return none 
**/
void CAN_PinConfig(void)
{
   //set P0.0 as RD1 and P0.1 as TD1/
   //clear current pin configurations
   LPC_PINCON->PINSEL0 &=~((3<<0) | (3<<2));

   //set CAN1 functionality
   LPC_PINCON->PINSEL0|=((1<<0) | (1<<2));

}

/**
* @brief  the Baud rate of CAN
*@param baud : the baud rate of the CAN data to be transmitted
  * valid values: 125 ...100000 in kbps
*@param clock: the peripheral clock frequency in MHz 
  * valid values: 100 MHz, 50 MHz, 25 MHz, 12.5 MHz
* return none 
**/
void CAN_Set_BRate(uint32_t baudrate, uint32_t pclk)
//void CAN_Set_BaudRate(LPC_CAN_TypeDef *CANx,uint32_t baudrate, uint32_t pclk)
{
    uint32_t brp, tseg1, tseg2, sjw;
	  LPC_CAN1->MOD = 1; // Enter Reset Mode
   
   

    /*// Ensure valid CAN peripheral
    if (CANx != LPC_CAN1 && CANx != LPC_CAN2) {
        return;  // Invalid CAN instance
    }**/

    // Calculate Baud Rate Prescaler (BRP)
    brp = (pclk / (baudrate * 16)) - 1;

    // Standardized Bit Timing Segments (TSEG1, TSEG2, SJW)
    tseg1 = 4;  // Time Segment 1 (Default 4)
    tseg2 = 3;  // Time Segment 2 (Default 3)
    sjw = 1;    // Synchronization Jump Width (Default 1)

    // Configure BTR Register
    LPC_CAN1->BTR = (sjw << 14) | (tseg1 << 16) | (tseg2 << 20) | brp;
	 LPC_CAN1->MOD = 0; // Exit Reset Mode
}


/**
* @brief the data transmitted from CAN
*@param id: CAN Message ID (Standard: 11-bit, Extended: 29-bit).
   * valid values: Standard Id- 0x000 to 0x7FF
                             Extended Id- 0x00000000 to 0x1FFFFFFF 
*@param data: Pointer to the data buffer
   * valid values: any data of 0 to 8 bytes 
*@param len : Number of bytes in the CAN message 
   * valid values: 0 to 8
*return none 
**/
void CAN_Transmit (uint32_t id, uint8_t *data, uint8_t len)
{
	//while(!(LPC_CAN1->SR &(1<<2))); //WAIT FOR tx READY
	
   LPC_CAN1->TFI1=(len & 0xF)<<16;    //SET DLC
   LPC_CAN1->TID1=id;           //set CAN ID
   //load message data
   for( i=0; i<len; i++)
{ 
	if(len<=4)
	{
    ((uint8_t*)&LPC_CAN1->TDA1)[i]=data[i];
	}
	else
	{
		((uint8_t*)&LPC_CAN1->TDB1)[i]=data[i];
	}
	
}

LPC_CAN1->CMR=(1<<0); //start transmission

}

/**
* @brief Receives a message from the CAN
*@param id: Pointer to store the received CAN Message ID
   * valid values: Standard Id- 0x000 to 0x7FF
                             Extended Id- 0x00000000 to 0x1FFFFFFF 
*@param data: Pointer to the data buffer to store received bytes.
   * valid values: any data of 0 to 8 bytes 
*return Number of bytes received (DLC value)
     * valid values: 0 to 8 bytes
**/
uint8_t  CAN_Receive(uint32_t *id, uint8_t *data)
{
    if (!(LPC_CAN1->GSR & (1 << 0)) )
    {  // Check if a new message is received
			return 0;
		}
		else
		{
       len = (LPC_CAN1->RFS >> 16) & 0xF;  // Extract DLC (data length)

        if (LPC_CAN1->RFS & (1 << 30))
         {  // Extended ID
            *id = LPC_CAN1->RID & 0x1FFFFFFF;
         }
        else
        {  // Standard ID
            *id = LPC_CAN1->RID & 0x7FF;
        }

        for ( i = 0; i <len; i++)
        { 
					if(len<=4)
          
            {				
							data[i] = ((uint8_t *)&LPC_CAN1->RDA)[i];  // Read data
						}
						else
						{
							data[i] = ((uint8_t *)&LPC_CAN1->RDB)[i]; 
						}
        }

        LPC_CAN1->CMR = (1 << 2);  // Release receive buffer
				//return len;
				return 1;
    }
   
}