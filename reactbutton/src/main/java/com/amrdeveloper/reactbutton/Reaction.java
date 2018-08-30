package com.amrdeveloper.reactbutton;

/**
 * Model Class to save one React Attribute
 */
public class Reaction {

    /**
     * ReactButton Text for this Reaction
     */
    private String reactText;

    /**
     * ReactButton Type for this Reaction
     */
    private String reactType;

    /**
     * ReactButton TextColor value for this Reaction
     */
    private String reactTextColor;

    /**
     * ReactButton Icon id value for this Reaction
     */
    private int reactIconId;

    /*
     * This Constructor for default state because React type not equal react Text
     * for example in library default state text is 'like' but type is 'default'
     */
    public Reaction(String reactText, String reactType, String reactTextColor, int reactIconId) {
        this.reactText = reactText;
        this.reactType = reactType;
        this.reactTextColor = reactTextColor;
        this.reactIconId = reactIconId;
    }

    /*
     * Constructor for all Reaction that text is equal type
     * for example in like state text is 'like' and type is 'like' also
     */
    public Reaction(String reactText, String reactTextColor, int reactIconId) {
        this.reactText = reactText;
        this.reactType = reactText;
        this.reactTextColor = reactTextColor;
        this.reactIconId = reactIconId;
    }

    public String getReactText() {
        return reactText;
    }

    public String getReactType() {
        return reactType;
    }

    public String getReactTextColor() {
        return reactTextColor;
    }

    public int getReactIconId() {
        return reactIconId;
    }

    /**
     * @param object : Reaction object
     * @return : true if new Reaction type equal current Reaction
     */
    @Override
    public boolean equals(Object object) {
        //Assert that obj type is Reaction
        if (object instanceof Reaction) {
            //Cast Object to Reaction
            Reaction react = (Reaction) object;
            //if react type equal current Reaction type
            return react.getReactType().equals(reactType);
        }
        return false;
    }
}

