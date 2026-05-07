package com.narxoz.rpg.memento;

import com.narxoz.rpg.combatant.HeroMemento;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stores hero snapshots as opaque values.
 * Caretaker cannot inspect HeroMemento internals — getters are package-private.
 */
public class Caretaker {

    private final Deque<HeroMemento> history = new ArrayDeque<>();

    /** Pushes a new snapshot onto the history stack. */
    public void save(HeroMemento memento) {
        history.push(memento);
    }

    /** Removes and returns the most recent snapshot. */
    public HeroMemento undo() {
        return history.isEmpty() ? null : history.pop();
    }

    /** Returns the most recent snapshot without removing it. */
    public HeroMemento peek() {
        return history.isEmpty() ? null : history.peek();
    }

    /** Reports how many snapshots are stored. */
    public int size() {
        return history.size();
    }
}
