package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Rule {

    private String lhs;
    private List<String> rhs;

    public Rule(String lhs, List<String> rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rule other = (Rule) obj;
        if (!Objects.equals(this.lhs, other.lhs)) {
            return false;
        }
        if (!this.rhs.equals(other.rhs)) {
            return false;
        }
        return true;
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

    public String[] getRhsArray(){
        /*
            Used only for creating the hash of ItemLR0
            Since the method Arrays.deepHashCode won't work on List<String> objects
        * */
        String[] result = new String[rhs.size()];
        rhs.toArray(result);
        return result;
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
