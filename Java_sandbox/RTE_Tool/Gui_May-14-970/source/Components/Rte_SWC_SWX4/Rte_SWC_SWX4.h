/*****************************************
*     Created on:	2021/03/22
*     Author:    	Prashant Gupta
*****************************************/


#ifndef	Rte_SWC_SWX4_SWCA_COMPOSITON_H_
#define	Rte_SWC_SWX4_SWCA_COMPOSITON_H_
/*****************************************
*     Schedular Mapping
*****************************************/

#define	Rte_SWC_SWX4_SWCA_COMPOSITON__RUNNABLE_null	Rte_SWC_SWX4_SWCA_COMPOSITON_


/*****************************************
*     API prototypes
*****************************************/

#define	Rte_Call_SWS_Rte_SWC_SWX4_null_null_StrainGuageAdcVal() (							                    		Adc_getConvertedValue(  , ) , ((RTE_E_OK)null))

#define	Rte_Read_SWS_Rte_SWC_SWX4_null_null_StrainGuageAdcVal() (              						       IoHwAbs_Adc_ReadStrainGauge(  , ) , ((RTE_E_OK)null))

#define	ad_afd_Rte_SWC_SWX4_null_null_afd(vgv) (kl( vgv , bnm) , ((vcc)null))

/*****************************************
*     Function Calls
*****************************************/

FUNC(void , Rte_SWC_SWX4_SWCA_COMPOSITON_APPL_CODE)
Rte_SWC_SWX4SWCA_COMPOSITONCyclicnull(void)
FUNC(void , Rte_SWC_SWX4_SWCA_COMPOSITON_APPL_CODE)
Rte_SWC_SWX4SWCA_COMPOSITONInit(void)

#endif