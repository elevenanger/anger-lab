package cn.anger.instanceconfinement;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : anger
 * 通过对象封闭和锁来确保类的线程安全
 */
@ThreadSafe
public class PersonSet {
    @GuardedBy("this")
    private final Set<Person> personSet = new HashSet<>();
    public synchronized void addPerson(Person person) {
        personSet.add(person);
    }
    public synchronized boolean containsPerson(Person person) {
        return personSet.contains(person);
    }
    public synchronized int numberOfPerson() {
        return personSet.size();
    }
}
