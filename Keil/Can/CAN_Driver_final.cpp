#include<stdio.h>
#include<stdint.h>

typedef unsigned short uint16;
typedef unsigned char uint8;
typedef unsigned int uint32;
//unsigned int x;

typedef struct {
    uint32 CAN_id;
    uint8 data[8]; // Maximum CAN payload size
    uint8 len;
} CAN_msg;

/**
* @brief the Initialization of CAN
* @param CAN_idx: index to CAN
   *@valid values: 1 - CAN1
                   2 - CAN2
* return none 
**/
void CAN_Init(uint8 CAN_idx);

/**
* @brief the pin configuration of CAN
* @param : none
* return none 

void CAN_PinConfig(void);
**/


/**
* @brief  the Baud rate of CAN
*@param CAN_idx: index to CAN
*@valid values: 1 - CAN1
                2 - CAN2
*@param baudrate_Kbps : the baud rate of the CAN data to be transmitted
  * valid values: 125, 250, 500, 1000 in kbps
*@param PCLK_MHz :Peripheral clock frequency (PCLK) in MHz 

*@param TSEG1 : Time segment 1 value 

*@param TSEG2 : Time segment 2 value 
* return none 
**/
void CAN_Set_BaudRate(uint8 CAN_idx, uint16 baudrate_Kbps, uint8 PCLK_MHz, uint8 TSEG1, uint8 TSEG2);

/**
* @brief the data transmitted from CAN
*@param CAN_idx: index to CAN
*@valid values: 1 - CAN1
                2 - CAN2
*@param CAN_msg : structure consisting CAN msg details mentioned below 				
*@param id: CAN Message ID (Standard: 11-bit, Extended: 29-bit).
   * valid values: Standard Id- 0x000 to 0x7FF
                   Extended Id- 0x00000000 to 0x1FFFFFFF 
*@param data: Pointer to the data buffer
   * valid values: any data of 0 to 8 bytes 
*@param len : Number of bytes in the CAN message 
   * valid values: 0 to 8
*return none 
**/

void CAN_Transmit(uint8 CAN_idx, CAN_msg *msg);

/**
*@brief Receives a message from the CAN
*@param idx: index to CAN
   *@valid values: 1 - CAN1
                   2 - CAN2
*@param CAN_msg : structure consisting CAN msg details mentioned below 

*@param id: Pointer to store the received CAN Message ID
   * valid values: Standard Id- 0x000 to 0x7FF
                             Extended Id- 0x00000000 to 0x1FFFFFFF 
*@param data: Pointer to the data buffer to store received bytes.
   * valid values: any data of 0 to 8 bytes 
*@param len: Number of bytes received (DLC value)
     * valid values: 0 to 8
* return none
**/

void CAN_Receive(uint8 CAN_idx, CAN_msg *msg);


/*#define MEG(x) (x * 1000 * 1000)

#define KIL(x) (x * 1000)


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