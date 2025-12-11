package com.prakash;

import java.util.*;
import java.util.stream.Collectors;

public class Day8 implements Problem {

    @Override
    public void p1() {

        var points = lines().stream().map(line -> line.split(","))
                .map(tuple -> Arrays.stream(tuple)
                        .map(Long::parseLong)
                        .toArray(Long[]::new))
                .map(Tuple::from)
                .toArray(Tuple[]::new);

        var map = Arrays.stream(points).collect(
                Collectors.toMap(
                        tuple -> tuple,
                        tuple -> {
                            var list = new HashSet<Tuple>();
                            list.add(tuple);
                            return list;
                        }
                )
        );

        var list = new ArrayList<Tuples>();

        for (int i = 0; i < points.length; i++) {
            var tuple = points[i];
            for (int j = i + 1; j < points.length; j++) {
                var tuples = new Tuples(tuple, points[j]);
                list.add(tuples);
            }
        }

        var sorted = list.stream().sorted(Comparator.comparingLong(Tuples::distance)).limit(1000).toList();

        for (Tuples t : sorted) {
            var pair = map.entrySet().stream().filter(entry -> entry.getValue().contains(t.tuple1)).findFirst().orElseThrow();
            var pair2 = map.entrySet().stream().filter(entry -> entry.getValue().contains(t.tuple2)).findFirst().orElseThrow();
            if (pair.getKey().equals(pair2.getKey())) continue;
            pair.getValue().addAll(pair2.getValue());
            map.remove(pair2.getKey());
        }

        var res = map.values().stream().map(HashSet::size).sorted(Comparator.comparingInt(Integer::intValue).reversed()).limit(3).reduce(1, (a, b) -> a * b);

        System.out.println("part 1: " + res);
    }

    @Override
    public void p2() {
        var points = lines().stream().map(line -> line.split(","))
                .map(tuple -> Arrays.stream(tuple)
                        .map(Long::parseLong)
                        .toArray(Long[]::new))
                .map(Tuple::from)
                .toArray(Tuple[]::new);

        var map = Arrays.stream(points).collect(
                Collectors.toMap(
                        tuple -> tuple,
                        tuple -> {
                            var list = new HashSet<Tuple>();
                            list.add(tuple);
                            return list;
                        }
                )
        );

        var list = new ArrayList<Tuples>();

        for (int i = 0; i < points.length; i++) {
            var tuple = points[i];
            for (int j = i + 1; j < points.length; j++) {
                var tuples = new Tuples(tuple, points[j]);
                list.add(tuples);
            }
        }

        var sorted = list.stream().sorted(Comparator.comparingLong(Tuples::distance)).toList();

        for (Tuples t : sorted) {
            var pair = map.entrySet().stream().filter(entry -> entry.getValue().contains(t.tuple1)).findFirst().orElseThrow();
            var pair2 = map.entrySet().stream().filter(entry -> entry.getValue().contains(t.tuple2)).findFirst().orElseThrow();
            if (pair.getKey().equals(pair2.getKey())) continue;
            if(map.size() == 2) {
                System.out.println("part 2: " + t.tuple1.getX() * t.tuple2.getX());
                return;
            }
            pair.getValue().addAll(pair2.getValue());
            map.remove(pair2.getKey());
        }

        var res = map.values().stream().map(HashSet::size).sorted(Comparator.comparingInt(Integer::intValue).reversed()).limit(3).reduce(1, (a, b) -> a * b);

        System.out.println("part 1: " + res);
    }

    static class Tuples {
        Tuple tuple1;

        public Tuple getTuple2() {
            return tuple2;
        }

        public Tuple getTuple1() {
            return tuple1;
        }

        Tuple tuple2;

        public Tuples(Tuple tuple1, Tuple tuple2) {
            this.tuple1 = tuple1;
            this.tuple2 = tuple2;
        }

        public long distance() {

            return (long) Math.sqrt(Math.pow((tuple2.getX() - tuple1.getX()), 2) + Math.pow(tuple2.getY() - tuple1.getY(), 2) + Math.pow(tuple2.getZ() - tuple1.getZ(), 2));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tuples)) return false;
            var tuples = (Tuples) o;

            return tuples.tuple1.equals(this.tuple1) && tuples.tuple2.equals(tuple2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tuple1.hashCode(), tuple2.hashCode());
        }
    }

    static class Tuple {
        private long x;

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }

        public long getZ() {
            return z;
        }

        private long y;
        private long z;

        public Tuple(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static Tuple from(Long[] arr) {
            return new Tuple(arr[0], arr[1], arr[2]);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tuple)) return false;
            var tuple = (Tuple) o;

            return this.x == tuple.x && this.y == tuple.y && this.z == tuple.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, x);
        }
    }
}
