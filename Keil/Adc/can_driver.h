#include<stdio.h>
#include<stdint.h>

typedef unsigned short uint16_t;
typedef unsigned char uint8_t;
typedef unsigned int uint32_t;
//unsigned int x;


/**
* @brief the Initialization of CAN
* @param : none
* return none 
**/
//void CAN_Init(LPC_CAN_TypeDef *CANx);
void CAN_Init(void);
/**
* @brief the pin configuration of CAN
* @param : none
* return none 
**/
void CAN_PinConfig(void);

/**
* @brief  the Baud rate of CAN
*@param baud : the baud rate of the CAN data to be transmitted
  * valid values: 125 ...100000 in kbps
*@param clock: the peripheral clock frequency in MHz 
  * valid values: 100 MHz, 50 MHz, 25 MHz, 12.5 MHz
* return none 
**/
void CAN_Set_BRate(uint32_t baudrate, uint32_t pclk);

//void CAN_Set_BaudRate(LPC_CAN_TypeDef *CANx,uint32_t baudrate, uint32_t pclk);

/**
* @brief the data transmitted from CAN
*@param id: CAN Message ID (Standard: 11-bit, Extended: 29-bit).
   * valid values: Standard Id- 0x000 to 0x7FF
                             Extended Id- 0x00000000 to 0x1FFFFFFF 
*@param data: Pointer to the data buffer
   * valid values: any data of 0 to 8 bytes 
*@param len : Number of bytes in the CAN message 
   * valid values: 0 to 8
*return none 
**/
void CAN_Transmit (uint32_t id, uint8_t *data, uint8_t len);

/**
* @brief Receives a message from the CAN
*@param id: Pointer to store the received CAN Message ID
   * valid values: Standard Id- 0x000 to 0x7FF
                             Extended Id- 0x00000000 to 0x1FFFFFFF 
*@param data: Pointer to the data buffer to store received bytes.
   * valid values: any data of 0 to 8 bytes 
*return Number of bytes received (DLC value)
     * valid values: 0 to 8 bytes
**/

uint8_t  CAN_Receive(uint32_t *id, uint8_t *data); 


/*#define MEG(x) (x * 1000 * 1000)

#define IL(x) (x * 1000)


#define IL(x) (x)
#define MEG(x) ((x) * 1000000)



//length macro
#define e_one  0
#define e_two 1
#define e_three 2
#define e_four 3
#define e_five 4
#define e_six 5
#define e_seven 6
#define e_eight 7
*/