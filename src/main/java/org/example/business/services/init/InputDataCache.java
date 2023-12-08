package org.example.business.services.init;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InputDataCache {
    private static final String FILE_PATH = "./src/main/resources/working_file.md";

    private static final Map<String, List<String>> inputData;

    static{
        try{
            inputData = readFileContent();
        }catch (Throwable ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    private static Map<String, List<String>> readFileContent() {
        Path path = Path.of(FILE_PATH);
        try {

            List<String> lines = Files.readAllLines(path).stream()
                    .filter(line -> !line.contains("[//]"))
                    .filter(line -> !line.isBlank())
                    .toList();

            return lines.stream()
                    .collect(Collectors.groupingBy(
                            line -> line.split("->")[0].trim(),
                            Collectors.mapping(
                                    line -> line.substring(line.indexOf("->") + 2).trim(),
                                    Collectors.toList()
                            )
                    ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static List<String> getInputData(
            Keys.InputDataGroup inputDataGroup,
            Keys.Entity entity
    ){
        return Optional.ofNullable(inputData.get(inputDataGroup.toString()))
                .orElse(List.of())
                .stream()
                .filter(line -> line.startsWith(entity.toString()))
                .toList();
    }

    public static List<String> getInputData(
            Keys.InputDataGroup inputDataGroup
    ){
        return new ArrayList<>(Optional.ofNullable(inputData.get(inputDataGroup.toString()))
                .orElse(List.of()));
    }



}
