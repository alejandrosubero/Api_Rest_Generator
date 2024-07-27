package com.generator.core.pojos;

import java.util.Objects;

public class ModelMethod {

    private String methodSignature;
    private Boolean create;

    public ModelMethod(String methodSignature, Boolean create) {
        this.methodSignature = methodSignature;
        this.create = create;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(String methodSignature) {
        this.methodSignature = methodSignature;
    }

    public Boolean getCreate() {
        return create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelMethod that = (ModelMethod) o;
        return Objects.equals(methodSignature, that.methodSignature) && Objects.equals(create, that.create);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodSignature, create);
    }
}
