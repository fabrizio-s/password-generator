package xylo.unit07.generator;

import org.apache.commons.math3.random.MersenneTwister;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Shuffler {

    private int numOfShuffles;
    private MersenneTwister prng;

    public Shuffler(MersenneTwister prng) {
        this(prng, 4);
    }

    public Shuffler(MersenneTwister prng, int numOfShuffles) {
        this.prng = prng;
        this.numOfShuffles = numOfShuffles;
    }

    //
    // Durstenfeld's algorithm:
    // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
    private void shuffle(char[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = prng.nextInt(i + 1);
            char temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public String shuffle(String string, int num) {
        char[] array = string.toCharArray();
        for (int i = 1; i <= num; i++)
            shuffle(array);
        return charArrayToString(array);
    }

    public String shuffle(String string) {
        int rand = prng.nextInt(numOfShuffles + 1);
        if (rand<2)
            return string;
        return shuffle(string, rand);
    }

    private String charArrayToString(char[] array) {
        StringBuilder sb = new StringBuilder();
        for (char ch : array)
            sb.append(ch);
        return sb.toString();
    }

}
