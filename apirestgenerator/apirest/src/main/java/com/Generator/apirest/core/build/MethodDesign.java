package com.Generator.apirest.core.build;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


public class MethodDesign {

    private Modifier modifiers;
    private RetunsType returnsType;
    private String returnsClass;
    private String methodName;
    private List<String> parameter; // (String args, String args, String args)
    private Boolean curlyBraces;
    private String methodBody;


    public String buildSyntaxOfMethod() {
        if (curlyBraces) {
            String modifiers = this.modifiers.toString().toLowerCase();

            //the Metohod is generated wicht {} and body inside.
            if (methodBody != null) {
                // inside of body in top and in end BREAK_LINE and
                //the method Body is generated.
            }
        } else {
            // put a ( ; ) to the end the Syntax
        }
        return null;
    }



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

    public MethodDesign() {
    }

    public MethodDesign(Modifier modifiers, RetunsType returnsType, String returnsClass, String methodName, List<String> parameter, Boolean curlyBraces, String methodBody) {
        this.modifiers = modifiers;
        this.returnsType = returnsType;
        this.returnsClass = returnsClass;
        this.methodName = methodName;
        this.parameter = parameter;
        this.curlyBraces = curlyBraces;
        this.methodBody = methodBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodDesign that = (MethodDesign) o;
        return Objects.equals(methodName, that.methodName) && Objects.equals(modifiers, that.modifiers) && Objects.equals(returnsType, that.returnsType) && Objects.equals(parameter, that.parameter) && Objects.equals(curlyBraces, that.curlyBraces) && Objects.equals(methodBody, that.methodBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodName, modifiers, returnsType, parameter, curlyBraces, methodBody);
    }

    public static MethodDesign.Builder builder(){
        return  new MethodDesign.Builder();
    }

    public interface MethodBuilder {
        public Builder methodName(String methodName);
        public Builder modifiers(Modifier modifiers);
        public Builder returnsType(RetunsType returnsType);
        public Builder returnsClass(String returnsClass);
        public Builder parameter(List<String> parameter);
        public Builder curlyBraces(Boolean curlyBraces);
        public Builder methodBody(String methodBody);
        public MethodDesign build();
    }


    public static class Builder implements MethodBuilder{
        private String methodName;
        private Modifier modifiers; // (Modifier.PUBLIC, Modifier.STATIC)  public, private, protected, static, final, abstract, and synchronized.
        private RetunsType returnsType; // void or class
        private List<String> parameter; // (String args, String args, String args)
        private Boolean curlyBraces;
        private String methodBody;
        private String returnsClass;

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
        public MethodDesign build() {
            MethodDesign method = new MethodDesign();

            if (this.methodName != null)
                method.setMethodName(this.methodName);

            if (this.modifiers != null)
                method.setReturnsType(this.returnsType);

            if (this.parameter !=null)
                method.setParameter(this.parameter);

            if (this.curlyBraces != null)
                method.setCurlyBraces(this.curlyBraces);

            if(returnsClass != null){
                method.setReturnsClass(this.returnsClass);
            }
            if (this.methodBody !=null)
                method.setMethodBody(this.methodBody);
            return method;
        }
    }






}












