/*
 *	Rte_SWC_PosLog.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_POSLOG_H_
#define RTE_SWCL_POSLOG_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_PosLog_Cyclic05ms SWC_PosLog_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Read_SWCS_PosLog_SWCS_CCM_PositionResult(unsigned7Byte_PositionResult)      ((unsigned7Byte_PositionResult)= Rte_SWCS_CCM_PositionResult, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_PosLog_SWCS_CCM_PositionResult(unsigned7Byte_PositionResult)      ((Rte_SWCS_CCM_PositionResult)= unsigned7Byte_PositionResult, ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_PosLog_SWCS_CCM_SmartDevicesPositions(unsigned55Byte_SmartDevicesPositions)      ((unsigned55Byte_SmartDevicesPositions)= Rte_SWCS_CCM_SmartDevicesPositions, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_PosLog_SWCS_CCM_SmartDevicesPositions(unsigned55Byte_SmartDevicesPositions)      ((Rte_SWCS_CCM_SmartDevicesPositions)= unsigned55Byte_SmartDevicesPositions, ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_PosLog_APPL_CODE)SWC_PosLog_Cyclic05ms(void);
FUNC(void, RTE_SWC_PosLog_APPL_CODE)SWC_PosLog_Init(void);

#endif /* RTE_SWCL_PosLog_H_ */