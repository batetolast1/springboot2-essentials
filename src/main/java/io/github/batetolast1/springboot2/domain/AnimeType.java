package io.github.batetolast1.springboot2.domain;

public enum AnimeType {
    SHONEN(1), SHOJO(2), SEINEN(3), JOSEI(4), KODOMUKE(5);

    private final Integer code;

    AnimeType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
