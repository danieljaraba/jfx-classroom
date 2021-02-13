package model;

import java.util.ArrayList;

public class Classroom {

    private ArrayList<UserAccount> accounts;

    public Classroom(){
        this.accounts = new ArrayList<UserAccount>();
    }

    public void addUser(String user, String password, String photoUrl, String gender, String career, String birthday, String favoriteBrwsr){
        UserAccount account = new UserAccount(user, password, photoUrl, gender, career, birthday, favoriteBrwsr);
        accounts.add(account);
    }

    public ArrayList<UserAccount> getAccounts(){
        return accounts;
    }
}
