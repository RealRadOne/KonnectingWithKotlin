import Converter.UConvert
import Model.*
import DAO.*
import com.mongodb.*
import org.litote.kmongo.*
import java.net.UnknownHostException
import org.bson.BSON
import org.bson.types.ObjectId
import java.util.ArrayList
import com.mongodb.BasicDBObjectBuilder
import com.mongodb.DBObject





@Suppress("DEPRECATION")
class DAO(mongo:MongoClient)
{
    var col:DBCollection
    init {
        this.col=mongo.getDB("Java").getCollection("User")
    }
    //CREATE
    fun createUser(u:User):User
    {
         val doc=UConvert.toDBObject(u)
         col.insert(doc)
         return u
    }
    //UPDATE
    fun updateUser(u:User)
    {
        val query = BasicDBObjectBuilder.start().append("_id",u.id).get()
        this.col.update(query, UConvert.toDBObject(u))

    }
    //READ
    fun readAllUser():ArrayList<User>
    {
        val data = ArrayList<User>()
        val cursor=col.find()
        while(cursor.hasNext())
        {
            val doc=cursor.next()
            val u:User=UConvert.toUser(doc)
            data.add(u)
        }
        return data
    }

    //DELETE
    fun deleteUser(UserID:String)
    {
        val query = BasicDBObjectBuilder.start().append("_id",UserID).get()
        this.col.remove(query)
    }
}
