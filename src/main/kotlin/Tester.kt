import Model.User
import com.mongodb.MongoClient
import org.bson.BSON
//Need to figure out how to use BSON Properly in KOTLIN
fun main(args: Array<String>) {
        var u = User("U0013", "Akshita", Email = "ak@123.gmail.com")
        var mongo = MongoClient("localhost", 27017)
        //CREATE TESTING
        var udoc=DAO(mongo)
        udoc.createUser(u)
        println("User added with Name ${u.Name} and ${u.id}")
        //UPDATE TESTING
        udoc.updateUser(u)
        //READ TESTING
        val data:ArrayList<User> =udoc.readAllUser()
        for(u:User in data)
        {
            println("The users are ${u.Name}")
        }
        //DELETE TESTING
        udoc.deleteUser("U0013")

}