//BY HASHMAP

class Solution {
    public int removeDuplicates(int[] nums) {
        HashMap<Integer,Integer> s = new HashMap<>();
        int ind = 0;
        int size = 0;
        for(int i=0;i<nums.length;i++){
            if(s.contains(nums[i],count<2)){
                s.add(nums[i],count+1);
                arr[ind++] = arr[i];
                size++;

            }


        }
        return nums;
    }

    //OPTIMAL Solution

    class Solution {
    public int removeDuplicates(int[] nums) {

        int ind = 0;

        for (int i = 0; i < nums.length; i++) {

            if (ind < 2 || nums[i] != nums[ind - 2]) {
                nums[ind] = nums[i];
                ind++;
            }
        }

        return ind;
    }
}