package interfaces;

interface I1{
    void f();
}
interface  I2{
    int f(int i );
}
interface  I3{
    int f();
}
class C {
    public int f(){
        return 1;
    }
}
class C2 implements I1,I2{
    @Override
    public void f() {

    }

    @Override
    public int f(int i) {
        return 1;
    }
}
class C3 extends C implements I2{
    @Override
    public int f(int i ) {
        return 1;
    }
}
class C4 extends C implements I3{
    @Override
    public int f() {
        return 1;
    }
}
// methods differ only by return type
//应该尽量避免方法的二义性问题

//class C5 extends C implements I1{
//
//}
//interface I4 extends I1,I3{
//
//}

//内部的接口可以是private
class A{
    private interface Cs{
        void f();
    }
}
