package xylo.unit07;

import xylo.unit07.generator.PasswordGenerator;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Main {

    public static final int NUMBER_OF_PASSWORDS = 300;

    public static void main(String[] args) {
	if (args.length != 2) {
		System.out.println("Invalid number of args.");
		System.exit(-1);
	}
	String characters = args[1];
	int length = 0;
	try {
		length = Integer.parseInt(args[0]);
	} catch (NumberFormatException ex) {
		System.out.println("Unable to parse argument '" + args[0] + "' to an integer.");
		System.exit(-1);
	}
        PasswordGenerator pg = new PasswordGenerator.Builder().characters(characters).length(length).build();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_PASSWORDS; i++)
            set.add(pg.generate());
        List<String> passwords = new ArrayList<>(set);
        Collections.shuffle(passwords);
        for (String password : passwords)
            System.out.println(password);
        System.out.println("######################################################################");
        System.out.println("##########  Number of unique passwords generated: " + passwords.size());
        System.out.println("######################################################################");
    }

}
