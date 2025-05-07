/*
 *	Rte_SWC_CoMD.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_COMD_H_
#define RTE_SWCL_COMD_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_CoMD_Cyclic05ms SWC_CoMD_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Call_SWCS_CoMD_UBSW_Mgr_CompuMethodDecoder_DecodeSignalValue(unsignedByte_SignalID, unsignedByte_DecodedValue, Ptr_unsignedByte_Value)      (DecodeSignalValue(unsignedByte_SignalID, unsignedByte_DecodedValue, Ptr_unsignedByte_Value), ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_CoMD_APPL_CODE)SWC_CoMD_Cyclic05ms(void);
FUNC(void, RTE_SWC_CoMD_APPL_CODE)SWC_CoMD_Init(void);

#endif /* RTE_SWCL_CoMD_H_ */