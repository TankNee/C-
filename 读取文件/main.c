#include <stdio.h>

int main()
{
	//下面是写数据，将数字0~9写入到data.txt文件中
	FILE *fpWrite=fopen("data.txt","w");
    int i=1;
    fprintf(fpWrite,"%d ",i);
	fclose(fpWrite);
	//下面是读数据，将读到的数据存到数组a[10]中，并且打印到控制台上
	int score_max[1]={0};
	FILE *fpRead=fopen("data.txt","r");

	for(int i=0;i<1;i++)
	{
		fscanf(fpRead,"%d ",&score_max[i]);
		printf("%d ",score_max[i]);
	}
	getchar();//等待

	return 1;
}
