#include "driver_CAN.h"
#include <stdio.h>
#include <lpc17xx.h>

extern uint8 i;
int main() {
    CAN_msg tx_msg, rx_msg;
    uint8 CAN_idx = 1;
    
		LPC_PINCON->PINSEL0 |= (0x01) | (0x01<<2);
	
    // Initialize CAN1
    CAN_Init(CAN_idx);
    
    // Set CAN baud rate to 500 Kbps with PCLK of 25 MHz, TSEG1 = 8, TSEG2 = 1
    CAN_Set_BaudRate(CAN_idx, 500, 25, 8, 1);
    
    // Prepare message for transmission
    tx_msg.CAN_id = 0x123;   // Standard ID
    tx_msg.len = 4;          // 4-byte data
    tx_msg.data[0] = 0x11;
    tx_msg.data[1] = 0x22;
    tx_msg.data[2] = 0x33;
    tx_msg.data[3] = 0x44;
    
    // Transmit the message
    CAN_Transmit(CAN_idx, &tx_msg);
    //printf("Message transmitted!\n");
    
    // Receive message
    CAN_Receive(CAN_idx, &rx_msg);
    //printf("Received Message ID: 0x%X\n", rx_msg.CAN_id);
    //printf("Data: ");
    for ( i = 0; i < rx_msg.len; i++) 
		{
        //printf("0x%X ", rx_msg.data[i]);
    }
   // printf("\n");
		
		while(1)
		{
		}

}
