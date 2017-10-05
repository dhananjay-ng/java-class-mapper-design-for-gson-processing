package student.rest;

public class QueryParserTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
      String json="{\"$or\":[{\"name\":\"Amar\",\"age\":\"5\"},{\"usertype\":\"Admin\"}],\"company_code\":{\"$ne\":\"CRPA\"}}";
       json="{\"company_code\":{\"$ne\":\"CRPA\"}}";
      //json="{\"$or\":[{\"name\":\"Amar\",\"age\":\"5\",\"$or\":[{\"name\":{\"$ne\":\"samar\"}},{\"company\":\"sap\"}]},{\"usertype\":\"Admin\"}],\"company_code\":{\"$ne\":\"CRPA\"}}";
    //  json="{\"age\":{\"$in\":[1,4,8,6]}}";  
       json="{\"company_code\":\"CRPA\"}";
       json="{\"age\":{\"$nin\":[1,4,8,6]}}";
       json="{\"company_code\":{\"$ne\":\"CRPA\"},\"age\":{\"$in\":[1,4,8,6]}}";
      new QueryParser().parse(json);

    }

}
