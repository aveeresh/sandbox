/*
 *	Rte_SWC_VehL.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_VEHL_H_
#define RTE_SWCL_VEHL_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_VehL_Cyclic05ms SWC_VehL_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Call_SWCS_VehL_UBSW_Mgr_CompuMethodDecoder_DecodeSignalValue(unsignedByte_SignalID, unsignedByte_DecodedValue, Ptr_unsignedByte_Value)      (DecodeSignalValue(unsignedByte_SignalID, unsignedByte_DecodedValue, Ptr_unsignedByte_Value), ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_VehL_SWCS_CCM_VehicleDoorsLockStatus(unsigned1Byte_VehicleDoorsLockStatus)      ((unsigned1Byte_VehicleDoorsLockStatus)= Rte_SWCS_CCM_VehicleDoorsLockStatus, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_VehL_SWCS_CCM_VehicleDoorsLockStatus(unsigned1Byte_VehicleDoorsLockStatus)      ((Rte_SWCS_CCM_VehicleDoorsLockStatus)= unsigned1Byte_VehicleDoorsLockStatus, ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_VehL_APPL_CODE)SWC_VehL_Cyclic05ms(void);
FUNC(void, RTE_SWC_VehL_APPL_CODE)SWC_VehL_Init(void);

#endif /* RTE_SWCL_VehL_H_ */