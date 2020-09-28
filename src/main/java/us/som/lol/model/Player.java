package us.som.lol.model;

public class Player {
    private Contract contract;
    private String familyName;
    private String firstName;
    private String summonerName;

    public Contract getContact() {
        return contract;
    }

    public void setContact(Contract contract) {
        this.contract = contract;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }
}
