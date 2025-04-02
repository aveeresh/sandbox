#ifndef MCU_REGS_H
#define MCU_REGS_H

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 CLKSRC:2;
		uint32 :30;
	}B;
}Mcu_CLKSRCSEL_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 PLLE:1;
		uint32 PLLC:1;
		uint32 :30;
	}B;
}Mcu_PLLCON_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 MSEL:15;
		uint32 :1;
		uint32 NSEL:1;
		uint32 :8;
	}B;
}Mcu_PLLCFG_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 MSEL:15;
		uint32 :1;
		uint32 NSEL:1;
		uint32 PLLE_STAT:1;
		uint32 PLLC_STAT:1;			
		uint32 PLOCK:1;			
		uint32 :5;			
	}B;
}Mcu_PLLSTAT_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 PLLFEED:8;
		uint32 :24;			
	}B;
}Mcu_PLLFEED_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 CCLKSEL:8;
		uint32 :24;			
	}B;
}Mcu_CCLKCFG_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 USBSEL:4;
		uint32 :24;			
	}B;
}Mcu_USBCLKCFG_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 PCLK_WDT:2;
		uint32 PCLK_TIMER0:2;
		uint32 PCLK_TIMER1:2;
		uint32 PCLK_UART0:2;
		uint32 PCLK_UART1:2;
		uint32 :2;
		uint32 PCLK_PWM1:2;
		uint32 PCLK_I2C0:2;
		uint32 PCLK_SPI:2;
		uint32 :2;
		uint32 PCLK_SSP1:2;
		uint32 PCLK_DAC:2;
		uint32 PCLK_ADC:2;
		uint32 PCLK_CAN1:2;
		uint32 PCLK_CAN2:2;
		uint32 PCLK_ACF:2;
	}B;
}Mcu_PCLKSEL0_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 PCLK_QEI:2;
		uint32 PCLK_GPIOINT:2;
		uint32 PCLK_PCB:2;
		uint32 PCLK_I2C1:2;
		uint32 :2;
		uint32 PCLK_SSP0:2;
		uint32 PCLK_TIMER2:2;
		uint32 PCLK_TIMER3:2;
		uint32 PCLK_UART2:2;
		uint32 PCLK_UART3:2;
		uint32 PCLK_I2C2:2;
		uint32 PCLK_I2S:2;
		uint32 :2;
		uint32 PCLK_RIT:2;
		uint32 PCLK_SYSCON:2;
		uint32 PCLK_MC:2;
	}B;
}Mcu_PCLKSEL1_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 PM0:1;
		uint32 PM1:1;
		uint32 BODRPM:1;
		uint32 BOGD:1;
		uint32 BORD:1;
		uint32 :5;
		uint32 SMFLAG:1;
		uint32 DSFLAG:1;
		uint32 PDFLAG:1;
		uint32 DPDFLAG:1;
		uint32 :18;
	}B;	
}Mcu_PCON_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 :1;
		uint32 PCTIM0:1;
		uint32 PCTIM1:1;
		uint32 PCUART0:1;
		uint32 PCUART1:1;
		uint32 :1;
		uint32 PCPWM1:1;
		uint32 PCI2C0:1;
		uint32 PCSPI:1;
		uint32 PCRTC:1;
		uint32 PCSSP1:1;		
		uint32 :1;
		uint32 PCADC:1;
		uint32 PCCAN1:1;
		uint32 PCCAN2:1;
		uint32 PCGPIO:1;
		uint32 PCRIT:1;
		uint32 PCMCPWM:1;
		uint32 PCQEI:1;
		uint32 PCI2C1:1;
		uint32 :1;
		uint32 PCSSP0:1;
		uint32 PCTIM2:1;
		uint32 PCTIM3:1;
		uint32 PCUART2:1;
		uint32 PCUART3:1;
		uint32 PCI2C2:1;
		uint32 PCI2S:1;
		uint32 :1;
		uint32 PCGPDMA:1;
		uint32 PCENET:1;
		uint32 PCUSB:1;
	}B;
}Mcu_PCONP_t;
	
/* 
 * Define register collections here 
 *
*/
typedef struct
{
	volatile Mcu_CLKSRCSEL_t CLKSRCSEL;
}Mcu_ClkRegs_t;

typedef struct
{
	volatile Mcu_PLLCON_t  PLLCON;
	volatile Mcu_PLLCFG_t  PLLCFG;
	volatile Mcu_PLLSTAT_t PLLSTAT;
	volatile Mcu_PLLFEED_t PLLFEED;
}Mcu_PLLRegs_t;

typedef struct
{
	volatile Mcu_CCLKCFG_t   CCLKCFG;
	volatile Mcu_USBCLKCFG_t USBCLKCFG;
	volatile Mcu_PCLKSEL0_t  PCLKSEL0;
	volatile Mcu_PCLKSEL1_t  PCLKSEL1;
}Mcu_ClkDivRegs_t;

typedef struct 
{
	volatile Mcu_PCON_t  PCON;
	volatile Mcu_PCONP_t PCONP;
}Mcu_PwrCtrlRegs_t;
#endif
