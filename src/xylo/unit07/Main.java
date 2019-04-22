package xylo.unit07;

import xylo.unit07.generator.PasswordGenerator;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Main {

    public static final int NUMBER_OF_PASSWORDS = 300;

    public static void main(String[] args) {
        PasswordGenerator pg = new PasswordGenerator.Builder().characters("_!@#$%^&()-=+?':;\"{}[].,/\\~`").length(64).build();
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
