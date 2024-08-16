package org.example;

import org.example.utils.customList.customLinkedListImpl.CustomLinkedList;
import org.example.utils.customList.CustomList;
import org.example.utils.customMap.CustomMap;
import org.example.utils.customMap.customHashMapImpl.CustomHashMap;
import org.example.utils.customQueue.CustomQueue;
import org.example.utils.customQueue.customPriorityQueueImpl.CustomPriorityQueue;
import org.w3c.dom.ls.LSOutput;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        CustomList<String> list = new CustomLinkedList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        list.add("fifth");
        list.add("sixth");
        list.update(5, "updated");

        System.out.println(list.get(4));
        System.out.println(list.size());
        System.out.println(list.get(5));

        list.delete(2);

        System.out.println("---------------------------");
        for (String s: list) {
            System.out.println(s);
        }

       CustomMap<String, Integer> map = new CustomHashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        map.put("fourth", 4);
        map.put("fifth", 5);
        map.put("sixth", 6);

        map.remove("third");

        System.out.println(map.size());
        System.out.println(map.get("fourth"));
        System.out.println(map);

        CustomQueue<Integer> q = new CustomPriorityQueue<>();
        q.add(12);
        q.add(14);
        q.add(10);
        q.add(55);
        q.add(8);
        q.add(13);
        q.add(10);
        q.add(12);
        q.add(20);
        q.add(220);
        System.out.println(q.peek());
        System.out.println(q);
    }
}