#ifndef UART_H
#define UART_H

#include "Types.h"

typedef enum {UART0, UART1, UART2, UART3}Uart_Idx_t;

typedef enum {UART_CFG_WSL5, UART_CFG_WSL6, UART_CFG_WSL7, UART_CFG_WSL8}Uart_WordLenSel_t;

typedef enum {UART_CFG_PAR_ODD, UART_CFG_PAR_EVEN, UART_CFG_PAR_STICK1, UART_CFG_PAR_STICK0}Uart_ParSel_t;
typedef enum {UART_CFG_PAR_1STPBIT, UART_CFG_PAR_2STPBIT}Uart_StpBit_t;

typedef enum {UART_CFG_BAUD_4800=4800, UART_CFG_BAUD_9600=9600, UART_CFG_BAUD_115200=115200}Uart_BaudRate_t;

typedef struct
{
	Uart_WordLenSel_t WordLength;
	Uart_StpBit_t StopBitSel;
	bool ParEn;
	Uart_ParSel_t ParSel;
	bool BreakCtrlEn;
	uint8 PCLK_MHz;
	Uart_BaudRate_t BaudRate;
}Uart_Config_t;

// Function Prototypes
void UART_Init(Uart_Idx_t, Uart_Config_t);  // Initialize all UARTs with default settings
void UART_SetBaudRate(Uart_Idx_t, uint8, Uart_BaudRate_t BaudRate);
void UART_SendByte(Uart_Idx_t, uint8);
void UART_SendString(Uart_Idx_t, uint8*, uint8);
uint8 UART_ReceiveByte(Uart_Idx_t, uint8*);
void UART_ReceiveData(Uart_Idx_t, uint8*, uint8);

#endif
