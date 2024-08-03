public abstract class X{
    protected int _xVal;
    public X(){
        _xVal = 3;
    }
    public X(int xVal){
        _xVal = xVal;
    }
    public abstract boolean foo int(n);
}

public class Y extends X{
    private int _yVal;
    public Y(int xVal, int yVal){
        super(xVal);
        _yVal = yVal;
    }
}

execute in the classes the method "equals", pay attention
objects of type X should be compared by their _xVal
objects of type Y should be compared by their _xVal and _yVal

write the method "equals" that should be in class X:

public boolean equals (Object obj)

{

      if תשובה
if(    )

            return false;

      return 


}

write the method "equals" that should be in class Y:

public boolean equals (X obj){
    if (       )
        return false
    return
}
}

