class Solution {
    public long beautifulSubarrays(int[] nums) {

        HashMap<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);

        long count = 0;
        int XOR = 0;

        for (int val : nums) {
            XOR ^= val;

            int target = XOR;

            if (freq.containsKey(target)) {
                count += freq.get(target);
            }

            freq.put(XOR, freq.getOrDefault(XOR, 0) + 1);
        }

        return count;
    }
}