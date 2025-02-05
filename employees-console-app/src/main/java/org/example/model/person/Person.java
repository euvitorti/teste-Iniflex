package org.example.model.person;

import java.time.LocalDate;

public record Person(String name, LocalDate birthDate) {
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
