package com.mkyong.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/json/product")
public class JSONService {

    @GET
    @Path("/get")
    @Produces("application/json")
    public Product getProductInJSON() {

        Product product = new Product();
        product.setName("TEST");
        return product;
    }

    @GET
    @Path("/average")
    @Produces("application/json")
    public String wow() {

        getData data = new getData();
        data.getcon();
        String alllof = data.getLat1() + "," + data.getLong1() + "," + data.getLat2() + "," + data.getLong2() + "," + data.getLat3() + "," + data.getLong3() + "," + data.getLat4() + "," + data.getLong4() + "," + data.getLat5() + "," + data.getLong5();

        //String alllof = "{"+"Lat1:"+data.getLat1()+","+"Long1:"+data.getLong1()+ ","+"Lat2:"+data.getLat2()+ ","+"Long2:"+data.getLong2()+","+"Lat3:"+data.getLat3()+ ","+"Long3:"+data.getLong3()+ ","+"Lat4:"+data.getLat4()+ ","+"Long4:"+data.getLong4()+ ","+"Lat5:"+data.getLat5()+ ","+"Long5:"+data.getLong5()+"}";

        data.setMidpoint(Double.parseDouble(data.getLat1()), Double.parseDouble(data.getLong1()), Double.parseDouble(data.getLat2()), Double.parseDouble(data.getLong2()));


        double average_lat = (Double.parseDouble(data.getLat1()) + Double.parseDouble(data.getLat2()) + Double.parseDouble(data.getLat3()) + Double.parseDouble(data.getLat4()) + Double.parseDouble(data.getLat5()) + Double.parseDouble(data.getLat6()) + Double.parseDouble(data.getLat7()) + Double.parseDouble(data.getLat8()) + Double.parseDouble(data.getLat9()) + Double.parseDouble(data.getLat10())) / 10;
        double average_long = (Double.parseDouble(data.getLong1()) + Double.parseDouble(data.getLong2()) + Double.parseDouble(data.getLong3()) + Double.parseDouble(data.getLong4()) + Double.parseDouble(data.getLong5()) + Double.parseDouble(data.getLong6()) + Double.parseDouble(data.getLong7()) + Double.parseDouble(data.getLong8()) + Double.parseDouble(data.getLong9()) + Double.parseDouble(data.getLong10())) / 10;



        String input = "{\n" + " \"lat1\": \"" + data.getLat1() + "\",\n" +
            "    \"long1\": \"" + data.getLong1() + "\"\n" +
            "}";

        return input;
    }

    @GET
    @Path("/averagecycle")
    @Produces("application/json")
    public String wowe() {

        getData_cycle data = new getData_cycle();
        data.getcon();
        String alllof = data.getLat1() + "," + data.getLong1() + "," + data.getLat2() + "," + data.getLong2() + "," + data.getLat3() + "," + data.getLong3() + "," + data.getLat4() + "," + data.getLong4() + "," + data.getLat5() + "," + data.getLong5();

        //String alllof = "{"+"Lat1:"+data.getLat1()+","+"Long1:"+data.getLong1()+ ","+"Lat2:"+data.getLat2()+ ","+"Long2:"+data.getLong2()+","+"Lat3:"+data.getLat3()+ ","+"Long3:"+data.getLong3()+ ","+"Lat4:"+data.getLat4()+ ","+"Long4:"+data.getLong4()+ ","+"Lat5:"+data.getLat5()+ ","+"Long5:"+data.getLong5()+"}";

        data.setMidpoint(Double.parseDouble(data.getLat1()), Double.parseDouble(data.getLong1()), Double.parseDouble(data.getLat2()), Double.parseDouble(data.getLong2()));


        double average_lat = (Double.parseDouble(data.getLat1()) + Double.parseDouble(data.getLat2()) + Double.parseDouble(data.getLat3()) + Double.parseDouble(data.getLat4()) + Double.parseDouble(data.getLat5()) + Double.parseDouble(data.getLat6()) + Double.parseDouble(data.getLat7()) + Double.parseDouble(data.getLat8()) + Double.parseDouble(data.getLat9()) + Double.parseDouble(data.getLat10())) / 10;
        double average_long = (Double.parseDouble(data.getLong1()) + Double.parseDouble(data.getLong2()) + Double.parseDouble(data.getLong3()) + Double.parseDouble(data.getLong4()) + Double.parseDouble(data.getLong5()) + Double.parseDouble(data.getLong6()) + Double.parseDouble(data.getLong7()) + Double.parseDouble(data.getLong8()) + Double.parseDouble(data.getLong9()) + Double.parseDouble(data.getLong10())) / 10;



        String input = "{\n" + " \"lat1\": \"" + data.getLat1() + "\",\n" +
            "    \"long1\": \"" + String.valueOf(data.getLong1()) + "\"\n" +
            "}";

        return input;
    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createProductInJSON(Product product) {

        product.getcon();
        String result = " LAT: " + product.lat +
            "  Long: " + product.lng +
            "  Cycler: " + product.name;
        return Response.status(201).entity(result).build();

    }

    @POST
    @Path("/post/runner")
    @Consumes("application/json")
    public Response createProductInJSON(Product_runner product) {

        product.getcon();
        String result = " LAT: " + product.lat +
            "  Long: " + product.lng +
            "  Runner: " + product.name;
        return Response.status(201).entity(result).build();

    }

}
