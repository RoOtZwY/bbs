package com.rootzwy.bbs.common.dto;

import java.util.Objects;

/**
 * @param <L> left
 * @param <R> right
 * @author zwy
 */
public class Pair<L, R> extends DTO {

    private final L left;
    private final R right;

    private Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    public L getLeftValue() {
        return left;
    }

    public R getRightValue() {
        return right;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Pair)) {
            return false;
        } else {
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return Objects.equals(this.getLeftValue(), pair.getLeftValue())
                    && Objects.equals(this.getRightValue(), pair.getRightValue());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(left) ^ Objects.hashCode(right);
    }

}
