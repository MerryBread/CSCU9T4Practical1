package com.stir.cscu9t4practical1;

public class SwimEntry extends Entry {
    private String where;

    SwimEntry (String n, int d, int m, int y, int h, int min, int s, float dist, String whr) {
        super(n, d, m, y, h, min, s, dist);
        where = whr;
    }

    public String getWhere() { return where;}

    @Override
    public String getEntry () {
        String result = getName() + " swam " + getDistance() + " km " + getWhere() + " in "
                + getHour() + ":" + getMin() + ":" + getSec() + " on "
                + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
        return result;
    }
}
