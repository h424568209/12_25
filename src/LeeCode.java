import java.util.*;


public class LeeCode {
    private Map<Character,Integer> map = new TreeMap<>();

    /**
     * 根据字符出现的频率排序
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     * @param s 字符串
     * @return 排序后的字符串
     */
    private String frequencySort(String s) {
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        List<Map.Entry<Character,Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        //根据value进行逆序排列
        list.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        //已经排好序 进行拼接返回
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character,Integer> maps : list){
            for(int i = 0; i < maps.getValue() ; i++){
                sb.append(maps.getKey());
            }
        }
        return sb.toString();
    }
    /**
     * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
     *
     * 用每一个点计算出其他点和这个点之间的距离，存放在map中，若map中有这个距离，则交换顺序后依然存在，所以乘2，再将map中的元素个数加1
     * 若没有这个距离，将这个距离放道map中
     * 每次一组循环进行map清零 重新开始记录
     * @param points 二维数组表示点的集合
     * @return 是“回旋镖” 的个数
     */
    private int numberOfBoomerangs(int[][] points) {
        Map<Double,Integer> map = new HashMap<>();
        int res = 0 ;
        for(int i = 0  ; i < points.length ; i++){
            map.clear();
            for(int j = 0 ; j < points.length ; j++){
                if(i == j ){
                    continue;
                }
                double d = Math.pow(points[i][0] - points[j][0],2)+Math.pow(points[i][1] - points[j][1],2);
                if(map.containsKey(d)){
                    res += map.get(d)*2;
                    map.put(d,map.get(d)+1);
                }else{
                    map.put(d,1);
                }
            }
        }
        return res;
    }

    /**
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100
     *
     *  维持一个p.length大小的窗口大长度，进行两个判断 长度和字符 ，如果都满足了 就是字母异位词
     */
    private List<Integer> findAnagrams(String s, String p) {
        //记录s的每个字符和出现的次数
        Map<Character ,Integer> smap = new HashMap<>();
        //记录p的每个字符和出现的次数
        Map<Character,Integer> pmap = new HashMap<>();
        for(char ch : p.toCharArray()){
            pmap.put(ch,pmap.getOrDefault(ch,0)+1);
        }
        List<Integer> res = new ArrayList<>();
        //候选字符的个数
        int count =0 ;
        int len = p.length();
        int left =0 ;
        int right = 0 ;
        while(right < s.length()){
            char ch = s.charAt(right);
            smap.put(ch,smap.getOrDefault(ch,0)+1);
            //如果p种包含当前字符，且s的窗口中该字符出现的次数不足，则该字符可以做为候选字符，count+1
            if(pmap.containsKey(ch) && smap.get(ch) <= pmap.get(ch)){
                count++;
            }
            //当候选字符个数等于p长度，此时left为起始索引
            if(count == len){
                res.add(left);
            }
            //当窗口大小等于p的长度时，窗口左边需要收缩一个字符
            if(right - left +1 >=len){
                char leftchar = s.charAt(left);
                //判断收缩的这个字符是不是候选字符，是则count-1
                if(pmap.containsKey(leftchar) && smap.get(leftchar) <= pmap.get(leftchar))
                    count--;
                //窗口收缩一个字符
                smap.put(leftchar,smap.getOrDefault(leftchar,1)-1);
                left++;
            }
            right++;
        }
        return res;
    }

    /**
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     * @param s 字符串
     * @return 最大回文长度
     */
    private int longestPalindrome(String s) {
        Map<Character,Integer> map=  new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int val = 0;
        int len = 0;
       for(Map.Entry<Character,Integer> entry  : map.entrySet()){
            val = entry.getValue();
           len+=((val/2)*2);
       }
       if(len< s.length()){
           len++;
       }
        return len;
    }
    private int longestPalindrome1(String s) {
        int [] arr = new int[58];
        for(char c : s.toCharArray()){
            arr[c-'A']++;
        }
        int lens = 0;
        for(int num : arr){
            lens += (num/2)*2;
        }
        if(lens <s.length()){
            lens ++;
        }
        return lens;
    }
    /**
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母。
     * @param s 原字符串
     * @param t 改变后的字符串
     * @return 多出的一位字符
     */
    private char findTheDifference(String s, String t) {
        int []arr = new int[26];
        for(char c:s.toCharArray()){
            arr[c-'a']++;
        }
        for(char c : t.toCharArray()){
            arr[c-'a']--;
            if(arr[c-'a'] < 0 ){
                return c;
            }
        }
        return t.charAt(t.length()-1);
    }

    /**
     *  将两个字符串转换成字符数组，求和做差，就可以得到插入的元素
     */
    private char findTheDifferences(String s, String t) {
        char num = 0 ;
        for(char c : t.toCharArray()){
            num+=c;
        }
        for(char c : s.toCharArray()){
            num-=c;
        }
        return num;
    }
    private char findTheDifference2(String s, String t) {
        String res=  s+t;
        int ret = 0;
        for(char c:res.toCharArray()){
            ret ^= c;
        }
        return (char)ret;
    }


    public static void main(String[] args) {
        LeeCode l = new LeeCode();
        System.out.println(l.findTheDifference2("ad","vda"));
        System.out.println(l.findTheDifference("ad","add"));
        System.out.println(l.findTheDifferences("add","adddd"));
        System.out.println(l.longestPalindrome("aaabbbAAxx"));
        System.out.println(l.longestPalindrome1("aaabbbAAcc"));

        System.out.println(l.findAnagrams("cbaebabacd","abc"));

        System.out.println(l.numberOfBoomerangs(new int[][]{{1,0},{0,0},{2,0}}));

        System.out.println(l.frequencySort("aaabbcddddEEPPPPPPPP"));
    }
}
