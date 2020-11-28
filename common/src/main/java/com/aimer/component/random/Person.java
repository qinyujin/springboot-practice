package com.aimer.component.random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author :覃玉锦
 * @create :2020-09-22 14:04:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable {
    private String name;
    private int age;

    @Override
    public int compareTo(Object o) {
        if(o instanceof Person){
            Person p = (Person) o;
            int cmp = this.name.compareTo((p.getName()));
            if(cmp!=0){
                return Integer.compare(this.age, p.getAge());
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
