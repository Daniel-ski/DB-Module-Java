package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.time.LocalDate;


    @Entity(name = "accounts")
    public class Account {

        @Id
        @Column(name = "id")
        private long id;

        @Column(name = "name")
        private String name;

        @Column(name = "age")
        private Integer age;

        @Column(name = "countOfFollowers")
        private Integer countOfFollowers;

        @Column(name = "createdOn")
        private LocalDate createdOn;

        @Column(name = "lastLogged")
        private LocalDate lastLogged;

        @Column(name = "nickname")
        private String nickname;

        public Account(String name, LocalDate createdOn, Integer age) {
            this.name = name;
            this.createdOn = createdOn;
            this.age = age;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getCountOfFollowers() {
            return countOfFollowers;
        }

        public void setCountOfFollowers(Integer countOfFollowers) {
            this.countOfFollowers = countOfFollowers;
        }

        public LocalDate getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(LocalDate createdOn) {
            this.createdOn = createdOn;
        }

        public LocalDate getLastLogged() {
            return lastLogged;
        }

        public void setLastLogged(LocalDate lastLogged) {
            this.lastLogged = lastLogged;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
