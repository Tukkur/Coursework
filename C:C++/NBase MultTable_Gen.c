/*
 CSCI 2021
 Multiplication table generator
 for any base
 
 finish the function
 printMultTable
 */

#include <stdio.h>
#include <stdlib.h>

char convertToChar(int i, int base);
void printNumber(int i, int base, int field_width, int char_count);
void printMultTable(int base);
void calcBase3(int number);



int main()
{
    int i = 59;
    int base;
    /*
     printf("...");
     printNumber(i, 10, 4, 0);
     printf("...");
     putchar('\n');
     printNumber(i, 12, 4, 0);
     putchar('\n');
     printNumber(i, 20, 4, 0);
     putchar('\n');
     */
    /*printf("This program will print a multiplication base for any base 2 to 36\n");
    printf("What base would you like to use?\n");
    scanf("%d", &base);*/
    printNumber(200, 3, 4, 0);
    putchar('\n');
    calcBase3(200);
    putchar('\n');
    calcBase3(1000);
    
    if (base >= 2 && base <= 36){
        printMultTable(base);
    }else{
        printf("invalid base\n");
    }
    system("pause");
    return 0;
}
/*
 i < base
 */









char convertToChar(int i, int base)
{
    char answer;
    
    if ( i < 10){
        answer = i + '0';
    }else{
        answer = i + 'A' - 10;
    }
    return answer;
}

void printNumber(int i, int base, int field_width, int char_count)
{
    char ch;
    char_count++;
    if (i < base){
        int k;
        ch = convertToChar(i, base);
        //see if we need to pad it with spaces
        for(k = char_count; k < field_width; ++k)
        {
            putchar(' ');
        }
        
    }else{
        int r = i % base;
        ch = convertToChar(r, base);
        
        i = i /base;
        printNumber(i, base, field_width, char_count);
       
        
    }

    printf("%c", ch);
}

void calcBase3(int number){
    int answer[6];
    int answerIndex=5;
    while(answerIndex!=-1){
        int remainder = number%3;
        answer[answerIndex]=remainder;
        number = number/3;
        answerIndex--;
    }
    int x;
    for(x=0;x<6;x++){
        printf("%d",answer[x]);
    }    
}


void printMultTable(int base)
{
    int i;
    int j;
    for(j = 1; j<=base;j++){
    for (i=1; i<=base; i++) {
        int x = i*j;
        if(x<10){
            printNumber(x, 10, 4, 0);
        }
        else{
        printNumber(x, base, 4, 0);
        }
    }
        printf("\n");
    }
    
    printf("finish \n");
}
