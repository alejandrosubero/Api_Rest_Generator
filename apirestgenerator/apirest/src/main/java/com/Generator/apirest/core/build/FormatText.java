package com.Generator.apirest.core.build;

import com.Generator.apirest.services.builders.IImportModel;
import org.springframework.stereotype.Component;

import java.util.LinkedList;


//@Component
public class FormatText {

    private static final String BREAK_LINE = "\r\n";
    private static final String TAB = "\t";
    private static final String DOUBLETAB = "\t\t";
    private static final String LINE_SPACE_REGEX = "(?m)^( {0,3})";
    private static final String SPLIT_REGEX = "\\n";


    public String reformat(String text) {

        StringBuilder textFormated = new StringBuilder();
        String[] lines = null;

        if (!text.isEmpty()) {
            lines = text.split(SPLIT_REGEX);
        }

        if (lines != null && lines.length > 0) {
            LinkedList<Integer> levelCurlyBrace = new LinkedList<>();

            for (String line : lines) {
                String lineSpace = line.replaceAll(LINE_SPACE_REGEX, DOUBLETAB);

                if (!levelCurlyBrace.isEmpty() && lineSpace.contains("}")) {
                    levelCurlyBrace.removeLast();
                    lineSpace.replaceAll(LINE_SPACE_REGEX, getTab((levelCurlyBrace.size() - 1)));
                } else if (!levelCurlyBrace.isEmpty()) {
                    lineSpace.replaceAll(LINE_SPACE_REGEX, getTab((levelCurlyBrace.size() - 1)));
                }
                if (lineSpace.contains("{")) {
                    levelCurlyBrace.add(1);
                }
                textFormated.append(lineSpace).append(BREAK_LINE);
            }
        }
        return textFormated.toString();
    }


    private static String getTab(Integer spaces) {
        StringBuilder newSpaces = new StringBuilder();
        Integer wileloop = 0;
        while (spaces >= wileloop) {
            newSpaces.append(TAB);
            wileloop++;
        }
        return newSpaces.toString();
    }
}
