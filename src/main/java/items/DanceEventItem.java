package items;

public class DanceEventItem {

	private String title;
	private String link;
	private String country;
	private String date;
	
	public DanceEventItem (String title, String link, String country, String date) {
		this.title = title;
		this.link = link;
		this.country = country;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Event [title=" + title + ", link=" + link + ", country=" + country + ", date=" + date + "]";
	}
	
	
	
}
