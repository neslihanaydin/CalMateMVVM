package com.example.calmatemvvm.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.calmatemvvm.model.Quote
import com.example.calmatemvvm.model.User
import javax.inject.Inject

class DatabaseHelper @Inject constructor(
    private val context: Context
) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + CL_U_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CL_U_USERNAME + " TEXT,"
                + CL_U_EMAIL + " TEXT,"
                + CL_U_PASSWORD + " TEXT,"
                + CL_U_FIRST_NAME + " TEXT,"
                + CL_U_LAST_NAME + " TEXT,"
                + CL_U_MOVE_GOAL + " INTEGER,"
                + CL_U_CREATED_AT + " TEXT" + ")")
        db.execSQL(CREATE_USER_TABLE)
        val CREATE_MEDICATION_TABLE = ("CREATE TABLE " + TABLE_MEDICATION + "("
                + CL_M_MEDICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CL_M_USER_ID + " INTEGER,"
                + CL_M_MEDICATION_NAME + " TEXT,"
                + "FOREIGN KEY (" + CL_M_USER_ID + ") REFERENCES " + TABLE_USER + "(" + CL_U_USER_ID + ")" + ")")
        db.execSQL(CREATE_MEDICATION_TABLE)
        val CREATE_MEDICATION_REMINDER_TABLE = ("CREATE TABLE " + TABLE_MEDICATION_REMINDER + "("
                + CL_MR_REMINDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CL_MR_USER_ID + " INTEGER,"
                + CL_MR_MEDICATION_ID + " INTEGER,"
                + CL_MR_DAY_OF_WEEK + " TEXT,"
                + CL_MR_REMINDER_TIME + " TEXT,"
                + CL_MR_IS_ACTIVE + " INTEGER,"
                + "FOREIGN KEY (" + CL_MR_USER_ID + ") REFERENCES " + TABLE_USER + "(" + CL_U_USER_ID + "),"
                + "FOREIGN KEY (" + CL_MR_MEDICATION_ID + ") REFERENCES " + TABLE_MEDICATION + "(" + CL_M_MEDICATION_ID + ")" + ")")
        db.execSQL(CREATE_MEDICATION_REMINDER_TABLE)
        val CREATE_MEDITATION_TABLE = ("CREATE TABLE " + TABLE_MEDITATION + "("
                + CL_MD_MEDITATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CL_MD_TITLE + " TEXT,"
                + CL_MD_DESCRIPTION + " TEXT,"
                + CL_MD_DURATION + " INTEGER,"
                + CL_MD_CATEGORY + " TEXT,"
                + CL_MD_PICTURE_URL + " TEXT,"
                + CL_MD_AUDIO_URL + " TEXT" + ")")
        db.execSQL(CREATE_MEDITATION_TABLE)
        val CREATE_MEDITATION_HISTORY_TABLE = ("CREATE TABLE " + TABLE_MEDITATION_HISTORY + "("
                + CL_MH_MEDITATION_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CL_MH_USER_ID + " INTEGER,"
                + CL_MH_MEDITATION_ID + " INTEGER,"
                + CL_MH_LAST_POSITION + " INTEGER,"
                + CL_MH_LAST_PLAYED_AT + " TEXT,"
                + "FOREIGN KEY (" + CL_MH_USER_ID + ") REFERENCES " + TABLE_USER + "(" + CL_U_USER_ID + "),"
                + "FOREIGN KEY (" + CL_MH_MEDITATION_ID + ") REFERENCES " + TABLE_MEDITATION + "(" + CL_MD_MEDITATION_ID + ")" + ")")
        db.execSQL(CREATE_MEDITATION_HISTORY_TABLE)
        val CREATE_FAVORITE_MEDITATION_TABLE = ("CREATE TABLE " + TABLE_FAVORITE_MEDITATION + "("
                + CL_FM_FAVORITE_MEDITATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CL_FM_USER_ID + " INTEGER,"
                + CL_FM_MEDITATION_ID + " INTEGER,"
                + CL_FM_FAVORITED_AT + " TEXT,"
                + "FOREIGN KEY (" + CL_FM_USER_ID + ") REFERENCES " + TABLE_USER + "(" + CL_U_USER_ID + "),"
                + "FOREIGN KEY (" + CL_FM_MEDITATION_ID + ") REFERENCES " + TABLE_MEDITATION + "(" + CL_MD_MEDITATION_ID + ")" + ")")
        db.execSQL(CREATE_FAVORITE_MEDITATION_TABLE)
        val CREATE_FAVORITE_QUOTES_TABLE = ("CREATE TABLE " + TABLE_FAVORITE_QUOTES + "("
                + CL_FQ_FAVORITE_QUOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CL_FQ_USER_ID + " INTEGER,"
                + CL_FQ_QUOTE + " TEXT,"
                + CL_FQ_QUOTE_AUTHOR + " TEXT,"
                + CL_FQ_FAVORITED_AT + " TEXT,"
                + "FOREIGN KEY (" + CL_FQ_USER_ID + ") REFERENCES " + TABLE_USER + "(" + CL_U_USER_ID + ")" + ")")
        db.execSQL(CREATE_FAVORITE_QUOTES_TABLE)
        val CREATE_NOTIFICATION_TABLE = ("CREATE TABLE " + TABLE_NOTIFICATION + "("
                + CL_N_NOTIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CL_N_USER_ID + " INTEGER,"
                + CL_N_NOTIFICATION_MESSAGE + " TEXT,"
                + CL_N_NOTIFICATION_TIME + " TEXT,"
                + CL_N_IS_READ + " INTEGER,"
                + "FOREIGN KEY (" + CL_N_USER_ID + ") REFERENCES " + TABLE_USER + "(" + CL_U_USER_ID + ")" + ")")
        db.execSQL(CREATE_NOTIFICATION_TABLE)
        initData(db)
    }

    private fun initData(db: SQLiteDatabase) {
        val cv = ContentValues()
        addTestUser(cv, db)
        addTestMedication(cv, db)
        addTestMeditation(cv, db)
        cv.clear()
    }

    private fun addTestUser(cv: ContentValues, db: SQLiteDatabase) {
        cv.put(CL_U_USERNAME, "alice@mail.com")
        cv.put(CL_U_EMAIL, "alice@mail.com")
        cv.put(CL_U_PASSWORD, "123456")
        cv.put(CL_U_FIRST_NAME, "Alice")
        cv.put(CL_U_LAST_NAME, "Smith")
        cv.put(CL_U_MOVE_GOAL, 0)
        cv.put(CL_U_CREATED_AT, "2020-01-01 00:00:00")
        db.insert(TABLE_USER, null, cv)
        cv.clear()
        cv.put(CL_U_USERNAME, "bob@mail.com")
        cv.put(CL_U_EMAIL, "bob@mail.com")
        cv.put(CL_U_PASSWORD, "123456")
        cv.put(CL_U_FIRST_NAME, "Bob")
        cv.put(CL_U_LAST_NAME, "Smith")
        cv.put(CL_U_MOVE_GOAL, 0)
        cv.put(CL_U_CREATED_AT, "2020-01-01 00:00:00")
        db.insert(TABLE_USER, null, cv)
        cv.clear()
    }

    private fun addTestMedication(cv: ContentValues, db: SQLiteDatabase) {
        cv.put(CL_M_USER_ID, 1)
        cv.put(CL_M_MEDICATION_NAME, "Medication 1")
        db.insert(TABLE_MEDICATION, null, cv)
        cv.clear()
        cv.put(CL_M_USER_ID, 1)
        cv.put(CL_M_MEDICATION_NAME, "Medication 2")
        db.insert(TABLE_MEDICATION, null, cv)
        cv.clear()
        cv.put(CL_M_USER_ID, 2)
        cv.put(CL_M_MEDICATION_NAME, "Medication 3")
        db.insert(TABLE_MEDICATION, null, cv)
        cv.clear()
    }

    private fun addTestMeditation(cv: ContentValues, db: SQLiteDatabase) {
        cv.put(CL_MD_TITLE, "1 Minute Calm")
        cv.put(
            CL_MD_DESCRIPTION,
            "Find peace in just one minute with our calming meditation video, helping you relax and center yourself amidst the busyness of life."
        )
        cv.put(CL_MD_DURATION, 1)
        cv.put(CL_MD_CATEGORY, "Quick")
        cv.put(CL_MD_PICTURE_URL, "Meditation 1 Picture URL")
        cv.put(CL_MD_AUDIO_URL, "Meditation 1 Audio URL")
        db.insert(TABLE_MEDITATION, null, cv)
        cv.clear()
        cv.put(CL_MD_TITLE, "Morning Meditation")
        cv.put(
            CL_MD_DESCRIPTION,
            "Begin your day with centeredness and a calm heart, ready to seize the endless possibilities ahead."
        )
        cv.put(CL_MD_DURATION, 10)
        cv.put(CL_MD_CATEGORY, "Daily")
        cv.put(CL_MD_PICTURE_URL, "Meditation 2 Picture URL")
        cv.put(CL_MD_AUDIO_URL, "Meditation 2 Audio URL")
        db.insert(TABLE_MEDITATION, null, cv)
        cv.clear()
        cv.put(CL_MD_TITLE, "1 Minute Breath Exercise")
        cv.put(
            CL_MD_DESCRIPTION,
            "Experience instant calm and clarity with our 1 Minute Breath Exercise video, as you synchronize your breath and unlock a moment of profound relaxation within."
        )
        cv.put(CL_MD_DURATION, 1)
        cv.put(CL_MD_CATEGORY, "Breath")
        cv.put(CL_MD_PICTURE_URL, "Meditation 3 Picture URL")
        cv.put(CL_MD_AUDIO_URL, "Meditation 3 Audio URL")
        db.insert(TABLE_MEDITATION, null, cv)
        cv.clear()
    }

    // set user move goal
    fun setUserMoveGoal(userId: Int, moveGoal: Int): String {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(CL_U_MOVE_GOAL, moveGoal)
        db.update(
            TABLE_USER,
            cv,
            CL_U_USER_ID + " = ?",
            arrayOf(userId.toString())
        )
        return "Move goal updated to $moveGoal"
    }

    fun getUserMoveGoal(userId: Int): Int{
        val db = this.readableDatabase
        val columns = arrayOf(
            CL_U_MOVE_GOAL
        )
        val selection = CL_U_USER_ID + " = ?"
        val selectionArgs = arrayOf(userId.toString())
        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null)
        var moveGoal = 0
        if (cursor.moveToFirst()) {
            moveGoal = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return moveGoal
    }

    // add a user
    fun addUser(
        username: String?,
        email: String?,
        date: String?,
        password: String?,
        firstName: String?,
        lastName: String?
    ): String {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(CL_U_USERNAME, username)
        cv.put(CL_U_EMAIL, email)
        cv.put(CL_U_PASSWORD, password)
        cv.put(CL_U_FIRST_NAME, firstName)
        cv.put(CL_U_LAST_NAME, lastName)
        cv.put(CL_U_MOVE_GOAL, 0)
        cv.put(CL_U_CREATED_AT, date)
        val result = db.insert(TABLE_USER, null, cv)
        return if (result == -1L) {
            "Failed to add user"
        } else {
            "User added successfully"
        }
    }

    // get a user by email
    fun getUserByEmail(email: String): User? {
        val db = this.readableDatabase
        val columns = arrayOf(
            CL_U_USER_ID,
            CL_U_USERNAME,
            CL_U_EMAIL,
            CL_U_PASSWORD,
            CL_U_FIRST_NAME,
            CL_U_LAST_NAME,
            CL_U_MOVE_GOAL,
            CL_U_CREATED_AT
        )
        val selection = CL_U_EMAIL + " = ?"
        val selectionArgs = arrayOf(email)
        var user : User? = null
        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null)
        if (cursor.moveToFirst()) {
            user = User(email,email, cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), null)
        }
        cursor.close()
        db.close()
        return user
    }



    fun getUserIdByEmail(email: String): Int {
        val db = this.readableDatabase
        val columns = arrayOf(
            CL_U_USER_ID,
            CL_U_USERNAME,
            CL_U_EMAIL,
            CL_U_PASSWORD,
            CL_U_FIRST_NAME,
            CL_U_LAST_NAME,
            CL_U_MOVE_GOAL,
            CL_U_CREATED_AT
        )
        val selection = CL_U_EMAIL + " = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null)
        var userId = 0
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return userId
    }

    fun updateUserPassword(email: String, newPassword: String): Int{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(CL_U_PASSWORD, newPassword)
        val result = db.update(
            TABLE_USER,
            cv,
            CL_U_EMAIL + " = ?",
            arrayOf(email.toString())
        )
        return result
    }

    // add a medication
    fun addMedication(userId: Int, medicationName: String?): String {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(CL_M_USER_ID, userId)
        cv.put(CL_M_MEDICATION_NAME, medicationName)
        val result = db.insert(TABLE_MEDICATION, null, cv)
        return if (result == -1L) {
            "Failed to add medication"
        } else {
            "Medication added successfully"
        }
    }

    // add a medication reminder
    fun addMedicationReminder(
        userId: Int,
        medicationId: Int,
        dayOfWeek: String?,
        reminderTime: String?,
        isActive: Int
    ): String {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(CL_MR_USER_ID, userId)
        cv.put(CL_MR_MEDICATION_ID, medicationId)
        cv.put(CL_MR_DAY_OF_WEEK, dayOfWeek)
        cv.put(CL_MR_REMINDER_TIME, reminderTime)
        cv.put(CL_MR_IS_ACTIVE, isActive)
        val result = db.insert(TABLE_MEDICATION_REMINDER, null, cv)
        return if (result == -1L) {
            "Failed to add medication reminder"
        } else {
            "Medication reminder added successfully"
        }
    }

    // add meditation to user's favorite
    fun addFavoriteMeditation(userId: Int, meditationId: Int): String {
        val db = this.writableDatabase
        val cv = ContentValues()
        val currentTimeMillis = System.currentTimeMillis()
        val timestampString = currentTimeMillis.toString()
        cv.put(CL_FM_USER_ID, userId)
        cv.put(CL_FM_MEDITATION_ID, meditationId)
        cv.put(CL_FM_FAVORITED_AT, timestampString)
        val result = db.insert(TABLE_FAVORITE_MEDITATION, null, cv)
        return if (result == -1L) {
            "Failed to add meditation to favorite"
        } else {
            "Meditation added to favorite successfully"
        }
    }

    // add quote to user's favorite
    fun addFavoriteQuote(userId: Int, quote: String, author: String){
        val db = this.writableDatabase
        val cv = ContentValues()
        val currentTimeMillis = System.currentTimeMillis()
        val timestampString = currentTimeMillis.toString()
        cv.put(CL_FQ_USER_ID, userId)
        cv.put(CL_FQ_QUOTE, quote)
        cv.put(CL_FQ_QUOTE_AUTHOR, author)
        cv.put(CL_FQ_FAVORITED_AT, timestampString)
        db.insert(TABLE_FAVORITE_QUOTES, null, cv)
    }

    fun getFavoritesQuotesByUserId(userId: Int): MutableList<Quote> {
        val db = this.readableDatabase
        val columns = arrayOf(
            CL_FQ_FAVORITE_QUOTES_ID,
            CL_FQ_USER_ID,
            CL_FQ_QUOTE,
            CL_FQ_QUOTE_AUTHOR,
            CL_FQ_FAVORITED_AT
        )
        // order by favorited at desc
        val selection = CL_FQ_USER_ID + " = ?"
        val selectionArgs = arrayOf(userId.toString())
        val cursor = db.query(TABLE_FAVORITE_QUOTES, columns, selection, selectionArgs, null, null, null)
        val favoriteQuotes = mutableListOf<Quote>()
        if (cursor.moveToFirst()) {
            do {
                val quote = Quote(
                    cursor.getInt(0),
                    cursor.getString(2),
                    cursor.getString(3)
                )
                favoriteQuotes.add(quote)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return favoriteQuotes
    }

    // check if user exists
    fun checkUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(CL_U_USER_ID)
        val selection = CL_U_USERNAME + " = ?" + " AND " + CL_U_PASSWORD + " = ?"
        val selectionArgs = arrayOf(username, password)
        val count = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null).count
        db.close()
        return if (count > 0) {
            true
        } else {
            false
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS USER")
        db.execSQL("DROP TABLE IF EXISTS MEDICATION")
        db.execSQL("DROP TABLE IF EXISTS MEDICATION_REMINDER")
        db.execSQL("DROP TABLE IF EXISTS MEDITATION")
        db.execSQL("DROP TABLE IF EXISTS MEDITATION_HISTORY")
        db.execSQL("DROP TABLE IF EXISTS FAVORITE_MEDITATION")
        db.execSQL("DROP TABLE IF EXISTS FAVORITE_QUOTES")
        db.execSQL("DROP TABLE IF EXISTS NOTIFICATION")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "calmate.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USER = "USER"
        private const val CL_U_USER_ID = "user_id"
        private const val CL_U_USERNAME = "username"
        private const val CL_U_EMAIL = "email"
        private const val CL_U_PASSWORD = "password"
        private const val CL_U_FIRST_NAME = "first_name"
        private const val CL_U_LAST_NAME = "last_name"
        private const val CL_U_MOVE_GOAL = "move_goal"
        private const val CL_U_CREATED_AT = "created_at"

        // MEDICATION TABLE
        private const val TABLE_MEDICATION = "MEDICATION"
        private const val CL_M_MEDICATION_ID = "medication_id"
        private const val CL_M_USER_ID = "user_id"
        private const val CL_M_MEDICATION_NAME = "medication_name"

        // MEDICATION REMINDER TABLE
        private const val TABLE_MEDICATION_REMINDER = "MEDICATION_REMINDER"
        private const val CL_MR_REMINDER_ID = "reminder_id"
        private const val CL_MR_USER_ID = "user_id"
        private const val CL_MR_MEDICATION_ID = "medication_id"
        private const val CL_MR_DAY_OF_WEEK = "day_of_week"
        private const val CL_MR_REMINDER_TIME = "reminder_time"
        private const val CL_MR_IS_ACTIVE = "is_active"

        // MEDITATION TABLE
        private const val TABLE_MEDITATION = "MEDITATION"
        private const val CL_MD_MEDITATION_ID = "meditation_id"
        private const val CL_MD_TITLE = "title"
        private const val CL_MD_DESCRIPTION = "description"
        private const val CL_MD_DURATION = "duration"
        private const val CL_MD_CATEGORY = "category"
        private const val CL_MD_PICTURE_URL = "picture_url"
        private const val CL_MD_AUDIO_URL = "audio_url"

        // MEDITATION HISTORY TABLE
        private const val TABLE_MEDITATION_HISTORY = "MEDITATION_HISTORY"
        private const val CL_MH_MEDITATION_HISTORY_ID = "meditation_history_id"
        private const val CL_MH_USER_ID = "user_id"
        private const val CL_MH_MEDITATION_ID = "meditation_id"
        private const val CL_MH_LAST_POSITION = "last_position"
        private const val CL_MH_LAST_PLAYED_AT = "last_played_at"

        // FAVORITE MEDITATION TABLE
        private const val TABLE_FAVORITE_MEDITATION = "FAVORITE_MEDITATION"
        private const val CL_FM_FAVORITE_MEDITATION_ID = "favorite_meditation_id"
        private const val CL_FM_USER_ID = "user_id"
        private const val CL_FM_MEDITATION_ID = "meditation_id"
        private const val CL_FM_FAVORITED_AT = "favorited_at"

        // FAVORITE QUOTES TABLE
        private const val TABLE_FAVORITE_QUOTES = "FAVORITE_QUOTES"
        private const val CL_FQ_FAVORITE_QUOTES_ID = "favorite_quotes_id"
        private const val CL_FQ_USER_ID = "user_id"
        private const val CL_FQ_QUOTE = "quote"
        private const val CL_FQ_QUOTE_AUTHOR = "quote_author"
        private const val CL_FQ_FAVORITED_AT = "favorited_at"

        // NOTIFICATION TABLE
        private const val TABLE_NOTIFICATION = "NOTIFICATION"
        private const val CL_N_NOTIFICATION_ID = "notification_id"
        private const val CL_N_USER_ID = "user_id"
        private const val CL_N_NOTIFICATION_MESSAGE = "notification_message"
        private const val CL_N_NOTIFICATION_TIME = "notification_time"
        private const val CL_N_IS_READ = "is_read"
    }
}