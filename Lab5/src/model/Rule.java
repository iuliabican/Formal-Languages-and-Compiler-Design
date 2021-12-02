package model;

import java.util.List;

public class Rule {

    private String lhs;
    private List<String> rhs;

    public Rule(String lhs, List<String> rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String getLhs() {
        return lhs;
    }

    public void setLhs(String lhs) {
        this.lhs = lhs;
    }

    public List<String> getRhs() {
        return rhs;
    }

    public void setRhs(List<String> rhs) {
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        result.append(this.lhs).append(" -> ");

        for (int i=0; i<this.rhs.size(); i++)
            result.append(rhs.get(i) + " ");

        return result.toString();
    }
}
