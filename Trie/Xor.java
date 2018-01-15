//基本思路： 从左到右用bit建trie，o(n*len), 此处len=31；
//建好后再依次遍历，更新结果，o(n*len),总体复杂度o(n) (len = const)

public class Xor {
    public static class TrieNode {
        int v;
        TrieNode[] children = new TrieNode[2];
        TrieNode (int val) {
            v = val;
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[]{3,10,5,25,2,8};
        int res = findMaximumXOR(nums);
        System.out.println(res);
    }
    public static int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        TrieNode root = new TrieNode(-1);
        // build trie
        for (int n : nums) {
            TrieNode curr = root;
            for (int i = 31; i >= 0; i--) {
                int temp = (n>>>i)&1;
                if (curr.children[temp] == null)
                    curr.children[temp] = new TrieNode(temp);
                curr = curr.children[temp];
            }
        }
        // get the result
        for (int n :nums) {
            int temp = 0;
            TrieNode curr = root;
            for (int i = 31; i >= 0; i--) {
                int val = ((n>>>i)&1)^1;
                if (curr.children[val] != null) {
                    //temp += ((val^1)<<i);
                    temp += (1<<i);
                    curr = curr.children[val];
                } else {
                    curr = curr.children[val^1];
                }
            }
            max = Math.max(max, temp);
        }
        return max;
    }
}