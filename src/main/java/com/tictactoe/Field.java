package com.tictactoe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Field {
    private final Map<Integer, Sign> field;
    // Создаем новый случайный объект
    Random rand = new Random();

    public Field() {
        field = new HashMap<>();
        field.put(0, Sign.EMPTY);
        field.put(1, Sign.EMPTY);
        field.put(2, Sign.EMPTY);
        field.put(3, Sign.EMPTY);
        field.put(4, Sign.EMPTY);
        field.put(5, Sign.EMPTY);
        field.put(6, Sign.EMPTY);
        field.put(7, Sign.EMPTY);
        field.put(8, Sign.EMPTY);
    }

    public Map<Integer, Sign> getField() {
        return field;
    }

    // ищет незанятую ячейку
    public int getEmptyFieldIndex() {

        // Генерируем случайное число между 0 и 8
        int random = rand.nextInt(8);

        long count = field.entrySet().stream()
                .filter(e -> e.getValue() == Sign.EMPTY)
                .map(Map.Entry::getKey).count();

        boolean isSign = true;

        if(count > 0 && field.get(4)==Sign.EMPTY){
            return 4;
        } else if (count > 0 && field.get(4)==Sign.CROSS && field.get(8)==Sign.CROSS && field.get(0)==Sign.EMPTY) {
            return 0;
        }  else if (count > 0 && field.get(4)==Sign.CROSS && field.get(0)==Sign.CROSS && field.get(8)==Sign.EMPTY) {
            return 8;
        }  else if (count > 0 && field.get(4)==Sign.CROSS && field.get(5)==Sign.CROSS && field.get(3)==Sign.EMPTY) {
            return 3;
        }  else if (count > 0 && field.get(4)==Sign.CROSS && field.get(3)==Sign.CROSS && field.get(5)==Sign.EMPTY) {
            return 5;
        }  else if (count > 0 && field.get(4)==Sign.CROSS && field.get(2)==Sign.CROSS && field.get(6)==Sign.EMPTY) {
            return 6;
        }  else if (count > 0 && field.get(4)==Sign.CROSS && field.get(6)==Sign.CROSS && field.get(2)==Sign.EMPTY) {
            return 2;
        }  else if (count > 0 && field.get(4)==Sign.CROSS && field.get(7)==Sign.CROSS && field.get(1)==Sign.EMPTY) {
            return 1;
        } else if (count > 0) {
            while (isSign) {
                Sign sign = field.get(random);
                if (sign == Sign.EMPTY) {
                    isSign=false;
                    return random;
                } else random = rand.nextInt(8);
            }
        }

//        return field.entrySet().stream()
//                .filter(e -> e.getValue() == Sign.EMPTY)
//                .map(Map.Entry::getKey)
//                .findFirst().orElse(-1);
    return -1;
    }

    // возвращает значения мапы “field” в виде списка, отсортированного в порядке возрастания индексов
    public List<Sign> getFieldData() {
        return field.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Sign checkWin() {
        List<List<Integer>> winPossibilities = List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8),
                List.of(0, 3, 6),
                List.of(1, 4, 7),
                List.of(2, 5, 8),
                List.of(0, 4, 8),
                List.of(2, 4, 6)
        );

        for (List<Integer> winPossibility : winPossibilities) {
            if (field.get(winPossibility.get(0)) == field.get(winPossibility.get(1))
                    && field.get(winPossibility.get(0)) == field.get(winPossibility.get(2))) {
                return field.get(winPossibility.get(0));
            }
        }
        return Sign.EMPTY;
    }
}