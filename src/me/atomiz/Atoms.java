package me.atomiz;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A custom list with specified elements of a given type.
 *
 * @param <T> the type
 */
class Atoms<T> {
	private final static int RESIZE_THRESHOLD = 10;
	private final int INITIAL_SIZE;
	private T[] atoms;
	private int size = 0;

	public Atoms() {
		INITIAL_SIZE = 0;
		atoms = (T[]) new Object[0];
	}

	public Atoms(int size) {
		INITIAL_SIZE = size;
		atoms = (T[]) new Object[INITIAL_SIZE];
	}

	public Atoms(T[] array) {
		INITIAL_SIZE = array.length;
		size = array.length;
		atoms = array;
	}

	public Atoms(Collection<T> list) {
		INITIAL_SIZE = list.size();
		size = list.size();
		atoms = (T[]) list.toArray();
	}

	public Atoms(Atoms<T> atoms) {
		INITIAL_SIZE = atoms.size;
		size = atoms.size;
		this.atoms = atoms.atoms;
	}

	// region base

	/**
	 * Returns the amount of occurrences of the specified element in the list.
	 *
	 * @param e the element to count
	 * @return the amount
	 */
	public int count(T e) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (Objects.equals(atoms[i], e))
				count++;
		}

		return count;
	}

	/**
	 * Returns the amount of elements meeting the filter.
	 *
	 * @param filter the filter to test for
	 * @return the amount
	 */
	public int countIf(Predicate<T> filter) {
		Objects.requireNonNull(filter);
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (filter.test(atoms[i]))
				count++;
		}

		return count;
	}

	/**
	 * Checks whether the list contains the specified element.
	 *
	 * @param e the element
	 * @return {@code true} if the list contains the specified element
	 */
	public boolean contains(T e) {
		for (int i = 0; i < size; i++)
			if (Objects.equals(atoms[i], e))
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
		for (int i = 0; i < size; i++)
			if (Objects.equals(atoms[i], e))
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
		for (int i = size - 1; i >= 0; i--)
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
		return size;
	}

	/**
	 * Returns {@code true} if the list has no elements.
	 *
	 * @return {@code true} if the list has no elements
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Clears the list.
	 */
	public void clear() {
		size = 0;
		atoms = (T[]) new Object[RESIZE_THRESHOLD];
	}

	/**
	 * Performs an action on each element.
	 *
	 * @param action the action
	 */
	public void forEach(Consumer<? super T> action) {
		Objects.requireNonNull(action);
		Arrays.stream(atoms, 0, size).forEach(action);
	}

	private void resize(int amount) {
		if (amount > 0) {
			if (size + amount > atoms.length)
				atoms = Arrays.copyOf(atoms, Math.max(atoms.length << 1, RESIZE_THRESHOLD));
		} else if (amount < 0) {
			if (atoms.length >> 1 >= size + amount)
				atoms = Arrays.copyOf(atoms, atoms.length << 1);
		}
	}

	// endregion

	// region add

	/**
	 * Adds the specified element to the list.
	 *
	 * @param e the element
	 */
	public void add(T e) {
		resize(1);
		atoms[size++] = e;
	}

	/**
	 * Inserts the specified element at the specified index in the list.
	 *
	 * @param index the index
	 * @param e     the element
	 */
	public void add(int index, T e) {
		resize(1);
		System.arraycopy(atoms, index, atoms, index + 1, atoms.length - index - 1);
		set(index, e);
		size++;
	}

	/**
	 * Adds the elements of the specified {@link Arrays Array} to the list.
	 *
	 * @param e the {@link Arrays array} of elements
	 */
	public void addAll(T[] e) {
		resize(e.length);
		Arrays.stream(e).forEach(a -> atoms[size++] = a);
	}

	/**
	 * Adds the elements of the specified {@link Collection} to the list.
	 *
	 * @param e the {@link Collection Collection}
	 */
	public void addAll(Collection<T> e) {
		e.forEach(this::add);
	}

	/**
	 * Adds the elements of the specified {@link Arrays Array} to the list.
	 *
	 * @param e the {@link Arrays array} of elements
	 */
	public void addAll(Atoms<T> e) {
		addAll(e.atoms);
	}

	/**
	 * Inserts the elements of the specified {@link Arrays Array} to the list starting from the specified index.
	 *
	 * @param index    the index
	 * @param elements the elements
	 */
	public void addAll(int index, T[] elements) {
		resize(elements.length);

		System.arraycopy(atoms, index, atoms, index + elements.length, atoms.length - index - elements.length);
		System.arraycopy(elements, 0, atoms, index, elements.length);

		size += elements.length;
	}

	/**
	 * Inserts the elements of the specified {@link Collection} to the list starting from the specified index.
	 *
	 * @param index      the index
	 * @param collection the collection to add
	 */
	public void addAll(int index, Collection<T> collection) {
		addAll(index, (T[]) collection.toArray());
	}

	/**
	 * Inserts the elements of the specified {@link Collection} to the list starting from the specified index.
	 *
	 * @param index the index
	 * @param atoms the collection to add
	 */
	public void addAll(int index, Atoms<T> atoms) {
		List<T> a = new ArrayList<>();
		atoms.forEach(a::add);
		addAll(index, a);
	}

	// endregion

	// region set/replace

	/**
	 * Sets the element at the specified index to the specified element.
	 *
	 * @param index   the index
	 * @param element the element
	 * @return the element previously at this index
	 */
	public T set(int index, T element) {
		if (index < 0)
			return null;

		T prev = atoms[index];
		atoms[index] = element;
		return prev;
	}

	/**
	 * Replaces all occurrences of this element with the specified replacement.
	 *
	 * @param element the element to replace
	 * @param replace the replacement
	 * @return the amount of replaced occurrences
	 */
	private int replace(T element, T replace, boolean first) {
		if (!contains(element))
			return -1;

		int index = first ? indexOf(element) : lastIndexOf(element);
		atoms[index] = replace;

		return index;
	}

	/**
	 * Replaces the first occurrence of this element with the specified replacement.
	 *
	 * @param element the element to replace
	 * @param replace the replacement
	 * @return the index of the replaced element
	 */
	public int replaceFirst(T element, T replace) {
		return replace(element, replace, true);
	}

	/**
	 * Replaces the last occurrence of this element with the specified replacement.
	 *
	 * @param element the element to replace
	 * @param replace the replacement
	 * @return the index of the replaced element
	 */
	public int replaceLast(T element, T replace) {
		return replace(element, replace, false);
	}

	/**
	 * Replaces all occurrences of this element with the specified replacement.
	 *
	 * @param element     the element to replace
	 * @param replacement the replacement
	 * @return the amount of replaced occurrences
	 */
	public int replaceAll(T element, T replacement) {
		if (!contains(element))
			return -1;

		int amount = 0;
		for (int i = 0; i < atoms.length; i++)
			if (Objects.equals(atoms[i], element)) {
				atoms[i] = replacement;
				amount++;
			}

		return amount;
	}

	// endregion

	// region remove

	private boolean remove(T e, boolean first) {
		int i = first ? indexOf(e) : lastIndexOf(e);

		if (i == -1)
			return false;

		atoms[i] = null;
		size--;
		System.arraycopy(atoms, i + 1, atoms, i, size - i);

		resize(-1);
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from the list.
	 *
	 * @param e the element
	 * @return {@code true} if the element was removed
	 */
	public boolean remove(T e) {
		return remove(e, true);
	}

	/**
	 * Removes the last occurrence of the specified element in the list.
	 */
	public boolean removeLast(T e) {
		return remove(e, false);
	}

	/**
	 * Removes the last element of the list.
	 */
	public void removeLast() {
		if (size == 0)
			return;
		atoms[--size] = null;
		resize(-1);
	}

	/**
	 * Removes all occurrences of the specified element from the list.
	 *
	 * @param e the element
	 * @return the amount of removed elements
	 */
	public int removeAll(T e) {
		if (!contains(e))
			return 0;

		int amount = 0;
		for (; contains(e); amount++)
			remove(e);

		return amount;
	}

	/**
	 * Removes all elements matching the specified condition.
	 *
	 * @param filter the condition
	 * @return the amount of removed elements
	 */
	public int removeIf(Predicate<T> filter) {
		Objects.requireNonNull(filter);
		int i = 0;
		for (; i < size; i++)
			if (filter.test(atoms[i]))
				remove(atoms[i--]);

		return i;
	}

	// endregion

	/**
	 * Retrieves the specified amount of elements starting from the specified index.
	 *
	 * @param start the start index (inclusive)
	 * @param count the end index (exclusive)
	 * @return the elements
	 */
	public Atoms<T> getRange(int start, int count) {
		Atoms<T> atoms = new Atoms<>();

		for (int i = start; i < start + count; i++)
			atoms.add(this.atoms[i]);

		return atoms;
	}

	/**
	 * Returns a list of the elements in the current list starting from the specified index and ending at the specified second index param.
	 *
	 * @param from the starting index (inclusive) of the sublist
	 * @param to   the ending index (exclusive) of the sublist
	 * @return the elements
	 */
	public Atoms<T> subList(int from, int to) {
		Atoms<T> atoms = new Atoms<>();

		for (int i = from; i < to; i++)
			atoms.add(this.atoms[i]);

		return atoms;
	}

	@Override
	public String toString() {
		return String.join("\n", Arrays.toString(atoms));
	}
}