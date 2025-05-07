/*
 *	Rte_SWC_LgcRM.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_LGCRM_H_
#define RTE_SWCL_LGCRM_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_LgcRM_LegicResponseMonitor_Cyclic05ms SWC_LgcRM_LegicResponseMonitor_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Call_SWCS_LgcRM_UBSW_Mgr_LegicCommand_SetCommand(unsignedByte_group, unsignedByte_cmd, unsignedByte_type, Ptr_byte_length)      (SetCommand(unsignedByte_group, unsignedByte_cmd, unsignedByte_type, Ptr_byte_length), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_LgcRM_UBSW_Mgr_ManagerRte_FlagEvnote(unsignedByte_EvNote)      (FlagEvnote(unsignedByte_EvNote), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_LgcRM_UBSW_Mgr_ManagerRte_ReadActivation(unsignedByte_acti, Ptr_boolean_value)      (ReadActivation(unsignedByte_acti, Ptr_boolean_value), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_LgcRM_UBSW_Mgr_ManagerRte_ResetActivation(unsignedByte_activation)      (ResetActivation(unsignedByte_activation), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_LgcRM_UBSW_Mgr_ManagerRte_ReadState(unsignedByte_state, Ptr_boolean_out)      (ReadState(unsignedByte_state, Ptr_boolean_out), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_LgcRM_UBSW_Mgr_LegicResponse_GetLegicCommandResponse(unsignedByte_kind, unsignedByte_response, unsignedByte_command, Ptr_unsignedByte_length)      (GetLegicCommandResponse(unsignedByte_kind, unsignedByte_response, unsignedByte_command, Ptr_unsignedByte_length), ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_LgcRM_SWCS_CCM_SignalStrengthForDistance(float_SignalStrengthForDistance)      ((float_SignalStrengthForDistance)= Rte_SWCS_CCM_SignalStrengthForDistance, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_LgcRM_SWCS_CCM_SignalStrengthForDistance(float_SignalStrengthForDistance)      ((Rte_SWCS_CCM_SignalStrengthForDistance)= float_SignalStrengthForDistance, ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_LgcRM_SWCS_CCM_BleDistances(unsigned5Byte_BleDistances)      ((unsigned5Byte_BleDistances)= Rte_SWCS_CCM_BleDistances, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_LgcRM_SWCS_CCM_BleDistances(unsigned5Byte_BleDistances)      ((Rte_SWCS_CCM_BleDistances)= unsigned5Byte_BleDistances, ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_LgcRM_APPL_CODE)SWC_LgcRM_Cyclic05ms(void);
FUNC(void, RTE_SWC_LgcRM_APPL_CODE)SWC_LgcRM_Init(void);

#endif /* RTE_SWCL_LgcRM_H_ */