# ReactButton
![Min API](https://img.shields.io/badge/Api-%2B15-red.svg)
![Stars](https://img.shields.io/github/stars/AmrDeveloper/ReactButton.svg)
![Forks](https://img.shields.io/github/forks/AmrDeveloper/ReactButton.svg)
![License](https://img.shields.io/github/license/AmrDeveloper/ReactButton.svg)

Android Library to make it easy to add ReactButton feature in your app with Multi Reactions like Facebook or Linkedin ...etc, you can add many reactions as you want, you can also split them into number of columns, and also customize the colors and text for each reactions

#### Demo :smile:

<p align="center">
<img src="/screenshots/facebook_reacts_demo.gif" height="400px" width="25%"> <img src="/screenshots/dc_reacts_demo.gif" height="400px" width="25%"> <img src="/screenshots/facebook_reacts_portal.png" height="400px" width="25%">
</p>
  
<p align="center">
<img src="/screenshots/facebook_reacts_landscape.png" height="260px">
</p>

#### Documentation:

Add ReactButton To Your Current Project:

Add it in your root build.gradle at the end of repositories
  
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
             
Add the dependency      

```gradle
implementation 'com.github.AmrDeveloper.ReactButton:reactbutton:2.0.0'
```

How To Initializing ReactButton:

```java
ReactButton reactButton = findViewById(R.id.buttonId);
```

Set your Reactions:

```java
reactButton.setReactions(Reaction... reaction);
```

Set Current Reaction:

```java
reactButton.setCurrentReaction(Reaction reaction);
```

Get Current Reaction:

```java
Reaction currentReaction = reactButton.getCurrentReaction();
```

Set Default Reaction:

```java
reactButton.setDefaultReaction(Reaction reaction);
```

Get Default Reaction:

```java
Reaction currentReaction = reactButton.getDefaultReaction();
```

Change Reaction dialog default Style:
```java
reactButton.setReactDialogShape(int styleID);
``` 

Change the number of reactions in the Columns, the default value is all reactions size
```java
reactButton.setDialogColumnsNumber(n);
```

Set setOnReactionChangeListener:

```java
reactButton.setOnReactionChangeListener(new ReactButton.OnReactionChangeListener() {
    @Override
    public void onReactionChange(Reaction reaction) {
        // Code that will execute when the reaction changed
    }
});
 ```

Set OnReactionDialogStateListener:

```java
reactButton.setOnReactionDialogStateListener(new ReactButton.OnReactionDialogStateListener() {
    @Override
    public void onDialogOpened() {
        // Code that will execute when the reaction dialog is opened
    }

    @Override
    public void onDialogDismiss() {
        // Code that will execute after the reaction dialog is dismissed
    }
});
```

Check if current Reaction is Default Reaction:
```java
boolean isDefault = reactButton.isDefaultReaction();
``` 
