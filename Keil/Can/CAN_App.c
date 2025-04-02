#include <lpc17xx.h>
#include "CAN_Driver.h"
#include "Mcu_Regs.h"

Mcu_PwrCtrlRegs_t *PwrCtrlRegs = (Mcu_PwrCtrlRegs_t *)0x400FC0C0;

int main() 
{
    CanMsg_t tx_msg;
		CanMsg_t RxMsg[10];
		CanIdx_t RxCanIdx;
		int i;
		int tx_count = 1;
	  uint32 RxCount=0;
		
		SystemInit();
	
		#if 1
		LPC_SC->PCONP |= (0x01<<13) | (0x01<<14);
		#else
		PwrCtrlRegs->PCONP.B.PCCAN1 = 1;
		PwrCtrlRegs->PCONP.B.PCCAN2 = 1;
		#endif
		
		//PCLK_CAN1 = 50 MHz, PCLK_CAN2 = 50 MHz
		LPC_SC->PCLKSEL0 |= (0x02<<26) | (0x02<<28) | ((uint32)0x02<<30);
		
		#if 1
		//P0.21(RX) and P0.22(TX) for CAN1
		LPC_PINCON->PINSEL1 |= (0x03<<10) | (0x03<<12);
	  //LPC_PINCON->PINMODE1 |= (0x02<<10) | (0x02<<12);
		//LPC_PINCON->PINMODE_OD0 |= (0x01<<21) | (0x01<<22);
		#else
		//P0.0(RX) and P0.1(TX) for CAN1
		LPC_PINCON->PINSEL0 |= (0x01) | (0x01<<2); 
		LPC_PINCON->PINMODE0 |= (0x02) | (0x02<<2);
		#endif
    
		// Initialize CAN1
    CAN_Init(CANCTRL1);
    
    // Set CAN baud rate to 500 Kbps with PCLK of 50 MHz, SJW = 1 TSEG1 = 15, TSEG2 = 7
    //CAN_Set_BaudRate(CANCTRL1, 3, 1, 15, 7);
    // Set CAN baud rate to 500 Kbps with PCLK of 50 MHz, SJW = 1 TSEG1 = 2, TSEG2 = 4
    CAN_Set_BaudRate(CANCTRL1, 9, 1, 3, 4);
    
    // Prepare message for transmission
    tx_msg.id.StdId.value = 0x555;   // Standard ID
    tx_msg.len = 5;      // 4-byte data
		tx_msg.data[1] = 0x55;
    tx_msg.data[2] = 0x55;
    tx_msg.data[3] = 0x55;
		tx_msg.data[4] = 0x55;
		
    while (1) 
		{
			tx_msg.data[0] = tx_count;
			//CAN_Transmit(CANCTRL1, tx_msg);  // Pass structure pointer
			CAN_Receive(&RxCanIdx, &RxMsg[RxCount++]);
			tx_count++;
      for(i=0;i<3000000;i++);  // Small delay
    }
}
