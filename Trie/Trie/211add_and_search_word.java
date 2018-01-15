// node处可以存当前的string值也可以存一个boolean值，但这样别忘了在add的时候i==length的时候给这个赋值
class WordDictionary {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord;
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        addWord(word.toCharArray(), 0, root);
    }
    private void addWord(char[] arr, int i, TrieNode root) {
        if (i == arr.length) {
            root.isWord = true;
            return;
        }
        char c = arr[i];
        if (root.next[c-'a'] == null)
            root.next[c-'a'] = new TrieNode();
        root = root.next[c-'a'];
        addWord(arr, i+1, root);
    }
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null || word.length() == 0)
            return true;
        return search(word.toCharArray(), 0, root);
    }
    private boolean search(char[] arr, int i, TrieNode root) {
        if (i == arr.length)
            return root.isWord;
        if (arr[i] == '.') { // go through all children
            for (int j = 0; j < 26; j++) {
                if (root.next[j] != null) {
                    boolean temp = search(arr, i+1, root.next[j]);
                    if (temp == true)
                        return true;
                }
            }
        } else {
            return root.next[arr[i] - 'a'] != null && search(arr, i+1, root.next[arr[i]-'a']);
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */