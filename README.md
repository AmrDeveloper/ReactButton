# ReactButton
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3d0f3616d9f544849b2b3190412833ab)](https://app.codacy.com/gh/AmrDeveloper/ReactButton?utm_source=github.com&utm_medium=referral&utm_content=AmrDeveloper/ReactButton&utm_campaign=Badge_Grade_Settings)
![Build](https://github.com/amrdeveloper/reactbutton/actions/workflows/build.yml/badge.svg)
![Min API](https://img.shields.io/badge/Api-%2B15-red.svg)
![Stars](https://img.shields.io/github/stars/AmrDeveloper/ReactButton.svg)
![Forks](https://img.shields.io/github/forks/AmrDeveloper/ReactButton.svg)
![License](https://img.shields.io/github/license/AmrDeveloper/ReactButton.svg)
[![](https://jitpack.io/v/AmrDeveloper/ReactButton.svg)](https://jitpack.io/#AmrDeveloper/ReactButton)

Android Library to make it easy to add ReactButton feature in your app with Multi Reactions like Facebook or Linkedin ...etc, you can add many reactions as you want, you can also split them into number of columns, and also customize the colors and text for each reactions

##### Note
> From version 2.0.0 and above the library will release without default icons to reduce the size and if you want the facebook reactions you can copy them from the example app [here](https://github.com/AmrDeveloper/ReactButton/tree/master/app)

#### Demo :smile:

<p align="center">
<img src="/screenshots/facebook_reacts_demo.gif" height="400px" width="22%"> <img src="/screenshots/dc_reacts_demo.gif" height="400px" width="22%"> <img src="/screenshots/facebook_reacts_2c.png" height="400px" width="22%"> <img src="/screenshots/facebook_reacts_3c.png" height="400px" width="22%">
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
implementation 'com.github.AmrDeveloper:ReactButton:2.0.1'
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

Set the dim amount for the reactions dialog from 0 for no dim to 1 for full dim, the default value is 0
```java
reactButton.setDimAmount(dim);
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

##### For full example please check the example app [here](https://github.com/AmrDeveloper/ReactButton/tree/master/app)
