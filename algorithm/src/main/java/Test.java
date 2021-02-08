import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str="JpgUNwIBAAAxfJgfQ%2BDC1ZzDgUYs1T%2Fs1ujVQwAEYH98C9ppOZe4NvgmUOQfvO2YceOBj8zORl6%2B9XlLZwTtMupEaj9WZ2vSbGVV1AgqEwTKq0VWfGdKT4f9MOQqu8XmURfzqdVeRa2RJBpZ89XloVwPiFTcKRgV4d44EJJd6xJtfv0Lo3NoFYG%2BYrPtCGpeVBlwy9wtarwQNVR3rDOoPdBJGFlWQyIqxGVvScXIa%2Bs%2B7myffSsVvuTdPtxfAPiXeONE92e8wKAavY2LJnAIT%2BXTfPjhZz%2F4n6Vh1%2FQRKSO2yG8eR4VyCiIVUqjY%2BzYRSEg3UDdmbw50kEX4T3mF%2FxyEeZQZkP2C3eA=";
        System.out.println(str);
        System.out.println();
        String s=java.net.URLDecoder.decode(str,"UTF-8");
        System.out.println(s);
        System.out.println();
        String ss=java.net.URLEncoder.encode(s,"UTF-8");
        System.out.println(ss);
    }
}
