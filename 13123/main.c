#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <windows.h>

#define High 20  // ��Ϸ����ߴ�
#define Width 30
int moveDirection; // С���ƶ�λ�ã��������ҷֱ���1��2��3��4��ʾ
int food_x,food_y; // ʳ���λ��
int canvas[High][Width] = {0}; // ��ά����洢��Ϸ�����ж�Ӧ��Ԫ��
	// 0Ϊ�ո�0��-1Ϊ�߿�#��-2Ϊʳ��F��1Ϊ��ͷ@������1������Ϊ����*
void gotoxy(int x,int y)  //����ƶ���(x,y)λ��
{
    HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
    COORD pos;
    pos.X = x;
    pos.Y = y;
    SetConsoleCursorPosition(handle,pos);
}




void startup() // ���ݳ�ʼ��
{
	int i,j;

	// ��ʼ���߿�
	for (i=0;i<High;i++)
	{
		canvas[i][0] = -1;
		canvas[i][Width-1] = -1;
	}
	for (j=0;j<Width;j++)
	{
		canvas[0][j] = -1;
		canvas[High-1][j] = -1;
	}

	// ��ʼ����ͷλ��
	canvas[High/2][Width/2] = 1;
	// ��ʼ������������Ԫ��ֵ�ֱ�Ϊ2,3,4,5....
	for (i=1;i<=4;i++)
		canvas[High/2][Width/2-i] = i+1;



	food_x = rand()%(High-5) + 2;
	food_y = rand()%(Width-5) + 2;
	canvas[food_x][food_y] = -2;
}






void show()  // ��ʾ����
{
	gotoxy(0,0);  // ����ƶ���ԭ��λ�ã������ػ�����
	int i,j;
	for (i=0;i<High;i++)
	{
		for (j=0;j<Width;j++)
		{
			if (canvas[i][j]==0)
				printf(" ");   //   ����ո�
			else if (canvas[i][j]==-1)
				printf("#");   //   ����߿�#
			else if (canvas[i][j]==1)
				printf("@");   //   �����ͷ@
			else if (canvas[i][j]>1)
				printf("*");   //   �������*
			else if (canvas[i][j]==-2)
				printf("F");   //   ���ʳ��F
		}
		printf("\n");
	}
	Sleep(100);
}
