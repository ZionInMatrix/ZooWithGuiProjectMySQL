package core;

import java.util.Date;

public class Osetrovatele {
    private int id;
    private String jmeno;
    private Date narozen;

    public Osetrovatele(int id, String jmeno, Date narozen) {
        this.id = id;
        this.jmeno = jmeno;
        this.narozen = narozen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public Date getNarozen() {
        return narozen;
    }

    public void setNarozen(Date narozen) {
        this.narozen = narozen;
    }

    @Override
    public String toString() {
        return String
                .format("Osetrovatel [id=%s, jmeno=%s, narozen=%s]",
                        id, jmeno, narozen);
    }



}
