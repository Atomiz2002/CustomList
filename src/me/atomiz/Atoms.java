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
        atoms = (T[]) new Object[0];
    }

    public Atoms(T[] array) {
        atoms = array;
    }

    @SuppressWarnings("unchecked")
    public Atoms(Collection<T> list) {
        atoms = (T[]) list.toArray();
    }

    /**
     * Counts the amount of occurrences of the specified element in the list.
     *
     * @param e the element to count
     * @return the count
     */
    public int count(T e) {
        int size = 0;
        for (Object el : atoms)
            if (el != null && el.equals(e))
                size++;

        return size;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param e the element
     * @return {@code true} if the list contains the specified element
     */
    public boolean contains(T e) {
        for (Object a : atoms)
            if (a != null)
                if (a.equals(e))
                    return true;

        return false;
    }

    /**
     * Retrieves the index of the specified element in the list.
     *
     * @param e the element
     * @return The index
     * @see Atoms#lastIndexOf(Object)
     */
    public int indexOf(T e) {
        for (int i = 0; i < atoms.length; i++)
            if (atoms[i] != null)
                if (atoms[i].equals(e))
                    return i;

//        throw new NoSuchElementException(e.toString());
        return -1;
    }

    /**
     * Retrieves the index of the last occurrence of the specified element in the list.
     *
     * @param e the element
     * @return its last index
     * @see Atoms#indexOf(Object)
     */
    public int lastIndexOf(T e) {
        for (int i = atoms.length - 1; i >= 0; i--) {
            if (atoms[i] != null)
                if (atoms[i].equals(e)) {
                    return i;
                }
        }
//        throw new NoSuchElementException(e.toString());
        return -1;
    }

    /**
     * Retrieves the element located at the specified index.
     *
     * @param i the index
     * @return the element
     */
    public T get(int i) {
        return atoms[i];
    }

    /**
     * Retrieves the elements located between the specified indexes.
     *
     * @param i the start index (inclusive)
     * @param j the end index (exclusive)
     * @return the elements
     */
    public Atoms<T> getRange(int i, int j) {
        Atoms<T> r = new Atoms<>();
        for (int k = i; k < j; k++)
            r.add(atoms[k]);

        return r;
    }

    /**
     * Adds the specified element to the list.
     *
     * @param e the element
     * @return the modified list
     * @see Atoms#addAll(Object[])
     * @see Atoms#addAll(Collection)
     */
    public Atoms<T> add(T e) {
        atoms = Arrays.copyOf(atoms, atoms.length + 1);
        atoms[atoms.length - 1] = e;

        return this;
    }

    /**
     * Adds the elements of the specified {@link Arrays Array} to the list.
     *
     * @param e the {@link Arrays array} of elements
     * @return the modified list
     * @see Atoms#add(Object)
     * @see Atoms#addAll(Collection)
     */
    public Atoms<T> addAll(T[] e) {
        Arrays.stream(e).forEach(this::add);

        return this;
    }

    /**
     * Adds the elements of the specified {@link Collection} to the list.
     *
     * @param e the {@link Collection Collection}
     * @return the modified list
     * @see Atoms#add(Object)
     * @see Atoms#addAll(Object[])
     */
    public Atoms<T> addAll(@NotNull Collection<T> e) {
        e.forEach(this::add);

        return this;
    }

    /**
     * Inserts the specified element at the specified index in the list.
     *
     * @param i the index
     * @param e the element
     * @return the modified list
     * @see Atoms#insertAll(int, Object[])
     * @see Atoms#insertAll(int, Collection)
     */
    public Atoms<T> insert(int i, T e) {
//        if (i > atoms.length - 1) throw new ArrayIndexOutOfBoundsException();

        atoms = Arrays.copyOf(atoms, atoms.length + 1);
        System.arraycopy(atoms, i, atoms, i + 1, atoms.length - i - 1);
        set(i, e);

        return this;
    }

    /**
     * Inserts the elements of the specified {@link Arrays Array} to the list starting from the specified index.
     *
     * @param i the index
     * @param e the element
     * @return the modified list
     * @see Atoms#insert(int, Object)
     * @see Atoms#insertAll(int, Collection)
     */
    public Atoms<T> insertAll(int i, T[] e) {
//        if (i > atoms.length - 1) throw new ArrayIndexOutOfBoundsException();

        atoms = Arrays.copyOf(atoms, atoms.length + e.length);
        System.arraycopy(atoms, i, atoms, i + e.length, atoms.length - i - e.length);

        for (int j = 0; j < e.length; j++)
            set(i + j, e[j]);

        return this;
    }

    /**
     * Inserts the elements of the specified {@link Collection} to the list starting from the specified index.
     *
     * @param i the index
     * @param e the element
     * @return the modified list
     * @see Atoms#insert(int, Object)
     * @see Atoms#insertAll(int, Object[])
     */
    @SuppressWarnings("unchecked")
    public Atoms<T> insertAll(int i, Collection<T> e) {
//        if (i > atoms.length - 1) throw new ArrayIndexOutOfBoundsException();

        insertAll(i, (T[]) e.toArray());

        return this;
    }

    /**
     * Replaces the element at the specified index with the specified element.
     *
     * @param i the index
     * @param e the element
     * @return the modified list
     * @see Atoms#set(Object, Object)
     */
    public Atoms<T> set(int i, T e) {
        atoms[i] = e;

        return this;
    }

    /**
     * Replaces the first occurrence of the specified element with the specified replacement element.
     *
     * @param e the element
     * @param r the replacement
     * @return the modified list
     * @see Atoms#set(int, Object)
     */
    public Atoms<T> set(T e, T r) {
        atoms[indexOf(e)] = r;
        return this;
    }

    /**
     * Removes the first occurrence of the element at the specified index in the list.
     *
     * @param i The index of the element to remove from the list
     * @return The modified list
     * @see Atoms#remove(Object)
     * @see Atoms#removeAll(int)
     * @see Atoms#removeAll(Object)
     * @see Atoms#removeLast()
     * @see Atoms#removeLast(Object)
     */
    public Atoms<T> remove(int i) {
        remove(get(i));

        return this;
    }

    /**
     * Removes the first occurrence of the specified element in the list.
     *
     * @param e The element
     * @return The modified list
     * @see Atoms#remove(int)
     * @see Atoms#removeAll(int)
     * @see Atoms#removeAll(Object)
     * @see Atoms#removeLast()
     * @see Atoms#removeLast(Object)
     */
    @SuppressWarnings("unchecked")
    public Atoms<T> remove(T e) {
        if (!contains(e)) return this;

        int index = indexOf(e);
        Object[] copy = new Object[atoms.length - 1];

        for (int i = 0, j = 0; i < atoms.length; i++) {
            if (i == index) continue;
            copy[j++] = atoms[i];
        }
        atoms = (T[]) copy;

        return this;
    }

    /**
     * Removes all occurrences of the element at the specified index in the list.
     *
     * @param i The index of the element
     * @return The modified list
     * @see Atoms#remove(int)
     * @see Atoms#remove(Object)
     * @see Atoms#removeAll(Object)
     * @see Atoms#removeLast()
     * @see Atoms#removeLast(Object)
     */
    public Atoms<T> removeAll(int i) {
        removeAll(get(i));

        return this;
    }

    /**
     * Removes all occurrences of the specified element in the list.
     *
     * @param e The element
     * @return The modified list
     * @see Atoms#remove(int)
     * @see Atoms#remove(Object)
     * @see Atoms#removeAll(int)
     * @see Atoms#removeLast()
     * @see Atoms#removeLast(Object)
     */
    public Atoms<T> removeAll(T e) {
        while (contains(e))
            remove(e);

        return this;
    }

    /**
     * Removes the last element of the list.
     *
     * @return The modified list
     * @see Atoms#remove(int)
     * @see Atoms#remove(Object)
     * @see Atoms#removeAll(int)
     * @see Atoms#removeAll(Object)
     * @see Atoms#removeLast(Object)
     */
    public Atoms<T> removeLast() {
        atoms = Arrays.copyOf(atoms, atoms.length - 1);

        return this;
    }

    /**
     * Removes the last occurrence of the specified element in the list.
     *
     * @param e the element
     * @return the modified list
     * @see Atoms#remove(int)
     * @see Atoms#remove(Object)
     * @see Atoms#removeAll(int)
     * @see Atoms#removeAll(Object)
     * @see Atoms#removeLast()
     */
    @SuppressWarnings("unchecked")
    public Atoms<T> removeLast(T e) {
        if (!contains(e)) return this;

        int index = lastIndexOf(e);
        Object[] copy = new Object[atoms.length - 1];

        for (int i = atoms.length - 1, j = i; i >= 0; i--) {
            if (i == index) continue;
            copy[--j] = atoms[i];
        }
        atoms = (T[]) copy;

        return this;
    }

    /**
     * Clears the list.
     *
     * @return the empty list
     */
    @SuppressWarnings("unchecked")
    public Atoms<T> clear() {
        atoms = (T[]) new Object[0];
        return this;
    }

    /**
     * Performs an action on each element.
     *
     * @param action the action
     * @return the modified list
     */
    public Atoms<T> forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T atom : atoms)
            action.accept(atom);

        return this;
    }

    /**
     * Prints each element of the list on a new line by replacing the empty values with the specified string.
     *
     * @param empty The replacement
     * @return The list
     * @see Atoms#println()
     * @see Atoms#print(Object)
     */
    public Atoms<T> println(String empty) {
        for (Object atom : atoms)
            if (atom == null || atom.toString().isEmpty())
                System.out.println(empty);
            else
                System.out.println(atom);
        return this;
    }

    /**
     * Prints each element of the list on a new line.
     *
     * @return the list
     * @see Atoms#println(String)
     * @see Atoms#print(Object)
     */
    public Atoms<T> println() {
        for (Object atom : atoms)
            System.out.println(atom);

        return this;
    }

    /**
     * Prints the specified value to the console but still returning the list (mainly used for debugging purposes).
     *
     * @param value the value
     * @return the list
     * @see Atoms#println()
     * @see Atoms#println(String)
     */
    public Atoms<T> print(Object value) {
        System.out.println(value);

        return this;
    }
}