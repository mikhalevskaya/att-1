package lib.my;
public class MyHSet<T> {
    private static final Boolean EXIST = true;
    private final MyHMap<T, Boolean> map = new MyHMap<>();

    public boolean contains(T o){
        return map.containsKey(o);
    }

    public boolean add(T t){
        if (map.containsKey(t)){
            return false;
        }
        map.put(t, EXIST);
        return true;
    }

    public boolean remove(T o){
        if (map.containsKey(o)){
            map.remove(o);
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return map.toString();
    }
}
