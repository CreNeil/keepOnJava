//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
// 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
// 动态规划 70 91 198
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(translateNum(12258));
        System.out.println(translateNum2(12258));
        System.out.println(climbStairs(5));
        System.out.println(numDecodings2("226"));
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

    // 70
    public static int climbStairs(int n) {
        int rnj2, rnj1 = 0;
        int rn = 1;

        for (int i = 0; i < n; i++) {
            rnj2 = rnj1;
            rnj1 = rn;
            rn = rnj1 + rnj2;
        }
        return rn;
    }

    //91
    public static int numDecodings(String s) {
        int[] f = new int[s.length() + 1];
        f[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[s.length()];
    }

    public static int numDecodings2(String s) {
        int fn2 = 0, fn1 = 1, fn = 0;
        for (int i = 1; i <= s.length(); i++) {
            fn = 0;
            if (s.charAt(i - 1) != '0') {
                fn += fn1;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                fn += fn2;
            }
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }


    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] f = new int[length];
        f[0] = nums[0];
        f[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            f[i] = Math.max(f[i - 2] + nums[i], f[i - 1]);
        }
        return f[length - 1];
    }

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int fn2 = nums[0];
        int fn1 = Math.max(nums[0], nums[1]);
        int fn = fn1;
        for (int i = 2; i < nums.length; i++) {
            fn = Math.max(fn2 + nums[i], fn1);
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }

}
