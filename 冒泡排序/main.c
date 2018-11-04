#include <stdio.h>
#include <stdlib.h>
#define N 5
int main()
{

    int score[N]={16,25,9,90,23};
    int i,j;
    int temp;
    for (i=0;i<N-1;i++)
    {
        for(j=0;j<N-1-i;j++)
        {
            if(score[j]<score[j+1])
            {
                temp=score[j];
                score[j]=score[j+1];
                score[j+1]=temp;
            }



        }

    }
    for(i=0;i<N;i++)
    {
        printf("%d  ",score[i]);
    }
    return 0;
}
