public class B extends A{
    public B(){
        super();
    }
    public B(int val){
        _x = f(val);
    }
    public int f(int x){
        return _x + x;
        
}
public void f(B b){
    _x = _x * b._x;
    
}
}