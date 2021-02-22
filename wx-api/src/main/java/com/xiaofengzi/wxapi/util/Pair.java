package com.xiaofengzi.wxapi.util;

import java.io.Serializable;

public final class Pair<L, R> implements Serializable {

    private static final long serialVersionUID = 1L;

    private L left;

    private R right;

    private Pair(L left, R right){
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<L, R>(left, right);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (this.left != other.left && (this.left != null && !this.left.equals(other.left))) {
            return false;
        }
        if (this.right != other.right && (this.right != null && !this.right.equals(other.right))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.left != null ? this.left.hashCode() : 0);
        hash = 37 * hash + (this.right != null ? this.right.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Pair[%s,%s]", left, right);
    }
}
