class Solution {
    public int missingNumber(int[] nums) {


       int XOR1 = 0;
       int XOR2 = 0;
       for(int i=0;i<nums.length;i++){
        XOR2 ^= nums[i];
        XOR1 ^= (i+1);
       } 
       return XOR1^XOR2;
    }
}