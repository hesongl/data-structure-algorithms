// 这个题最重要的是想明白三种情况：1.cat - cattle， 吃完了dict的词发现是isword，append这个dict cat
// 2. cab - cattle, cattle 吃不完dict的词发现a下面的c是 null TrieNode了，append整个词cattle
// 3. rat - r ， r吃不完dict的词，但是for循环已经退出了，这个时候检测当前curr节点，所以之前两个在break之前都要把curr接着前进：curr = curr.next[c-'a']
// 这样一来第三问对应情况就可以跟前两种区分开了： 如果curr != null,  并且curr.isWord == false, 说明词比dict短，也是append整个词
// 最后别忘了sb的接口deleteCharAt(sb.length() - 1)来删掉trailing space
// 这个题给个9分了

class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        if (sentence == null || sentence.length() == 0)
            return null;
        String[] words = sentence.split(" ");
        // build trie
        TrieNode root = new TrieNode();
        for (String s : dict) {
            TrieNode curr = root;
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                if (curr.next[c-'a'] == null)
                    curr.next[c-'a'] = new TrieNode();
                curr = curr.next[c-'a'];
            }
            curr.word = s;
            curr.isWord = true;
        }
        StringBuilder sb = new StringBuilder();
        
        for (String t : words) {
            char[] arr = t.toCharArray();
            TrieNode curr = root;
            for (int i = 0; i <arr.length; i++) {
                char c = arr[i];
                if (curr.next[c-'a'] == null) {
                    sb.append(t).append(" ");
                    curr = curr.next[c-'a'];
                    break;
                } else { 
                    if (curr.next[c-'a'].isWord) {
                        sb.append(curr.next[c-'a'].word).append(" ");
                        curr = curr.next[c-'a'];
                        break;
                    }
                    curr = curr.next[c-'a'];
                }
            }
            if (curr != null && !curr.isWord) { // r for rat
                sb.append(t).append(" ");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    class TrieNode {
        String word;
        boolean isWord;
        TrieNode[] next = new TrieNode[26];
    }
}