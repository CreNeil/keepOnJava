public class Solution2 {
    // 题目归类类型： 随机概率调整， 位运算
    // 给定随机函数f，等概率返回1~5，如何实现等概率返回1~7
    // 思路首先构造 等概率返回0，1的函数f1，再通过位运算实现等概率返回1~7

    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    public static int f1() {
        int res = 0;
        do {
            res = f();
        } while (res == 3);
        return res > 3 ? 1 : 0;
    }

    public static int result() {
        int res = 0;
        do {
            res = (f1() << 2) + (f1() << 1) + (f1());
        } while (res == 7);
        return res + 1;
    }

    public static void main(String[] args) {
        int n = 10000000;
        int[] count = new int[8];

        for (int i = 0; i < n; i++) {
            count[result()]++;
        }

        for (int i = 0; i < 8; i++) {
            System.out.println(i + " : " + count[i]);
        }

        System.out.println("变形题目");

        int[] count2 = new int[2];

        for (int i = 0; i < n; i++) {
            count2[resultP()]++;
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(i + " : " + count2[i]);
        }
    }


    // 变形题目， 随机函数f，以p概率返回0，以 1-p概率返回1，如何实现等概率返回0，1
    // 解决方式 连续调用两次 ff 如果结果为 00 11 那么重新做， 如果结果为01 返回0，结果为10 返回1

    public static int fp() {
        return Math.random() > 0.9 ? 0 : 1;
    }

    public static int resultP() {
        int result = 0;
        int[] set = new int[2];
        do {
            set[0] = fp();
            set[1] = fp();
        } while (set[0] == set[1]);
        return set[0] == 0 ? 1 : 0;
    }
}
