// Brute Approch
//Time Complexity O(N^2)
//Space Complexity O(N)
class Solution {
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        int n = arr.length;
        ArrayList<Integer>nse = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            int prev = -1;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    prev = arr[j];
                    break;
                }
            }
            nse.add(prev);
        }
        return nse;
        
        
    }
}

//Optimized Approch
//Time Complexity O(N)
//Space Complexity O(N)
class Solution {
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        int n = arr.length;
        ArrayList<Integer>nse = new ArrayList<>();
        Stack<Integer>st = new Stack<>();
        for(int i=0;i<n;i++){
            int prev = -1;
            while(!st.isEmpty() && st.peek() >= arr[i]){
                st.pop();
            }
            if(st.isEmpty()){
                prev = -1;
            }
            else{
                prev = st.peek();
            }
            nse.add(prev);
            st.push(arr[i]);
        }
        return nse;
        
        
    }
}