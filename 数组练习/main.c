#include <stdio.h>
#include <stdlib.h>
#define N 7
int main()
{
    double score[N]={8,4,2,1,23,344,12};
    int i;
    double sum1=0;
    double shuzi;
    /*for (i=0;i<N;i++)
    {
        printf("数组中第%d个数字是:%.0lf\n",i+1,score[i]);
    }*/
    for (i=0;i<N;i++)
    {
        sum1+=score[i];
    }
    printf("这些数字的和是：%.0lf\n平均值是：%.2lf\n",sum1,sum1/N);




for(;;)
{


    printf("请输入一个你认为在数组中的数字：");
    scanf("%lf",&shuzi);
    printf("\n");
    for(i=0;i<N;i++)
    {
        if (shuzi==score[i])
        {
            printf("输入正确!\n");
            break;
        }

        if (i==6)
        {
            printf("没有找到!\n");
        }
    }

}

    return 0;
}
