#ifndef CAN_DRIVER_H
#define CAN_DRIVER_H

#include "Types.h"

typedef enum {CANCTRL1, CANCTRL2} CanIdx_t;

typedef union
{
	struct
	{
		uint32 value:11;
		uint32 :21;
	}StdId;
	struct
	{
		uint32 value:29;
		uint32 :2;
	}ExtId;
}CanId_t;

typedef struct 
{
    uint8 data[8];
		CanId_t id;
    uint8 len;
} CanMsg_t;

void CAN_Init(CanIdx_t);
void CAN_Set_BaudRate(CanIdx_t, uint8, uint8, uint8, uint8);
void CAN_Transmit(CanIdx_t, CanMsg_t);
bool CAN_Receive(CanIdx_t *, CanMsg_t *);

#endif
