
package com.example.prueba;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementList {

    @SerializedName("statement")
    @Expose
    private List<Statement> statement = null;

    public List<Statement> getStatement() {
        return statement;
    }

    public void setStatement(List<Statement> statement) {
        this.statement = statement;
    }

}
