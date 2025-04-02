//#include "lpc1768_can_macros.h"
//#include "lpc1768_can_reg_axs.h"
#include<stdio.h>
#include<string.h>
#include "driver_CAN.h"
#include <lpc17xx.h>
	
uint8 i;


/**
* @brief the Initialization of CAN
* @param CAN_idx: index to CAN
   *@valid values: 1 - CAN1
                   2 - CAN2
* return none 
**/
void CAN_Init(uint8 CAN_idx)
{
	if(CAN_idx==1)
	LPC_SC->PCONP |=(1<<13); //enable power to CAN1
    else if(CAN_idx==2)
	{
		 LPC_SC->PCONP |=(1<<14); //enable power to CAN2
	}
    else	
    {
		printf("Invalid index\n");
	}
	
}


/**
* @brief the pin configuration of CAN
* @param : none
* return none 

void CAN_PinConfig(void)
{
   //set P0.0 as RD1 and P0.1 as TD1/
   //clear current pin configurations
   LPC_PINCON->PINSEL0 &=~((3<<0) | (3<<2));

   //set CAN1 functionality
   
   LPC_PINCON->PINSEL0|=((1<<0) | (1<<2));

}

**/

/**
* @brief  the Baud rate of CAN
*@param CAN_idx: index to CAN
*@valid values: 1 - CAN1
                2 - CAN2
*@param baudrate_Kbps : the baud rate of the CAN data to be transmitted
  * valid values: 125, 250, 500, 1000 in kbps
*@param PCLK_MHz :Peripheral clock frequency (PCLK) in MHz 

*@param TSEG1 : Time segment 1 value 

*@param TSEG2 : Time segment 2 value 
* return none 
**/
void CAN_Set_BaudRate(uint8 CAN_idx, uint16 baudrate_Kbps, uint8 PCLK_MHz, uint8 TSEG1, uint8 TSEG2)
{
    uint32 brp, btr_value;
    
    // Assuming Sampling point as 87.5%
    switch (baudrate_Kbps) {
        case 125:  
		
            brp =  PCLK_MHz/ (baudrate_Kbps *(TSEG1+TSEG2+1))-1;
            break;
        case 250:  
            brp = PCLK_MHz/ (baudrate_Kbps * (TSEG1+TSEG2+1))-1;
            break;
        case 500:  
            brp = PCLK_MHz/ (baudrate_Kbps * (TSEG1+TSEG2+1))-1;
            break;
        case 1000: 
            brp = PCLK_MHz/ (baudrate_Kbps * (TSEG1+TSEG2+1))-1;
            break;
        default:
            printf("Invalid baud rate, exit function");
    }
    // BTR value encoding:
    // | 22-20: TESG2  | 19-16: TESG1  | 15-14: SJW | 9-0: BRP |
    //btr_value = ((TSEG2-1) << 20) | ((TSEG1-1) << 16) | (0 << 14) | brp;
		brp = 4;
		btr_value = ((TSEG2-1) << 20) | ((TSEG1-1) << 16) | (0 << 14) | brp;
 
    if(CAN_idx==1)
	{
		LPC_CAN1->MOD = 1;        // Enter Reset Mode
    LPC_CAN1->BTR = btr_value; // Set BTR register
    LPC_CAN1->MOD = 0;      // Exit Reset Mode
	}
	else if(CAN_idx==2)
	{
		LPC_CAN2->MOD = 1;        // Enter Reset Mode
    LPC_CAN2->BTR = btr_value; // Set BTR register
    LPC_CAN2->MOD =0;      // Exit Reset Mode
	}
	else 
	{
		printf("invalid index ");
	}
}
   
    /*// Ensure valid CAN peripheral
    if (CANx != LPC_CAN1 && CANx != LPC_CAN2) {
        return;  // Invalid CAN instance
    }
    // Calculate Baud Rate Prescaler (BRP)
    brp = (pclk / (baudrate * 16)) - 1;

    // Standardized Bit Timing Segments (TSEG1, TSEG2, SJW)
    tseg1 = 4;  // Time Segment 1 (Default 4)
    tseg2 = 3;  // Time Segment 2 (Default 3)
    sjw = 1;    // Synchronization Jump Width (Default 1)

    // Configure BTR Register
    LPC_CAN1->BTR = (sjw << 14) | (tseg1 << 16) | (tseg2 << 20) | brp;
	**/



/**
* @brief the data transmitted from CAN
*@param CAN_idx: index to CAN
*@valid values: 1 - CAN1
                2 - CAN2
*@param CAN_msg : structure consisting CAN msg details mentioned below 				
*@param id: CAN Message ID (Standard: 11-bit, Extended: 29-bit).
   * valid values: Standard Id- 0x000 to 0x7FF
                   Extended Id- 0x00000000 to 0x1FFFFFFF 
*@param data: Pointer to the data buffer
   * valid values: any data of 0 to 8 bytes 
*@param len : Number of bytes in the CAN message 
   * valid values: 0 to 8
*return none 
**/


void CAN_Transmit(uint8 CAN_idx, CAN_msg *msg)
{
    if (CAN_idx == 1)
    {
        while (!(LPC_CAN1->SR & (1 << 2))); // WAIT FOR TX READY
			
			  LPC_CAN2->TFI1 = ((msg->CAN_id > 0x7FF) ? (1 << 31) : 0);  //set FF to 1 if it is extended ID
		    LPC_CAN1->TFI1 = ((msg->len & 0xF) << 16); // DLC
        LPC_CAN1->TID1 = msg->CAN_id; // Set CAN ID
        
		//load message data
        for ( i = 0; i < msg->len; i++)
        {
            if (msg->len <= 4)
            {
                ((uint8*)&LPC_CAN1->TDA1)[i] = msg->data[i];
            }
            else
            {
                ((uint8*)&LPC_CAN1->TDB1)[i] = msg->data[i];
            }
        }
        
        LPC_CAN1->CMR = (1 << 0); // Start transmission
    }
    else if (CAN_idx == 2)
    {
        while (!(LPC_CAN2->SR & (1 << 2))); // WAIT FOR TX READY
        
        LPC_CAN2->TFI1 = ((msg->CAN_id > 0x7FF) ? (1 << 31) : 0);   //set FF to 1 if it is extended ID
		    LPC_CAN2->TFI1 = ((msg->len & 0xF) << 16); // Set DLC
        LPC_CAN2->TID1 = msg->CAN_id; // Set CAN ID
        
        for (i = 0; i < msg->len; i++)
        {
            if (msg->len <= 4)
            {
                ((uint8*)&LPC_CAN2->TDA1)[i] = msg->data[i];
            }
            else
            {
                ((uint8*)&LPC_CAN2->TDB1)[i] = msg->data[i];
            }
        }
        
        LPC_CAN2->CMR = (1 << 0); // Start transmission
    }
    else
    {
        printf("Enter valid index\n");
    }
}


/**
*@brief Receives a message from the CAN
*@param idx: index to CAN
   *@valid values: 1 - CAN1
                   2 - CAN2
*@param CAN_msg : structure consisting CAN msg details mentioned below 

*@param id: Pointer to store the received CAN Message ID
   * valid values: Standard Id- 0x000 to 0x7FF
                             Extended Id- 0x00000000 to 0x1FFFFFFF 
*@param data: Pointer to the data buffer to store received bytes.
   * valid values: any data of 0 to 8 bytes 
*@param len: Number of bytes received (DLC value)
     * valid values: 0 to 8
* return none
**/


void CAN_Receive(uint8 CAN_idx, CAN_msg *msg)
{
    if (CAN_idx == 1)
    {
        while (!(LPC_CAN1->GSR & (1 << 0))); // Check if a new message is received
        
        msg->len = (LPC_CAN1->RFS & 0xF0000) >> 16; // Extract DLC
        
        msg->CAN_id = (LPC_CAN1->RFS & (1 << 31)) ? (LPC_CAN1->RID & 0x1FFFFFFF) : (LPC_CAN1->RID & 0x7FF); // Extract ID
        
        for (i = 0; i < msg->len; i++)
        {
			if(msg->len <= 4)
			{
				msg->data[i] = ((uint8*)&LPC_CAN1->RDA)[i];
            }
            else	
			{
				msg->data[i] =((uint8*)&LPC_CAN1->RDB)[i];
			}
        }
        
        LPC_CAN1->CMR = (1 << 2); // Release receive buffer
    }
	
    else if (CAN_idx == 2)
    {
        while (!(LPC_CAN2->GSR & (1 << 0))); // Check if a new message is received
        
        msg->len = (LPC_CAN2->RFS & 0xF0000) >> 16; // Extract DLC
        
        msg->CAN_id = (LPC_CAN2->RFS & (1 << 31)) ? (LPC_CAN2->RID & 0x1FFFFFFF) : (LPC_CAN2->RID & 0x7FF); // Extract ID
        
        for (i = 0; i < msg->len; i++)
        {
            if(msg->len <= 4)
			{
				msg->data[i] = ((uint8*)&LPC_CAN2->RDA)[i];
            }
            else	
			{
				msg->data[i] =((uint8*)&LPC_CAN2->RDB)[i];
			}
        }
        
        LPC_CAN2->CMR = (1 << 2); // Release receive buffer
    }
    else
    {
        printf("Enter valid index\n");
    }
}
