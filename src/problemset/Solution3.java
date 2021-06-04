//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
// 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
// 动态规划 70 91 198
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(translateNum(12258));
        System.out.println(translateNum2(12258));
    }

    //bad 还可以优化
    public static int translateNum(int num) {
        char[] numbers = Integer.toString(num).toCharArray();
        // f 下标代表字母数量
        int[] f = new int[numbers.length + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 1; i < numbers.length; i++) {
            if ((Integer.parseInt(((Character) (numbers[i])).toString()) < 6 && Integer.parseInt(((Character) (numbers[i - 1])).toString()) == 2) ||
                    Integer.parseInt(((Character) numbers[i - 1]).toString()) == 1) {
                f[i + 1] = f[i - 1] + f[i];
            } else {
                f[i + 1] = f[i];
            }
        }
        return f[numbers.length];
    }

    // 空间优化 滚动数组空间优化
    // 因为解只与前两个状态有关所以可以将 f[i-1] 和 f[i] 记为 p q 最终结果记为r
    public static int translateNum2(int num) {
        String src = String.valueOf(num);
        // i-1 , i, i+1
        int p = 1, q = 1, r = 1;
        for (int i = 1; i < src.length(); i++) {
            p = q;
            q = r;
            r = 0;
            r += q;
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }

}
