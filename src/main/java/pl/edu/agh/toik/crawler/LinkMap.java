package main.java.pl.edu.agh.toik.crawler;

/**
 * Created by xav92 on 10.06.15.
 */
public class LinkMap {
    private String name;
    private String link;

    public LinkMap(String name, String link){
        this.name = name;
        this.link = link;
    }

    public String getName(){
        return this.name;
    }

    public String getLink(){
        return this.link;
    }
}
