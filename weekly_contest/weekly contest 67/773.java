class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() == 0 ||wordDict == null ||wordDict.size() == 0)
            return res;
        HashMap<String, List<String>> map = new HashMap();
        HashSet<String> set = new HashSet<>();
        set.addAll(wordDict);
        res = bt(s, set, map);
        return res;
    }
    private List<String> bt (String s, HashSet<String>set, HashMap<String, List<String>> map) {
        if (map.containsKey(s))
            return map.get(s);
        List<String>res = new LinkedList<>();
        if (s.length() == 0) {
        	res.add("");
        	return res;
        }
        for (String str : set) {
        	if (s.startsWith(str)) {
        		List<String>sublist = bt(s.substring(str.length()), set, map);
        		for (String ss : sublist) {
        			res.add(str + (sublist.isEmpty()? "" : " ") + ss);
        		}
        	}
        }
        map.put(s, res);
        return res;
    }
}class Solution {
        
    private class seg {
        public int beg, end;
        public seg(int b, int e) {
            beg = b;
            end = e;
        }
    }
        public List<Integer> partitionLabels(String S) {
                // 类似merge intervals
        List<Integer> res = new LinkedList<>();
        if (S == null || S.length() == 0)
            return res;
        HashMap<Character, LinkedList<Integer>> map = new HashMap<>();
        char[] arr = S.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            LinkedList<Integer> list = map.getOrDefault(arr[i], new LinkedList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        PriorityQueue<seg> pq = new PriorityQueue<seg>(1, new Comparator<seg>() {

            @Override
            public int compare(seg s1, seg s2) {
                // TODO Auto-generated method stub
                return s1.beg - s2.beg;
            }
            
        });
        for (Map.Entry e : map.entrySet()) {
            char k = (char)e.getKey();
            LinkedList<Integer> v = (LinkedList<Integer>)e.getValue();
            pq.offer(new seg(v.peekFirst(), v.peekLast()));
            //System.out.println(v.peekFirst() + ":" + v.peekLast());
        }
        // merge intervals
        seg s1 = pq.poll();
        while (pq.size() != 0) {
            if (pq.peek().beg <= s1.end) { // merge
                s1.end = Math.max(s1.end, pq.peek().end);
                pq.poll();
            } else {
                seg s2 = pq.poll();
                res.add(s2.beg-s1.beg);
                s1 = s2;
            }
        }
        if (s1.beg == 0)
            res.add(S.length());
        else {
            res.add(S.length() - s1.beg);
        }
        return res;
    }

}