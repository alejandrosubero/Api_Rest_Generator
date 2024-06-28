package com.Generator.apirest.core.design;

import com.Generator.apirest.core.build.BuildFileClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BodyMethodDesign implements DesingCommunInterface {

    private List<String> bodyLines = new ArrayList<>();

    public BodyMethodDesign() {
    }

    public List<String> getBodyLines() {
        return bodyLines;
    }

    public void setBodyLines(List<String> bodyLines) {
        this.bodyLines = bodyLines;
    }

    public void addItem(String item){
        this.bodyLines.add(item);
    }

    @Override
    public String toString() {
//        List<String> toStringbodyLines = new ArrayList<>();
//        toStringbodyLines = bodyLines.stream().map(line -> stringEnsamble(List.of(line,BREAK_LINE))).collect(Collectors.toList());
        return stringEnsamble(bodyLines.stream().map(line -> stringEnsamble(List.of(line, BREAK_LINE))).collect(Collectors.toList()));
    }

    public static BodyMethodDesign.Builder builder() {
        return new BodyMethodDesign.Builder();
    }

    public interface BodyMethodDesignBuilder {
        public Builder bodyLines(List<String> bodyLines);

        public BodyMethodDesign build();
    }

    public static class Builder implements BodyMethodDesignBuilder {

        private List<String> bodyLines = new ArrayList<>();

        @Override
        public Builder bodyLines(List<String> bodyLines) {
            this.bodyLines = bodyLines;
            return this;
        }

        @Override
        public BodyMethodDesign build() {
            BodyMethodDesign body = new BodyMethodDesign();
            if (this.bodyLines != null && this.bodyLines.size() > 0) {
                body.setBodyLines(this.bodyLines);
            }
            return body;
        }
    }
}
