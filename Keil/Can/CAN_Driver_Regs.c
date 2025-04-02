#include "CAN_Driver.h"
#include "Can_Regs.h"

#define MEGA(x) (x * 1000000)
#define KILO(x) (x * 1000)

static Can_GeneralRegs_t *CanGeneralRegs[] = {(Can_GeneralRegs_t*)0x40044000, (Can_GeneralRegs_t*)0x40048000};
static Can_CentralRegs_t *CanCentralRegs = (Can_CentralRegs_t*)0x40040000; 
static Can_AccpFltrRegs_t *CanAccpFltrRegs = (Can_AccpFltrRegs_t*)0x4003C000;

void CAN_Init(CanIdx_t CanIdx) 
{
	CanAccpFltrRegs->AFMR.B.AccOff = 0;
	CanAccpFltrRegs->AFMR.B.AccBP  = 1;
}

void CAN_Set_BaudRate(CanIdx_t CanIdx, uint8 BRP, uint8 SJW, uint8 TSEG1, uint8 TSEG2) 
{
		#if 0
		CanGeneralRegs[CanIdx]->MOD.R  |= 0x01; // Enter Reset Mode
		temp = &(CanGeneralRegs[CanIdx-1]->BTR.R);
    CanGeneralRegs[CanIdx]->BTR.R = btr_value; // Set BTR register
		//CanGeneralRegs[CanIdx-1]->MOD.R |= (0x01<<2); //enable self test mode

    CanGeneralRegs[CanIdx]->MOD.R &= (~(0x01) & 0xBF); // Exit Reset Mode
		#else
		CanGeneralRegs[CanIdx]->MOD.B.RM = 1;
		CanGeneralRegs[CanIdx]->BTR.B.SJW = SJW;
		CanGeneralRegs[CanIdx]->BTR.B.BRP = BRP;
		CanGeneralRegs[CanIdx]->BTR.B.TSEG1 = TSEG1;
		CanGeneralRegs[CanIdx]->BTR.B.TSEG2 = TSEG2;
		CanGeneralRegs[CanIdx]->MOD.B.RM = 0;
		#endif
}

void CAN_Transmit(CanIdx_t CanIdx, CanMsg_t TxMsg) 
{
		//while (!(CAN1->SR & (1 << 2))); // Wait until TX buffer is empty
		CanGeneralRegs[CanIdx]->TFI1.B.DLC    = TxMsg.len;
		CanGeneralRegs[CanIdx]->TID1.StdId.Id = TxMsg.id.StdId.value;
		CanGeneralRegs[CanIdx]->TDA1.R        = TxMsg.data[0]; // Load first 4 bytes
		CanGeneralRegs[CanIdx]->TDB1.R        = TxMsg.data[4]; // Load next 4 bytes
		CanGeneralRegs[CanIdx]->CMR.R         = (1 << 5) | (1 << 0); // Request transmission
		//while(!((CAN1->GSR)&(0x01<<3)));
}

bool CAN_Receive(CanIdx_t *CanIdx, CanMsg_t *RxMsg)
{
	bool RxStatus = FALSE;
	uint32 TimeOutCount = 5000000;
	uint32 RDA, RDB;
	int i;
	
	while(!RxStatus && --TimeOutCount)
	{
		//RxStatus = CanCentralRegs->CANRxSR.B.RB1 || CanCentralRegs->CANRxSR.B.RB2;
	  RxStatus = CanGeneralRegs[CANCTRL1]->GSR.B.RBS || CanGeneralRegs[CANCTRL2]->GSR.B.RBS;
	}

	if( RxStatus==TRUE )
	{
		//Either CAN1 or CAN2 is receiving a message, process it further
		if( CanCentralRegs->CANRxSR.B.RB1 )
		{
			*CanIdx = CANCTRL1;
			RxMsg->len            = CanGeneralRegs[CANCTRL1]->RFS.B.DLC;
			RxMsg->id.StdId.value = CanGeneralRegs[CANCTRL1]->RID.StdId.Id;
			RDA                   = CanGeneralRegs[CANCTRL1]->RDA.R;
			RDB                   = CanGeneralRegs[CANCTRL1]->RDB.R;
			
			for( i=0 ; i<RxMsg->len ; i++ )
			{
				if( i<4 )
				{
					RxMsg->data[i] = (RDA & (0x000000FF<<(8*(i%4))))>>(8*(i%4));
				}
				else
				{
					RxMsg->data[i] = (RDB & (0x000000FF<<(8*(i%4))))>>(8*(i%4));
				}					
			}
		}
		else
		{
			*CanIdx = CANCTRL2;
			RxMsg->len            = CanGeneralRegs[CANCTRL2]->RFS.B.DLC;
			RxMsg->id.StdId.value = CanGeneralRegs[CANCTRL2]->RID.StdId.Id;
			RxMsg->data[0]        = CanGeneralRegs[CANCTRL2]->RDA.R;
			RxMsg->data[4]        = CanGeneralRegs[CANCTRL2]->RDB.R;		
		}
	}
	
	return(RxStatus);
}
