package org.example.business.services.init;

import java.util.Arrays;
import java.util.List;

public class DataManipulationUtil {

    public static List<String> dataAsList(String line) {
        return Arrays.asList(line.substring(line.indexOf("->") + 2).trim().split(";"));
    }
}
