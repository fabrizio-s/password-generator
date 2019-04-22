package xylo.unit07.generator;

import org.apache.commons.math3.random.MersenneTwister;

@SuppressWarnings({"WeakerAccess", "unused"})
public class PasswordGenerator {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";

    private String characters;
    private int length;
    private Shuffler shuffler;
    private MersenneTwister prng;

    public PasswordGenerator(Builder builder) {
        prng = new MersenneTwister(System.nanoTime());
        shuffler = new Shuffler(prng);
        characters = shuffler.shuffle(LETTERS + DIGITS + builder.characters);
        length = builder.length;
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            characters = shuffler.shuffle(characters);
            int rand = prng.nextInt(characters.length());
            char ch = characters.charAt(rand);
            if (Character.isLetter(ch) && prng.nextInt(2)==0) {
                if (Character.isLowerCase(ch))
                    ch = Character.toUpperCase(ch);
                else ch = Character.toLowerCase(ch);
            }
            sb.append(ch);
        }
        return shuffler.shuffle(sb.toString());
    }

    public static class Builder {

        private String characters = "";
        private int length = 10;

        public Builder characters(String characters) {
            this.characters = characters;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public PasswordGenerator build() {
            return new PasswordGenerator(this);
        }

    }

}
