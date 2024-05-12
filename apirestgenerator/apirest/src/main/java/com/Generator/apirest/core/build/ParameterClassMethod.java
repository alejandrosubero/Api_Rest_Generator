package com.Generator.apirest.core.build;

import java.util.Objects;

public class ParameterClassMethod {
    private String atributoClass;
    private String atributoName;

    public ParameterClassMethod() {
    }

    public ParameterClassMethod(String atributoClass, String atributoName) {
        this.atributoClass = atributoClass;
        this.atributoName = atributoName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterClassMethod parameterClassMethod = (ParameterClassMethod) o;
        return Objects.equals(atributoClass, parameterClassMethod.atributoClass) && Objects.equals(atributoName, parameterClassMethod.atributoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atributoClass, atributoName);
    }

    @Override
    public String toString() {
        StringBuilder parameter = new StringBuilder();
        parameter.append(atributoClass);
        parameter.append(" ");
        parameter.append(atributoName);
        return parameter.toString();
    }

    public static ParameterClassMethod.Builder builder() {
        return new ParameterClassMethod.Builder();
    }

    public interface ParameterBuilder{
        public Builder atributoClass(String atributoClass);
        public Builder atributoName(String atributoName);
        public ParameterClassMethod build();
    }

    public static class Builder implements ParameterBuilder{
        private String atributoClass;
        private String atributoName;


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
        public ParameterClassMethod build() {
            ParameterClassMethod parameterClassMethod = new ParameterClassMethod();

            if(this.atributoName !=null)
                parameterClassMethod.setAtributoName(this.atributoName);

            if (this.atributoClass != null)
                parameterClassMethod.setAtributoClass(this.atributoClass);

            return parameterClassMethod;
        }
    }
}
