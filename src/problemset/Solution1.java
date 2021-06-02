class Solution1 {
    // 题目描述：给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1
    // 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new Solution1().trap(height));
        System.out.println(new Solution1().trapOpti(height));
    }
    // 根据题意可知去掉开头结尾的柱子，我们从i=1 可是计算第i个柱子位置所能承载的水柱，此处的水柱高度只与i位置左侧最高柱子和
    // i位置右侧最高柱子的高度有关，所有我们做如下分析，
    // 首先求出每个i位置左侧柱子的最大值和右侧柱子高度的最大值，然后取左右最大值中的最小值和当前i位置的柱子高度做差，如果大于0
    // ，那么此处可以盛水，小于0则不可以
    public int trap(int[] height) {
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            if (i == 0) {
                maxLeft[i] = height[i];
                maxRight[height.length - 1 - i] = height[height.length - 1 - i];
            } else {
                maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
                maxRight[height.length - 1 - i] = Math.max(height[height.length - 1 - i], maxRight[height.length - i]);
            }
        }
        int result = 0;
        for (int j = 1; j < height.length - 1; j++) {
            result += Math.max(Math.min(maxLeft[j], maxRight[j]) - height[j], 0);
        }
        return result;
    }

    // 双指针空间优化
    // 上述时间复杂度为O(n),空间上还可以优化，采用双指针方法对空间进行优化，记maxleft是左侧最大值，maxright是右侧最大值
    // 左右指针向中间移动，更新maxleft 和maxright，当maxleft 和 maxright 谁小，那么就更新数小侧的指针left或者right指向的
    // i位置的水, 相等时都可以更新,注意while停止的条件是左侧超过右侧
    public int trapOpti(int[] height){
        if (height.length <=1){
            return 0;
        }
        int maxLeft = height[0];
        int maxRight = height[height.length -1];
        int left = 1;
        int right = height.length-2;
        int result = 0;
        while (left <= right){
            if (maxLeft <= maxRight){
                result += Math.max(maxLeft - height[left], 0);
                maxLeft = Math.max(maxLeft, height[left++]);
            }else{
                result += Math.max(maxRight - height[right], 0);
                maxRight = Math.max(maxRight, height[right--]);
            }
        }
        return result;
    }

}