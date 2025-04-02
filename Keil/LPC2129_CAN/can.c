#include <lpc21xx.h>

#define LED_PIN 18
#define LED_BLINK_COUNT 3

int main()
{
	int i, led_count;
	unsigned char ERRBIT;
	unsigned char count=1;
	
	//setup PLL for XTAL=10MHz CCLK = 50MHz
	PLLCFG = (0x4) | (0x2<<5);		//MSEL=5 PSEL=3
	PLLCON |= (0x01) | (0x01<<1); 
	PLLFEED = 0xAA;
	for(i=0 ; i<10000 ; i++);
	PLLFEED = 0x55;
	while( !(PLLSTAT & (0x01<<10)));
	
	VPBDIV = 0x01;
	
	//setup CAN2 Tx(P0.24) and Rx(P0.25) pins
	PINSEL1 |= (0x01<<16) | (0x01<<18);
	
	//Setup CAN2 controller baudrate = 500 kbps SP=68% BRP=3 SJW=1 TSEG1=15 TSEG2=7
	C2MOD |= 0x01;
	C2BTR = (0x03) | (0x0F<<16) | (0x07<<20);
	C2MOD &= ~(0x01);
	
	//setup blinking LED
	IO0DIR |= (0x01<<LED_PIN);
	
	//prepare CAN message for transmission
	C2TFI1 = ((0x07)<<16);
	C2TID1 = (0x123);
	C2TDA2 = 0x55555555;

	//alive status when debugger is not connected
	for(led_count=0 ; led_count<LED_BLINK_COUNT ; led_count++ )
	{
		IO0SET |= (0x01<<LED_PIN);
		for(i=0 ; i<4000000 ; i++);
		IO0CLR |= (0x01<<LED_PIN);
		for(i=0 ; i<4000000 ; i++);			
	}

	while(1)
	{
		C2TDA1 = 0x55555500 | (count++);
		C2CMR |= (0x01<<5) | (0x01<<0);
		//wait for successful transmission
		while(!(C2GSR & (0x01<<3)));
		
		//blink LED(P0.18) is ACK error
		ERRBIT = ((C2ICR & (0x1F<<16))>>16); 
		if( ERRBIT==0x19 )
		{
			IO0SET = (0x01<<LED_PIN);
			for(i=0 ; i<4000000 ; i++);
			IO0CLR = (0x01<<LED_PIN);
			for(i=0 ; i<4000000 ; i++);			
		}
		
		for(i=0 ; i<4000000 ; i++);
	}
	
}
