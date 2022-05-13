package com.example.WeSwingServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ser.Serializers;
import items.DanceEventItemID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;

import items.DanceEventItem;
import profile.Profile;
//import profile.Profile;

@Controller
@RequestMapping(path="/process")
public class WeSwingService {

    @CrossOrigin
    @RequestMapping(path="/show")
    @ResponseBody
    public List<DanceEventItemID> info() {
        List<DanceEventItemID> events = new ArrayList<>();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

// DATA FROM  -SWINGPLANIT-
        try {
            HtmlPage page = webClient.getPage("https://www.swingplanit.com/");
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

            String title = page.getTitleText();
            System.out.println("Page Title: " + title);

            List<?> anchors = page.getByXPath("//a[@class='maintitles']");
            List<?> anchors1 = page.getByXPath("//a[starts-with(@href,'/events/')]");

            for (int i = 0; i < anchors.size(); i++) {
                HtmlAnchor link = (HtmlAnchor) anchors.get(i);

                // ACQUIRE DATA
                String articleTitle = link.getAttribute("title");
                String articleLink = link.getHrefAttribute();
                String pins =  link.getLastChild().getTextContent();
                String country = ((HtmlDivision) link.getByXPath(".//div[@class='pins']").get(0)).getTextContent();
                String day = ((HtmlDivision) link.getByXPath(".//div[@class='daycalendar']").get(0)).getTextContent();

                System.out.println("\nTitle: " + articleTitle);
                System.out.println("Link: " + articleLink);
                System.out.println("Country: " + country);
                System.out.println("Day: " + day);

                // INTERNAL SCRAPPING
                HtmlPage internalPage = webClient.getPage(articleLink);
                webClient.getCurrentWindow().getJobManager().removeAllJobs();
                webClient.close();

                String internalTitle = internalPage.getTitleText();
                System.out.println("Page Title: " + internalTitle);

                List<?> internalAnchors = internalPage.getByXPath("//div[@class='postcardleft']");
                System.out.println(internalAnchors.size());

                String date = ((HtmlListItem) internalPage.getByXPath(".//li").get(0)).getTextContent();
                date = date.substring(6, date.length());
                System.out.println(date);

                String town = ((HtmlListItem) internalPage.getByXPath(".//li").get(2)).getTextContent();
                town = town.substring(6, town.length());
                System.out.println(town);

                String website = ((HtmlListItem) internalPage.getByXPath(".//li").get(3)).getTextContent();
                website = website.substring(9, website.length());
                System.out.println(website);

                String styles = ((HtmlListItem) internalPage.getByXPath(".//li").get(4)).getTextContent();
                styles = styles.substring(8, styles.length());
                System.out.println(styles);

                String description = ((HtmlDivision) internalPage.getByXPath(".//div[@class='scroll-pane2']").get(0)).getTextContent();
                description = description.trim();
                System.out.println(description);
                BasesDades.inserirEvent(articleTitle,country,town,date,styles,description,"");

            }
            events = BasesDades.totsEvents();

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        int count = 0;
        for(DanceEventItemID event: events){
            System.out.println(count);
            count++;
            System.out.println(event.toString());
        }
        return events;
    }

    @CrossOrigin
    @RequestMapping(path = "/events")
    @ResponseBody
    public List<DanceEventItemID> getEvents() {
        List<DanceEventItemID> events;
        events = BasesDades.totsEvents();
        for(DanceEventItemID event: events){
            System.out.println(event.toString());
        }
        return events;
    }
    @CrossOrigin
    @RequestMapping(path = "/item/{id}")
    @ResponseBody
    public List<DanceEventItemID> getItem(@PathVariable(value="id") String id) {
    // fetch from database to get item with speceific id
    // return item
        System.out.println("a");
        List<DanceEventItemID> events = new ArrayList<>();
        events = BasesDades.unEvent(id);
        return events;
    }

    //Profile data
    @CrossOrigin
    @RequestMapping(path="/profile/{username}")
    @ResponseBody
    public Profile getProfile(@PathVariable(value="username") String username) {
        Profile profile = BasesDades.getUser(username);
        return profile;
    }


    //Edit profile
    @CrossOrigin
    @RequestMapping(path = "/saveProfile", method = RequestMethod.POST)
    public void process(@RequestBody Profile profile) {
        System.out.println(profile.getUsername());
        System.out.println(profile.getFullName());
        System.out.println(profile.getDate());
        System.out.println(profile.getEmail());
        System.out.println(profile.getGender());
        System.out.println(profile.getCountry());
        System.out.println(profile.getLanguage());
        System.out.println(profile.getDescription());
//UPDATE DATABASE SEARCHING WITH USERNAME
        BasesDades.updateUser(profile.getUsername(),profile.getFullName(),profile.getDate(),profile.getEmail(),profile.getGender(),profile.getCountry(),profile.getLanguage(), profile.getDescription());
    }

    @CrossOrigin
    @RequestMapping(path="/addEvent", method = RequestMethod.POST)
    public void addEvent(@RequestBody DanceEventItem newItem) {
        System.out.println(newItem.getTitle());
        System.out.println(newItem.getCountry());
        System.out.println(newItem.getTown());
        System.out.println(newItem.getDate());
        System.out.println(newItem.getStyles());
        System.out.println(newItem.getDescription());
        System.out.println(newItem.getOrganizer());
        BasesDades.inserirEvent(newItem.getTitle(), newItem.getCountry(), newItem.getTown(),newItem.getDate(),newItem.getStyles(),newItem.getDescription(),newItem.getOrganizer());
//UPDATE DATABASE
    }

    @CrossOrigin
    @RequestMapping(path="/insertUser", method = RequestMethod.POST)
    public void insertUser(@RequestBody Profile profile) {
        System.out.println(profile.getUsername());
        System.out.println(profile.getFullName());
        System.out.println(profile.getDate());
        System.out.println(profile.getEmail());
        System.out.println(profile.getGender());
        System.out.println(profile.getCountry());
        System.out.println(profile.getLanguage());
        BasesDades.insertUser(profile.getUsername(),profile.getFullName(),profile.getDate(), profile.getEmail(), profile.getGender(),profile.getCountry(),profile.getLanguage());
    }

}