class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int maxi = 0;
        int count = 0;
        for(int j=0;j<n;j++){
            if(nums[j] == 1){
                count++;
                maxi = Math.max(maxi,count);
            }
            else{
                count = 0; 
            } 
        }
        return maxi;
    }
}