//Сжатие текстового файла

//1. Прочитать файл. Удалить дублирующиеся строки, указав в файле назначения количество удаленных дублирующихся строк.

//2. Реализовать восстановление сжатой версии в полную.


import java.io.*;
import java.util.HashSet;
import java.util.Set;
//В классе `FileCompression` определены два метода: `compressFile()` и `decompressFile()`. Есть также метод `main()`, который запускает сжатие файла и восстановление из сжатого файла.
public class FileCompression {
    public static void main(String[] args) {
        String sourceFileName = "source.txt";
        String compressedFileName = "compressed.txt";
        String decompressedFileName = "decompressed.txt";
        // Шаг 1: Сжатие файла
        compressFile(sourceFileName, compressedFileName);
        // Шаг 2: Восстановление из сжатого файла
        decompressFile(compressedFileName, decompressedFileName);
    }
    //Метод `compressFile()` принимает имя исходного файла и имя сжатого файла.
    private static void compressFile(String sourceFileName, String compressedFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));//для чтения исходного файла и объект 
             BufferedWriter writer = new BufferedWriter(new FileWriter(compressedFileName))) { 
            //для записи сжатого файла. Строки из исходного файла считываются по одной, и если строка не содержится в множестве `uniqueLines`, 
            //она записывается в сжатый файл, а если уже содержится, то считается, что дубликат удален. В конце метода выводится количество удаленных дубликатов.
            Set<String> uniqueLines = new HashSet<>();
            int removedDuplicates = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (uniqueLines.add(line)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    removedDuplicates++;
                }
            }
            System.out.println("Количество удаленных дубликатов: " + removedDuplicates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Метод `decompressFile()` принимает имя сжатого файла и имя файла после распаковки. 
    //Внутри метода создается объект `BufferedReader` для чтения сжатого файла и объект `BufferedWriter` для записи файла после распаковки. 
    //Строки из сжатого файла считываются по одной и записываются в файл после распаковки.
    private static void decompressFile(String compressedFileName, String decompressedFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(decompressedFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
