package lib.my;
public class MyEntry<K, V> {//элемент HashMap
    private int hashCode;
    private K key;
    private V value;
    private MyEntry next;

    public MyEntry(int hashCode, K key, V value){
        this.hashCode = hashCode;
        this.key = key;
        this.value = value;
    }

    public int getHashCode(){
        return hashCode;
    }

    public void setHashCode( int hashCode){
        this.hashCode = hashCode;
    }

    public K getKey(){
        return key;
    }

    public void setKey( K key){
        this.key = key;
    }

    public V getValue(){
        return value;
    }

    public void setValue(V value){
        this.value = value;
    }

    public MyEntry<K, V> getNext(){
        return next;
    }

    public void setNext(MyEntry<K, V> next){
        this.next = next;
    }
}
