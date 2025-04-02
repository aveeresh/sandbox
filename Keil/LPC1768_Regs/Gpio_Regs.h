#ifndef GPIO_REGS_H
#define GPIO_REGS_H

#include "Types.h"

typedef union
{
	uint32 R;
	struct
	{
		uint32 FIODIR:32;
	}B;
}Gpio_FIODIR_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 FIODIR:32;
	}B;
}Gpio_FIOSET_t;

typedef union
{
	uint32 R;
	struct
	{
		uint32 FIODIR:32;
	}B;
}Gpio_FIOPIN_t;


#endif
