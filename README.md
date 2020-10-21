# ReactButton
Android Library written in Java to Create ReactButton with Multi Reactions like Facebook or Linkedin :sunglasses:

![Min API](https://img.shields.io/badge/Api-%2B15-red.svg)
![Stars](https://img.shields.io/github/stars/AmrDeveloper/ReactButton.svg)
![Forks](https://img.shields.io/github/forks/AmrDeveloper/ReactButton.svg)
![License](https://img.shields.io/github/license/AmrDeveloper/ReactButton.svg)

Default Reactions :smile:

<img src="https://i.imgur.com/Cnut0ex.png" width="200">

Custom Reactions :wink:

<img src="https://i.imgur.com/C4srnIt.png" width="200">

Description :

Add ReactButton To Your Current Project :

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
implementation 'com.github.AmrDeveloper.ReactButton:reactbutton:1.0.7'
```
            
Default Case :

    Text = Like 
    Emoji is black Hand
    If User Click on Button it Text Will still like but reaction will be blue hand
    and if user click long on button it will show dialog to choose one reaction from 6 reactions

How To Initializing ReactButton :

```java
ReactButton reactButton = findViewById(R.id.buttonId);
```

Set Six Reactions if you want to change Default Reactions:

```java
reactButton.setReactions(Reaction... reaction);
```

Set Current Reaction:

```java
reactButton.setCurrentReaction(Reaction reaction);
```

Get Current Reaction :

```java
Reaction currentReaction = reactButton.getCurrentReaction();
```

Set Default Reaction:

```java
reactButton.setDefaultReaction(Reaction reaction);
```

Get Default Reaction :

```java
Reaction currentReaction = reactButton.getDefaultReaction();
```

Change Reaction dialog default Style :
```java
reactButton.setReactDialogShape(int styleID);
``` 

Set On Click Listener :

```java
reactButton.setReactClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //Your Code
    }
});
 ```

Set on Long Click Listener :

```java
reactButton.setReactDismissListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {
        //Your Code
        return false;
    }
});
```

Check if current Reaction is Default Reaction:
```java
boolean isDefault = reactButton.isDefaultReaction();
``` 
