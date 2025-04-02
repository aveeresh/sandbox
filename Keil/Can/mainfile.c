#include "LPC17xx.h"

#define CAN1_CLK 24000000  // Set according to PCLK of CAN peripheral

// Function to initialize CAN1 with 500 kbps baud rate
void CAN_Init(void) {
    LPC_SC->PCONP |= (1 << 13); // Power ON CAN1
    LPC_PINCON->PINSEL0 |= (1 << 0) | (1 << 2); // P0.0 (RD1) and P0.1 (TD1) as CAN1

    LPC_CAN1->MOD = 1; // Enter Reset mode
    LPC_CAN1->BTR = (1 << 20) | (3 << 16) | (1 << 14) | (5 - 1); // BRP=4, TSEG1=3, TSEG2=1, SJW=1 -> 500 kbps for 24MHz PCLK
    LPC_CAN1->MOD = 0; // Exit Reset mode

    LPC_CAN1->IER = 0; // Disable all interrupts (Polling Mode)
}

// Function to transmit a CAN frame
void CAN_Transmit(uint32_t id, uint8_t *data, uint8_t length) {
    while (!(LPC_CAN1->SR & (1 << 2))); // Wait until the TX buffer is empty

    LPC_CAN1->TFI1 = (length << 16); // Set data length
    LPC_CAN1->TID1 = id; // Set identifier
    LPC_CAN1->TDA1 = *((uint32_t *)&data[0]); // Load first 4 bytes
    LPC_CAN1->TDB1 = *((uint32_t *)&data[4]); // Load next 4 bytes
    LPC_CAN1->CMR = (1 << 5) | (1 << 0); // Request transmission
}

// Function to receive a CAN frame (Blocking Mode)
void CAN_Receive(uint32_t *id, uint8_t *data) {
		int i;
		uint32_t rda,rdb;
    while (!(LPC_CAN1->SR & (1 << 8))); // Wait until message is received

    *id = LPC_CAN1->RID;  // Read ID
    rda = LPC_CAN1->RDA;
    rdb = LPC_CAN1->RDB;

    // Extract bytes
    for (i = 0; i < 4; i++) {
        data[i] = (rda >> (i * 8)) & 0xFF;
        data[i + 4] = (rdb >> (i * 8)) & 0xFF;
    }

    LPC_CAN1->CMR = (1 << 2); // Release RX buffer
}

// Main function to test CAN communication
int main(void) {
    uint32_t received_id;
    uint8_t tx_data[8] = {1, 2, 3, 4, 5, 6, 7, 8};
    uint8_t rx_data[8];

    CAN_Init();

    while (1) {
        CAN_Transmit(0x123, tx_data, 8); // Transmit frame with ID 0x123

       
    }
}
