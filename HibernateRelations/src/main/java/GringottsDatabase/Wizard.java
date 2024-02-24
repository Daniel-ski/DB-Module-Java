package GringottsDatabase;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wizards")
public class Wizard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name",length = 60)
    private String lastName;

    @Column(length = 1000,columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false)
    private int age;

    @OneToOne
    @JoinColumn
    private MagicWand magicWand;

    @OneToMany
    @JoinTable(name = "wizard_deposits")
    private List<Deposit> deposits;


}
