//Сжатие текстового файла

//1. Прочитать файл. Удалить дублирующиеся строки, указав в файле назначения количество удаленных дублирующихся строк.

//2. Реализовать восстановление сжатой версии в полную.


import java.io.*;
import java.util.HashSet;
import java.util.Set;
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
    private static void compressFile(String sourceFileName, String compressedFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(compressedFileName))) {
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
