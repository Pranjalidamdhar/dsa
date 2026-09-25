class Solution {
    public int longestSubarray(int[] arr, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxlen = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            // Subarray from index 0 to i
            if (sum == k) {
                maxlen = i + 1;
            }

            // We need an earlier prefix sum = sum - k
            if (map.containsKey(sum - k)) {
                maxlen = Math.max(maxlen, i - map.get(sum - k));
            }

            // Store ONLY the first occurrence
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxlen;
    }
}