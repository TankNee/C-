#include <stdio.h>
#include <stdlib.h>

int main()
{
    int goodsprice;
    const R=56;
    int i;

    for(i=0;i<5;i++)
    {
        printf("**********************************\n");
        printf("����������²����Ʒ�۸�");
        scanf("%d",&goodsprice);

        if (goodsprice>R)
        {
            printf("������¸���\n");
            printf("������ĵ�%d�֣������ֻ�ܲ²���Σ���\n",i+1);
            if (i=4)
            {
                break;
            }
        }
        if(goodsprice<R)
        {
            printf("������µ���\n");
            printf("������ĵ�%d�֣������ֻ�ܲ²���Σ���\n",i+1);
            if (i==4)
            {
                break;
            }
        }
        if (goodsprice==R)
        {
            printf("��ϲ������\n");
            break;
        }
         printf("**********************************\n\n");
         getchar();




    }
    return 0;
}
