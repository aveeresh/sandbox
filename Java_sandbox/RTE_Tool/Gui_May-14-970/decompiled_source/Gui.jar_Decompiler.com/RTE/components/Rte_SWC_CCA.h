/*
 *	Rte_SWC_CCA.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_CCA_H_
#define RTE_SWCL_CCA_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_CCA_Cyclic05ms SWC_CCA_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Call_SWCS_CCA_UBSW_Mgr_ManagerRte_FlagEvnote(unsignedByte_EvNote, unsignedByte_EvNote2, Ptr_wer_werfwe)      (FlagEvnote(unsignedByte_EvNote, unsignedByte_EvNote2, Ptr_wer_werfwe), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_CCA_UBSW_Mgr_ManagerRte_ReadActivation(unsignedByte_acti, Ptr_boolean_value)      (ReadActivation(unsignedByte_acti, Ptr_boolean_value), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_CCA_UBSW_Mgr_ManagerRte_ResetActivation(unsignedByte_activation)      (ResetActivation(unsignedByte_activation), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_CCA_UBSW_Mgr_ManagerRte_ReadState(unsignedByte_state, Ptr_boolean_out)      (ReadState(unsignedByte_state, Ptr_boolean_out), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_CCA_UBSW_Mgr_VoidType_sdfsdf)      (sdfsdf), ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_CCA_SWCS_CCM_ConfigurationAndCalibration(unsigned54Byte_ConfigurationAndCalibration)      ((unsigned54Byte_ConfigurationAndCalibration)= Rte_SWCS_CCM_ConfigurationAndCalibration, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_CCA_SWCS_CCM_ConfigurationAndCalibration(unsigned54Byte_ConfigurationAndCalibration)      ((Rte_SWCS_CCM_ConfigurationAndCalibration)= unsigned54Byte_ConfigurationAndCalibration, ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_CCA_SWCS_CCM_Void(Base_Void)      ((Base_Void)= Rte_SWCS_CCM_Void, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_CCA_SWCS_CCM_Void(Base_Void)      ((Rte_SWCS_CCM_Void)= Base_Void, ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_CCA_APPL_CODE)SWC_CCA_Cyclic05ms(void);
FUNC(void, RTE_SWC_CCA_APPL_CODE)SWC_CCA_Init(void);

#endif /* RTE_SWCL_CCA_H_ */