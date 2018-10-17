### 一

补全一个 java 应用程序，在主类的 main 方法中实现下列功能：

+ 将一个数组反序；

+ 比如给定[1,2,3,4,5]，打印[5,4,3,2,1] 2）

+ ```java
  /*
   * Exercise 1-1
   */
  public class Solution {
  
      public static void main(String[] args) {
          int[] array1 = {1,2,3,4,5};
          int[] array2 = {3,1,4,1,5,9,2,6};
  
          showReversedArray(array1);
          showReversedArray(array2);
      }
  
      /*
       * Reverse an array and print it in the standard output
       * @param a the input array
       */
      public static void showReversedArray(int[] a) {
  
          int temp = 0 ;
          for (int i = 0 ; i< a.length/2;i++)
          {
              temp = a[i];
              a[i] = a[a.length-i-1];
              a[a.length-i-1] = temp;
          }
          for (int i =0;i<a.length;i++)
          {
              System.out.print(a[i]);
          }
          System.out.println();
  
      }
  
  }
  ```

---

### 二 

补全一个 java 应用程序，在主类的 main 方法中实现下列功能： 

+ 删除一个数组中的重复元素；

+ 比如给定[1,2,3,3]，打印[1,2,3]；给定[1,1,1]，打印[1]；

+ ```java
  /*
   * Exercise 1-1
   */
  import java.util.Arrays;
  public class Solution {
  
      public static void main(String[] args) {
          int[] array1 = {1,2,3,4,4};
          int[] array2 = {3,1,4,1,5,9,2,6,5,3,5,8,9,3,9};
          int[] array3 = {1,1,1,1};
          showNonDuplicateArray(array1);
          showNonDuplicateArray(array2);
          showNonDuplicateArray(array3);
      }
  
      /*
       * Remove duplicate elements in an array
       * and print it in the standard output
       * @param a the input array
       */
      public static void showNonDuplicateArray(int[] a) {
  
          int count = 0 ;
          for (int i =0;i< a.length;i++)
          {
              for (int j = i+1;j<a.length;j++)
              {
                  if(a[i]==a[j])
                  {
                      a[j]=Integer.MAX_VALUE;
                  }
              }
          }
          for (int i =0;i< a.length;i++)
          {
              if(a[i]==Integer.MAX_VALUE)
              {
                  count++;
              }
          }
          int[] temp = new int[a.length-count];
          Arrays.sort(a);
          for (int i =0;i< a.length-count;i++)
          {
  
              if(a[i]!=Integer.MAX_VALUE)
              {
  
                  temp[i]=a[i];
              }
          }
          for (int i =0;i<a.length-count;i++)
          {
              System.out.printf("%d\t",temp[i]);
          }
          System.out.println();
  
  
      }
  
  }
  ```

---

### 三

Point 类代表一个二维平面上的点，请给出其子类 PointGrey 的实现，并创 建 PointColor，用于代表一个由 RGB 三原色表示的彩色点。补全所有方法后， 取消注释，运行 main 函数查看输出（彩色点的灰度取 RGB 三通道的整数均值）。 

---

### 四 √

请补全长方形 Rectangle 面积的计算方法；请创建 Circle 类，并实现其面积 的计算。 

---

### 五

在一个纸牌游戏中，有一个代表纸牌的类 Card，每张纸牌具有数字和花色两 种属性；另一个类 Deck，代表一副完整的牌（除去大小王）。请补全这两个类， 并实现如下功能：

+ 补全所有的构造函数。 我们将 3 张牌分为一组 CardGroup，
+ 判断一个 CardGroup 中有几张牌的数字是一样大的，比如[5,5,3]，返 回 2；[4,6,9]返回 1；
+ 判断一个 CardGroup 中是否所有牌的花色是一样的；
+ 请补全比较两个牌组大小的方法 compare(CardGroup c)，并在 main 函 数中比较几组随机牌组的大小。比较规则如下：(1) 3 张牌花色相同 > (2)3 张 数字相同 > (3)2 张数字相同 >(4)3 张不一样。同一级别的牌组则认为一样大， 无需进行进一步比较。 