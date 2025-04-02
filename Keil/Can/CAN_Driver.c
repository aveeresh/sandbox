#include "CAN_Driver.h"
#include "Can_Regs.h"

#define MEG(x) (x * 1000000)
#define KIL(x) (x * 1000)

void CAN_Init(uint8 CAN_Controller) {
		volatile uint32 *powercontrol = (volatile uint32 *)PowerControl_Addr;
    if (CAN_Controller == 1) {
        *powercontrol |= (1 << 13); // Power ON CAN1
    } else if (CAN_Controller == 2) {
        *powercontrol |= (1 << 14); // Power ON CAN2
    }
}



void CAN_Set_BaudRate(uint8 CAN_idx, uint32 baudrate_Kbps, uint32 PCLK_MHz, uint8 TSEG1, uint8 TSEG2) {
    uint32 brp, btr_value;

    switch (baudrate_Kbps) {
        case 125:  
            brp = (MEG(PCLK_MHz)/ ((KIL(baudrate_Kbps)) *(TSEG1+TSEG2+1)))-1;
				//(PCLK_MHz/ ((baudrate_Kbps) *(TSEG1+TSEG2+1)))-1; 
            break;
        case 250:  
            brp = (MEG(PCLK_MHz)/ ((KIL(baudrate_Kbps)) *(TSEG1+TSEG2+1)))-1;
				//(PCLK_MHz/ ((baudrate_Kbps) *(TSEG1+TSEG2+1)))-1; 
            break;
        case 500:  
            brp = (MEG(PCLK_MHz)/ ((KIL(baudrate_Kbps)) *(TSEG1+TSEG2+1)))-1;

				//(PCLK_MHz/ ((baudrate_Kbps) *(TSEG1+TSEG2+1)))-1; 
            break;
        case 1000: 
            brp = (MEG(PCLK_MHz)/ ((KIL(baudrate_Kbps)) *(TSEG1+TSEG2+1)))-1;
				//(PCLK_MHz/ ((baudrate_Kbps) *(TSEG1+TSEG2+1)))-1; 
            break;
        
    }
		
		brp = 3;

    btr_value = ((TSEG2 - 1) << 20) | ((TSEG1 - 1) << 16) | (0 << 14) | brp;

    if (CAN_idx == 1) {
        CAN1->MOD |= 0x01; // Enter Reset Mode
				for(int i=0 ; i<10000 ; i++);
        CAN1->BTR = btr_value; // Set BTR register
        CAN1->MOD &= ~(0x01); // Exit Reset Mode
    } else if (CAN_idx == 2) {
        CAN2->MOD = 1; // Enter Reset Mode
        CAN2->BTR = btr_value; // Set BTR register
        CAN2->MOD = 0; // Exit Reset Mode
    } 
}

void CAN_Transmit(uint8 CAN_Controller, CAN_msg *msg) {
		if(CAN_Controller==1){
			while (!(CAN1->SR & (1 << 2))); // Wait until TX buffer is empty
			CAN1->TFI1 = (msg->len << 16); // Set data length
			CAN1->TID1 = msg->id; // Set identifier
			CAN1->TDA1 = *((uint32 *)&msg->data[0]); // Load first 4 bytes
			CAN1->TDB1 = *((uint32 *)&msg->data[4]); // Load next 4 bytes
			CAN1->CMR = (1 << 5) | (1 << 0); // Request transmission
			//while(!((CAN1->GSR)&(0x01<<3)));
		}
		else if(CAN_Controller==2){
			while (!(CAN2->SR & (1 << 2))); // Wait until TX buffer is empty
			CAN2->TFI1 = (msg->len << 16); // Set data length
			CAN2->TID1 = msg->id; // Set identifier
			CAN2->TDA1 = *((uint32 *)&msg->data[0]); // Load first 4 bytes
			CAN2->TDB1 = *((uint32 *)&msg->data[4]); // Load next 4 bytes
			CAN2->CMR = (1 << 5) | (1 << 0); // Request transmission
			while(!((CAN2->GSR)&(0x01<<3)));
		}
}

