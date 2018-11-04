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
        printf("请你输入你猜测的商品价格：");
        scanf("%d",&goodsprice);

        if (goodsprice>R)
        {
            printf("错误！你猜高了\n");
            printf("这是你的第%d轮，你最多只能猜测五次！！\n",i+1);
            if (i=4)
            {
                break;
            }
        }
        if(goodsprice<R)
        {
            printf("错误！你猜低了\n");
            printf("这是你的第%d轮，你最多只能猜测五次！！\n",i+1);
            if (i==4)
            {
                break;
            }
        }
        if (goodsprice==R)
        {
            printf("恭喜你答对了\n");
            break;
        }
         printf("**********************************\n\n");
         getchar();




    }
    return 0;
}
