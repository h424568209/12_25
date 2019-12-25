package hoding;

import java.util.*;

public class iterators {
    class    Pet{
        static final int id =1;
        public int id(){
            return id;
        }
    }

    /**
     * iteator能够将遍历的序列的操作与序列底层的结构分离 --> 迭代器统一了对容器的访问方式
     */
    public static void display(Iterator<Pet> it){
        while(it.hasNext()){
            Pet next = it.next();
            System.out.println(next.id() +" :" + next);
        }
        System.out.println();
    }

    /**
     *  +U+n+c --- +e +I +t --- +a - +i -+ n +t+y ---+-+I+u--+l+e+s---
     *  +代表将后面的字母进行压栈， - 表示弹出栈顶字母并打印
     */
    public void stackpractice(){
        String s = "+U+n+c---+e+I+t---+a-+i-+n+t+y---+-+I+u--+l+e+s---";
        Stack<Character> stack = new Stack<>();
        int i =0 ;
        while(i<s.length()){
            char c = s.charAt(i);
            if(c=='+'){
                stack.push(s.charAt(++i));
            }else  if (c == '-'){
                System.out.println(stack.pop());
            }
            i++;
        }
    }
    public static void main(String[] args) {
        ArrayList<Pet> arrayList=  new ArrayList<>();
        LinkedList<Pet> linkedList =new LinkedList<>();
        HashSet<Pet> hashSet = new HashSet<>();
        TreeSet <Pet> treeSet = new TreeSet<>();
        display(arrayList.iterator());
        display(linkedList.iterator());
        display(hashSet.iterator());
        display(treeSet.iterator());


        iterators l = new iterators();
       l.stackpractice();

       //优先级队列 --覆写compare 方法进行逆序输出
       PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
           @Override
           public int compare(Integer o1, Integer o2) {
               return o2-o1;
           }
       });
       priorityQueue.offer(3);
        priorityQueue.offer(23);
        priorityQueue.offer(33);
        priorityQueue.offer(34);
        priorityQueue.offer(31);
        priorityQueue.offer(35);
        priorityQueue.offer(23);
        priorityQueue.offer(33);
        priorityQueue.offer(53);
        priorityQueue.offer(63);
        priorityQueue.offer(34);
        priorityQueue.offer(13);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }
}
