package ar.edu.itba.webapp.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {
    @Size(min = 6, max = 100)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String username;

    @Size(min = 6, max = 100)
    private String password;

    @NotNull
    private String repeatPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
        checkPassword();
    }

    private void checkPassword() {
        if(this.password == null || this.repeatPassword == null){
            return;
        }else if(!this.password.equals(repeatPassword)){
            this.repeatPassword = null;
        }
    }
}