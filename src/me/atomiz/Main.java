package me.atomiz;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * An AtomicState object
 */
enum AtomicState {
    LIQUID, SOLID, GAS, PLASMA, UNKNOWN
}

public class Main {

    public static void main(String[] args) {
        Atoms<Atom> atoms = new Atoms<>();
        List<Atom> toAdd = new ArrayList<>();
        toAdd.add(new Atom("added1", 4, AtomicState.LIQUID, 4, 53, 63));
        toAdd.add(new Atom("added2", 8, AtomicState.GAS, 0.3, 2, 33));
        toAdd.add(new Atom("added3", 1254, AtomicState.PLASMA, -14, 543, 2));

        new Atoms<>(new Atom[]{new Atom("d")}).print("\n- Constructor for arrays").println();
        new Atoms<>(toAdd).print("\n- Constructor for collections").println();

        atoms
                .print("\n- Adding values")
                .add(null)
                .add(new Atom())
                .add(new Atom("x"))
                .add(new Atom("y"))
                .add(new Atom("x"))
                .add(new Atom("y"))
                .add(new Atom("x"))
                .add(new Atom("y"))
                .add(new Atom("x"))
                .add(new Atom("y"))
                .println().print("\n- Replacing empty values with NULL")
                .println("NULL").print("\n- Removing x")
                .remove(new Atom("x"))
                .println().print("\n- Removing last x")
                .removeLast(new Atom("x"))
                .println().print("\n- Removing last")
                .removeLast()
                .println()
                .print("\n- Contains x? = " + atoms.contains(new Atom("x")))
                .print("\n- Count of x = " + atoms.count(new Atom("x")))
                .print("\n- Adding new x")
                .add(new Atom("x"))
                .println()
                .print("\n- Count of x = " + atoms.count(new Atom("x")))
                .print("\n- Removing all x")
                .removeAll(new Atom("x"))
                .print("\n- Removing all elements equal to the one at index 0")
                .removeAll(0)
                .print("\n- Clearing and adding a new x")
                .clear()
                .add(new Atom("x", 5, AtomicState.SOLID, 50, 23, 536))
                .println().print("\n- Bulk adding x1 x2 x3")
                .addAll(new Atom[]{new Atom("x1"), new Atom("x2"), new Atom("x3")})
                .println().print("\n- Bulk adding added1, 2, 3")
                .addAll(toAdd)
                .println().print("\n- Setting added1 to an empty added 1")
                .set(new Atom("added1", 4, AtomicState.LIQUID, 4, 53, 63), new Atom("added 1"))
                .println().print("\n- Setting added2 to an empty added 2")
                .set(atoms.indexOf(new Atom("added2", 8, AtomicState.GAS, 0.3, 2, 33)), new Atom("added 2"))
                .println().print("\n- Inserting ZERO at index 0")
                .insert(0, new Atom("added2"))
                .println().print("\n- Removing the element at index 5")
                .remove(5)
                .println().print("\n- Renaming all elements")
                .forEach(atom -> atom.name = "edited*")
                .println().print("\nThe End");
    }
}

/**
 * An Atom object
 */
class Atom {

    String name;
    int number;
    AtomicState state;
    double mass;
    double density;
    double meltingPoint;

    /**
     * Creates an empty instance of an {@link Atom} with a default name of null and values defaulting to 0 and an {@link AtomicState#UNKNOWN
     * AtomicState.UNKNOWN}
     */
    public Atom() {
        this.name = "atom";
        this.number = 0;
        this.state = AtomicState.UNKNOWN;
        this.mass = 0;
        this.density = 0;
        this.meltingPoint = 0;
    }

    /**
     * Creates an empty instance of an Atom with the specified name
     *
     * @param name The name
     */
    public Atom(String name) {
        this.name = name;
        this.number = 0;
        this.state = AtomicState.UNKNOWN;
        this.mass = 0;
        this.density = 0;
        this.meltingPoint = 0;
    }

    /**
     * Create a new instance of an {@link Atom}
     *
     * @param name         The name
     * @param number       The atomic number
     * @param state        The {@link AtomicState}
     * @param mass         The mass
     * @param density      The density
     * @param meltingPoint The melting point
     */
    public Atom(String name, int number, AtomicState state, double mass, double density, double meltingPoint) {
        this.name = name;
        this.number = number;
        this.state = state;
        this.mass = mass;
        this.density = density;
        this.meltingPoint = meltingPoint;
    }

    @Override
    public String toString() {
//        if (this == null) return null;
        return name + " - "
                + number + ", "
                + state.toString().toLowerCase() + ", "
                + mass + ", "
                + density + ", "
                + meltingPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atom atom = (Atom) o;
        return number == atom.number
                && Double.compare(atom.mass, mass) == 0
                && Double.compare(atom.density, density) == 0
                && Double.compare(atom.meltingPoint, meltingPoint) == 0
                && Objects.equals(name, atom.name)
                && state == atom.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, state, mass, density, meltingPoint);
    }
}

/**
 * A custom list of a given type
 *
 * @param <Element> The type
 */
class Atoms<Element> {
    Object[] atoms;

    /**
     * Creates an empty list with initial size of 0
     */
    public Atoms() {
        atoms = new Object[0];
    }

    public Atoms(Collection<Element> collection) {
        atoms = collection.toArray();
    }

    public Atoms(Element[] array) {
        atoms = array;
    }

    /**
     * @param e The element to count
     * @return The amount of occurrences of the specified element in the list
     */
    public int count(Element e) {
        int size = 0;
        for (Object el : atoms)
            if (el != null && el.equals(e))
                size++;

        return size;
    }

    /**
     * @param e The element to check the list for
     * @return true if the list contains the specified element
     */
    public boolean contains(Element e) {
        for (Object a : atoms)
            if (a != null)
                if (a.equals(e))
                    return true;

        return false;
    }

    /**
     * @param e The element to get the index of in the list
     * @return The index of the specified element in the list
     */
    public int indexOf(Element e) {
        for (int i = 0; i < atoms.length; i++)
            if (atoms[i] != null)
                if (atoms[i].equals(e))
                    return i;

//        throw new NoSuchElementException(e.toString());
        return -1;
    }

    /**
     * @param e The element to get the last index of in the list
     * @return The last index of the specified element in the list
     */
    public int lastIndexOf(Element e) {
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
     * @param i The index of the element to get from the list
     * @return The element located at the specified index in the list
     */
    @SuppressWarnings("unchecked")
    public Element get(int i) {
        return (Element) atoms[i];
    }

    /**
     * @param e The element to add to the list
     * @return The modified list
     */
    public Atoms<Element> add(Element e) {
        atoms = Arrays.copyOf(atoms, atoms.length + 1);
        atoms[atoms.length - 1] = e;

        return this;
    }

    /**
     * @param e The array of elements to add to the list
     * @return The modified list
     */
    public Atoms<Element> addAll(Element[] e) {
        Arrays.stream(e).forEach(this::add);

        return this;
    }

    /**
     * @param e The {@link Collection Collection} of elements to add to the list
     * @return The modified list
     */
    public Atoms<Element> addAll(@NotNull Collection<Element> e) {
        e.forEach(this::add);

        return this;
    }

    /**
     * @param i The index to insert the specified element at
     * @param e The element to insert at the specified index
     * @return The modified list
     */
    public Atoms<Element> insert(int i, Element e) {
        if (i > atoms.length - 1) throw new ArrayIndexOutOfBoundsException();

        atoms = Arrays.copyOf(atoms, atoms.length + 1);
        System.arraycopy(atoms, i, atoms, i + 1, atoms.length - i - 1);
        set(i, e);

        return this;
    }

    /**
     * @param i The index of the element to replace
     * @param e The element to replace with
     * @return The modified list
     */
    public Atoms<Element> set(int i, Element e) {
        atoms[i] = e;

        return this;
    }

    /**
     * @param e The element to replace in the list
     * @param r The element to replace with in the list
     * @return The modified list
     */
    public Atoms<Element> set(Element e, Element r) {
        atoms[indexOf(e)] = r;
        return this;
    }

    /**
     * Removes the first occurrence of the element at the specified index in the list
     *
     * @param i The index of the element to remove from the list
     * @return The modified list
     */
    public Atoms<Element> remove(int i) {
        remove(get(i));

        return this;
    }

    /**
     * Removes the first occurrence of the specified element in the list
     *
     * @param e The element
     * @return The modified list
     */
    public Atoms<Element> remove(Element e) {
        if (!contains(e)) return this;

        int index = indexOf(e);
        Object[] copy = new Object[atoms.length - 1];

        for (int i = 0, j = 0; i < atoms.length; i++) {
            if (i == index) continue;
            copy[j++] = atoms[i];
        }
        atoms = copy;

        return this;
    }

    /**
     * Removes all occurrences of the element at the specified index in the list
     *
     * @param i The index of the element
     * @return The modified list
     */
    public Atoms<Element> removeAll(int i) {
        removeAll(get(i));

        return this;
    }

    /**
     * Removes all occurrences of the specified element in the list
     *
     * @param e The element
     * @return The modified list
     */
    public Atoms<Element> removeAll(Element e) {
        while (contains(e))
            remove(e);

        return this;
    }

    /**
     * Removes the last element of the list
     *
     * @return The modified list
     */
    public Atoms<Element> removeLast() {
        atoms = Arrays.copyOf(atoms, atoms.length - 1);

        return this;
    }

    /**
     * Removes the last occurrence of the specified element in the list
     *
     * @param e The element
     * @return The modified list
     */
    public Atoms<Element> removeLast(Element e) {
        if (!contains(e)) return this;

        int index = lastIndexOf(e);
        Object[] copy = new Object[atoms.length - 1];

        for (int i = atoms.length - 1, j = i; i >= 0; i--) {
            if (i == index) continue;
            copy[--j] = atoms[i];
        }
        atoms = copy;

        return this;
    }

    public Atoms<Element> clear() {
        atoms = new Object[0];
        return this;
    }

    @SuppressWarnings("unchecked")
    public Atoms<Element> forEach(Consumer<? super Element> action) {
        Objects.requireNonNull(action);
        for (Object atom : atoms)
            action.accept((Element) atom);

        return this;
    }

    /**
     * Prints each element of the list on a new line by replacing the empty values with the specified string
     *
     * @param empty The replacement
     * @return The list
     * @see Atoms#println()
     */
    public Atoms<Element> println(String empty) {
        for (Object atom : atoms)
            if (atom == null || atom.toString().isEmpty())
                System.out.println(empty);
            else
                System.out.println(atom);
        return this;
    }

    /**
     * Prints each element of the list on a new line
     *
     * @return The list
     * @see Atoms#println(String)
     */
    public Atoms<Element> println() {
        for (Object atom : atoms)
            System.out.println(atom);

        return this;
    }

    /**
     * Prints the specified value to the console but still returning the list (mainly used for debugging purposes)
     *
     * @param value The value
     * @return The list
     * @see Atoms#println()
     * @see Atoms#println(String)
     */
    public Atoms<Element> print(String value) {
        System.out.println(value);

        return this;
    }
}