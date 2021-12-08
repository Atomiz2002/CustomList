package me.atomiz;

import java.util.Objects;

class Atom {

    String name;
    int number;
    AtomicState state;
    double mass;
    double density;
    double meltingPoint;

    /**
     * Creates an empty instance of an {@link Atom} with a default name of "atom" and values defaulting to 0 and an
     * {@link AtomicState#UNKNOWN}.
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
     * Creates an empty instance of an Atom with the specified name.
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
     * Create a new instance of an {@link Atom}.
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