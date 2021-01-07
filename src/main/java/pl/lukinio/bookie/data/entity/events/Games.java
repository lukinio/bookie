package pl.lukinio.bookie.data.entity.events;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "games")
public class Games implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column private LocalDateTime date;
    @Column private String home;
    @Column private String away;
    @Column(name = "H") private Double homeOdd;
    @Column(name = "D") private Double drawOdd;
    @Column(name = "A") private Double awayOdd;

    public Games(LocalDateTime date, String home, String away, Double homeOdd, Double drawOdd, Double awayOdd) {
        this.date = date;
        this.home = home;
        this.away = away;
        this.homeOdd = homeOdd;
        this.drawOdd = drawOdd;
        this.awayOdd = awayOdd;
    }
    public Games(){}

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), home, away, homeOdd, drawOdd, awayOdd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Games game = (Games) o;
        return Objects.equals(home, game.home) && Objects.equals(away, game.away);
    }
    @Override
    public String toString(){
        return String.format("Match{%s vs %s}", home, away);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getHome() {
        return home;
    }
    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }
    public void setAway(String away) {
        this.away = away;
    }

    public Double getHomeOdd() {
        return homeOdd;
    }
    public void setHomeOdd(Double homeOdd) {
        this.homeOdd = homeOdd;
    }

    public Double getDrawOdd() {
        return drawOdd;
    }
    public void setDrawOdd(Double drawOdd) {
        this.drawOdd = drawOdd;
    }

    public Double getAwayOdd() {
        return awayOdd;
    }
    public void setAwayOdd(Double awayOdd) {
        this.awayOdd = awayOdd;
    }
}
