package main.Misc;

// A glorified goto statement :)
public class RetryWithStringArgException extends Exception {

    private String string;

    public RetryWithStringArgException(String argument){
        setString(argument);
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
