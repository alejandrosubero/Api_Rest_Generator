package com.Generator.apirest.pojos.back;

public class MethodManager {

    private Boolean methodFindByOrLoop; // method find for tipe or neme or atributed

    private Boolean methodfindById;

    private Boolean metohdSave;

    private Boolean methodgetAll;

    private Boolean methodDelete;

    private Boolean methodUpdate;

    private Boolean methodsaveOrUpdate;

    private Boolean methodContaining; // metodo contain de atributos de la clase

    private Boolean methodContainingRelacion; // si hay relacion

    private Boolean methodContainingRelacionNoBiDirectional; // si hay relacion bideireccional

    public MethodManager() {
    }

    public MethodManager(Boolean defaultValue) {
        this.validDefault(defaultValue);
    }


    public void validDefault(Boolean defaultValue) {
        if(defaultValue == null) {defaultValue = false;}

        this.methodFindByOrLoop = defaultValue;
        this.methodfindById = defaultValue;
        this.metohdSave = defaultValue;
        this.methodgetAll = defaultValue;
        this.methodUpdate = defaultValue;
        this.methodsaveOrUpdate = defaultValue;
        this.methodContaining = defaultValue;
        this.methodContainingRelacion = defaultValue;
        this.methodContainingRelacionNoBiDirectional = defaultValue;
    }


    public boolean isMethodFindByOrLoop() {
        return methodFindByOrLoop;
    }

    public void setMethodFindByOrLoop(boolean methodFindByOrLoop) {
        this.methodFindByOrLoop = methodFindByOrLoop;
    }

    public boolean isMethodfindById() {
        return methodfindById;
    }

    public void setMethodfindById(boolean methodfindById) {
        this.methodfindById = methodfindById;
    }

    public boolean isMetohdSave() {
        return metohdSave;
    }

    public void setMetohdSave(boolean metohdSave) {
        this.metohdSave = metohdSave;
    }

    public boolean isMethodgetAll() {
        return methodgetAll;
    }

    public void setMethodgetAll(boolean methodgetAll) {
        this.methodgetAll = methodgetAll;
    }

    public boolean isMethodDelete() {
        return methodDelete;
    }

    public void setMethodDelete(boolean methodDelete) {
        this.methodDelete = methodDelete;
    }

    public boolean isMethodUpdate() {
        return methodUpdate;
    }

    public void setMethodUpdate(boolean methodUpdate) {
        this.methodUpdate = methodUpdate;
    }

    public boolean isMethodsaveOrUpdate() {
        return methodsaveOrUpdate;
    }

    public void setMethodsaveOrUpdate(boolean methodsaveOrUpdate) {
        this.methodsaveOrUpdate = methodsaveOrUpdate;
    }

    public boolean isMethodContaining() {
        return methodContaining;
    }

    public void setMethodContaining(boolean methodContaining) {
        this.methodContaining = methodContaining;
    }

    public boolean isMethodContainingRelacion() {
        return methodContainingRelacion;
    }

    public void setMethodContainingRelacion(boolean methodContainingRelacion) {
        this.methodContainingRelacion = methodContainingRelacion;
    }

    public boolean isMethodContainingRelacionNoBiDirectional() {
        return methodContainingRelacionNoBiDirectional;
    }

    public void setMethodContainingRelacionNoBiDirectional(boolean methodContainingRelacionNoBiDirectional) {
        this.methodContainingRelacionNoBiDirectional = methodContainingRelacionNoBiDirectional;
    }


    @Override
    public String toString() {
        return "MethodManager [methodFindByOrLoop=" + methodFindByOrLoop + ", methodfindById=" + methodfindById
                + ", metohdSave=" + metohdSave + ", methodgetAll=" + methodgetAll + ", methodDelete=" + methodDelete
                + ", methodUpdate=" + methodUpdate + ", methodsaveOrUpdate=" + methodsaveOrUpdate
                + ", methodContaining=" + methodContaining + ", methodContainingRelacion=" + methodContainingRelacion
                + ", methodContainingRelacionNoBiDirectional=" + methodContainingRelacionNoBiDirectional + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (methodContaining ? 1231 : 1237);
        result = prime * result + (methodContainingRelacion ? 1231 : 1237);
        result = prime * result + (methodContainingRelacionNoBiDirectional ? 1231 : 1237);
        result = prime * result + (methodDelete ? 1231 : 1237);
        result = prime * result + (methodFindByOrLoop ? 1231 : 1237);
        result = prime * result + (methodUpdate ? 1231 : 1237);
        result = prime * result + (methodfindById ? 1231 : 1237);
        result = prime * result + (methodgetAll ? 1231 : 1237);
        result = prime * result + (methodsaveOrUpdate ? 1231 : 1237);
        result = prime * result + (metohdSave ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodManager other = (MethodManager) obj;
        if (methodContaining != other.methodContaining)
            return false;
        if (methodContainingRelacion != other.methodContainingRelacion)
            return false;
        if (methodContainingRelacionNoBiDirectional != other.methodContainingRelacionNoBiDirectional)
            return false;
        if (methodDelete != other.methodDelete)
            return false;
        if (methodFindByOrLoop != other.methodFindByOrLoop)
            return false;
        if (methodUpdate != other.methodUpdate)
            return false;
        if (methodfindById != other.methodfindById)
            return false;
        if (methodgetAll != other.methodgetAll)
            return false;
        if (methodsaveOrUpdate != other.methodsaveOrUpdate)
            return false;
        if (metohdSave != other.metohdSave)
            return false;
        return true;
    }


}
