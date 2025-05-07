/*
 *	Rte_SWC_DfSAct.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_DFSACT_H_
#define RTE_SWCL_DFSACT_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_DfSAct_Cyclic05ms SWC_DfSAct_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Call_SWCS_DfSAct_UBSW_Mgr_LegicCommand_SetCommand(unsignedByte_group, unsignedByte_cmd, unsignedByte_type, Ptr_byte_length)      (SetCommand(unsignedByte_group, unsignedByte_cmd, unsignedByte_type, Ptr_byte_length), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_DfSAct_UBSW_Mgr_CompuMethodEncoder_EncodeSignal(unsignedByte_Signal ID, unsignedByte_Value, Ptr_unsignedByte_Encoded Value)      (EncodeSignal(unsignedByte_Signal ID, unsignedByte_Value, Ptr_unsignedByte_Encoded Value), ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_DfSAct_SWCS_CCM_UwbDistanceOverCan(unsigned8Byte_UwbDistanceOverCan)      ((unsigned8Byte_UwbDistanceOverCan)= Rte_SWCS_CCM_UwbDistanceOverCan, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_DfSAct_SWCS_CCM_UwbDistanceOverCan(unsigned8Byte_UwbDistanceOverCan)      ((Rte_SWCS_CCM_UwbDistanceOverCan)= unsigned8Byte_UwbDistanceOverCan, ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_DfSAct_SWCS_CCM_UwbDistance(unsigned10Byte_UwbDistance)      ((unsigned10Byte_UwbDistance)= Rte_SWCS_CCM_UwbDistance, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_DfSAct_SWCS_CCM_UwbDistance(unsigned10Byte_UwbDistance)      ((Rte_SWCS_CCM_UwbDistance)= unsigned10Byte_UwbDistance, ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_DfSAct_APPL_CODE)SWC_DfSAct_Cyclic05ms(void);
FUNC(void, RTE_SWC_DfSAct_APPL_CODE)SWC_DfSAct_Init(void);

#endif /* RTE_SWCL_DfSAct_H_ */