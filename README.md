
  <h1>CalMate</h1>

  <p>CalMate is an application that enables users to manage their physical and mental health with its user-friendly interface. In today's fast-paced world, where people struggle to find time for themselves, mental health issues have become prevalent. CalMate is designed with motivational features to encourage users to dedicate a few minutes each day for themselves. Additionally, if users have regular medication needs, the app provides reminders to ensure timely intake.</p>

# Project Overview

The application has been designed and developed to meet specific requirements, incorporating custom components and code segments. Furthermore, I have integrated two external APIs within the application: the Google FIT API and ZenQuotes.

## Google FIT API

The Google FIT API plays a significant role in the application, allowing us to collect user activity data, thereby assisting in monitoring their health and fitness activities. For more information on the Google FIT API, please visit the [Google FIT API Documentation](https://developers.google.com/fit/overview).

## ZenQuotes API

ZenQuotes serves to enrich the content of our application by providing users with inspirational quotes. For additional details, please refer to the [ZenQuotes API Documentation](https://docs.zenquotes.io/zenquotes-documentation/).

## Demo Video

Check out the demo video of the app on YouTube: [Demo Video](https://youtu.be/LI8nKEiElIY).

  <h2>USER MANUAL</h2>

  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/d970721a-eae3-4996-8411-941937a821aa" alt="Welcome Page" align="left" width="200" height="450">
  
  <p>This report provides an overview of the usage and features of the CalMate application as presented to the end-users.</p>

  <p>It's essential that the Google FIT application is installed on the device to ensure proper data collection.</p>

  <p>The application requests the following permissions from users:</p>

  <ul>
    <li>When the application is first launched, it will request location permission.</li>
    <li>Subsequently, users will need to log into their Google account to use the application because of the Daily Step Counter.</li>
    <li>As users proceed, they'll be prompted to grant Calendar access when adding a medication reminder. The application will then create an event with the provided medication information, sending reminders to users.</li>
  </ul>

  <p>The Welcome Page's primary objective is to greet users, acquaint them with the app's purpose, and emphasize the benefits they can derive from its use.</p>

  <br/><br/><br/><br/><br/><br/>

  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/71fcf0e0-6d4d-4ec3-a1b0-a98f36bd5c51" alt="Onboarding Page 1" align="left" width="200" height="450">
  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/6f4a225f-d106-4d80-bd3d-8f48d30a30bd" alt="Onboarding Page 2" align="left" width="200" height="450">
  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/9737f1a4-3d14-400b-8693-3e83172fb132" alt="Onboarding Page 3" align="left" width="200" height="450">

  <p>The Onboarding Page holds a significant role within the CalMate app, responsible for acquainting users with the essential features and advantages of the application. It acts as a user-friendly guide during the initial interactions, offering a smooth pathway for users to grasp the app's offerings and understand how it can enrich their experience.</p>

  <br/><br/>

  <p>The Onboarding Page extends a warm welcome to new users, creating an initial impression that shapes the tone of their entire experience.</p>

  <br/><br/><br/><br/><br/><br/>

  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/933138f9-34c9-4d02-9f86-54a910bb10dc" alt="Home Page" align="left" width="200" height="450">

  <p>The Home Page presents a user-friendly arrangement that organizes various sections and features:</p>

  <p>The Home Page dynamically displays a welcoming message based on the current time of day, creating a sense of hospitality. It also enhances the personal touch by showing the user's first name.</p>

  <p>Navigation Buttons: This Page incorporates a set of buttons, each accompanied by an icon and label, representing specific aspects of the app:</p>

  <ul>
    <li>Medications: Enables users to manage their medication.</li>
    <li>Daily Goal: Assists users in tracking their daily step goals.</li>
    <li>Meditation: Provides access to meditation resources.</li>
    <li>Positivity: Offers a section related to mood and positive aspects.</li>
    <li>Mood Input: Allows users to express their current emotional state.</li>
  </ul>

  <p>Interactive Cards: The Home Page showcases interactive cards that provide additional information and actions:</p>

  <ul>
    <li>Meditation List: Presents a curated list of recommended meditations.</li>
    <li>Set Goal: Empowers users to set their daily step goals.</li>
    <li>Add Medication: Provides a means for users to include new medications.</li>
  </ul>

  <br/><br/>

  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/0cf84893-601c-41d6-a36d-8673cf09c182" alt="Medication Page 1" align="left" width="200" height="450">
  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/ec8d0050-a37d-4588-a05f-fbd3935b01ad" alt="Medication Page 2" align="left" width="200" height="450">
  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/2a45b374-4713-46d8-9f58-4f98ae37b6ef" alt="Medication Page 3" align="left" width="200" height="450">

  <p>Access to the Medication Page is available through two methods on the Home Page. Firstly, it can be accessed by clicking on the widget labeled "Medications". The second method is by clicking on the Floating Action Button located in the area with a slider.</p>

  <p>It enables users to schedule medication reminders, seamlessly integrating with the device's calendar. This feature empowers users to set up timely alerts for taking their prescribed medications, thereby enhancing health adherence and overall well-being.</p>

  <p>By selecting the days on which they need to be reminded, users can customize their medication schedule. The Add Time button provides users with a means to specify the exact time they wish to receive medication reminders.</p>

  <br/><br/>

  <p>When users tap the Add Medication button, the fragment processes the user's selections and initiates the creation of medication events in the device's calendar.</p>

  <br/><br/>

  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/e1375173-8e7b-4d86-b2a8-ffead9042d63" alt="Fitness Page" align="left" width="200" height="450">

  <p>The "Set a Goal" feature can be accessed through two distinct methods within the application, providing users with the flexibility to set their preferred daily step count. The initial method allows access by clicking a button located on the slider widget situated on the home page, which is labeled with the phrase "Set a goal based on how active you are."</p>

  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/2fcd3987-6f94-4b92-99e7-83355bb81c4e" alt="Set a Goal" align="left" width="200" height="75">

  <p>The second method involves clicking on the 'Daily Steps' button on the home page.</p>


 <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/>
   <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/4482e2ee-afba-440b-99e0-abbc839ec893" alt="Meditation Page" align="left" width="200" height="450">
  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/ed1ec306-04b9-4302-998e-6be90c9a7cce" alt="Meditation Page" align="left" width="200" height="450">

  <p>Users can select a specific meditation session from the list. The user is directed to the Meditation Page, where they can engage in the chosen meditation.</p>

  <p>A user-friendly interface where they can play, pause, seek, and time their meditation sessions. The fragment is designed to provide a seamless and soothing meditation experience by allowing users to listen to guided audio while tracking the progress of their meditation session.</p>

 <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/>
  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/e8f49835-7090-4f51-a44a-c1c3145e22fd" alt="Recommended Meditations" align="left" width="400" height="500">
  <p>The Recommended Meditations Section includes a "View All" button that allows users to navigate to a comprehensive meditation list. When the button is clicked, users are directed to the Meditation Page where they can explore a wider range of meditation sessions.</p>


<br/><br/> <br/><br/> <br/><br/> <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  <img src="https://github.com/neslihanaydin/CalMateMVVM/assets/26707748/47d6beec-8f75-4205-a732-045aa75ba3db" alt="Favorites Page" align="left" width="200" height="450">

  <p>The Favorites Page is designed to display a list of user-selected favorite quotes. The primary purpose of the Favorites Page is to provide users with a dedicated space to view and revisit their favorite quotes. Users can easily access the quotes they have previously marked as favorites, creating a personalized collection of meaningful and inspirational quotes.</p>

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
## Contact

If you have any questions or feedback regarding my project, please feel free to reach out to me:

- Email: [neslihantrpc@gmail.com](mailto:neslihantrpc@gmail.com)
