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
