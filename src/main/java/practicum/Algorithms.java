package practicum;

import java.util.*;

public class Algorithms {

    /**
     * Отсортируйте список, НЕ используя методы стандартной библиотеки (напр. Collections.sort).
     */
    public static List<Integer> sort(List<Integer> list) {
        List<Integer>  newList = new ArrayList<>(list);
        int listSize = newList.size();
        for (int i = 1; i < listSize; i++) {
            Integer key = newList.get(i);
            int j = i - 1;
            while (j >= 0 && newList.get(j) > key) {
                newList.add(j + 1, newList.get(j));
                newList.remove(j+2);
                j -= 1;
            }
            newList.add(j + 1, key);
            newList.remove(j+2);
        }
        return newList;
    }

    /**
     * Удалите дубликаты из списка.
     *
     * Усложнение: не используйте дополнительные структуры данных
     *  для хранения промежуточных значений.
     *  (списки, массивы, хэш-таблицы, множества и т.п.).
     * К списку-результату это не относится.
     */
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer>  newList = new ArrayList<>(list);

        for (int i = 0; i < newList.size(); i++) {
            Integer key = newList.get(i);
            for (int j = 0; j < newList.size(); j++) {
                if (key == newList.get(j) && i != j) {
                    newList.remove(j);
                }
            }
        }
        return newList;
    }

    /**
     * Проверьте, является ли список "палиндромом".
     * Палиндром -- это список, который в обе стороны читается одинаково.
     * Например:
     *  палиндромы: [1 2 1], [3 2 1 2 3], [2 2 2], []
     *  не палиндромы: [1 2 3], [2 2 3], [3 2 1 3 2]
     *
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n)
     */
    public static boolean isPalindrome(List<Integer> list) {
        if (list.isEmpty()) return true;
        boolean isPalindrome = false;
        int length = list.size();
        int lastIndex  = length - 1;
        for (int i = 0; i < length / 2; i ++) {
            if (list.get(i) == list.get(lastIndex - i)) {
                isPalindrome = true;
            } else {
                return false;
            }
        }

        return isPalindrome;
    }

    /**
     * Объедините два отсортированных списка в один отсортированный список.
     * Например:
     * [1 3 5] + [2 4 6] = [1 2 3 4 5 6]
     * [1 2 3] + [1 3 5] = [1 1 2 3 3 5]
     * [] + [1] = [1]
     * [7] + [1 4] = [1 4 7]
     *
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n).
     */
    public static List<Integer> mergeSortedLists(List<Integer> a, List<Integer> b) {
        int sizeA = a.size();
        int sizeB = b.size();
        List<Integer> result = new ArrayList<>();

        // Индексы, по которым идёт итерация
        int indexA = 0;
        int indexB = 0;
        int index = 0;


        while (index < sizeA + sizeB) {
            if (indexA < sizeA && indexB < sizeB) {
                if (a.get(indexA) < b.get(indexB)) {
                    result.add(a.get(indexA));
                    indexA++;
                } else {
                    result.add(b.get(indexB));
                    indexB++;
                }
            } else {
                if (indexB < sizeB) {
                    result.add(b.get(indexB));
                    indexB++;
                }
                if (indexA < sizeA) {
                    result.add(a.get(indexA));
                    indexA++;
                }
            }
            index++;
        }

        return result;

    }

    /**
     * Проверьте, что в массиве нет дубликатов.
     * Верните true, если дубликатов нет, иначе false.
     *
     * Усложнение: не используйте дополнительные структуры данных
     *  (списки, массивы, хэш-таблицы, множества и т.п.).
     */
    public static boolean containsEveryElementOnce(int[] array) {
        if (array.length == 0) return true;
        int j = 0;
        boolean result = false;

        while(j < array.length) {
            int checkElem = array[j];
            for (int i = 0; i < array.length; i++) {
                if (checkElem == array[i] && j != i) {
                    return false;
                } else {
                    result = true;
                }
            }
            j++;
        }
        return result;
    }

    /**
     * Определите, является ли один массив перестановкой другого.
     * Т.е. в массивах хранится один и тот же набор элементов, но (возможно) в разном порядке.
     *
     * Для решения нжуно использовать одну хэш-таблицу.
     *
     * Например:
     * [1 2 3] и [3 2 1] = true
     * [1 1 2] и [1 2 1] = true
     * [1 2 3] и [1 2 3] = true
     * [] и [] = true
     *
     * [1 2] и [1 1 2] = false, разный набор элементов
     */
    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length == 0 && b.length == 0) return true;
        if (a.length != b.length) return false;
        HashMap<Integer, Integer> elemOfFirstArray = new HashMap<>();
        int count;

        for (int i = 0; i< a.length; i++) {
            if (a[i] == b[i]) {
                count = elemOfFirstArray.getOrDefault(a[i], 0);
                count += 2;
                elemOfFirstArray.put(a[i], count);
            } else {
                if (!elemOfFirstArray.containsKey(a[i])) {
                    elemOfFirstArray.put(a[i], 1);
                } else {
                    count = elemOfFirstArray.get(a[i]);
                    count += 1;
                    elemOfFirstArray.put(a[i], count);
                }
                if (!elemOfFirstArray.containsKey(b[i])) {
                    elemOfFirstArray.put(b[i], 1);
                } else {
                    count = elemOfFirstArray.get(b[i]);
                    count += 1;
                    elemOfFirstArray.put(b[i], count);
                }
            }
        }
        for (int i : elemOfFirstArray.values()) {
            if (i % 2 != 0) {
                return false;
            }
        }
        return true;

    }

    /**
     * Сложная задача.
     *
     * В памяти компьютера изображения (часто) хранятся в виде двумерного массива.
     * Напишите метод, который повернёт "изображение" на 90 градусов вправо.
     * "Изображение" в данном примере -- двумерный массив целых чисел.
     *
     * Например:
     * на входе:
     * [ [1 2]
     *   [3 4]
     *   [5 6] ]
     *
     * на выходе:
     * [ [5 3 1]
     *   [6 4 2] ]
     */
    public static int[][] rotateRight(int[][] image) {
        int columns = image.length;
        int rows = image[0].length;
        int endRows = rows - 1;
        int endColumns = columns - 1;
        int[][] rotateImage = new int[rows][columns];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j ++) {
                rotateImage[endRows - j][endColumns - i] =  image[i][endRows - j];
            }
        }
        return rotateImage;
    }
}
