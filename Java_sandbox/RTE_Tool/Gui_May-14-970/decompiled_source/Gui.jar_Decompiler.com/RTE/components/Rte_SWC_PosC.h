/*
 *	Rte_SWC_PosC.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_POSC_H_
#define RTE_SWCL_POSC_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_PosC_PositionCalculationRunnable_Cyclic05ms SWC_PosC_PositionCalculationRunnable_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Read_SWCS_PosC_SWCS_CCM_SmartDevicesPositions(unsigned55Byte_SmartDevicesPositions)      ((unsigned55Byte_SmartDevicesPositions)= Rte_SWCS_CCM_SmartDevicesPositions, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_PosC_SWCS_CCM_SmartDevicesPositions(unsigned55Byte_SmartDevicesPositions)      ((Rte_SWCS_CCM_SmartDevicesPositions)= unsigned55Byte_SmartDevicesPositions, ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_PosC_SWCS_CCM_BleDistances(unsigned5Byte_BleDistances)      ((unsigned5Byte_BleDistances)= Rte_SWCS_CCM_BleDistances, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_PosC_SWCS_CCM_BleDistances(unsigned5Byte_BleDistances)      ((Rte_SWCS_CCM_BleDistances)= unsigned5Byte_BleDistances, ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_PosC_SWCS_CCM_SignalStrengthForDistance(float_SignalStrengthForDistance)      ((float_SignalStrengthForDistance)= Rte_SWCS_CCM_SignalStrengthForDistance, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_PosC_SWCS_CCM_SignalStrengthForDistance(float_SignalStrengthForDistance)      ((Rte_SWCS_CCM_SignalStrengthForDistance)= float_SignalStrengthForDistance, ((Std_ReturnType)RTE_E_OK))
#define Rte_Read_SWCS_PosC_SWCS_CCM_UwbDistance(unsigned10Byte_UwbDistance)      ((unsigned10Byte_UwbDistance)= Rte_SWCS_CCM_UwbDistance, ((Std_ReturnType)RTE_E_OK))
#define Rte_Write_SWCS_PosC_SWCS_CCM_UwbDistance(unsigned10Byte_UwbDistance)      ((Rte_SWCS_CCM_UwbDistance)= unsigned10Byte_UwbDistance, ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_PosC_APPL_CODE)SWC_PosC_Cyclic05ms(void);
FUNC(void, RTE_SWC_PosC_APPL_CODE)SWC_PosC_Init(void);

#endif /* RTE_SWCL_PosC_H_ */