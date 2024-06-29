package com.generator.core.design;



import com.generator.core.design.reference.Modifier;
import com.generator.core.interfaces.IConstantModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParameterClassMethod {
    private String atributoClass;
    private String atributoName;
    private List<String> annotation = new ArrayList<>();
    private Modifier modifier;
    private String parameterSignatuer;

//TODO: COLOCAR UN NUEVO PARAMETRO  =  private Modifier modifier;

    public ParameterClassMethod() {
    }

    public ParameterClassMethod(String atributoClass, String atributoName) {
        this.atributoClass = atributoClass;
        this.atributoName = atributoName;
    }

    public ParameterClassMethod(String atributoClass, String atributoName, List<String> annotation, Modifier modifier) {
        this.atributoClass = atributoClass;
        this.atributoName = atributoName;
        this.annotation = annotation;
        this.modifier = modifier;
    }

    public ParameterClassMethod(String atributoClass, String atributoName, List<String> annotation, Modifier modifier, String parameterSignatuer) {
        this.atributoClass = atributoClass;
        this.atributoName = atributoName;
        this.annotation = annotation;
        this.modifier = modifier;
        this.parameterSignatuer = parameterSignatuer;
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

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public String getParameterSignatuer() {
        return parameterSignatuer;
    }

    public void setParameterSignatuer(String parameterSignatuer) {
        this.parameterSignatuer = parameterSignatuer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParameterClassMethod)) return false;
        ParameterClassMethod that = (ParameterClassMethod) o;
        return Objects.equals(atributoClass, that.atributoClass) && Objects.equals(atributoName, that.atributoName) && Objects.equals(annotation, that.annotation) && modifier == that.modifier && Objects.equals(parameterSignatuer, that.parameterSignatuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atributoClass, atributoName, annotation, modifier, parameterSignatuer);
    }

    @Override
    public String toString() {

        StringBuilder parameter = new StringBuilder();
        StringBuilder internal = new StringBuilder(IConstantModel.BREAK_LINE);

        if (this.annotation !=null && this.annotation.size() > 0) {
            for (String anntationParameter : this.annotation) {
                internal.append(anntationParameter);
                internal.append(IConstantModel.BREAK_LINE);
            }
        }

        if (this.modifier != null) {
            internal.append(this.modifier.toString().toLowerCase());
            internal.append(IConstantModel.SPACE);
        }

        if(atributoClass != null && atributoName != null){
            parameter.append(internal.toString());
            parameter.append(atributoClass);
            parameter.append(IConstantModel.SPACE);
            parameter.append(atributoName);
        }

////        parameter.append("\t");
        if (this.annotation != null && this.annotation.size() > 0) {
            parameter.append(IConstantModel.SEMICOLON);
            parameter.append(IConstantModel.BREAK_LINE);
        }

        if(parameterSignatuer != null && atributoClass == null && atributoName == null){
            parameter.append(parameterSignatuer);
        }

        return parameter.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public interface ParameterBuilder {
        public Builder atributoClass(String atributoClass);

        public Builder atributoName(String atributoName);

        public Builder annotations(List<String> annotation);

        public Builder modifier(Modifier modifier);

        public Builder parameterSignatuer(String parameterSignatuer);

        public ParameterClassMethod build();

    }

    public static class Builder implements ParameterBuilder {
        private String atributoClass;
        private String atributoName;
        private List<String> annotation = new ArrayList<>();
        private Modifier modifier;
        private String parameterSignatuer;

        @Override
        public Builder atributoClass(String atributoClass) {
            this.atributoClass = atributoClass;
            return this;
        }

        @Override
        public Builder atributoName(String atributoName) {
            this.atributoName = atributoName;
            return this;
        }

        @Override
        public Builder annotations(List<String> annotation) {
            this.annotation = annotation;
            return this;
        }

        @Override
        public Builder modifier(Modifier modifier) {
            this.modifier = modifier;
            return this;
        }

        @Override
        public Builder parameterSignatuer(String parameterSignatuer) {
            this.parameterSignatuer = parameterSignatuer;
            return this;
        }

        @Override
        public ParameterClassMethod build() {
            ParameterClassMethod parameterClassMethod = new ParameterClassMethod();

            if (this.atributoName != null)
                parameterClassMethod.setAtributoName(this.atributoName);

            if (this.atributoClass != null)
                parameterClassMethod.setAtributoClass(this.atributoClass);

            if (this.annotation != null) {
                parameterClassMethod.setAnnotation(this.annotation);
            }

            if (this.modifier != null) {
                parameterClassMethod.setModifier(this.modifier);
            }

            if(this.parameterSignatuer !=null){
                parameterClassMethod.setParameterSignatuer(this.parameterSignatuer);
            }

            return parameterClassMethod;
        }
    }
}
