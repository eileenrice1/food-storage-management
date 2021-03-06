package edu.csu.cs370.foodStorage;
import spark.Spark;
import spark.Request;
import spark.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.ArrayList;

import com.google.gson.*;

public class RestfulServer 
{
	private final Logger log = LoggerFactory.getLogger(RestfulServer.class);
	private int port;
	private FoodStorage database;
	private Gson gson;
    
    public RestfulServer(int port)
    {
		this.port = port;
		this.database = new FoodStorage("foodStorage.json");
		this.gson = new GsonBuilder().setDateFormat("dd MMM yyyy").create();
		this.configureResfulApiServer();
		this.processRestfulApiRequests();
    }

    private void configureResfulApiServer()
    {
		Spark.port(this.port);
		System.out.println("Server configured to run on port " + this.port);
    }

    private void processRestfulApiRequests()
    {
		Spark.post("/D2", this::printBodyAndEcho);
		Spark.post("/additem", this::addItem);
		Spark.post("/takeitem", this::takeItem);
		Spark.get("/show/all", this::showAll);
		Spark.get("/show/before", this::getExpiresBefore);
		Spark.get("/", this::echoRequest);
	}
	
	private String addItem(Request request, Response response)
	{
		this.database.addItem(this.gson.fromJson(request.body(), Item.class));
		return printBodyAndEcho(request, response);
	}

	private String takeItem(Request request, Response response)
	{
		return "" + this.database.removeFromItem(this.gson.fromJson(request.body(), Item.class)) + '\n';
	}

	private String showAll(Request request, Response response)
	{
		System.out.println(response.body());
		this.respondWithJson(response);
		return this.gson.toJson(this.database.getItems()) + '\n';
	}

	private String getExpiresBefore(Request request, Response response)
	{
		System.out.println("Getting items expiring before " + request.body());
		this.respondWithJson(response);

		Date d = this.gson.fromJson(request.body(), Date.class);
		ArrayList<Item> items = new ArrayList();
		for (Item i : this.database.getItems())
		{
			if (i.getExpirationDate().compareTo(d) <= 0) items.add(i);
		}
		return this.gson.toJson(items) + '\n';
	}

    private String printBodyAndEcho(Request request, Response response)
    {
		System.out.println(request.body());
		return echoRequest(request, response);
    }

    private String echoRequest(Request request, Response response)
    {
		this.respondWithJson(response);
		return HttpRequestToJson(request);
	}
	
	private void respondWithJson(Response response)
	{
		response.type("application/json");
		response.header("Access-Control-Allow-Origin", "*");
		response.status(200);
	}

    private String HttpRequestToJson(Request request)
    {
		return "{\n"
			+ "\"attributes\":\""    + request.attributes()    + "\",\n"
			+ "\"body\":\""          + request.body()          + "\",\n"
			+ "\"contentLength\":\"" + request.contentLength() + "\",\n"
			+ "\"contentType\":\""   + request.contentType()   + "\",\n"
			+ "\"contextPath\":\""   + request.contextPath()   + "\",\n"
			+ "\"cookies\":\""       + request.cookies()       + "\",\n"
			+ "\"headers\":\""       + request.headers()       + "\",\n"
			+ "\"host\":\""          + request.host()          + "\",\n"
			+ "\"ip\":\""            + request.ip()            + "\",\n"
			+ "\"params\":\""        + request.params()        + "\",\n"
			+ "\"pathInfo\":\""      + request.pathInfo()      + "\",\n"
			+ "\"serverPort\":\""    + request.port()          + "\",\n"
			+ "\"protocol\":\""      + request.protocol()      + "\",\n"
			+ "\"queryParams\":\""   + request.queryParams()   + "\",\n"
			+ "\"requestMethod\":\"" + request.requestMethod() + "\",\n"
			+ "\"scheme\":\""        + request.scheme()        + "\",\n"
			+ "\"servletPath\":\""   + request.servletPath()   + "\",\n"
			+ "\"session\":\""       + request.session()       + "\",\n"
			+ "\"uri\":\""           + request.uri()           + "\",\n"
			+ "\"url\":\""           + request.url()           + "\",\n"
			+ "\"userAgent\":\""     + request.userAgent()     + "\",\n"
			+ "}\n";
    }
    
    public static void main( String[] args )
    {
        System.out.println( "Attempting to start server" );
		RestfulServer server = new RestfulServer(8080);
    }
}
