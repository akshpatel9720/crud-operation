package com.example.taskcrudoperation.controller;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EX {
    public static void main(String[] args) {
//        Integer array[] = {1, 2, 3, 4, 5, 6,25,30};
//        List<Integer> list = Arrays.asList(array);
//        Map<Integer, Integer> pairs = new HashMap();
//        int sum = 31;
//        for (int i : array) {
//            if (pairs.containsKey(i)) {
//                pairs.put(sum - i, i);
//            } else if (!pairs.containsValue(i)) {
//                pairs.put(sum - i, null);
//            }
//        }
//        pairs.values().removeAll(Collections.singleton(null));
//        System.out.println("Pairs are" + pairs);

//        String s = "hardik aksh sahil";
//        Set<String> set = new HashSet<>();


//        String a[] = s.split(" ");
//        for (String s1 : a) {
//            set.add(s1);
//            System.out.println(s1);
//        }
//        System.out.println(set);

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map.put("seriesId", "pqn");
        map.put("JonerVn", "xyz");
        map.put("testVn", 123);
        map.put("seasonId", "abc");
        map.put("abc", 123);
        map.put("xyz", 123);

//        map1 = map.entrySet().stream().filter(p -> p.getKey().contains("Id") || p.getKey().contains("Vn")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        System.out.println(map1);

        String s = (String) map.get("seriesId");
        System.out.println(s);

        Set<Integer> set = new HashSet<Integer>();
        set.add(10);
        set.add(0);
        set.add(30);
        set.add(40);



    }
}


