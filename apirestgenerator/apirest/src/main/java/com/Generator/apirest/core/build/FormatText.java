package com.Generator.apirest.core.build;

import java.util.LinkedList;
import java.util.regex.*;

//@Component
public class FormatText {

    private static final String BREAK_LINE = "\r\n";
    private static final String TAB = "\t";
    private static final String DOUBLETAB = "\t\t";
    private static final String LINE_SPACE_REGEX = "(?m)^( {0,3})";
    private static final String SPLIT_REGEX = "\\n";


    public String reformatSpaceInOneLine(String text) {
        return text.replaceAll(LINE_SPACE_REGEX, TAB);
    }


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


    public String reformatMethod(String text) {

        StringBuilder textFormated = new StringBuilder();
        String[] lines = null;
        String newText = formatted(text);

        if (!newText.isEmpty()) {
            lines = newText.split(SPLIT_REGEX);
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
                textFormated.append(lineSpace);
            }
        }
        return textFormated.toString();
    }


    public String saltosDeLinea(String text) {
        String regex = "(\\n)+";
        String textoSinSaltosMultiples = text.replaceAll(regex, "\n");
        return textoSinSaltosMultiples;
    }

    public String noformatted(String text){
        String regex = "(\\n\\s*){2,}";
        String indentRegex = "^(?!\\s*$)";
        String formattedCode = text.replaceAll(regex, "\n");
        formattedCode = formattedCode.replaceAll(indentRegex, "    ");
        return formattedCode;
    }


    public static String reemplazarSaltosLinea(String texto) {
        Pattern patron = Pattern.compile("\n+");
        Matcher matcher = patron.matcher(texto);
        return matcher.replaceAll("\n");
    }

    public static String removespaceToStartTheLine(String text) {
        Pattern patron = Pattern.compile("(?m)^( {0,5})");
        Matcher matcher = patron.matcher(text);
        return matcher.replaceAll(" ");
    }


    public String formatted(String text){
        String[] lines = null;
        String block1 =  reemplazarSaltosLinea(text);
        String block2 = removespaceToStartTheLine(block1);

      return block2;
    }

}
