package arrays.simple.sum;

public class Covid {
    private Long id;
    private String country;
    private int active;
    private int death;
    private int recovered;

    public Covid() {
    }

    public Covid(Long id, String country, int active, int death, int recovered) {
        this.id = id;
        this.country = country;
        this.active = active;
        this.death = death;
        this.recovered = recovered;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public int getActive() { return active; }
    public void setActive(int active) { this.active = active; }
    public int getDeath() { return death; }
    public void setDeath(int death) { this.death = death; }
    public int getRecovered() { return recovered; }
    public void setRecovered(int recovered) { this.recovered = recovered; }
}