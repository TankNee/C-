#include <stdio.h>

int main()
{
	//������д���ݣ�������0~9д�뵽data.txt�ļ���
	FILE *fpWrite=fopen("data.txt","w");
    int i=1;
    fprintf(fpWrite,"%d ",i);
	fclose(fpWrite);
	//�����Ƕ����ݣ������������ݴ浽����a[10]�У����Ҵ�ӡ������̨��
	int score_max[1]={0};
	FILE *fpRead=fopen("data.txt","r");

	for(int i=0;i<1;i++)
	{
		fscanf(fpRead,"%d ",&score_max[i]);
		printf("%d ",score_max[i]);
	}
	getchar();//�ȴ�

	return 1;
}
