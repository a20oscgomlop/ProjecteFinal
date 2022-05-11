package items;

public class DanceEventItemID {

    private int id;
    private String title;
    private String country;
    private String town;
    private String date;
    private String styles;
    private String description;
    private String organizer;

    public DanceEventItemID (int id, String title, String country, String town, String date, String styles, String description, String organizer) {
        this.id=id;
        this.title = title;
        this.country = country;
        this.town = town;
        this.date = date;
        this.styles = styles;
        this.description = description;
        this.organizer = organizer;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Event [id=" + id +", title=" + title + ", country=" + country + ", town=" + town + ", date=" + date + ", styles=" + styles + ", description=" + description + "]";
    }



}
