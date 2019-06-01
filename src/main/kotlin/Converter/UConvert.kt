package Converter
import Model.*
import org.bson.types.ObjectId
import com.mongodb.BasicDBObjectBuilder
import com.mongodb.DBObject
import org.bson.BSON
import org.litote.kmongo.json

object UConvert
{
    fun toDBObject(u:User):DBObject
    {
        val value=BasicDBObjectBuilder.start()
        value.append("_id",u.id)
        value.append("Name",u.Name)
        value.append("Email",u.Email)
        return value.get()
    }
    fun toUser(doc:DBObject):User
    {
        val u=User(doc.get("_id").toString(),doc.get("Name").toString(),doc.get("Email").toString())
        return u
    }
}