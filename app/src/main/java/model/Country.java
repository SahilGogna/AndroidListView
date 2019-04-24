package model;

public class Country implements Comparable{

    private String cName;
    private String cCapital;

    public Country(){

    }

    public Country(String cName, String cCapital){
        this.cName = cName;
        this.cCapital = cCapital;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcCapital() {
        return cCapital;
    }

    public void setcCapital(String cCapital) {
        this.cCapital = cCapital;
    }

    @Override
    public String toString(){
        return cName;
    }

    @Override
    public int compareTo(Object o) {
        Country otherObject = (Country) o;
        if(cName.compareTo(otherObject.cName)>0)
            return 1;
        else if(cName.compareTo(otherObject.cName) == 0)
            return 0;
        else
            return -1;
    }
}
