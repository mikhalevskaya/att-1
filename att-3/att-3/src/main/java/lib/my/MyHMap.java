package lib.my;
public class MyHMap<K, V> {
    public Object [] elements;
    public int size = 256;

    public MyHMap(){
        elements = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public void put(K key, V value){
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode%elements.length);//позиция
        MyEntry<K, V> entry = new MyEntry<>(hashCode, key, value);

        if (elements[index] == null){
            elements[index] = entry;
        } else {
            MyEntry<K, V> node = (MyEntry<K, V>) elements[index];
            while (node.getNext()!= null){
                node = node.getNext();
            }
            node.setNext(entry);
        }
    }


    @SuppressWarnings("unchecked")
    public V remove (K key){//удаление по ключу
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode%elements.length);//номер этого элемента в таблице

        if (elements[index] != null){
            MyEntry<K, V> node = (MyEntry<K, V>) elements[index];
            MyEntry<K, V> next = node.getNext();
            MyEntry<K, V> prev = node;
             while (node != null){
                 if (node.getHashCode() == hashCode){
                     if(key.equals(node.getKey())){
                         V valueToReturn = node.getValue();
                         if (prev == node){
                             elements[index] = node.getNext();
                         } else {
                             prev.setNext(node.getNext());
                         }
                         return valueToReturn;
                     }
                 }
                 prev = node;
                 node = next;
                 if (next != null){
                     next = next.getNext();
                 }
             }
        }
        return null;
    }

    private int index(K key){//индекс конкретного ключа в таблице
        if (key == null){
            return 0;
        }
        return Math.abs(key.hashCode()%size);
    }

    @SuppressWarnings("unchecked")
    private MyEntry<K, V> getEntry(K key, int index){
        if (index< 0){
            index = index(key);
        }
        for (MyEntry<K, V> curr = (MyEntry<K, V>) elements[index]; curr != null; curr = curr.getNext()){
            if (key.equals(curr.getKey())){
                return curr;
            }
        }
        return null;
    }

    public boolean containsKey(K key){
        return getEntry(key, -1) != null;
    }

    public K searchKey(K key){
        if (containsKey(key)){
            return key;
        } else {
            return null;
        }
    }
    @Override
    @SuppressWarnings("unchecked")
    public String toString(){
    StringBuilder stb = new StringBuilder();
        for (int i = 0; i< size; i++){
            if (elements[i] != null){
                MyEntry<K, V> currNode = (MyEntry<K, V>) elements[i];
                while (currNode != null){
                    stb.append(currNode.getKey() +" ");
                    currNode = currNode.getNext();
                }
            }
        }
        return stb.toString();
    }

    @SuppressWarnings("unchecked")
    public int count(K t, V present){//считает количество элементов с указанным ключом
        int k = 0;
        for (int i = 0; i< size; i++){
            if (elements[i] != null){
                MyEntry<K, V> currNode = (MyEntry<K, V>) elements[i];
                while (currNode != null){
                    if (currNode.getKey().equals(t)){
                        k++;
                    }
                    currNode = currNode.getNext();
                }
            }
        }
        return k;
    }
}
