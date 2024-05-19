package com.Generator.apirest.core.build;

import com.Generator.apirest.services.builders.IConstantModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParameterClassMethod {
    private String atributoClass;
    private String atributoName;
    private List<String> annotation = new ArrayList<>();

//TODO: COLOCAR UN NUEVO PARAMETRO  =  private Modifier modifier;
    
    public ParameterClassMethod() {
    }

    public ParameterClassMethod(String atributoClass, String atributoName) {
        this.atributoClass = atributoClass;
        this.atributoName = atributoName;
    }

    public ParameterClassMethod(String atributoClass, String atributoName, List<String> annotation) {
        this.atributoClass = atributoClass;
        this.atributoName = atributoName;
        this.annotation = annotation;
    }

    public String getAtributoClass() {
        return atributoClass;
    }

    public void setAtributoClass(String atributoClass) {
        this.atributoClass = atributoClass;
    }

    public String getAtributoName() {
        return atributoName;
    }

    public void setAtributoName(String atributoName) {
        this.atributoName = atributoName;
    }

    public List<String> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(List<String> annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterClassMethod that = (ParameterClassMethod) o;
        return Objects.equals(atributoClass, that.atributoClass) && Objects.equals(atributoName, that.atributoName) && Objects.equals(annotation, that.annotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atributoClass, atributoName);
    }


    @Override
    public String toString() {
        StringBuilder parameter = new StringBuilder();
        StringBuilder internal = new StringBuilder(IConstantModel.BREAK_LINE);
        if(this.annotation.size() > 0){
            for (String anntationParameter : this.annotation){
                internal.append(anntationParameter);
//                internal.append("\r\n");
                internal.append(IConstantModel.BREAK_LINE);
            }
        }
        parameter.append(internal.toString());
        parameter.append(atributoClass);
        parameter.append(IConstantModel.TAB);
//        parameter.append("\t");
        parameter.append(atributoName);

        if(this.annotation !=null && this.annotation.size() > 0){
            parameter.append(IConstantModel.SEMICOLON);
            parameter.append(IConstantModel.BREAK_LINE);
//            parameter.append(";");
        }

        return parameter.toString();
    }

    public static ParameterClassMethod.Builder builder() {
        return new ParameterClassMethod.Builder();
    }

    public interface ParameterBuilder{
        public Builder atributoClass(String atributoClass);
        public Builder atributoName(String atributoName);
        public Builder annotations(List<String> annotation);
        public ParameterClassMethod build();
    }

    public static class Builder implements ParameterBuilder{
        private String atributoClass;
        private String atributoName;
        private List<String> annotation = new ArrayList<>();

        @Override
        public Builder atributoClass(String atributoClass) {
            this.atributoClass=atributoClass;
            return this;
        }

        @Override
        public Builder atributoName(String atributoName) {
            this.atributoName =atributoName;
            return this;
        }

        @Override
        public Builder annotations(List<String> annotation) {
            this.annotation = annotation;
            return this;
        }

        @Override
        public ParameterClassMethod build() {
            ParameterClassMethod parameterClassMethod = new ParameterClassMethod();

            if(this.atributoName !=null)
                parameterClassMethod.setAtributoName(this.atributoName);

            if (this.atributoClass != null)
                parameterClassMethod.setAtributoClass(this.atributoClass);

            if(this.annotation !=null){
                parameterClassMethod.setAnnotation(this.annotation);
            }

            return parameterClassMethod;
        }
    }
}
