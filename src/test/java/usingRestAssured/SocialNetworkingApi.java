package usingRestAssured;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class SocialNetworkingApi {
    @Test
    public void getSpecificSocialNetworkingComment(){
        given().contentType(ContentType.JSON).log().all().
                when().get("https://jsonplaceholder.typicode.com/comments/{id}", 2).
                then().log().all().statusCode(200).
                body("name", is("quo vero reiciendis velit similique earum")).
                body("email", is("Jayne_Kuhic@sydney.com")).
                body("body", is("est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et"));
    }
//    @Test
    public void postASocialNetworkingComment(){
        HashMap<String, String> pBody = new HashMap<>();
        pBody.put("postId", "1");
        pBody.put("name", "My second comment");
        pBody.put("email", "josephine.abdulsalam@sydney.com");
        pBody.put("body", "I like the post make by my best friend");

given().contentType(ContentType.JSON).log().all().with().body(pBody).
        when().post("https://jsonplaceholder.typicode.com/comments").
        then().log().all().statusCode(201).
        body("email", is("josephine.abdulsalam@sydney.com")).
        body("body", is("I like the post make by my best friend"));
    }
}
