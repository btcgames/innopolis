package homework;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<Integer, String>(0);

        map.put("21", "qwe");
        map.put("14", "14obj");
        map.put("1", "1obj");
        map.put(null, "1obj");

        System.out.println(map.get("14"));
        System.out.println(map.get("1"));
        System.out.println(map.get(null));
        System.out.println(map.size());
        map.remove("21");


        System.out.println(map.size());
        System.out.println(map.containsKey("21"));
        System.out.println(map.containsKey("1"));
        System.out.println(map.containsKey(null));

    }


}
