package albumcollection;

public class Artist implements Comparable<Artist> {
    private String name;
    private Date born;


    @Override
    public int compareTo(Artist o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", born=" + born +
                '}';
    }
}
