package com.belle.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

public class Safes {


    private static final Integer INIT_SIZE = 0;

    public static <K, V> Map<K, V> of(Map<K, V> source) {
        return (Map) Optional.ofNullable(source).orElse(Maps.newHashMapWithExpectedSize(INIT_SIZE));
    }

    public static <T> Set<T> of(Set<T> source) {
        return Optional.ofNullable(source).orElse(Sets.newHashSetWithExpectedSize(INIT_SIZE));
    }

    public static <T> List<T> of(List<T> source) {
        return Optional.ofNullable(source).orElse(Lists.newArrayListWithCapacity(INIT_SIZE));
    }

    public static <T> Iterator<T> of(Iterator<T> source) {
        return Optional.ofNullable(source).orElse((Iterator<T>) Lists.newArrayListWithCapacity(INIT_SIZE).iterator());
    }

    public static <T> Collection<T> of(Collection<T> source) {
        return Optional.ofNullable(source).orElse(Lists.newArrayListWithCapacity(INIT_SIZE));
    }

    public static <T> Iterable<T> of(Iterable<T> source) {
        return Optional.ofNullable(source).orElse(Lists.newArrayListWithCapacity(INIT_SIZE));
    }
}
