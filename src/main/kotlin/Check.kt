import org.litote.kmongo.*
import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.MongoException
import java.net.UnknownHostException

/*@Author-Sakshi Sinha
* Checking Database connection 
* */


fun main(args: Array<String>) {

    data class User(val Name:String,val Email:String)
    try
    {
        //Connecting to Database
        val mongo = MongoClient("localhost", 27017)

        val db = mongo.getDB("Java")

        val table = db.getCollection("User")
        println("Connection created")

        //Reflection example
        val person = User("Sakshi","shrishtisakshi311@gmail.com")

        //Insert document
        val document = BasicDBObject()
        document.put("Name","Prisha")
        document.put("Email","kushti@preesha.com")
        table.insert(document)

        //Using KMongo to execute query--Code May still need refactoring
        val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
        val database = client.getDatabase("Kandle") //normal java driver usage
        val col = database.getCollection<User>()
        val names : User? = col.findOne(User::Name eq "Sakshi")
        println("Name:${names.toString()}")

        //Method 2 to execute query
        val search=BasicDBObject()
        search.put("Name","Sakshi")
        val cursor=table.find(search)
        while(cursor.hasNext()) {
            val doc=cursor.next()
            val Uname=doc.get("Name")
            val Email=doc.get("Email")
            println("Name: $Uname Email $Email")
        }
    }
    catch (e: UnknownHostException) {
        e.printStackTrace ( )
    } catch (e: MongoException) {
        e.printStackTrace()
    }

}