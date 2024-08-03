public abstract class A{
    protected int _x;
    public A(){
        _x = 1;
    }
    
    public abstract int f(int x);
    
    public void f(A a){
        _x = a._x;
    }
}