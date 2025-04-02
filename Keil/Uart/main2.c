#include "uart2.h"

int main() {
    UART_Config_t uartConfig = {9600, 8, 1, 'N'};
		 char receivedChar;

    // Initialize UART0
    UART_Init(1, &uartConfig);

    // Send Test Message
    UART_SendString(1, "Hello UART!\r\n");

    // Receive Character
    receivedChar = UART_ReceiveChar(1);

    // Echo the received character
    UART_SendChar(1, receivedChar);

    while (1);
}
