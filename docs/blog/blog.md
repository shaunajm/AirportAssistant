# Blog: Airport Assistant App

**Shauna Moran**

## Blog 1 - 15/10/2018
#### Selecting a project idea

After much deliberation I have finally settled on a project idea. I hope to develop an android application to help people who are stressed by airports get through the experience. I came up with this idea as I myself get nervous in airports. The main feature of my project will utilise AR to detect whether a hand luggage bag is the correct dimensions for the user’s airline. I think this will be the toughest element of the project but I’m looking forward to learning more about it. I have asked Ray Walshe to be my supervisor and he has helped me nail down a final idea over the last number of days. I have submitted my project proposal and am now further investigating my project idea and preparing my proposal presentation.

## Blog 2 - 23/10/2018
#### Preparing for Project Proposal Presentations

I am currently preparing for my project proposal presentation which is tomorrow at 3pm. We have to prepare a 5 minute presentation and will be asked questions following this. I hope my project is approved so that I can get working on my Functional Specification and get into the building of my application.

## Blog 3 - 24/10/2018
#### Project approved

My Project Proposal presentation was today at 3pm. A group of five of us students presented our project ideas for Martin Crane and Charlie Daly. They seemed to like my project idea but expressed their worries that I was taking on a lot of work as my project has a lot of moving parts which must be integrated.

My project was approved with the feedback "Airport Assistant Good idea Issues: - Lot of moving parts (user interface design is key to ensure consistent usability) - Have sth to demo (Bag dimensions with ARSUite could be tough but have a fall-back: standard size)" Thankfully the project was approved but I will take this feedback onboard to assist me with my project in the future.

## Blog 4 - 09/11/2018
#### Meeting with Ray

Today I met with Ray for a supervisor meeting. He had a number of questions about my project and advice. He asked me to figure out what data is obtained from QR codes on boarding passes? To investigate if I will need to obtain Ethical Approval for my project. Draw UI Diagrams to help give myself a better idea of how my application will work. Investigate using Google Calendar and Google Drive to add features to my application and consider using the measuring algorithm to measure liquids.

I also wanted to ask him about using Firebase as a database and he agreed that as I am using a large amount of Google Software, progressing with Firebase made sense. He had read over my Functional Specification which I had emailed him earlier that day and liked it's standard. I uploaded my first draft of my Functional Specification to git today. I just have some minor changes to make before it is completed.

## Blog 5 - 23/11/2018
#### Update on Project Progress

We have a had a large amount of work for our semester one modules to complete so my project work has been hard to keep up with project work. I have investigated the some of topics which Ray had queries about and must arrange a meeting for next week. I have found out what data is taken from the QR codes on the boarding passes using the Google Pay API for Passes.

I also emailed a member of the school ethics committee to ask if I need to obtain Ethical Approval for my project. They said I do indeed need to due to the fact that I will be holding passenger data. I've also made some small changes to my Functional Specification which is now due on the 30th of November due to an extension.

## Blog 6 - 28/10/2018
#### Meeting 2 with Ray

I met with Ray today and we discussed the progress I had made since our last meeting. I informed him taht I wasn't too happy with the progress I had made as I had a lot of assignments at the moment and had not been able to a lot the time I had wished to my project. Ray understood this but recommended that I try to find some time over the next few months to get the bones of my project built that I could develop further at a later time and take some of the pressure off second semester.

 Ray read my Ethical Approval form and signed it which I, immediately after our meeting, scanned and sent off to the School's Ethical Approval committee. Hopefully this will be approved soon.

 As I mentioned earlier, I had completed research on elements of my project which Ray had asked me to look into. We talked about this research and took note of how it will affect future project work.

Overall, Ray was satisfied with my progress and we arranged to try and meet mid- January before semester 2 starts.


## Blog 7 - 03/12/2018
#### Functional Specification Submitted and UI Started

I was delighted that I got the time to spend most of the past weekend working on my project as I had made significant progress with my other assignments. After my meeting with Ray where he advised I get a prototype up and running by mid January which I can then further develop throughout the semester, I decided to begin developing the UI of my application.

Using my previously drawn UI diagrams I determined that at this moment my application will have 23 screens but this could change as I progress through the project. I created 23 java and 23 xml files this weekend and designed the skeleton UIs for 12 of my screens, these include login, home, check-in, security and duty free. I also added a progress bar to all 23 pages.

Functional Specifications were due last Friday and I had it submitted and read over a number of times before this. I am happy with my Functional Spec and am delighted I have made further progress on the project.


## Blog 8 - 14/12/2018
#### Project Progress and Exam Study Period
Unfortunately my project has not received the attention I would have like it to over the last few weeks. I made more progress on my UI any free time but due to the vast number of assignments which we were due at the end of the semester, I have not had an awful lot of that.

Over the next number of weeks, I will try to take some time out of exam study to continue progress on my project to have myself in a good position for the beginning of next semester. My primary goal, as decided by myself and Ray, will be to have a MVP completed for mid-January with my Google APIs set up.

## Blog 9 - 22/01/2019
#### Post Exam Update
Exams went really well but I will need to dedicate more time to my project during the week off to ensure I am at the stage I would like to be before the beginning of semester 2. I have completed a small bit more work on my UI but have not began working on my APIs yet.

Unfortunately I never got a chance to organise a meeting with Ray as I was very busy studying for my exams. I will meet with him during the first week of semester 2 after I have completed some more work on the project.

## Blog 10 - 31/01/2019
#### Busy week off and first week of Semester 2
I am delighted with the progress I have made on my project over the last two weeks. I have the core of my UI set up and all of the buttons are functioning. I have also been working on my Google APIs and have my login set up alongside my Firebase database.

I have also been working on my Google Maps API and nearly have the basic maps functionality working, I just have a few small issues to iron out. From my research of the topic I have learned that I will have to implement a number of Google Maps APIs to achieve the functionality I want in my project. These include the Distance, Directions and Roads APIs. My goal is to work on this functionality in the next number of days.

Unfortunately, I discovered an issue with Google Pay for Passes API. I was under the impression that the API worked in a similar method to the standard Google Pay API but unfortunately it does not. This API is only for the use of airlines such as Ryanair to allow their customers to save their boarding passes to the Google Pay for Passes API and will not allow me to save boarding passes to my application.

This issue will mean that I am not able to display boarding passes in my application and will have come up with another way of taking in the flight information of a user. I am currently planning on asking a user their flight number, which applies to a flight from the same airline at a specific time regardless of day, and what day they are flying. This will involve web scraping. I will look into this during the next week after I get Ray's opinion of the idea.


## Blog 11 - 01/02/2019
#### Meeting 3 with Ray

I met with Ray this morning at 11am to discuss my project. We discussed my progress to date which Ray was happy with. I asked him about my issue with the Google Pay for Passes API. We agreed that web scraping will be the best approach to tackle this issue.

I asked Ray his opinion on when I should begin developing the AR functionality of my application as during my project. During my project proposal presentations Martin Crane had recommended that I leave this functionality to the end as it is best to have a working application that I can demo before I add in what will take it to the next level. Ray agreed with this advice and said I should work on the other functionality first.

We discussed how I could integrate the AR functionality into other aspects of my application. I will look into using the AR to project information about where a user is if they hold up their camera as they pass through the airport.

Ray also recommended that I look into using the Ordance survey API as it might provide more detail from within the airport that may not be available on the Google Maps API. Between now and the next meeting I will working on the Maps functionality, my Ethical Approval documentation and the web scraping functionality.


## Blog 12 - 08/02/2019
#### Weekly Update

This week there was positives and negatives for the Airport Assistant App. Great progress was made on the user interface of the application with back button functionality being added and the user interface itself being restructured. Unfortunately, a setback this week was attempting to get the Google Map APIs working together. I made great progress with the beginning of developing the map functionality and quickly got the map set up and a users location being requested and then shown on the application.

After this functionality I moved onto integrating the Directions API into the project. I am having trouble implementing this. It is difficult to debug as it is not throwing an error. I have noticed that the API is returning no routes and this is therefore resulting in a null pointer which throws an exception.

Instead of getting bogged down with this error, I am going to move on and look at adding read and write functionality to my database. I am also looking into web scraping functionality and will return to the maps functionality on Monday with a fresh pair of eyes.

While researching my maps functionality and how much of this will be necessary in my project, I have decided that I will also implement the Google Places API in the duty free and arrivals sections of my application. This will allow users to see what is around them that they may be interested in as they pass through these two areas.

I met with Ray briefly today to get my ethical approval documentation signed. I completed this documentation throughout the week and this morning uploaded it to my git for approval.


## Blog 13 - 15/02/2019
#### Weekly Update

A good amount of progress was made in developing my application this week considering that week 3 brought about assignment work from two of my other modules and results day.

The focus of this week was...
* writing and reading to and from the Firebase Database
* web scraping
* creating warning messages when a user hasn't filled in a field
* use case testing
* completing some pages such as the Security screen

As I began use case testing this week I discovered a number of bugs throughout the application. I stepped through the application and tried clicking different options on each page. This resulted in the discovery of a number of bugs including the application crashing if a user didn't input certain fields. To resolve these issues I prevented the user from not entering these fields and popped up a message to alert them that certain fields had to be entered. An example of this can be seen here...

![](../media/EmptyFieldError.png)  

The main web scraping functionality implemented this week was that users could input their flight number and I then scraped the Dublin Airport website to get the flight time, destination and airline. The user later confirms their journey and then moves onto progressing through the journey.

*Enter Flight Number*           | *Scraped Flight Information*
:---------------------------------:|:-------------------------------------:
![](../media/InputFlightNumber.png) |![](../media/ScrapedFlightInfo.png)

All of this data is written to the Firebase Database where it can later be read from. This can be seen here....

![](../media/FirebaseDataDisplay.png)

Unfortunately, I did not get to work on the Maps functionality this week. I will be working on this over the next week and will hopefully make significant progress despite being busy with other assignments at the moment. I will also be working on other web scraping functionality.


## Blog 14 - 21/02/2019
#### Ethical Approval Granted

This morning I received an email to inform me that my Ethical Approval submission had been approved.  

![](../media/EthicsApproved.png)

I was delighted to have this completed as once I reach the user testing phase of my project I will need such approval. It is great to have this done early so that I can return to focussing on the developing and testing of my project.

## Blog 15 - 22/02/2019
#### Weekly Update

I am very happy with the progress I have made this week. The biggest headway made this week was with the Google Maps functionality. I decided to face into the issue I was facing first thing on Monday morning and thankfully, I resolved it. It turned out that it was a simple issue with the API key which was easy to fix but difficult to discover. Once this was corrected my application now returned directions for a user's chosen method of transport. This can be seen here...

*Driving*           | *Public Transport*         |  *Cycling*         | *Walking*        
:---------------------------------:|:---------------------------------:|:---------------------------------:|:-------------------------------------:
![](../media/DrivingToAirport.png) |![](../media/PublicTransportToAirport.png)|![](../media/CyclingToAirport.png) |![](../media/WalkingToAirport.png)

Once I had this completed I started to organise myself to establish where I am in terms of my project. Thankfully, I have been keeping a journal of my project where I am consistently keeping track of what I have completed and what I have yet to do. I also track changes in the project and bugs I discover which I must return to. On Monday I am some small things to resolve to have the ability to mark pages as "Complete". Although these pages are not perfect, they are at a standard that can be further enhanced later if I have time. I must be conscious of the time constraint on these project and manage my time appropriately. As you can see, at this time I have "Completed" eight of the pages of my application. The majority of my remaining functionality is down to the Maps functionality, some minor fixes and of course AR. I am excited to tackle AR but have to ensure that I have an application I am confident with before I add on what is, in my opinion, the WOW factor.

![](../media/ProjectJournal.png)

As the map functionality for each of these sections is completely different, I have to spend some time working on the set up of each page. This was what the majority of this week doing. At the moment I have the Maps API, Current Location API and Directions API working on one of these pages, Transport to Airport, and am working on getting this working in Duty Free, Directions to Gate and Arrivals Directions. Once each of these are working I will progress onto adding the Distance Matrix API to all of these pages (as it gives the travel distance and time) and the Places API (which gives local points of interest) to the Duty Free and Arrivals pages. If I have time I will also implement the Roads API (gives information such as speed limits) to the Transport to Airport and Arrivals pages if I have the time.

I also have a number of minor bug fixes and issues to work on over the next week. Finally, I will email Ray this week to organise a meeting.

## Blog 16 - 28/02/2019
#### Meeting 4 with Ray

I met with Ray this morning at 11:15 to discuss the progress of my project. I was proud of the work I had gotten through in the last 4 weeks so was excited to see what he would think of my progress. We discussed my progress and what I plan to complete in the next number of weeks. Ray mentioned that I should outline goals for the AR functionality I wish to add to the application.

 We discussed that I firstly hope to achieve the functionality to measure hand luggage using my application. As the ARCore SDK is currently released in Beta only, this may be a difficult task to achieve. It was for this reason that I implemented the rest of the applications core functionality first so that I could dedicate a vast amount of time to this functionality.

 If I am successful at achieving this functionality and still have some time remaining, I hope to adapt this functionality to possibly measure the containers of liquids which will be passing through security. This could possibly be achieved by getting the area of a container and calculating its rough measurement of millilitres. This would allow the application to warn users if there container is over a certain size.

 I could also possibly explore the possibility of having the AR functionality used with the Google Maps functionality to give directions. I will have to look into the ARCore SDK to see if this functionality is feasible.

 Below is a snippet from my project journal which displays the progress I have made in my project since my last meeting and my goals I wish to accomplish before my next one.

![](../media/blogmeeting4progress.png)

## Blog 17 - 01/03/2019
#### Weekly Update

A vast amount of functionality has been added over the last week. This includes adding the ability for users to input their destination when they arrive at the destination airport. The application then provides the user with directions to this location. As you can see in the screenshots below, these directions are given from Heathrow Airport. Ideally, I would use the users current location and direct from there, but as this would not be possible to demo in this project, I have implemented it in this manner. Similarly to directions to the airport, the user can select their desired mode of transport.

*Arrivals Input*           | *Arrivals Zoom In*         |  *Arrivals Zoom Out*                
:---------------------------------:|:---------------------------------:|:---------------------------------:|
![](../media/ArrivalsDestinationInput.png) |![](../media/ArrivalsZoomIn.png)|![](../media/ArrivalsZoomOut.png)


Secondly, I implemented the directions to gate functionality. As Dublin Airport to not openly provide gate information outside of the airport, users will have to manually input their gate number. They are instructured to check the departure board for their flight. Airport App displays the users flight information to aid in this process. Once the user inputs this gate number they are directed to this gate. In the screenshots below it can be seen that unfortunately this is not currently directing users exactly to their gate. This appears to be due to the number of floors in the airport. We can see that the application is directing the user to an elevator to access their gate's floor. I will have to look into this issue further and will hopefully be able to resolve it. The time taken to get to the gate is so long in this current example as the user is walkiing to their gate and the below screenshot is directing the user from Dublin Airport.


*Gate Number Input*           | *Gate Directions Zoom In*         |  *Gate Directions Zoom Out*                
:---------------------------------:|:---------------------------------:|:---------------------------------:|
![](../media/GateNumberEntry.png) |![](../media/DirectionsToGateZoomIn.png)|![](../media/DirectionsToGateZoomOut.png)

Users are also now presented with the time and the distance it will take to get to the user's goal. This can be seen for each of the four modes of transport below.


*Driving*           | *Public Transport*         |  *Cycling*         | *Walking*        
:---------------------------------:|:---------------------------------:|:---------------------------------:|:-------------------------------------:
![](../media/TimeDrivingToAirport.png) |![](../media/TimePublicTransportToAirport.png)|![](../media/TimeCyclingToAirport.png) |![](../media/TimeWalkingToAirport.png)

Finally, the places api has been added to the Duty Free screen. This allows users to see what is around them in terms of shops, bars, cafes and other services. This can be seen in the example below.


![](../media/DutyFreePlaces.png)

## Blog 18 - 08/03/2019
#### Weekly Update

This week has been paticularly difficult. I have been trying to  make progress with the AR functionality of the application but unfortunately, due to my lack of experience in the area, it has been tough to make sense of. I started off by reading the Google ARCore SDK documentation.

I learned a number of things from this including
* hardware requirements for using ARCore
* software requirements for using ARCore
* how to use the Sceneform project
* steps to set up ARCore

I began setting up my project and immediately noticed some issues I would face throughout the process. The most prominent of these being the API level required to run ARCore on an android device. I have a Samsung S8 which is currently at API level 25. Although the basic functionality of ARCore requires API level 25, using the Google Sceneform project requires API level 27. Unfortunately, my laptop is unable to run an emulator. This means I must use a device with the latest form of android. This has been released in Ireland on the Samsung S9 but not yet on the S8. It is expected to be released in the next month.

I have decided to use the Google Sceneform project as I believe it's the best way to progress with my project as it assists with certain aspects of working with ARCore. Therefore, to combat this issue I will be testing my application on a friend's phone for the time being. This will limit me to running my application at certain times. I will spend other times working on testing and documentation so that I am not wasting time.

Once this issue was dealt with, I began integrating the Google Sceneform project into Airport Assistant. Once again this process threw up a number of issues. The first of these appeared to be an issue in the gradle. In the end I had to git stash and begin integrating the project again as I was dedicating too much time into trying to patch the problem as I could not find the root cause.

I began this set up again and yet again ran into issues. As the Google ARCore SDK is in beta I was always destined to experience issues with it. I am delighted that I got around to this functionality early in the process of developing my application. I am currently facing an issue with the AR functionality crashing. I am currently working on combatting this.

I hope to make some progress on this functionality in the next week. I also aim to set up my testing pipeline and make some progress on both my user manual and technical manual.

## Blog 19 - 15/03/2019
#### Weekly Update

After much debugging, deleting and starting again I finally combatted my issue with setting up the basics of the AR functionality. As you can see in the below screenshot, users are asked to move their phone by the icon below so that the device can begin to get an idea of the area around it. The application then begins to map out the planes it sees using a collection of points.

*Technical Specification*           | *User Manual*
:---------------------------------:|:-------------------------------------:
![](../media/ARScan1.jpg) |![](../media/ARScan2.jpg)

When workig with new, difficult to use software it is hard to feel as if we are always making sufficient progress. But I must remember that I have dedicated a vast amount of time to work on this functionality. As I couldn't work on developing my application at all times, due to the fact that I had to run it on a friends phone, I began working on other aspects of the project such as the documentation.

My technical and user manuals are just about completed. They still need work on aspects such as writing about functionality that has yet to be completed. But I am happy with the progress I have made in this area of my project.

*Technical Specification*           | *User Manual*
:---------------------------------:|:-------------------------------------:
![](../media/techspeccover.png) |![](../media/usermanualcover.png)

I have also been working on testing on over the last week. I spent a good bit of time looking into unit testing. At the current moment, due to the functionality of my application, I am not sure how this or even if this could be applied to this project. I have started working on system testing and if I feel unit testing can be applied at a later date I will work on it then.

Unfortunately, it has been difficult to make a large amount of progress on my project this week due to having a lab exam and two assignments for other modules completed. I have been dedicating as much time as possible into the project and hopefully will be able to further progress next week.

Finally, at the end of the week I got good news. Android Pie was now available on the Samsung S8. I downloaded the update and can now thankfully run my application on my own phone.


*Device Updated*           | *Update Information*
:---------------------------------:|:-------------------------------------:
![](../media/UpdateCompleted1.jpg) |![](../media/PieUpdateEdited.jpg)

In the next week I will work on further developing the AR functionality of the application and begin working on system tests.


## Blog 20 - 22/03/2019
#### Weekly Update

This week I decided to face into further developing the AR aspect of the application. I came up with a plan A and a plan B. Plan A would be that I have the application measuring the luggage and returning the dimensions of the suitcase. Plan B would involve projecting a box of the correct dimensions, a user could then check if their bag fixs within this box.

I began by projecting a 3D model of a suitcase. This suitcase could later be resized to match each airlines specific dimensions. With this pushed to git as my basis for what I wanted to develop I began looking into plan A. I decided to implement this I would follow the idea from the below diagram.  

![](../media/DirectionsIdea.png)

As you can see above, a user firstly marks the red point. This is the top left front corner of the bag. This will be clearly explained to users in the screen before the AR Camera. This will act as the central point for the measurements. They then mark the three blue points. One on the bottom left hand corner of the bag, one of the front left hand corner and finally one of the back right corner. The application will then draw a line from the red (central) point to each of these points. This allows us to see the length, width and depth of the hand luggage.

I had a number of issues implementing due to the functionality. I believe this is primarily down to the fact that ARCore is still in Beta and therefore a number of the bugs present in the software have not been dealt with. It is also difficult to work with the technology due to the fact that there is a limited number of documentation.

One of the most problematic issues I faced during this development can be seen below. When I was mapping the points I could see that the lines were not measuring correctly. The line was stretching far beyond both the red and blue coloured balls. To investigate this issue further I logged the length of the lines. Here I noticed that the lines were all measuring at a length of 1 no matter where I placed them. It is also important to note that the measurement unit here is metres. This will be converted into centimetres later. I could tell from this that the values were being round at some point. Once I removed this issue, the correct values were returned and the lines were properly drawn. This can also be seen below.

*Not measuring correctly*           | *Measuring correctly*
:---------------------------------:|:-------------------------------------:
![](../media/DirectionsARTestFail.jpg) |![](../media/DirectionsARTestSuccess.jpg)

***Not measuring correctly***  
![](../media/DirectionsARTestResultFail.png)

***Measuring correctly***
![](../media/DirectionsARTestResultSuccess.png)

With this functionality added, I then had to take these values and compare then to an airlines permitted hand luggage size. I added a number of airlines to my Firebase database to compare my values to. To do this, I went through Dublin Airport's departures for the day to check what airlines appeared to be departing most often. These ten were added to my database and can been seen below. Within the airline I store their permitted hand luggage size. This is a snippet from my database.

![](../media/DatabaseAirline.png)

I then compare the permitted hand luggage dimensions with a user's measured hand luggage dimensions. This was displayed on the AR Results page with a user's pass/fail result. An example of this screen when a user's luggage passes and fails can be seen below.

*Pass*           | *Fail*
:---------------------------------:|:-------------------------------------:
![](../media/PassARTest.png) |![](../media/FailARTest.png)

I am now moving onto adding the option for users to clear the points they have put down if they enter them incorrectly. I am also hoping to add the measurements for each line on the line themselves. I will also work on the AR tutorial screen which comes before the AR functionality. Here I will step users through how to use the AR aspect of the application.

## Blog 21 - 27/03/2019
#### Meeting 5 with Ray

Today I met with Ray to discuss the progress I had making on the project. I had primarily worked on the AR functionality, documentation and testing. I have explained each of these areas in further detaill in the last number of blogs. Ray was happy with my progress. I explained how I branched into documentation when I was waiting on the latest Android update to be available on my phone.

![](../media/blogmeeting_5.jpg)

We discussed my planned work. Ray believes I should look into the AR for liquids functionality in further detail. He recommended that instead of only factoring in cuboids and cyclinders as I had intended, I should investigate using items such as cones for toothpaste bottles. I will have to look into this in before I begin developing the functionality. I will also look into touching up the AR functionality I have already designed, redesigning my user interface, adding some minor functionality such as web scraping and pop ups, the google drive functionality I had previously discussed, documentation, testing and general code refactoring.

Ray said I should also begin looking into my video walkthrough and presentation.

## Blog 22 - 29/03/2019
#### Weekly Update

The primary focus of the last week was yet again the AR functionality of the application. I have been looking into perfecting the measurements, adding the AR tutorial screen and finally displaying the results. Alongside this, I have worked on certain aspects of the User Interface such as making certain options become invisible unless the user selects a radio button to activate them, adding the security wait time functionality, adding the onboard screen and adding sign out functionality.

I will firstly delve into the AR Tutorial screen. This screen comes before the AR functionality and guides the user on how to use it. A screenshot can be seen below. The user is shown an image of how to scan their baggage and also provided with step by step instructions. From this screen the user can decide to skip the AR baggage scan or can progress to the bag scan.

![](../media/ARTutorial2.jpg)

Once a user progresses to the baggage scan, they will map out there points as instructed. Once the user has placed these points they will stay in place. I plan to add the functionality for a user to clear the points they have placed later. Below we can see three screenshots of the points placed on a suitcase from a number of different angles.

*AR Scan Front*           | *AR Scan Side* |*AR Scan from above*
:---------------------------------:|:-------------------------------------:|:-------------------------------------:
![](../media/measureFront.jpg) |![](../media/measureSide.jpg)|![](../media/measureTop.jpg)

Once a user is happy with the points they have placed the can progress into the next screen. Here they will be provided with the measurements of their hand luggage compared to the dimensions allowed by their airline. They are then informed whether their bag has passed or failed this AR check. As you can see below, this AR measurement estimates the length of the bag is 49cm. When the bag was measured with a measuring tape we can also see that measurement is in fact 49cm.

Here we can also see that our bag's dimensions are 49cm x 26cm x 17cm and the airline's bag dimensions are 55cm x 40cm x 24cm. As our bag is smaller than the maximum dimensions our bag has passed the AR check.

*Bag with measuring tape*           | *AR Results*
:---------------------------------:|:-------------------------------------:
![](../media/measureTapeLength1.jpg) |![](../media/measureResults.jpg)

As Dublin Airport does not provide security wait time information I decided to scrape this information from a different airport as a proof of concept. This will show how the functionality would work if this information was available. I ended up scraping this information from Stuttgart Airport. This screen can be seen below.

![](../media/securityWaitTime.jpg)

I also added the onboard screen. This displays the user's destination and flight time. It also displays a link to the user's airline's onboard information. I investigated scraping this information and displaying it on this screen but as there is a vast amount of information, including food menus, entertainment and much more, it would not be practical to display it.  

![](../media/onboardScreen.jpg)

Here we can see the sign out button which is now present on the home screen. Here a user can click this button to log out of the application.

![](../media/signOutButton.jpg)

Finally I have added the functionality that certain fields remain invisible until the user selects an option that would deem them necessary. An example of this is the transport to airport screen which can be seen below. If a user wants directions to the airport the next field becomes available. If a user does not want directions then this field remains hidden. This is as this is unnecessary for the user to see if they do not want directions. This has been implemented on a number of screens in an attempt to benefit the usability of the app.

*Information invisible*           | *Information now visible*
:---------------------------------:|:-------------------------------------:
![](../media/radioNoClick.jpg) |![](../media/radioYesClick.jpg)

## Blog 23 - 05/04/2019
#### Weekly Update

The beginning of this week was spent redesigning the UI of the application. I had initially designed the bare bones of the app so the UI was extremely plain and needed more work. The general idea behind this design was to add icons and make the UI as readable, easy to use and visually appealing as possible. As an example we can see below the AR Results page before and after I redesigned my UI.

*Old AR Results Screen*           | *New AR Results Screen*
:---------------------------------:|:-------------------------------------:
![](../media/measureResults.jpg) |![](../media/arResultsNew.jpg)

Below are some screenshots of the new design of a number of these pages.

*New Confirm Journey Screen*           | *New Check-In Luggage Screen*|*New Arrivals Directions Screen*
:---------------------------------:|:-------------------------------------:|:---------------------------------:
![](../media/confirmJourney.jpg) |![](../media/checkInQuestion.jpg)|![](../media/destinationDirections.jpg)
