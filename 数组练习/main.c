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
        printf("�����е�%d��������:%.0lf\n",i+1,score[i]);
    }*/
    for (i=0;i<N;i++)
    {
        sum1+=score[i];
    }
    printf("��Щ���ֵĺ��ǣ�%.0lf\nƽ��ֵ�ǣ�%.2lf\n",sum1,sum1/N);




for(;;)
{


    printf("������һ������Ϊ�������е����֣�");
    scanf("%lf",&shuzi);
    printf("\n");
    for(i=0;i<N;i++)
    {
        if (shuzi==score[i])
        {
            printf("������ȷ!\n");
            break;
        }

        if (i==6)
        {
            printf("û���ҵ�!\n");
        }
    }

}

    return 0;
}
