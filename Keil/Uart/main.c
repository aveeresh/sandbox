#include "uart.h"
#include <stdint.h>  // Fixes `uint8_t` errors

int main() {
    // ? Automatically initializes all UARTs with 9600 baud, 8N1, No parity
    UART_Init();

    // ? Change UART2 to 115200 baud dynamically
    UART_SetConfig(2, 115200, 8, 1, 0);

    // ? Send a test message
    UART_SendData(2, (uint8_t *)"UART2 Configured!\n", 18);

    while (1) {
        if (UART_IsReceiveReady(2)) {
            uint8_t received_byte = UART_ReceiveByte(2);
            UART_SendByte(2, received_byte);  // Echo received data
        }
    }
}
