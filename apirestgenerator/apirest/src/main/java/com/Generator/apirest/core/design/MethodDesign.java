package com.Generator.apirest.core.design;


import com.Generator.apirest.core.build.Modifier;
import com.Generator.apirest.core.build.RetunsType;

import java.util.*;


public class MethodDesign implements MethodInterface {

    private Modifier modifiers;
    private RetunsType returnsType;
    private String returnsClass;
    private String methodName;
    private Boolean curlyBraces;
    private String methodBody;
    private List<String> parameter = new ArrayList<>(); // (String args, String args, String args)
    private List<String> annotation = new ArrayList<>();


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Modifier getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifier modifiers) {
        this.modifiers = modifiers;
    }

    public RetunsType getReturnsType() {
        return returnsType;
    }

    public void setReturnsType(RetunsType returnsType) {
        this.returnsType = returnsType;
    }

    public String getReturnsClass() {
        return returnsClass;
    }

    public void setReturnsClass(String returnsClass) {
        this.returnsClass = returnsClass;
    }

    public List<String> getParameter() {
        return parameter;
    }

    public void setParameter(List<String> parameter) {
        this.parameter = parameter;
    }

    public Boolean getCurlyBraces() {
        return curlyBraces;
    }

    public void setCurlyBraces(Boolean curlyBraces) {
        this.curlyBraces = curlyBraces;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public List<String> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(List<String> annotation) {
        this.annotation = annotation;
    }

    public MethodDesign() {
    }

    public MethodDesign(Modifier modifiers, RetunsType returnsType, String returnsClass, String methodName, Boolean curlyBraces, String methodBody, List<String> parameter, List<String> annotation) {
        this.modifiers = modifiers;
        this.returnsType = returnsType;
        this.returnsClass = returnsClass;
        this.methodName = methodName;
        this.curlyBraces = curlyBraces;
        this.methodBody = methodBody;
        this.parameter = parameter;
        this.annotation = annotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodDesign that = (MethodDesign) o;
        return modifiers == that.modifiers && returnsType == that.returnsType && Objects.equals(returnsClass, that.returnsClass) && Objects.equals(methodName, that.methodName) && Objects.equals(curlyBraces, that.curlyBraces) && Objects.equals(methodBody, that.methodBody) && Objects.equals(parameter, that.parameter) && Objects.equals(annotation, that.annotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifiers, returnsType, returnsClass, methodName, curlyBraces, methodBody, parameter, annotation);
    }

    @Override
    public String toString() {
        return buildSyntaxOfMethod();
    }

    private String buildSyntaxOfMethod() {
        StringBuilder methodTx = new StringBuilder();
        methodTx.append(this.annotationBuild(this.annotation));
        methodTx.append(this.modifiers.toString().toLowerCase());
        methodTx.append(this.returnsTypeBuild(this.returnsType, this.returnsClass));
        methodTx.append(this.methodName);
        methodTx.append(this.parameterBuildStructure(this.parameter));
        methodTx.append(this.bodyBuildStructure(this.curlyBraces, methodBody));
        return methodTx.toString();
    }


    public static MethodDesign.Builder builder() {
        return new MethodDesign.Builder();
    }

    public interface MethodBuilder {
        public Builder methodName(String methodName);
        public Builder modifiers(Modifier modifiers);
        public Builder returnsType(RetunsType returnsType);
        public Builder returnsClass(String returnsClass);
        public Builder parameter(List<String> parameter);
        public Builder curlyBraces(Boolean curlyBraces);
        public Builder methodBody(String methodBody);
        public Builder annotation(List<String> annotation);
        public MethodDesign build();
    }


    public static class Builder implements MethodBuilder {
        private String methodName;
        private Modifier modifiers; // (Modifier.PUBLIC, Modifier.STATIC)  public, private, protected, static, final, abstract, and synchronized.
        private RetunsType returnsType; // void or class
        private List<String> parameter; // (String args, String args, String args)
        private Boolean curlyBraces;
        private String methodBody;
        private String returnsClass;
        private List<String> annotation = new ArrayList<>();

        @Override
        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        @Override
        public Builder modifiers(Modifier modifiers) {
            this.modifiers = modifiers;
            return this;
        }

        @Override
        public Builder returnsType(RetunsType returnsType) {
            this.returnsType = returnsType;
            return this;
        }

        @Override
        public Builder returnsClass(String returnsClass) {
            this.returnsClass = returnsClass;
            return this;
        }

        @Override
        public Builder parameter(List<String> parameter) {
            this.parameter = parameter;
            return this;
        }

        @Override
        public Builder curlyBraces(Boolean curlyBraces) {
            this.curlyBraces = curlyBraces;
            return this;
        }

        @Override
        public Builder methodBody(String methodBody) {
            this.methodBody = methodBody;
            return this;
        }

        @Override
        public Builder annotation(List<String> annotation) {
            this.annotation = annotation;
            return this;
        }

        @Override
        public MethodDesign build() {
            MethodDesign method = new MethodDesign();

            if (this.methodName != null)
                method.setMethodName(this.methodName);

            if (this.modifiers != null)
                method.setModifiers(this.modifiers);

            if (this.returnsType != null)
                method.setReturnsType(this.returnsType);

            if (returnsClass != null)
                method.setReturnsClass(this.returnsClass);

            if (this.parameter != null)
                method.setParameter(this.parameter);

            if (this.curlyBraces != null)
                method.setCurlyBraces(this.curlyBraces);

            if (this.annotation != null)
                method.setAnnotation(this.annotation);

            if (this.methodBody != null)
                method.setMethodBody(this.methodBody);

            return method;
        }
    }


}












