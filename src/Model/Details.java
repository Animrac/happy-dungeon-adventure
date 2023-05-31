package src.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Details object helps us contain information about the type of hero
 * and their description for the GUI.
 */
public class Details implements Serializable {

    private final StringProperty name;
    private final StringProperty text;

    public Details(String name, String text) {
        this.name = new SimpleStringProperty(name);
        this.text = new SimpleStringProperty(text);
    }

    public String getText() {
        return text.get();
    }
    public String getName() {
        return name.get();
    }
    @Override
    public String toString() {
        return getName();
    }

}