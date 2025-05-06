/*****************************************
*     Created on:	2021/03/22
*     Author:    	Prashant Gupta
*****************************************/


#ifndef	Rte_SWC_SWX2_SWCA_COMPOSITON_H_
#define	Rte_SWC_SWX2_SWCA_COMPOSITON_H_
/*****************************************
*     Schedular Mapping
*****************************************/

#define	Rte_SWC_SWX2_SWCA_COMPOSITON__RUNNABLE_100ms	Rte_SWC_SWX2_SWCA_COMPOSITON_


/*****************************************
*     API prototypes
*****************************************/

#define	Rte_Call_SWS_Rte_SWC_SWX2_null_null_StrainGuageAdcVal() (							                    		Adc_getConvertedValue(  , ) , ((RTE_E_OK)null))

#define	Rte_Read_SWS_Rte_SWC_SWX2_null_null_StrainGuageAdcVal() (              						       IoHwAbs_Adc_ReadStrainGauge(  , ) , ((RTE_E_OK)null))

#define	fg_kj_Rte_SWC_SWX2_null_null_njk() (hv(  , hgc) , ((v)null))

/*****************************************
*     Function Calls
*****************************************/

FUNC(void , Rte_SWC_SWX2_SWCA_COMPOSITON_APPL_CODE)
Rte_SWC_SWX2SWCA_COMPOSITONCyclic100ms(void)
FUNC(void , Rte_SWC_SWX2_SWCA_COMPOSITON_APPL_CODE)
Rte_SWC_SWX2SWCA_COMPOSITONInit(void)

#endif