package org.example.model.employee;

import org.example.model.person.Person;

import java.math.BigDecimal;

public record Employee(Person person, BigDecimal salary, String role) {}
