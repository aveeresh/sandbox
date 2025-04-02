#ifndef UART_H
#define UART_H

#include "LPC17xx.h"
#include <stdint.h>
#include <stdbool.h>

// UART Configuration Structure
typedef struct {
    uint32_t baudRate;
    uint8_t dataBits;
    uint8_t stopBits;
    char parity;  // 'N' - None, 'O' - Odd, 'E' - Even
} UART_Config_t;

// Function Declarations
void UART_Init(uint8_t uartx, UART_Config_t *config);
void UART_SendChar(uint8_t uartx, char ch);
void UART_SendString(uint8_t uartx, const char *str);
char UART_ReceiveChar(uint8_t uartx);
void UART_ReceiveString(uint8_t uartx, char *buffer, uint16_t max_length);
bool UART_IsTransmitReady(uint8_t uartx);
bool UART_IsReceiveReady(uint8_t uartx);

#endif
