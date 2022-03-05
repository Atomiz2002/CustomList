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

		_p(0, "[ = = = Atoms Demo = = = ]");
		AtomsDemo(toAdd);

		_p(37, "[ = = = END OF DEMO = = = ]");
	}

	private static void AtomsDemo(Collection<Atom> toAdd) {
		_p(1, "[Empty constructor]");
		new Atoms<>().forEach(Main::p);

		_p(2, "[Empty constructor with a initial capacity of 5]");
		new Atoms<>(5).forEach(Main::p);

		_p(3, "[Array constructor]");
		new Atoms<>(new Atom[]{
				new Atom("atom1"),
				new Atom("atom2") }
		).forEach(Main::p);

		_p(4, "[Collection constructor]");
		new Atoms<>(toAdd).forEach(Main::p);

		_p(5, "[Atoms constructor]");
		new Atoms<>(new Atoms<>(new Atom[]{
				new Atom("a"),
				new Atom("b") })).forEach(Main::p);

		_p(6, "Arbitrary number of arguments constructor");
		Atoms<Atom> atoms = new Atoms<>(new Atom[]{
				new Atom(),
				new Atom("x", 4, AtomicState.PLASMA, 4, 4, 3),
				new Atom("y", 35, AtomicState.GAS, 13, 5, 35),
				new Atom("x"),
				new Atom("y", 23, AtomicState.UNKNOWN, 34, 2, 51),
				new Atom("x"),
				new Atom("y"),
				new Atom("x"),
				new Atom("y"),
				null,
				null });
		print(atoms);

		_p(7, "Size : " + atoms.size());

		_p(8, "Removing atoms if their name is 'y' : " + atoms.removeIf(e -> e != null && e.name != null && e.name.equals("y")));
		print(atoms);

		_p(9, "Retrieving the 2nd element : " + atoms.get(1));

		_p(10, "Replacing the last empty XX with Y : " + atoms.replaceLast(new Atom("XX"), new Atom("Y")));
		print(atoms);

		_p(11, "Replacing the first empty x with XX : " + atoms.replaceFirst(new Atom("x"), new Atom("XX")));
		print(atoms);

		_p(12, "Replacing all empty 'x' atoms with 'XX' : " + atoms.replaceAll(new Atom("x"), new Atom("XX")));
		print(atoms);

		_p(13, "Removing the first empty XX : " + atoms.remove(new Atom("XX")));
		print(atoms);

		_p(14, "Removing the last empty YY : " + atoms.removeLast(new Atom("YY")));
		print(atoms);

		_p(15, "Removing the last element");
		atoms.removeLast();
		print(atoms);

		_p(16, "Contains an empty XX : " + atoms.contains(new Atom("XX")));

		_p(17, "Count of the empty XX : " + atoms.count(new Atom("XX")));

		_p(18, "Adding an empty X");
		atoms.add(new Atom("X"));
		print(atoms);

		_p(19, "Adding the list to itself at index 1");
		atoms.addAll(1, atoms);
		print(atoms);

		_p(20, "Count of null elements : " + atoms.count(null));

		_p(21, "Count of elements with the name 'XX' : " + atoms.countIf(a -> a != null && a.name != null && a.name.equals("XX")));

		_p(22, "Removing all null elements : " + atoms.removeAll(null));
		print(atoms);

		_p(23, "Removing atoms if their name starts with any case 'x' : " + atoms.removeIf(e -> e != null && e.name != null && e.name.toLowerCase().startsWith("x")));
		print(atoms);

		_p(24, "Adding all atoms to the list");
		atoms.addAll(atoms);
		print(atoms);

		_p(25, "Clearing");
		atoms.clear();

		_p(26, "isEmpty : " + atoms.isEmpty());
		atoms.forEach(Main::p);

		_p(27, "Adding a new X atom");
		atoms.add(new Atom("X", 5, AtomicState.SOLID, 50, 23, 536));
		print(atoms);

		_p(28, "isEmpty : " + atoms.isEmpty());
		atoms.forEach(Main::p);

		_p(29, "Bulk adding an array - x1, x2, x3");
		atoms.addAll(new Atom[]{
				new Atom("x1"),
				new Atom("x2"),
				new Atom("x3") });
		print(atoms);

		_p(30, "Bulk adding a collection - added1, added2, added3");
		atoms.addAll(toAdd);
		print(atoms);

		_p(31, "Setting the 3rd element to an empty X : " + atoms.set(3, new Atom("X")));
		print(atoms);

		_p(32, "Add an empty ZERO atom at index 0");
		atoms.add(0, new Atom("ZERO"));
		print(atoms);

		_p(33, "Bulk adding a collection at index 0");
		atoms.addAll(0, toAdd);
		print(atoms);

		_p(34, "The elements between the indexes 1-6 :");
		atoms.subList(1, 6).forEach(Main::p);

		_p(35, "Replacing all names with edited");
		atoms.forEach(atom -> {
			if (atom != null)
				atom.name = "edited";
		});
		print(atoms);

		_p(36, "Printing the 3 elements after index 3");
		atoms.getRange(3, 3).forEach(Main::p);
	}

	private static void p(Object o) {
		System.out.println(o);
	}

	private static void _p(int i, Object o) {
		p("");
		p("----------------------------------------------------------------------------");
		p("<" + i + "> " + o);
		p("----------------------------------------------------------------------------");
	}

	private static void print(Atoms<?> list) {
		list.forEach(Main::p);
	}
}