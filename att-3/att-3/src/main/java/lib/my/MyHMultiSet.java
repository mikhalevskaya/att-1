package lib.my;
public class MyHMultiSet<T> {
    private static final Boolean PRESENT = true;
    private final MyHMap<T, Boolean> map = new MyHMap<>();

    public boolean contains(T o){
        return map.containsKey(o);
    }

    public boolean add(T t){
        map.put(t, PRESENT);
        return true;
    }

    public boolean remove(T o){
        if (map.containsKey(o)){
            map.remove(o);
            return true;
        }
        return false;
    }

    public int count(T t){
        return map.count(t, PRESENT);
    }

    @Override
    public String toString(){
        return map.toString();
    }
}
