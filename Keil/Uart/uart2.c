#include "uart2.h"

// Select UART instance
static LPC_UART_TypeDef* UART_Select(uint8_t uartx) {
    switch (uartx) {
        case 0: return LPC_UART0;
        case 1: return (LPC_UART_TypeDef*) LPC_UART1;
        case 2: return LPC_UART2;
        case 3: return LPC_UART3;
        default: return (LPC_UART_TypeDef*) LPC_UART1;
    }
}

// UART Initialization
void UART_Init(uint8_t uartx, UART_Config_t *config) {
	uint32_t Fdiv;
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);

 // Enable Power for the Selected UART
    switch (uartx) {
        case 0: LPC_SC->PCONP |= (1 << 3); break;  // UART0
        case 1: LPC_SC->PCONP |= (1 << 4); break;  // UART1
        case 2: LPC_SC->PCONP |= (1 << 24); break; // UART2
        case 3: LPC_SC->PCONP |= (1 << 25); break; // UART3
        default: return;
    }

    // Select UART Pins (Example: UART0 P0.2 (TX) and P0.3 (RX))
    if (uartx ==1) {
LPC_PINCON->PINSEL4 |= (2 << 0) | (2 << 2);  // P0.2, P0.3
    }

    // Set Baud Rate
     Fdiv = (SystemCoreClock / (16 * config->baudRate));
    UARTx->LCR = 0x83; // Enable DLAB
    UARTx->DLM = Fdiv >> 8;
    UARTx->DLL = Fdiv & 0xFF;
    UARTx->LCR &= ~0x80; // Disable DLAB

    // Configure Frame Format
    UARTx->LCR = (config->dataBits - 5) | ((config->stopBits - 1) << 2);
    if (config->parity == 'O') UARTx->LCR |= (1 << 3) | (1 << 4);
    else if (config->parity == 'E') UARTx->LCR |= (1 << 3);
    
    // Enable FIFO
    UARTx->FCR = 0x07;
}

// Send a Single Character
void UART_SendChar(uint8_t uartx, char ch) {
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);
    while (!(UARTx->LSR & 0x20)); // Wait until THR is empty
    UARTx->THR = ch;
}

// Send a String
void UART_SendString(uint8_t uartx, const char *str) {
    while (*str) {
        UART_SendChar(uartx, *str++);
    }
}

// Receive a Character
char UART_ReceiveChar(uint8_t uartx) {
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);
    while (!(UARTx->LSR & 0x01)); // Wait for data
    return UARTx->RBR;
}

// Receive a String
void UART_ReceiveString(uint8_t uartx, char *buffer, uint16_t max_length) {
    uint16_t i = 0;
    char ch;
    while (i < max_length - 1) {
        ch = UART_ReceiveChar(uartx);
        if (ch == '\n' || ch == '\r') break;
        buffer[i++] = ch;
    }
    buffer[i] = '\0'; // Null terminate
}

// Check if UART is Ready to Transmit
bool UART_IsTransmitReady(uint8_t uartx) {
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);
    return (UARTx->LSR & 0x20) ? true : false;
}

// Check if UART is Ready to Receive
bool UART_IsReceiveReady(uint8_t uartx) {
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);
    return (UARTx->LSR & 0x01) ? true : false;
}
