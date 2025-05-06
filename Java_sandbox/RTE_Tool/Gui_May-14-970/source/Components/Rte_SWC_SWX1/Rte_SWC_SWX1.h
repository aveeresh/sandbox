/*****************************************
*     Created on:	2021/03/22
*     Author:    	Prashant Gupta
*****************************************/


#ifndef	Rte_SWC_SWX1_SWCA_SENSOR_H_
#define	Rte_SWC_SWX1_SWCA_SENSOR_H_
/*****************************************
*     Schedular Mapping
*****************************************/

#define	Rte_SWC_SWX1_SWCA_SENSOR__RUNNABLE_200ms	Rte_SWC_SWX1_SWCA_SENSOR_


/*****************************************
*     API prototypes
*****************************************/

#define	Rte_Call_SWS_Rte_SWC_SWX1_null_null_StrainGuageAdcVal(0x22) (Adc_getConvertedValue( 0x22 , unint) , ((RTE_E_OK)null))

#define	Rte_Read_SWS_Rte_SWC_SWX1_null_null_StrainGuageAdcVal(0x22) (              						IoHwAbs_Adc_ReadStrainGauge( 0x22 , unint) , ((RTE_E_OK)null))

#define	afdf_afd_Rte_SWC_SWX1_null_null_afd() ((  , ) , ((dsfad)null))

#define	ewr_dafwer_Rte_SWC_SWX1_null_null_xvd() (bye(  , ) , ((Hi)null))

/*****************************************
*     Function Calls
*****************************************/

FUNC(void , Rte_SWC_SWX1_SWCA_SENSOR_APPL_CODE)
Rte_SWC_SWX1SWCA_SENSORCyclic200ms(void)
FUNC(void , Rte_SWC_SWX1_SWCA_SENSOR_APPL_CODE)
Rte_SWC_SWX1SWCA_SENSORInit(void)

#endif