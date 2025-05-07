/*
 *	Rte_SWC_LgcSPI.h
 *
 *	Created on: Fri May 14 11:47:34 IST 2021
 *	Author: PrashantGupta
 */
/* double include prevention */
#ifndef RTE_SWCL_LGCSPI_H_
#define RTE_SWCL_LGCSPI_H_
#include "../Rte.h"
#include "../../RTE/Rte_Type.h"
/**********************************************************************************************************************
 * Schedular Mapping
 *********************************************************************************************************************/
#define RTE_RUNNABLE_SWC_LgcSPI_LgcSpi_Cyclic05ms SWC_LgcSPI_LgcSpi_Cyclic05ms

/**********************************************************************************************************************
 * API prototypes
 *********************************************************************************************************************/
#define Rte_Call_SWCS_LgcSPI_UBSW_Mgr_LegicCommand_SetCommand(unsignedByte_group, unsignedByte_cmd, unsignedByte_type, Ptr_byte_length)      (SetCommand(unsignedByte_group, unsignedByte_cmd, unsignedByte_type, Ptr_byte_length), ((Std_ReturnType)RTE_E_OK))
#define Rte_Call_SWCS_LgcSPI_UBSW_Mgr_LegicResponse_GetLegicCommandResponse(unsignedByte_kind, unsignedByte_response, unsignedByte_command, Ptr_unsignedByte_length)      (GetLegicCommandResponse(unsignedByte_kind, unsignedByte_response, unsignedByte_command, Ptr_unsignedByte_length), ((Std_ReturnType)RTE_E_OK))




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

FUNC(void, RTE_SWC_LgcSPI_APPL_CODE)SWC_LgcSPI_Cyclic05ms(void);
FUNC(void, RTE_SWC_LgcSPI_APPL_CODE)SWC_LgcSPI_Init(void);

#endif /* RTE_SWCL_LgcSPI_H_ */