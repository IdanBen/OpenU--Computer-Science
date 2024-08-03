public class D extends B{
    public D(int val){
        _x = val - _x;
    }
    public void f(A a){
        _x = _x +a._x + 1;

    }
    public void f(B b){
        _x = _x*b._x;
    }
    public void f(D d){
        _x = d._x-1;
    }
}