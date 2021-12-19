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
class AtomsBuilder<T> {
    T[] builder;

    @SuppressWarnings("unchecked")
    public AtomsBuilder() {
        this.builder = (T[]) new Object[0];
    }

    public AtomsBuilder(T[] array) {
        builder = array;
    }

    @SuppressWarnings("unchecked")
    public AtomsBuilder(Collection<T> list) {
        builder = (T[]) list.toArray();
    }

    // region basics

    /**
     * Returns the amount of occurrences of the specified element in the list.
     *
     * @param e the element to count
     * @return the amount
     */
    public int count(@NotNull T e) {
        int size = 0;
        for (T el : builder)
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
        for (T a : builder)
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
        for (int i = 0; i < builder.length; i++)
            if (Objects.equals(builder[i], e))
                if (builder[i].equals(e))
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
        for (int i = builder.length - 1; i >= 0; i--)
            if (Objects.equals(builder[i], e))
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
        return builder[i];
    }

    /**
     * Returns the amount of elements stored in the list.
     *
     * @return the list size
     */
    public int size() {
        return builder.length;
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
     *
     * @return the modified list
     */
    @SuppressWarnings("unchecked")
    public AtomsBuilder<T> clear() {
        builder = (T[]) new Object[0];

        return this;
    }

    /**
     * Performs an action on each element.
     *
     * @param action the action
     * @return the modified list
     */
    public AtomsBuilder<T> forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T atom : builder)
            action.accept(atom);

        return this;
    }

    /**
     * Retrieves the specified amount of elements starting from the specified index.
     *
     * @param start the start index (inclusive)
     * @param count the end index (exclusive)
     * @return the elements
     */
    public AtomsBuilder<T> getRange(int start, int count) {
        AtomsBuilder<T> result = new AtomsBuilder<>();
        for (int k = start; k < count; k++)
            result.add(builder[k]);

        return result;
    }

    /**
     * Returns a list of the elements in the current list starting from the specified index and ending at the specified second index param.
     *
     * @param from the starting index (inclusive) of the sublist
     * @param to   the ending index (exclusive) of the sublist
     * @return the elements
     */
    public AtomsBuilder<T> subList(int from, int to) {
        AtomsBuilder<T> result = new AtomsBuilder<>();
        for (int k = from; k < to; k++)
            result.add(builder[k]);

        return result;
    }

    // endregion

    // region print

    /**
     * Prints each element of the list on a new line.
     */
    public void println() { // not returning the list to improve readability when using this method
        for (T atom : builder)
            System.out.println(atom);
    }

    /**
     * Prints each element of the list on a new line by replacing the empty values with the specified string.
     *
     * @param replacement The replacement
     */
    public void println(String replacement) {
        for (T atom : builder)
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
     * @return the modified list
     */
    public AtomsBuilder<T> add(T e) {
        builder = Arrays.copyOf(builder, builder.length + 1);
        builder[builder.length - 1] = e;

        return this;
    }

    /**
     * Inserts the specified element at the specified index in the list.
     *
     * @param index the index
     * @param e     the element
     * @return the modified list
     */
    public AtomsBuilder<T> add(int index, T e) {
        builder = Arrays.copyOf(builder, builder.length + 1);
        System.arraycopy(builder, index, builder, index + 1, builder.length - index - 1);
        set(index, e);

        return this;
    }

    /**
     * Adds the elements of the specified {@link Arrays Array} to the list.
     *
     * @param e the {@link Arrays array} of elements
     * @return the modified list
     */
    public AtomsBuilder<T> addAll(T[] e) {
        Arrays.stream(e).forEach(this::add);

        return this;
    }

    /**
     * Adds the elements of the specified {@link Collection} to the list.
     *
     * @param e the {@link Collection Collection}
     * @return the modified list
     */
    public AtomsBuilder<T> addAll(@NotNull Collection<T> e) {
        e.forEach(this::add);

        return this;
    }

    /**
     * Inserts the elements of the specified {@link Arrays Array} to the list starting from the specified index.
     *
     * @param index   the index
     * @param element the element
     * @return the modified list
     */
    public AtomsBuilder<T> addAll(int index, T[] element) {
        builder = Arrays.copyOf(builder, builder.length + element.length);
        System.arraycopy(builder, index, builder, index + element.length, builder.length - index - element.length);

        for (int j = 0; j < element.length; j++)
            set(index + j, element[j]);

        return this;
    }

    /**
     * Inserts the elements of the specified {@link Collection} to the list starting from the specified index.
     *
     * @param index      the index
     * @param collection the collection to add
     * @return the modified list
     */
    @SuppressWarnings("unchecked")
    public AtomsBuilder<T> addAll(int index, Collection<T> collection) {
        addAll(index, (T[]) collection.toArray());

        return this;
    }

    // endregion

    // region set

    /**
     * Sets the element at the specified index to the specified element.
     *
     * @param index   the index
     * @param element the element
     * @return the modified list
     */
    public AtomsBuilder<T> set(int index, T element) {
        builder[index] = element;

        return this;
    }

    /**
     * Sets the first occurrence of the specified element to the specified replacement element.
     *
     * @param element the element to replace
     * @param replace the replacement
     * @return the modified list
     */
    public AtomsBuilder<T> set(T element, T replace) {
        if (contains(element))
            builder[indexOf(element)] = replace;

        return this;
    }

    /**
     * Sets the last occurrence of the specified element to the specified replacement.
     *
     * @param element the element to replace
     * @param replace the replacement
     * @return the modified list
     */
    public AtomsBuilder<T> setLast(T element, T replace) {
        if (contains(element))
            builder[lastIndexOf(element)] = replace;

        return this;
    }

    // endregion

    // region remove

    /**
     * Removes the element at the specified index from the list.
     *
     * @param i the index of the element to remove from the list
     * @return the modified list
     */
    public AtomsBuilder<T> remove(int i) {
        if (i != size() - 1)
            System.arraycopy(builder, i + 1, builder, i, size() - i - 1);

        removeLast();

        return this;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param e the element
     * @return the modified list
     */
    public AtomsBuilder<T> remove(T e) {
        if (contains(e))
            if (indexOf(e) != size() - 1)
                System.arraycopy(builder, indexOf(e) + 1, builder, indexOf(e), size() - indexOf(e) - 1);

        removeLast();

        return this;
    }

    /**
     * Removes the last element of the list.
     *
     * @return the modified list
     */
    public AtomsBuilder<T> removeLast() {
        if (builder.length > 0) builder = Arrays.copyOf(builder, builder.length - 1);

        return this;
    }

    /**
     * Removes the last occurrence of the specified element in the list.
     *
     * @return the modified list
     */
    public AtomsBuilder<T> removeLast(T e) {
        return remove(lastIndexOf(e));
    }

    /**
     * Removes all occurrences of the specified element from the list.
     *
     * @param e The element
     * @return the modified list
     */
    public AtomsBuilder<T> removeAll(T e) {
        while (contains(e))
            remove(e);

        return this;
    }

    // endregion
}