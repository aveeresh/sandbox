// C Program for how to create a Typedef for a Function
// Pointer
#include <stdio.h>

typedef int (*Operation)(int, int);
int add(int a, int b) { return a + b; }

// Function to the subtract two integers
int subtracts(int a, int b) { return a - b; }

// Driver Code
int main()
{
    // Declare function pointers using typedef
    Operation operationAdd = add;
    Operation operationSubtract = subtracts;
    printf("Addition result: %d\n", operationAdd(20, 9));
    printf("Subtraction result: %d\n",
           operationSubtract(20, 9));
    return 0;
}
