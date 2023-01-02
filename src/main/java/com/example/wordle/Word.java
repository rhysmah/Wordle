package com.example.wordle;

import java.util.Arrays;

public class Word {

    public static final int    LETTERS_PER_WORD          = 5;
    public static final String CONTAINS_VALID_CHARACTERS = "^[a-zA-Z]*$";

    /*
     * Checks that a word contains specified letters per word.
     */
    private static boolean validLength(final String word) {
        return word.length() == LETTERS_PER_WORD;
    }

    /*
     * Checks that a word contains valid characters.
     */
    private static boolean validCharacters(final String word) {
        return word.matches(CONTAINS_VALID_CHARACTERS);
    }

    /*
     * Checks if the userWord is in the word list.
     */
    private static boolean validWord(final String word) {
        return Arrays.asList(WordList.WORDS).contains(word);
    }

    /*
     * Checks that word is a valid five-letter English word.
     */
    public static boolean validateUserGuess(final String word) {
        return validLength(word) && validCharacters(word) && validWord(word);
    }
}
