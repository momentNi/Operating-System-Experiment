package Experiment4;

import java.util.HashMap;

public class MFD {

    private HashMap<String, UFD> pairs = new HashMap<>();

    public void addPair(String name, UFD pointer){
        pairs.put(name, pointer);
    }

    public boolean find(String name){
        return pairs.containsKey(name);
    }

    public UFD get(String name){
        return pairs.get(name);
    }

    public void remove(String fileName) {
        pairs.remove(fileName);
    }

    public void show(){
        for(String i : pairs.keySet()) {
            System.out.println(pairs.get(i).toString());
        }
    }
}
