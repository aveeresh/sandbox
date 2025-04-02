#include "Uart.h"
#include "LPC17xx.h"

#define UART_USED UART0

int main() 
{
	Uart_Config_t UartConfig = 
	{
		.WordLength = UART_CFG_WSL8,
	  .StopBitSel = UART_CFG_PAR_1STPBIT,
	  .ParEn = FALSE,
	  .ParSel = UART_CFG_PAR_ODD,
	  .BreakCtrlEn = FALSE,
		.PCLK_MHz = 25,
		.BaudRate = UART_CFG_BAUD_9600
	};
	
	/* Setup P0.2(Tx) and P0.3(Rx) as UART0 */
	LPC_PINCON->PINSEL0 |= (0x01<<4) | (0x01<<6);
	
	/* Enable clock to the UART module */
	LPC_SC->PCONP |= (0x01<<3);
	
	UART_Init(UART_USED, UartConfig);

  //Change UART2 to 9600 baud dynamically
  UART_SetBaudRate(UART_USED, 25, UART_CFG_BAUD_9600);
	
	while( 1 )
	{
		//Send a test message
		UART_SendString(UART_USED, (uint8*)"UART2 Configured!\n", 18);
		for(int i=0 ; i<4000000 ; i++);
	}
}
