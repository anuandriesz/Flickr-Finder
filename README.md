# Flickr-Finder

# Requirement

Create a mobile app that displays photos returned from the Flickr search API.

# Technologies used 

- Android SDK
- REST API (Consumed through Retrofit2)
- JSON Parsing (GSON)
- Dependency Injection  (Dagger Hilt)
- Concurrency (Kotlin Coroutines)
- Image display - Glide
- Navigation - Jetpack - navigation


# IDE 
- Android Studio Electric Eel | 2022.1.1


# Features 
- Image search and display 25 images in the screen.
- Image details screen with back navigation feature.


# Future Improvements 
- Save prior search terms and present them as quick search options
    
    Possible solutions 
        According to this app need to clarify the search history requirement.
        For example, if the search terms should only be stored for the current session and not saved 
        across app launches SharedPreferences or SQLite would be a good option.
        But if the search terms should be stored permanently, and you want to query them later, 
        SQLite or Room would be a better option.

- Ability to bookmark and view images offline

- Page results (allowing more than the initial 25 to be displayed)
    
    Possible Solution 
        Can be accommodate a user input option for user to define how many images per page require and that amount 
        can pass as a parameter to the API call (per_page= AMOUNT).



# Notes on API call for testing

 Example Postman script for texting the API call 

 Method: GET
 URL: https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=YOUR_API_KEY&text=horse&tags=horse&per_page=25&extras=url_o,url_q&format=json&nojsoncallback=1
 Headers: None
 Body: None

 Note: Please replace "YOUR_API_KEY" with your own Flickr API key.
 Also you may want to add some query parameters to the url to filter the returned images.

 For example, 
 to filter the images by its license type, you may want to add &license=1,2,3,4,5,6,7,8 to the url.
  (1 = Attribution-NonCommercial-ShareAlike License,
  2 = Attribution-NonCommercial License, 
  3 = Attribution-NonCommercial-NoDerivs License, 
  4 = Attribution License, 
  5 = Attribution-ShareAlike License, 
  6 = Attribution-NoDerivs License, 
  7 = No known copyright restrictions, 
  8 = United States Government Work)


# Reference 
 Android Developer Documentation
 https://developer.android.com/?gclid=Cj0KCQiAt66eBhCnARIsAKf3ZNG1hfxyNidKSJ4aBTiLbSyk1m0b3Sp8BNW9dORrSbFdFi0SsMnxFeUaAp1REALw_wcB&gclsrc=aw.ds
