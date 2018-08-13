# ReactButton
Android Library By Java to Create ReactButton with 6 Reaction Face 

Description :

Add ReactButton To Your Current Project :

Add it in your root build.gradle at the end of repositories
    
    allprojects {
        repositories {
			maven { url 'https://jitpack.io' }
		}
	}
    
             
Add the dependency      

    implementation 'com.github.AmrDeveloper.ReactButton:app:1.0.6'
            
Default Case :

    Text = Like 
    Emoji is black Hand
    If User Click on Button it Text Will still like but reaction will be blue hand
    and if user click long on button it will show dialog to choose one reaction from 6 reactions

How To Initializing ReactButton :

```java
ReactButton reactButton = findViewById(R.id.buttonId);
```

Set Emoji Type on Button :

```java
   reactButton.setCurrentEmojiType(String reactionType);
   
   Types : 
   ReactButton.DEFAULT
   ReactButton.LIKE
   ReactButton.LOVE
   ReactButton.SMILE
   ReactButton.WOW
   ReactButton.SAD
   ReactButton.ANGRY
```

To Get Current Reaction On Button :

```java
String currentReaction = reactButton.getCurrentReactType();

switch(currentReaction)
{
   case ReactButton.LIKE:
       //Text Is Like , Emoji Is Blue Hand
       break;
       
   case ReactButton.LOVE:
       //Text Is Like , Emoji Is Red Heart
       break;
       
   case ReactButton.SMILE:
        //Text Is Smile , Emoji Is Smile Face
       break;
       
   case ReactButton.WOW:
       //Text Is Wow , Emoji Is Wow Face
       break;
       
   case ReactButton.SAD:
       //Text Is Sad , Emoji Is Dark Hand
       break;
       
   case ReactButton.ANGRY:
       //Text Is Angry , Emoji Is Angry Face
       break; 
       
   default:
       //Text Is Like , Emoji Is Dark Hand
       break;
}
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
  
Change Reatction layout background color :
 ```java
  reactButton.setDialogBackgroundColor(int color);
  ```
  
Change Reaction dialog shape :
 ```java
  reactButton.setReactDialogShape(int shapeID);
  ```
  
