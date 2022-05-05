package com.be.ac.umons.babaisyou;

import com.be.ac.umons.babaisyou.rules.Rule;
import javafx.scene.Group;

import java.util.ArrayList;

public interface theRules {

    public ArrayList<Rule> getRules();
    public void appliRules(Group root);
}
