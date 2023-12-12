package _else;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class PrepareDataForLocalization {

    public static void main(String[] args) throws IOException {
        convertScvLocalisationToSQL(
                Path.of("resources/Wykaz_urzędowych_nazw_miejscowości_stan_na_1.1.2019.csv"),
                        Path.of("resources/localization.sql")
                );
    }


    public static void convertScvLocalisationToSQL(Path path1, Path path2) throws IOException {
        try (
                BufferedReader in = Files.newBufferedReader(path1);
                BufferedWriter out = Files.newBufferedWriter(path2)
        ) {
//            Files.deleteIfExists(path2);
//            Files.createFile(path2);
            List<String> list = in.lines()
                    .map(s -> s.split(","))
                    .filter(arr -> arr[1].contains("częśc wsi"))
                    .map(arr ->
                            String.format("%s;%s;%s;%s",
                                    arr[4], arr[3], arr[2], arr[0]))

                    .toList();
            System.out.println(list);
            out.write(in.readLine());
        }
    }

}
