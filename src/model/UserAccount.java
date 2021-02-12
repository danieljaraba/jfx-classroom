package model;

public class UserAccount {

    private String user;
    private String password;
    private String photoUrl;
    private String gender;
    private String career;
    private String birthday;
    private String favoriteBrwsr;

    public UserAccount(String user, String password, String photoUrl, String gender, String career, String birthday, String favoriteBrwsr){
        this.user = user;
        this.password = password;
        this.photoUrl = photoUrl;
        this.gender = gender;
        this.career = career;
        this.birthday = birthday;
        this.favoriteBrwsr = favoriteBrwsr;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getGender() {
        return gender;
    }

    public String getCareer() {
        return career;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getFavoriteBrwsr() {
        return favoriteBrwsr;
    }
}
