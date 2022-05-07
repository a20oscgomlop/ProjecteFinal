package com.example.WeSwingServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import items.DanceEventItem;

@Controller
@RequestMapping(path="/process")
public class WeSwingService {
	
	@CrossOrigin
	@RequestMapping(path="/show")
	@ResponseBody
	public List<DanceEventItem> info() {
		List<DanceEventItem> events = new ArrayList<>();
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
                
                events.add(new DanceEventItem (articleTitle, articleLink, country, day));
                BasesDades.inserirEvent(articleTitle,articleLink,country,day);
            }


        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
		return events;
	}
	
	@CrossOrigin
	@RequestMapping(path = "/item/{id}")
	public List<DanceEventItem> getItem(@PathVariable(value="id") String id) {
		// fetch from database to get item with speceific id
		// return item
		return null;
		
	}
}
