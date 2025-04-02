#include "Uart.h"
#include "Uart_Regs.h"

#define MEGA(x) (((x)*1000000))

static Uart_Regs_t *UartRegs[4] = {(Uart_Regs_t*)0x4000C000, (Uart_Regs_t*)0x00000000, (Uart_Regs_t*)0x40098000, (Uart_Regs_t*)0x4009C000}; 

// Initialize all UARTs with default configuration
void UART_Init(Uart_Idx_t UartIdx, Uart_Config_t UartCfg) 
{
	/* setup Word Length Select */
	UartRegs[UartIdx]->LCR.B.WordLenSel = UartCfg.WordLength;
		
	if( UartCfg.StopBitSel == UART_CFG_PAR_1STPBIT )
	{
		UartRegs[UartIdx]->LCR.B.StpBitSel = 0;
	}
	else
	{
		UartRegs[UartIdx]->LCR.B.StpBitSel = 1;
	}
	
	if( UartCfg.ParEn==FALSE )
	{
		UartRegs[UartIdx]->LCR.B.ParEn = 0;
	}
	else
	{
		UartRegs[UartIdx]->LCR.B.ParEn = 1;
	}
	
	UartRegs[UartIdx]->LCR.B.ParSel = UartCfg.ParSel;
	
	if( UartCfg.BreakCtrlEn==FALSE )
	{
		UartRegs[UartIdx]->LCR.B.BrkCtrl = 0;
	}
	else
	{
		UartRegs[UartIdx]->LCR.B.BrkCtrl = 1;
	}	
	
	/* Set the baud rate */
	UART_SetBaudRate(UartIdx, UartCfg.PCLK_MHz, UartCfg.BaudRate);
}

// Configure UART with specific settings
void UART_SetBaudRate(Uart_Idx_t UartIdx, uint8 PCLK_UART_MHz, Uart_BaudRate_t BaudRate) 
{
	uint16 DivVal;
	
	DivVal = MEGA(PCLK_UART_MHz)/(16 * BaudRate);
	
	/* Set DLAB bit in LCR to access DLL and DLM registers */
	UartRegs[UartIdx]->LCR.B.DLAB = 1;
	while(!UartRegs[UartIdx]->LCR.B.DLAB);
	
	UartRegs[UartIdx]->RBR_THR_DLL.DLL.R = (DivVal & 0x00FF);
	UartRegs[UartIdx]->DLM_IER.DLM.R     = (DivVal & 0xFF00)>>8;
	
	/* Set DLAB = 0 */
	UartRegs[UartIdx]->LCR.B.DLAB = 0;
	while(UartRegs[UartIdx]->LCR.B.DLAB);
}

// Send a single byte
void UART_SendByte(Uart_Idx_t UartIdx, uint8 TxData) 
{	
	/* Set DLAB bit in LCR to 0 to access THR register */
	UartRegs[UartIdx]->LCR.B.DLAB = 0;
	while(UartRegs[UartIdx]->LCR.B.DLAB);

	/* Update the data to be sent in the holding register */
	UartRegs[UartIdx]->RBR_THR_DLL.THR.R = TxData;
	
	/* wait until data has been transmitted */
	while( !(UartRegs[UartIdx]->LSR.B.TEMT) );
}

// Send multiple bytes
void UART_SendString(Uart_Idx_t UartIdx, uint8 *data, uint8 length) 
{
	uint8 i;
	
  for(i=0 ; i<length ; i++) 
	{
		UART_SendByte(UartIdx, data[i]);
  }
}

// Receive a single byte
bool UART_ReceiveByte(Uart_Idx_t UartIdx, uint8 *data) 
{
	return(0);
}

// Receive multiple bytes
void UART_ReceiveData(Uart_Idx_t UartIdx, uint8 *buffer, uint8 length) 
{
}
