# EsperAssigment Android Application

EsperAssigment android application mainly displays the different model's mobile list and their features.

## Description

EsperAssigment android application mainly displays the different model's mobile list and their features. From which the user can select his own configuration but the response we get from the URL consists of an exclusion list from which we will check whether the user selected and the exclusion matches or not. If it doesn't match we will display the data on the new screen.

URL : [https://my-json-server.typicode.com/mhrpatel12/esper-assignment/db](https://my-json-server.typicode.com/mhrpatel12/esper-assignment/db)

The project is divided into three phases of development :

 Project Creation and Initial SetUp\
\
   In this phase of development, I planned on architecture and its components used in the development of the android application and project setup. I used MVVM architecture, Room for ORM  & SQLite for Android Internal Database for the android application.\
\
 -> Developing UI & Adapter's\
\
In this phase of development, I designed UI and Wireframe for the given requirement and add adapters for the Spinners. By which the data is populated in the spinner and the user can browse through the options.\
\
 -> Final Commit\
\
In this phase of development, we use Retrofit for calling the API for getting the data & then converting into mutable live data and then later saving it into the database. Whenever there is no network we use it to sync up this data into the spinner. After that, we are applying conditions that check whether the user-selected matches with the exclusion list or not. If it doesn't match then we show the data on the new screen.


## Installation
Clone this repository and import into **Android Studio**
```bash
https://github.com/narsimha-na/NA-Notes.git
```


## ScreenShots

<div>
  
  <img style="display: inline-block; margin: 10px;" height="370px" width="210px" src="https://github.com/narsimha-na/Esper-Assigment/blob/master/1.jpeg">
  <img style="display: inline-block;" height="370px" width="210px" src="https://github.com/narsimha-na/Esper-Assigment/blob/master/2.jpeg">
</div>
<br>

## Authors

[Subbagari Narsimha Reddy](https://www.linkedin.com/in/narsimha-reddy-3976b113a/)
