package me.atomiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Main {

    public static void main(String[] args) {

        List<Atom> toAdd = new ArrayList<>();
        toAdd.add(new Atom("added1", 4, AtomicState.LIQUID, 4, 53, 63));
        toAdd.add(new Atom("added2", 8, AtomicState.GAS, 0.3, 2, 33));
        toAdd.add(new Atom("added3", 1254, AtomicState.PLASMA, -14, 543, 2));

        System.out.println("\n[ = = = Atoms Demo = = = ]");
        AtomsDemo(toAdd);

        System.out.println("\n[ = = = AtomsBuilder Demo = = = ]");
        AtomsBuilderDemo(toAdd);
    }

    private static void AtomsDemo(Collection<Atom> toAdd) {
        Atoms<Atom> atoms = new Atoms<>();

        System.out.println("\n> Array constructor");
        new Atoms<>(new Atom[]{new Atom("d")}).println();

        System.out.println("\n> Collection constructor");
        new Atoms<>(toAdd).println();

        System.out.println("\n> 1 isEmpty = " +
                atoms.isEmpty());

        System.out.println("\n> Adding the values");
        atoms.add(null);
        atoms.add(new Atom());
        atoms.add(new Atom("x"));
        atoms.add(new Atom("y"));
        atoms.add(new Atom("x"));
        atoms.add(new Atom("y"));
        atoms.add(new Atom("x"));
        atoms.add(new Atom("y"));
        atoms.add(new Atom("x"));
        atoms.add(new Atom("y"));
        atoms.println();

        System.out.println("\n> Printing by replacing the empty values with NULL");
        atoms.println("NULL");

        System.out.println("\n> 2 isEmpty : " +
                atoms.isEmpty());

        System.out.println("\n> Retrieving the 2nd element : " +
                atoms.get(1));

        System.out.println("\n> Replacing the last x with y : " +
                atoms.setLast(new Atom("x"), new Atom("y")));
        atoms.println();

        System.out.println("\n> Removing x");
        atoms.remove(new Atom("x"));
        atoms.println();

        System.out.println("\n> Removing last x");
        atoms.removeLast(new Atom("x"));
        atoms.println();

        System.out.println("\n> Removing last element");
        atoms.removeLast();
        atoms.println();

        System.out.println("\n> Contains x : " + atoms.contains(new Atom("x")));

        System.out.println("\n> 1 Count of x : " + atoms.count(new Atom("x")));

        System.out.println("\n> Adding new x");
        atoms.add(new Atom("x"));
        atoms.println();

        System.out.println("\n> 2 Count of x : " +
                atoms.count(new Atom("x")));

        System.out.println("\n> Removing all x : " +
                atoms.removeAll(new Atom("x")));
        atoms.println();

        System.out.println("\n> Clearing and adding a new x");
        atoms.clear();
        atoms.add(new Atom("x", 5, AtomicState.SOLID, 50, 23, 536));
        atoms.println();

        System.out.println("\n> Bulk adding an array");
        atoms.addAll(new Atom[]{new Atom("x1"), new Atom("x2"), new Atom("x3")});
        atoms.println();

        System.out.println("\n> Bulk adding a collection");
        atoms.addAll(toAdd);
        atoms.println();

        System.out.println("\n> Setting added1 to an empty added 1 = " +
                atoms.set(new Atom("added1", 4, AtomicState.LIQUID, 4, 53, 63), new Atom("added 1")));
        atoms.println();

        System.out.println("\n> Setting added2 to an empty added 2 = " +
                atoms.set(atoms.indexOf(new Atom("added2", 8, AtomicState.GAS, 0.3, 2, 33)), new Atom("added 2")));
        atoms.println();

        System.out.println("\n> Inserting ZERO at index 0");
        atoms.add(0, new Atom("ZERO"));
        atoms.println();

        System.out.println("\n> Inserting all toAdd elements at index 0");
        atoms.addAll(0, toAdd);
        atoms.println();

        System.out.println("\n> Removing the element at index 5 : " +
                atoms.remove(5));
        atoms.println();

        System.out.println("\n> The elements between the indexes 1-6:");
        atoms.subList(1, 6).println();

        System.out.println("\n> Replacing all names with edited");
        atoms.forEach(atom -> atom.name = "edited");
        atoms.println();

        System.out.println("\n> Printing the 6 elements after index 1");
        atoms.getRange(1, 6).println();
    }

    private static void AtomsBuilderDemo(Collection<Atom> toAdd) {
        AtomsBuilder<Atom> atomsBuilder = new AtomsBuilder<>();

        System.out.println("\n> Array constructor");
        new AtomsBuilder<>(new Atom[]{new Atom("d")}).println();

        System.out.println("\n> Collection constructor");
        new AtomsBuilder<>(toAdd).println();

        System.out.println("\n> 1 isEmpty = " + atomsBuilder.isEmpty());

        System.out.println("\n> Adding the values and replacing all nulls with NULL");
        atomsBuilder
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
                .println("NULL");

        System.out.println("> 2 isEmpty : " + atomsBuilder.isEmpty());

        System.out.println("> Retrieving the 2nd element : " + atomsBuilder.get(1));

        System.out.println("> Replacing the last x with y");
        System.out.println("  L Removing x");
        System.out.println("  L Removing last x");
        System.out.println("  L Removing last element");
        atomsBuilder
                .setLast(new Atom("x"), new Atom("y"))
                .remove(new Atom("x"))
                .removeLast(new Atom("x"))
                .removeLast()
                .println();

        System.out.println("\n> Contains x : " + atomsBuilder.contains(new Atom("x")));

        System.out.println("\n> 1 Count of x : " + atomsBuilder.count(new Atom("x")));

        System.out.println("\n> Adding new x");
        atomsBuilder.add(new Atom("x"));
        atomsBuilder.println();

        System.out.println("\n> 2 Count of x : " + atomsBuilder.count(new Atom("x")));

        System.out.println("\n> Removing all x : " + atomsBuilder.removeAll(new Atom("x")));
        atomsBuilder.println();

        System.out.println("\n> Clearing and adding a new x");
        System.out.println("\n  L Bulk adding an array");
        System.out.println("\n  L Bulk adding a collection");
        System.out.println("\n  L Setting added1 to an empty added 1");
        System.out.println("\n  L Setting added2 to an empty added 2");
        System.out.println("\n  L Inserting ZERO at index 0");
        System.out.println("\n  L Inserting all toAdd elements at index 0");
        System.out.println("\n  L Removing the element at index 5");
        atomsBuilder
                .clear()
                .add(new Atom("x", 5, AtomicState.SOLID, 50, 23, 536))
                .addAll(new Atom[]{new Atom("x1"), new Atom("x2"), new Atom("x3")})
                .addAll(toAdd)
                .set(new Atom("added1", 4, AtomicState.LIQUID, 4, 53, 63), new Atom("added 1"))
                .set(atomsBuilder.indexOf(new Atom("added2", 8, AtomicState.GAS, 0.3, 2, 33)), new Atom("added 2"))
                .add(0, new Atom("ZERO"))
                .addAll(0, toAdd)
                .remove(5)
                .println();

        System.out.println("\n> The elements between the indexes 1-6:");
        atomsBuilder.subList(1, 6).println();

        System.out.println("\n> Replacing all names with edited");
        atomsBuilder.forEach(atom -> atom.name = "edited");
        atomsBuilder.println();

        System.out.println("\n> Printing the 6 elements after index 1");
        atomsBuilder.getRange(1, 6).println();
    }
}