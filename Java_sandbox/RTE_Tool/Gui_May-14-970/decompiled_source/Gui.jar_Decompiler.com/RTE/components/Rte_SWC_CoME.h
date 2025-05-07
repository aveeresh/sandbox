/*
 *	Rte_SWC_CoME.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_COME_H_
#define RTE_SWCL_COME_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_CoME_Cyclic05ms SWC_CoME_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Call_SWCS_CoME_UBSW_Mgr_CompuMethodEncoder_EncodeSignal(unsignedByte_Signal ID, unsignedByte_Value, Ptr_unsignedByte_Encoded Value)      (EncodeSignal(unsignedByte_Signal ID, unsignedByte_Value, Ptr_unsignedByte_Encoded Value), ((Std_ReturnType)RTE_E_OK))




/**********************************************************************************************************************
 * Rte_Read_<p>_<d> (explicit S/R communication with isQueued = false)
 *********************************************************************************************************************/

/**********************************************************************************************************************
 * Rte_IsUpdated_<p>_<d> (explicit S/R communication with isQueued = false)
 *********************************************************************************************************************/

/**********************************************************************************************************************
 * Rte_Write_<p>_<d> (explicit S/R communication with isQueued = false)
 *********************************************************************************************************************/

/**********************************************************************************************************************
 * Rte_Call_<p>_<o> (unmapped) for synchronous C/S communication
 *********************************************************************************************************************/

FUNC(void, RTE_SWC_CoME_APPL_CODE)SWC_CoME_Cyclic05ms(void);
FUNC(void, RTE_SWC_CoME_APPL_CODE)SWC_CoME_Init(void);

#endif /* RTE_SWCL_CoME_H_ */