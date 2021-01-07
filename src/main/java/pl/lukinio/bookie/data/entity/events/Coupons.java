package pl.lukinio.bookie.data.entity.events;

import javax.persistence.*;

@Entity
public class Coupons {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "userId") private Long userId;
}
