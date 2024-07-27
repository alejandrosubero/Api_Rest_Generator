package com.generator.core.format.operator;

import java.util.List;

public class RemoveFormatte implements IBaseString {

    protected String start(String multiLineText) {
        List<String> lineList = IBaseString.createList(multiLineText);
        if(lineList != null && lineList.size()>0){
            lineList.removeIf(String::isEmpty);
            return  remuveSpaceDoubleInLine(remuveIndentation(lineList));
        }
        return "";
    }

    private String remuveIndentation(List<String> lineList){
        StringBuffer writeText2 = new StringBuffer();
        lineList.forEach(linetext -> writeText2.append(linetext.trim()).append(IBaseString.createNewLine));
       return writeText2.toString();
    }

    private String remuveSpaceDoubleInLine(String multiLineText){

        StringBuffer writeText2 = new StringBuffer();

        for (int i = 0; i < multiLineText.length(); i++) {
            char currentChar = multiLineText.charAt(i);
            if (currentChar == ' ' && i > 0 && multiLineText.charAt(i - 1) == ' ') {
                continue;
            } else if (currentChar == '\u00A0' || currentChar == '\t') {
                writeText2.append(' ');
            } else {
                writeText2.append(currentChar);
            }
        }
        return writeText2.toString();
    }
}

