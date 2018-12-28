package transfer;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TransferFiles {
    private static final String PATH_TO_FILES = "Введите путь к файлам, откуда бы вы хотели их скопировать и переместить: ";
    private static final String MOVE_TO = "Введите путь куда бы вы хотели их переместить: ";
    private static final String ACCESS_ERROR = "Ошибка доступа, путь не найден";
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> nameFiles = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(PATH_TO_FILES);
        Path dir = Paths.get(scanner.nextLine());
        System.out.println(MOVE_TO);
        String newDir = scanner.nextLine();

        transferFile(dir, newDir);
        if (nameFiles.size() != 0){
            statisticsFile();
        }
    }

    private static void transferFile(Path dir, String newDir) {
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
            for (Path entry:stream) {
                nameFiles.add(String.valueOf(entry));
                Files.move(entry , Paths.get(newDir + "\\" + entry.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (IOException e){
            System.out.println(ACCESS_ERROR);
        }
    }

    private static void statisticsFile() {
        System.out.println("Файлов перемещено: " + nameFiles.size());
        for (String list: nameFiles){
            System.out.println(list);
        }
    }

}
