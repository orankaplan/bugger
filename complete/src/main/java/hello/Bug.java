package hello;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabi on 12/23/2015.
 */
public class Bug {
    String description;
    List<String> blamers = new ArrayList<>();

    public List<String> getBlamers() {
        return blamers;
    }

    public void setBlamers(List<String> blamers) {
        this.blamers = blamers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
