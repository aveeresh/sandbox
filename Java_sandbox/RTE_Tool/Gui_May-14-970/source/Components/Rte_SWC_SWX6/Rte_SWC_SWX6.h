/*****************************************
*     Created on:	2021/03/22
*     Author:    	Prashant Gupta
*****************************************/


#ifndef	Rte_SWC_SWX6_SWCA_SENSOR_H_
#define	Rte_SWC_SWX6_SWCA_SENSOR_H_
/*****************************************
*     Schedular Mapping
*****************************************/

#define	Rte_SWC_SWX6_SWCA_SENSOR__RUNNABLE_null	Rte_SWC_SWX6_SWCA_SENSOR_


/*****************************************
*     API prototypes
*****************************************/

#define	Rte_Call_SWS_Rte_SWC_SWX6_null_null_StrainGuageAdcVal(null) (							              Adc_getConvertedValue( null , null) , ((RTE_E_OK)null))

#define	Rte_Read_SWS_Rte_SWC_SWX6_null_null_StrainGuageAdcVal(null) (              						IoHwAbs_Adc_ReadStrainGauge( null , null) , ((RTE_E_OK)null))

/*****************************************
*     Function Calls
*****************************************/

FUNC(void , Rte_SWC_SWX6_SWCA_SENSOR_APPL_CODE)
Rte_SWC_SWX6SWCA_SENSORCyclicnull(void)
FUNC(void , Rte_SWC_SWX6_SWCA_SENSOR_APPL_CODE)
Rte_SWC_SWX6SWCA_SENSORInit(void)

#endif