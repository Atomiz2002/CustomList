package me.atomiz;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Atoms<Atom> atoms = new Atoms<>();
        List<Atom> toAdd = new ArrayList<>();
        toAdd.add(new Atom("added1", 4, AtomicState.LIQUID, 4, 53, 63));
        toAdd.add(new Atom("added2", 8, AtomicState.GAS, 0.3, 2, 33));
        toAdd.add(new Atom("added3", 1254, AtomicState.PLASMA, -14, 543, 2));

        new Atoms<>(new Atom[]{new Atom("d")}).print("\n- Constructor for arrays").println();
        new Atoms<>(toAdd).print("\n- Constructor for collections").println();

        atoms.print("\n- Adding values")
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
                .insert(0, new Atom("ZERO"))
                .println().print("\n- Inserting all toAdd elements at index 0")
                .insertAll(0, toAdd)
                .println().print("\n- Removing the element at index 5")
                .remove(5)
                .println().print("\n- Replacing all names with edited")
                .forEach(atom -> atom.name = "edited")
                .println().print("\n- The elements between the indexes 1-6")
                .getRange(1, 6)
                .println();
    }
}