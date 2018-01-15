package Trie;


class Trie {
    
    class TrieNode {
        char c;
        boolean isEnd;
        TrieNode[] children;
        public TrieNode(char cc) {
            c = cc;
        }
    }
    public static void main(String[] args) {
        TrieNode t;
        
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(' '); // root doesn't need to have anything
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(word.toCharArray(), 0, root);
    }
    
    private void insert(char[] arr, int i, TrieNode root) {
        if (i == arr.length) {
            root.isEnd = true;
            return;
        }
        if (root.children == null)
            root.children = new TrieNode[26];
        for (int j = 0; j < 26; j++) {
            //这里存在一个问题： 对每一个数组对象都要new 来初始化
            root.children[i].c = (char)('a' + j);
        }
        insert(arr, i+1, root.children[arr[i] - 'a']);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(word.toCharArray(), 0, root);
    }
    private boolean search(char[] arr, int i, TrieNode root) {
        if (i == arr.length) {
            return root.isEnd;
        }
        return root.children != null && search(arr, i+1, root.children[arr[i] - 'a']);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return startsWith(prefix.toCharArray(), 0, root);
    }
    private boolean startsWith(char[] arr, int i, TrieNode root) {
        if (i == arr.length) {
            return true;
        }
        if (root.children == null)
            return false;
        return startsWith(arr, i+1, root.children[arr[i] - 'a']);
    }
}
