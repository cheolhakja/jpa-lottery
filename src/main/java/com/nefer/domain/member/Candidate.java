package com.nefer.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Candidate {

    private Long id;
    private String name;

    public Candidate(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candidate(Long id) {
        this(id, null);
    }

    public Candidate(String name) {
        this(null, name);
    }

    public Candidate() {
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
