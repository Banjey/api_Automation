package Step_Definitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import utilities.RequestBodyServices;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SocialNetworkingStepDef extends BaseSteps{
    Response responseForGetService, responseForGetComment;
    RequestBodyServices requestBodyServices;
    DocumentContext requestBody;
    Response responseForPostAComment;

    @Given("service is up and running")
    public void service_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(serviceUrl);
        getCall();
        responseForGetService =getResponse();
        assertThat(responseForGetService.statusCode(), Is.is(200));
    }

    @When("i search for {string} of a comment with a GET method")
    public void i_search_for_of_a_comment_with_a_get_method(String id) {
        setHeadersWithContentType();
        setEndpointPath(makeCommentEndpoint + id);
        getCall();
        responseForGetComment =getResponse();

    }
    @Then("i should get the correct {string}, {string}, {string} and {string} returned with status code of {int}")
    public void i_should_get_the_correct_and_returned_with_status_code_of(String id, String name, String email, String body, Integer sCode) {
        assertThat(responseForGetComment.statusCode(),Is.is(sCode));
        assertThat(responseForGetComment.body().jsonPath().get("id"), is(Integer.parseInt(id)));
        assertThat(responseForGetComment.body().jsonPath().get("name"), is (name));
        assertThat(responseForGetComment.body().jsonPath().get("email"), is (email));
        assertThat(responseForGetComment.body().jsonPath().get("body"), is (body));

    }

    @When("I create a new comment with the following details {string},{string}, {string} and {string},")
    public void iCreateANewCommentWithTheFollowingDetailsAnd(String postId, String name, String email, String body) {
        setHeadersWithContentType();
        setEndpointPath(makeCommentEndpoint);
        requestBodyServices = new RequestBodyServices();
        requestBody = loadJsonTemplate(MakeACommentPayload);

        requestBodyServices.setRequestBodyForNewComment(requestBody,postId, name, email, body);

        getPostCall();
        responseForPostAComment =getResponse();
    }

    @Then("i should get the correct {string},{string}, {string} and {string} returned and with status code of {int}")
    public void iShouldGetTheCorrectAndReturnedAndWithStatusCodeOf(String pId, String Name, String Email, String Body, int sCode) {
        assertThat(responseForPostAComment.statusCode(), is(sCode));
        assertThat(responseForPostAComment.body().jsonPath().get("postId"), is (pId));
        assertThat(responseForPostAComment.body().jsonPath().get("name"), is (Name));
        assertThat(responseForPostAComment.body().jsonPath().get("email"), is (Email));
        assertThat(responseForPostAComment.body().jsonPath().get("body"), is (Body));
    }
}
