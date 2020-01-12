package task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    ArrayList<Entry<String>> tempList = new ArrayList<>();


    public CustomTree(){
        root = new Entry("0");
        tempList.add(root);
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName){
            this.elementName = elementName;
            availableToAddLeftChildren  = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren(){
            return (availableToAddLeftChildren|availableToAddRightChildren);
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return tempList.size()-1;
    }

    @Override
    public String set(int index, String element){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s){
        Entry<String> child = new Entry<>(s);

        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).availableToAddLeftChildren) {
                tempList.get(i).leftChild = child;
                tempList.get(i).availableToAddLeftChildren = false;
                child.parent = tempList.get(i);
                break;
            }
            if (tempList.get(i).availableToAddRightChildren) {
                tempList.get(i).rightChild = child;
                tempList.get(i).availableToAddRightChildren = false;
                child.parent = tempList.get(i);
                break;
            }

        }
        tempList.add(child);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!(o instanceof String))
            throw new UnsupportedOperationException();

        String elementName = (String) o;

        //Перебираем лист, пропуская root
        for(int i = 1; i < tempList.size(); i++) {

            Entry nextEntry = tempList.get(i);
            //Находим необходимую для удаления запись
            if(nextEntry.elementName.equals(elementName)) {

                //Удаляем найденную запись и ее детей
                removeChilds(nextEntry);
                tempList.remove(i);

                //восстанавливаем возможность добавить левого или правого ребенка
                Entry parent = nextEntry.parent;

                if(parent.leftChild.elementName == elementName) {
                    parent.availableToAddLeftChildren = true;
                    parent.leftChild = null;
                } else {
                    parent.availableToAddRightChildren = true;
                    parent.rightChild = null;
                }
            }
        }


        return true;
    }

    private void removeChilds(Entry entry) {
        List<Entry> childs = new ArrayList<>();
        for(int i = 0;  i < tempList.size(); i++) {
            Entry e = tempList.get(i);
            if(e.elementName == entry.elementName) {
                Entry leftChild = e.leftChild;
                Entry rightChild = e.rightChild;

                if(leftChild != null) {
                    removeChilds(leftChild);
                    childs.add(leftChild);
                }

                if(rightChild != null) {
                    removeChilds(rightChild);
                    childs.add(rightChild);
                }
            }
        }
        tempList.removeAll(childs);
    }


    public int getIndex(String s){
        int i = 0;
        for (Entry<String> stringEntry : tempList) {
            i++;
            if (stringEntry.elementName.equals(s)) return (i-1);}
        return -1;
    }


    @Override
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }

    public String getParent(String s) {
          for (Entry<String> stringEntry : tempList) {
              if (stringEntry.elementName.equals(s)) {  return stringEntry.parent.elementName;}
        }
        return null;

    }

}
