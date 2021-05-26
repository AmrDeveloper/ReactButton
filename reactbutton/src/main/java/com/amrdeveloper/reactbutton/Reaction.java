package com.amrdeveloper.reactbutton;

import java.util.Arrays;

public class Reaction {

    private final String reactText;

    private final String reactType;

    private final String reactTextColor;

    private final int reactIconId;

    /**
     * This Constructor for default state because React type not equal react Text
     * for example in library default state text is 'like' but type is 'default'
     */
    public Reaction(String reactText, String reactType, String reactTextColor, int reactIconId) {
        this.reactText = reactText;
        this.reactType = reactType;
        this.reactTextColor = reactTextColor;
        this.reactIconId = reactIconId;
    }

    /**
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

    public String getReactTextColor() {
        return reactTextColor;
    }

    public int getReactIconId() {
        return reactIconId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reaction reaction = (Reaction) o;
        return reactIconId == reaction.reactIconId &&
                (reactText != null && reactText.equals(reaction.reactText)) &&
                (reactType != null && reactType.equals(reaction.reactType)) &&
                (reactTextColor != null && reactTextColor.equals(reaction.reactTextColor));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[]{
                reactIconId,
                reactText.hashCode(),
                reactType.hashCode(),
                reactTextColor.hashCode(),
        });
    }
}
