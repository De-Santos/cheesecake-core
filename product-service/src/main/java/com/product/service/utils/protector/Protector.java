package com.product.service.utils.protector;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Protector {
    public static <T extends Throwable> void notNullRequired(Object o, T e) throws T {
        if (Objects.isNull(o)) {
            throw e;
        }
    }

    public static <T extends Throwable> void notNullRequired(T e, Object... o) throws T {
        for (Object obj : o) {
            if (Objects.isNull(obj)) {
                throw e;
            }
        }
    }

    public static void notNullRequired(Object... o) {
        if (Objects.isNull(o)) throw new NullPointerException();
        for (Object obj : o) {
            if (Objects.isNull(obj)) {
                throw new NullPointerException();
            }
        }
    }

    public static <T extends Throwable> void requiredOneNull(T e, Object... o) throws T {
        boolean haveNull = false;
        for (Object obj : o) {
            if (Objects.isNull(obj) && Boolean.TRUE.equals(haveNull)) throw e;
            if (Objects.isNull(obj)) haveNull = true;
        }
    }

    public static <T> T safeNull(T obj) {
        if (Objects.isNull(obj))
            throw new NullPointerException();
        return obj;
    }

    public static <T extends Iterable<O>, O> T safeNull(T iterable) {
        if (Objects.isNull(iterable)) {
            throw new NullPointerException();
        }
        iterable.forEach(item -> {
            if (Objects.isNull(item)) {
                throw new NullPointerException();
            }
        });
        return iterable;
    }

}
