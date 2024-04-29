package UserSystem.domain.entities;

import UserSystem.annotations.email.Email;
import UserSystem.annotations.password.Password;
import UserSystem.constants.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name",length = 30,nullable = false,unique = true)
    @Length(min = 4,max = 30,message = Constants.USERNAME_INCORRECT_LENGTH)
    private String userName;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String fullName;
    @Column(nullable = false,unique = true)
    @Password(  containsDigit = true,
                containsLowerCase = true,
                containsSpecialSymbols = true,
                containsUpperCase = true
             )
    private String password;

    @Column(nullable = false,unique = true)
    @Email()
    private String email;

    @Lob
    @Size(max = 1024 * 1024)
    private byte[] profilePicture;

    @Column(name = "registered_on")
    private LocalDate registeredOn ;
    @Column(name = "last_time_logged_in")
    private LocalDate lastTimeLoggedIn ;

    @Column()
    @Min(value = 1 ,message = Constants.AGE_TOO_LOW)
    @Max(value = 120, message = Constants.AGE_TOO_HIGH)
    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;


    @ManyToOne
    private Town bornTown;


    @ManyToOne
    private Town currentlyLiving;


    @ManyToMany()
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friends_id")
    )
    private Set<User> friends;

    @OneToMany
    private Set<Album> albums;

    public User() {
        this.albums = new HashSet<>();
        this.friends = new HashSet<>();
    }

    public User(String userName, String firstName, String lastName,String password,
                String email,LocalDate registeredOn,
                LocalDate lastTimeLoggedIn, int age, boolean isDeleted) {

        this();

        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = isDeleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDate getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDate lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = true;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentlyLiving() {
        return currentlyLiving;
    }

    public void setCurrentlyLiving(Town currentlyLiving) {
        this.currentlyLiving = currentlyLiving;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public String getFullName() {
        return fullName;
    }
    public String getFullNameAndAge() {
        return String.format(Constants.FULL_NAME_AGE_FORMAT, this.firstName, this.lastName, this.age);
    }

    public void setFullName(String fullName) {
        this.fullName = this.firstName + " " + this.lastName;
    }
}
