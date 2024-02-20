package com.msc.ms.authentification.password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PasswordGenerator {
    private static final int LIMIT_PERCENT = 100;
    @Value("#{'${msc.password.allowed.simbols}'.split(':')}")
    private String[] symbols;
    @Value("#{'${msc.password.allowed.numbers}'.split(',')}")
    private String[] numbers;
    @Value("#{'${msc.password.allowed.characters.lowwers}'.split(',')}")
    private String[] lowers;
    @Value("#{'${msc.password.allowed.characters.upppers}'.split(',')}")
    private String[] uppers;

    private final Random random;

    public String getRandomPassword(int percentSymbols, int percentNumber, int percentUppers, int percentLowers, int size) {
        final var characterList = new ArrayList<String>();
        if (size < 1) {
            throw new IllegalArgumentException("The password length must to be more than 0 ");
        }
        if (percentSymbols < 0 || percentNumber < 0 || percentUppers < 0 || percentLowers < 0) {
            throw new IllegalArgumentException("Any of the percents given must to be less than 0 ");
        }
        final var totalPercent = percentSymbols + percentNumber + percentLowers + percentUppers;
        if (totalPercent > LIMIT_PERCENT) {
            throw new IllegalArgumentException("the sum of the percents given is over the allowed total percent: " + totalPercent);
        }
        return getCharacter(percentSymbols, percentNumber, percentUppers, percentLowers, size);
    }

    private String getCharacter(int percentSymbols, int percentNumber, int percentUppers, int percentLowers, int size) {
        var character = new ArrayList<>();
        var passwordStr = new StringBuilder();
        final var amountSymbols = percentSymbols > 0 ? Math.round((float) (size * percentSymbols) / LIMIT_PERCENT) : 0;
        final var amountNumber = percentNumber > 0 ? Math.round((float) (size * percentNumber) / LIMIT_PERCENT) : 0;
        final var amountUppers = percentUppers > 0 ? Math.round((float) (size * percentUppers) / LIMIT_PERCENT) : 0;
        final var amountLower = percentLowers > 0 ? Math.round((float) (size * percentLowers) / LIMIT_PERCENT) : 0;
        character.addAll(getRandomCharacters(List.of(this.symbols), amountSymbols));
        character.addAll(getRandomCharacters(List.of(this.numbers), amountNumber));
        character.addAll(getRandomCharacters(List.of(this.uppers), amountUppers));
        character.addAll(getRandomCharacters(List.of(this.lowers), amountLower));
        if (character.size() < size) {
            character.addAll(completePassword(percentSymbols, percentNumber, percentUppers, percentLowers, (size - character.size())));
        }
        Collections.shuffle(character);
        character.forEach(passwordStr::append);

        return passwordStr.toString().trim();
    }

    private List<String> completePassword(int percentSymbols, int percentNumber, int percentUppers, int percentLowers, int pending) {
        var map = new HashMap<String, Integer>();
        var complementString = new ArrayList<String>();
        map.put("Symbols", percentSymbols);
        map.put("Numbers", percentNumber);
        map.put("Uppers", percentUppers);
        map.put("Lowers", percentLowers);
        Map<String, Integer> sorted = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });
        sorted.putAll(map);
        String key = "";
        for (var entry : sorted.entrySet()) {
            key = entry.getKey();
            break;
        }
        switch (key) {
            case "Symbols":
                complementString = (ArrayList<String>) getRandomCharacters(List.of(this.symbols), pending);
                break;
            case "Numbers":
                complementString = (ArrayList<String>) getRandomCharacters(List.of(this.numbers), pending);
                break;
            case "Uppers":
                complementString = (ArrayList<String>) getRandomCharacters(List.of(this.uppers), pending);
                break;
            case "Lowers":
                complementString = (ArrayList<String>) getRandomCharacters(List.of(this.lowers), pending);
                break;

        }
        return complementString;

    }

    private List<String> getRandomCharacters(List<String> origin, int size) {
        final var sequence = new ArrayList<String>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                sequence.add(origin.get(this.random.nextInt(origin.size())));
            }
        }
        return sequence;
    }


}
