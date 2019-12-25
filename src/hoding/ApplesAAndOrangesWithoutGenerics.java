package hoding;

import java.util.ArrayList;

class apple{
    private static long counter = 0;
    private final long id = counter++;
    public long id(){
        return id;
    }
    static class orange{    }
}
public class ApplesAAndOrangesWithoutGenerics {
    public static void main(String[] args) {
        ArrayList apples = new ArrayList();
        for(int i =0 ; i < 3 ; i++){
            apples.add(new apple());
        }
      //  apples.add(new apple.orange());
        for(int i = 0 ; i <apples.size() ; i++){
            System.out.println( ((apple)apples.get(i)).id() );
        }
    }
}
