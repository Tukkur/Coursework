/*
    Kevin Lee
    Sample program for CSCI
    
    This program illustrates how ASCII characters,
    integers and floats are stored as binary bits.
    
    Some students find this a bit too much but I don't worry
    about their two-bit opinions!

*/

#include <stdlib.h>
#include <stdio.h>

//function prototypes
void printIntAsBinary(int source, int numBits); //function prototype
int pretendFloatIsInt(float x);
void printFloatBits(float x);


int main()
{
   int i;
   float x;
    char ch;
    
    printf("sizeof(int) = %d\n", sizeof(int));
    printf("sizeof(short) = %d\n", sizeof(short));
    printf("sizeof(long) = %d\n", sizeof(long));
   
   x = 0.0;
   printFloatBits(x); 
   x = !x;
    printFloatBits(x); 
    
   do{    
    //try playing with chars
    printf("Enter a character or '.' to exit loop\n");
    scanf("%c", &ch);
    getchar(); //eat the newline
    printIntAsBinary(ch, 8);
    printf("%c %d %x %o\n", ch, ch, ch, ch);
    printf("\n");
   }while(ch != '.');  
   
  do{    
    //try playing with ints
    printf("Enter an integer number or 999 to exit loop\n");
    scanf("%d", &i);
    printIntAsBinary(i, 32);
    printf("\n");
    
   }while(i != 999);  

   do{    
    //try playing with floats
     printf("Enter a floating point number or -10000 to exit loop\n");
     scanf("%f", &x);
     printf("%e\n", x);
     printFloatBits(x);
     printf("\n");
    }while(x > -10000); 

    system("pause"); //warning! pause is a DOS command
	return EXIT_SUCCESS;  
}





/*
    given the source integer this function prints it out in binary 
    as ones and zeros
    
    numBits controls how many bits we want to printed, counted from
    the right
    
    it assumes that an int is 32 bits long
*/
void printIntAsBinary(int source, int numBits)
{
   unsigned int  mask = 0x00000001; 
   int i;
   
   if( numBits < 0 || numBits > 32) //do a little error checking
      printf("error in printIntAsBinary , numBits out of range\n");
   
   //first get the mask to the leftmost bit they want to see
   for( i = 2; i <= numBits; i++)
   {
         mask = mask << 1;
   }
   
   for( i = 1; i <= numBits; i++)
   {
      if (mask & source){ //test the bit at this position for a 1 or 0
            printf("1");
      }else 
            printf("0");
            
      if(i % 4 == 0)
            printf(" ");
      mask = mask >> 1; //shift the mask to the right 1 place for the next bit
   
   }
   printf("\n");
}



/*
    first attempt, check if correct
*/

void printDoubleBits(double x)
{
     unsigned char *iPtr, mask = 0x1;
     int i,j;
     int count = 0;
     
     printf("x = %f\n", x);
     iPtr = (unsigned char*) &x; //take the address of x and pretend it is an integer
     iPtr += 7;
    
     
      for( i = 1; i <= 8; i++)
   {
           mask = 128;
      for(j = 1; j <= 8; ++j)
      {     
            ++count;
          if (mask & (*iPtr)){ //test the bit at this position for a 1 or 0
            printf("1");
          }else 
            printf("0");
            
            if(count == 1 ||  count == 12)
                   printf(" ");  
            
          if(j % 4 == 0)
            printf(" ");
           mask = mask >> 1; //shift the mask to the right 1 place for the next bit
      }
           
  //    printf("mask = %lld\n", mask);
      --iPtr;
   }
   printf("\n");   
}

/*
   Miserable function but it works

*/
void printFloatBits(float x)
{
    int source, *intPointer;

    intPointer = (int *) &x; //take the address of x and pretend it is an integer
    
    source = *intPointer; //transfer the value to result
 //   printIntAsBinary(result, 32);
    
   unsigned int  mask = 0x00000001; 
   int i;
   
   
   //first get the mask to the leftmost bit they want to see
   for( i = 2; i <= 32; i++)
   {
         mask = mask << 1;
   }
   
   for( i = 1; i <= 32; i++)
   {
      if (mask & source){ //test the bit at this position for a 1 or 0
            printf("1");
      }else 
            printf("0");
            
      if(i == 1 || i == 9)
            printf(" ");
      mask = mask >> 1; //shift the mask to the right 1 place for the next bit
   
   }
   printf("\n");
}



