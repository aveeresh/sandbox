
/********************************************************************************/
// FILE INFORMATION
/********************************************************************************/
/**
 * @file	: Nvm_cfg.h
 * @brief	: Header file of NVM Manager queue management sub-module
 *
 * @copyright	(c)2020 All right reserved
 */

/********************************************************************************/
// HEADER GUARD
/********************************************************************************/
#ifndef __NVM_CFG_H__
#define __NVM_CFG_H__

/********************************************************************************/
// LOCAL & GLOBAL DEFINITION
/********************************************************************************/

#ifdef GLOBAL
#undef GLOBAL
#endif

#ifdef LOCAL
#undef LOCAL
#endif

#define GLOBAL	extern
#define LOCAL	static

/********************************************************************************/
// CONFIGURATION PARAMETERS
/********************************************************************************/
#define NVM_BLOCK_MAX_LIMIT             9U        //Number of Logical Blocks in Memory

#define NVM_MAX_DATA_BLOCK_SIZE         13U       //Maximum Data Size of a Logical Block

#define CRC_MAX_SIZE                    1U        //Maximum CRC Byte Size (Max = 4)

#define NVM_QUEUE_SIZE                  9U        //Queue Size: Number of requests in queue



#endif /* __NVM_CFG_H__ */