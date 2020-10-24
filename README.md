# SampleRoomImpl
Sample implementation of Room library in Android.
This is a very basic student DB app. It allows you to save student info to the DB using Room API and fetch them as well. Although the info is displayed in the logcat only. 

Room DB has 3 main components - 
Entity - Represents table in the DB
Dao - Interface which is responsible for data operations for a table entity.
An abstract class that extends RoomDatabase class. It holds reference to Entities and Dao in the DB. It also has version number info. 

Creating a RoomDB instance is expensive so it should ideally be done at the Application level. 
