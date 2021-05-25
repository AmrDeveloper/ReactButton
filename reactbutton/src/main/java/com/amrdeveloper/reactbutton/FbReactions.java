package com.amrdeveloper.reactbutton;

final class FbReactions {

    private static Reaction defaultReact = new Reaction(
            ReactConstants.LIKE,
            ReactConstants.DEFAULT,
            ReactConstants.GRAY,
            R.drawable.ic_gray_like);

    private static Reaction[] reactions = {
            new Reaction(ReactConstants.LIKE,ReactConstants.BLUE,R.drawable.ic_like),
            new Reaction(ReactConstants.LOVE,ReactConstants.RED_LOVE,R.drawable.ic_heart),
            new Reaction(ReactConstants.SMILE,ReactConstants.YELLOW_WOW,R.drawable.ic_happy),
            new Reaction(ReactConstants.WOW,ReactConstants.YELLOW_WOW,R.drawable.ic_surprise),
            new Reaction(ReactConstants.SAD,ReactConstants.YELLOW_HAHA,R.drawable.ic_sad),
            new Reaction(ReactConstants.ANGRY,ReactConstants.RED_ANGRY,R.drawable.ic_angry),
            new Reaction(ReactConstants.ANGRY,ReactConstants.RED_ANGRY,R.drawable.ic_angry),
    };


    static Reaction getDefaultReact() {
        return defaultReact;
    }

    static Reaction[] getReactions() {
        return reactions;
    }
}
