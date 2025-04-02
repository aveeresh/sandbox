#include "uart.h"
#include <stdint.h>
#include <stddef.h>  // ? Fixes NULL issue

// Default UART settings
 uint32_t default_baudrate = 9600;
 uint8_t default_databits = 8;
 uint8_t default_stopbits = 1;
 uint8_t default_parity = 0;

// Select the correct UART instance
static LPC_UART_TypeDef* UART_Select(uint8_t uartx) {
    switch (uartx) {
        case 0: return LPC_UART0;
        case 1: return (LPC_UART_TypeDef *)LPC_UART1;  
        case 2: return LPC_UART2;
        case 3: return LPC_UART3;
        default: return NULL;  // ? Fixed NULL issue
    }
}

// Initialize all UARTs with default configuration
void UART_Init(void) {
    uint8_t i;  // ? Declare before the loop
    for (i = 0; i < 4; i++) {
        UART_SetConfig(i, default_baudrate, default_databits, default_stopbits, default_parity);
    }
}

// Configure UART with specific settings
void UART_SetConfig(uint8_t uart_num, uint32_t baudrate, uint8_t databits, uint8_t stopbits, uint8_t parity) {
    LPC_UART_TypeDef *UARTx = UART_Select(uart_num);
    uint16_t divisor;    
    uint8_t lcr_value = 0;

    if (!UARTx) return;

    // Enable clock for UART
    switch (uart_num) {
        case 0: LPC_SC->PCONP |= (1 << 3); break;
        case 1: LPC_SC->PCONP |= (1 << 4); break;
        case 2: LPC_SC->PCONP |= (1 << 24); break;
        case 3: LPC_SC->PCONP |= (1 << 25); break;
    }

    // Enable DLAB to set baud rate
    UARTx->LCR = (1 << 7);

    // Calculate & Set Baud Rate
    divisor = SystemCoreClock / (16 * baudrate);
    UARTx->DLL = divisor & 0xFF;
    UARTx->DLM = (divisor >> 8) & 0xFF;

    // Configure parity, stop bits, data bits
    lcr_value = (databits - 5); 
    if (stopbits == 2) lcr_value |= (1 << 2);
    if (parity == 1) lcr_value |= (1 << 3);   
    if (parity == 2) lcr_value |= (3 << 3);   

    UARTx->LCR = lcr_value;
}

// Send a single byte
void UART_SendByte(uint8_t uartx, uint8_t data) {
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);
    if (!UARTx) return;
    while (!(UARTx->LSR & (1 << 5))); 
    UARTx->THR = data;
}

// Send multiple bytes
void UART_SendData(uint8_t uartx, const uint8_t *data, uint16_t length) {
    uint16_t i;  // ? Declare before the loop
    for (i = 0; i < length; i++) {
        UART_SendByte(uartx, data[i]);
    }
}

// Receive a single byte
uint8_t UART_ReceiveByte(uint8_t uartx) {
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);
    if (!UARTx) return 0;
    while (!(UARTx->LSR & 1)); 
    return UARTx->RBR;
}

// Receive multiple bytes
void UART_ReceiveData(uint8_t uartx, uint8_t *buffer, uint16_t max_length) {
    uint16_t i;  // ? Declare before the loop
    for (i = 0; i < max_length; i++) {
        buffer[i] = UART_ReceiveByte(uartx);
    }
}

// Check if UART is ready to transmit
bool UART_IsTransmitReady(uint8_t uartx) {
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);
    return UARTx && (UARTx->LSR & (1 << 5));
}

// Check if UART has received data
bool UART_IsReceiveReady(uint8_t uartx) {
    LPC_UART_TypeDef *UARTx = UART_Select(uartx);
    return UARTx && (UARTx->LSR & 1);
}
