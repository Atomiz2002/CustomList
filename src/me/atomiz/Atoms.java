package me.atomiz;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * A custom list with specified elements of a given type.
 *
 * @param <T> the type
 */
class Atoms<T> {
    T[] atoms;

    @SuppressWarnings("unchecked")
    public Atoms() {
        this.atoms = (T[]) new Object[0];
    }

    public Atoms(T[] array) {
        atoms = array;
    }

    @SuppressWarnings("unchecked")
    public Atoms(Collection<T> list) {
        atoms = (T[]) list.toArray();
    }

    // TODO: Create constructor which argument of type Atoms
    
    // region basics

    /**
     * Returns the amount of occurrences of the specified element in the list.
     *
     * @param e the element to count
     * @return the amount
     */
    public int count(@NotNull T e) {
        int size = 0;
        for (T el : atoms)
            if (Objects.equals(el, e))
                size++;

        return size;
    }

    /**
     * Checks whether the list contains the specified element.
     *
     * @param e the element
     * @return {@code true} if the list contains the specified element
     */
    public boolean contains(@NotNull T e) {
        for (T a : atoms)
            if (Objects.equals(a, e))
                return true;

        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list or -1 if the list does not contain the element.
     *
     * @param e the element
     * @return the index of the element or -1
     */
    public int indexOf(T e) {
        for (int i = 0; i < atoms.length; i++)
            if (Objects.equals(atoms[i], e))
                if (atoms[i].equals(e))
                    return i;

        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in the list or -1 if the list does not contain the element.
     *
     * @param e the element
     * @return the last index of the element or -1
     */
    public int lastIndexOf(T e) {
        for (int i = atoms.length - 1; i >= 0; i--)
            if (Objects.equals(atoms[i], e))
                return i;

        return -1;
    }

    /**
     * Returns the element located at the specified index in the list.
     *
     * @param i the index
     * @return the element
     */
    public T get(int i) {
        return atoms[i];
    }

    /**
     * Returns the amount of elements stored in the list.
     *
     * @return the list size
     */
    public int size() {
        return atoms.length;
    }

    /**
     * Returns {@code true} if the list has no elements.
     *
     * @return {@code true} if the list has no elements
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Clears the list.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        atoms = (T[]) new Object[0];
    }

    /**
     * Performs an action on each element.
     *
     * @param action the action
     */
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T atom : atoms)
            action.accept(atom);
    }

    /**
     * Retrieves the specified amount of elements starting from the specified index.
     *
     * @param start the start index (inclusive)
     * @param count the end index (exclusive)
     * @return the elements
     */
    public Atoms<T> getRange(int start, int count) {
        Atoms<T> result = new Atoms<>();
        for (int k = start; k < count; k++)
            result.add(atoms[k]);

        return result;
    }

    /**
     * Returns a list of the elements in the current list starting from the specified index and ending at the specified second index param.
     *
     * @param from the starting index (inclusive) of the sublist
     * @param to   the ending index (exclusive) of the sublist
     * @return the elements
     */
    public Atoms<T> subList(int from, int to) {
        Atoms<T> result = new Atoms<>();
        for (int k = from; k < to; k++)
            result.add(atoms[k]);

        return result;
    }

    /**
     * Prints each element of the list on a new line.
     */
    //TODO: Instead of printing directly to the console, you can override toString method. Then the consumer of this library can decide where and how to print the result.
    //Most of the time writing directly to the console is a bad practice. If you decide to use this library in a desktop application, you won't need to print into the console but into a UI component.
    //In this scenarion should you create a separate method printlnToComponent or something like this?
    public void println() {
        for (T atom : atoms)
            System.out.println(atom);
    }

    /**
     * Prints each element of the list on a new line by replacing the empty values with the specified string.
     *
     * @param replacement The replacement
     */
    public void println(String replacement) {
        for (T atom : atoms)
            if (atom == null || atom.toString().isEmpty())
                System.out.println(replacement);
            else
                System.out.println(atom);
    }

    // endregion

    // region add

    /**
     * Adds the specified element to the list.
     *
     * @param e the element
     */
    //TODO: Why do you create a new array each time you add an element. What is the O notation for this operation? What should be in a perfect scenarion?
    public void add(T e) {
        atoms = Arrays.copyOf(atoms, atoms.length + 1);
        atoms[atoms.length - 1] = e;
    }

    /**
     * Inserts the specified element at the specified index in the list.
     *
     * @param index the index
     * @param e     the element
     */
    //TODO: Same thing applies here
    public void add(int index, T e) {
        atoms = Arrays.copyOf(atoms, atoms.length + 1);
        System.arraycopy(atoms, index, atoms, index + 1, atoms.length - index - 1);
        set(index, e);
    }

    /**
     * Adds the elements of the specified {@link Arrays Array} to the list.
     *
     * @param e the {@link Arrays array} of elements
     */
    //TODO: What is the O notation for this operation? What should be in a perfect scenarion?
    public void addAll(T[] e) {
        Arrays.stream(e).forEach(this::add);
    }

    /**
     * Adds the elements of the specified {@link Collection} to the list.
     *
     * @param e the {@link Collection Collection}
     */
    public void addAll(@NotNull Collection<T> e) {
        e.forEach(this::add);
    }
    
    //TODO: How to concatenate two Atoms objects?

    /**
     * Inserts the elements of the specified {@link Arrays Array} to the list starting from the specified index.
     *
     * @param index   the index
     * @param element the element
     */
    public void addAll(int index, T[] element) {
        atoms = Arrays.copyOf(atoms, atoms.length + element.length);
        System.arraycopy(atoms, index, atoms, index + element.length, atoms.length - index - element.length);

        for (int j = 0; j < element.length; j++)
            set(index + j, element[j]);
    }

    /**
     * Inserts the elements of the specified {@link Collection} to the list starting from the specified index.
     *
     * @param index      the index
     * @param collection the collection to add
     */
    @SuppressWarnings("unchecked")
    public void addAll(int index, Collection<T> collection) {
        addAll(index, (T[]) collection.toArray());
    }

    // endregion

    // region set

    /**
     * Sets the element at the specified index to the specified element.
     *
     * @param index   the index
     * @param element the element
     * @return the element previously at this index
     */
    public T set(int index, T element) {
        T prev = atoms[index];
        atoms[index] = element;

        return prev;
    }

    /**
     * Sets the first occurrence of the specified element to the specified replacement element.
     *
     * @param element the element to replace
     * @param replace the replacement
     * @return {@code true} if the list was modified
     */
    //TODO: Instead of checking with contains, you can use indexOf directly. If there is no occurrence you will receive index -1, for which you can check in the if statement.
    public boolean set(T element, T replace) {
        if (!contains(element)) return false;

        atoms[indexOf(element)] = replace;
        return true;
    }

    /**
     * Sets the last occurrence of the specified element to the specified replacement.
     *
     * @param element the element to replace
     * @param replace the replacement
     * @return {@code true} if the list was modified
     */
    //TODO: Similar to the above TODO
    public boolean setLast(T element, T replace) {
        if (!contains(element)) return false;

        atoms[lastIndexOf(element)] = replace;

        return true;
    }

    // endregion

    // region remove

    /**
     * Removes the element at the specified index from the list.
     *
     * @param i the index of the element to remove from the list
     * @return the removed element from this index
     */
    public T remove(int i) {
        T e = atoms[i];

        if (i != size() - 1)
            System.arraycopy(atoms, i + 1, atoms, i, size() - i - 1);

        removeLast();

        return e;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param e the element
     */
    public void remove(T e) {
        if (contains(e))
            if (indexOf(e) != size() - 1)
                System.arraycopy(atoms, indexOf(e) + 1, atoms, indexOf(e), size() - indexOf(e) - 1);

        removeLast();
    }

    /**
     * Removes the last element of the list.
     */
    public void removeLast() {
        if (atoms.length > 0) atoms = Arrays.copyOf(atoms, atoms.length - 1);
    }

    /**
     * Removes the last occurrence of the specified element in the list.
     */
    public void removeLast(T e) {
        remove(lastIndexOf(e));
    }

    /**
     * Removes all occurrences of the specified element from the list.
     *
     * @param e The element
     * @return {@code true} if the list was modified
     */
    public boolean removeAll(T e) {
        if (!contains(e)) return false;

        while (contains(e))
            remove(e);

        return true;
    }

    // endregion
}
