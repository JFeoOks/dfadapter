package jfeoks.old.model;

public class Smile {

    private String smileString = "I'm smiling";

    public String getSmileString() {
        return smileString;
    }

    public void setSmileString(String smileString) {
        this.smileString = smileString;
    }

    @Override
    public String toString() {
        return smileString;
    }
}
