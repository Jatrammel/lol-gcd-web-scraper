package us.som.lol.model;

import java.util.List;

public class Team {
    private String league;
    private String name;
    private List<Player> players;
    private Region region;

    public Team() {}

    public Team(String league, String name, Region region) {
        this.league = league;
        this.name = name;
        this.region = region;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
