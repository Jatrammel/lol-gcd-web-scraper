package us.stateofmisery.lol;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.stateofmisery.lol.model.Contract;
import us.stateofmisery.lol.model.Player;
import us.stateofmisery.lol.model.Region;
import us.stateofmisery.lol.model.Team;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream stream = App.class.getResourceAsStream("/gcd.properties")) {
            props.load(stream);
            String url = props.getProperty("gcd.url");
            System.out.println(url);
            Document doc = Jsoup.connect(url).maxBodySize(0).timeout(60000).get();
            Map<String, Team> teams = new HashMap<>();

            doc.getElementById("sheets-viewport").getElementById(props.getProperty("na.id")).select("tbody tr").forEach(element -> {
                StringBuffer str = new StringBuffer();
                if (StringUtils.isNoneBlank(element.selectFirst("td").text())) {
                    Elements cells = element.getElementsByTag("td");
                    Team team = teams.computeIfAbsent(cells.get(1).text(), t -> {
                        Team t1 = new Team(cells.get(0).text(), cells.get(1).text(), Region.NA);
                        t1.setPlayers(new ArrayList<>());
                        return t1;
                    });
                Player player = new Player();
                player.setFirstName(cells.get(4).text());
                player.setFamilyName(cells.get(5).text());
                player.setSummonerName(cells.get(2).text());
                team.getPlayers().add(player);
                Contract contract = new Contract();
                contract.setPosition(cells.get(3).text());
                contract.setTeam(team.getName());
            }
        });

        System.out.println("Total Teams: " + teams.size());
        System.out.println("Teams: ");
        teams.entrySet().forEach(team -> {
            System.out.println("\t" + team.getKey());
            team.getValue().getPlayers().forEach(player -> System.out.println("\t\t" + player.getSummonerName()));
        });
    } catch(
    FileNotFoundException e)

    {
        System.out.println(e);
    } catch(
    IOException e)

    {
        System.out.println(e);
    }
}
}
