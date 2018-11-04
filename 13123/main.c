#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <windows.h>

#define High 20  // 游戏画面尺寸
#define Width 30
int moveDirection; // 小蛇移动位置，上下左右分别用1，2，3，4表示
int food_x,food_y; // 食物的位置
int canvas[High][Width] = {0}; // 二维数组存储游戏画布中对应的元素
	// 0为空格0，-1为边框#，-2为食物F，1为蛇头@，大于1的正数为蛇身*
void gotoxy(int x,int y)  //光标移动到(x,y)位置
{
    HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
    COORD pos;
    pos.X = x;
    pos.Y = y;
    SetConsoleCursorPosition(handle,pos);
}




void startup() // 数据初始化
{
	int i,j;

	// 初始化边框
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

	// 初始化蛇头位置
	canvas[High/2][Width/2] = 1;
	// 初始化蛇身，画布中元素值分别为2,3,4,5....
	for (i=1;i<=4;i++)
		canvas[High/2][Width/2-i] = i+1;



	food_x = rand()%(High-5) + 2;
	food_y = rand()%(Width-5) + 2;
	canvas[food_x][food_y] = -2;
}






void show()  // 显示画面
{
	gotoxy(0,0);  // 光标移动到原点位置，以下重画清屏
	int i,j;
	for (i=0;i<High;i++)
	{
		for (j=0;j<Width;j++)
		{
			if (canvas[i][j]==0)
				printf(" ");   //   输出空格
			else if (canvas[i][j]==-1)
				printf("#");   //   输出边框#
			else if (canvas[i][j]==1)
				printf("@");   //   输出蛇头@
			else if (canvas[i][j]>1)
				printf("*");   //   输出蛇身*
			else if (canvas[i][j]==-2)
				printf("F");   //   输出食物F
		}
		printf("\n");
	}
	Sleep(100);
}
