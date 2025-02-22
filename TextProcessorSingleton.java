import java.util.Random;

public class TextProcessorSingleton {
    private static TextProcessorSingleton instance;
    private boolean modifySogl; // true — изменяем только согласные, false — изменяем только гласные

    private TextProcessorSingleton() {
        // Приватный конструктор для Singleton
    }

    public static TextProcessorSingleton getInstance() {
        if (instance == null) {
            instance = new TextProcessorSingleton();
        }
        return instance;
    }

    public void setModifySogl(boolean modifySogl) {
        this.modifySogl = modifySogl;
    }

    public boolean isModifySogl() {
        return modifySogl;
    }

    public String processText(String inputText) {
        String Glas = "AEIOUYaeiouyАЕЁИОУЫЭЮЯаеёиоуыэюя";
        String sogls = "BCDFGHJKLMNPQRSTVWXZbcdfghjklmnpqrstvwxzБВГДЖЗЙКЛМНПРСТФХЦЧШЩбвгджзйклмнпрстфхцчшщ";
        StringBuilder result = new StringBuilder(inputText);
        Random random = new Random();

        int GlasCount = 0;
        int soglCount = 0;

        // Подсчет количества гласных и согласных
        for (char ch : inputText.toCharArray()) {
            if (Glas.indexOf(ch) != -1) {
                GlasCount++;
            } else if (sogls.indexOf(ch) != -1) {
                soglCount++;
            }
        }

        // Если modifySogl = true, изменяем только согласные
        if (modifySogl) {
            while (GlasCount != soglCount) {
                if (GlasCount > soglCount) {
                    // Добавляем случайную согласную
                    char newsogl = sogls.charAt(random.nextInt(sogls.length()));
                    int insertPos = random.nextInt(result.length() + 1);
                    result.insert(insertPos, newsogl);
                    soglCount++;
                } else {
                    // Удаляем случайную согласную
                    int deleteIndex = findRandomCharIndex(result, sogls, random);
                    if (deleteIndex != -1) {
                        result.deleteCharAt(deleteIndex);
                        soglCount--;
                    } else {
                        break; // Выход, если удалять нечего
                    }
                }
            }
        }
        // Если modifySogl = false, изменяем только гласные
        else {
            while (GlasCount != soglCount) {
                if (GlasCount < soglCount) {
                    // Добавляем случайную гласную
                    char newGlas = Glas.charAt(random.nextInt(Glas.length()));
                    int insertPos = random.nextInt(result.length() + 1);
                    result.insert(insertPos, newGlas);
                    GlasCount++;
                } else {
                    // Удаляем случайную гласную
                    int deleteIndex = findRandomCharIndex(result, Glas, random);
                    if (deleteIndex != -1) {
                        result.deleteCharAt(deleteIndex);
                        GlasCount--;
                    } else {
                        break; // Выход, если удалять нечего
                    }
                }
            }
        }

        return result.toString();
    }

    // Метод для поиска случайного символа в строке
    private int findRandomCharIndex(StringBuilder text, String charSet, Random random) {
        for (int i = 0; i < text.length(); i++) {
            int index = random.nextInt(text.length());
            if (charSet.indexOf(text.charAt(index)) != -1) {
                return index;
            }
        }
        return -1; // Если символ не найден
    }
}