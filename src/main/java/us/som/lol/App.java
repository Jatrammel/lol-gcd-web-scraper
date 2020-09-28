package us.som.lol;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.som.lol.model.Contract;
import us.som.lol.model.Region;
import us.som.lol.model.Team;
import us.som.lol.model.Player;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream stream = App.class.getResourceAsStream("/gcd.properties")) {
            props.load(stream);
            String url = props.getProperty("gcd.url");
            System.out.println(url);
            Document doc = Jsoup.connect(url).maxBodySize(0).timeout(60000).get();
            Map<String, Team> teams = new ConcurrentHashMap<>();

            doc.getElementById("sheets-viewport").getElementById(props.getProperty("na.id")).select("tbody tr").stream().skip(2).forEach(row -> {
                StringBuffer str = new StringBuffer();
                Elements elements = row.select("td");
                if (StringUtils.isNotBlank(elements.first().text()) && !elements.stream().limit(9).anyMatch(e -> StringUtils.isBlank(e.text()))) {
                    Team team = teams.computeIfAbsent(elements.get(1).text(), t -> {
                        Team t1 = new Team(elements.get(0).text(), elements.get(1).text(), Region.NA);
                        t1.setPlayers(new ArrayList<>());
                        return t1;
                    });
                    Player player = new Player();
                    player.setFirstName(elements.get(4).text());
                    player.setFamilyName(elements.get(5).text());
                    player.setSummonerName(elements.get(2).text());
                    team.getPlayers().add(player);
                    Contract contract = new Contract();
                    contract.setPosition(elements.get(3).text());
                    contract.setTeam(team.getName());
                }
            });

            System.out.println("Total Teams: " + teams.size());
            System.out.println("Teams: ");
            teams.entrySet().forEach(team -> {
                System.out.println("\t" + team.getKey());
                team.getValue().getPlayers().forEach(player -> System.out.println("\t\t" + player.getSummonerName()));
            });
        } catch (
                FileNotFoundException e) {
            System.out.println(e);
        } catch (
                IOException e) {
            System.out.println(e);
        }
    }
}
