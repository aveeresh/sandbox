/*
 *	Rte_SWC_DfUA.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_DFUA_H_
#define RTE_SWCL_DFUA_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_DfUA_Cyclic05ms SWC_DfUA_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Call_SWCS_DfUA_UBSW_Mgr_CompuMethodDecoder_DecodeSignalValue(unsignedByte_SignalID, unsignedByte_DecodedValue, Ptr_unsignedByte_Value)      (DecodeSignalValue(unsignedByte_SignalID, unsignedByte_DecodedValue, Ptr_unsignedByte_Value), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_DfUA_UBSW_Mgr_LegicResponse_GetLegicCommandResponse(unsignedByte_kind, unsignedByte_response, unsignedByte_command, Ptr_unsignedByte_length)      (GetLegicCommandResponse(unsignedByte_kind, unsignedByte_response, unsignedByte_command, Ptr_unsignedByte_length), ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_DfUA_SWCS_CCM_UwbDistance(unsigned10Byte_UwbDistance)      ((unsigned10Byte_UwbDistance)= Rte_SWCS_CCM_UwbDistance, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_DfUA_SWCS_CCM_UwbDistance(unsigned10Byte_UwbDistance)      ((Rte_SWCS_CCM_UwbDistance)= unsigned10Byte_UwbDistance, ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_DfUA_SWCS_CCM_UwbDistanceOverCan(unsigned8Byte_UwbDistanceOverCan)      ((unsigned8Byte_UwbDistanceOverCan)= Rte_SWCS_CCM_UwbDistanceOverCan, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_DfUA_SWCS_CCM_UwbDistanceOverCan(unsigned8Byte_UwbDistanceOverCan)      ((Rte_SWCS_CCM_UwbDistanceOverCan)= unsigned8Byte_UwbDistanceOverCan, ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_DfUA_APPL_CODE)SWC_DfUA_Cyclic05ms(void);
FUNC(void, RTE_SWC_DfUA_APPL_CODE)SWC_DfUA_Init(void);

#endif /* RTE_SWCL_DfUA_H_ */