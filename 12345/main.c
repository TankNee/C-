#include <stdio.h>
#include <stdlib.h>
#include<conio.h>
#include<windows.h>



int main()
{
    char input;
    while(input!='P'||input!='p')
    {
        if(kbhit())
        {
            input=kbhit();
        }
        if(!kbhit())
        {
            Sleep(100);
            printf("Hello World");
        }
    }
}









/*int main()
{
 char ch;
 while(ch!='p')
 {
    if(kbhit())
    {
        ch=getch();
    }
    while(!kbhit())

    {
        Sleep(1000);
        printf("Hello world!\t");
    }
 }
  return 0 ;
}*/


