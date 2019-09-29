# Singapore Mobile Data Usage Stats APP

This App has been created to show the latest stats of the Mobile data that being used by singapore population for past few years.

There are two screens in the the App one is the launcher activity while other is the list activity that shows all the data as per the year

This code has been written in Kotlin for Android using Androidx components in MVVM Pattern.

This code has the unittests written using junit and Ui test cases written using espresso.

This app also consits of a local database so when ever there is no service available it looks into database and shows the list.

The libraries or languages that are used for this project are
```
1. Kotlin
2. Retrofit - service calls
3. junit - Testing
4. espresso - ui Testing
5. Mockito - Testing
6. Gson
7. Rxjava
8. RecyclerViews
9. cardViews
10. androidx components
11. coroutines
12. anko
13. Room - For Local Data Base.
```

The App fetches the data from the folloeing URL
```
***mobile-data-usage***
https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f
```
